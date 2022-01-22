import java.util.Stack;

/**
 * OpHash
 * @author Robert Lewis
 * @version 1.0
 */
public class OpHash extends AbstractOps {

    @Override
    public void execute(Stack<String> stack) throws VerificationException {
        String value = stack.pop();
        System.out.println("OpHash Value: " + value);
        String hashCopy = CryptoUtil.hash(value);
        System.out.println("OpHASH hashCopy: " + hashCopy);
        stack.push(hashCopy);
        
    }

}
