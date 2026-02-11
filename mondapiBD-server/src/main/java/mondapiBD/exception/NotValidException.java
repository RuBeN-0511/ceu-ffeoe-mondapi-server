package mondapiBD.exception;

public class NotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4240078280961596658L;

	public NotValidException() {
	}

	public NotValidException(String message) {
		super(message);
	}

	public NotValidException(Throwable cause) {
		super(cause);
	}

	public NotValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
