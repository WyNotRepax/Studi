package de.hsos.swa.reederei.flottenmanagement.control;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import de.hsos.swa.reederei.flottenmanagement.entity.Schiff;

@ApplicationScoped
public class SchiffService {

    public List<Schiff> findAllSchiff(){
        return Schiff.listAll();
    }

    public Schiff findSchiffById(long id) {
        return Schiff.findById(id);
    }

    @Transactional
    public Schiff createSchiff(String name) {
        Schiff schiff = new Schiff(name);
        Schiff.persist(schiff);

        return schiff;
    }
}
