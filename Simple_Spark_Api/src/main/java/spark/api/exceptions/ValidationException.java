package spark.api.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {

  private final List<String> errors;

  public ValidationException(List<String> errors) {
    super("Validation failed with errors");
    this.errors = errors;
  }

  public ValidationException(List<String> errors, Throwable cause) {
    super("Validation failed with errors", cause);
    this.errors = errors;
  }

  public List<String> getErrors() {
    return errors;
  }
}
