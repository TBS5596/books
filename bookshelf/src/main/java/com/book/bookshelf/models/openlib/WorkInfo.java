package com.book.bookshelf.models.openlib;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Component
public class WorkInfo {
    private List<String> subjects;
    private String key;
    private String title;
    private String description;
    private List<Author> authors;
    private Type type;
    private List<Integer> covers;
    @SerializedName("subject_people")
    private List<String> subjectPeople;
    @SerializedName("subject_times")
    private List<String> subjectTimes;
    @SerializedName("latest_revision")
    private int latestRevision;
    private int revision;
    private Created created;
    @SerializedName("last_modified")
    private LastModified lastModified;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Author {
        @SerializedName("author")
        private WorkAuthor workAuthor;
        @SerializedName("type")
        private AuthorType authorType;

        @Override
        public String toString() {
            return workAuthor.getKey();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class WorkAuthor {
        private String key;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class AuthorType {
        private String key;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Type {
        private String key;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Created {
        @SerializedName("type")
        private String createdType;
        @SerializedName("value")
        private String createdValue;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class LastModified {
        @SerializedName("type")
        private String lastModifiedType;
        @SerializedName("value")
        private String lastModifiedValue;
    }
}