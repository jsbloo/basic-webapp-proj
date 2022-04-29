package bae.project.api.controller;

import bae.project.api.domain.Factoid;
import bae.project.api.domain.User;
import bae.project.api.security.UserPrincipalDetailsService;
import bae.project.api.service.FactoidService;
import bae.project.api.service.UserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
public class UserControllerUnitTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private FactoidService factoidService;

    @MockBean
    private UserService service;

    @MockBean
    private UserPrincipalDetailsService userPrincipalDetailsService;

    @Test
    public void createTest() throws Exception{
        User entry = new User("username1","password1",1,"USER");
        String entryAsJSON = mapper.writeValueAsString(entry);

        User result = new User(1L,"username1","password1",1,"USER");
        String resultAsJSON = mapper.writeValueAsString(result);

        Mockito.when(service.create(entry)).thenReturn(result);

        mvc.perform(post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(entryAsJSON)).andExpect(status().isCreated())
                .andExpect(content().json(resultAsJSON));
    }

    @Test
    public void getByIdTest() throws Exception{
        User result = new User(2L,"name","test content",1, "USER");
        String resultAsJSON = this.mapper.writeValueAsString(result);

        Mockito.when(this.service.getById(2L)).thenReturn(result);

        mvc.perform(get("/user/getById/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(resultAsJSON));
    }

    //TODO:not working idk why
/*    @Test
    public void getByUsernameTest() throws Exception{
        User result = new User(2L,"name","test_content",1, "USER");
        String resultAsJSON = this.mapper.writeValueAsString(result);

        Mockito.when(this.service.findByUsername("user")).thenReturn(result);

        mvc.perform(get("/user/name")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(resultAsJSON));
    }*/
}
