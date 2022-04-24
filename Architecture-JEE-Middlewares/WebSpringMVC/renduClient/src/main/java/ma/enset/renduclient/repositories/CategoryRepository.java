package ma.enset.renduclient.repositories;

import ma.enset.renduclient.entities.Category;
import ma.enset.renduclient.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
