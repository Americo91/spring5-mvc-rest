package spring5mvc.rest.controllers.v1;

import org.hibernate.jpa.boot.spi.Bootstrap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring5mvc.rest.api.v1.mapper.CustomerMapper;
import spring5mvc.rest.api.v1.model.CustomerDTO;
import spring5mvc.rest.boostrap.Boostrap;
import spring5mvc.rest.domain.Customer;
import spring5mvc.rest.repositories.CategoryRepository;
import spring5mvc.rest.repositories.CustomerRepository;
import spring5mvc.rest.services.CategoryService;
import spring5mvc.rest.services.CustomerService;
import spring5mvc.rest.services.CustomerServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by @author stopp on 26/10/2020
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ControllerIntegrationTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;
    CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading Data");
        System.out.println(customerRepository.findAll()
                                             .size());

        Boostrap bootstrap = new Boostrap(categoryRepository, customerRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void patchCustomerUpdateFirstName() {
        String updatedName = "UpdatedName";
        Long id = getCustomerIdValue();

        Customer original = customerRepository.getOne(id);
        assertNotNull(original);

        String originalFirstName = original.getFirstName();
        String originalLastName = original.getLastName();

        CustomerDTO customerDTO = CustomerDTO.builder().firstName(updatedName).build();

        customerService.patchCustomer(id, customerDTO);
        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getFirstName());
        assertNotEquals(originalFirstName, updatedCustomer.getFirstName());
        assertEquals(originalLastName, updatedCustomer.getLastName());
    }

    @Test
    public void patchCustomerUpdateLastName() {
        String updatedName = "UpdatedName";
        Long id = getCustomerIdValue();

        Customer original = customerRepository.getOne(id);
        assertNotNull(original);

        String originalFirstName = original.getFirstName();
        String originalLastName = original.getLastName();

        CustomerDTO customerDTO = CustomerDTO.builder().lastName(updatedName).build();

        customerService.patchCustomer(id, customerDTO);
        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(originalFirstName, updatedCustomer.getFirstName());
        assertEquals(updatedName, updatedCustomer.getLastName());
        assertNotEquals(originalLastName, updatedCustomer.getLastName());
    }

    private Long getCustomerIdValue(){
        List<Customer> customers = customerRepository.findAll();

        System.out.println("Customers Found: " + customers.size());

        //return first id
        return customers.get(0).getId();
    }
}
