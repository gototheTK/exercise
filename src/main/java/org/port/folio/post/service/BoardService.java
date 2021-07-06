package org.port.folio.post.service;

import java.util.List;

import org.port.folio.post.model.Board;
import org.port.folio.post.model.Reply;
import org.port.folio.post.repository.BoardRepository;
import org.port.folio.post.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;


    @Transactional
    public void 글쓰기(Board board){

        boardRepository.save(board);

    }

    @Transactional(readOnly = true)
    public Page<Board> 글목록가져오기(Pageable pageable){
        
        Page<Board> boards= boardRepository.findAll(pageable);

        return boards;

    }

    @Transactional(readOnly = true)
    public Board 글상세보기(Integer id){
        
        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당하는 글이 존재하지 않습니다.");
        });

        return board;

    }

    @Transactional
    public void 글수정하기(Board board){

        Board entity = boardRepository.findById(board.getId()).orElseThrow(()->{
            return new IllegalArgumentException("해당하는 글이 없습니다.");
        });

        entity.setTitle(board.getTitle());
        entity.setContent(board.getContent());

        boardRepository.save(entity);

    }

    @Transactional
    public void 글삭제하기(Integer id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 댓글등록하기(Integer userId, Integer boardId,Reply reply){

        replyRepository.mSave(userId, boardId, reply.getContent());
        
    }


    @Transactional
    public void 댓글삭제하기(Integer id){

        replyRepository.deleteById(id);
        
    }


}
