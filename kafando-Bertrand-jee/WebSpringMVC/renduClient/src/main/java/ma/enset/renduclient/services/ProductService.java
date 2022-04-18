package ma.enset.renduclient.services;

import ma.enset.renduclient.dtos.ProductDTO;

import java.util.List;

public interface ProductService {

    public ProductDTO save(ProductDTO productDTO);
    public List<ProductDTO>products();
}
