package com.dunk.django.contoller;

import com.dunk.django.domain.DjangoMember;
import com.dunk.django.service.MemberCheckService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class LoginController {

    private final MemberCheckService service;

    // 로그인창 들어가기
    @GetMapping("/login")
    public void login() {
        log.info("================/login================");
    }

    // 로그인 창에서 정보 입력
    @PostMapping("/login")
    public void postLogin(DjangoMember member, Model model) {
        log.info("================/Post login================");
        log.info(member);
        UserDetails findMember = service.loadUserByUsername(member.getId());

        log.info(findMember);

        model.addAttribute("userId", member.getId());
    }

    // 회원가입창으로 들어가기.
    @GetMapping("/signup")
    public void signUp() {
        log.info("================/signup================");
    }

    // 회원가입창에서 정보 입력 후
    @PostMapping("/signup")
    public String postSignUp(DjangoMember member) {
        log.info("================POST -- /signup================");
        log.info(member);

        service.register(member);

        return "redirect:/django/index";
    }

    // 로그아웃 이후 URL 이동
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/member/login";
    }

    /* Security Test Page */
    @GetMapping("/all")
    public void all() {
        log.info("all...");
    }

    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/member")
    public void member() {
        log.info("member....");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void admin() {
        log.info("admin...");
    }

}