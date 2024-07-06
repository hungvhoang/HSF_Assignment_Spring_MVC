package HSF301_Assignment_Spring_MVC.repositories;

import HSF301_Assignment_Spring_MVC.pojos.Review;
import HSF301_Assignment_Spring_MVC.pojos.ReviewKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, ReviewKey> {}

