package com.lbs.transport;

import javax.crypto.Cipher;

public interface ISessionBase {
  String getSessionCode();
  
  Object getRemoteCryptoParam();
  
  Object getLocalCryptoParam();
  
  Object getSessionData();
  
  Cipher getDecryptionCipher();
  
  Cipher getEncryptionCipher();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\transport\ISessionBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */