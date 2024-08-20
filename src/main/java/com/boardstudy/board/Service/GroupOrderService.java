package com.boardstudy.board.Service;

import com.boardstudy.board.entity.GroupOrder;
import com.boardstudy.board.repository.GroupOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupOrderService {

    @Autowired
    private GroupOrderRepository groupOrderRepository;

    public void createOrder(GroupOrder groupOrder, MultipartFile imageFile) throws IOException {
        if(imageFile != null && !imageFile.isEmpty()){
            String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/images";
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);
            imageFile.transferTo(saveFile);

            groupOrder.setImagePath("/images/" + fileName);
        }

        groupOrderRepository.save(groupOrder);
    }
    public Optional<GroupOrder> findGroupOrderById(Integer id) {
        return groupOrderRepository.findById(id);
    }
}
