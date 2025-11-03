package com.school.midland.user.mappers;

import com.school.midland.user.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageMapper {

    public <T> PageResponse<T> toPageResponse(Page<T> page) {
        return PageResponse.<T>builder()
                .content(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }
}