package com.boardstudy.board.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private String filename;

    private String filepath;

    private String visibility; //공개 범위 (예: 공개, 비공개)

    private boolean isRestaurant;

    private String category; //카테고리 (예: 한식, 중식, 양식 등)

    private int likeCount; //좋아요 수

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;


}


