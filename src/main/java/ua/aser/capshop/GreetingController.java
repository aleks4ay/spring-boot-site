package ua.aser.capshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.aser.capshop.domain.Customer;
import ua.aser.capshop.domain.Product;
import ua.aser.capshop.repository.CustomerRepository;
import ua.aser.capshop.repository.ProductRepository;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/myapp/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }
    @GetMapping("/myapp/shop")
    public String main(Map<String, Object> model) {
        Iterable<Product> products = productRepository.findAll();
        model.put("products", products);
        return "main_page";
    }

    @PostMapping("/myapp/add")
    public String main(@RequestParam String name,@RequestParam Integer price,
                       @RequestParam Integer amount,@RequestParam String description,
                       Map<String, Object> model) {
        Product product = new Product(name, price,amount,description);
        productRepository.save(product);
        return "redirect:shop";
    }

    @PostMapping("/myapp/filter")
    public String filter (@RequestParam String filter, Map<String, Object> model) {
        Iterable<Product> products;
        if (filter != null && !filter.isEmpty()) {
            products = productRepository.findByName(filter);
            model.put("products", products);
            return "main_page";
        } else {
            return "redirect:shop";
        }
    }
    @PostMapping("/myapp/delete")
    public String deleteById (@RequestParam String id, Map<String, Object> model) {
        productRepository.deleteById(Integer.valueOf(id));
        return "redirect:shop";
    }

    @RequestMapping("/")
    public String start() {
        return "redirect:myapp/shop";
    }

    @GetMapping("/customer")
    public String mainCustomer(Map<String, Object> model) {
        Iterable<Customer> customers = customerRepository.findAll();
        model.put("customers", customers);
        return "customer_page";
    }
    @GetMapping("/customer_add")
    public String newCustomer(Map<String, Object> model) {
        return "customer_new";
    }
    @PostMapping("/customer_add")
    public String newCustomer(@RequestParam String name,@RequestParam String address,
                       @RequestParam String email, Map<String, Object> model) {
        Customer customer = new Customer(name, email, address);
        customerRepository.save(customer);
        return "redirect:customer";
    }

    @GetMapping("/delete_customer")
    public String deleteCustomerById (@RequestParam String id, Map<String, Object> model) {
        customerRepository.deleteById(Long.valueOf(id));
        return "redirect:customer";
    }

}