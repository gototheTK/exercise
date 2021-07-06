package org.port.folio.post.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.port.folio.post.config.auth.PrincipalDetails;
import org.port.folio.post.model.Board;
import org.port.folio.post.model.Reply;
import org.port.folio.post.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class BoardController {

    @Autowired
    BoardService boardService;


    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size=5, sort="id", direction = Sort.Direction.DESC) Pageable pageable){


        Page<Board> boards = boardService.글목록가져오기(pageable);

        

        List<String> pages = new ArrayList<>();

        String tag0 = "<li class='page-item disabled'> <a class='page-link' href='?page=";
        String tag1 = "<li class='page-item'> <a class='page-link' href='?page=";
        String tag2 = "'>";
        String tag3 = "</a><li/>";

        int total = boards.getTotalPages();
        int current = pageable.getPageNumber();
        int size = 3;
        // int strat = current - current%size;
        // int end = current + (size-current%size);
        int half = size / 2;
        int strat = 0;
        int end = size;
        int odd = size%2;
        if(current>half){
           strat = current + half< total ? current - half :
        total-size;
            end = current + half<total ? current + (half) + odd : total;
        }


        for(int i=strat; i<end && i<total; i++){
            String page = "";
            if(pageable.getPageNumber()==i){
                page = tag0 + i +tag2 + (i+1) + tag3;
            }else {
                page = tag1 + i +tag2 + (i+1) + tag3;
            }
            pages.add(page);
        }


        model.addAttribute("boards", boards);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("pages", pages);
        model.addAttribute("next", pageable.next().getPageNumber());

        return "/index";
    }

    @GetMapping("/board/writeForm")
    public String writeForm(){
        return "/board/writeForm";
    }

    @GetMapping("/board/updateForm/{id}")
    public String updateForm(@PathVariable Integer id, Model model){

        Board board = boardService.글상세보기(id);

        model.addAttribute("board", board);

        

        return "/board/updateForm";
    }

    @GetMapping("/board/detailForm/{id}")
    public String detailForm(@AuthenticationPrincipal PrincipalDetails principal,@PathVariable Integer id,Model model){

        Board board = boardService.글상세보기(id);
        
        String username = principal.getUsername();

        String tag0 = 
        "<li id=\"reply--{{id}}\" class=\"list-group-item d-flex justify-content-between\">"
        + "<div>{{content}}</div>" + "<div id=\"delete--{{id}}\" class=\"font-italic\">작성자 : {{user.username}} &nbsp" +
        " <a href=\"/board/replyDelete/{{id}}/{{board.id}}\" class=\"btn btn-danger badge\">삭제</a></div></li>";
        
        String tag1 = 
        "<li id=\"reply--{{id}}\" class=\"list-group-item d-flex justify-content-between\">"
        + "<div>{{content}}</div>" + "<div id=\"delete--{{id}}\" class=\"font-italic\">작성자 : {{user.username}} &nbsp" + "</div></li>"
        ;

        Iterator<Reply> itr = board.getReplys().iterator();

        
        
        List<String> replys = new ArrayList<>();
        
        while(itr.hasNext()){

            if(itr.next().getUser().getUsername().equals(username)){
                replys.add(tag0);
            }else{
                replys.add(tag1);
            }
 
        }


        model.addAttribute("board",board);
        model.addAttribute("replys", replys);
        model.addAttribute("writer", board.getUser().getUsername().equals(username));
        return"/board/detailForm";
    }

}
