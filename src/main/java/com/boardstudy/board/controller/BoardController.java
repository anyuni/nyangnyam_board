package com.boardstudy.board.controller;

import com.boardstudy.board.Service.BoardService;
import com.boardstudy.board.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;


    @GetMapping("/board/write") //localhost:8090/board/write
    public String boardWriteForm(){

        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, @RequestParam("file") MultipartFile file) throws Exception{

        boardService.write(board,file);

        //글 작성 완료된 메세지를 띄우기(confirm)
        model.addAttribute("message", "글 작성 완료");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model,
                            @PageableDefault(page=0, size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(value = "searchkeyword", required = false) String searchkeyword){

        Page<Board> list = null;

        if(searchkeyword == null){
            list = boardService.boardList(pageable);
        }else{
            list = boardService.boardSearchList(searchkeyword, pageable);
        }

        int nowPage= list.getPageable().getPageNumber()+1;
        int startPage= Math.max(nowPage-4,1);
        int endPage= Math.min(nowPage+5,list.getTotalPages());
        model.addAttribute("List", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "boardlist";
    }

    @GetMapping("/board/view")// localhost:8090/board/view?id=1
    public String boardView(Model model, @RequestParam("id") Integer id){

        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("id") Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){

        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, @RequestParam("file") MultipartFile file)  throws Exception {

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp, file);
        return "redirect:/board/list";
    }
}
