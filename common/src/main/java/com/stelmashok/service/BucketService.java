package com.stelmashok.service;

import com.stelmashok.domain.Bucket;
import com.stelmashok.domain.User;
import com.stelmashok.dto.BucketDto;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);

    void addProducts (Bucket bucket, List<Long> productIds);

    BucketDto getBucketByUser(String name);
}
