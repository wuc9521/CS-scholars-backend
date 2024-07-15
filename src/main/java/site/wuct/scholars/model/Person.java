package site.wuct.scholars.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "people")
@Getter
@Setter
public class Person {
    @Id
    @Column(name = "pid")
    private Integer pid; 

    @Column(name = "name")
    private String name; 

    @Column(name = "major")
    private String majorarea;

    @Column(name = "hindex")
    private Integer hindex; 

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Person{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", majorarea='" + majorarea + '\'' +
                ", hindex=" + hindex +
                '}' + "\n";
    }
}
