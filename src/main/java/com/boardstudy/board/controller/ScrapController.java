package com.boardstudy.board.controller;

import com.boardstudy.board.Service.ScrapService;
import com.boardstudy.board.Service.UserService;
import com.boardstudy.board.Service.GroupOrderService;
import com.boardstudy.board.entity.GroupOrder;
import com.boardstudy.board.entity.Scrap;
import com.boardstudy.board.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/scrap")
public class ScrapController {

    @Autowired
    private ScrapService scrapService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupOrderService groupOrderService;  // GroupOrderService 추가

    @PostMapping("/add")
    public String addScrap(@RequestParam Integer userId, @RequestParam Integer groupOrderId) {
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + userId));

        GroupOrder groupOrder = groupOrderService.findGroupOrderById(groupOrderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group order Id: " + groupOrderId));

        Scrap scrap = new Scrap();
        scrap.setUser(user);
        scrap.setGroupOrder(groupOrder);

        scrapService.saveScrap(scrap);
        return "redirect:/scrap/list?userId=" + userId;  // 수정: 스크랩 목록으로 리다이렉트
    }

    @GetMapping("/list")
    public List<Scrap> getUserScraps(@RequestParam Integer userId) {
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + userId));
        return scrapService.getScrapsByUser(user);
    }
}
