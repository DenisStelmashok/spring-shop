package com.stelmashok.dao;

import com.stelmashok.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository  extends JpaRepository<Bucket, Long> {
}
