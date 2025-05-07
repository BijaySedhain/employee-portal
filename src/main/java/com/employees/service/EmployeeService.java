package com.employees.service;

import com.employees.dto.EmployeeDTO;
import com.employees.entity.Employee;
import com.employees.exception.EmployeeNotFoundException;
import com.employees.mapper.EmployeeMapper;
import com.employees.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDTO> getAllEmployees() {
        logger.info("Fetching all employees");
        List<Employee> employees = employeeRepository.findAll();
        logger.info("Successfully fetched {} employees", employees.size());
        return employees.stream()
                .map(employeeMapper::toDto).toList();
    }

    public EmployeeDTO getEmployeeById(Long id) {
        logger.info("Fetching employee with id: {}", id);
         Employee employee = employeeRepository.findById(id)
                 .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
         logger.debug("Employee found: {}", employee);
        return employeeMapper.toDto(employee);
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        logger.info("Saving employee: {}", employeeDTO);
        Employee  employee = employeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        logger.debug("Successfully saved employee with ID: {}", savedEmployee.getId());
        return employeeMapper.toDto(savedEmployee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDto) {
        logger.info("Updating employee: {}", employeeDto);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        employeeMapper.updateEmployeeFromDto(employeeDto, employee);
        Employee updatedEmployee = employeeRepository.save(employee);
        logger.debug("Successfully updated employee with ID: {}", updatedEmployee.getId());
        return employeeMapper.toDto(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
