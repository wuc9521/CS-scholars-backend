package site.wuct.scholars.model;

import javax.persistence.*;

@Entity
@Table(name = "publication")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "year_published")
    private Integer yearPublished;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Scholar author;
}
