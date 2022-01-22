import java.util.Stack;

/**
 * OpDup
 * @author Robert Lewis
 * @version 1.0
 */
public class OpDup extends AbstractOps {

    @Override
    public void execute(Stack<String> stack) throws VerificationException {
        String value = stack.pop();
        String copy = value;
        System.out.println("OpDup Value: " + value);
        System.out.println("OpDup Copy: " + copy);
        stack.push(value);
        stack.push(copy);
        
    }

}
