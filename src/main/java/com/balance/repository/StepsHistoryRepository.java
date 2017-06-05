package com.balance.repository;

import com.balance.model.StepsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by da_20 on 5/6/2017.
 */
@Repository
public interface StepsHistoryRepository extends JpaRepository<StepsHistory, Integer> {
}
