package com.wakabatimes.ankysentence.app.domain.model.category;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Categories {
    private List<Category> categories;

    public Categories () {
        categories = new ArrayList<>();
    }

    public Categories(List<Category> categories) {
        this.categories = new ArrayList<>(categories);
    }

    /**
     * categories add
     * @param category
     * @throws RuntimeException
     */
    public void add(@NonNull Category category) throws RuntimeException {
        if(this.containsName(category)) {
            throw new DuplicatedCategoryException(category.toString());
        }
        this.categories.add(category);
    }

    /**
     * categories count
     * @return Integer
     */
    public Integer size() {
        return this.categories.size();
    }

    /**
     * containName
     * @param compare_item
     * @return Boolean
     */
    public boolean containsName(Category compare_item) {
        for(Category category : this.categories) {
            if (category.getCategoryName().equals(compare_item.getCategoryName()) && !category.getCategoryId().equals(compare_item.getCategoryId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * get list
     *
     * @return List<User>
     */
    public List<Category> list() {
        return Collections.unmodifiableList(this.categories);
    }
}
