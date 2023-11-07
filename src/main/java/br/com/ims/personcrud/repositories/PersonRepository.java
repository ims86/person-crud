package br.com.ims.personcrud.repositories;

import br.com.ims.personcrud.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository  extends JpaRepository<Person, Long> {
}
