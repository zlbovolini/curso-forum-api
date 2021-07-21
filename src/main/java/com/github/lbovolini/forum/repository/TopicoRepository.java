package com.github.lbovolini.forum.repository;

import com.github.lbovolini.forum.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
