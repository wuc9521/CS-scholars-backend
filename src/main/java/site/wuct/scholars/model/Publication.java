package site.wuct.scholars.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "publications")
@Getter
@Setter
public class Publication {
    @Id
    private Long pubid;

    @Column(name = "pmid")
    private String pmid;

    @Column(name = "doi")
    private String doi; 

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Publication{" +
                "pubid=" + pubid +
                ", pmid='" + pmid + '\'' +
                ", doi='" + doi + '\'' +
                '}' + "\n";
    }
}
