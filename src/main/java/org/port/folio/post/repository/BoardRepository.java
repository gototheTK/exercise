package org.port.folio.post.repository;

import org.port.folio.post.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer>{
    
}
