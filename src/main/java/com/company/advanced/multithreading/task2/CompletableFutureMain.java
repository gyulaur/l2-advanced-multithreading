package com.company.advanced.multithreading.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompletableFutureMain {

    private static Logger logger = LoggerFactory.getLogger(CompletableFutureMain.class);

    private static EmployeeService employeeService;

    public static void main(String[] args) {

        employeeService = new EmployeeService();

        CompletableFuture<Void> hiredEmployeesWithSalaryFuture = employeeService.hiredEmployees()
                .thenComposeAsync(CompletableFutureMain::handleSalaries)
                .thenAcceptAsync(employees -> employees.forEach(System.out::println));

        hiredEmployeesWithSalaryFuture.join();
    }

    public static CompletableFuture<List<EmployeeService.Employee>> handleSalaries(List<EmployeeService.Employee> employees) {
        List<CompletableFuture<EmployeeService.Employee>> completableFutures = employees.stream()
                .map(employee -> CompletableFuture.supplyAsync(() -> employee)
                        .thenCombineAsync(employeeService.getSalary(employee.id), (employee1, salary) -> new EmployeeService.Employee(employee.id, salary))
                )
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));

        CompletableFuture<List<EmployeeService.Employee>> allCompletableFuture = allFutures.thenApply(future -> completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList())
        );

        return allCompletableFuture.thenApply(ArrayList::new);
    }
}
