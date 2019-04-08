package com.wakabatimes.ankysentence.app.interfaces.category;

import com.wakabatimes.ankysentence.app.domain.model.book.BookId;
import com.wakabatimes.ankysentence.app.domain.model.category.Category;
import com.wakabatimes.ankysentence.app.domain.model.category.CategoryFactory;
import com.wakabatimes.ankysentence.app.domain.model.category.CategoryId;
import com.wakabatimes.ankysentence.app.domain.model.category.CategoryName;
import com.wakabatimes.ankysentence.app.domain.model.user.User;
import com.wakabatimes.ankysentence.app.domain.service.category.CategoryService;
import com.wakabatimes.ankysentence.app.interfaces.category.dto.CategoryResponseDto;
import com.wakabatimes.ankysentence.app.interfaces.category.form.CategoryDeleteForm;
import com.wakabatimes.ankysentence.app.interfaces.category.form.CategorySaveForm;
import com.wakabatimes.ankysentence.app.interfaces.category.form.CategoryUpdateForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

import static com.wakabatimes.ankysentence.config.ApiUrlConstants.API_URL;

@Slf4j
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    private HttpStatus status = HttpStatus.OK;

    @PostMapping(value = API_URL + "/categories", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CategoryResponseDto save(Principal principal, @Valid @RequestBody CategorySaveForm form){
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();

        if(!user.getUserId().getValue().equals(form.getUserId())){
            log.error("不正なアクセスです。");
            status = HttpStatus.BAD_REQUEST;
            categoryResponseDto.setStatus(status.getReasonPhrase());
            categoryResponseDto.setMessage("不正なアクセスです。");
            return categoryResponseDto;
        }

        try{
            BookId bookId = new BookId(form.getBookId());
            CategoryName name = new CategoryName(form.getName());
            Category category = CategoryFactory.create(name);
            categoryService.save(category,bookId);
            categoryResponseDto.setId(category.getCategoryId().getValue());
            categoryResponseDto.setName(category.getCategoryName().getValue());
            categoryResponseDto.setStatus(status.getReasonPhrase());
            categoryResponseDto.setMessage("カテゴリを作成しました。");
        }catch (RuntimeException e){
            log.error(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            categoryResponseDto.setStatus(status.getReasonPhrase());
            categoryResponseDto.setMessage(e.getMessage());
        }
        return categoryResponseDto;
    }

    @PostMapping(value = API_URL + "/categories/{categoryId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CategoryResponseDto update(@PathVariable String categoryId,  Principal principal, @Valid @RequestBody CategoryUpdateForm form){
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();

        if(!user.getUserId().getValue().equals(form.getUserId())){
            log.error("不正なアクセスです。");
            status = HttpStatus.BAD_REQUEST;
            categoryResponseDto.setStatus(status.getReasonPhrase());
            categoryResponseDto.setMessage("不正なアクセスです。");
            return categoryResponseDto;
        }

        try{
            CategoryId categoryId1 = new CategoryId(categoryId);
            Category getCategory = categoryService.get(categoryId1);

            CategoryName name = new CategoryName(form.getName());
            Category category = new Category(categoryId1,name,getCategory.getCategorySortNumber());
            categoryService.update(category);
            categoryResponseDto.setId(category.getCategoryId().getValue());
            categoryResponseDto.setName(category.getCategoryName().getValue());
            categoryResponseDto.setStatus(status.getReasonPhrase());
            categoryResponseDto.setMessage("カテゴリを更新しました。");
        }catch (RuntimeException e){
            log.error(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            categoryResponseDto.setStatus(status.getReasonPhrase());
            categoryResponseDto.setMessage(e.getMessage());
        }
        return categoryResponseDto;
    }

    @PostMapping(value = API_URL + "/categories/{categoryId}/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CategoryResponseDto delete(@PathVariable String categoryId,  Principal principal, @Valid @RequestBody CategoryDeleteForm form){
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();

        if(!user.getUserId().getValue().equals(form.getUserId())){
            log.error("不正なアクセスです。");
            status = HttpStatus.BAD_REQUEST;
            categoryResponseDto.setStatus(status.getReasonPhrase());
            categoryResponseDto.setMessage("不正なアクセスです。");
            return categoryResponseDto;
        }

        try{
            CategoryId categoryId1 = new CategoryId(categoryId);
            categoryService.delete(categoryId1);
            categoryResponseDto.setStatus(status.getReasonPhrase());
            categoryResponseDto.setMessage("カテゴリを削除しました。");
        }catch (RuntimeException e){
            log.error(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            categoryResponseDto.setStatus(status.getReasonPhrase());
            categoryResponseDto.setMessage(e.getMessage());
        }
        return categoryResponseDto;
    }
}
