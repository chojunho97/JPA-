package com.mailplug.homework.board.dto;

import com.mailplug.homework.board.entity.Board;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    @Size(max = 100, message = "Title should not be longer than 100 characters.")
    private String title;
    private String name;
    private String content;
    private String writer;

    public Board toEntity() {
        return Board.builder()
                .name(name)
                .title(title)
                .content(content)
                .writer(writer)
                .hits(0)
                .build();
    }
}

