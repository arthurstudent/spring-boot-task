package com.task.springbootuniversity.service.impl;

import com.task.springbootuniversity.exceptions.DepartmentNotFoundException;
import com.task.springbootuniversity.io.entity.Department;
import com.task.springbootuniversity.io.entity.Lector;
import com.task.springbootuniversity.io.entity.enums.Degree;
import com.task.springbootuniversity.io.repository.DepartmentRepository;
import com.task.springbootuniversity.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Lector findHeadOfDepartment(String departmentName) {
        Optional<Department> department = Optional.ofNullable(departmentRepository.findByName(departmentName));

        if (department.isEmpty()) {
            throw new DepartmentNotFoundException(String.format("Department %s is not found. Please check your input", departmentName));
        }

        return department.get().getHeadOfDepartment();
    }

    @Override
    public HashMap<Degree, Long> countDepartmentStatistics(String departmentName) {

        Optional<Department> department = Optional.ofNullable(departmentRepository.findByName(departmentName));

        return department
                .orElseThrow(() -> new DepartmentNotFoundException(String.format("Department %s is not found. Please check your input", departmentName)))
                .getLectors()
                .stream()
                .collect(groupingBy(Lector::getDegree, HashMap::new, Collectors.counting()));
    }

    @Override
    public Double countAverageSalary(String departmentName) {

        Optional<Department> department = Optional.ofNullable(departmentRepository.findByName(departmentName));

        if (department.isEmpty()) {
            throw new DepartmentNotFoundException(String.format("Department %s is not found. Please check your input", departmentName));
        }

        return department
                .map(Department::getLectors)
                .map(lectors -> lectors
                        .stream()
                        .mapToDouble(Lector::getSalary)
                        .average()
                        .orElse(Double.NaN))
                .orElse(Double.NaN);
    }

    @Override
    public Integer countOfEmployeeByDepartment(String departmentName) {

        Optional<Integer> countByName = Optional.ofNullable(departmentRepository.findCountByName(departmentName));
        return countByName.orElse(0);
    }
}
