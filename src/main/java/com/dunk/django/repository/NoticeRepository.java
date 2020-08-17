package com.dunk.django.repository;

import com.dunk.django.domain.Notice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    
}