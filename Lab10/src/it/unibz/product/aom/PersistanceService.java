package it.unibz.product.aom;

public class PersistenceService {
    private SessionFactory sessionFactory;

    public PersistenceService() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void save(Object entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Object entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    public Object retrieve(Class clazz, Long id) {
        Session session = sessionFactory.openSession();
        Object entity = session.get(clazz, id);
        session.close();
        return entity;
    }
}

