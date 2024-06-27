package site.wuct.scholars.model;

import javax.persistence.*;

@Entity
@Table(name = "people")
public class People {
    @Id
    @Column(name = "pid")
    private Integer pid; 

    @Column(name = "name")
    private String name; 

    @Column(name = "majorarea")
    private String majorarea;

    @Column(name = "hindex")
    private Integer hindex; 

    @Column(name = "locid")
    private Integer locid;
}
