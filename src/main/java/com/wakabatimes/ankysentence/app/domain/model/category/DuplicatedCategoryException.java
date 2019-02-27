package com.wakabatimes.ankysentence.app.domain.model.category;

class DuplicatedCategoryException   extends RuntimeException  {
    DuplicatedCategoryException(String s) {
        super(String.format("重複しています。 [%s]", s));
    }
}