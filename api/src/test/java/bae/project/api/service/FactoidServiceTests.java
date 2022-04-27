package bae.project.api.service;

import bae.project.api.domain.Factoid;
import bae.project.api.repo.FactoidRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FactoidServiceTests {

    @Autowired
    private FactoidService service;

    @MockBean
    private FactoidRepo repo;

    @Test
    public void getAllTest() {
        List<Factoid> output = new ArrayList<>();
        output.add(new Factoid("test content",true, "test explanation"));

        Mockito.when(this.repo.findAll()).thenReturn(output);

        assertEquals(output, this.service.getAll());

        Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }

    @Test
    public void getByIdTest() {
        Optional<Factoid> OptionalOutput = Optional.of(new Factoid(1L,"test content",true, "test explanation"));
        Factoid output = new Factoid(1L,"test content",true, "test explanation");

        Mockito.when(this.repo.findById(1L)).thenReturn(OptionalOutput);

        assertEquals(output, this.service.getById(1L));

        Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
    }

    @Test
    public void getRandomTest() {
        Factoid output = new Factoid(1L,"test false content",false, "test false explanation");

        Mockito.when(this.repo.getRandom()).thenReturn(output);

        assertEquals(output,this.service.getRandom());

        Mockito.verify(this.repo, Mockito.times(1)).getRandom();
    }

    @Test
    public void createTest() {
        Factoid input = new Factoid("test content",true, "test explanation");
        Factoid output = new Factoid(1L,"test content",true, "test explanation");

        Mockito.when(this.repo.saveAndFlush(input)).thenReturn(output);

        assertEquals(output, this.service.create(input));

        Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(input);
    }

    @Test
    public void updateTest() {
        Factoid input = new Factoid("test content",true, "test explanation");
        Optional<Factoid> existing = Optional.of(new Factoid(1L,"test content123",true, "test explanation123"));
        Factoid output = new Factoid(1L,"test content",true, "test explanation");

        Mockito.when(this.repo.findById(1L)).thenReturn(existing);
        Mockito.when(this.repo.saveAndFlush(output)).thenReturn(output);

        assertEquals(output, this.service.update(1L, input));

        Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
        Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(output);
    }

    @Test
    public void deleteTest() {
        Factoid output = new Factoid(1L,"test content",true, "test explanation");

        Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(output));
        Mockito.when(this.repo.existsById(1L)).thenReturn(false);

        assertTrue(this.service.delete(1L));

        Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
        Mockito.verify(this.repo, Mockito.times(1)).delete(output);
        Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
    }
}
