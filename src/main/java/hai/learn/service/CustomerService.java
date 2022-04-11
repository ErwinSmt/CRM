package hai.learn.service;

import hai.learn.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers();

    public void saveCustomer(Customer createCustomer);

    Customer getCustomer(int theId);

    void deleteCustomer(int theId);
}
