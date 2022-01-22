import java.util.Stack;

import junit.framework.TestCase;

/**
 * OpDup Tests
 * @author Robert Lewis
 * @version 1.0
 */
public class OpDupTest extends TestCase {

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
        AbstractOps op2 = new OpDup();
        try {
            op2.execute(stack);
        } 
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
        assertEquals("1", stack.peek());
        stack.pop();
        assertEquals(1, stack.size());
        assertEquals("1", stack.peek());
        
    }
}
