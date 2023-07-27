package com.pblgllgs.blog.repository;

import com.pblgllgs.blog.entity.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
}
