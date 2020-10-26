package spring5mvc.rest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.web.JsonPath;

import java.util.List;

/**
 * Created by @author stopp on 22/10/2020
 */
@Data
@AllArgsConstructor
public class CategoryListDTO {
    @JsonProperty("categories")
    List<CategoryDTO> categoryDTOList;
}
