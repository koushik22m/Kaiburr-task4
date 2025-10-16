package com.example.kaiburr.repository;

import com.example.kaiburr.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository; // if MongoDB
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, Long> {
}
