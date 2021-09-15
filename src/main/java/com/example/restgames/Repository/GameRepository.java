package com.example.restgames.Repository;

import java.util.List;
import com.example.restgames.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByDeletedIsNull();
    Optional<Game> findByDeletedIsNullAndId(Long id);
}
