package br.com.ims.personcrud.config;

import br.com.ims.personcrud.entities.Contact;
import br.com.ims.personcrud.entities.Person;
import br.com.ims.personcrud.repositories.ContactRepository;
import br.com.ims.personcrud.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ContactRepository contactRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void run(String... args) throws Exception {

        Person p1 = new Person(null, "Person 1", "25514523411", sdf.parse("01/01/1990"));
        Person p2 = new Person(null, "Person 2", "08859861217", sdf.parse("01/02/1990"));

        Contact c1 = new Contact(null, "Contato 1 - Person 1", "999999999", "contato1@gmail.com", p1);
        Contact c2 = new Contact(null, "Contato 2 - Person 1", "999999999", "contato2@gmail.com", p1);
        Contact c3 = new Contact(null, "Contato 1 - Person 2", "999999999", "contato3@gmail.com", p2);

        personRepository.saveAll(Arrays.asList(p1, p2));
        contactRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
}
