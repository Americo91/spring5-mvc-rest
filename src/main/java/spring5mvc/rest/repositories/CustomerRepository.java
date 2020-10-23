package spring5mvc.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring5mvc.rest.domain.Customer;

/**
 * Created by @author stopp on 22/10/2020
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
