package com.myStreaming.DysnovPlus.dao.repository;

import com.myStreaming.DysnovPlus.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActeurRepo extends JpaRepository<Personne, Long> {
}
