package com.example.restgames.Repository;

import com.example.restgames.Model.Distribuidora;
import com.example.restgames.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface DistribuidoraRepository extends JpaRepository<Distribuidora, Long> {
    List<Distribuidora> findByDeletedIsNull();
    Optional<Distribuidora> findByDeletedIsNullAndId(Long id);
}
