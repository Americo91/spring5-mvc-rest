package spring5mvc.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import spring5mvc.rest.api.v1.mapper.CustomerMapper;
import spring5mvc.rest.api.v1.model.CustomerDTO;
import spring5mvc.rest.domain.Customer;
import spring5mvc.rest.repositories.CustomerRepository;
import spring5mvc.rest.services.CustomerService;
import spring5mvc.rest.services.CustomerServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    public static final Long ID = 1L;
    public static final String NAME = "Luca";
    public static final String LASTNAME = "Bianco";

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void getCustomers() {
        List<Customer> customerList = List.of(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDTO> customerDTOS = customerService.getCustomers();
        assertEquals(3, customerDTOS.size());
    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(NAME);
        customer.setLastName(LASTNAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(ID);
        assertEquals(ID, customerDTO.getId());
        assertEquals(NAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());
    }
}