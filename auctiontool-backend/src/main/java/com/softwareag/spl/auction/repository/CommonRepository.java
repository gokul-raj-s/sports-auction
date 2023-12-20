package com.softwareag.spl.auction.repository;

import com.softwareag.spl.auction.model.Common;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CommonRepository extends ElasticsearchRepository<Common, String> {
}
