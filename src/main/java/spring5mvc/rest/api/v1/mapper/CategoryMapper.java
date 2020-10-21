package spring5mvc.rest.api.v1.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import spring5mvc.rest.api.v1.model.CategoryDTO;
import spring5mvc.rest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by @author stopp on 21/10/2020
 */
@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
