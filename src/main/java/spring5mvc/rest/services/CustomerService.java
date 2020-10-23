package spring5mvc.rest.services;

import org.springframework.stereotype.Service;
import spring5mvc.rest.api.v1.model.CustomerDTO;

import java.util.List;

/**
 * Created by @author stopp on 22/10/2020
 */
public interface CustomerService {

    List<CustomerDTO> getCustomers();

    CustomerDTO getCustomerById(Long id);
}
