package mondapiBD.exception;

public class InactiveUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7359707088853766922L;

	public InactiveUserException() {
	}

	public InactiveUserException(String message) {
		super(message);
	}

	public InactiveUserException(Throwable cause) {
		super(cause);
	}

	public InactiveUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public InactiveUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
