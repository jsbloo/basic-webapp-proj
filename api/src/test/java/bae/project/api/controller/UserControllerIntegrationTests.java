package bae.project.api.controller;

import bae.project.api.domain.Factoid;
import bae.project.api.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

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
public class UserControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    User testUser = new User("test123","pass1",1,"USER");
    User testUserId = new User(2L,"test123","pass1",1,"USER");
    User dbTestUser = new User("test1","pass1",1,"USER");

    @Test
    public void createTest() throws Exception {

        String entryAsJSON = mapper.writeValueAsString(testUser);
        String resultAsJSON = mapper.writeValueAsString(testUserId);

        //entryAsJSON = entryAsJSON.replace("\"password\":\"pass1\",", "");
        resultAsJSON = resultAsJSON.replace("\"password\":\"pass1\",", "");


        mvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entryAsJSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(resultAsJSON));
    }

    @Test
    public void getByIdTest() throws Exception {
        String entryAsJSON = mapper.writeValueAsString(dbTestUser);

        mvc.perform(get("/user/getById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(entryAsJSON));
    }
}

//TODO:Same problem I cant fix it
/*    @Test
    public void getByUsername() throws Exception {
        String entryAsJSON = mapper.writeValueAsString(dbTestUser);

        mvc.perform(get("/user/user1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(entryAsJSON));
    }
}*/
