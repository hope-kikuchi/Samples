package sample.jpa;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="table_beta")
@NoArgsConstructor
@ToString(callSuper = true)
public class EntityBeta extends EntityAlpha implements Serializable {
    @Column(name="beta_code")
    protected String code;

    public EntityBeta(String name, String code) {
        super(name);
        this.code = code;
    }

    @Override
    public void update(String name) {
        this.name = "Update{" + name + "}";
        this.code = "Update[" + name + "]";
    }
}
