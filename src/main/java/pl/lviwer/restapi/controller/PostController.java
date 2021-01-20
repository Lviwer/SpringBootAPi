package pl.lviwer.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.lviwer.restapi.controller.dto.PostDto;
import pl.lviwer.restapi.model.Post;
import pl.lviwer.restapi.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page > 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return PostDtoMapper.mapToPostDtos(postService.getPosts(pageNumber, sortDirection));
    }


    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        int pageNumber = page != null && page > 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return postService.getPostsWithComments(pageNumber, sortDirection);
    }


    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable long id) {
        return postService.getSinglePost(id);
    }


    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);

    }








}
