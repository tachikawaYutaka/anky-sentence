package com.wakabatimes.ankysentence.app.infrastructure.category.dto;

import com.wakabatimes.ankysentence.app.domain.model.category.Category;
import lombok.Data;

@Data
public class CategoryDto {
    private String id;
    private String name;
    private Integer sortNumber;

    public CategoryDto(){}

    public CategoryDto(Category category) {
        this.id = category.getCategoryId().getValue();
        this.name = category.getCategoryName().getValue();
    }
}
