package mat.CircuitBraker.demo.exceptions;

/**
 * The type Already exists entity exception.
 */
public class ServiceDownException extends RuntimeException {
  /**
   * Instantiates a new Already exists entity exception.
   *
   * @param msg the msg
   */
  public ServiceDownException(String msg){
        super(msg);
    }

    private ServiceDownException(){}
}
