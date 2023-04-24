package com.task.springbootuniversity;

import com.task.springbootuniversity.io.entity.Lector;
import com.task.springbootuniversity.io.entity.enums.Degree;
import com.task.springbootuniversity.service.DepartmentService;
import com.task.springbootuniversity.service.LectorService;
import com.task.springbootuniversity.utils.UniversityConsoleServiceUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class UniversityConsoleService implements CommandLineRunner {

    private final DepartmentService departmentService;

    private final LectorService lectorService;

    public UniversityConsoleService(DepartmentService departmentService, LectorService lectorService) {
        this.departmentService = departmentService;
        this.lectorService = lectorService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(UniversityConsoleServiceUtils.GREETINGS);

        boolean exit = false;
        while (!exit) {
            System.out.println(UniversityConsoleServiceUtils.ENTER_A_COMMAND);
            String command = scanner.nextLine();

            if (command.startsWith(UniversityConsoleServiceUtils.WHO_IS_A_HEAD_OF_DEPARTMENT)) {

                String departmentName = command
                        .substring(UniversityConsoleServiceUtils.WHO_IS_A_HEAD_OF_DEPARTMENT.length()).trim();
                Optional<Lector> headOfDepartment = Optional.ofNullable(departmentService.findHeadOfDepartment(departmentName));
                headOfDepartment.ifPresent(lector ->
                        UniversityConsoleServiceUtils.showHeadOfDepartmentName(departmentName, lector.getName()));

            } else if (command.startsWith(UniversityConsoleServiceUtils.SHOW_THE_COUNT_OF_EMPLOYEE_FOR)) {

                String departmentName = command
                        .substring(UniversityConsoleServiceUtils.SHOW_THE_COUNT_OF_EMPLOYEE_FOR.length()).trim();
                Integer countOfEmployeeByDepartment = departmentService.countOfEmployeeByDepartment(departmentName);
                System.out.println(UniversityConsoleServiceUtils
                        .checkCountOfEmployeeByDepartment(countOfEmployeeByDepartment, departmentName));

            } else if (command.startsWith("Show") && command.endsWith("statistics")) {

                String departmentName = command.substring("Show".length(), command.indexOf("statistics")).trim();
                HashMap<Degree, Long> departmentStatistics = departmentService.countDepartmentStatistics(departmentName);
                UniversityConsoleServiceUtils.showDepartmentStatistics(departmentStatistics);

            } else if (command.startsWith(UniversityConsoleServiceUtils.SHOW_THE_AVERAGE_SALARY)) {

                String departmentName = command
                        .substring(UniversityConsoleServiceUtils.SHOW_THE_AVERAGE_SALARY.length()).trim();
                Double averageSalary = departmentService.countAverageSalary(departmentName);
                UniversityConsoleServiceUtils.showAverageSalaryOfDepartment(departmentName, averageSalary);

            } else if (command.startsWith(UniversityConsoleServiceUtils.GLOBAL_SEARCH_BY)) {

                String template = command
                        .substring(UniversityConsoleServiceUtils.GLOBAL_SEARCH_BY.length()).trim();
                List<String> namesByKeyword = lectorService.findLectorsNamesByKeyword(template);
                UniversityConsoleServiceUtils.showByKeyword(namesByKeyword);

            } else if (command.equals("exit")) {
                exit = true;
            } else {
                System.out.println(UniversityConsoleServiceUtils.COMMAND_IS_NOT_RECOGNIZED);
            }
        }

        System.out.println(UniversityConsoleServiceUtils.EXITING_FROM_CONSOLE);
    }
}
