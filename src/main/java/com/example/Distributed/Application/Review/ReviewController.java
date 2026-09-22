package com.example.Distributed.Application.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;


@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    // @PostMapping("/review")
    // public String handleReview(
    //         @RequestParam Long productId,
    //         @RequestParam String productName,
    //         @RequestParam String userName,
    //         @RequestParam String reviewText,
    //         Model model) {
    //     ReviewModel review = new ReviewModel();
    //     review.setProductId(productId);
    //     review.setProductName(productName);
    //     review.setUserName(userName);
    //     review.setReviewText(reviewText);
    //     review.setDate(LocalDateTime.now());

    //     model.addAttribute("review", review);

    //     return "product-detail";
    // }

    @MessageMapping("/review")
    @SendTo("/topic/reviews")
    public ReviewModel broadcastReview(ReviewModel review) {
        
        review.setDate(LocalDateTime.now());
        reviewRepository.save(review); // Save to database
        
        return review;
     }


}
