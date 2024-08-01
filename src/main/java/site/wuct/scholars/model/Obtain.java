package site.wuct.scholars.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "obtain")
@Getter
@Setter
public class Obtain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pid")
    private Integer person;

    @Column(name = "grant_id")
    private Integer grant;
}
