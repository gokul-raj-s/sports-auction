package com.softwareag.spl.auction.repository;

import com.softwareag.spl.auction.model.PlayerAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PlayerAssignmentRepository extends ElasticsearchRepository<PlayerAssignment, String> {

    @Query("{\"match_phrase\": {\"teamName\": {\"query\": \"?0\"}}}")
    Page<PlayerAssignment> findByTeamName(String teamName, Pageable pageable);
}
