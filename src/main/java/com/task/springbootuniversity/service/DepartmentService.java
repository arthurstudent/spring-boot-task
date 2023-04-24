package com.task.springbootuniversity.service;

import com.task.springbootuniversity.io.entity.Lector;
import com.task.springbootuniversity.io.entity.enums.Degree;

import java.util.HashMap;

public interface DepartmentService {

    Lector findHeadOfDepartment(String departmentName);

    HashMap<Degree, Long> countDepartmentStatistics(String departmentName);

    Double countAverageSalary(String departmentName);

    Integer countOfEmployeeByDepartment(String departmentName);
}
