package ma.enset.renduclient.services;

import lombok.AllArgsConstructor;
import ma.enset.renduclient.dtos.ProductDTO;
import ma.enset.renduclient.entities.Product;
import ma.enset.renduclient.mappers.CatalogMappers;
import ma.enset.renduclient.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private CatalogMappers catalogMappers;
    private ProductRepository productRepository;
    @Override
    public ProductDTO save(ProductDTO productDTO) {
       Product product=catalogMappers.fromProductDTO(productDTO);
        product.setId(UUID.randomUUID().toString());
       Product savedProduct=productRepository.save(product);

        return catalogMappers.fromProduct(savedProduct);
    }

    @Override
    public List<ProductDTO> products() {
        List<Product>products=productRepository.findAll();
       List<ProductDTO>productDTOS=
               products.stream().map(
                       p->catalogMappers.fromProduct(p)

               ).collect(Collectors.toList());

        return productDTOS;
    }
}
