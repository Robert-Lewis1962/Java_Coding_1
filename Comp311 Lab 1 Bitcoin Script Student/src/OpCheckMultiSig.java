import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Stack;

/**
 * OpCheckMultiSig checks multiple keys with signatures
 *
 * @author Robert Lewis
 * @version 1.0
 */
public class OpCheckMultiSig extends AbstractOps {
    private String data;

    /**
     * OpCheckMultiSig
     * 
     * @param tranactionHash data for signature
     */
    public OpCheckMultiSig(String tranactionHash) {
        this.data = tranactionHash;
    }

    @Override
    public void execute(Stack<String> stack) throws VerificationException {
        ArrayList<PublicKey> hashKey = new ArrayList<PublicKey>();
        ArrayList<String> hashSig = new ArrayList<String>();
        int i = 0;
        int j = 0;
        
        String num = stack.pop();
        System.out.println(num);
        int numOfKeys = Integer.parseInt(num);
        for (i = 0; i < numOfKeys; i++) {
            String pubKey = stack.pop();
            System.out.println("OpChekMutliSig pubKey: " + pubKey);
            PublicKey key = CryptoUtil.publicKeyFromString(pubKey);
            hashKey.add(key);
        }

        num = stack.pop();
        System.out.println(num);
        int numOfSigs = Integer.parseInt(num);
        for (i = 0; i < numOfSigs; i++) {
            String pubSig = stack.pop();
            System.out.println("OpChekMutliSig pubSig: " + pubSig);
            hashSig.add(pubSig);
        }

        for (i = 0, j = 0; i < hashKey.size(); i++) {
            if (j < hashSig.size()) {
                System.out.println("Yesss");
                if (CryptoUtil.checkSignature(this.data, hashSig.get(j), 
                        hashKey.get(i))) {
                    j++;
                }
            }
            if (j == hashSig.size()) {
                System.out.println("check");
                stack.push("1");
            }
        }
        
        if (j != hashSig.size()) {
            stack.push("0");
        }
    }

}
