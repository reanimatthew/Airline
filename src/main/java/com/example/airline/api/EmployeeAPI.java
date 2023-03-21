package com.example.airline.api;

import com.example.airline.dto.EmployeeDTO;
import com.example.airline.mapper.EmployeeMapper;
import com.example.airline.model.Employee;
import com.example.airline.service.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)


//TODO Постман выбрасывает ошибку! Непонятно, откуда она берется
public class EmployeeAPI {
    EmployeeService employeeService;
    EmployeeMapper employeeMapper;

    @GetMapping
    public List<EmployeeDTO> getAll() {
        return employeeMapper.mapToListDTO(employeeService.getAll());
    }

    @GetMapping("passport")
    public EmployeeDTO getByPassport(String passport) {
        return employeeMapper.mapToDTO(employeeService.getByPassport(passport));
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    EmployeeDTO save(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.mapToEntity(employeeDTO);
        return employeeMapper.mapToDTO(employeeService.save(employee));
    }
}
