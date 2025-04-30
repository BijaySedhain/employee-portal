package com.employees.mapper;


import com.employees.dto.EmployeeDTO;
import com.employees.entity.Employee;

public class EmployeeMapper {

    /**
     * Converts an Employee entity to an EmployeeDTO.
     *
     * @param employee the Employee entity to convert
     * @return the converted EmployeeDTO
     */
    public EmployeeDTO toDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setJobTitle(employee.getJobTitle());
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setHireDate(employee.getHireDate());
        employeeDTO.setEmploymentType(employee.getEmploymentType());
        employeeDTO.setEmergencyContact(employee.getEmergencyContact());

        return employeeDTO;
    }

    /**
     * Converts an EmployeeDTO to an Employee entity.
     *
     * @param employeeDTO the EmployeeDTO to convert
     * @return the converted Employee entity
     */
    public Employee toEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setJobTitle(employeeDTO.getJobTitle());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setHireDate(employeeDTO.getHireDate());
        employee.setEmploymentType(employeeDTO.getEmploymentType());
        employee.setEmergencyContact(employeeDTO.getEmergencyContact());

        return employee;

}
