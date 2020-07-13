package cn.guyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Des 主页的controller
 * @Author guyu
 * @Date 2020/7/13 22:24
 * @Param
 * @Return
 */
@Controller
public class IndexController {

    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        //加载view视图
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(String username, String userpwd, HttpSession session,
                            Model model) {

        if ("admin".equals(username) && "123456".equals(userpwd)) {
            session.setAttribute("username", username);
            //重定向首页
            return "redirect:index";
        }
        //用户名或者密码错误
        String info = "管理员账号、密码错误，重新输入";
        model.addAttribute("info", info);
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        //清除会话数据
        session.invalidate();

        //页面跳转
        return "redirect:login";
    }


}
