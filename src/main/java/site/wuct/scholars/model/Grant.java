package site.wuct.scholars.model;
import javax.persistence.*;

@Entity
@Table(name = "grants")
public class Grant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private Scholar recipient;
}
