package com.restful.socialmedia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restful.socialmedia.controller.PostController;
import com.restful.socialmedia.model.Post;
import com.restful.socialmedia.model.PostRequest;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.service.PostService;
import com.restful.socialmedia.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Collections;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private UserService userService;

    @Test
    public void testCreatePost() throws Exception {
        User author = new User();
        author.setUsername("username");
        author.setEmail("email@example.com");
        author.setPassword("password");

        Post post = new Post();
        post.setAuthor(author);

        Mockito.when(userService.getUserByName("username")).thenReturn(author);
        Mockito.when(postService.createPost(author, "Title", "Text", Collections.singletonList("ImagePath"))).thenReturn(post);

        PostRequest request = new PostRequest("Title", "Text", Collections.singletonList("ImagePath"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

