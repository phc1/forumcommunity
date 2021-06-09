package com.prespringboot.community.forum.dto;

import com.prespringboot.community.forum.model.User;
import lombok.Data;

@Data
public class CommentCreateDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private String content;
    private User user;
}
