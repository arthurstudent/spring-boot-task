package com.task.springbootuniversity.utils;

import com.task.springbootuniversity.io.entity.enums.Degree;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;

@UtilityClass
public class UniversityConsoleServiceUtils {

    public static final String GREETINGS = "Welcome to the University Console Service!";

    public static final String WHO_IS_A_HEAD_OF_DEPARTMENT = "Who is a head of department";

    public static final String SHOW_THE_AVERAGE_SALARY = "Show the average salary for the department";

    public static final String SHOW_THE_COUNT_OF_EMPLOYEE_FOR = "Show count of employee for";

    public static final String COMMAND_IS_NOT_RECOGNIZED = "Command is not recognized";

    public static final String ENTER_A_COMMAND = "Enter a command: ";

    public static final String EXITING_FROM_CONSOLE = "Exiting University Console Service..";

    public static final String GLOBAL_SEARCH_BY = "Global search by";

    public static void showHeadOfDepartmentName(String departmentName, String headOfDepartmentName) {
        System.out.printf("Head of %s department is %s%n", departmentName, headOfDepartmentName);
    }

    public static void showAverageSalaryOfDepartment(String departmentName, Double averageSalary) {
        System.out.printf("The average salary of %s is %s%n", departmentName, averageSalary);
    }

    public static void showDepartmentStatistics(HashMap<Degree, Long> departmentStatistics) {
        departmentStatistics.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    public static void showByKeyword(List<String> names) {
        if (names == null) System.out.println("Lectors cant be find");
        assert names != null;
        names.forEach(System.out::println);
    }

    public static String checkCountOfEmployeeByDepartment(int count, String departmentName) {
        return count == 0 ? String.format("Department %s is empty or doesn't exist", departmentName)
                : String.valueOf(count);
    }

    public static void showMainMenu() {
        System.out.println("\nYou can type such commands");
        System.out.println("-------------------------");
        System.out.println("Who is a head of department {department_name}");
        System.out.println("Show {department_name} statistics");
        System.out.println("Show the average salary for the department {department_name}");
        System.out.println("Show count of employee for {department_name}");
        System.out.println("Global search by {template}");
        System.out.println("Exit");
        System.out.println("-------------------------\n");
    }
}
