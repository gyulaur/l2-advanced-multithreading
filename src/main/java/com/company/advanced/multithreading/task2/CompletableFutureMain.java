package com.company.advanced.multithreading.task2;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompletableFutureMain {

    private static Logger logger = LoggerFactory.getLogger(CompletableFutureMain.class);

    public static void main(String[] args) {

        EmployeeService employeeService = new EmployeeService();
        CompletableFuture<Integer> cf = new CompletableFuture<>();

        CompletableFuture<List<EmployeeService.Employee>> listCompletableFuture = employeeService.hiredEmployees()
                .thenCompose(employees -> employeeService.handleSalaries(employees));

        try {
            List<EmployeeService.Employee> employees1 = listCompletableFuture.get();
            logger.info("Employees: " + employees1);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
