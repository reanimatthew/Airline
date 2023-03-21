package com.example.airline.mapper;

import com.example.airline.dto.AirportDTO;
import com.example.airline.dto.EmployeeDTO;
import com.example.airline.dto.FlightDTO;
import com.example.airline.model.Airport;
import com.example.airline.model.Employee;
import com.example.airline.model.Flight;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeMapper {
    FlightMapper flightMapper;

    public EmployeeDTO mapToDTO(Employee employee) {
        FlightDTO flightDTO = flightMapper.mapToDTO(employee.getFlight());
        return EmployeeDTO
                .builder()
                .id(employee.getId())
                .passport(employee.getPassport())
                .surname(employee.getSurname())
                .name(employee.getName())
                .patronymic(employee.getPatronymic())
                .age(employee.getAge())
                .positionType(employee.getPositionType())
                .salary(employee.getSalary())
                .experience(employee.getExperience())
                .flightDTO(flightDTO)
                .build();
    }

    public Employee mapToEntity(EmployeeDTO employeeDTO) {
        Flight flight = flightMapper.mapToEntity(employeeDTO.getFlightDTO());
        return Employee
                .builder()
                .id(employeeDTO.getId())
                .passport(employeeDTO.getPassport())
                .surname(employeeDTO.getSurname())
                .name(employeeDTO.getName())
                .patronymic(employeeDTO.getPatronymic())
                .age(employeeDTO.getAge())
                .positionType(employeeDTO.getPositionType())
                .salary(employeeDTO.getSalary())
                .experience(employeeDTO.getExperience())
                .flight(flight)
                .build();
    }

    public List<EmployeeDTO> mapToListDTO(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee entity : employees
        ) {
            employeeDTOs.add(mapToDTO(entity));
        }
        return employeeDTOs;
    }

    public List<Employee> mapToListEntity(List<EmployeeDTO> employeeDTOs) {
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDTO dto : employeeDTOs
        ) {
            employees.add(mapToEntity(dto));
        }
        return employees;
    }
}

