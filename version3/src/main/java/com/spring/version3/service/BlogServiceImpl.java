package com.spring.version3.service;

import com.spring.version3.common.Blog;

public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).title("My Blog").userId(22).build();
        System.out.println("Client queries id is: "+ id);
        return blog;
    }
}
