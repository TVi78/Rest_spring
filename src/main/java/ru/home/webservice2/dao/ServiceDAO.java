package ru.home.webservice2.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.home.webservice2.models.Landmark;
import ru.home.webservice2.models.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * CRUD операции Service
 *
 * @see Service
 */

@Slf4j
@Component
public class ServiceDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public ServiceDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Service> index() {
        log.info("get all services");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select s from Service s", Service.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Service show(int id) {
        log.info("get show");
        Session session = sessionFactory.getCurrentSession();
        return session.get(Service.class, id);
    }

    @Transactional
    public void save(Service service) {
        Session session = sessionFactory.getCurrentSession();
        session.save(service);
        log.info("save");
    }

    @Transactional
    public void update(int id, Service updatedService) {
        Session session = sessionFactory.getCurrentSession();
        Service serviceToBeUpdated = session.get(Service.class, id);
        serviceToBeUpdated.setDescription(updatedService.getDescription());
        log.info("Service update");
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Service.class, id));
        log.info("Service delete");
    }
}
