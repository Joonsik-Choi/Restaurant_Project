package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.Review;
import kr.co.fastcampus.eatgo.application.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(@PathVariable("restaurantId") Long restaurantId,  @Validated @RequestBody Review resource) throws URISyntaxException {
        Review review= reviewService.addReview(restaurantId, resource);
        String url = "/restaurants/"+restaurantId+"/reviews/"+review.getId();
        return ResponseEntity.created(new URI(url)
        ).body("{}");
    }
}
