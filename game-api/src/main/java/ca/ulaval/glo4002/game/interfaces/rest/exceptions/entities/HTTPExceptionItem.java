package ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities;

public abstract class HTTPExceptionItem extends RuntimeException {
    private final int status;
    private final String code;
    private final String message;

    public HTTPExceptionItem(int status, String code, String message) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

}
