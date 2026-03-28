package modules.blockchain.domain.valueobjects;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Hash value object
 * Creates a digital signature by hashing aggregate input given to it
 */
public class Hash {
    private final String _hash;

    /**
     * @param hash hashed value to be stored as property
     */
    private Hash(String hash) {
        this._hash = hash;
    }

    /**
     * Creates a Hash object
     * @param args array of inputs to hash
     * @return Hash object that contains a digital signature
     */
    public static Hash create(String ...args) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            StringBuilder input = new StringBuilder();
            for( String arg : args ) {
                input.append(arg);
            }
            byte[] hashedBytes = digest.digest(input.toString().getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            // ensure hashed bytes are converted from signed bytes to unsigned bytes and convert to string
            for(byte hashedByte :  hashedBytes) {
                // converts to unsigned int
                String hex = Integer.toHexString(0xff & hashedByte);
                // since hex strings are meant to be doubles, if a single string is found,
                // prepend with 0
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            // creates hash value object and stores hash value in it
            return new Hash(hexString.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param hash hashed string value
     * @return Hash object
     */
    public static Hash reconstitute(String hash) {
        return new Hash(hash);
    }

    /**
     * Returns hash property
     * @return hash
     */
    public String getHash() {
        return this._hash;
    }
}
