import java.security.PublicKey;
import java.util.Stack;

/**
 * OP_CheckSig
 * @author Robert Lewis
 * @version 1.0
 *
 */
public class OpCheckSig extends AbstractOps {
    private String data;

    /**
     * OP_CheckSig
     * 
     *@param tranactionHash data for signature
     */
    public OpCheckSig(String tranactionHash) {
        this.data = tranactionHash;
    }

    @Override
    public void execute(Stack<String> stack) throws VerificationException {
        
        String pubKey = stack.pop();
        System.out.println("OpChek pubKey: " + pubKey);
        String sig = stack.pop();
        System.out.println("OpChek sig: " + sig);
        PublicKey key = CryptoUtil.publicKeyFromString(pubKey);

        if (CryptoUtil.checkSignature(this.data, sig, key)) {
            stack.push("1");
        } 
        else {
            stack.push("0");
        }
    }
}
