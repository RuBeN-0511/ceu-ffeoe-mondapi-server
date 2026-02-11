package mondapiBD.exception;

public class IncorrectPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9015733365996327636L;

	public IncorrectPasswordException() {
	}

	public IncorrectPasswordException(String message) {
		super(message);
	}

	public IncorrectPasswordException(Throwable cause) {
		super(cause);
	}

	public IncorrectPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
