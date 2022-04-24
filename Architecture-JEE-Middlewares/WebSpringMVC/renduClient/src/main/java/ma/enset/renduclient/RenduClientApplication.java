package ma.enset.renduclient;

import ma.enset.renduclient.entities.Category;
import ma.enset.renduclient.entities.Product;
import ma.enset.renduclient.repositories.CategoryRepository;
import ma.enset.renduclient.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class RenduClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RenduClientApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository, CategoryRepository categoryRepository){
        return  args -> {
            Stream.of("Computers","printers","Smart Phones").forEach( p->{
                Category category=new Category();
                category.setName(p);
                categoryRepository.save(category);
            });

            categoryRepository.findAll().forEach(cat->{
                for(int i=1; i<=5;i++){
                    Product product=new Product();
                    product.setId(UUID.randomUUID().toString());
                    product.setPrix(Math.random()*9000);
                    product.setQuantity(Math.random()*50);
                    product.setNom(cat.getName()+"_"+i);
                    product.setCategory(cat);
                    productRepository.save(product);
                }
            });

        };
    }

}
