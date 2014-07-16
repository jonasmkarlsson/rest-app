package se.jmkit.rest.common.entity;

import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({ "create", "read", "update", "delete" })
public class SecurityAccess {

    @Transient
    private boolean create;

    @Transient
    private boolean read;

    @Transient
    private boolean update;

    @Transient
    private boolean delete;

    public SecurityAccess() {
        this(true, true, true, true);
    }

    public SecurityAccess(final boolean create, final boolean read, final boolean update, final boolean delete) {
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
    }

    /**
     * @return the create
     */
    public boolean isCreate() {
        return create;
    }

    /**
     * @param create the create to set
     */
    public void setCreate(boolean create) {
        this.create = create;
    }

    /**
     * @return the read
     */
    public boolean isRead() {
        return read;
    }

    /**
     * @param read the read to set
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    /**
     * @return the update
     */
    public boolean isUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(boolean update) {
        this.update = update;
    }

    /**
     * @return the delete
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (create ? 1231 : 1237);
        result = prime * result + (delete ? 1231 : 1237);
        result = prime * result + (read ? 1231 : 1237);
        result = prime * result + (update ? 1231 : 1237);
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SecurityAccess [create=" + create + ", read=" + read + ", update=" + update + ", delete=" + delete + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SecurityAccess)) {
            return false;
        }
        SecurityAccess other = (SecurityAccess) obj;
        if (create != other.create) {
            return false;
        }
        if (delete != other.delete) {
            return false;
        }
        if (read != other.read) {
            return false;
        }
        if (update != other.update) {
            return false;
        }
        return true;
    }

}
