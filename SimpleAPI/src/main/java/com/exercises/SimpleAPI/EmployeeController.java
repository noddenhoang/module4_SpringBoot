package com.exercises.SimpleAPI;

import com.exercises.SimpleAPI.dto.ApiResponse;
import com.exercises.SimpleAPI.exception.ApiException;
import com.exercises.SimpleAPI.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        employees.add(new Employee(1, "Nguyen Van A", "12/12/2003", Employee.Gender.MALE, 200000.0));
        employees.add(new Employee(2, "Nguyen Thi B", "12/12/2004", Employee.Gender.FEMALE, 300000.0));
        employees.add(new Employee(3, "Nguyen BD", "12/12/2005", Employee.Gender.OTHER, 400000.0));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployee(@PathVariable("id") int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return ResponseEntity.ok(ApiResponse.<Employee>builder().data(employee).build());
            }
        }
        throw new ApiException(ErrorCode.EMPLOYEE_NOT_EXIST);
    }


    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employee.setId(employees.size() + 1);
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable int id, @RequestBody Employee employee) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .map(e -> {
                    e.setName(employee.getName());
                    e.setDob(employee.getDob());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    return ResponseEntity.ok(e);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .map(e -> {
                    employees.remove(e);
                    return ResponseEntity.ok(e);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
