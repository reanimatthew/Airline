package com.example.airline.service;

import com.example.airline.model.Employee;
import com.example.airline.repositories.EmployeeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getByPassport(String passport) {
        return employeeRepository.findEmployeeByPassport(passport).orElseThrow();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
}
