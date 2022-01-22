import java.util.Stack;

import junit.framework.TestCase;

/**
 * OpVerifyTest
 * @author Robert Lewis
 *@version 1.0
 */
public class OpVerifyTest extends TestCase {
    
    /**
     * test 1
     */
    public void testOpVerify1() {
        // verifying stack
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
        AbstractOps op2 = new OpVerify();
        try {
            op2.execute(stack);
        } 
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        
    }
    
    /**
     * test 2
     */
    public void testOpVerify2() {
        // verifying stack
        AbstractOps op1 = new OpVerify();
        Stack<String> stack = new Stack<String>();
        boolean caught = true;

        try {
            op1.execute(stack);
        } 
        catch (VerificationException e) {
            caught = false;
        }
        assertFalse(caught);
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }
    
    /**
     * test 3
     */
    public void testOpVerify3() {
        // verifying stack
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
        
        AbstractOps op2 = new OpPush("0");

        caught = true;

        try {
            op2.execute(stack);
        } 
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
        assertEquals("0", stack.peek());
        
        caught = true;
        AbstractOps op3 = new OpVerify();
        try {
            op3.execute(stack);
        } 
        catch (VerificationException e) {
            caught = false;
        }
        assertFalse(caught);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
    }
    
    /**
     * test 3
     */
    public void testOpVerify4() {
        // verifying stack
        AbstractOps op1 = new OpPush("0");
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
        assertEquals("0", stack.peek());
        
        caught = true;
        AbstractOps op3 = new OpVerify();
        try {
            op3.execute(stack);
        } 
        catch (VerificationException e) {
            caught = false;
        }
        assertFalse(caught);
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }
}
