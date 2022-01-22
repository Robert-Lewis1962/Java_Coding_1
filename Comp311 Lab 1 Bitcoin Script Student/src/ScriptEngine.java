
import java.util.Stack;

/**
 * ScriptEngine
 * 
 * @author Robert Lewis
 * @version 1.0
 */
public class ScriptEngine {
    private Stack<String> bitStack = new Stack<String>();
    private String data;

    /**
     * data for the signature
     * 
     * @param transactionHash the data for the signature
     */
    public ScriptEngine(String transactionHash) {
        this.data = transactionHash;
    }

    /**
     * gets the stack
     * 
     * @return the stack
     */
    public Stack<String> getStack() {
        return this.bitStack;
    }

    /**
     * Executes the script
     * 
     * @param script the script
     */
    public void rawExecute(String script) throws VerificationException {
        String command;
        String value;
        String[] line = script.split("\n");
        for (int i = 0; i < line.length; i++) {
            System.out.println(i);
            String factor = line[i];
            String[] operations = factor.split(" ");

            if (operations.length == 2) {
                command = operations[0];
                value = operations[1];

                if (command.equals("OP_PUSH")) {
                    OpPush push = new OpPush(value);
                    push.execute(this.bitStack);
                }
            } 
            else {
                command = operations[0];

                if (command.equals("OP_EQUAL")) {
                    OpEqual isEqual = new OpEqual();
                    isEqual.execute(this.bitStack);
                } 
                else if (command.equals("OP_DUP")) {
                    OpDup copy = new OpDup();
                    copy.execute(this.bitStack);
                } 
                else if (command.equals("OP_HASH")) {
                    OpHash hashVersion = new OpHash();
                    hashVersion.execute(this.bitStack);
                } 
                else if (command.equals("OP_VERIFY")) {
                    OpVerify checkValue = new OpVerify();
                    if (this.bitStack.peek().equals("0")) {
                        i = line.length - 1;
                    } 
                    checkValue.execute(this.bitStack);
                } 
                else if (command.equals("OP_CHECKSIG")) {
                    OpCheckSig sig = new OpCheckSig(this.data);
                    sig.execute(this.bitStack);
                }
                else if (command.equals("OP_CHECKMULTISIG")) {
                    OpCheckMultiSig sig = new OpCheckMultiSig(this.data);
                    System.out.println("JOJO!!!");
                    sig.execute(this.bitStack);
                }
            }

        }
    }

    /**
     * Executes the script
     * 
     * @param script the script
     * @return true or false
     */
    public boolean execute(String script) {
        try {
            this.rawExecute(script);
        } 
        catch (VerificationException e) {
            return false;
        }
        return !this.bitStack.peek().equals("0");
    }
}
