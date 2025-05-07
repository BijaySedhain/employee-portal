package com.employees;

import com.employees.dto.EmployeeDTO;
import com.employees.entity.Employee;

import java.time.LocalDate;

public class TestDataUtility {

    public static EmployeeDTO buildTestDtoWithMinimalData() {
        return EmployeeDTO.builder()
                .firstName("Anup")
                .lastName("Sharma")
                .build();
    }

    public static EmployeeDTO buildTestEmployeeDtoWithFullData() {
        return EmployeeDTO.builder()
                .id(1L)
                .firstName("Anup")
                .lastName("Sharma")
                .email("anupsharma@gmail.com")
                .phone("1234567890")
                .jobTitle("Software Engineer")
                .department("IT")
                .hireDate(LocalDate.of(2023, 1, 1))
                .employmentType("Full-Time")
                        .emergencyContact("9876543210")
                .build();
    }

    public static Employee buildTestEmployeeWithFullData() {
        return Employee.builder()
                .id(1L)
                .firstName("Anup")
                .lastName("Sharma")
                .email("anupsharma@gmail.com")
                .phone("1234567890")
                .jobTitle("Software Engineer")
                .department("IT")
                .hireDate(LocalDate.of(2023, 1, 1))
                .employmentType("Contractor")
                .emergencyContact("9876543210")
                .build();
    }
}
