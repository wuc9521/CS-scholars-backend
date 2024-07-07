package site.wuct.scholars.model;

import javax.persistence.*;

@Entity
@Table(name = "publications")
public class Publication {
    @Id
    private Long pubid;

    @Column(name = "pmid")
    private String pmid;

    @Column(name = "doi")
    private String doi; 
}
