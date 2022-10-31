package com.lbs.channel;

import com.lbs.transport.CryptorException;

public interface ICryptor {
  public static final int SECRETKEY = 0;
  
  public static final int PRIVATEKEY = 0;
  
  public static final int PUBLICKEY = 1;
  
  public static final int ENCRYPTPARAM = 2;
  
  public static final int DECRYPTPARAM = 3;
  
  public static final int CRYPTORTYPE = 4;
  
  public static final int SYMETRIC = 0;
  
  public static final int ASYMETRIC = 1;
  
  boolean setParam(int paramInt, Object paramObject);
  
  Object getParam(int paramInt) throws CryptorException;
  
  byte[] encrypt(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws CryptorException;
  
  byte[] decrypt(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws CryptorException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\channel\ICryptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */