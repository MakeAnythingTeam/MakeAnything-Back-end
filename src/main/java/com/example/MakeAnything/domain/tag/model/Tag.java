package com.example.MakeAnything.domain.tag.model;

import com.example.MakeAnything.domain.modeltag.model.ModelTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagName;

    @OneToMany(mappedBy = "tag")
    private List<ModelTag> modelTag;

    @Builder
    public Tag(String tagName) {
        this.tagName = tagName;
    }
}