package pl.lviwer.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lviwer.restapi.controller.dto.PostDto;
import pl.lviwer.restapi.model.Post;
import pl.lviwer.restapi.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam (required = false) int page) {
        int pageNumber = page > 0 ? page : 0;
        return PostDtoMapper.mapToPostDtos(postService.getPosts(page));
    }


    @GetMapping("/post/{id}")
    public Post getPost(@PathVariable long id) {
        return postService.getSinglePost(id);
    }


}
