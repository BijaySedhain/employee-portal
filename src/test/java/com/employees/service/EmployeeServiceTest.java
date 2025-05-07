package com.employees.service;

import com.employees.TestDataUtility;
import com.employees.dto.EmployeeDTO;
import com.employees.entity.Employee;
import com.employees.mapper.EmployeeMapper;
import com.employees.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        employee = TestDataUtility.buildTestEmployeeWithFullData();
        employeeDTO = TestDataUtility.buildTestEmployeeDtoWithFullData();
    }

    @Test
    void getAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee));
        List<EmployeeDTO> result = employeeService.getAllEmployees();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getEmployeeById() {
            when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
            when(employeeMapper.toDto(employee)).thenReturn(employeeDTO);

            EmployeeDTO result = employeeService.getEmployeeById(1L);
            assertEquals(employeeDTO, result);
    }

    @Test
    void saveEmployee() {
        when(employeeMapper.toEntity(employeeDTO)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.toDto(employee)).thenReturn(employeeDTO);

        EmployeeDTO result = employeeService.saveEmployee(employeeDTO);
        assertNotNull(result);
        assertEquals(employeeDTO, result);
    }

    @Test
    void updateEmployee() {
        Long employeeId = 1L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.toDto(employee)).thenReturn(employeeDTO);

        EmployeeDTO result = employeeService.updateEmployee(employeeId, employeeDTO);

        assertNotNull(result);
        assertEquals(employeeDTO, result);
        verify(employeeRepository).findById(employeeId);
        verify(employeeMapper).updateEmployeeFromDto(employeeDTO, employee); // Verify invocation
        verify(employeeRepository).save(employee);
        verify(employeeMapper).toDto(employee);
    }

    @Test
    void deleteEmployee() {
        Long employeeId = 1L;
        when(employeeRepository.existsById(employeeId)).thenReturn(true);
        employeeService.deleteEmployee(employeeId);
        verify(employeeRepository).deleteById(employeeId);
    }
}