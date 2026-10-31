
package JavaBank;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Arrays;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordHasher {

    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 32;

    private static final int ITERATIONS = 2;
    private static final int MEMORY = 19456;
    private static final int PARALLELISM = 1;
    private static final int VERSION = Argon2Parameters.ARGON2_VERSION_13;

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final Argon2 ARGON2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    private static byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        SECURE_RANDOM.nextBytes(salt);
        return salt;
    }

    private static byte[] derivePasswordHash(char[] password, byte[] salt) {

        Argon2Parameters parameters = new Argon2Parameters.Builder(
            Argon2Parameters.ARGON2_id)
            .withVersion(VERSION)
            .withIterations(ITERATIONS)
            .withMemoryAsKB(MEMORY)
            .withParallelism(PARALLELISM)
            .withSalt(salt)
            .build();

        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(parameters);

        byte[] hash = new byte[HASH_LENGTH];
        generator.generateBytes(password, hash);

        return hash;
    }

    public static String hashAndEncodePassword(String password) {

        byte[] salt = generateSalt();
        char[] passwordChars = password.toCharArray();
        byte[] hashedPassword = null;

        try {
            hashedPassword = derivePasswordHash(passwordChars, salt);

            String encodedSalt = Base64.getEncoder()
                .withoutPadding()
                .encodeToString(salt);

            String encodedHash = Base64.getEncoder()
                .withoutPadding()
                .encodeToString(hashedPassword);

            StringBuilder phc = new StringBuilder();

            phc.append("$argon2id");
            phc.append("$v=").append(VERSION);
            phc.append("$m=").append(MEMORY)
                .append(",t=").append(ITERATIONS)
                .append(",p=").append(PARALLELISM);
            phc.append("$").append(encodedSalt);
            phc.append("$").append(encodedHash);

            return phc.toString();

        } finally {
            ARGON2.wipeArray(passwordChars);

            if (hashedPassword != null) {
                Arrays.fill(hashedPassword, (byte) 0);
            }
        }
    }

    public static boolean verifyPassword(String password, String storedPassword) {
        char[] passwordChars = password.toCharArray();

        try {
            return ARGON2.verify(storedPassword, passwordChars);
        } finally {
            ARGON2.wipeArray(passwordChars);
        }
    }

    public static void main(String[] args) {

        String hash = hashAndEncodePassword("password123");

        System.out.println(hash);
        System.out.println(verifyPassword("password123", hash));
        System.out.println(verifyPassword("wrongpassword", hash));
    }
}
