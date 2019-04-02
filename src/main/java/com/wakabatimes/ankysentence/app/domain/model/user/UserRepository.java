package com.wakabatimes.ankysentence.app.domain.model.user;

public interface UserRepository {
    /**
     * getUserByMail
     * @param userMailAddress
     * @return User
     */
    User getUserByMail(String userMailAddress);

    /**
     * save
     * @param user
     */
    void save(User user);

    /**
     * saveHash
     * @param user
     * @param result
     */
    void saveHash(User user, String result);

    /**
     * deleteHash
     * @param user
     */
    void deleteHash(User user);

    /**
     * countUserHashAndUserId
     * @param hash
     * @return
     */
    Long countUserHashAndUserId(UserId userId, UserHash hash);

    /**
     * activate
     * @param userId
     */
    void activate(UserId userId);

    /**
     * getUserIdByHash
     * @param hash
     * @return UserId
     */
    UserId getUserIdByHash(UserHash hash);

    /**
     * getUserByMailAddress
     * @param userMailAddress
     * @return User
     */
    User getUserByMailAddress(UserMailAddress userMailAddress);

    /**
     * update
     * @param user
     */
    void update(User user);

    /**
     * delete
     * @param userId
     */
    void delete(UserId userId);

    /**
     * countUserByIdAndMail
     * @param userId
     * @param userMailAddress
     * @return Long
     */
    Long countUserByIdAndMail(UserId userId, UserMailAddress userMailAddress);

    /**
     * countUserHash
     * @param hash
     * @return Long
     */
    Long countUserHash(UserHash hash);

    /**
     * updatePassword
     * @param userId
     * @param userPassword
     */
    void updatePassword(UserId userId, UserPassword userPassword);

    /**
     * getHashByUserId
     * @param userId
     * @return UserHash
     */
    UserHash getHashByUserId(UserId userId);

    /**
     * getById
     * @param userId
     * @return User
     */
    User getById(UserId userId);

    /**
     * getUserByHash
     * @param userHash
     * @return User
     */
    User getUserByHash(UserHash userHash);
}
