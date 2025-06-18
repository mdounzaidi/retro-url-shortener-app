package com.mdounzaidi.retro.url.shortner.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Document("UrlList")
@Data
@NoArgsConstructor
public class URLEntry {

    @Id
    ObjectId id;
    @NonNull
    @Indexed(unique = true)
    String originalURL;
    @NonNull
    String shortUrl;
    LocalDate startDate;
    LocalDate endDate;
  }
