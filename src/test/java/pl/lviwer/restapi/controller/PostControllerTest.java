package pl.lviwer.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.pro.packaged.gi;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.lviwer.restapi.model.Post;
import pl.lviwer.restapi.repository.PostRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional //don't save new files to the database during test // here or under classname
    void shouldGetSinglePost() throws Exception {
        //given
        Post newPost = new Post();
        newPost.setTitle("Test");
        newPost.setContent("Test content");
        newPost.setCreated(LocalDateTime.now());
        postRepository.save(newPost);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/posts/" + newPost.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                //  .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))   //only in simple cases use jsonPath
                .andReturn();
        //then
        Post post = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post.class);
        assertThat(post).isNotNull();
        assertThat(post.getId()).isEqualTo(newPost.getId());
        assertThat(post.getTitle()).isEqualTo("Test");
        assertThat(post.getContent()).isEqualTo("Test content");

    }

}