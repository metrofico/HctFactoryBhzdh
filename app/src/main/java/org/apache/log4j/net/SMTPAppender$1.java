package org.apache.log4j.net;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class SMTPAppender$1 extends Authenticator {
   private final SMTPAppender this$0;

   SMTPAppender$1(SMTPAppender var1) {
      this.this$0 = var1;
   }

   protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(SMTPAppender.access$000(this.this$0), SMTPAppender.access$100(this.this$0));
   }
}
