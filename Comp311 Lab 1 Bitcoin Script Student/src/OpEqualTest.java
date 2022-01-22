import java.util.Stack;
import junit.framework.TestCase;

/**
 * OpEqual Tests
 * @author Robert Lewis
 * @version 1.0
 */
public class OpEqualTest extends TestCase {

    /**
     * test 1
     */
    public void testEqualsOps1() {
        //Push 2 on stack and see they are equal or not
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
        
        // Checking to see if the two values are equal
        AbstractOps op2 = new OpEqual();
        try {
            op2.execute(stack);
        } 
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals("1", stack.peek());
    }
    
    /**
     * test 2
     */
    public void testEqualsOps2() {
        //Push 2 on stack and see they are equal or not
        AbstractOps op1 = new OpPush("2");
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
        assertEquals("2", stack.peek());

        op1 = new OpPush("1");
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
        
        // Checking to see if the two values are equal
        AbstractOps op2 = new OpEqual();
        try {
            op2.execute(stack);
        } 
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals("0", stack.peek());
    }
}
