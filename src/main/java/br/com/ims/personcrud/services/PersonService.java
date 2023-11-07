package br.com.ims.personcrud.services;

import br.com.ims.personcrud.entities.Contact;
import br.com.ims.personcrud.entities.Person;
import br.com.ims.personcrud.repositories.PersonRepository;
import br.com.ims.personcrud.services.exceptions.DatabaseException;
import br.com.ims.personcrud.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository repository;

    @Autowired
    private ContactService contactService;

    public List<Person> findAll(){
        return repository.findAll();
    }

    public Person findById(Long id){
        Optional<Person> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Person insert(Person obj){
        try{
            Person person = repository.save(obj);
            return updatePersonContact(obj, person);
        }
        catch (ConstraintViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Person update(Long id, Person obj){
        try {
            Person entity = repository.getReferenceById(id);
            updateData(obj, entity);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateData(Person obj, Person entity) {
        entity.setName(obj.getName());
        entity.setBornDate(obj.getBornDate());
        for(Contact x : obj.getContacts()){
            x.setPerson(entity);
            contactService.update(x.getId(), x);
        }
    }

    public Person updatePersonContact(Person obj, Person entity) {
        for(Contact x : obj.getContacts()){
            x.setPerson(entity);
        }
        return repository.save(entity);
    }
}
