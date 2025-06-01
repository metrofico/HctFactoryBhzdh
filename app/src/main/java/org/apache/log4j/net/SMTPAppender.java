package org.apache.log4j.net;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.CyclicBuffer;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;

public class SMTPAppender extends AppenderSkeleton {
   static Class class$org$apache$log4j$spi$TriggeringEventEvaluator;
   private String bcc;
   private int bufferSize;
   protected CyclicBuffer cb;
   private String cc;
   protected TriggeringEventEvaluator evaluator;
   private String from;
   private boolean locationInfo;
   protected Message msg;
   private boolean smtpDebug;
   private String smtpHost;
   private String smtpPassword;
   private String smtpUsername;
   private String subject;
   private String to;

   public SMTPAppender() {
      this(new DefaultEvaluator());
   }

   public SMTPAppender(TriggeringEventEvaluator var1) {
      this.smtpDebug = false;
      this.bufferSize = 512;
      this.locationInfo = false;
      this.cb = new CyclicBuffer(this.bufferSize);
      this.evaluator = var1;
   }

   // $FF: synthetic method
   static String access$000(SMTPAppender var0) {
      return var0.smtpUsername;
   }

   // $FF: synthetic method
   static String access$100(SMTPAppender var0) {
      return var0.smtpPassword;
   }

   // $FF: synthetic method
   static Class class$(String var0) {
      try {
         Class var2 = Class.forName(var0);
         return var2;
      } catch (ClassNotFoundException var1) {
         throw new NoClassDefFoundError(var1.getMessage());
      }
   }

   public void activateOptions() {
      MimeMessage var1 = new MimeMessage(this.createSession());
      this.msg = var1;

      MessagingException var10000;
      label28: {
         boolean var10001;
         String var4;
         try {
            this.addressMessage(var1);
            var4 = this.subject;
         } catch (MessagingException var3) {
            var10000 = var3;
            var10001 = false;
            break label28;
         }

         if (var4 == null) {
            return;
         }

         try {
            this.msg.setSubject(var4);
            return;
         } catch (MessagingException var2) {
            var10000 = var2;
            var10001 = false;
         }
      }

      MessagingException var5 = var10000;
      LogLog.error("Could not activate SMTPAppender options.", var5);
   }

   protected void addressMessage(Message var1) throws MessagingException {
      String var2 = this.from;
      if (var2 != null) {
         var1.setFrom(this.getAddress(var2));
      } else {
         var1.setFrom();
      }

      var2 = this.to;
      if (var2 != null && var2.length() > 0) {
         var1.setRecipients(RecipientType.TO, this.parseAddress(this.to));
      }

      var2 = this.cc;
      if (var2 != null && var2.length() > 0) {
         var1.setRecipients(RecipientType.CC, this.parseAddress(this.cc));
      }

      var2 = this.bcc;
      if (var2 != null && var2.length() > 0) {
         var1.setRecipients(RecipientType.BCC, this.parseAddress(this.bcc));
      }

   }

   public void append(LoggingEvent var1) {
      if (this.checkEntryConditions()) {
         var1.getThreadName();
         var1.getNDC();
         var1.getMDCCopy();
         if (this.locationInfo) {
            var1.getLocationInformation();
         }

         this.cb.add(var1);
         if (this.evaluator.isTriggeringEvent(var1)) {
            this.sendBuffer();
         }

      }
   }

   protected boolean checkEntryConditions() {
      ErrorHandler var1;
      String var2;
      if (this.msg == null) {
         var1 = super.errorHandler;
         var2 = "Message object not configured.";
      } else {
         String var3;
         StringBuffer var4;
         if (this.evaluator == null) {
            var1 = super.errorHandler;
            var4 = new StringBuffer();
            var3 = "No TriggeringEventEvaluator is set for appender [";
         } else {
            if (super.layout != null) {
               return true;
            }

            var1 = super.errorHandler;
            var4 = new StringBuffer();
            var3 = "No layout set for appender named [";
         }

         var2 = var4.append(var3).append(super.name).append("].").toString();
      }

      var1.error(var2);
      return false;
   }

   public void close() {
      synchronized(this){}

      try {
         super.closed = true;
      } finally {
         ;
      }

   }

   protected Session createSession() {
      Properties var2;
      try {
         var2 = new Properties(System.getProperties());
      } catch (SecurityException var5) {
         var2 = new Properties();
      }

      String var3 = this.smtpHost;
      if (var3 != null) {
         var2.put("mail.smtp.host", var3);
      }

      Object var4 = null;
      SMTPAppender$1 var6 = (SMTPAppender$1)var4;
      if (this.smtpPassword != null) {
         var6 = (SMTPAppender$1)var4;
         if (this.smtpUsername != null) {
            var2.put("mail.smtp.auth", "true");
            var6 = new SMTPAppender$1(this);
         }
      }

      Session var7 = Session.getInstance(var2, var6);
      boolean var1 = this.smtpDebug;
      if (var1) {
         var7.setDebug(var1);
      }

      return var7;
   }

   InternetAddress getAddress(String var1) {
      try {
         InternetAddress var2 = new InternetAddress(var1);
         return var2;
      } catch (AddressException var3) {
         super.errorHandler.error("Could not parse address [" + var1 + "].", var3, 6);
         return null;
      }
   }

