package sample.jpa;

import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="table_alpha")
@NoArgsConstructor
public class EntityAlpha implements Serializable {
    @EmbeddedId
    private EmbeddableId id;

    private String name;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="alpha_id", referencedColumnName="id")
    private List<EntityBeta> betaList;

    @PrePersist
    private void prePersist() {
        this.id = new EmbeddableId();
    }

    public EntityAlpha(String name, EntityBeta... beta) {
        this.name = name;
        this.betaList = java.util.Arrays.asList(beta);
    }

    public void update(String name) {
        this.name = name;

        for (int i = 0; i < this.betaList.size(); i++) {
            this.betaList.get(i).update(name + "[" + i + "]");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{id=" + id + ", name=" + name + ", betaList=[");

        String text = this.betaList.stream().map(EntityBeta::toString).collect(java.util.stream.Collectors.joining(", "));
        sb.append(text).append("]}");

        return sb.toString();
    }

    public void delete() {
        if (0 < this.betaList.size()) {
            this.betaList.remove(0);
        }
    }

    public String id() {
        return "" + this.id;
    }
}
