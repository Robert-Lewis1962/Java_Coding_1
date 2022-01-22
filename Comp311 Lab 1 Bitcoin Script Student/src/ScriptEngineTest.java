import java.security.KeyPair;

import junit.framework.TestCase;

/**
 * Tests for ScriptEngine
 * @author Robert Lewis
 * @version 1.0
 *
 */
public class ScriptEngineTest extends TestCase {

    /**
     * Script test 1
     */
    public void testScript1() {
        ScriptEngine script = new ScriptEngine("1");
        assertTrue(script.getStack().isEmpty());
        // testing if script passes or not
        String line = "OP_PUSH 1";
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(1, script.getStack().size());
        assertEquals("1", script.getStack().peek());
        assertTrue(script.execute(line));
    }
    
    /**
     * Script test 2
     */
    public void testScript2() {
        ScriptEngine script = new ScriptEngine("1");
        assertTrue(script.getStack().isEmpty());
        // testing if script passes or not
        String line = "OP_PUSH 0";
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(1, script.getStack().size());
        assertEquals("0", script.getStack().peek());
        assertFalse(script.execute(line));
    }
    
    /**
     * Script test 3
     */
    public void testScript3() {
        ScriptEngine script = new ScriptEngine("1");
        assertTrue(script.getStack().isEmpty());
        // testing if script passes or not
        String line = "OP_PUSH 0\n"
                + "OP_PUSH 1";
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(2, script.getStack().size());
        assertEquals("1", script.getStack().peek());
        assertTrue(script.execute(line));
    }
    
    /**
     * Script test 4
     */
    public void testScript4() {
        ScriptEngine script = new ScriptEngine("1");
        assertTrue(script.getStack().isEmpty());
        // testing if script passes or not
        String line = "OP_PUSH 0\n"
                + "OP_PUSH 1\n"
                + "OP_EQUAL";
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(1, script.getStack().size());
        assertEquals("0", script.getStack().peek());
        assertFalse(script.execute(line));
    }
    
    /**
     * Script test 5
     */
    public void testScript5() {
        ScriptEngine script = new ScriptEngine("1");
        assertTrue(script.getStack().isEmpty());
        // testing if script passes or not
        String line = "OP_PUSH 0\n"
                + "OP_PUSH 1\n"
                + "OP_DUP";
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(3, script.getStack().size());
        assertEquals("1", script.getStack().peek());
        assertTrue(script.execute(line));
    }
    
    /**
     * Script test 6
     */
    public void testScript6() {
        ScriptEngine script = new ScriptEngine("1");
        assertTrue(script.getStack().isEmpty());
        // testing if script passes or not
        String line = "OP_PUSH 0\n"
                + "OP_PUSH 1\n"
                + "OP_HASH";
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(2, script.getStack().size());
        assertEquals("6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49"
                + "c01e52ddb7875b4b", script.getStack().peek());
        assertTrue(script.execute(line));
    }
    
    /**
     * Script test 7
     */
    public void testScript7() {
        ScriptEngine script = new ScriptEngine("1");
        assertTrue(script.getStack().isEmpty());
        // testing if script passes or not
        String line = "OP_PUSH 0\n"
                + "OP_VERIFY\n"
                + "OP_PUSH 1";
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertFalse(caught);
        assertTrue(script.getStack().isEmpty());
        assertEquals(0, script.getStack().size());
        assertFalse(script.execute(line));
    }
    
