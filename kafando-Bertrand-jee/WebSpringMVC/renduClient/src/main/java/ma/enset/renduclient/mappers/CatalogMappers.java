package ma.enset.renduclient.mappers;

import ma.enset.renduclient.dtos.CategoryDTO;
import ma.enset.renduclient.dtos.ProductDTO;
import ma.enset.renduclient.entities.Category;
import ma.enset.renduclient.entities.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service //ou component
public class CatalogMappers {
    public ProductDTO fromProduct(Product product){
        ProductDTO productDTO=new ProductDTO();
        BeanUtils.copyProperties(product,productDTO);
        productDTO.setCategoryDTO(fromCategory(product.getCategory()));
        return productDTO;
    }

    public Product fromProductDTO(ProductDTO productDTO){
        Product product=new Product();
        BeanUtils.copyProperties(productDTO,product);
        product.setCategory(fromCategoyDTO(productDTO.getCategoryDTO())); //pour eviter mettre le meme nom
        return  product;
    }


    public CategoryDTO fromCategory(Category category){
        CategoryDTO categoryDTO=new CategoryDTO();
        BeanUtils.copyProperties(category,categoryDTO);
        return categoryDTO;
    }
    public  Category fromCategoyDTO(CategoryDTO categoryDTO){
        Category category=new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        return  category;
    }
}
