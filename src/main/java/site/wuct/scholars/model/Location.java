package site.wuct.scholars.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "locations")
@Getter
@Setter
public class Location {
    @Id
    @Column(name = "locid")
    private Integer locid;

    @Column(name = "loc_name")
    private String locName;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Location{" +
                "locid=" + locid +
                ", locName='" + locName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}' + "\n";
    }
}