    /**
     * Script test 8
     */
    public void testScript8() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        System.out.println("where" + data);
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
        //CrpytoUtil Area
        // create key pairs
        KeyPair pair = CryptoUtil.generateKeyPair();
        // create signature 
        String sig = CryptoUtil.signData(data, pair.getPrivate());
        //converts public key to string
        String pkString = CryptoUtil.stringFromPublicKey(pair.getPublic());
        
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig, pair.getPublic()));
        
        // testing if script passes or not
        String line = "OP_PUSH " + sig + "\n"
                + "OP_PUSH " + pkString + "\n"
                + "OP_CHECKSIG";
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(1, script.getStack().size());
        assertEquals("1", script.getStack().peek());
        
    }
    
    
    /**
     * Script test 9
     */
    public void testScript9() {
        String data = CryptoUtil.stringFromBytes("Timmy".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
        //CrpytoUtil Area
        // create key pairs
        KeyPair pair = CryptoUtil.generateKeyPair();  
        // create signature 
        String sig = CryptoUtil.signData(data, pair.getPrivate());
        //converts public key to string
        String pkString = CryptoUtil.stringFromPublicKey(pair.getPublic());
        //convert stringKey to hash value
        String hashCopy = CryptoUtil.hash(pkString);
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig, pair.getPublic()));
        
        // testing if script passes or not
        String line = "OP_PUSH " + sig + "\n"
                + "OP_PUSH " + pkString + "\n"
                + "OP_DUP\n" 
                + "OP_HASH\n"
                + "OP_PUSH " + hashCopy + "\n"
                + "OP_EQUAL\n"
                + "OP_VERIFY\n"
                + "OP_CHECKSIG";

        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(1, script.getStack().size());
        assertEquals("1", script.getStack().peek());
        
    }
   
    /**
     * Script test 10
     */
    public void testScript10() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
        //CrpytoUtil Area
        // create key pairs
        KeyPair pair = CryptoUtil.generateKeyPair();
        KeyPair pair2 = CryptoUtil.generateKeyPair(); 
        // create signature 
        String sig = CryptoUtil.signData(data, pair2.getPrivate());
        //converts public key to string
        String pkString = CryptoUtil.stringFromPublicKey(pair.getPublic());
        //convert stringKey to hash value
        String hashCopy = CryptoUtil.hash(pkString);
        //Verify the signature with the public key
        assertFalse(CryptoUtil.checkSignature(data, sig, pair.getPublic()));
        
        // testing if script passes or not
        String line = "OP_PUSH " + sig + "\n"
                + "OP_PUSH " + pkString + "\n"
                + "OP_DUP\n" 
                + "OP_HASH\n"
                + "OP_PUSH " + hashCopy + "\n"
                + "OP_EQUAL\n"
                + "OP_VERIFY\n"
                + "OP_CHECKSIG";
        
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(1, script.getStack().size());
        assertEquals("0", script.getStack().peek());
        assertFalse(script.execute(line));
    }
    
    /**
     * Script test 11
     */
    public void testScript11() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
      //CrpytoUtil Area
        // create key pairs
        KeyPair pair1 = CryptoUtil.generateKeyPair();  
        // create signature 
        String sig1 = CryptoUtil.signData(data, pair1.getPrivate());
        //converts public key to string
        String pkString1 = CryptoUtil.stringFromPublicKey(pair1.getPublic());
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig1, pair1.getPublic()));
        
     // testing if script passes or not
        String line = "OP_PUSH " + sig1 + "\n"
                + "OP_PUSH " + "1" + "\n"
                + "OP_PUSH " + pkString1 + "\n"
                + "OP_PUSH " + "1" + "\n"
                + "OP_CHECKMULTISIG";
        
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(1, script.getStack().size());
        assertEquals("1", script.getStack().peek());
        assertTrue(script.execute(line));
    }
    
    /**
     * Script test 12
     */
    public void testScript12() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
      //CrpytoUtil Area
        // create key pairs
        KeyPair pair1 = CryptoUtil.generateKeyPair();
        KeyPair pair2 = CryptoUtil.generateKeyPair(); 
        // create signature 
        String sig1 = CryptoUtil.signData(data, pair1.getPrivate());
        String sig2 = CryptoUtil.signData(data, pair2.getPrivate());
        //converts public key to string
        String pkString1 = CryptoUtil.stringFromPublicKey(pair1.getPublic());
        String pkString2 = CryptoUtil.stringFromPublicKey(pair2.getPublic());
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig1, pair1.getPublic()));
        assertTrue(CryptoUtil.checkSignature(data, sig2, pair2.getPublic()));
        
     // testing if script passes or not
        String line = "OP_PUSH " + sig2 + "\n"
                + "OP_PUSH " + sig1 + "\n"
                + "OP_PUSH " + "2" + "\n"
                + "OP_PUSH " + pkString2 + "\n"
                + "OP_PUSH " + pkString1 + "\n"
                + "OP_PUSH " + "2" + "\n"
                + "OP_CHECKMULTISIG";
        
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(1, script.getStack().size());
        assertEquals("1", script.getStack().peek());
        assertTrue(script.execute(line));
    }
    
    /**
     * Script test 13
     */
    public void testScript13() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
      //CrpytoUtil Area
        // create key pairs
        KeyPair pair1 = CryptoUtil.generateKeyPair();
        KeyPair pair2 = CryptoUtil.generateKeyPair();
        // create signature 
        String sig1 = CryptoUtil.signData(data, pair1.getPrivate());
        String sig2 = CryptoUtil.signData(data, pair2.getPrivate());
        //converts public key to string
        String pkString1 = CryptoUtil.stringFromPublicKey(pair1.getPublic());
        String pkString2 = CryptoUtil.stringFromPublicKey(pair2.getPublic());
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig1, pair1.getPublic()));
        assertTrue(CryptoUtil.checkSignature(data, sig2, pair2.getPublic()));
        
     // testing if script passes or not
        String line = "OP_PUSH " + sig2 + "\n"
                + "OP_PUSH " + sig1 + "\n"
                + "OP_PUSH " + "2" + "\n"
                + "OP_PUSH " + pkString2 + "\n"
                + "OP_PUSH " + pkString1 + "\n"
                + "OP_PUSH " + "2" + "\n"
                + "OP_PUSH " + "1" + "\n"
                + "OP_VERIFY" + "\n"
                + "OP_CHECKMULTISIG";
        
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(1, script.getStack().size());
        assertEquals("1", script.getStack().peek());
        assertTrue(script.execute(line));
    }
    
    //TESTING FOR HINTS
    
    /**
     * Script test 14
     */
    public void testScriptHint1() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
        //CrpytoUtil Area
        // create key pairs
        KeyPair pair = CryptoUtil.generateKeyPair();
        // create signature 
        String sig = CryptoUtil.signData(data, pair.getPrivate());
        //converts public key to string
        String pkString = CryptoUtil.stringFromPublicKey(pair.getPublic());
        //convert stringKey to hash value
        String hashCopy = CryptoUtil.hash(pkString);
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig, pair.getPublic()));
        
        // testing if script passes or not
        String line = "OP_PUSH " + sig + "\n"
                + "OP_PUSH " + pkString + "\n"
                + "OP_DUP\n" 
                + "OP_HASH\n"
                + "OP_PUSH " + hashCopy + "\n"
                + "OP_EQUAL\n"
                + "OP_VERIFY\n"
                + "OP_CHECKSIG\n"
                + "OP_VERIFY";

        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        
        assertTrue(caught);
        assertTrue(script.getStack().isEmpty());
        assertEquals(0, script.getStack().size());        
    }  
    
    /**
     * Script test 15
     */
    public void testScriptHint2() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
        //CrpytoUtil Area
        // create key pairs
        KeyPair pair = CryptoUtil.generateKeyPair(); 
        // create signature 
        String sig = CryptoUtil.signData(data, pair.getPrivate());
        //converts public key to string
        String pkString = CryptoUtil.stringFromPublicKey(pair.getPublic());
        //convert stringKey to hash value
        String hashCopy = CryptoUtil.hash(pkString);
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig, pair.getPublic()));
        
        // testing if script passes or not
        String line = "OP_PUSH " + sig + "\n"
                + "OP_PUSH " + pkString + "\n"
                + "OP_PUSH " + sig + "\n"
                + "OP_PUSH " + pkString + "\n"
                + "OP_DUP\n" 
                + "OP_HASH\n"
                + "OP_PUSH " + hashCopy + "\n"
                + "OP_EQUAL\n"
                + "OP_VERIFY\n"
                + "OP_CHECKSIG\n"
                + "OP_VERIFY\n"
                + "OP_CHECKSIG";

        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        
        assertTrue(caught);
        assertEquals(1, script.getStack().size());
        assertEquals("1", script.getStack().peek());
        assertTrue(script.execute(line));
    } 
    
    /**
     * Script test 16
     */
    public void testScriptHint3() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
        //CrpytoUtil Area
        // create key pairs
        KeyPair pair = CryptoUtil.generateKeyPair();
        // create signature 
        String sig = CryptoUtil.signData(data, pair.getPrivate());
        //converts public key to string
        String pkString = CryptoUtil.stringFromPublicKey(pair.getPublic());
        //convert stringKey to hash value
        String hashCopy = CryptoUtil.hash(pkString);
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig, pair.getPublic()));
        
        // testing if script passes or not
        String line = "OP_PUSH " + sig + "\n"
                + "OP_PUSH " + pkString + "\n"
                + "OP_PUSH " + sig + "\n"
                + "OP_PUSH " + pkString + "\n"
                + "OP_DUP\n" 
                + "OP_HASH\n"
                + "OP_PUSH " + hashCopy + "\n"
                + "OP_EQUAL\n"
                + "OP_VERIFY\n"
                + "OP_CHECKSIG\n"
                + "OP_VERIFY\n"
                + "OP_CHECKSIG";

        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        
        assertTrue(caught);
        assertEquals(1, script.getStack().size());
        assertEquals("1", script.getStack().peek());
        assertTrue(script.execute(line));
    }
    
    /**
     * Script test 17
     */
    public void testScriptHint4() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
        //CrpytoUtil Area
        // create key pairs
        KeyPair pair = CryptoUtil.generateKeyPair();
        KeyPair pair2 = CryptoUtil.generateKeyPair(); 
        // create signature 
        String sig = CryptoUtil.signData(data, pair2.getPrivate());
        //converts public key to string
        String pkString = CryptoUtil.stringFromPublicKey(pair.getPublic());
        //convert stringKey to hash value
        String hashCopy = CryptoUtil.hash(pkString);
        //Verify the signature with the public key
        assertFalse(CryptoUtil.checkSignature(data, sig, pair.getPublic()));
        
        // testing if script passes or not
        String line = "OP_PUSH " + sig + "\n"
                + "OP_PUSH " + pkString + "\n"
                + "OP_DUP\n" 
                + "OP_HASH\n"
                + "OP_PUSH " + hashCopy + "\n"
                + "OP_EQUAL\n"
                + "OP_VERIFY\n"
                + "OP_CHECKSIG\n"
                + "OP_VERIFY\n";
        
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertFalse(caught);
        assertTrue(script.getStack().isEmpty());
        assertEquals(0, script.getStack().size());
        assertFalse(script.execute(line));
    }
    
    /**
     * Script test 18
     */
    public void testScriptHint5() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
      //CrpytoUtil Area
        // create key pairs
        KeyPair pair1 = CryptoUtil.generateKeyPair();
        KeyPair pair2 = CryptoUtil.generateKeyPair(); 
        // create signature 
        String sig1 = CryptoUtil.signData(data, pair1.getPrivate());
        String sig2 = CryptoUtil.signData(data, pair2.getPrivate());
        //converts public key to string
        String pkString1 = CryptoUtil.stringFromPublicKey(pair1.getPublic());
        String pkString2 = CryptoUtil.stringFromPublicKey(pair2.getPublic());
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig1, pair1.getPublic()));
        assertTrue(CryptoUtil.checkSignature(data, sig2, pair2.getPublic()));
        
     // testing if script passes or not
        String line = "OP_PUSH " + sig1 + "\n"
                + "OP_PUSH " + sig2 + "\n"
                + "OP_PUSH " + "2" + "\n"
                + "OP_PUSH " + pkString2 + "\n"
                + "OP_PUSH " + pkString1 + "\n"
                + "OP_PUSH " + "2" + "\n"
                + "OP_CHECKMULTISIG";
        
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(1, script.getStack().size());
        assertEquals("0", script.getStack().peek());
        assertFalse(script.execute(line));
    }
    
    /**
     * Script test 19
     */
    public void testScriptHint6() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
        //CrpytoUtil Area
        // create key pairs
        KeyPair pair = CryptoUtil.generateKeyPair();  
        // create signature 
        String sig = CryptoUtil.signData(data, pair.getPrivate());
        //converts public key to string
        String pkString = CryptoUtil.stringFromPublicKey(pair.getPublic());
        //convert stringKey to hash value
        String hashCopy = CryptoUtil.hash(pkString);
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig, pair.getPublic()));
        
        // testing if script passes or not
        String line = "OP_PUSH " + pkString + "\n"
                + "OP_PUSH " + sig + "\n"
                + "OP_DUP\n" 
                + "OP_HASH\n"
                + "OP_PUSH " + hashCopy + "\n"
                + "OP_EQUAL\n"
                + "OP_VERIFY\n"
                + "OP_CHECKSIG";
        
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertFalse(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(2, script.getStack().size());
        assertFalse(script.execute(line));
    }

    /**
     * Script test 20
     */
    public void testScriptHint7() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
      //CrpytoUtil Area
        // create key pairs
        KeyPair pair1 = CryptoUtil.generateKeyPair();
        KeyPair pair2 = CryptoUtil.generateKeyPair();
        // create signature 
        String sig1 = CryptoUtil.signData(data, pair1.getPrivate());
        String sig2 = CryptoUtil.signData(data, pair2.getPrivate());
        //converts public key to string
        String pkString1 = CryptoUtil.stringFromPublicKey(pair1.getPublic());
        String pkString2 = CryptoUtil.stringFromPublicKey(pair2.getPublic());
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig1, pair1.getPublic()));
        assertTrue(CryptoUtil.checkSignature(data, sig2, pair2.getPublic()));
        
     // testing if script passes or not
        String line = "OP_PUSH " + sig1 + "\n"
                + "OP_PUSH " + sig2 + "\n"
                + "OP_PUSH " + "2" + "\n"
                + "OP_PUSH " + pkString1 + "\n"
                + "OP_PUSH " + pkString2 + "\n"
                + "OP_PUSH " + "2" + "\n"
                + "OP_CHECKMULTISIG";
        
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(1, script.getStack().size());
        assertEquals("1", script.getStack().peek());
        assertTrue(script.execute(line));
    }
    
    /**
     * Script test 21
     */
    public void testScriptHint8() { 
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
      //CrpytoUtil Area
        // create key pairs
        KeyPair pair1 = CryptoUtil.generateKeyPair();
        KeyPair pair2 = CryptoUtil.generateKeyPair();
        // create signature 
        String sig1 = CryptoUtil.signData(data, pair1.getPrivate());
        String sig2 = CryptoUtil.signData(data, pair2.getPrivate());
        //converts public key to string
        String pkString1 = CryptoUtil.stringFromPublicKey(pair1.getPublic());
        String pkString2 = CryptoUtil.stringFromPublicKey(pair2.getPublic());
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig1, pair1.getPublic()));
        assertFalse(CryptoUtil.checkSignature(data, sig1, pair2.getPublic()));
        
     // testing if script passes or not
        String line = "OP_PUSH " + sig2 + "\n"
                + "OP_PUSH " + sig1 + "\n"
                + "OP_PUSH " + "2" + "\n"
                + "OP_PUSH " + pkString2 + "\n"
                + "OP_PUSH " + pkString1 + "\n"
                + "OP_PUSH " + "2" + "\n"
                + "OP_DUP\n" 
                + "OP_HASH\n"
                + "OP_PUSH " + CryptoUtil.hash("2") + "\n"
                + "OP_EQUAL\n"
                + "OP_VERIFY\n"
                + "OP_CHECKMULTISIG";
        
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(1, script.getStack().size());
        assertEquals("1", script.getStack().peek());
        assertTrue(script.execute(line));
    }
    
    /**
     * Script test 22
     */
    public void testScriptHint9() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
      //CrpytoUtil Area
        // create key pairs
        KeyPair pair1 = CryptoUtil.generateKeyPair();
        KeyPair pair2 = CryptoUtil.generateKeyPair();
        KeyPair pair3 = CryptoUtil.generateKeyPair();
        // create signature 
        String sig1 = CryptoUtil.signData(data, pair1.getPrivate());
        //String sig2 = CryptoUtil.signData(data, pair2.getPrivate());
        //String sig3 = CryptoUtil.signData(data, pair2.getPrivate());
        //converts public key to string
        String pkString1 = CryptoUtil.stringFromPublicKey(pair1.getPublic());
        String pkString2 = CryptoUtil.stringFromPublicKey(pair2.getPublic());
        String pkString3 = CryptoUtil.stringFromPublicKey(pair3.getPublic());
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig1, pair1.getPublic()));
        assertFalse(CryptoUtil.checkSignature(data, sig1, pair2.getPublic()));
        assertFalse(CryptoUtil.checkSignature(data, sig1, pair3.getPublic()));
        
     // testing if script passes or not
        String line = "OP_PUSH " + sig1 + "\n"
                + "OP_PUSH " + "1" + "\n"
                + "OP_PUSH " + pkString2 + "\n"
                + "OP_PUSH " + pkString1 + "\n"
                + "OP_PUSH " + pkString3 + "\n"
                + "OP_PUSH " + "3" + "\n"
                + "OP_CHECKMULTISIG";
        
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(2, script.getStack().size());
        assertEquals("1", script.getStack().peek());
        assertTrue(script.execute(line));
    }
    
    /**
     * Script test 23
     */
    public void testScriptHint10() {
        String data = CryptoUtil.stringFromBytes("Robert Lewis".getBytes());
        ScriptEngine script = new ScriptEngine(data);
        assertTrue(script.getStack().isEmpty());
        
      //CrpytoUtil Area
        // create key pairs
        KeyPair pair1 = CryptoUtil.generateKeyPair();
        KeyPair pair2 = CryptoUtil.generateKeyPair(); 
        KeyPair pair3 = CryptoUtil.generateKeyPair();
        // create signature 
        String sig1 = CryptoUtil.signData(data, pair1.getPrivate());
        String sig2 = CryptoUtil.signData(data, pair2.getPrivate());
        //converts public key to string
        String pkString1 = CryptoUtil.stringFromPublicKey(pair1.getPublic());
        String pkString2 = CryptoUtil.stringFromPublicKey(pair2.getPublic());
        String pkString3 = CryptoUtil.stringFromPublicKey(pair3.getPublic());
        //Verify the signature with the public key
        assertTrue(CryptoUtil.checkSignature(data, sig1, pair1.getPublic()));
        assertTrue(CryptoUtil.checkSignature(data, sig2, pair2.getPublic()));
        
     // testing if script passes or not
        String line = "OP_PUSH " + sig1 + "\n"
                + "OP_PUSH " + sig2 + "\n"
                + "OP_PUSH " + "2" + "\n"
                + "OP_PUSH " + pkString3 + "\n"
                + "OP_PUSH " + pkString2 + "\n"
                + "OP_PUSH " + pkString1 + "\n"
                + "OP_PUSH " + "3" + "\n"
                + "OP_CHECKMULTISIG";
        
        boolean caught = true;
        try {
            script.rawExecute(line);
        }
        catch (VerificationException e) {
            caught = false;
        }
        assertTrue(caught);
        assertFalse(script.getStack().isEmpty());
        assertEquals(1, script.getStack().size());
        assertEquals("0", script.getStack().peek());
    }
}
