package JavaBank;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import java.security.SecureRandom;
import java.util.Base64;


public class PasswordHasher {
    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 32;

    private static final int ITERATIONS = 2;
    private static final int MEMORY = 19456; // 19 MiB or 19456 KiB
    private static final int PARALLELISM = 1;
    private static final int VERSION = Argon2Parameters.ARGON2_VERSION_13;
    private static final SecureRandom secureRandom = new SecureRandom();

    private static byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        return salt;
    }


    private static byte[] hashPassword(char[] password, byte[] salt) {
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


    public static boolean verifyPassword(String password, String storedPassword) {

        return false;
    }

    public static String encodeHash(String password) {
        byte[] salt = generateSalt();
        byte[] hashedPassword = hashPassword(password.toCharArray(), salt);
        StringBuilder phc = new StringBuilder();
        phc.append("$argon2id");
        phc.append("$v=19");
        phc.append("$m=65536,t=3,p=4");
        phc.append("$").append(Base64.getEncoder().withoutPadding().encodeToString(salt));
        phc.append("$").append(Base64.getEncoder().withoutPadding().encodeToString(hashedPassword));

        return phc.toString();
    }
    
    public static void main(String[]args){

        System.out.println(encodeHash("Canadabois"));
    }
}
