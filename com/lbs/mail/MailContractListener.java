package com.lbs.mail;

import com.lbs.remoteclient.IClientContext;

public interface MailContractListener {
  void beforeMailSend(IClientContext paramIClientContext, MailEvent paramMailEvent) throws Exception;
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\mail\MailContractListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */