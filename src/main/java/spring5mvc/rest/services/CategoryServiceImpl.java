package spring5mvc.rest.services;

import org.springframework.stereotype.Service;
import spring5mvc.rest.api.v1.mapper.CategoryMapper;
import spring5mvc.rest.api.v1.model.CategoryDTO;
import spring5mvc.rest.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by @author stopp on 22/10/2020
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                                 .stream()
                                 .map(categoryMapper::categoryToCategoryDTO)
                                 .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
