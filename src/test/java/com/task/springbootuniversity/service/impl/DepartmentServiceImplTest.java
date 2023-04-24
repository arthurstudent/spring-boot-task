package com.task.springbootuniversity.service.impl;

import com.task.springbootuniversity.exceptions.DepartmentNotFoundException;
import com.task.springbootuniversity.io.entity.Department;
import com.task.springbootuniversity.io.entity.Lector;
import com.task.springbootuniversity.io.entity.enums.Degree;
import com.task.springbootuniversity.io.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Lector lector;

    private Department department;

    static String departmentName = "Applied Mathematics";

    @BeforeEach
    void setUp() {
        lector = new Lector();
        lector.setDegree(Degree.ASSOCIATE_PROFESSOR);
        lector.setName("Ivan Petrov");
        lector.setSalary(5000);

        department = new Department();
        department.setName(departmentName);
        department.setHeadOfDepartment(lector);

        setLectors();
    }

    @Test
    void findHeadOfDepartment() {
        when(departmentRepository.findByName(anyString())).thenReturn(department);
        Lector headOfDepartment = departmentService.findHeadOfDepartment(departmentName);

        assertNotNull(headOfDepartment);
        assertEquals(headOfDepartment.getName(), lector.getName());
    }

    @Test
    final void findHeadOfDepartment_departmentIsNotFound() {
        when(departmentRepository.findByName(anyString())).thenReturn(null);

        DepartmentNotFoundException departmentNotFoundException =
                assertThrows(DepartmentNotFoundException.class, () -> departmentService.findHeadOfDepartment(departmentName));
        assertEquals("Department Applied Mathematics is not found. Please check your input", departmentNotFoundException.getMessage());
    }

    @Test
    void countDepartmentStatistics() {

        when(departmentRepository.findByName(anyString())).thenReturn(department);
        Map<Degree, Long> countDepartmentStatistics = departmentService.countDepartmentStatistics(departmentName);

        assertEquals(3, countDepartmentStatistics.size());
        assertEquals(countDepartmentStatistics.get(Degree.ASSISTANT), 1);
        assertEquals(countDepartmentStatistics.get(Degree.ASSOCIATE_PROFESSOR), 1);
        assertEquals(countDepartmentStatistics.get(Degree.PROFESSOR), 1);
    }

    @Test
    void countDepartmentStatistics_departmentIsNotFound() {
        when(departmentRepository.findByName(anyString())).thenReturn(null);

        assertThrows(DepartmentNotFoundException.class, () -> departmentService.findHeadOfDepartment(departmentName));
    }

    @Test
    void countAverageSalary() {
        when(departmentRepository.findByName(anyString())).thenReturn(department);
        Double countAverageSalary = departmentService.countAverageSalary(departmentName);

        assertNotNull(countAverageSalary);
        assertEquals(5000,countAverageSalary);
    }

    @Test
    void countAverageSalary_departmentIsNotFound() {
        when(departmentRepository.findByName(anyString())).thenReturn(null);

        assertThrows(DepartmentNotFoundException.class, () -> departmentService.findHeadOfDepartment(departmentName));
    }

    @Test
    void countOfEmployeeByDepartment() {
        when(departmentRepository.findCountByName(anyString())).thenReturn(null);
        Integer appliedMathematics = departmentService.countOfEmployeeByDepartment(departmentName);
        assertEquals(0, appliedMathematics);
    }

    private Set<Lector> getMockLectors(){
        Lector assistant = new Lector();
        assistant.setSalary(4000);
        assistant.setDegree(Degree.ASSISTANT);
        assistant.setName("Dmytro Ivanov");

        Lector associateProfessor = new Lector();
        associateProfessor.setSalary(5000);
        associateProfessor.setDegree(Degree.ASSOCIATE_PROFESSOR);
        associateProfessor.setName("Igor Vankov");

        Lector professor = new Lector();
        professor.setSalary(6000);
        professor.setDegree(Degree.PROFESSOR);
        professor.setName("Alex Petrenko");

        return Set.of(assistant, associateProfessor, professor);
    }

    private void setLectors(){
        Set<Lector> mockLectors = getMockLectors();
        mockLectors.forEach(lector -> department.addLector(lector));
    }
}