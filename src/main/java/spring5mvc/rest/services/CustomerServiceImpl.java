package spring5mvc.rest.services;

import org.springframework.stereotype.Service;
import spring5mvc.rest.api.v1.mapper.CustomerMapper;
import spring5mvc.rest.api.v1.model.CustomerDTO;
import spring5mvc.rest.controllers.v1.CustomerController;
import spring5mvc.rest.domain.Customer;
import spring5mvc.rest.repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by @author stopp on 22/10/2020
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll()
                                 .stream()
                                 .map(
                                         customer -> {
                                             CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                                             customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                                             return customerDTO;
                                         })
                                 .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                                 .map(customerMapper::customerToCustomerDTO)
                                 .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        customerDTO.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));
        return customerDTO;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);
        return saveAndReturnDTO(customer);
    }

    private String getCustomerUrl(Long id) {
        return CustomerController.BASE_URL +"/"+ id;
    }
}
