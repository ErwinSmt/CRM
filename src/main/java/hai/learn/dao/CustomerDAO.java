package hai.learn.dao;

import hai.learn.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> getCustomers();

    void saveCustomer(Customer saveCustomer);

    Customer getCustomer(int theId);

    void deleteCustomer(int theId);
}
