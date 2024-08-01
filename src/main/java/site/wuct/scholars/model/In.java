package site.wuct.scholars.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "In")
@Getter
@Setter
public class In {
    @Id
    private Long id;

    @Column(name = "pid")
    private Integer person;

    @Column(name = "loc_id")
    private Integer location;
}
