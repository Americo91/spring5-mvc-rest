package spring5mvc.rest.api.v1.mapper;

import org.junit.jupiter.api.Test;
import spring5mvc.rest.api.v1.model.CustomerDTO;
import spring5mvc.rest.domain.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private static final String LASTNAME = "Fallon";
    private static final String NAME = "Jimmy";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    void testCustomerToCustomerDTO() {
        Customer customer = Customer.builder().firstName(NAME).lastName(LASTNAME).build();
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertNotNull(customerDTO);
        assertEquals(NAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());
    }

    @Test
    void customerDtoToCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(NAME);
        customerDTO.setLastName(LASTNAME);
        customerDTO.setId(1L);

        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);

        assertNotNull(customer);
        assertEquals(1l, customer.getId());
        assertEquals(NAME, customer.getFirstName());
        assertEquals(LASTNAME, customer.getLastName());
    }
}