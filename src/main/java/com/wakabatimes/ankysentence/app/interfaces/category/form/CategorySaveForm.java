package com.wakabatimes.ankysentence.app.interfaces.category.form;

import lombok.Data;

@Data
public class CategorySaveForm {
    private String userId;
    private String bookId;
    private String name;
}
