package com.artur.spring.DAO;

import com.artur.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PersonDAO {
    private List<Person> people;
    private static AtomicInteger PEOPLE_COUNT = new AtomicInteger();

    {
        people = new ArrayList<>();
        people.add(new Person(PEOPLE_COUNT.getAndIncrement(), "tom",29,"tom@mail.com"));
        people.add(new Person(PEOPLE_COUNT.getAndIncrement(), "Rick",20,"r@gmail.com"));
        people.add(new Person(PEOPLE_COUNT.getAndIncrement(), "Art", 33, "efwe@list.ru"));
        people.add(new Person(PEOPLE_COUNT.getAndIncrement(), "Kate", 19, "p@aaa.ru"));
    }

    public List<Person> index () { return people;}

    public Person show (int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save (Person person) {
        person.setId(PEOPLE_COUNT.getAndIncrement());
        people.add(person);
    }

    public void update (int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete (int id) {
       people.removeIf(person -> person.getId() == id);
    }
}
