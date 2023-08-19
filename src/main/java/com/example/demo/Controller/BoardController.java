package com.example.demo.Controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping
    public ResponseEntity<List<Board>> getAllBoards() {
        List<Board> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long boardId) {
        Board board = boardService.getBoardById(boardId);
        if (board != null) {
            return ResponseEntity.ok(board);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        Board createdBoard = boardService.createBoard(board);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long boardId, @RequestBody Board updatedBoard) {
        Board board = boardService.updateBoard(boardId, updatedBoard);
        if (board != null) {
            return ResponseEntity.ok(board);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: " + ex.getMessage());
    }
}

