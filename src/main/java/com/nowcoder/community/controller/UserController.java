package com.nowcoder.community.controller;

import com.nowcoder.community.annotation.LoginRequired;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author pumpkin
 * @date 2022/3/13
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class) ;

    @Value("${community.path.domain}")
    private String domain ;

    @Value("${community.path.upload}")
    private String uploadPath ;

    @Value("${server.servlet.context-path}")
    private String contextPath ;

    @Autowired
    private UserService userService ;

    @Autowired
    private HostHolder hostHolder ;

    @GetMapping("/setting")
    @LoginRequired
    public String getSettingPage(){
        return "site/setting" ;
    }

    @PostMapping("/upload")
    @LoginRequired
    public String uploadHeader(MultipartFile headerImage , Model model){
        if( headerImage == null ){
            model.addAttribute("error" , "您还没有选择图片") ;
            return "site/setting" ;
        }
        String filename = headerImage.getOriginalFilename();
        //截取文件后缀
        String suffix = filename.substring(filename.lastIndexOf("."));
        if(StringUtils.isBlank(suffix)){
            model.addAttribute("error" , "文件格式不对") ;
            return "site/setting" ;
        }
        //生成随机文件名
        filename = CommunityUtil.generateUUID() + suffix;
        File dest = new File(uploadPath + "/" + filename);
        try {
            headerImage.transferTo(dest);
        } catch (IOException e) {
            logger.error("上传文件失败" + e.getMessage());
            throw new RuntimeException("上传文件失败，服务器发生异常" , e) ;
        }

        //更新当前用户的头像的路径（web路径）
        //http://localhost:8080/community/user/header/xxx.png
        User user = hostHolder.getUser();
        String headerUrl = domain + contextPath + "/user/header/" + filename ;
        userService.updateHeader(user.getId() , headerUrl) ;

        return "redirect:/index" ;
    }

    @GetMapping("/header/{fileName}")
    public void getHeader(@PathVariable("fileName") String fileName , HttpServletResponse response){
        //服务器存放路径
        fileName = uploadPath + "/" + fileName ;
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        response.setContentType("image/" + suffix);
        try (
            ServletOutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(fileName)
            ){
            byte[] buffer = new byte[1024];
            int b = 0 ;
            while( (b=fis.read(buffer)) != -1 ){
                os.write(buffer, 0 ,b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/changePassword")
    @LoginRequired
    public String changePassword(Model model , String oldPassword, String newPassword , String confirmPassword){
        if( StringUtils.isBlank(oldPassword) ){
            model.addAttribute("oldPasswordMsg" , "旧密码不能为空");
            return "site/setting" ;
        }

        if( StringUtils.isBlank(newPassword) ){
            model.addAttribute("newPasswordMsg" , "新密码不能为空") ;
            return "site/setting" ;
        }

        if( !newPassword.equals(confirmPassword) ){
            model.addAttribute("confirmPasswordMsg" , "两次数据密码不一样") ;
            return "site/setting" ;
        }

        User user = hostHolder.getUser();
        if( user.getPassword().equals(CommunityUtil.md5(oldPassword + user.getSalt())) ){
            int i = userService.changePassword(user.getId(), CommunityUtil.md5(newPassword + user.getSalt()));
        }
        else{
            model.addAttribute("oldPasswordMsg" , "旧密码错误");
            return "site/setting" ;
        }
        return "redirect:/index" ;
    }

}
