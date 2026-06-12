package com.example.apkabazadanych2.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

}
