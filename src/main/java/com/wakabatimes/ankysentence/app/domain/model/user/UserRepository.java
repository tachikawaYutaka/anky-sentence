package com.wakabatimes.ankysentence.app.domain.model.user;

public interface UserRepository {
    /**
     *
     * @param userMailAddress
     * @return User
     */
    User getUserByMail(String userMailAddress);

    /**
     *
     * @param user
     */
    void save(User user);

    /**
     *
     * @param user
     * @param result
     */
    void saveHash(User user, String result);

    /**
     *
     * @param user
     */
    void deleteHash(User user);

    /**
     *
     * @param hash
     * @return
     */
    Long countUserHashAndUserId(UserId userId, String hash);

    /**
     *
     * @param userId
     */
    void activate(UserId userId);

    /**
     *
     * @param hash
     * @return
     */
    UserId getUserIdByHash(String hash);

    /**
     *
     * @param userMailAddress
     * @return
     */
    User getUserByMailAddress(UserMailAddress userMailAddress);

    /**
     *
     * @param user
     */
    void update(User user);

    /**
     *
     * @param userId
     */
    void delete(UserId userId);

    /**
     *
     * @param userId
     * @param userMailAddress
     */
    Long countUserByIdAndMail(UserId userId, UserMailAddress userMailAddress);

    /**
     *
     * @param hash
     * @return
     */
    Long countUserHash(String hash);

    /**
     *
     * @param userId
     * @param userPassword
     */
    void updatePassword(UserId userId, UserPassword userPassword);
}
