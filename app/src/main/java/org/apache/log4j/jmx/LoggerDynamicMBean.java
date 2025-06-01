package org.apache.log4j.jmx;

import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.Vector;
import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;

public class LoggerDynamicMBean extends AbstractDynamicMBean implements NotificationListener {
   private static Logger cat;
   static Class class$org$apache$log4j$Appender;
   static Class class$org$apache$log4j$jmx$LoggerDynamicMBean;
   private Vector dAttributes = new Vector();
   private String dClassName = this.getClass().getName();
   private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
   private String dDescription = "This MBean acts as a management facade for a org.apache.log4j.Logger instance.";
   private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[1];
   private Logger logger;

   static {
      Class var1 = class$org$apache$log4j$jmx$LoggerDynamicMBean;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.jmx.LoggerDynamicMBean");
         class$org$apache$log4j$jmx$LoggerDynamicMBean = var0;
      }

      cat = Logger.getLogger(var0);
   }

   public LoggerDynamicMBean(Logger var1) {
      this.logger = var1;
      this.buildDynamicMBeanInfo();
   }

   private void buildDynamicMBeanInfo() {
      Constructor[] var1 = this.getClass().getConstructors();
      this.dConstructors[0] = new MBeanConstructorInfo("HierarchyDynamicMBean(): Constructs a HierarchyDynamicMBean instance", var1[0]);
      this.dAttributes.add(new MBeanAttributeInfo("name", "java.lang.String", "The name of this Logger.", true, false, false));
      this.dAttributes.add(new MBeanAttributeInfo("priority", "java.lang.String", "The priority of this logger.", true, true, false));
      MBeanParameterInfo var3 = new MBeanParameterInfo("class name", "java.lang.String", "add an appender to this logger");
      MBeanParameterInfo var2 = new MBeanParameterInfo("appender name", "java.lang.String", "name of the appender");
      this.dOperations[0] = new MBeanOperationInfo("addAppender", "addAppender(): add an appender", new MBeanParameterInfo[]{var3, var2}, "void", 1);
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

   void addAppender(String var1, String var2) {
      cat.debug("addAppender called with " + var1 + ", " + var2);
      Class var4 = class$org$apache$log4j$Appender;
      Class var3 = var4;
      if (var4 == null) {
         var3 = class$("org.apache.log4j.Appender");
         class$org$apache$log4j$Appender = var3;
      }

      Appender var5 = (Appender)OptionConverter.instantiateByClassName(var1, var3, (Object)null);
      var5.setName(var2);
      this.logger.addAppender(var5);
   }

   void appenderMBeanRegistration() {
      Enumeration var1 = this.logger.getAllAppenders();

      while(var1.hasMoreElements()) {
         this.registerAppenderMBean((Appender)var1.nextElement());
      }

   }

   public Object getAttribute(String var1) throws AttributeNotFoundException, MBeanException, ReflectionException {
      if (var1 == null) {
         throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke a getter of " + this.dClassName + " with null attribute name");
      } else if (var1.equals("name")) {
         return this.logger.getName();
      } else if (var1.equals("priority")) {
         Level var4 = this.logger.getLevel();
         return var4 == null ? null : var4.toString();
      } else {
         if (var1.startsWith("appender=")) {
            try {
               StringBuffer var2 = new StringBuffer();
               ObjectName var5 = new ObjectName(var2.append("log4j:").append(var1).toString());
               return var5;
            } catch (Exception var3) {
               cat.error("Could not create ObjectName" + var1);
            }
         }

         throw new AttributeNotFoundException("Cannot find " + var1 + " attribute in " + this.dClassName);
      }
   }

   protected Logger getLogger() {
      return this.logger;
   }

   public MBeanInfo getMBeanInfo() {
      MBeanAttributeInfo[] var1 = new MBeanAttributeInfo[this.dAttributes.size()];
      this.dAttributes.toArray(var1);
      return new MBeanInfo(this.dClassName, this.dDescription, var1, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
   }

   public void handleNotification(Notification var1, Object var2) {
      cat.debug("Received notification: " + var1.getType());
      this.registerAppenderMBean((Appender)var1.getUserData());
   }

   public Object invoke(String var1, Object[] var2, String[] var3) throws MBeanException, ReflectionException {
      if (var1.equals("addAppender")) {
         this.addAppender((String)var2[0], (String)var2[1]);
         return "Hello world.";
      } else {
         return null;
      }
   }

   public void postRegister(Boolean var1) {
      this.appenderMBeanRegistration();
   }

   void registerAppenderMBean(Appender var1) {
      String var2 = var1.getName();
      cat.debug("Adding AppenderMBean for appender named " + var2);

      try {
         AppenderDynamicMBean var3 = new AppenderDynamicMBean(var1);
         ObjectName var7 = new ObjectName("log4j", "appender", var2);
         super.server.registerMBean(var3, var7);
         Vector var9 = this.dAttributes;
         StringBuffer var4 = new StringBuffer();
         String var5 = var4.append("appender=").append(var2).toString();
         var4 = new StringBuffer();
         MBeanAttributeInfo var8 = new MBeanAttributeInfo(var5, "javax.management.ObjectName", var4.append("The ").append(var2).append(" appender.").toString(), true, true, false);
         var9.add(var8);
      } catch (Exception var6) {
         cat.error("Could not add appenderMBean for [" + var2 + "].", var6);
      }

   }

   public void setAttribute(Attribute var1) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
      if (var1 != null) {
         String var2 = var1.getName();
         Object var3 = var1.getValue();
         if (var2 != null) {
            if (var2.equals("priority")) {
               if (var3 instanceof String) {
                  var2 = (String)var3;
                  Level var4 = this.logger.getLevel();
                  if (var2.equalsIgnoreCase("NULL")) {
                     var4 = null;
                  } else {
                     var4 = OptionConverter.toLevel(var2, var4);
                  }

                  this.logger.setLevel(var4);
               }

            } else {
               throw new AttributeNotFoundException("Attribute " + var2 + " not found in " + this.getClass().getName());
            }
         } else {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke the setter of " + this.dClassName + " with null attribute name");
         }
      } else {
         throw new RuntimeOperationsException(new IllegalArgumentException("Attribute cannot be null"), "Cannot invoke a setter of " + this.dClassName + " with null attribute");
      }
   }
}
