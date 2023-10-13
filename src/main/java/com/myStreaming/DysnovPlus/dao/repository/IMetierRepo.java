package com.myStreaming.DysnovPlus.dao.repository;

import com.myStreaming.DysnovPlus.entity.Metier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMetierRepo extends JpaRepository<Metier, Integer> {

    @Query("SELECT m FROM Metier m WHERE m.metier = ?1")
    Optional<Metier> getMetierByName(String name);

}
