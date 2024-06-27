package site.wuct.scholars.model;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Locations {
    @Id
    private Integer locid;

    @Column(name = "location")
    private String location;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;
}


// locid,location,city,state,country