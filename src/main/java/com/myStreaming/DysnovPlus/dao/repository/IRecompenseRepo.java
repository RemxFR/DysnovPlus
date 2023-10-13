package com.myStreaming.DysnovPlus.dao.repository;

import com.myStreaming.DysnovPlus.entity.Recompenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecompenseRepo extends JpaRepository<Recompenses, Integer> {
}
