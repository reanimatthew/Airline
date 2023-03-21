package com.example.airline.api;

import com.example.airline.dto.GroundServiceDTO;
import com.example.airline.mapper.GroundServiceMapper;
import com.example.airline.model.GroundService;
import com.example.airline.service.GroundServiceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/groundservices")

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GroundServiceAPI {
    GroundServiceService groundServiceService;
    GroundServiceMapper groundServiceMapper;

    @GetMapping
    List<GroundServiceDTO> getAll() {
        return groundServiceMapper.mapToListDTO(groundServiceService.getAll());
    }

    @GetMapping("name")
    GroundServiceDTO getByName(String name) {
        return groundServiceMapper.mapToDTO(groundServiceService.getByName(name));
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    GroundServiceDTO save(GroundServiceDTO groundServiceDTO) {
        GroundService groundService = groundServiceMapper.mapToEntity(groundServiceDTO);
        return groundServiceMapper.mapToDTO(groundServiceService.save(groundService));
    }
}
