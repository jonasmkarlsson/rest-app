package se.jmkit.rest.common.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({ "size", "clazz", "entities" })
public class JSONWrapperEntity<T extends AbstractEntity<T>> {

    private int size;
    private String clazz;
    private List<T> entities;

    /**
     * @return the entities
     */
    public List<T> getEntities() {
        return entities;
    }

    /**
     * @param entity the entities to set
     */
    public void setEntities(List<T> entities) {
        this.entities = entities;
        this.size = entities.size();
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the clazz
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

}
