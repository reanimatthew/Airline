package com.example.airline.api;

import com.example.airline.dto.PlaneDTO;
import com.example.airline.mapper.PlaneMapper;
import com.example.airline.model.Plane;
import com.example.airline.service.PlaneService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/planes")

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaneAPI {
    PlaneService planeService;
    PlaneMapper planeMapper;

    @GetMapping
    List<PlaneDTO> getAll() {
        return planeMapper.mapToListDTO(planeService.getAll());
    }

    @GetMapping("registrationNumber")
    PlaneDTO getByRegistrationNumber(String registrationNumber) {
        return planeMapper.mapToDTO(planeService.getByRegistrationNumber(registrationNumber));
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    PlaneDTO save(@RequestBody PlaneDTO planeDTO) {
        Plane plane = planeMapper.mapToEntity(planeDTO);
        return planeMapper.mapToDTO(planeService.save(plane));
    }
}
