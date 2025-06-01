package org.apache.log4j.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.xml.DOMConfigurator;

public class JMSSink implements MessageListener {
   static Class class$org$apache$log4j$net$JMSSink;
   static Logger logger;

   static {
      Class var1 = class$org$apache$log4j$net$JMSSink;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.net.JMSSink");
         class$org$apache$log4j$net$JMSSink = var0;
      }

      logger = Logger.getLogger(var0);
   }

   public JMSSink(String var1, String var2, String var3, String var4) {
      try {
         InitialContext var5 = new InitialContext();
         TopicConnection var7 = ((TopicConnectionFactory)lookup(var5, var1)).createTopicConnection(var3, var4);
         var7.start();
         var7.createTopicSession(false, 1).createSubscriber((Topic)var5.lookup(var2)).setMessageListener(this);
      } catch (Exception var6) {
         logger.error("Could not read JMS message.", var6);
      }

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

   protected static Object lookup(Context var0, String var1) throws NamingException {
      try {
         Object var3 = var0.lookup(var1);
         return var3;
      } catch (NameNotFoundException var2) {
         logger.error("Could not find name [" + var1 + "].");
         throw var2;
      }
   }

   public static void main(String[] var0) throws Exception {
      if (var0.length != 5) {
         usage("Wrong number of arguments.");
      }

      String var4 = var0[0];
      String var2 = var0[1];
      String var3 = var0[2];
      String var1 = var0[3];
      String var5 = var0[4];
      if (var5.endsWith(".xml")) {
         new DOMConfigurator();
         DOMConfigurator.configure(var5);
      } else {
         new PropertyConfigurator();
         PropertyConfigurator.configure(var5);
      }

      new JMSSink(var4, var2, var3, var1);
      BufferedReader var6 = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Type \"exit\" to quit JMSSink.");

      while(!var6.readLine().equalsIgnoreCase("exit")) {
      }

      System.out.println("Exiting. Kill the application if it does not exit due to daemon threads.");
   }

   static void usage(String var0) {
      System.err.println(var0);
      PrintStream var2 = System.err;
      StringBuffer var3 = (new StringBuffer()).append("Usage: java ");
      Class var1 = class$org$apache$log4j$net$JMSSink;
      Class var4 = var1;
      if (var1 == null) {
         var4 = class$("org.apache.log4j.net.JMSSink");
         class$org$apache$log4j$net$JMSSink = var4;
      }

      var2.println(var3.append(var4.getName()).append(" TopicConnectionFactoryBindingName TopicBindingName username password configFile").toString());
      System.exit(1);
   }

   public void onMessage(Message var1) {
      try {
         if (var1 instanceof ObjectMessage) {
            LoggingEvent var5 = (LoggingEvent)((ObjectMessage)var1).getObject();
            Logger.getLogger(var5.getLoggerName()).callAppenders(var5);
         } else {
            Logger var2 = logger;
            StringBuffer var3 = new StringBuffer();
            var2.warn(var3.append("Received message is of type ").append(var1.getJMSType()).append(", was expecting ObjectMessage.").toString());
         }
      } catch (JMSException var4) {
         logger.error("Exception thrown while processing incoming message.", var4);
      }

   }
}
