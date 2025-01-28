package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Employee {

    private String empId;
    private String name;
    private double salary;

    public Employee(String empId, String name, double salary) {
        this.empId = empId;
        this.name = name;
        this.salary = salary;
    }

    public String getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

class EmployeeRepo {
    public void save(Employee employee) {
        String jdbcUrl = "jdbc:mysql:";
        String dbUser = "your_username";
        String dbPassword = "your_password";

        String insertSQL = "INSERT INTO employees (emp_id, name, salary) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, employee.getEmpId());
            pstmt.setString(2, employee.getName());
            pstmt.setDouble(3, employee.getSalary());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Employee saved successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error saving employee to the database.");
        }
    }
}

class TaxCalculator {
    public double calculateTax(int salary) {
        if (salary <= 50000) {
            return salary * 0.15; // 15% tax for salaries <= 50,000
        } else if (salary <= 100000) {
            return salary * 0.20; // 20% tax for salaries between 50,001 and 100,000
        } else {
            return salary * 0.25; // 25% tax for salaries > 100,000
        }
    }
}