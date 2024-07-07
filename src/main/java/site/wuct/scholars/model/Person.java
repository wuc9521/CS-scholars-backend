package site.wuct.scholars.model;

import javax.persistence.*;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @Column(name = "pid")
    private Integer pid; 

    @Column(name = "name")
    private String name; 

    @Column(name = "major")
    private String majorarea;

    @Column(name = "hindex")
    private Integer hindex; 
}
