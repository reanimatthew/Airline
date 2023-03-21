package com.example.airline.mapper;

import com.example.airline.dto.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageMapper {

    public <T> PageDTO<T> mapToDTO(Page<T> page) {
        return PageDTO
                .<T>builder()
                .content(page.getContent())
                .offset(page.getNumber())
                .limit(page.getSize())
                .totalPages(page.getTotalPages())
                .build();
    }

}
