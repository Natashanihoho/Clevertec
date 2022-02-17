package by.gordievich.task.service;

import by.gordievich.task.exceptions.UnknownIdException;

public interface Interpreter {

    String interpret(String[] args) throws UnknownIdException;
}
