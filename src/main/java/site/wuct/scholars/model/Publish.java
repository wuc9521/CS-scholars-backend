package site.wuct.scholars.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "publish")
@Getter
@Setter
public class Publish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pid")
    private Integer person;

    @Column(name = "pubid")
    private Integer publication;
}
