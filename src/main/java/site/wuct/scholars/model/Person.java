package site.wuct.scholars.model;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid; 

    @Column(name = "name")
    private String name; 

    @Column(name = "major")
    private String major;

    @Column(name = "hindex")
    private Integer hindex; 
}
