package com.employees.controller;

import com.employees.TestDataUtility;
import com.employees.dto.EmployeeDTO;
import com.employees.entity.Employee;
import com.employees.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void  setUp(){
        employeeDTO = TestDataUtility.buildTestEmployeeDtoWithFullData();
        employee = TestDataUtility.buildTestEmployeeWithFullData();
    }

    @Test
    void getEmployees() {
    }

    @Test
    void getEmployeeById() {
    }

    @Test
    void saveEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}