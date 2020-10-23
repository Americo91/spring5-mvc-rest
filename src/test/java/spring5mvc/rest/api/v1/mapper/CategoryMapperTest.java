package spring5mvc.rest.api.v1.mapper;

import org.junit.jupiter.api.Test;
import spring5mvc.rest.api.v1.model.CategoryDTO;
import spring5mvc.rest.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    public static final String NAME = "Joe";
    private static final Long ID = 1L;
    CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        CategoryDTO categoryDto = mapper.categoryToCategoryDTO(category);
        assertEquals(NAME, categoryDto.getName());
        assertEquals(ID, categoryDto.getId());
    }
    
}