package com.wakabatimes.ankysentence.app.interfaces.user.dto;

import com.wakabatimes.ankysentence.app.domain.aggregates.user_book.UserBook;
import lombok.Data;

import java.util.List;

@Data
public class UserBookResponseDto {
    private String id;
    private String name;
    private Integer sort;
    private List<UserBookCategoryResponseDto> categories;

    public UserBookResponseDto(UserBook userBook){
        this.id = userBook.getBook().getBookId().getValue();
        this.name=userBook.getBook().getBookName().getValue();
        this.sort=userBook.getBook().getBookSortNumber().getValue();
    }
}
