package site.wuct.scholars.model;

import javax.persistence.*;

@Entity
@Table(name = "publication")
public class Publication {
    @Id
    private Long pubid;

    @Column(name = "pmid")
    private String pmid;

    @Column(name = "doi")
    private String doi; 

    @Column(name = "pid")
    private Integer pid;
}
