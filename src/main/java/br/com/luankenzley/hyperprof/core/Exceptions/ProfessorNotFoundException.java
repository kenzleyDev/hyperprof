package br.com.luankenzley.hyperprof.core.Exceptions;

public class ProfessorNotFoundException extends ModelNotFoundException{

    public ProfessorNotFoundException() {
        super("Professor não encontrado");
    }

    public ProfessorNotFoundException(String message) {
        super(message);
    }
}
