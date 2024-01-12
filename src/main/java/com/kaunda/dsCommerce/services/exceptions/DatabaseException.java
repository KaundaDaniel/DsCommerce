package com.kaunda.dsCommerce.services.exceptions;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String mensagem){
        super(mensagem);
    }
}
