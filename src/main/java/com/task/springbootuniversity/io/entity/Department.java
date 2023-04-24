package com.task.springbootuniversity.io.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NamedEntityGraph(
        name = "lectors-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("lectors"),
                @NamedAttributeNode("headOfDepartment")
        }
)
@Entity(name = "departments")
@NoArgsConstructor
@Getter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "head_of_department_id")
    private Lector headOfDepartment;

    @ManyToMany(mappedBy = "departments")
    private Set<Lector> lectors = new HashSet<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setHeadOfDepartment(Lector headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public void addLector(Lector lector) {
        lectors.add(lector);
        lector.getDepartments().add(this);
    }
}
