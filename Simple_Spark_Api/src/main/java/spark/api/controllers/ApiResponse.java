package spark.api.controllers;

public class ApiResponse {
	private final boolean success;
	private final String message;
	private final Object data;

	public ApiResponse(boolean success) {
		this(success, null, null);
	}

	public ApiResponse(boolean success, String message) {
		this(success, message, null);
	}

	public ApiResponse(boolean success, Object data) {
		this(success, null, data);
	}

	public ApiResponse(boolean success, String message, Object data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
}
