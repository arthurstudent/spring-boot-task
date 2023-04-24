package com.task.springbootuniversity.io.repository;

import com.task.springbootuniversity.io.entity.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @EntityGraph("lectors-entity-graph")
    Department findByName(String name);

    @Query(value = "select count(l.id) from lectors as l inner join lector_department as ld on l.id = ld.lector_id " +
            "inner join departments as d on ld.department_id = d.id where d.name = :name", nativeQuery = true)
    Integer findCountByName(String name);
}
