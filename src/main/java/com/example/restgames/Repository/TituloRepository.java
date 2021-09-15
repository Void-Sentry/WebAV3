package com.example.restgames.Repository;

import com.example.restgames.Model.Game;
import com.example.restgames.Model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface TituloRepository extends JpaRepository<Titulo, Long> {
    List<Titulo> findByDeletedIsNull();
    Optional<Titulo> findByDeletedIsNullAndId(Long id);
}