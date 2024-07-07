package HSF301_Assignment_Spring_MVC.repositories;

import HSF301_Assignment_Spring_MVC.pojos.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
