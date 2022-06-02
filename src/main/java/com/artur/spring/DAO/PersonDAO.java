package com.artur.spring.DAO;

import com.artur.spring.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
       return session.createQuery("select p from Person  p", Person.class).getResultList();
    }

    @Transactional
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }
    @Transactional
    public Optional<Person> show(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person  p", Person.class).getResultList().stream().
                filter(person -> person.getEmail().equals(email)).findAny();
                }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person toBeUpdated = session.get(Person.class,id);
        toBeUpdated.setName(updatedPerson.getName());
        toBeUpdated.setAge(updatedPerson.getAge());
        toBeUpdated.setEmail(updatedPerson.getEmail());
    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class,id));
    }

//    public void testMultipleUpdate() {
//        List<Person> people = create1000person();
//        long before = System.currentTimeMillis();
//
//        for (Person person : people) {
//            jdbcTemplate.update("INSERT INTO person VALUES (?,?,?,?,?)",
//                   person.getId(), person.getName(), person.getAge(), person.getEmail(),person.getAddress());
//        }
//
//        long after = System.currentTimeMillis();
//        System.out.println("Time " + (after - before));
//    }
//
//    public void testBatchUpdate() {
//        List<Person> people = create1000person();
//        long before = System.currentTimeMillis();
//
//        jdbcTemplate.batchUpdate("INSERT INTO person VALUES (?,?,?,?)", new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setInt(1,people.get(i).getId());
//                ps.setString(2,people.get(i).getName());
//                ps.setInt(3,people.get(i).getAge());
//                ps.setString(4,people.get(i).getEmail());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return people.size();
//            }
//        });
//
//        long after = System.currentTimeMillis();
//        System.out.println("Time " + (after - before));
//    }
//
//    private List<Person> create1000person() {
//        List<Person> people = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            people.add(new Person(i, "Name" + i, 30, "test" + i + "@mail.ru", "belgorod"));
//        }
//        return people;
//    }
}
