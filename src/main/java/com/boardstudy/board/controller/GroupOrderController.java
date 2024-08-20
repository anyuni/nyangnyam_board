package com.boardstudy.board.controller;

import com.boardstudy.board.Service.GroupOrderService;
import com.boardstudy.board.entity.GroupOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GroupOrderController {

    @Autowired
    private GroupOrderService groupOrderService;

    @GetMapping("/group-order/create")
    public String showOrderForm(Model model) {
        model.addAttribute("groupOrder", new GroupOrder());
        return "groupOrderForm";
    }

    @PostMapping("/group-order/create")
    public String createOrder(GroupOrder groupOrder, MultipartFile imageFile) {
        try{
            groupOrderService.createOrder(groupOrder, imageFile);
        } catch(Exception e){
            e.printStackTrace();
            return "redirect:/group-order/create?error";
        }
        return "redirect:/group-order/list";
    }




}
