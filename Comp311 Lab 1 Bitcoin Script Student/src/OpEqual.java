import java.util.Stack;

/**
 * OpEqual 
 * @author Robert Lewis
 * @version 1.0
 */
public class OpEqual extends AbstractOps {

    @Override
    public void execute(Stack<String> stack) throws VerificationException {
        String value1 = stack.pop();
        String value2 = stack.pop();
        
        System.out.println("OpEqual Vaue1: " + value1);
        System.out.println("OpEqual Vaue2: " + value2);
        if (value1.equals(value2)) {
            stack.push("1");
        }
        else {
            stack.push("0");
        }
        
    }

}
