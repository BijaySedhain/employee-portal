package com.employees.mapper;


import com.employees.dto.EmployeeDTO;
import com.employees.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = {LocalDateMapper.class})
public interface EmployeeMapper {

    EmployeeDTO toDto(Employee employee);

    Employee toEntity(EmployeeDTO EmployeeDto);

    @Mapping(target = "id", ignore = true)
    void updateEmployeeFromDto(EmployeeDTO dto, @MappingTarget Employee employee);

}
