package com.company.advanced.multithreading.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeService {

    public static final Random RANDOM = new Random();
    private static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public CompletableFuture<List<Employee>> hiredEmployees() {
        return CompletableFuture.supplyAsync(() -> getEmployees());
    }

    private List<Employee> getEmployees() {
        return List.of(
                new Employee(1, 1),
                new Employee(2, 2),
                new Employee(3, 3),
                new Employee(4, 4),
                new Employee(5, 5),
                new Employee(6, 6),
                new Employee(7, 7),
                new Employee(8, 8),
                new Employee(9, 9),
                new Employee(10, 10)
        );
    }

    public CompletableFuture<List<Employee>> handleSalaries(List<Employee> employees) {
        List<CompletableFuture<Employee>> completableFutures = employees.stream()
                .map(employee -> CompletableFuture.supplyAsync(() -> {
                            logger.info("Employee: " + employee.id);
                            CompletableFuture<Integer> salary = getSalary(employee.getId());
                            employee.setSalary(salary.join());
                            return employee;
                        })
                )
                .collect(Collectors.toList());
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));
        CompletableFuture<List<Employee>> allCompletableFuture = allFutures.thenApply(future -> completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList())
        );

        return allCompletableFuture.thenApply(ArrayList::new);
    }

    public CompletableFuture<Integer> getSalary(int employeeId) {
        return CompletableFuture.supplyAsync(() -> {
            logger.info("GetSalary Started, EmployeeId: {}", employeeId);
            try {
                Thread.sleep(1000);
                logger.info("GetSalary Finished, EmployeeId:  {}", employeeId);
            } catch (InterruptedException e) {
            }
            return RANDOM.nextInt(1000);
        });
    }

    public class Employee {
        int id;
        int salary;

        public Employee(int id, int salary) {
            this.id = id;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public Employee setId(int id) {
            this.id = id;
            return this;
        }

        public int getSalary() {
            return salary;
        }

        public Employee setSalary(int salary) {
            this.salary = salary;
            return this;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", salary=" + salary +
                    '}';
        }
    }
}
