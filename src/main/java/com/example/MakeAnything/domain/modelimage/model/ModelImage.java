package com.example.MakeAnything.domain.modelimage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ModelImage {
    @Id
    @GeneratedValue
    @Column(name = "modelImageId")
    private Long id;

    private String modelImageUrl;
}
