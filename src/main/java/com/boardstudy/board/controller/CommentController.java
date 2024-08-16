package com.boardstudy.board.controller;

import com.boardstudy.board.Service.BoardService;
import com.boardstudy.board.Service.CommentService;
import com.boardstudy.board.entity.Board;
import com.boardstudy.board.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BoardService boardService;

    @PostMapping("/comment/add")
    public String addComment(@RequestParam("boardId") Integer boardId,
                             @RequestParam("content") String content,
                             @RequestParam("author") String author) {
        Board board = boardService.boardView(boardId);
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAuthor(author);
        comment.setBoard(board);

        commentService.saveComment(comment);

        return "redirect:/board/view?id=" + boardId;
    }

    @GetMapping("/comment/delete")
    public String deleteComment(@RequestParam("id") Integer id,
                                @RequestParam("boardId") Integer boardId) {
        commentService.deleteComment(id);
        return "redirect:/board/view?id=" + boardId;
    }



}
