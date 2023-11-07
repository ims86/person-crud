package br.com.ims.personcrud.services;

import br.com.ims.personcrud.entities.Contact;
import br.com.ims.personcrud.repositories.ContactRepository;
import br.com.ims.personcrud.services.exceptions.DatabaseException;
import br.com.ims.personcrud.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {
    
    @Autowired
    private ContactRepository repository;

    public Contact findById(Long id){
        Optional<Contact> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Contact insert(Contact obj){
        try{
            return repository.save(obj);
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

    public void update(Long id, Contact obj){
        try {
//            Contact entity = repository.getReferenceById(id);
//            updateData(obj, entity);
            repository.save(obj);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private static void updateData(Contact obj, Contact entity) {
        entity.setName(obj.getName());
        entity.setPhone(obj.getPhone());
        entity.setEmail(obj.getEmail());
    }

}
