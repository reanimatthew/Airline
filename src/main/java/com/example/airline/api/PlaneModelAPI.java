package com.example.airline.api;

import com.example.airline.dto.PlaneModelDTO;
import com.example.airline.mapper.PlaneModelMapper;
import com.example.airline.model.PlaneModel;
import com.example.airline.service.PlaneModelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/planemodels")

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaneModelAPI {
    PlaneModelService planeModelService;
    PlaneModelMapper planeModelMapper;

    @GetMapping
    List<PlaneModelDTO> getAll() {
        return planeModelMapper.mapToListDTO(planeModelService.getAll());
    }

    @GetMapping("model")
    PlaneModelDTO getByModel(String model) {
        return planeModelMapper.mapToDTO(planeModelService.getByModel(model));
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    PlaneModelDTO save(@RequestBody PlaneModelDTO planeModelDTO) {
        PlaneModel planeModel = planeModelMapper.mapToEntity(planeModelDTO);
        return planeModelMapper.mapToDTO(planeModelService.save(planeModel));
    }
}
