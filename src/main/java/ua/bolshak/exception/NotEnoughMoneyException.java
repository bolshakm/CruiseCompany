package ua.bolshak.exception;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException(){}

    public NotEnoughMoneyException(String massage, Throwable exception){
        super(massage, exception);
    }

    public NotEnoughMoneyException(String massage){
        super(massage);
    }

    public NotEnoughMoneyException(Throwable exception){
        super(exception);
    }

}
