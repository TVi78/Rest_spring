package ru.home.webservice2.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.home.webservice2.models.City;

import java.util.List;

/**
 * CRUD операции City
 *
 * @see City
 */
@Slf4j
@Component
public class CityDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CityDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<City> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from City p", City.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public City show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(City.class, id);
    }

    @Transactional
    public void save(City city) {
        Session session = sessionFactory.getCurrentSession();
        session.save(city);
    }

    @Transactional
    public void update(int id, City updatedCity) {
        Session session = sessionFactory.getCurrentSession();
        City cityToBeUpdated = session.get(City.class, id);
        cityToBeUpdated.setName(updatedCity.getName());
        cityToBeUpdated.setPopulation(updatedCity.getPopulation());
        cityToBeUpdated.setMetro(updatedCity.isMetro());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(City.class, id));
    }
}
