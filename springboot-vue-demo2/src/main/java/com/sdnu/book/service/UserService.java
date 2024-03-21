package com.sdnu.book.service;

import com.sdnu.book.common.Result;
import com.sdnu.book.entity.User;

/**
 * 用户服务接口
 *
 * @author Beyong
 * @date 2024/03/20 16:23
 **/
public interface UserService {
    Result<?> save(User user);
    Result<?> updateUser(User user);
    Result<?> deleteUser(Long id);
    Result<?> findPage(Integer pageNum, Integer pageSize, String search);
}
