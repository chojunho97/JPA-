package com.mailplug.homework.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailplug.homework.board.dto.BoardRequestDto;
import com.mailplug.homework.board.model.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
    }

//    @Test
//    public void testSave() throws Exception {
//        BoardRequestDto dto = new BoardRequestDto();
//        dto.setTitle("Test Title");
//        dto.setContent("Test Content");
//        dto.setName("Test Name");
//
//        when(boardService.save(any())).thenReturn(1L);
//
//        mockMvc.perform(post("/api/boards")
//                        .header("X-USERID", "testUser")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(dto)))
//                .andExpect(status().isOk());
//
//        verify(boardService, times(1)).save(any());
//    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/boards")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());

        verify(boardService, times(1)).findAll(0, 10, null);
    }

    // Add more tests as needed
}
