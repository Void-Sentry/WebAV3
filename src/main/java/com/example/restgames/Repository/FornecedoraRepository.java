package com.example.restgames.Repository;

import com.example.restgames.Model.Fornecedora;
import com.example.restgames.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FornecedoraRepository extends JpaRepository<Fornecedora, Long> {
    List<Fornecedora> findByDeletedIsNull();
    Optional<Fornecedora> findByDeletedIsNullAndId(Long id);
}
