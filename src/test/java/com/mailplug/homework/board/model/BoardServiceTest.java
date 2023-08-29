package com.mailplug.homework.board.model;

import com.mailplug.homework.board.dto.BoardRequestDto;
import com.mailplug.homework.board.dto.BoardResponseDto;
import com.mailplug.homework.board.entity.Board;
import com.mailplug.homework.board.entity.BoardRepository;
import com.mailplug.homework.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;


//    @Test
//    public void testFindByIdNotFound() {
//        when(boardRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(CustomException.class, () -> boardService.findById(1L));
//    }

}
