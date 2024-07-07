package HSF301_Assignment_Spring_MVC.services;

import HSF301_Assignment_Spring_MVC.pojos.Review;
import HSF301_Assignment_Spring_MVC.pojos.ReviewKey;

import java.util.List;

public interface IReviewService {

    public void save(Review review);

    public void update(Review review);

    public void delete(ReviewKey id);

    public List<Review> getAll();

    public Review findByID(ReviewKey id);

}
