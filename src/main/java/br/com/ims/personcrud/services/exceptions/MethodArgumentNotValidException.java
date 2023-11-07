package br.com.ims.personcrud.services.exceptions;

public class MethodArgumentNotValidException extends RuntimeException {

    public MethodArgumentNotValidException(String msg){
        super(msg);
    }
}
