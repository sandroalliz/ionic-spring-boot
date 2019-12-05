package com.sandro.cursojava.domain.enums;

import antlr.StringUtils;

public enum  Profile {

    ADMIN(1, "ROLE_ADMIN"),
    CUSTOMER(2, "ROLE_CUSTOMER");

    private int code;
    private String description;

    private Profile(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        return this.code;
    }

    public String getDescription(){
        return this.description;
    }

    public static Profile toEnum(Integer code){
        if(code == null){
            return null;
        }

        for(Profile x : Profile.values()){
            if(code.equals(x.getCode())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + code);
    }

    public static Profile toEnumByDescription(String description){
        if(description == null || description == ""){
            return null;
        }

        for(Profile x : Profile.values()){
            if(description.equals(x.getDescription())){
                return x;
            }
        }

        throw new IllegalArgumentException("Descrição inválido: " + description);
    }
}
