package com.example.airline.mapper;

import com.example.airline.dto.PlaneDTO;
import com.example.airline.dto.PlaneModelDTO;
import com.example.airline.model.Plane;
import com.example.airline.model.PlaneModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaneModelMapper {

    public PlaneModelDTO mapToDTO(PlaneModel planeModel) {
        return PlaneModelDTO
                .builder()
                .id(planeModel.getId())
                .manufacturer(planeModel.getManufacturer())
                .model(planeModel.getModel())
                .numberOfSeat(planeModel.getNumberOfSeat())
                .numberOfCrewMembers(planeModel.getNumberOfCrewMembers())
                .build();
    }

    public PlaneModel mapToEntity(PlaneModelDTO planeModelDTO) {
        return PlaneModel
                .builder()
                .id(planeModelDTO.getId())
                .manufacturer(planeModelDTO.getManufacturer())
                .model(planeModelDTO.getModel())
                .numberOfSeat(planeModelDTO.getNumberOfSeat())
                .numberOfCrewMembers(planeModelDTO.getNumberOfCrewMembers())
                .build();
    }

    public List<PlaneModelDTO> mapToListDTO(List<PlaneModel> planeModels) {
        List<PlaneModelDTO> planeModelDTOs = new ArrayList<>();
        for (PlaneModel entity : planeModels
        ) {
            planeModelDTOs.add(mapToDTO(entity));
        }
        return planeModelDTOs;
    }

    public List<PlaneModel> mapToListEntity(List<PlaneModelDTO> planeModelDTOs) {
        List<PlaneModel> planeModels = new ArrayList<>();
        for (PlaneModelDTO dto : planeModelDTOs
        ) {
            planeModels.add(mapToEntity(dto));
        }
        return planeModels;
    }
}
