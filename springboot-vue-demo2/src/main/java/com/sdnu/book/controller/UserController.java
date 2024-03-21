package com.sdnu.book.controller;

import com.sdnu.book.common.Result;
import com.sdnu.book.entity.User;
import com.sdnu.book.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * User控制器
 *
 * @author Beyong
 * @date 2024/03/20 15:50
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @PostMapping
    public Result<?> save(@RequestBody User user) {
        System.out.println("kkk");
        System.out.println(user);
        return userService.save(user);
    }
    @PutMapping
    public Result<?> updateUser(@RequestBody User user) {
        System.out.println("kkk");
        System.out.println(user);
        return userService.updateUser(user);
    }
    @DeleteMapping("/{id}")
    public Result<?> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        Result result = userService.findPage(pageNum, pageSize, search);
        return result;
    }

}




















