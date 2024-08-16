package com.boardstudy.board.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="board_id", nullable=false)
    private Board board;
    private String content;
    private String author;

}