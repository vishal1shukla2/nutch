package org.apache.nutch.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vishalshukla on 29/08/15.
 */
public class HashUtil {

  public static String generateHash(String content){

    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
      md.update(content.getBytes());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }


    byte byteData[] = md.digest();

    //convert the byte to hex format method 1
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < byteData.length; i++) {
      sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();

  }

  public static void main(String[] args){
    String hash = generateHash("AC m / v \"Captain Muromtsev\" at 08.00 24.07.2015g. in statu quo ante");
    System.out.println(hash);
  }

}
