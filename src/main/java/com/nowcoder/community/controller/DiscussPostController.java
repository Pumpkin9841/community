package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author pumpkin
 * @date 2022/3/13
 */
@Controller
@RequestMapping("/discuss")
public class DiscussPostController {

    @Autowired
    private DiscussPostService discussPostService ;

    @Autowired
    private UserService userService ;

    @Autowired
    private HostHolder hostHolder ;

    @PostMapping("/add")
    @ResponseBody
    public String addDisscussPost( String title ,String content ){
        User user = hostHolder.getUser();
        if( user == null ){
            return CommunityUtil.getJSONString(403 , "你还没有登录哦") ;
        }
        DiscussPost post = new DiscussPost();
        post.setUserId(String.valueOf(user.getId()));
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post) ;
        return CommunityUtil.getJSONString(0 , "发布成功") ;
    }

    @GetMapping("/detail/{discussPostId}")
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model){
        //帖子
        DiscussPost discussPost = discussPostService.findDiscussPostById(discussPostId);
        model.addAttribute("post" , discussPost) ;
        //作者
        User user = userService.findUserById(Integer.parseInt(discussPost.getUserId()));
        model.addAttribute("user" , user) ;
        //TODO 帖子的回复

        return "site/discuss-detail" ;
    }
}
