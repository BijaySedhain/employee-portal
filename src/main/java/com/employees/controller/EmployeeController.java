package com.employees.controller;

import com.employees.dto.EmployeeDTO;
import com.employees.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @GetMapping
//    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
//        List<EmployeeDTO> employeeList = employeeService.getAllEmployees();
//        return new ResponseEntity<>(employeeList, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return new ResponseEntity<>(employeeService.getEmployees(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@Valid @PathVariable Long id) {
        logger.info("Received request to get employee with id: {}", id);
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        logger.info("Successfully retrieved employee with id: {}", id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        logger.info("Received request to save employee: {}", employeeDTO);
        EmployeeDTO savedEmployee = employeeService.saveEmployee(employeeDTO);
        logger.info("Successfully saved employee with ID: {}", savedEmployee.getId());
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        logger.info("Received request to update employee with id: {}", id);
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        logger.info("Successfully updated employee with id: {}", id);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        logger.info("Received request to delete employee with id: {}", id);
        employeeService.deleteEmployee(id);
        logger.info("Successfully deleted employee with id: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
