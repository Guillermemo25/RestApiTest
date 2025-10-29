package com.chakray.userapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllUsers() throws Exception {
        // Perform the request
        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON))
                // Check the status code
                .andExpect(status().isOk())
                // Check the content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Check the response body
                .andExpect(jsonPath("$").isNotEmpty())
                // Check the number of users
                .andExpect(jsonPath("$.length()").value(3))
                // Check the user fields
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].email").isNotEmpty())
                .andExpect(jsonPath("$[0].phone").isNotEmpty())
                .andExpect(jsonPath("$[0].password").isNotEmpty())
                .andExpect(jsonPath("$[0].tax_id").isNotEmpty())
                .andExpect(jsonPath("$[0].created_at").isNotEmpty())
                .andExpect(jsonPath("$[0].addresses").isArray())
                .andExpect(jsonPath("$[0].addresses.length()").isNotEmpty());
    }
}
