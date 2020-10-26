package spring5mvc.rest.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spring5mvc.rest.api.v1.mapper.CustomerMapper;
import spring5mvc.rest.api.v1.model.CustomerDTO;
import spring5mvc.rest.domain.Customer;
import spring5mvc.rest.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    public static final Long ID = 1L;
    public static final String NAME = "Jimmy";

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void createNewCustomer() {
        Customer customer = Customer.builder().id(ID).firstName(NAME).build();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);
        assertNotNull(savedDto);
        assertEquals(ID, savedDto.getId());
        assertEquals(NAME, savedDto.getFirstName());
        assertEquals("/api/v1/customers/1", savedDto.getCustomerUrl());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void saveCustomerByDTO() {
        Customer customer = Customer.builder().id(ID).firstName(NAME).build();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(customer.getFirstName());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        CustomerDTO savedDto = customerService.saveCustomerByDTO(ID, customerDTO);
        assertNotNull(savedDto);
        assertEquals(ID, savedDto.getId());
        assertEquals(NAME, savedDto.getFirstName());
        assertEquals("/api/v1/customers/1", savedDto.getCustomerUrl());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void getCustomers() {
        List<Customer> customers = List.of(new Customer(), new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getCustomers();
        assertEquals(customers.size(), customerDTOS.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void getCustomerById() {
        Customer customer = Customer.builder().id(ID).firstName(NAME).build();

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        CustomerDTO customerDTO = customerService.getCustomerById(ID);
        assertEquals(ID, customerDTO.getId());
        assertEquals(NAME, customerDTO.getFirstName());
        verify(customerRepository, times(1)).findById(anyLong());
    }

    @Test
    void patchCustomer() {
        Customer customer = Customer.builder().id(ID).firstName(NAME).build();

        String updateName = "Tommy";

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO dto = CustomerDTO.builder().id(ID).firstName(updateName).build();

        CustomerDTO updatedCustomerDTO = customerService.patchCustomer(ID, dto);
        assertNotNull(updatedCustomerDTO);
        assertEquals(ID, updatedCustomerDTO.getId());
        assertNotEquals(NAME, updatedCustomerDTO.getFirstName());
    }

    @Test
    void deleteCustomer() {
        customerRepository.deleteById(ID);
        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}