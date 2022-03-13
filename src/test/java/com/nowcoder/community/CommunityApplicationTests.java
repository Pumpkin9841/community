package com.nowcoder.community;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.mapper.DiscussPostMapper;
import com.nowcoder.community.mapper.LoginTicketMapper;
import com.nowcoder.community.mapper.UserMapper;
import com.nowcoder.community.util.MailClient;
import com.nowcoder.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests {

    @Autowired
    UserMapper userMapper ;

    @Autowired
    DiscussPostMapper discussPostMapper ;

    @Autowired
    LoginTicketMapper loginTicketMapper ;

    @Autowired
    private SensitiveFilter sensitiveFilter ;

    @Autowired
    private MailClient mailClient ;

    @Test
    void contextLoads() {
//        int i = discussPostMapper.selectDiscussPostRows(103);
//        System.out.println(i);
////        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(101, 0, 10);
////        for (DiscussPost post : discussPosts) {
////            System.out.println(post);
////        }
        mailClient.sendMail("602437361@qq.com" , "test" , "hello");
    }

    @Test
    void contextLoads1() {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(101);
        ticket.setTicket("abc");
        ticket.setStatus(0);
        ticket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));
        loginTicketMapper.insertLoginTicket(ticket) ;
    }

    @Test
    void contextLoads2() {
        LoginTicket abc = loginTicketMapper.selectByTicket("abc");
        System.out.println(abc);
        int abc1 = loginTicketMapper.updateStatus("abc", 1);
        System.out.println(abc1);
    }

    @Test
    void contextLoads3() {
        String text = "这里可以赌  博，可以嫖娼，可以吸毒，可以开票，哈哈哈" ;
        text = sensitiveFilter.filter(text);
        System.out.println(text);
    }

}
