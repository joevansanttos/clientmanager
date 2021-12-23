package com.joevan.clientmanager.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PhoneControllerTest {

    @Autowired
    private MockMvc mockMvc; //

    @Test
    public void return400IfNumbersIsEquals() throws Exception {

        URI uri = new URI("/phone/add");

        String json = "{" +
                "\"numbers\":\"9999999999\"," +
                "\"clientId\": 22" +
                "}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

    @Test
    public void return400IfNumbersAreNull() throws Exception {

        URI uri = new URI("/phone/add");

        String json = "{" +
                "\"numbers\":\"\"," +
                "\"clientId\": 22" +
                "}";


        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

    @Test
    public void return200IfNumbersAreValid() throws Exception {

        URI uri = new URI("/phone/add");

        String json = "{" +
                "\"numbers\":\"1198232323\"," +
                "\"clientId\": 22" +
                "}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));
    }

}