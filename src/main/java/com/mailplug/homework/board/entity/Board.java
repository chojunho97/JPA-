package com.mailplug.homework.board.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String title;

    private String content;

    private String writer;

    private int hits;

    private char deleteYn = 'N';

    private LocalDateTime createAt = LocalDateTime.now();

    private LocalDateTime updateAt;

    @Builder
    public Board(String name, String title, String content, String writer, int hits) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hits = hits;
    }

    public void update(String name, String title, String content, String writer) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.updateAt = LocalDateTime.now();
    }

    public void markAsDeleted() {
        this.deleteYn = 'Y';
    }

    public void increaseHits() {
        this.hits++;
    }
}
