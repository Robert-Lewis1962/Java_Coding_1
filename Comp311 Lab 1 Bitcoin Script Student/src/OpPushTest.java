import java.util.Stack;

import junit.framework.TestCase;

/**
 * OpPush Tests
 * @author Robert Lewis
 * @version 1.0
 */
public class OpPushTest extends TestCase {

    /**
     * test 1
     */
    public void testPushOps() {

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
        assertEquals("1", stack.peek());
        
        try {
            op1.execute(stack);
        } 
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
        assertEquals("1", stack.peek());
        
    }
}
