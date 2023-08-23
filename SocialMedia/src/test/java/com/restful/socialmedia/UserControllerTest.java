package com.restful.socialmedia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restful.socialmedia.controller.UserController;
import com.restful.socialmedia.model.RegistrationRequest;
import com.restful.socialmedia.model.User;
import com.restful.socialmedia.repository.UserRepository;
import com.restful.socialmedia.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Test
    public void testRegisterUser() throws Exception {
        RegistrationRequest request = new RegistrationRequest("username", "email@mail.com", "password");
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        Mockito.when(userService.registerUser(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email@mail.com"));
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
