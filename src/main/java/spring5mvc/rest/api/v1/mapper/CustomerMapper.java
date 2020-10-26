package spring5mvc.rest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import spring5mvc.rest.api.v1.model.CustomerDTO;
import spring5mvc.rest.domain.Customer;

/**
 * Created by @author stopp on 22/10/2020
 */
@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
