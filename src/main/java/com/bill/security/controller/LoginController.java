package com.bill.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Deprecated
@Controller
public class LoginController {
    private final String PREFIX = "pages/";

    @GetMapping("/")
    public String index() {
        return "welcome";
    }


    /**
     * login page
     *
     * @return
     */
    @GetMapping("/userlogin")
    public String loginPage() {
        return PREFIX + "login";
    }

    @PostMapping("/user")
    public String loginUser() {
        return "welcome";
    }

    /**
     * level1
     *
     * @param path
     * @return
     */
    @GetMapping("/level1/{path}")
    public String level1(@PathVariable("path") String path) {
        return PREFIX + "level1/" + path;
    }

    /**
     * level2
     *
     * @param path
     * @return
     */
    @GetMapping("/level2/{path}")
    public String level2(@PathVariable("path") String path) {
        return PREFIX + "level2/" + path;
    }

    /**
     * level3
     *
     * @param path
     * @return
     */
    @GetMapping("/level3/{path}")
    public String level3(@PathVariable("path") String path) {
        return PREFIX + "level3/" + path;
    }


}
