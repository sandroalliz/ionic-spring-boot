package com.sandro.cursojava.domain.enums;

import antlr.StringUtils;

public enum  CustomerType {

    PESSOA_FISICA(1, "Pessoa Física"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private int code;
    private String description;

    private CustomerType(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        return this.code;
    }

    public String getDescription(){
        return this.description;
    }

    public static CustomerType toEnum(Integer code){
        if(code == null){
            return null;
        }

        for(CustomerType x : CustomerType.values()){
            if(code.equals(x.getCode())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + code);
    }

    public static CustomerType toEnumByDescription(String description){
        if(description == null || description == ""){
            return null;
        }

        for(CustomerType x : CustomerType.values()){
            if(description.equals(x.getDescription())){
                return x;
            }
        }

        throw new IllegalArgumentException("Descrição inválido: " + description);
    }
}
