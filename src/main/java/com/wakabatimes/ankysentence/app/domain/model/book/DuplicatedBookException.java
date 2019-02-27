package com.wakabatimes.ankysentence.app.domain.model.book;

class DuplicatedBookException  extends RuntimeException  {
    DuplicatedBookException(String s) {
        super(String.format("重複しています。 [%s]", s));
    }
}
