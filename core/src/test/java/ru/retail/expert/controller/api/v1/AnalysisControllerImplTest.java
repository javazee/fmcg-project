package ru.retail.expert.controller.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.retail.expert.model.LoadShipmentsDto;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
class AnalysisControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAnalyseAll() throws Exception {
        this.mockMvc.perform(get("/api/v1/analyse"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("success")));
    }

    @Test
    void testLoadByFilters() throws Exception {
        this.mockMvc.perform(post("/api/v1/analyse")
                        .content(objectMapper.writeValueAsString(new LoadShipmentsDto(Collections.EMPTY_LIST, List.of("Chain 1", "Chain 2"), Collections.EMPTY_LIST)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("success")));
    }
}
