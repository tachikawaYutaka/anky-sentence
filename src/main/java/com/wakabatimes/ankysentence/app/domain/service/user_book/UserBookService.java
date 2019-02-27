package com.wakabatimes.ankysentence.app.domain.service.user_book;

import com.wakabatimes.ankysentence.app.domain.aggregates.user_book.UserBooks;
import com.wakabatimes.ankysentence.app.domain.model.user.UserId;

public interface UserBookService {
    /**
     * ユーザーに紐づくブック、カテゴリ、問題全取得
     * @param userId
     * @return
     */
    UserBooks getByUserId(UserId userId);
}
