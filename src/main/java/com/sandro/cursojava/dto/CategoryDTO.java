package com.sandro.cursojava.dto;

import com.sandro.cursojava.domain.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public CategoryDTO(){

    }

    public CategoryDTO(Category domain){
        this.id = domain.getId();
        this.name = domain.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
