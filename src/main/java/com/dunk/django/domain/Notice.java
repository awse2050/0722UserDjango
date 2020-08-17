package com.dunk.django.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_notice")
public class Notice extends BaseModEntity {

    // PK값 자동생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nno;
    private String title, content, writer;

}