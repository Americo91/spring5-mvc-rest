package spring5mvc.rest.services;

import org.springframework.stereotype.Service;
import spring5mvc.rest.api.v1.model.CategoryDTO;

import java.util.List;

/**
 * Created by @author stopp on 22/10/2020
 */
public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