   public String getBcc() {
      return this.bcc;
   }

   public int getBufferSize() {
      return this.bufferSize;
   }

   public String getCc() {
      return this.cc;
   }

   public String getEvaluatorClass() {
      TriggeringEventEvaluator var1 = this.evaluator;
      String var2;
      if (var1 == null) {
         var2 = null;
      } else {
         var2 = var1.getClass().getName();
      }

      return var2;
   }

   public String getFrom() {
      return this.from;
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public boolean getSMTPDebug() {
      return this.smtpDebug;
   }

   public String getSMTPHost() {
      return this.smtpHost;
   }

   public String getSMTPPassword() {
      return this.smtpPassword;
   }

   public String getSMTPUsername() {
      return this.smtpUsername;
   }

   public String getSubject() {
      return this.subject;
   }

   public String getTo() {
      return this.to;
   }

   InternetAddress[] parseAddress(String var1) {
      try {
         InternetAddress[] var2 = InternetAddress.parse(var1, true);
         return var2;
      } catch (AddressException var3) {
         super.errorHandler.error("Could not parse address [" + var1 + "].", var3, 6);
         return null;
      }
   }

   public boolean requiresLayout() {
      return true;
   }

   protected void sendBuffer() {
      Exception var10000;
      label87: {
         MimeBodyPart var4;
         StringBuffer var5;
         String var6;
         boolean var10001;
         try {
            var4 = new MimeBodyPart();
            var5 = new StringBuffer();
            var6 = super.layout.getHeader();
         } catch (Exception var15) {
            var10000 = var15;
            var10001 = false;
            break label87;
         }

         if (var6 != null) {
            try {
               var5.append(var6);
            } catch (Exception var14) {
               var10000 = var14;
               var10001 = false;
               break label87;
            }
         }

         int var3;
         try {
            var3 = this.cb.length();
         } catch (Exception var13) {
            var10000 = var13;
            var10001 = false;
            break label87;
         }

         int var1 = 0;

         label73:
         while(true) {
            if (var1 >= var3) {
               try {
                  var6 = super.layout.getFooter();
               } catch (Exception var9) {
                  var10000 = var9;
                  var10001 = false;
                  break;
               }

               if (var6 != null) {
                  try {
                     var5.append(var6);
                  } catch (Exception var8) {
                     var10000 = var8;
                     var10001 = false;
                     break;
                  }
               }

               try {
                  var4.setContent(var5.toString(), super.layout.getContentType());
                  MimeMultipart var18 = new MimeMultipart();
                  var18.addBodyPart(var4);
                  this.msg.setContent(var18);
                  Message var19 = this.msg;
                  Date var17 = new Date();
                  var19.setSentDate(var17);
                  Transport.send(this.msg);
                  return;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break;
               }
            }

            label90: {
               String[] var21;
               try {
                  LoggingEvent var20 = this.cb.get();
                  var5.append(super.layout.format(var20));
                  if (!super.layout.ignoresThrowable()) {
                     break label90;
                  }

                  var21 = var20.getThrowableStrRep();
               } catch (Exception var12) {
                  var10000 = var12;
                  var10001 = false;
                  break;
               }

               if (var21 != null) {
                  int var2 = 0;

                  while(true) {
                     try {
                        if (var2 >= var21.length) {
                           break;
                        }
                     } catch (Exception var11) {
                        var10000 = var11;
                        var10001 = false;
                        break label73;
                     }

                     try {
                        var5.append(var21[var2]);
                        var5.append(Layout.LINE_SEP);
                     } catch (Exception var10) {
                        var10000 = var10;
                        var10001 = false;
                        break label73;
                     }

                     ++var2;
                  }
               }
            }

            ++var1;
         }
      }

      Exception var16 = var10000;
      LogLog.error("Error occured while sending e-mail notification.", var16);
   }

   public void setBcc(String var1) {
      this.bcc = var1;
   }

   public void setBufferSize(int var1) {
      this.bufferSize = var1;
      this.cb.resize(var1);
   }

   public void setCc(String var1) {
      this.cc = var1;
   }

   public void setEvaluatorClass(String var1) {
      Class var3 = class$org$apache$log4j$spi$TriggeringEventEvaluator;
      Class var2 = var3;
      if (var3 == null) {
         var2 = class$("org.apache.log4j.spi.TriggeringEventEvaluator");
         class$org$apache$log4j$spi$TriggeringEventEvaluator = var2;
      }

      this.evaluator = (TriggeringEventEvaluator)OptionConverter.instantiateByClassName(var1, var2, this.evaluator);
   }

   public void setFrom(String var1) {
      this.from = var1;
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }

   public void setSMTPDebug(boolean var1) {
      this.smtpDebug = var1;
   }

   public void setSMTPHost(String var1) {
      this.smtpHost = var1;
   }

   public void setSMTPPassword(String var1) {
      this.smtpPassword = var1;
   }

   public void setSMTPUsername(String var1) {
      this.smtpUsername = var1;
   }

   public void setSubject(String var1) {
      this.subject = var1;
   }

   public void setTo(String var1) {
      this.to = var1;
   }
}
