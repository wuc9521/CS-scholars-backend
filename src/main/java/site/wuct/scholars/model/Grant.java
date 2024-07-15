package site.wuct.scholars.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "grants")
@Getter
@Setter
public class Grant {
    @Id
    private Long grantid;

    @Column(name = "budget_start")
    private Date budgetStart;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Grant{" +
                "grantid=" + grantid +
                ", budgetStart=" + budgetStart +
                '}' + "\n";
    }
}
