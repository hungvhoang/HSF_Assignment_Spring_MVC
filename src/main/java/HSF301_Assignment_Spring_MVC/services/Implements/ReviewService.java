package HSF301_Assignment_Spring_MVC.services.Implements;

import HSF301_Assignment_Spring_MVC.pojos.Review;
import HSF301_Assignment_Spring_MVC.pojos.ReviewKey;
import HSF301_Assignment_Spring_MVC.repositories.ReviewRepository;
import HSF301_Assignment_Spring_MVC.services.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void update(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void delete(ReviewKey id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findByID(ReviewKey id) {
        return reviewRepository.getReferenceById(id);
    }
}
