package com.mailplug.homework.board.model;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mailplug.homework.board.dto.BoardRequestDto;
import com.mailplug.homework.board.dto.BoardResponseDto;
import com.mailplug.homework.board.entity.Board;
import com.mailplug.homework.board.entity.BoardRepository;
import com.mailplug.homework.exception.CustomException;
import com.mailplug.homework.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(final BoardRequestDto params) {
        Board entity = boardRepository.save(params.toEntity());
        return entity.getId();
    }

    public Page<BoardResponseDto> findAll(int page, int size, String title) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "id", "createAt"));
        Page<Board> boards;
        if (title == null) {
            boards = boardRepository.findAllByDeleteYn('N', pageable);
        } else {
            boards = boardRepository.findByTitleContainingAndDeleteYn(title, 'N', pageable);
        }
        return boards.map(BoardResponseDto::new);
    }
    @Transactional
    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));

        board.increaseHits();

        return new BoardResponseDto(board);
    }

    @Transactional
    public Long update(final Long id, final BoardRequestDto params) {
        Board entity = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getName(), params.getTitle(), params.getContent(), params.getWriter());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Board entity = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.markAsDeleted();  // 게시글을 삭제표시로 변경
    }

    public boolean isAuthor(Long id, String writerId) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        return board.getWriter().equals(writerId);
    }

    public Page<BoardResponseDto> findAllByBoardName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "id", "createAt"));
        Page<Board> boards = boardRepository.findByNameAndDeleteYn(name, 'N', pageable);  // Fetch non-deleted boards by board name
        return boards.map(BoardResponseDto::new);
    }
}
