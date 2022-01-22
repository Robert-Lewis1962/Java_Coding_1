import java.util.Stack;

/**
 * SubClass for Operations.
 *
 * @author Robert Lewis
 * @version 1.0
 */
public class OpPush extends AbstractOps {
    private String value;
    
    /**
     * takes value to push onto stack
     * @param arg the value
     */
    public OpPush(String arg) {
        this.value = arg;
    }
    
    @Override
    public void execute(Stack<String> stack) throws VerificationException {
        System.out.println("OpPush value: " + value);
        stack.push(this.value);
    }
}
