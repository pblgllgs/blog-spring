package com.pblgllgs.blog.controller;

import com.pblgllgs.blog.payload.PostDto;
import com.pblgllgs.blog.payload.PostResponse;
import com.pblgllgs.blog.service.impl.CommentService;
import com.pblgllgs.blog.service.impl.PostService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.pblgllgs.blog.utils.AppConstants.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final ModelMapper modelMapper;

    private final CommentService commentService;

    public PostController(PostService postService, ModelMapper modelMapper, CommentService commentService) {
        this.postService = postService;
        this.modelMapper = modelMapper;
        this.commentService = commentService;
    }

    @ApiOperation(value = "Create Post REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/v1")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Posts REST API")
    @GetMapping("/v1")
    public ResponseEntity<PostResponse> findAll(
            @RequestParam(value = "pageNumber", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return new ResponseEntity<>(postService.findAll(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Post By Id REST API")
    @GetMapping("/v1/{id}")
    public ResponseEntity<PostDto> findByIdV1(@PathVariable("id") long id) {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

//    @GetMapping("/v2/{id}")
//    public ResponseEntity<PostDtoV2> findByIdV2(@PathVariable("id") long id) {
//        PostDto postDto = postService.findById(id);
//        PostDtoV2 postDtoV2 = new PostDtoV2();
//        postDtoV2.setId(postDto.getId());
//        postDtoV2.setTitle(postDto.getTitle());
//        postDtoV2.setDescription(postDto.getDescription());
//        postDtoV2.setContent(postDto.getContent());
//        Set<CommentDto> commentDtoSet = new HashSet<>(commentService.getCommentsByPostId(id));
//        postDtoV2.setComments(commentDtoSet);
//        List<String> tags = List.of("Java", "AWS", "SpringBoot");
//        postDtoV2.setTags(tags);
//        return new ResponseEntity<>(postDtoV2, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/{id}", params = "version=3")
//    public ResponseEntity<PostDtoV2> findByIdV3(@PathVariable("id") long id) {
//        PostDto postDto = postService.findById(id);
//        PostDtoV2 postDtoV2 = modelMapper.map(postDto, PostDtoV2.class);
//        postDtoV2.setTags(List.of("Java", "AWS", "SpringBoot"));
//        return new ResponseEntity<>(postDtoV2, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/{id}", headers = "X-API-VERSION=4")
//    public ResponseEntity<PostDtoV2> findByIdV4(@PathVariable("id") long id) {
//        PostDto postDto = postService.findById(id);
//        PostDtoV2 postDtoV2 = modelMapper.map(postDto, PostDtoV2.class);
//        postDtoV2.setTags(List.of("Java", "AWS", "SpringBoot"));
//        return new ResponseEntity<>(postDtoV2, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/{id}", produces = "application/vnd.java.v5+json")
//    public ResponseEntity<PostDtoV2> findByIdV5(@PathVariable("id") long id) {
//        PostDto postDto = postService.findById(id);
//        PostDtoV2 postDtoV2 = modelMapper.map(postDto, PostDtoV2.class);
//        postDtoV2.setTags(List.of("Java", "AWS", "SpringBoot"));
//        return new ResponseEntity<>(postDtoV2, HttpStatus.OK);
//    }

    @ApiOperation(value = "Update Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/v1/{id}")
    public ResponseEntity<PostDto> updateById(@Valid @RequestBody PostDto postDto, @PathVariable("id") long id) {
        return new ResponseEntity<>(postService.update(postDto, id), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        postService.deleteById(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }
}
