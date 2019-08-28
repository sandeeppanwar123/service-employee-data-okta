package com.daffodil.mapper;

import com.daffodil.dto.EmployeeDTO;
import com.daffodil.entity.EmployeeEntity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {

    public static EmployeeEntity map(EmployeeDTO employeeDTO) {
        return new EmployeeEntity()
                .setAddresses(employeeDTO.getAddress())
                .setDepartment(employeeDTO.getDepartment())
                .setEmployeeId(employeeDTO.getEmployeeId())
                .setEmployeeName(employeeDTO.getEmployeeName())
                .setEmployementType(employeeDTO.getEmployementType());

    }

    public static EmployeeDTO map(EmployeeEntity employeeEntity) {
        return new EmployeeDTO()
                .setDepartment(employeeEntity.getDepartment())
                .setEmployeeId(employeeEntity.getEmployeeId())
                .setEmployeeName(employeeEntity.getEmployeeName())
                .setAddress(employeeEntity.getAddresses())
                .setEmployementType(employeeEntity.getEmployementType())
                ;


    }

    public static List<EmployeeDTO> map(List<EmployeeEntity> employeeList) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<EmployeeDTO>();

        employeeList.stream().forEach(employeeEntity -> employeeDTOS.add(map(employeeEntity)));

        return employeeDTOS;

    }


}
