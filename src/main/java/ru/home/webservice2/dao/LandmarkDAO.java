package ru.home.webservice2.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.home.webservice2.models.Landmark;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * CRUD операции Landmark
 *
 * @see Landmark
 */

@Slf4j
@Component
public class LandmarkDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public LandmarkDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Landmark> index() {
        log.info("get all landmarks");
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Landmark p", Landmark.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<Landmark> sortFiltr(Map<String, String> params) {
        log.info("get sort-filtr");
        Session session = sessionFactory.getCurrentSession();
        if ((params.get("sort") != null) && (params.get("filtr") == null)) {
            return session.createQuery("select p from Landmark p order by p.name asc", Landmark.class)
                    .getResultList();
        } else if ((params.get("filtr") != null) && (params.get("sort") == null)) {
            String filtr = params.get("filtr").toUpperCase();
            return session.createQuery("select p from Landmark p where p.type=:filtr", Landmark.class)
                    .setParameter("filtr", filtr)
                    .getResultList();
        } else if (params.size() == 0) {
            return session.createQuery("select p from Landmark p", Landmark.class)
                    .getResultList();
        } else {
            String filtr = params.get("filtr").toUpperCase();
            return session.createQuery("select p from Landmark p where p.type=:filtr order by p.name asc", Landmark.class)
                    .setParameter("filtr", filtr)
                    .getResultList();
        }
    }

    @Transactional(readOnly = true)
    public Landmark show(int id) {
        log.info("get show");
        Session session = sessionFactory.getCurrentSession();
        return session.get(Landmark.class, id);
    }

    @Transactional(readOnly = true)
    public List filtrCity(String city) {
        log.info("get filtrCity");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c.landmark from City c where c.name=:city", Collection.class);
        query.setParameter("city", city);
        return query.getResultList();
    }

    @Transactional
    public void save(Landmark landmark) {
        Session session = sessionFactory.getCurrentSession();
        session.save(landmark);
        log.info("save");
    }

    @Transactional
    public void update(int id, Landmark updatedLandmark) {
        Session session = sessionFactory.getCurrentSession();
        Landmark landmarkToBeUpdated = session.get(Landmark.class, id);
        landmarkToBeUpdated.setDescription(updatedLandmark.getDescription());
        log.info("get update");
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Landmark.class, id));
        log.info("get delete");
    }
}
