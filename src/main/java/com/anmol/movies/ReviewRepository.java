package com.anmol.movies;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends MongoRepository<Review , Object> {
}
