package hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;
public class HashingExample {

  public static void main(String[] args) {
    //System.out.println("--------------------------");

    Scanner in = new Scanner(System.in);

    System.out.println("Enter zID: ");
    String number = in.nextLine();
    System.out.println();
    System.out.println("--------------------------");
    System.out.println("You Entered: " + number);

    System.out.println("--------------------------");
    System.out.println();

    //Convert string to bytes
    byte[] bytes = number.getBytes();

    // Printing the read line
    System.out.println(doHashing(number, bytes));

    in.close();
  }

  //Generate random salt value
  public static byte[] getSalt() throws NoSuchAlgorithmException {
    SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
    //Create array for sallt (16 bytes length)
    byte[] salt = new byte[16];
    //Get random salt
    secureRandom.nextBytes(salt);
    //Return generated salt
    return salt;
  }

  public static String doHashing(String password, byte[] salt) {
    try {
      Scanner in = new Scanner(System.in);

      System.out.println(
        "Enter Hashing Algorithm (e.g MD5, SHA-256, SHA-512...): "
      );

      //Input hashing algorithm
      String alg = in.nextLine();
      System.out.println();
      System.out.println("--------------------------");
      System.out.println("Hashing Algorithm: " + alg);

      in.close();

      System.out.println("--------------------------");
      System.out.println();
      //Get selected algorithm MessageDigest
      MessageDigest messageDigest = MessageDigest.getInstance(alg);
      //Add salt to MessageDigest
      messageDigest.update(salt);

      messageDigest.update(password.getBytes());
      //Digest input
      byte[] resultByteArray = messageDigest.digest();
      //Reset MessageDigest
      messageDigest.reset();

      StringBuilder sb = new StringBuilder();

      for (byte b : resultByteArray) {
        sb.append(String.format("%02x", b));
      }
      
      // //VERIFY HASH TESTING
      // System.out.println("Verifying hash...should be the same as:");
      // // Compare hash with hash string to verify
      // String str = "31daad71e8ed04dc6b3bbc0b4d946dc4";
      // System.out.println(str);
      // //System.out.println(sb.hashCode() == str.hashCode());
      // System.out.println(sb.toString().equals(str));
      // System.out.println("--------------------------");

      //Display generated hash
      System.out.println("Generated hash:");
      return sb.toString();

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    return "";
  }
}
