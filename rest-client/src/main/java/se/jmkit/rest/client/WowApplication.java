package se.jmkit.rest.client;

import java.util.List;
import java.util.StringTokenizer;

import se.jmkit.rest.client.http.RestURIBuilder;
import se.jmkit.rest.client.http.controller.PersonControllerClient;
import se.jmkit.rest.common.constants.Constant;
import se.jmkit.rest.common.entity.Person;

import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class WowApplication extends Application {
    private static final long serialVersionUID = 2192808513028842236L;
    private String schema = "http://";
    private String host = "localhost:8080";
    private String path = "/jmkit-app-rest/rest/person";

    private RestURIBuilder restURIBuilder = new RestURIBuilder(schema, host, path);

    private Table contactList = new Table();
    private Form contactEditor = new Form();
    private HorizontalLayout bottomLeftCorner = new HorizontalLayout();
    private Button contactRemovalButton;
    private IndexedContainer companyDirectoryData = retrieveCompanyDirectoryData();

    boolean updateFlag = false;

    public enum Column {
        // , age("Age"), company("Company"), companyId("CompanyId");
        id("Id"), first("First name"), last("Last name");
        private static Column[] visible = { first, last };
        private String displayName;

        public static String[] getVisibleColumnNames() {
            String[] visibleColumnNames = new String[visible.length];
            int i = 0;
            for (Column column : visible) {
                visibleColumnNames[i++] = column.displayName;
            }
            return visibleColumnNames;
        }

        Column(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public static Column[] getVisibleColumns() {
            return visible;
        }
    }

    @Override
    public void init() {
        initLayout();
        initContactAddRemoveButtons();
        initAddressList();
        initFilteringControls();
    }

    private void initLayout() {
        SplitPanel splitPanel = new SplitPanel(SplitPanel.ORIENTATION_HORIZONTAL);
        setMainWindow(new Window("Address Book", splitPanel));
        // Left Side
        VerticalLayout left = new VerticalLayout();
        left.setSizeFull();
        left.addComponent(contactList);
        contactList.setSizeFull();
        left.setExpandRatio(contactList, 1);
        splitPanel.addComponent(left);
        bottomLeftCorner.setWidth("100%");
        left.addComponent(bottomLeftCorner);
        // other side
        splitPanel.addComponent(contactEditor);
        contactEditor.setSizeFull();
        contactEditor.getLayout().setMargin(true);
        contactEditor.setImmediate(true);
        contactEditor.setFormFieldFactory(new PersonFactory());
    }

    @SuppressWarnings("serial")
    private void initContactAddRemoveButtons() {
        // New item button
        bottomLeftCorner.addComponent(new Button("+", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                Object id = contactList.addItem();
                contactList.setValue(id);
            }
        }));

        // Remove item button
        contactRemovalButton = new Button("-", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                final Item item = contactList.getItem(contactList.getValue());
                final String personId = (String) item.getItemProperty(Column.id.displayName).getValue();
                contactList.removeItem(contactList.getValue());
                contactList.select(null);
            }
        });
        contactRemovalButton.setVisible(false);
        bottomLeftCorner.addComponent(contactRemovalButton);
    }

    @SuppressWarnings("serial")
    private void initAddressList() {
        contactList.setContainerDataSource(companyDirectoryData);
        contactList.setVisibleColumns(Column.getVisibleColumnNames());
        contactList.setSelectable(true);
        contactList.setImmediate(true);

        contactList.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                Object id = contactList.getValue();
                updateFlag = true;
                contactEditor.setItemDataSource(id == null ? null : contactList.getItem(id));
                contactRemovalButton.setVisible(id != null);
                updateFlag = false;
            }
        });
    }

    @SuppressWarnings("serial")
    private void initFilteringControls() {
        for (final Column column : Column.getVisibleColumns()) {
            final TextField sf = new TextField();
            bottomLeftCorner.addComponent(sf);
            sf.setWidth("100%");
            sf.setInputPrompt(column.displayName);
            sf.setImmediate(true);
            bottomLeftCorner.setExpandRatio(sf, 1);
            sf.addListener(new Property.ValueChangeListener() {
                public void valueChange(ValueChangeEvent event) {
                    companyDirectoryData.removeContainerFilters(column.displayName);
                    if (sf.toString().length() > 0 && !column.displayName.equals(sf.toString())) {
                        companyDirectoryData.addContainerFilter(column.displayName, sf.toString(), true, false);
                    }
                    getMainWindow().showNotification("" + companyDirectoryData.size() + " matches found");
                }
            });
        }
    }

    @SuppressWarnings("serial")
    private class PersonFactory extends DefaultFieldFactory {

        public PersonFactory() {
        }

        @Override
        public Field createField(final Item item, Object propertyId, Component uiContext) {
            Field result = null;
            result = super.createField(item, propertyId, uiContext);
            if (Column.id.displayName.equals(propertyId)) { // || Column.companyId.displayName.equals(propertyId)) {
                result.setVisible(false);
            }
            // }
            result.addListener(new Property.ValueChangeListener() {
                public void valueChange(ValueChangeEvent event) {
                    if (!updateFlag && isValid(item)) {
                        updateFlag = true;
                        getMainWindow().showNotification("Updated Record: " + (String) item.getItemProperty(Column.id.displayName).getValue());
                        WowApplication.this.updateDB(item);
                        updateFlag = false;
                    }
                }
            });
            return result;
        }
    }

    public boolean isValid(Item item) {
        String first = (String) item.getItemProperty(Column.first.displayName).getValue();
        String last = (String) item.getItemProperty(Column.last.displayName).getValue();
        return !"".equals(first) && !"".equals(last);
    }

    private IndexedContainer retrieveCompanyDirectoryData() {
        List<Person> aperson = getPersons();
        IndexedContainer ic = new IndexedContainer();

        for (Column p : Column.values()) {
            ic.addContainerProperty(p.displayName, String.class, "");
        }
        for (Person person : aperson) {
            Object id = ic.addItem();
            StringTokenizer tokenizer = new StringTokenizer(person.getName());
            if (tokenizer.hasMoreElements()) {
                ic.getContainerProperty(id, Column.first.displayName).setValue(tokenizer.nextToken());
                if (tokenizer.hasMoreElements()) {
                    ic.getContainerProperty(id, Column.last.displayName).setValue(tokenizer.nextToken());
                }
            }
            ic.getContainerProperty(id, Column.id.displayName).setValue(person.getId());
        }
        return ic;
    }

    public void updateDB(final Item item) {
        String sid = (String) item.getItemProperty(Column.id.displayName).getValue();
        if (!"".equals(sid)) {
            Long id = Long.parseLong(sid);
            Person person = getPerson(id);
            populatePerson(item, person);
        } else {
            Person person = new Person();
            populatePerson(item, person);

            Person newPerson = createPersion(person);
            item.getItemProperty(Column.id.displayName).setValue(newPerson.getId());
        }
    }

    private List<Person> getPersons() {
        return new PersonControllerClient(restURIBuilder, "/" + Constant.PERSON).list();
    }

    private Person createPersion(Person person) {
        return new PersonControllerClient(restURIBuilder, "/" + Constant.PERSON).create(person);
    }

    private Person getPerson(Long id) {
        return new PersonControllerClient(restURIBuilder, "/" + Constant.PERSON).read(id);
    }

    private void populatePerson(Item item, Person person) {
        person.setFirstname((String) item.getItemProperty(Column.first.displayName).getValue());
        person.setLastname((String) item.getItemProperty(Column.last.displayName).getValue());
    }
}
