package com.dunk.django.service;

import java.util.Optional;

import com.dunk.django.domain.Notice;
import com.dunk.django.dto.GenericListDTO;
import com.dunk.django.dto.NoticeDTO;
import com.dunk.django.dto.PageDTO;
import com.dunk.django.repository.NoticeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class NoticeServiceImpl implements NoticeService {
    // 자동주입 @RequiredArgsConstructor 선언 필요
    private final NoticeRepository repository;

    @Override
    public Notice get(Long nno) {
        Optional<Notice> entity = repository.findById(nno);

        return entity.get();
    }

    @Override
    public GenericListDTO<NoticeDTO, Notice> getList(PageDTO pageDTO) {
        final Sort sort = Sort.by("nno").descending();

        Pageable pageable = pageDTO.makePageable(sort);

        Page<Notice> result = repository.findAll(pageable);

        log.info(result);

        GenericListDTO<NoticeDTO, Notice> listDTO = new GenericListDTO<>(result, en -> bindToDTO(en));

        return listDTO;
    }

}