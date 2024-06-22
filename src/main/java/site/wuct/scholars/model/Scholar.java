package site.wuct.scholars.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "people")
public class Scholar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "institute_id", nullable = false)
    private Institute institute;

    @OneToMany(mappedBy = "author")
    private Set<Publication> publications;

    @OneToMany(mappedBy = "recipient")
    private Set<Grant> grants;

    // Standard getters and setters
}
    
