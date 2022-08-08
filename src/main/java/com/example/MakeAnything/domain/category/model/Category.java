package com.example.MakeAnything.domain.category.model;

import com.example.MakeAnything.domain.model.model.Model;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Model> model;

    @Builder
    public Category(String categoryName, List<Model> model) {
        this.categoryName = categoryName;
        this.model = model;
    }
}