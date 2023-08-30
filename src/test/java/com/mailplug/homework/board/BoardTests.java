package com.mailplug.homework.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mailplug.homework.board.entity.Board;
import com.mailplug.homework.board.entity.BoardRepository;

@SpringBootTest
public class BoardTests {

    @Autowired
    BoardRepository boardRepository;

//    @Test
//    void save() {
//
//        Board params = Board.builder()
//                .title("게시글 제목")
//                .content("게시글 내용")
//                .writer("작성자")
//                .hits(0)
//                .build();
//
//        boardRepository.save(params);
//
//        Board entity = boardRepository.findById((long) 1).get();
//        assertThat(entity.getTitle()).isEqualTo("게시글 제목");
//        assertThat(entity.getContent()).isEqualTo("게시글 내용");
//        assertThat(entity.getWriter()).isEqualTo("작성자");
//    }

//    @Test
//    void findAll() {
//
//        long boardsCount = boardRepository.count();
//
//        // 전체 게시글 리스트 조회
//        List<Board> boards = boardRepository.findAll();
//    }

//    @Test
//    void delete() {
//
//        Board entity = boardRepository.findById((long) 1).get();
//
//        // 게시글 삭제
//        boardRepository.delete(entity);
//    }

}