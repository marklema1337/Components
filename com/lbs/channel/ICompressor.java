package com.lbs.channel;

import java.io.IOException;

public interface ICompressor {
  int getSignature();
  
  byte[] compress(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  byte[] uncompress(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\channel\ICompressor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */