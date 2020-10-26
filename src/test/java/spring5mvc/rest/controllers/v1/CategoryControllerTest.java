package spring5mvc.rest.controllers.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring5mvc.rest.api.v1.model.CategoryDTO;
import spring5mvc.rest.services.CategoryService;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest {

    private static final String NAME = "Jim";

    @Mock
    CategoryService categoryService;
    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                                 .build();
    }

    @Test
    void getAllCategories() throws Exception {
        List<CategoryDTO> categoryDTOS =
                List.of(CategoryDTO.builder()
                                   .id(1L)
                                   .name(NAME)
                                   .build(),
                        CategoryDTO.builder()
                                   .id(2L)
                                   .name("Fruit")
                                   .build());

        when(categoryService.getAllCategories()).thenReturn(categoryDTOS);


        mockMvc.perform(get(CategoryController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    void getCategoryByName() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                                             .id(1L)
                                             .name(NAME)
                                             .build();

        when(categoryService.getCategoryByName(anyString())).thenReturn(categoryDTO);


        mockMvc.perform(get(CategoryController.BASE_URL + "/" + NAME).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name", equalTo(NAME)));
        //                       .andReturn()
        //                       .getResponse()
        //                       .getContentAsString();
    }

    @Test
    void getCategoryByNameNotFound() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                                             .id(1L)
                                             .name(NAME)
                                             .build();

        when(categoryService.getCategoryByName(anyString())).thenReturn(categoryDTO);

        mockMvc.perform(get(CategoryController.BASE_URL + "Foo").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }
}