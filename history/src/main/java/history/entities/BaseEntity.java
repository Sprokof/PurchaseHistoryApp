package history.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public BaseEntity(){}

    public boolean isNew() {
        return id == null;
    }

    public Long getId() {
        return id;
    }

    public long id() {
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof BaseEntity entity)) return false;
        return Objects.equals(this.id, entity.id);
    }
}
