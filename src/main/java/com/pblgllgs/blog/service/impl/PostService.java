package com.pblgllgs.blog.service.impl;

import com.pblgllgs.blog.payload.PostDto;
import com.pblgllgs.blog.payload.PostResponse;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse findAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    PostDto findById(long id);

    PostDto update(PostDto postDto, long id);

    void deleteById(long id);
}
