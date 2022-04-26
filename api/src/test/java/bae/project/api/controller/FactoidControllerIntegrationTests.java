package bae.project.api.controller;

import bae.project.api.domain.Factoid;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:testschema.sql",
        "classpath:testdata.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class FactoidControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void createTest() throws Exception {
        Factoid entry = new Factoid("test content",true, "test explanation");
        String entryAsJSON = mapper.writeValueAsString(entry);

        Factoid result = new Factoid(2L,"test content",true, "test explanation");
        String resultAsJSON = mapper.writeValueAsString(result);

        mvc.perform(post("/factoid/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entryAsJSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(resultAsJSON));

    }

    @Test
    public void getByIdTest() throws Exception {
        Factoid entry = new Factoid(1L,"test false content",false, "test false explanation");
        String entryAsJSON = mapper.writeValueAsString(entry);

        mvc.perform(get("/factoid/getById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(entryAsJSON));
    }

    @Test
    public void getAllTest() throws Exception{
        List<Factoid> output = new ArrayList<>();
        output.add(new Factoid(1L,"test false content",false, "test false explanation"));

        String outputAsJSON = this.mapper.writeValueAsString(output);

        mvc.perform(get("/factoid/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(outputAsJSON));
    }

    @Test
    public void getRandomTest() throws Exception{
        Factoid entry = new Factoid(1L,"test false content",false, "test false explanation");
        String entryAsJSON = this.mapper.writeValueAsString(entry);

        mvc.perform(get("/factoid/getRandom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entryAsJSON))
                .andExpect(status().isOk())
                .andExpect(content().json(entryAsJSON));
    }

    @Test
    public void updateTest() throws Exception {
        Factoid entry = new Factoid("test content",true, "test explanation");
        Factoid result = new Factoid(1L,"test content",true, "test explanation");

        String entryAsJSON = this.mapper.writeValueAsString(entry);
        String resultAsJSON = this.mapper.writeValueAsString(result);

        mvc.perform(put("/factoid/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entryAsJSON))
                .andExpect(status().isOk())
                .andExpect(content().json(resultAsJSON));
    }

    @Test
    public void deleteTest() throws Exception {
        mvc.perform(delete("/factoid/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
