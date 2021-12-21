package com.luisbsb.quarkuscrud.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(enumeration = {"COMEDIA", "TERROR"})
public enum Type {
    
    COMEDIA("COMEDIA"),
    TERROR("TERROR");

    String descricao;

    Type(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

}
