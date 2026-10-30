package JavaBank;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class PasswordHasher {
    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 32;

    private static final int ITERATIONS = 2;
    private static final int MEMORY = 19456; // 19 MiB or 19456 KiB
    private static final int PARALLELISM = 1;

    private static byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static void main(String[]args) {
        System.out.println(Arrays.toString(generateSalt()));
    }



    
}
