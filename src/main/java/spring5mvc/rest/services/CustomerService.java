package spring5mvc.rest.services;

import spring5mvc.rest.api.v1.model.CustomerDTO;

import java.util.List;

/**
 * Created by @author stopp on 22/10/2020
 */
public interface CustomerService {

    List<CustomerDTO> getCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);
}
