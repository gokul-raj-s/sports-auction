package com.softwareag.spl.auction.repository;

import com.softwareag.spl.auction.model.Team;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TeamRepository extends ElasticsearchRepository<Team, String> {
}
