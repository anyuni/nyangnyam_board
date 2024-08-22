package com.boardstudy.board.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GroupOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int teamSize;

    @Column(nullable = false)
    private String deliveryLocation;

    @Column(nullable = false)
    private String menu;

    private String imagePath;

    private String category;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt = new java.util.Date();
}
