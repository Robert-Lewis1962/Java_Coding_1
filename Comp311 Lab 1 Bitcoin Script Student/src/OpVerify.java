import java.util.Stack;

/**
 * OpVerify
 * @author Robert Lewis
 * @version 1.0
 */
public class OpVerify extends AbstractOps {

    @Override
    public void execute(Stack<String> stack) throws VerificationException {
        if (stack.isEmpty()) {
            throw new VerificationException();
        }
        String value = stack.pop();
        System.out.println("OpVerify: " + value);
        if (value.equals("0")) {
            throw new VerificationException();
        } 

    }

}
