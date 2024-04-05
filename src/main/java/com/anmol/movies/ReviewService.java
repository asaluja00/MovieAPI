package com.anmol.movies;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review CreateReview(String reviewBody  , String imbdId){
        Review review = reviewRepository.insert(new Review(reviewBody));



        // As out Movie class containes array of reviews
        // we need to create a service class which will create an object of review
        // then we will push that object in out array for review in movie;

        // first we will create review body  and push it in review object
        // then we look into movie table and search for ibdb id of movie using matching criteria
        // then we will simple push that review into the movie object database using monngoDB template

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imbdId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;

    }
}