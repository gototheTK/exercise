package org.port.folio.post.repository;

import org.port.folio.post.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {


    @Modifying
    @Query(value = "INSERT INTO Reply(userId, boardId, content, createdate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
    public int mSave(Integer userId, Integer boardId, String content);

}
