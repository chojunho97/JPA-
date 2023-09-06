package com.mailplug.homework.board.controller;

import com.mailplug.homework.exception.CustomException;
import com.mailplug.homework.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mailplug.homework.board.dto.BoardRequestDto;
import com.mailplug.homework.board.dto.BoardResponseDto;
import com.mailplug.homework.board.model.BoardService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<Long> save(@RequestBody BoardRequestDto params,
                                     @RequestHeader("X-USERID") String userId) {
        params.setWriter(userId);
        Long id = boardService.save(params);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/boards")
    public ResponseEntity<Page<BoardResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title) {
        Page<BoardResponseDto> boards = boardService.findAll(page, size, title);
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponseDto> findById(@PathVariable Long id) {
        BoardResponseDto board = boardService.findById(id);  // 삭제 여부 및 존재 여부 확인은 Service 내에서 처리
        return ResponseEntity.ok(board);
    }

    @PatchMapping("/boards/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id,
                                       @RequestBody BoardRequestDto params,
                                       @RequestHeader("X-USERID") String userId) {
        if (!boardService.isAuthor(id, userId)) {
            throw new CustomException(ErrorCode.FORBIDDEN);
        }
        Long updatedId = boardService.update(id, params);  // 삭제 여부 및 존재 여부 확인은 Service 내에서 처리
        return ResponseEntity.ok(updatedId);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @RequestHeader("X-USERID") String userId) {
        if (!boardService.isAuthor(id, userId)) {
            throw new CustomException(ErrorCode.FORBIDDEN);
        }
        boardService.delete(id);  // 삭제 여부 및 존재 여부 확인은 Service 내에서 처리
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/boards/type/{boardName}")
    public ResponseEntity<Page<BoardResponseDto>> findAllByBoardName(
            @PathVariable String boardName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<BoardResponseDto> boards = boardService.findAllByBoardName(boardName, page, size);
        return ResponseEntity.ok(boards);
    }
}
