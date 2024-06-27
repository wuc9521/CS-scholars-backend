package site.wuct.scholars.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "grants")
public class Grants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long grantid;

    @Column(name = "budget_start")
    private Date budgetStart; 
}
