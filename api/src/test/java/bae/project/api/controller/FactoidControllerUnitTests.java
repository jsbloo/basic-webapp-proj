package bae.project.api.controller;

import bae.project.api.domain.Factoid;
import bae.project.api.service.FactoidService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FactoidControllerUnitTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private FactoidService service;

    @Test
    public void createTest() throws Exception{
        Factoid entry = new Factoid("test content",true, "test explanation");
        String entryAsJSON = mapper.writeValueAsString(entry);

        Factoid result = new Factoid(2L,"test content",true, "test explanation");
        String resultAsJSON = mapper.writeValueAsString(result);

        Mockito.when(service.create(entry)).thenReturn(result);

        mvc.perform(post("/factoid/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(entryAsJSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(resultAsJSON));
    }

    @Test
    public void getByIdTest() throws Exception{
        Factoid result = new Factoid(2L,"test content",true, "test explanation");
        String resultAsJSON = this.mapper.writeValueAsString(result);

        Mockito.when(this.service.getById(2L)).thenReturn(result);

        mvc.perform(get("/factoid/getById/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(resultAsJSON));
    }

    @Test
    public void updateTest() throws Exception {
        Factoid entry = new Factoid("test content",true, "test explanation");
        String entryAsJSON = this.mapper.writeValueAsString(entry);

        Mockito.when(this.service.update(1L, entry)).thenReturn(entry);

        mvc.perform(put("/factoid/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entryAsJSON))
                .andExpect(status().isOk())
                .andExpect(content().json(entryAsJSON));
    }

    @Test
    public void deleteTest() throws Exception {
        Mockito.when(this.service.delete(1L)).thenReturn(true);

        mvc.perform(delete("/factoid/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteFailTest() throws Exception {
        Mockito.when(this.service.delete(3L)).thenReturn(false);

        mvc.perform(delete("/factoid/delete/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void getRandomTest() throws Exception{
        Factoid entry = new Factoid("test content",true, "test explanation");
        String entryAsJSON = this.mapper.writeValueAsString(entry);

        Mockito.when(this.service.getRandom()).thenReturn(entry);

        mvc.perform(get("/factoid/getRandom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entryAsJSON))
                .andExpect(status().isOk())
                .andExpect(content().json(entryAsJSON));
    }

}
