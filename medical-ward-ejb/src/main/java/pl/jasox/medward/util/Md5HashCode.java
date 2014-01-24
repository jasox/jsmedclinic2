package pl.jasox.medward.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/** */
public class Md5HashCode {
  // ...
	private static Logger logger = Logger.getLogger(Md5HashCode.class.getName());
	
  /**
   * Koduje dany String MD5 
   * @param  text     String do zakodowania
   * @return rezultat kodowania MD5
   */
  public static String getMd5HashCode(String text) {
    String passMD5 = null;
    try {
      byte[] passwordBytes    = text.getBytes();
      MessageDigest algorithm = MessageDigest.getInstance("MD5");
      algorithm.reset();
      algorithm.update(passwordBytes);
      byte[] messageDigest   = algorithm.digest();
      StringBuffer hexString = new StringBuffer();
      for ( int i = 0; i < messageDigest.length; i++ ) {
        String hex = Integer.toHexString(0xFF & messageDigest[i]);
        if ( hex.length() == 1 ) {
          hexString.append('0');
        }
        hexString.append(hex);
      }
      passMD5 = hexString + "";
    } 
    catch (NoSuchAlgorithmException ex) {
        logger.severe("MD5 error: " + ex.getMessage());
    }
    return passMD5;
  }
  
}
