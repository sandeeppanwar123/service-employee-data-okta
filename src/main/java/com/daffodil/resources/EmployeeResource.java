package com.daffodil.resources;

import com.daffodil.model.api.EmployeeDTO;
import com.daffodil.exception.EmployeeException;
import com.daffodil.mapper.EmployeeMapper;
import com.daffodil.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.daffodil.resources.UrlConstants.*;


@RestController
@RequestMapping(VERSIONED_API)
public class EmployeeResource {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeResource.class);
    private EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO employeeDTO) throws EmployeeException {
        logger.info("creating employee with employeeId :" + employeeDTO.getEmployeeId());
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
                .body(EmployeeMapper.map(employeeService.addEmployee(EmployeeMapper.map(employeeDTO))));
    }

    @PutMapping()
    public EmployeeDTO update(@RequestBody EmployeeDTO employeeDTO) throws EmployeeException {
        logger.info("updating employee with employeeId :" + employeeDTO.getEmployeeId());
        return EmployeeMapper.map(employeeService.updateEmployee(EmployeeMapper.map(employeeDTO)));
    }

    @CrossOrigin
    @GetMapping(value = EMP_ID)
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Integer empId) throws EmployeeException {
        logger.info("getting employee details for employeeId :" + empId);

        return ResponseEntity.ok()
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT,OPTIONS")
                .body(EmployeeMapper.map(employeeService.getEmployee(empId)));

    }
    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getEmployee() {
        logger.info("Loading all avaialble employee...");

        return ResponseEntity.ok()
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT,OPTIONS")
                .body(EmployeeMapper.map(employeeService.listEmployee()));

    }

    @DeleteMapping(value = EMP_ID)
    public String deleteEmployee(@PathVariable Integer empId) throws EmployeeException {
        logger.info("deleting employee details for employeeId :" + empId);
        employeeService.deleteEmployee(empId);
        return "{Employee deleted for employeeId: " + empId + "}";
    }
}




