package se.jmkit.rest.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.AbstractPersistable;

import se.jmkit.rest.common.string.NonPrintable;
import se.jmkit.rest.common.string.ToStringConverter;

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractEntity<T> extends AbstractPersistable<Long> implements Serializable {

    private static final long serialVersionUID = 9213828076223625203L;

    @NonPrintable
    @Column(name = "creation_time", nullable = false)
    private Date creationTime;

    @NonPrintable
    @Column(name = "modification_time", nullable = false)
    private Date modificationTime;

    @NonPrintable
    @Version
    private long version = 0;

    @NonPrintable
    @Transient
    private SecurityAccess securityAccess;

    public AbstractEntity() {
        securityAccess = new SecurityAccess();
    }

    public abstract void update(T entity);

    @PreUpdate
    public void preUpdate() {
        modificationTime = new Date();
    }

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        creationTime = now;
        modificationTime = now;
    }

    // @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCreationTime() {
        return creationTime;
    }

    // @JsonSerialize(using = CustomDateSerializer.class)
    public Date getModificationTime() {
        return modificationTime;
    }

    public long getVersion() {
        return version;
    }

    /**
     * @return the securityAccess
     */
    public SecurityAccess getSecurityAccess() {
        return securityAccess;
    }

    /**
     * @param securityAccess the securityAccess to set
     */
    public void setSecurityAccessEntity(SecurityAccess securityAccess) {
        this.securityAccess = securityAccess;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString(java.lang.Object)
     */
    @Override
    public String toString() {
        return ToStringConverter.toString(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
        result = prime * result + ((modificationTime == null) ? 0 : modificationTime.hashCode());
        result = prime * result + (int) (version ^ (version >>> 32));
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    @SuppressWarnings("all")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof AbstractEntity)) {
            return false;
        }
        AbstractEntity<T> other = (AbstractEntity<T>) obj;
        if (creationTime == null) {
            if (other.creationTime != null) {
                return false;
            }
        } else if (!creationTime.equals(other.creationTime)) {
            return false;
        }
        if (modificationTime == null) {
            if (other.modificationTime != null) {
                return false;
            }
        } else if (!modificationTime.equals(other.modificationTime)) {
            return false;
        }
        if (version != other.version) {
            return false;
        }
        return true;
    }

}
