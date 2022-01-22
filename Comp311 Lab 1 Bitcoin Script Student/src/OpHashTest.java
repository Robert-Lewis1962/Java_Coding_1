import java.util.Stack;

import junit.framework.TestCase;

/**
 * OpHash Tests
 * @author Robert Lewis
 * @version 1.0
 */
public class OpHashTest extends TestCase {

    /**
     * test 1
     */
    public void testOpDub1() {
        // making a copy
        AbstractOps op1 = new OpPush("1");
        Stack<String> stack = new Stack<String>();
        boolean caught = true;

        try {
            op1.execute(stack);
        } 
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals("1", stack.peek());
        
        caught = true;
        AbstractOps op2 = new OpHash();
        try {
            op2.execute(stack);
        } 
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals("6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49"
                + "c01e52ddb7875b4b", stack.peek());
        
    }
}
