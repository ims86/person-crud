package br.com.ims.personcrud.repositories;

import br.com.ims.personcrud.entities.Contact;
import br.com.ims.personcrud.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
