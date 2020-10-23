package spring5mvc.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring5mvc.rest.domain.Category;

/**
 * Created by @author stopp on 21/10/2020
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
