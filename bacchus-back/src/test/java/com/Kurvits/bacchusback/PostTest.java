package com.Kurvits.bacchusback;

import com.Kurvits.bacchusback.User.User;
import com.Kurvits.bacchusback.User.UserController;
import com.Kurvits.bacchusback.User.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class PostTest{

    @Autowired
    MockMvc mvc;

    @Autowired
    private UserController userController;

    @Test
    public void addUserTest() throws Exception {
        UUID uuid = UUID.randomUUID();
        User user = new User(uuid, "Test User", "Ball", 10);

        ObjectMapper objectMapper = new ObjectMapper();


        RequestBuilder request = MockMvcRequestBuilders
                .post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

    }
}
