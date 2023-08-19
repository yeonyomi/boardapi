package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;


    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(Comment comment, Long postId) {
        Comment newComment = commentRepository.save(comment);

        // 게시글 엔티티 조회 및 댓글 수 업데이트
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            post.setCommentsCount(post.getCommentsCount() + 1);
            postRepository.save(post);
        }

        return newComment;
    }

    public Comment updateComment(Long commentId, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(commentId).orElse(null);
        if (existingComment != null) {
            existingComment.setContents(updatedComment.getContents());
            // 나머지 필드 업데이트
            return commentRepository.save(existingComment);
        }
        return null;
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            // 댓글 삭제 전 게시글 엔티티 조회
            Post post = comment.getPost();

            // 댓글 삭제 및 게시글의 댓글 수 업데이트
            commentRepository.delete(comment);
            if (post != null) {
                post.setCommentsCount(post.getCommentsCount() - 1);
                postRepository.save(post);
            }
        }
    }
}

