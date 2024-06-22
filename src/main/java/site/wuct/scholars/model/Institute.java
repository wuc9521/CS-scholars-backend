package site.wuct.scholars.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "institute")
public class Institute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "institute")
    private Set<Scholar> people;
}
