package com.task.springbootuniversity.io.repository;

import com.task.springbootuniversity.io.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

    @Query(value = "select full_name from lectors where full_name LIKE %:keyword%", nativeQuery = true)
    List<String> findByTemplate(@Param("keyword") String keyword);

}
