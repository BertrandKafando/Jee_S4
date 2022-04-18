package ma.enset.renduclient.repositories;

import ma.enset.renduclient.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product,String> {
}
