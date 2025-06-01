package org.apache.log4j.net;

import java.util.Properties;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class JMSAppender extends AppenderSkeleton {
   String initialContextFactoryName;
   boolean locationInfo;
   String password;
   String providerURL;
   String securityCredentials;
   String securityPrincipalName;
   String tcfBindingName;
   String topicBindingName;
   TopicConnection topicConnection;
   TopicPublisher topicPublisher;
   TopicSession topicSession;
   String urlPkgPrefixes;
   String userName;

   public void activateOptions() {
      Exception var10000;
      label117: {
         boolean var10001;
         InitialContext var20;
         label113: {
            label118: {
               String var1;
               Properties var2;
               try {
                  LogLog.debug("Getting initial context.");
                  if (this.initialContextFactoryName == null) {
                     break label118;
                  }

                  var2 = new Properties();
                  var2.put("java.naming.factory.initial", this.initialContextFactoryName);
                  var1 = this.providerURL;
               } catch (Exception var19) {
                  var10000 = var19;
                  var10001 = false;
                  break label117;
               }

               if (var1 != null) {
                  try {
                     var2.put("java.naming.provider.url", var1);
                  } catch (Exception var18) {
                     var10000 = var18;
                     var10001 = false;
                     break label117;
                  }
               } else {
                  try {
                     LogLog.warn("You have set InitialContextFactoryName option but not the ProviderURL. This is likely to cause problems.");
                  } catch (Exception var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label117;
                  }
               }

               try {
                  var1 = this.urlPkgPrefixes;
               } catch (Exception var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label117;
               }

               if (var1 != null) {
                  try {
                     var2.put("java.naming.factory.url.pkgs", var1);
                  } catch (Exception var15) {
                     var10000 = var15;
                     var10001 = false;
                     break label117;
                  }
               }

               try {
                  var1 = this.securityPrincipalName;
               } catch (Exception var14) {
                  var10000 = var14;
                  var10001 = false;
                  break label117;
               }

               if (var1 != null) {
                  try {
                     var2.put("java.naming.security.principal", var1);
                     var1 = this.securityCredentials;
                  } catch (Exception var13) {
                     var10000 = var13;
                     var10001 = false;
                     break label117;
                  }

                  if (var1 != null) {
                     try {
                        var2.put("java.naming.security.credentials", var1);
                     } catch (Exception var12) {
                        var10000 = var12;
                        var10001 = false;
                        break label117;
                     }
                  } else {
                     try {
                        LogLog.warn("You have set SecurityPrincipalName option but not the SecurityCredentials. This is likely to cause problems.");
                     } catch (Exception var11) {
                        var10000 = var11;
                        var10001 = false;
                        break label117;
                     }
                  }
               }

               try {
                  var20 = new InitialContext(var2);
                  break label113;
               } catch (Exception var10) {
                  var10000 = var10;
                  var10001 = false;
                  break label117;
               }
            }

            try {
               var20 = new InitialContext();
            } catch (Exception var9) {
               var10000 = var9;
               var10001 = false;
               break label117;
            }
         }

         TopicConnectionFactory var3;
         StringBuffer var21;
         String var22;
         try {
            var21 = new StringBuffer();
            LogLog.debug(var21.append("Looking up [").append(this.tcfBindingName).append("]").toString());
            var3 = (TopicConnectionFactory)this.lookup(var20, this.tcfBindingName);
            LogLog.debug("About to create TopicConnection.");
            var22 = this.userName;
         } catch (Exception var8) {
            var10000 = var8;
            var10001 = false;
            break label117;
         }

         TopicConnection var23;
         if (var22 != null) {
            try {
               var23 = var3.createTopicConnection(var22, this.password);
            } catch (Exception var7) {
               var10000 = var7;
               var10001 = false;
               break label117;
            }
         } else {
            try {
               var23 = var3.createTopicConnection();
            } catch (Exception var6) {
               var10000 = var6;
               var10001 = false;
               break label117;
            }
         }

         try {
            this.topicConnection = var23;
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label117;
         }

         try {
            LogLog.debug("Creating TopicSession, non-transactional, in AUTO_ACKNOWLEDGE mode.");
            this.topicSession = this.topicConnection.createTopicSession(false, 1);
            var21 = new StringBuffer();
            LogLog.debug(var21.append("Looking up topic name [").append(this.topicBindingName).append("].").toString());
            Topic var25 = (Topic)this.lookup(var20, this.topicBindingName);
            LogLog.debug("Creating TopicPublisher.");
            this.topicPublisher = this.topicSession.createPublisher(var25);
            LogLog.debug("Starting TopicConnection.");
            this.topicConnection.start();
            var20.close();
            return;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var24 = var10000;
      super.errorHandler.error("Error while activating options for appender named [" + super.name + "].", var24, 0);
   }

   public void append(LoggingEvent var1) {
      if (this.checkEntryConditions()) {
         try {
            ObjectMessage var2 = this.topicSession.createObjectMessage();
            if (this.locationInfo) {
               var1.getLocationInformation();
            }

            var2.setObject(var1);
            this.topicPublisher.publish(var2);
         } catch (Exception var3) {
            super.errorHandler.error("Could not publish message in JMSAppender [" + super.name + "].", var3, 0);
         }

      }
   }

   protected boolean checkEntryConditions() {
      String var1;
      if (this.topicConnection == null) {
         var1 = "No TopicConnection";
      } else if (this.topicSession == null) {
         var1 = "No TopicSession";
      } else if (this.topicPublisher == null) {
         var1 = "No TopicPublisher";
      } else {
         var1 = null;
      }

      if (var1 != null) {
         super.errorHandler.error(var1 + " for JMSAppender named [" + super.name + "].");
         return false;
      } else {
         return true;
      }
   }

   public void close() {
      // $FF: Couldn't be decompiled
   }

   public String getInitialContextFactoryName() {
      return this.initialContextFactoryName;
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public String getPassword() {
      return this.password;
   }

   public String getProviderURL() {
      return this.providerURL;
   }

   public String getSecurityCredentials() {
      return this.securityCredentials;
   }

   public String getSecurityPrincipalName() {
      return this.securityPrincipalName;
   }

   public String getTopicBindingName() {
      return this.topicBindingName;
   }

   protected TopicConnection getTopicConnection() {
      return this.topicConnection;
   }

   public String getTopicConnectionFactoryBindingName() {
      return this.tcfBindingName;
   }

   protected TopicPublisher getTopicPublisher() {
      return this.topicPublisher;
   }

   protected TopicSession getTopicSession() {
      return this.topicSession;
   }

   String getURLPkgPrefixes() {
      return this.urlPkgPrefixes;
   }

   public String getUserName() {
      return this.userName;
   }

   protected Object lookup(Context var1, String var2) throws NamingException {
      try {
         Object var4 = var1.lookup(var2);
         return var4;
      } catch (NameNotFoundException var3) {
         LogLog.error("Could not find name [" + var2 + "].");
         throw var3;
      }
   }

   public boolean requiresLayout() {
      return false;
   }

   public void setInitialContextFactoryName(String var1) {
      this.initialContextFactoryName = var1;
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }

   public void setPassword(String var1) {
      this.password = var1;
   }

   public void setProviderURL(String var1) {
      this.providerURL = var1;
   }

   public void setSecurityCredentials(String var1) {
      this.securityCredentials = var1;
   }

   public void setSecurityPrincipalName(String var1) {
      this.securityPrincipalName = var1;
   }

   public void setTopicBindingName(String var1) {
      this.topicBindingName = var1;
   }

   public void setTopicConnectionFactoryBindingName(String var1) {
      this.tcfBindingName = var1;
   }

   public void setURLPkgPrefixes(String var1) {
      this.urlPkgPrefixes = var1;
   }

   public void setUserName(String var1) {
      this.userName = var1;
   }
}
