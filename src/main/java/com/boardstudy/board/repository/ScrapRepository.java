package com.boardstudy.board.repository;

import com.boardstudy.board.entity.Scrap;
import com.boardstudy.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Integer> {
    List<Scrap> findByUser(User user);
}
