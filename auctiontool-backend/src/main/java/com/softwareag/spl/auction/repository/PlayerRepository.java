package com.softwareag.spl.auction.repository;

import com.softwareag.spl.auction.model.Player;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PlayerRepository extends ElasticsearchRepository<Player, String> {
}
