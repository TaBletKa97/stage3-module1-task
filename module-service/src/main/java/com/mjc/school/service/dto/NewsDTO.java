package com.mjc.school.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NewsDTO {
    private long newsId;
    private String newsTitle;
    private String newsContent;
    private LocalDateTime newsLastUpdateDate;
    private LocalDateTime newsCreateDate;
    private long authorId;
    private String authorName;
}
