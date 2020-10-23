package spring5mvc.rest.boostrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring5mvc.rest.domain.Category;
import spring5mvc.rest.domain.Customer;
import spring5mvc.rest.repositories.CategoryRepository;
import spring5mvc.rest.repositories.CustomerRepository;

/**
 * Created by @author stopp on 21/10/2020
 */
@Component
public class Boostrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Boostrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");
        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded = " + categoryRepository.count() );

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("JIM");
        customer.setLastName("Dev");

        customerRepository.save(customer);
        System.out.println("Customer loaded = " + customerRepository.count());

    }
}
