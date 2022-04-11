package hai.learn.controller;

import hai.learn.dao.CustomerDAO;
import hai.learn.entity.Customer;
import hai.learn.service.CustomerService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String listCustomers(Model model){

        List<Customer> theCustomers = customerService.getCustomers();

        model.addAttribute("customer",theCustomers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String addCustomer(Model model){

        Customer customer = new Customer();

        model.addAttribute("customer",customer);


        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("addCustomer") Customer saveCustomer){

        // save the customer
        customerService.saveCustomer(saveCustomer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String updateCustomer(@RequestParam("customerId") int theId,
                                 Model model){
        // get the customer from database
        Customer customer = customerService.getCustomer(theId);

        // set customer as a model attribute to from
        model.addAttribute("customer", customer);
        // send over to out form

        return "customer-form";

    }

    @GetMapping("/showFormForDelete")
    public String deleteCustomer(@RequestParam("customerId") int theId){

        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";

    }

}
