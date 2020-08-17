package com.dunk.django.service;

import javax.transaction.Transactional;

import com.dunk.django.domain.Notice;
import com.dunk.django.dto.GenericListDTO;
import com.dunk.django.dto.NoticeDTO;
import com.dunk.django.dto.PageDTO;

@Transactional
public interface NoticeService {

    /* <DTO, Entity> */
    GenericListDTO<NoticeDTO, Notice> getList(PageDTO pageDTO);

    Notice get(Long nno);

    // DTO객체를 Entity(VO)객체로 바꾼다.
    default Notice bindToEntity(NoticeDTO dto) {
        Notice entity = Notice.builder().nno(dto.getNno()).title(dto.getTitle()).content(dto.getContent())
                .writer(dto.getWriter()).build();

        return entity;
    }

    default NoticeDTO bindToDTO(Notice entity) {
        NoticeDTO dto = NoticeDTO.builder().nno(entity.getNno()).title(entity.getTitle()).content(entity.getContent())
                .writer(entity.getWriter()).build();

        return dto;
    }
}