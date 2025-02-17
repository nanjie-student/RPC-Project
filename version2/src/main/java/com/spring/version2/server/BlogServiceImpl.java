package com.spring.version2.server;

import com.spring.version2.common.Blog;
import com.spring.version2.service.BlogService;

public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).userId(22).title("my blog").build();
        System.out.println("service queries"+ id +"blog");
        return blog;
    }
}
