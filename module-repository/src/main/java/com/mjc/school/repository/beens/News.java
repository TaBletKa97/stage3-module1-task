package com.mjc.school.repository.beens;

import com.mjc.school.repository.util.NameValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Getter
@EqualsAndHashCode
public class News {
    private static long idCounter = 1;

    private final LocalDateTime createDate = LocalDateTime.now();
    private final long id;
    private String title;
    private String content;
    @Setter
    private LocalDateTime lastUpdateDate;
    @Setter
    private Author author;

    public News(String title, String content, Author author) {
        id = idCounter++;
        setTitle(title);
        setContent(content);
        this.lastUpdateDate = LocalDateTime.now();
        this.author = author;
    }

    public void setTitle(String title) {
        int minLength = 5;
        int maxLength = 30;
        NameValidator.validateLength(title, minLength,
                maxLength, "Title");
        this.title = title;
    }

    public void setContent(String content) {
        int minLength = 5;
        int maxLength = 255;
        NameValidator.validateLength(content, minLength,
                maxLength, "Content");
        this.content = content;
    }

    @Override
    public String toString() {
        Instant instantCreateDate = createDate.truncatedTo(ChronoUnit.SECONDS)
                .atZone(ZoneId.systemDefault()).toInstant();
        Instant instantLastUpdateDate = lastUpdateDate
                .truncatedTo(ChronoUnit.SECONDS).atZone(ZoneId.systemDefault())
                .toInstant();
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + instantCreateDate +
                ", lastUpdateDate=" + instantLastUpdateDate +
                ", author=" + author +
                '}';
    }
}
