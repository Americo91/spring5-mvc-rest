package spring5mvc.rest.controllers.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring5mvc.rest.api.v1.model.CustomerDTO;
import spring5mvc.rest.domain.Customer;
import spring5mvc.rest.exceptions.ResourceNotFoundException;
import spring5mvc.rest.services.CustomerService;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static spring5mvc.rest.controllers.v1.AbstractRestControllerTest.asJsonString;

class CustomerControllerTest {

    private static final String NAME = "Jim";
    private static final String LASTNAME = "Dumper";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                                 .setControllerAdvice(new RestResponseEntityExceptionHandler())
                                 .build();
    }

    @Test
    void getAllCustomers() throws Exception {
        List<CustomerDTO> customerDTOS = List.of(CustomerDTO.builder()
                                                            .build(), CustomerDTO.builder()
                                                                                 .build());

        when(customerService.getCustomers()).thenReturn(customerDTOS);

        mockMvc.perform(get(CustomerController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    void getCustomerById() throws Exception {
        CustomerDTO customerDTO = createCustomerDTO();

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get(CustomerController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.firstName", equalTo(NAME)))
               .andExpect(jsonPath("$.lastName", equalTo(LASTNAME)))
               .andExpect(jsonPath("$.id", equalTo(1)));

    }

    @Test
    void createNewCustomer() throws Exception {
        CustomerDTO customerDTO = createCustomerDTO();
        CustomerDTO returnDto = createCustomerDTO();
        returnDto.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnDto);

        mockMvc.perform(post(CustomerController.BASE_URL).contentType(MediaType.APPLICATION_JSON)
                                                         .content(asJsonString(customerDTO)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.firstName", equalTo(NAME)))
               .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    void updateCustomer() throws Exception {
        CustomerDTO customerDTO = createCustomerDTO();
        CustomerDTO returnDto = createCustomerDTO();
        returnDto.setCustomerUrl(CustomerController.BASE_URL + "/1");
        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDto);


        mockMvc.perform(put(CustomerController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON)
                                                               .content(asJsonString(customerDTO)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.firstName", equalTo(NAME)))
               .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    void patchCustomer() throws Exception {
        CustomerDTO customerDTO = createCustomerDTO();
        CustomerDTO retDto = CustomerDTO.builder()
                                        .id(1L)
                                        .firstName(NAME)
                                        .lastName("Tom")
                                        .build();
        retDto.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(retDto);

        mockMvc.perform(patch(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.firstName", equalTo(NAME)))
               .andExpect(jsonPath("$.lastName", equalTo("Tom")))
               .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }


    @Test
    void deleteCustomer() throws Exception {
        mockMvc.perform(delete(CustomerController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
        verify(customerService, times(1)).deleteCustomer(anyLong());
    }

    @Test
    void testNotFoundException() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get(CustomerController.BASE_URL + "222").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }

    private CustomerDTO createCustomerDTO() {
        return CustomerDTO.builder()
                          .id(1L)
                          .firstName(NAME)
                          .lastName(LASTNAME)
                          .build();
    }
}
