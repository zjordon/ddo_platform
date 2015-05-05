package com.jason.ddoMsg.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesUtils {
	
	  private static final byte[] BYTES = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
		    0, 0, 0, 0, 0, 0, 0, 10, 11, 12, 13, 14, 15 };
	  
	 
	  /**
		  * 加密
		 * 
		  * @param content 需要加密的内容
		 * @param password  加密密码
		 * @return
		  */ 
	 public static byte[] encrypt(String content, String password) { 
	         try {            
	                 KeyGenerator kgen = KeyGenerator.getInstance("AES"); 
	                 kgen.init(128, new SecureRandom(password.getBytes())); 
	                 SecretKey secretKey = kgen.generateKey(); 
	                 byte[] enCodeFormat = secretKey.getEncoded(); 
	                 SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES"); 
	                 Cipher cipher = Cipher.getInstance("AES");// 创建密码器 
	                 byte[] byteContent = content.getBytes("utf-8"); 
	                 cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化 
	                 byte[] result = cipher.doFinal(byteContent); 
	                 return result; // 加密 
	         } catch (NoSuchAlgorithmException e) { 
	                 e.printStackTrace(); 
	         } catch (NoSuchPaddingException e) { 
	                 e.printStackTrace(); 
	         } catch (InvalidKeyException e) { 
	                 e.printStackTrace(); 
	         } catch (UnsupportedEncodingException e) { 
	                 e.printStackTrace(); 
	         } catch (IllegalBlockSizeException e) { 
	                 e.printStackTrace(); 
	         } catch (BadPaddingException e) { 
	                 e.printStackTrace(); 
	         } 
	         return null; 
	 } 
	 
	 /**解密
	  * @param content  待解密内容
	  * @param password 解密密钥
	  * @return
	   */ 
	  public static byte[] decrypt(byte[] content, String password) { 
	          try { 
	                   KeyGenerator kgen = KeyGenerator.getInstance("AES"); 
	                   kgen.init(128, new SecureRandom(password.getBytes())); 
	                   SecretKey secretKey = kgen.generateKey(); 
	                   byte[] enCodeFormat = secretKey.getEncoded(); 
	                   SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");             
	                   Cipher cipher = Cipher.getInstance("AES");// 创建密码器 
	                  cipher.init(Cipher.DECRYPT_MODE, key);// 初始化 
	                  byte[] result = cipher.doFinal(content); 
	                  return result; // 加密 
	          } catch (NoSuchAlgorithmException e) { 
	                  e.printStackTrace(); 
	          } catch (NoSuchPaddingException e) { 
	                  e.printStackTrace(); 
	          } catch (InvalidKeyException e) { 
	                  e.printStackTrace(); 
	          } catch (IllegalBlockSizeException e) { 
	                  e.printStackTrace(); 
	          } catch (BadPaddingException e) { 
	                  e.printStackTrace(); 
	          } 
	          return null; 
	  }
	  


		  public static String bytes2Hex(byte[] bts)
		  {
		    String des = "";
		    String tmp = null;
		    for (int i = 0; i < bts.length; i++)
		    {
		      tmp = Integer.toHexString(bts[i] & 0xFF);
		      if (tmp.length() == 1)
		      {
		        des = des + "0";
		      }
		      des = des + tmp;
		    }
		    return des;
		  }

		  public static byte[] hexToBytes(String hexStr)
		  {
		    String innerStr = hexStr.toUpperCase();

		    byte[] innerBytes = new byte[innerStr.length() / 2];

		    for (int i = 0; i < innerStr.length() - 1; i += 2)
		    {
		      innerBytes[(i / 2)] = ((byte)(BYTES[(innerStr.charAt(i) - '0')] << 4 | BYTES[(innerStr.charAt(i + 1) - '0')]));
		    }

		    return innerBytes;
		  }
	  
	  /**将二进制转换成16进制
	   * @param buf
	    * @return
	    */ 
	   public static String parseByte2HexStr(byte buf[]) { 
	           StringBuffer sb = new StringBuffer(); 
	           for (int i = 0; i < buf.length; i++) { 
	                   String hex = Integer.toHexString(buf[i] & 0xFF); 
	                   if (hex.length() == 1) { 
	                           hex = '0' + hex; 
	                   } 
	                   sb.append(hex.toLowerCase()); 
	           } 
	           return sb.toString(); 
	   } 
	   
	   /**将16进制转换为二进制
	    * @param hexStr
	     * @return
	     */ 
	    public static byte[] parseHexStr2Byte(String hexStr) { 
	            if (hexStr.length() < 1) 
	                    return null; 
	            byte[] result = new byte[hexStr.length()/2]; 
	            for (int i = 0;i< hexStr.length()/2; i++) { 
	                    int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16); 
	                    int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16); 
	                    result[i] = (byte) (high * 16 + low); 
	            } 
	            return result; 
	    } 
	    
	    /**
	       * 加密
	      *
	       * @param content 需要加密的内容
	      * @param password  加密密码
	      * @return
	       */ 
	      public static byte[] encrypt2(String content, String password) { 
	              try { 
	                      SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES"); 
	                      Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding"); 
	                      byte[] byteContent = content.getBytes("utf-8"); 
	                      cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化 
	                      byte[] result = cipher.doFinal(byteContent); 
	                      return result; // 加密 
	              } catch (NoSuchAlgorithmException e) { 
	                      e.printStackTrace(); 
	              } catch (NoSuchPaddingException e) { 
	                      e.printStackTrace(); 
	              } catch (InvalidKeyException e) { 
	                      e.printStackTrace(); 
	              } catch (UnsupportedEncodingException e) { 
	                      e.printStackTrace(); 
	              } catch (IllegalBlockSizeException e) { 
	                      e.printStackTrace(); 
	              } catch (BadPaddingException e) { 
	                      e.printStackTrace(); 
	              } 
	              return null; 
	      }
	      
	      public static byte[] encrypt3(String content, String password)
	      {
	        try
	        {
	          SecretKeySpec keyspec = new SecretKeySpec(password.getBytes(), "AES");

	          Cipher cipher = Cipher.getInstance("AES");

	          cipher.init(1, keyspec);

	          return cipher.doFinal(content.getBytes("UTF-8"));
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	        }
	        return null;
	      }

	      public static byte[] decrypt3(byte[] content, String password)
	      {
	        try
	        {
	          SecretKeySpec keyspec = new SecretKeySpec(password.getBytes(), "AES");

	          Cipher cipher = Cipher.getInstance("AES");

	          cipher.init(2, keyspec);

	          return cipher.doFinal(content);
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	        }
	        return null;
	      }
	  
	  public static void main(String[] args) {
		  try{
			  
		  
		  String content = "CP1291"; 
		  String password = "zytddo1230000000"; 
		  //加密 
		 System.out.println("加密前：" + content); 
		 byte[] encryptResult = encrypt3(content, password);
		 //String tt4 = Base64.encode(encryptResult);
		 System.out.println(parseByte2HexStr(encryptResult));
		 
		 String res="0b4b0cc97a4c007045eed59fd7c0b31e";
		 
		 byte[] encryptResult2 = parseHexStr2Byte(res);
		  //解密 
		 byte[] decryptResult = decrypt3(encryptResult2,password); 
		 System.out.println("解密后：" + new String(decryptResult)); 
		 
		 int n=6;
		 String s="abc";
		 System.out.println("%1$0"+(n-s.length())+"d");
		 System.out.println(s+String.format("%1$0"+(n-s.length())+"d",0));
		  }
		  catch(Exception e){
			  
		  }
		  
	}
}