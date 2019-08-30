package com.daffodil.service;

import com.daffodil.model.entity.EmployeeEntity;
import com.daffodil.exception.EmployeeException;

import com.daffodil.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity addEmployee(EmployeeEntity employeeEntity) throws EmployeeException {
        if (isEmployeeExist(employeeEntity.getEmployeeId())) {
            throw new EmployeeException("Employee already exist with given employeeId: " + employeeEntity.getEmployeeId()
            );
        } else
            return employeeRepository.save(employeeEntity);
    }

    private boolean isEmployeeExist(Integer employeeId) {
        return employeeRepository.existsByEmployeeId(employeeId);
    }

    @Transactional
    public EmployeeEntity updateEmployee(EmployeeEntity employeeEntity) throws EmployeeException {
        if (!isEmployeeExist(employeeEntity.getEmployeeId())) {
            throw new EmployeeException("Employee does not exist with given employeeId: " + employeeEntity.getEmployeeId()
            );
        }
        return employeeRepository
                .save(employeeEntity);
    }

    @Transactional
    public void deleteEmployee(Integer empId) throws EmployeeException {
        if (!isEmployeeExist(empId)) {
            throw new EmployeeException("Employee does not exist with given employeeId: " + empId);
        } else
            employeeRepository.deleteByEmployeeId(empId);
    }

    public EmployeeEntity getEmployee(Integer empId) throws EmployeeException {
        if (isEmployeeExist(empId))
            return employeeRepository.findByEmployeeId(empId).get();
        else
            throw new EmployeeException("Employee does not exist with given employeeId: " + empId);

    }

    public List<EmployeeEntity> listEmployee() {
        return employeeRepository.findAll();
    }

}
