package site.wuct.scholars.model;

import javax.persistence.*;

@Entity
@Table(name = "publication")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pubid;

    @Column(name = "pmid")
    private String pmid;

    @Column(name = "doi")
    private String doi; 
}
