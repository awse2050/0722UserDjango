package com.dunk.django.contoller;

import com.dunk.django.domain.Board;
import com.dunk.django.dto.BoardDTO;
import com.dunk.django.dto.GenericListDTO;
import com.dunk.django.dto.PageDTO;
import com.dunk.django.service.BoardService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/board")
public class BoardController {
    // 주입
    private final BoardService service;

    // 자유게시판 전체 보기
    @GetMapping("/list")
    public void list(@ModelAttribute("pageDTO") PageDTO pageDTO, Model model) {

        GenericListDTO<BoardDTO, Board> result = service.getList(pageDTO);

        result.getDtoList().forEach(dto -> log.info(dto));

        model.addAttribute("result", result);
    }

    // 게시물 등록 창 가기
    @GetMapping("/register")
    public void register(@ModelAttribute("pageDTO") PageDTO pageDTO, Authentication auth, Model model) {
        log.info("/register.....................");
        // 로그인 했을 때만 정보를 전달
        if (auth != null) {
            log.info(auth.getName());
            model.addAttribute("username", auth.getName());
        }
    }

    // 게시물 등록시
    @PostMapping("/register")
    public String register(BoardDTO dto, RedirectAttributes rttr) {
        log.info("POST Register.....................");
        log.info("dto : " + dto);

        Long bno = service.register(dto);

        rttr.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }

    // 게시물 보기 혹은 수정 창으로
    @GetMapping({ "/get", "/modify" })
    public void get(Authentication auth, Long bno, @ModelAttribute("pageDTO") PageDTO pageDTO, Model model) {
        log.info("/get.................");
        log.info("bno : " + bno);
        log.info("pageDTO : " + pageDTO);

        model.addAttribute("get", service.get(bno));
    }

    // 게시물 수정 창에서 수정시.
    @PostMapping("/modify")
    public String modify(BoardDTO dto, @ModelAttribute("pageDTO") PageDTO pageDTO, RedirectAttributes rttr) {
        log.info("POST MODIFY------------------------------------------");
        log.info(dto);

        service.modify(dto);
        rttr.addAttribute("bno", dto.getBno());
        rttr.addAttribute("page", pageDTO.getPage());
        rttr.addAttribute("size", pageDTO.getSize());

        return "redirect:/board/get";
    }

    // 게시물 제거시
    @PostMapping("/remove")
    public String remove(Long bno) {
        log.info("/remove...........................");
        log.info("bno : " + bno);
        service.remove(bno);

        return "redirect:/board/list";
    }

}