package com.example.sprintboottest.repositories;

import com.example.sprintboottest.models.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long> {
}
