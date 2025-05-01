package com.employees.service;

import com.employees.dto.EmployeeDTO;
import com.employees.entity.Employee;
import com.employees.exception.EmployeeException;
import com.employees.mapper.EmployeeMapper;
import com.employees.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeDTO getEmployeeById(Long id) {

         Employee employee = employeeRepository.findById(id)
                 .orElseThrow(() -> new EmployeeException("Employee not found with id: " + id));

        return employeeMapper.toDto(employee);
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee  employee = employeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(savedEmployee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeException("Employee not found with id: " + id));

        employeeMapper.updateEmployeeFromDto(employeeDto, employee);
        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
