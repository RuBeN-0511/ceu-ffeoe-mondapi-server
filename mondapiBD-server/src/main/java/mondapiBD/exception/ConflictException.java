package mondapiBD.exception;

public class ConflictException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5228257759809261220L;

	public ConflictException() {
	}

	public ConflictException(String message) {
		super(message);
	}

	public ConflictException(Throwable cause) {
		super(cause);
	}

	public ConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
