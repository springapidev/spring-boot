package com.coderbd.coderbdblog.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "blog_tag", uniqueConstraints = {@UniqueConstraint(columnNames="tag_name")})
@EqualsAndHashCode(of = "id")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "tag_name")
    private String tagName;

    public Long getId() {
        return id;
    }


    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
