package sample.jpa;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="table_alpha")
@NoArgsConstructor
@ToString
public class EntityAlpha implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="table_beta_id")
    private EntityBeta beta;

    public EntityAlpha(String name, EntityBeta beta) {
        this.name = name;
        this.beta = beta;
    }

    public void update(String name) {
        this.name = name;
        this.beta.update(name);
    }

}
