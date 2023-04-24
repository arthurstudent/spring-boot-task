package com.task.springbootuniversity.io.entity;

//import jakarta.persistence.*;

import com.task.springbootuniversity.io.entity.enums.Degree;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "lectors")
@NoArgsConstructor
@Getter
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Degree degree;

    @Column
    private int salary;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "lector_department",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    private Set<Department> departments = new HashSet<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
