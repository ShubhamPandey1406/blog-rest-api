package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    //to retrive the post entity this has been injected
    private PostRepository postRepository;
    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository=postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment=mapToEntiity(commentDto);

        //retrieve post entity by id

        Post post=postRepository.findById(postId).orElseThrow( ()-> new ResourceNotFoundException("Post","id",postId));

        // set post to comment entity "As we are maintaing postid in post tabble"
        comment.setPost(post);

   //save comment entity to DB

      Comment newComment=  commentRepository.save(comment);


        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        //retrieve comments by postId

        List<Comment> comments=commentRepository.findByPostId(postId);



       //Convert the list of comment entities to list of comment dtos
        return comments.stream().map(comment -> mapToDto(comment)).toList();
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {

        Post post=postRepository.findById(postId).orElseThrow( ()-> new ResourceNotFoundException("Post","id",postId));

        //retrieve comment by id

        Comment comment=commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment","id",commentId)
        );


        if(comment.getPost().getId()!= post.getId()){
           new ResourceNotFoundException(HttpStatus.BAD_REQUEST,"Comment does not belong to post",0);


        }


        return mapToDto(comment);
    }

    private CommentDto mapToDto(Comment comment)
    {
        CommentDto commentDto= new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;




    }


    private Comment mapToEntiity(CommentDto commentDto)
    {
         Comment comment= new Comment();
         comment.setId(commentDto.getId());
         comment.setName(commentDto.getName());
         comment.setEmail(commentDto.getEmail());
         comment.setBody(commentDto.getBody());

         return comment;
    }


}
