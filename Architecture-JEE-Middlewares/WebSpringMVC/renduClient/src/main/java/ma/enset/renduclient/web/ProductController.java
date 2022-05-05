package ma.enset.renduclient.web;

import lombok.AllArgsConstructor;
import ma.enset.renduclient.dtos.ProductDTO;
import ma.enset.renduclient.entities.Product;
import ma.enset.renduclient.repositories.ProductRepository;
import ma.enset.renduclient.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
 @AllArgsConstructor
public class ProductController {
    ProductRepository productRepository;
    ProductService productService;
    @GetMapping("/products")
    public List<ProductDTO> getProducts(){
      return   productService.products();
    }
    @GetMapping("/products/{id}")
    public  Product getProduct(@PathVariable String id){
        return productRepository.findById(id).get();
    }

    @PostMapping("/products")
    public ProductDTO postProduct(@RequestBody ProductDTO product){
        return productService.save(product);
    }

    @PutMapping("/products/{id}")
    public ProductDTO putProduct(@RequestBody ProductDTO productDTO,@PathVariable String id){
        productDTO.setId(id);

        return    productService.save(productDTO);
    }

    @DeleteMapping("/products/{id}")
    public  void deleteProduct(@PathVariable String id){
         productRepository.deleteById(id);
    }
}
