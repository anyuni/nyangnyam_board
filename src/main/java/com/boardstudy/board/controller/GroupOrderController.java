package com.boardstudy.board.controller;

import com.boardstudy.board.Service.GroupOrderService;
import com.boardstudy.board.entity.GroupOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GroupOrderController {

    @Autowired
    private GroupOrderService groupOrderService;

    @GetMapping("/group-order/create")
    public String showOrderForm(Model model) {
        model.addAttribute("groupOrder", new GroupOrder());
        return "group-order/grouporderform";  // Correct path to your form template
    }

    @PostMapping("/group-order/create")
    public String createOrder(GroupOrder groupOrder, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            if (groupOrder.getContent() == null || groupOrder.getContent().trim().isEmpty()) {
                return "redirect:/group-order/create?error=content";
            }
            if (groupOrder.getTeamSize() <= 0) {
                return "redirect:/group-order/create?error=teamSize";
            }
            if (groupOrder.getDeliveryLocation() == null || groupOrder.getDeliveryLocation().trim().isEmpty()) {
                return "redirect:/group-order/create?error=deliveryLocation";
            }
            if (groupOrder.getMenu() == null || groupOrder.getMenu().trim().isEmpty()) {
                return "redirect:/group-order/create?error=menu";

            }
            groupOrderService.createOrder(groupOrder, imageFile);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/group-order/create?error";
        }
        return "redirect:/group-order/list";
    }

    @GetMapping("/group-order/list")
    public String listOrders(Model model) {
        model.addAttribute("orders", groupOrderService.getAllOrders());
        return "group-order/grouporderlist";  // Correct path to your list template
    }
}
