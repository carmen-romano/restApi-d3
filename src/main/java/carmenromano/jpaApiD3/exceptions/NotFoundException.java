package carmenromano.jpaApiD3.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("Record con id " + id + " non trovato!");
    }
}
