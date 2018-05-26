package se.jmkit.rest.common.string;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Helper class that uses Reflection to create a String representation of an object. The String is pretty-formatted and contains all the properties and their
 * values in the object, its references and super classes. This is used for logging and debugging.
 * 
 * @author Gareth Woodham, Capgemini Sweden AB
 */
public abstract class ToStringConverter {
    public static final String LINE_BREAK = System.getProperty("line.separator");
    public static final char TAB = '\t';

    private ToStringConverter() {

    }

    // list of field names which should not be printed, typically JPA stuff
    private static final Collection<String> NO_PRINT = new ArrayList<String>();
    static {
        NO_PRINT.add("pcInheritedFieldCount");
        NO_PRINT.add("pcFieldNames");
        NO_PRINT.add("pcFieldTypes");
        NO_PRINT.add("pcFieldFlags");
        NO_PRINT.add("pcPCSuperclass");
        NO_PRINT.add("pcStateManager");
        NO_PRINT.add("pcDetachedState");
        NO_PRINT.add("pcStateManager");
        NO_PRINT.add("$jacocoData");
        NO_PRINT.add("$jacocoInit");
    }

    /**
     * Dump the object to a String.
     * 
     * @param obj The object
     * @return A string representation of the object
     */
    @SuppressWarnings("all")
    public static final String toString(Object obj) {
        StringBuilder buf = new StringBuilder();

        // fetch the class and name
        Class<? extends Object> clazz = obj.getClass();
        String className = clazz.getSimpleName();

        buf.append(className + " [");

        // iterate up through any superclasses
        do {
            // fetch all the fields for this class
            Field[] fields = clazz.getDeclaredFields();

            // handle even private fields
            AccessibleObject.setAccessible(fields, true);

            // loop through the fields
            for (Field field : fields) {
                // skip constants (normally declared 'static final')
                boolean isStatic = Modifier.isStatic(field.getModifiers());
                boolean isFinal = Modifier.isFinal(field.getModifiers());
                if (isStatic && isFinal) {
                    continue;
                }

                // skip fields decorated with the NonPrintable annotation
                // (to prevent "back references" from causing circular
                // dependencies)
                if (field.getAnnotation(NonPrintable.class) != null) {
                    continue;
                }

                // skip uninteresting fields specified in Map (JPA junk)
                String fieldName = field.getName();
                if (NO_PRINT.contains(fieldName)) {
                    continue;
                }

                // skip JPA rubbish, eg
                // "class$Lse$rsv$em$ref$entity$EmReCnCodeEO"
                if (fieldName != null && fieldName.startsWith("class$")) {
                    continue;
                }

                try {
                    Object thisObj = field.get(obj);
                    buf.append(field.getName() + "=" + thisObj.toString());
                } catch (Exception e) {
                    buf.append(e.getMessage());
                }
                buf.append(",");
            }
            clazz = clazz.getSuperclass();
        } while (clazz != null && clazz != Object.class);
        return buf.substring(0, buf.length() - 1).toString() + "]";
    }
}
