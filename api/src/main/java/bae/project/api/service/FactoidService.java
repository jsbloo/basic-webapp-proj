package bae.project.api.service;

import bae.project.api.domain.Factoid;
import bae.project.api.exceptions.FactoidNotFoundException;
import bae.project.api.exceptions.InvalidFactoidException;
import bae.project.api.repo.FactoidRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoidService {

    private FactoidRepo repo;

    public FactoidService(FactoidRepo repo){this.repo = repo;}

    public Factoid create(Factoid factoid){ return repo.saveAndFlush(factoid);}

    public Factoid getById(long id){return repo.findById(id).orElseThrow(FactoidNotFoundException::new);}

    public List<Factoid> getAll(){return repo.findAll();}

    public Factoid update(long id, Factoid factoid){
        Factoid existing = repo.findById(id).orElseThrow(FactoidNotFoundException::new);
        factoid.setId(existing.getId());

        try{repo.saveAndFlush(factoid);}catch (Exception e){throw new InvalidFactoidException();//TODO:rethink this
        }

        return factoid;
    }

    public boolean delete(long id){
        Factoid factoid = repo.findById(id).orElseThrow(FactoidNotFoundException::new);
        repo.delete(factoid);

        return !repo.existsById(id);
    }

    public Factoid getRandom() {
        return repo.getRandom();
    }
}
