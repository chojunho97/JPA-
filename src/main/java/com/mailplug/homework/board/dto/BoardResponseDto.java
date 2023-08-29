package com.mailplug.homework.board.dto;

import java.time.LocalDateTime;

import com.mailplug.homework.board.entity.Board;

import lombok.Getter;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private int hits;
    private char deleteYn;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.hits = entity.getHits();
        this.deleteYn = entity.getDeleteYn();
        this.createAt = entity.getCreateAt();
        this.updateAt = entity.getUpdateAt();
    }
}