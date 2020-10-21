package spring5mvc.rest.boostrap;

import org.springframework.boot.CommandLineRunner;
import spring5mvc.rest.domain.Category;
import spring5mvc.rest.repositories.CategoryRepository;

/**
 * Created by @author stopp on 21/10/2020
 */
public class Boostrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    public Boostrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");
        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        System.out.println("Data Loaded = " + categoryRepository.count() );
    }
}
