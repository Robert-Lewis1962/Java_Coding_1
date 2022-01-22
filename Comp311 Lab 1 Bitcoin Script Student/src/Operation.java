import java.util.Stack;

/**
 * Interface class for Operations.
 *
 * @author Robert Lewis
 * @version 1.0
 */

public interface Operation {
    /**
     * 
     * @param stack for operations
     * @throws VerificationException
     */
    void execute(Stack<String> stack) throws VerificationException;
}
