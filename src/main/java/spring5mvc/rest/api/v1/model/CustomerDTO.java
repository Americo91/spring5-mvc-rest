package spring5mvc.rest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by @author stopp on 22/10/2020
 */
@Data
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    @JsonProperty("customer_url")
    private String customerUrl;
    private String firstName;
    private String lastName;

    @Builder
    public CustomerDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
