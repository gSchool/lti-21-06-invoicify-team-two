package com.example.invoicify;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.invoicify.models.User;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//
//import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest
public class UserControllerTest {

    User user = new User();

    @Autowired
    MockMvc mvc;

    @Test
    public void apiEndpoint() throws Exception {
        this.mvc.perform(get("/api/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Invoicify Users"));
    }

//    As a user want to login,
//    given I have a valid ID and password
//
//    POST http://localhost:8080/api/session

    @Test
    public void getUserLoginSession() throws Exception {
        ObjectMapper userMap = new ObjectMapper();
        user.setId(1L);
        user.setUsername("bob");
        user.setPassword("password");

        String userObject = userMap.writeValueAsString(user);
        System.out.println(userObject);
        RequestBuilder request = post("/api/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userObject);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", equalTo("bob" )))
                .andExpect(jsonPath("$.password", equalTo("password" )));
    }
}
