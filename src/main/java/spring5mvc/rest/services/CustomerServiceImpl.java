package spring5mvc.rest.services;

import org.springframework.stereotype.Service;
import spring5mvc.rest.api.v1.mapper.CustomerMapper;
import spring5mvc.rest.api.v1.model.CustomerDTO;
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
                                             customerDTO.setCustomerUrl("/api/v1/customer/" + customer.getId());
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
}
