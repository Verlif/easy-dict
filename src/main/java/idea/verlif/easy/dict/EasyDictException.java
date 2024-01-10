package idea.verlif.easy.dict;

public class EasyDictException extends RuntimeException {
    public EasyDictException() {
    }

    public EasyDictException(String message) {
        super(message);
    }

    public EasyDictException(String message, Throwable cause) {
        super(message, cause);
    }

    public EasyDictException(Throwable cause) {
        super(cause);
    }

    public EasyDictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
