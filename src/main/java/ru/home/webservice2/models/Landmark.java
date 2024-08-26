
package ru.home.webservice2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "enum_postgressql", typeClass = EnumTypePostgreSql.class)
@Table(name = "landmark")
public class Landmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    //@Temporal(TemporalType.DATE)
    @Column(name = "data")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @org.hibernate.annotations.Type(type = "enum_postgressql")
    private Type type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "landmark_service",
            joinColumns = { @JoinColumn(name = "landmark_id") },
            inverseJoinColumns = { @JoinColumn(name = "service_id") })
    private List<Service> service= new ArrayList<>();

//    @NotEmpty
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "cityLandmark",
//            joinColumns = { @JoinColumn(name = "landmark_id") },
//            inverseJoinColumns = { @JoinColumn(name = "city_id") })
//    private Set<City> city = new HashSet<>();

//    @ManyToMany(mappedBy = "City")
//    private List<City> landmark;

//    @ManyToMany(mappedBy = "Service")
//    private List<Service> service;
}
