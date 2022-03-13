package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pumpkin
 * @date 2022/3/10
 */
@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService ;

    @Autowired
    private UserService userService ;

    @GetMapping("/index")
    public String getIndex(Model model , Page page){
        page.setRows(discussPostService.findDiscussPostsRows(0));
        System.out.println(page.getRows());
        page.setPath("index");
        List<DiscussPost> discussPostList = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<HashMap<String, Object>> discussPosts = new ArrayList<>() ;
        for (DiscussPost post : discussPostList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("post" , post) ;
            User user = userService.findUserById(Integer.valueOf(post.getUserId()));
            map.put("user" , user) ;
            discussPosts.add(map) ;
        }
        model.addAttribute("discussPosts" , discussPosts) ;
        return "index" ;
    }

}
