package com.example.MakeAnything.domain.modeltag.model;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.tag.model.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ModelTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelId")
    private Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tagId")
    private Tag tag;

    @Builder
    public ModelTag(Model model, Tag tag) {
        this.model = model;
        this.tag = tag;
    }
}