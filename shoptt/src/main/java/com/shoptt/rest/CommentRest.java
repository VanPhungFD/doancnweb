package com.shoptt.rest;

import com.shoptt.entity.Comment;
import com.shoptt.entity.User;
import com.shoptt.repository.CommentRepository;
import com.shoptt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommentRest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/user/addcomment")
    public Comment save(@RequestBody Comment comment){
        User user = userService.getUserWithAuthority();
        comment.setCreatedDate(new Date(System.currentTimeMillis()));
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    @DeleteMapping("/user/deletecomment")
    public void deleteComment(@RequestParam("id") Long id) throws Exception {
        Comment comment = commentRepository.findById(id).get();
        User user = userService.getUserWithAuthority();
        if(user.getId() != comment.getUser().getId()){
            throw new Exception("bạn không đủ quyền");
        }
        commentRepository.delete(comment);
    }

    @GetMapping("/public/commentByPro")
    public List<Comment> findByProductId(@RequestParam("id") Long id){
        List<Comment> list = commentRepository.findByProId(id);
        try {
            User user = userService.getUserWithAuthority();
            for(Comment c : list){
                if(c.getUser().getId() == user.getId()){
                    c.setMyComment(1);
                }
                else{
                    c.setMyComment(0);
                }
            }
        }
        catch (Exception e){
            return list;
        }
        return list;
    }
}
