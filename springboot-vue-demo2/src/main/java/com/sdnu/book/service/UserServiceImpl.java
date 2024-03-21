package com.sdnu.book.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.book.common.Result;
import com.sdnu.book.entity.User;
import com.sdnu.book.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Wrapper;

/**
 * 用户服务实现
 *
 * @author Beyong
 * @date 2024/03/20 16:26
 **/
@Service
public class UserServiceImpl implements UserService{
    @Resource
    UserMapper userMapper;

    @Override
    public Result<?> save(User user) {
        if(user.getPassword() == null) {
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }

    @Override
    public Result<?> updateUser(User user) {
        userMapper.updateById(user);
        return Result.success();
    }

    @Override
    public Result<?> deleteUser(Long id) {
        userMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result<?> findPage(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if(StrUtil.isNotBlank(search)) {
            wrapper.like(User::getNickName, search);
        };
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }
}
