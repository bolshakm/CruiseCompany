package ua.bolshak.exception;

public class SoldOutException extends Exception {

    public SoldOutException(){}

    public SoldOutException(String massage, Throwable exception){
        super(massage, exception);
    }

    public SoldOutException(String massage){
        super(massage);
    }

    public SoldOutException(Throwable exception){
        super(exception);
    }
}
