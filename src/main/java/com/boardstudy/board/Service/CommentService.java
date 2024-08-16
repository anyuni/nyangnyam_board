package com.boardstudy.board.Service;

import com.boardstudy.board.entity.Comment;
import com.boardstudy.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByBoardId(Integer boardId){
        return commentRepository.findByBoardId(boardId);
    }
    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }
    public void deleteComment(Integer Id){
        commentRepository.deleteById(Id);
    }
}
