package com.sdnu.book;

import com.sdnu.book.entity.User;
import com.sdnu.book.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookApplicationTests {
    @Resource
    UserMapper userMapper;
	@Test
	void contextLoads() {
        User user = new User();
        user.setId(1);
        user.setUsername("小李");
        userMapper.insert(user);
	}

}
