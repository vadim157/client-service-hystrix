package com.trofimets.springcloud.clientservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "gallerydb")
public class Book {
    @Id
    private String id;
    @NotBlank
    @Size(max = 10)
    private String title;
    private String description;
    private String imageLink;
}
