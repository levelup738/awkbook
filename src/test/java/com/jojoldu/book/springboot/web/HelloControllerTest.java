package com.jojoldu.book.springboot.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;

// JUNIT5에서는 @runwith 생략가능
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello_리턴() throws Exception{
        String hello = "hello";
        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(hello));
    }
    @Test
    public void hello_dto_리턴() throws Exception{
        String name = "hello";
        int amount = 1000;
        mvc.perform(MockMvcRequestBuilders.get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount", is(amount)));
    }
}
