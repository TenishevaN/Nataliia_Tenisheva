package com.epam.spring.homework.mvc.repairAgency.controller;

import com.epam.spring.homework.mvc.repairAgency.dto.ReviewDto;
import com.epam.spring.homework.mvc.repairAgency.service.ReviewService;
import com.epam.spring.homework.mvc.repairAgency.test.config.TestWebConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(value = ReviewController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)

public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    private final ObjectMapper mapper = new ObjectMapper();
    private static ReviewDto reviewDto;

    @BeforeAll
    static void initializateValues() {

        reviewDto = ReviewDto
                .builder()
                .comment("testComment")
                .build();
    }

    @Test
    void createReviewTest() throws Exception {

        given(reviewService.add(any(ReviewDto.class))).willReturn(reviewDto);

        mockMvc.perform(post("/review")
                        .content(mapper.writeValueAsString(reviewDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    public void getAllReviews() throws Exception {

        when(reviewService.getAll()).thenReturn(Arrays.asList(reviewDto));

        mockMvc.perform(get("/review"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].comment").value(reviewDto.getComment()))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getAllByRequestIdTest() throws Exception {

        given(reviewService.getAllByRequestId(1L)).willReturn(Arrays.asList(reviewDto));

        mockMvc.perform(get("/review/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].comment").value(reviewDto.getComment()));
    }

    @Test
    void validateNotBlankCommentFieldTest() throws Exception {

        ReviewDto reviewDto = ReviewDto
                .builder()
                .comment("")
                .build();

        given(reviewService.add(any(ReviewDto.class))).willReturn(reviewDto);

        mockMvc.perform(post("/review")
                        .content(mapper.writeValueAsString(reviewDto))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isNotAcceptable())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
