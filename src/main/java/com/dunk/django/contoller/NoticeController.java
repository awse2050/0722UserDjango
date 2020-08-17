package com.dunk.django.contoller;

import com.dunk.django.domain.Notice;
import com.dunk.django.dto.GenericListDTO;
import com.dunk.django.dto.NoticeDTO;
import com.dunk.django.dto.PageDTO;
import com.dunk.django.service.NoticeService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService service;

    // 공지사항 전체 보기
    @GetMapping("/list")
    public void getList(@ModelAttribute("pageDTO") PageDTO pageDTO, Model model) {

        GenericListDTO<NoticeDTO, Notice> result = service.getList(pageDTO);

        result.getDtoList().forEach(dto -> log.info(dto));

        model.addAttribute("result", result);
    }

    // 공지사항 게시물 하나 보기
    @GetMapping("/get")
    public void get(Authentication auth, Long nno, @ModelAttribute("pageDTO") PageDTO pageDTO, Model model) {
        log.info("/get.................");
        log.info("nno : " + nno);
        log.info("pageDTO : " + pageDTO);

        model.addAttribute("get", service.get(nno));
    }
}