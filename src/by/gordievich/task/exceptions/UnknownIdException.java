package by.gordievich.task.exceptions;

public class UnknownIdException extends Exception {
    private int id;
    public UnknownIdException(String message) {
        super(message);
    }
}
