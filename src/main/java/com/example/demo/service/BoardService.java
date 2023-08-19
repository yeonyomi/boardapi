package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board updateBoard(Long boardId, Board updatedBoard) {
        Board existingBoard = boardRepository.findById(boardId).orElse(null);
        if (existingBoard != null) {
            existingBoard.setDisplayName(updatedBoard.getDisplayName());
            existingBoard.setBoardType(updatedBoard.getBoardType());
            existingBoard.setFavorite(updatedBoard.isFavorite());
            existingBoard.setOrderNo(updatedBoard.getOrderNo());
            return boardRepository.save(existingBoard);
        }
        return null;
    }

    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
