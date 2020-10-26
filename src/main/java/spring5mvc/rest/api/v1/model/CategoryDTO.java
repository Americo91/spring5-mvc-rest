package spring5mvc.rest.api.v1.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by @author stopp on 21/10/2020
 */
@Data
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;

    @Builder
    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
