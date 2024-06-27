package site.wuct.scholars.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "grants")
public class Grants {
    @Id
    private Long grantid;

    @Column(name = "budget_start")
    private Date budgetStart; 
}
