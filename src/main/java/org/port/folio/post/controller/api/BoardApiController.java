package org.port.folio.post.controller.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.port.folio.post.config.auth.PrincipalDetails;
import org.port.folio.post.model.Board;
import org.port.folio.post.model.Reply;
import org.port.folio.post.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BoardApiController {

    @Autowired
    private BoardService boardService;


    @PostMapping("/board/write")
    public void write(Board board, @AuthenticationPrincipal PrincipalDetails principal ,HttpServletResponse response) throws IOException{
       
        board.setUser(principal.getUser());
       
        boardService.글쓰기(board);

        response.sendRedirect("/");
    }

    @PostMapping({"/board/update"})
    public void update(Board board,HttpServletResponse response) throws IOException{
       
       
        boardService.글수정하기(board);

        response.sendRedirect("/");
    }

    @GetMapping({"/board/delete/{id}"})
    public void delete(@PathVariable Integer id,HttpServletResponse response) throws IOException{
       
       
        boardService.글삭제하기(id);

        response.sendRedirect("/");
    }

    @PostMapping("/board/replyWrite")
    public void writeRply(Integer userId, Integer boardId,Reply reply,HttpServletResponse response) throws IOException{
       

       
        boardService.댓글등록하기(userId, boardId,reply);

        response.sendRedirect("/board/detailForm/" + boardId);
    }

    @GetMapping("/board/replyDelete/{id}/{boardId}")
    public void writeRply(@PathVariable Integer id, 
    @PathVariable Integer boardId,  HttpServletResponse response) throws IOException{
       

       
        boardService.댓글삭제하기(id);

        response.sendRedirect("/board/detailForm/" + boardId);
    }

}
