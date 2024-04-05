package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.PageDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.service.BoardService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody : 데이터 주고 받는 REST 역할
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @PostMapping("/post.do")
    public boolean postBoard(BoardDto boardDto){
        return boardService.postBoard(boardDto);
    }

    @GetMapping("/get.do")
    public PageDto getBoard(int page, int view){
        System.out.println("page = " + page + ", view = " + view);
        return boardService.getBoard(page, view);
    }

    @PutMapping("/put.do")
    public boolean putBoard(){
        return boardService.putBoard();
    }

    @DeleteMapping("/delete.do")
    public boolean deleteBoard(int bno){
        return boardService.deleteBoard(bno);
    }

}
