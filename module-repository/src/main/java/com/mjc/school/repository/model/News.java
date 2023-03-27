package com.mjc.school.repository.model;

import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class News extends Entity<Long> {
    private static long idCounter = 1;

    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Author author;

    public News(String title, String content, Author author) {
        setId(idCounter++);
        this.title = title;
        this.content = content;
        this.author = author;
        createDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        lastUpdateDate = createDate;
    }

    @Override
    public String toString() {
        return "News{" +
                "id =" + getId() +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", author=" + author +
                '}';
    }
}
