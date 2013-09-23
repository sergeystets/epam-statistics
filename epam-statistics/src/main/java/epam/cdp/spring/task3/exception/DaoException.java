package epam.cdp.spring.task3.exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = 6227797653076730170L;

	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}
