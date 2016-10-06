package sample.jpa;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class EmbeddableAlpha implements Serializable {
    @Column(name="alpha_value")
    private String value;

    @Embedded
    private EmbeddableBeta beta;

    public EmbeddableAlpha(String value, EmbeddableBeta beta) {
        this.value = value;
        this.beta = beta;
    }
}
