package com.example.MakeAnything.domain.modelfile.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class ModelFile {
    @Id
    @GeneratedValue
    @Column(name = "modelFileId")
    private Long id;

    private String modelFileUrl;
}
