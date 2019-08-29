package com.sandro.cursojava.domain.enums;

public enum  TipoCliente {

    PESSOA_FISICA(1, "Pessoa Física"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private int code;
    private String description;

    private TipoCliente(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        return this.code;
    }

    public String getDescription(){
        return this.description;
    }

    public static TipoCliente toEnum(Integer code){
        if(code == null){
            return null;
        }

        for(TipoCliente x : TipoCliente.values()){
            if(code.equals(x.getCode())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + code);
    }
}
