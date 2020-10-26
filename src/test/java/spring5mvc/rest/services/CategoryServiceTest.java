package spring5mvc.rest.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spring5mvc.rest.api.v1.mapper.CategoryMapper;
import spring5mvc.rest.api.v1.model.CategoryDTO;
import spring5mvc.rest.domain.Category;
import spring5mvc.rest.repositories.CategoryRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    public static final Long ID = 1L;
    public static final String NAME = "Jimmy";

    CategoryService categoryService;
    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategories() {
        List<Category> categoryList  = List.of(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categoryList);

        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
        assertEquals(categoryList.size(), categoryDTOS.size());
    }

    @Test
    void getCategoryByName() {
        Category category = Category.builder().id(ID).name(NAME).build();

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}