package com.company.advanced.multithreading.task2;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeService {

    public static final Random RANDOM = new Random();
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    public List<Employee> hiredEmployees() {
        LOGGER.info("Load hired Employees");
        return generateEmployees();
    }

    public int getSalary(int employeeId) {
        LOGGER.info("GetSalary Started, EmployeeId: {}", employeeId);
        try {
            Thread.sleep(1000);
            LOGGER.info("GetSalary Finished, EmployeeId:  {}", employeeId);
        } catch (InterruptedException e) {
            LOGGER.error("GetSalary is failed, EmployeeId:  {}", employeeId);
        }
        return RANDOM.nextInt(100);
    }

    private List<Employee> generateEmployees() {
        return IntStream.range(0, 1_000)
                .mapToObj(index -> new Employee(index, 0))
                .collect(Collectors.toList());
    }

    public static class Employee {
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
