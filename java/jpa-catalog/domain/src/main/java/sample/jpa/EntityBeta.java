package sample.jpa;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="table_beta")
@NoArgsConstructor
public class EntityBeta implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy="betaList")
    private List<EntityAlpha> alphaList;

    public EntityBeta(String name) {
        this.name = name;
    }

    public void update(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EntityBeta{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alphaList=[" + this.alphaList.stream().map(it -> "" + it.id()).collect(java.util.stream.Collectors.joining(", ")) + "]" +
                '}';
    }
}
