import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256 {

	private String saltString = "1234567890123456";
	private String SECRET_KEY = "my_super_secret_key_ho_ho_ho";
	private byte[] SALT;
	private static final	byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  private static final IvParameterSpec ivspec = new IvParameterSpec(iv);

	public AES256 () {
		SALT = saltString.getBytes();
	}
	public AES256 (String secretKey) {
		SECRET_KEY = secretKey;
		SALT = saltString.getBytes();
	}
	public AES256 (String secretKey, String salt) {
		String secret = secretKey;
		if (secretKey.length() < 32 || secretKey.length() > 32) {
			if (secretKey.length() > 32) {
				SECRET_KEY = secret.substring(0, 31);
			} else {
				if (secret.length() < 16)
					secret = secret.concat(secretKey);
				secret = secret.concat(secretKey.substring(0, 15 - secretKey.length()));
			}
		}

		String salt_ = salt;
		SECRET_KEY = secret;
		if (salt.length() < 16  || salt.length() > 16) {
			if (salt.length() > 16) {
				salt_ = salt.substring(0, 15);
			} else {
				if (salt.length() < 8)
					salt = salt.concat(salt);
				salt_ = salt.concat(salt.substring(0, 7 - secret.length()));
			}
		}
		SALT = salt_.getBytes();
	}

	public void changeSalt (String Salt) {
		SALT = Salt.getBytes();
	}
	public void changeSecretKey (String SecretKey) {
		SECRET_KEY = SecretKey;
	}
	public String encrypt (String strToEncrypt) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT, 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public String decrypt (String strToDecrypt) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT, 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}
}
