package bae.project.api.controller;

import bae.project.api.domain.Factoid;
import bae.project.api.service.FactoidService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("factoid")
public class FactoidController {

    FactoidService service;

    public FactoidController(FactoidService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Factoid> create(@RequestBody Factoid factoid){
        return new ResponseEntity<>(service.create(factoid), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Factoid> getById(@PathVariable long id){
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Factoid>> getAll(){
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }

    @GetMapping("/getRandom")
    public ResponseEntity<Factoid> getRandom(){
        return new ResponseEntity<>(service.getRandom(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Factoid> update(@PathVariable long id, @RequestBody Factoid factoid){
        return new ResponseEntity<>(service.update(id,factoid), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        return (service.delete(id))? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//TODO:custom exception
    }

}
