package bae.project.api.service;

import bae.project.api.domain.Factoid;
import bae.project.api.repo.FactoidRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoidService {

    private FactoidRepo repo;

    public FactoidService(FactoidRepo repo){this.repo = repo;}

    public Factoid create(Factoid factoid){ return repo.saveAndFlush(factoid);}

    public Factoid getById(long id){return repo.findById(id).get();}//TODO:add exception throw

    public List<Factoid> getAll(){return repo.findAll();}

    public Factoid update(long id, Factoid factoid){
        Factoid existing = repo.findById(id).get();//TODO:Add error checking
        factoid.setId(existing.getId());

        return repo.saveAndFlush(factoid);//TODO:Add error check
    }

    public boolean delete(long id){
        repo.deleteById(id);

        return !repo.existsById(id);
    }
}
