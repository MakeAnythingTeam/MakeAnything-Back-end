package com.example.MakeAnything.domain.category.model;

import com.example.MakeAnything.domain.model.model.Model;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Getter
public enum Category {

    ALL("전체"),
    ETC("기타"),
    ARCHITECTURE("건축물"),
    ROBOT("로봇");

    private String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    @OneToMany(mappedBy = "category")
    private List<Model> model;

    public static Category fromCode(String dbData) {
        return Arrays.stream(Category.values())
                .filter(v -> v.getCategoryName().equals(dbData))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("아이템 카테고리에 %s가 존재하지 않습니다.", dbData)));
    }
}