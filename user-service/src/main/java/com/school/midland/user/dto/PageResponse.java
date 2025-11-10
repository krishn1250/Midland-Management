package com.school.midland.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    // ✅ Convenience constructor for manual pagination
    public PageResponse(List<T> content, long totalElements, int page, int size, int totalPages) {
        this.content = content;
        this.totalElements = totalElements;
        this.pageNumber = page;
        this.pageSize = size;
        this.totalPages = totalPages;
        this.last = page >= totalPages - 1;
    }
}
