package com.koszalka.shortener.unit;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestBody;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.koszalka.shortener.bo.ShortenerBO;
import com.koszalka.shortener.persistence.dto.ShortenerDTO;
import com.koszalka.shortener.persistence.entities.ShortenerEntity;
import com.koszalka.shortener.rest.controllers.ShortenerController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortenerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ShortenerBO shortenerBo;

    @InjectMocks
    private ShortenerController shortenerController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(shortenerController).build();
    }

    @Test
    private void shortenerControllerGetGone() throws Exception {
        Mockito
            .when(shortenerBo.getUrlFromHash("bf9ZP7"))
            .thenReturn(new ShortenerEntity());

        mockMvc
            .perform(MockMvcRequestBuilders.get("/v1/bf9ZP7"))
            .andExpect(status().isGone());
    }

    @Test
    private void shortenerControllerPost() throws Exception {

        ShortenerDTO data = new ShortenerDTO();
        data.setExpiresAt(Long.valueOf("1565343583227"));
        data.setOriginalUrl("https://glboo.com");

        this.mockMvc
            .perform(post("/v1/new")
             .contentType(MediaType.APPLICATION_JSON).content(data.toString()))
            .andExpect(status().isCreated());
    }
}
