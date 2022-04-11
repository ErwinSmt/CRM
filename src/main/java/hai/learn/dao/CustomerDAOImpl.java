package hai.learn.dao;

import hai.learn.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class CustomerDAOImpl implements CustomerDAO{

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getCustomers() {

        // get the current hibernate session
        Session session = this.sessionFactory.getCurrentSession();

        // create a query
        List<Customer> customers = session.createQuery("from Customer ",Customer.class).getResultList();

        // return the results

        return customers;
    }

    @Override
    public void saveCustomer(Customer saveCustomer) {

        Session session = this.sessionFactory.getCurrentSession();

        // save or update customer
        session.saveOrUpdate(saveCustomer);


    }

    @Override
    public Customer getCustomer(int theId) {

        Session session = this.sessionFactory.getCurrentSession();

        return session.get(Customer.class, theId);
    }

    @Override
    public void deleteCustomer(int theId) {

        Session session = sessionFactory.getCurrentSession();

        Query query =
                session.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId",theId);

        query.executeUpdate();
    }
}
