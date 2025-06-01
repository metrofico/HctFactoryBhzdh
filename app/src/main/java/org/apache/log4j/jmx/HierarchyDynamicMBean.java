package org.apache.log4j.jmx;

import java.lang.reflect.Constructor;
import java.util.Vector;
import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.InvalidAttributeValueException;
import javax.management.ListenerNotFoundException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcaster;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationFilter;
import javax.management.NotificationFilterSupport;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.LoggerRepository;

public class HierarchyDynamicMBean extends AbstractDynamicMBean implements HierarchyEventListener, NotificationBroadcaster {
   static final String ADD_APPENDER = "addAppender.";
   static final String THRESHOLD = "threshold";
   static Class class$org$apache$log4j$jmx$HierarchyDynamicMBean;
   private static Logger log;
   private String dClassName = this.getClass().getName();
   private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
   private String dDescription = "This MBean acts as a management facade for org.apache.log4j.Hierarchy.";
   private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[1];
   private LoggerRepository hierarchy = LogManager.getLoggerRepository();
   private NotificationBroadcasterSupport nbs = new NotificationBroadcasterSupport();
   private Vector vAttributes = new Vector();

   static {
      Class var1 = class$org$apache$log4j$jmx$HierarchyDynamicMBean;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.jmx.HierarchyDynamicMBean");
         class$org$apache$log4j$jmx$HierarchyDynamicMBean = var0;
      }

      log = Logger.getLogger(var0);
   }

   public HierarchyDynamicMBean() {
      this.buildDynamicMBeanInfo();
   }

   private void buildDynamicMBeanInfo() {
      Constructor[] var1 = this.getClass().getConstructors();
      this.dConstructors[0] = new MBeanConstructorInfo("HierarchyDynamicMBean(): Constructs a HierarchyDynamicMBean instance", var1[0]);
      this.vAttributes.add(new MBeanAttributeInfo("threshold", "java.lang.String", "The \"threshold\" state of the hiearchy.", true, true, false));
      MBeanParameterInfo var2 = new MBeanParameterInfo("name", "java.lang.String", "Create a logger MBean");
      this.dOperations[0] = new MBeanOperationInfo("addLoggerMBean", "addLoggerMBean(): add a loggerMBean", new MBeanParameterInfo[]{var2}, "javax.management.ObjectName", 1);
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

   public void addAppenderEvent(Category var1, Appender var2) {
      log.debug("addAppenderEvent called: logger=" + var1.getName() + ", appender=" + var2.getName());
      Notification var3 = new Notification("addAppender." + var1.getName(), this, 0L);
      var3.setUserData(var2);
      log.debug("sending notification.");
      this.nbs.sendNotification(var3);
   }

   public ObjectName addLoggerMBean(String var1) {
      Logger var2 = LogManager.exists(var1);
      return var2 != null ? this.addLoggerMBean(var2) : null;
   }

   ObjectName addLoggerMBean(Logger var1) {
      String var5 = var1.getName();
      NotificationFilterSupport var4 = null;

      ObjectName var2;
      Exception var3;
      ObjectName var9;
      label20: {
         LoggerDynamicMBean var11;
         try {
            var11 = new LoggerDynamicMBean(var1);
            var2 = new ObjectName("log4j", "logger", var5);
         } catch (Exception var8) {
            var3 = var8;
            var9 = var4;
            break label20;
         }

         try {
            super.server.registerMBean(var11, var2);
            var4 = new NotificationFilterSupport();
            StringBuffer var6 = new StringBuffer();
            var4.enableType(var6.append("addAppender.").append(var1.getName()).toString());
            var1 = log;
            var6 = new StringBuffer();
            var1.debug(var6.append("---Adding logger [").append(var5).append("] as listener.").toString());
            this.nbs.addNotificationListener(var11, var4, (Object)null);
            Vector var12 = this.vAttributes;
            StringBuffer var13 = new StringBuffer();
            String var14 = var13.append("logger=").append(var5).toString();
            var6 = new StringBuffer();
            MBeanAttributeInfo var10 = new MBeanAttributeInfo(var14, "javax.management.ObjectName", var6.append("The ").append(var5).append(" logger.").toString(), true, true, false);
            var12.add(var10);
            return var2;
         } catch (Exception var7) {
            var3 = var7;
            var9 = var2;
         }
      }

      log.error("Could not add loggerMBean for [" + var5 + "].", var3);
      var2 = var9;
      return var2;
   }

   public void addNotificationListener(NotificationListener var1, NotificationFilter var2, Object var3) {
      this.nbs.addNotificationListener(var1, var2, var3);
   }

   public Object getAttribute(String var1) throws AttributeNotFoundException, MBeanException, ReflectionException {
      if (var1 != null) {
         log.debug("Called getAttribute with [" + var1 + "].");
         if (var1.equals("threshold")) {
            return this.hierarchy.getThreshold();
         } else {
            if (var1.startsWith("logger")) {
               int var2 = var1.indexOf("%3D");
               String var3;
               if (var2 > 0) {
                  var3 = var1.substring(0, var2) + '=' + var1.substring(var2 + 3);
               } else {
                  var3 = var1;
               }

               try {
                  StringBuffer var4 = new StringBuffer();
                  ObjectName var6 = new ObjectName(var4.append("log4j:").append(var3).toString());
                  return var6;
               } catch (Exception var5) {
                  log.error("Could not create ObjectName" + var3);
               }
            }

            throw new AttributeNotFoundException("Cannot find " + var1 + " attribute in " + this.dClassName);
         }
      } else {
         throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke a getter of " + this.dClassName + " with null attribute name");
      }
   }

   protected Logger getLogger() {
      return log;
   }

   public MBeanInfo getMBeanInfo() {
      MBeanAttributeInfo[] var1 = new MBeanAttributeInfo[this.vAttributes.size()];
      this.vAttributes.toArray(var1);
      return new MBeanInfo(this.dClassName, this.dDescription, var1, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
   }

   public MBeanNotificationInfo[] getNotificationInfo() {
      return this.nbs.getNotificationInfo();
   }

   public Object invoke(String var1, Object[] var2, String[] var3) throws MBeanException, ReflectionException {
      if (var1 != null) {
         if (var1.equals("addLoggerMBean")) {
            return this.addLoggerMBean((String)var2[0]);
         } else {
            throw new ReflectionException(new NoSuchMethodException(var1), "Cannot find the operation " + var1 + " in " + this.dClassName);
         }
      } else {
         throw new RuntimeOperationsException(new IllegalArgumentException("Operation name cannot be null"), "Cannot invoke a null operation in " + this.dClassName);
      }
   }

   public void postRegister(Boolean var1) {
      log.debug("postRegister is called.");
      this.hierarchy.addHierarchyEventListener(this);
      this.addLoggerMBean(this.hierarchy.getRootLogger());
   }

   public void removeAppenderEvent(Category var1, Appender var2) {
      log.debug("removeAppenderCalled: logger=" + var1.getName() + ", appender=" + var2.getName());
   }

   public void removeNotificationListener(NotificationListener var1) throws ListenerNotFoundException {
      this.nbs.removeNotificationListener(var1);
   }

   public void setAttribute(Attribute var1) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
      if (var1 != null) {
         String var2 = var1.getName();
         Object var3 = var1.getValue();
         if (var2 != null) {
            if (var2.equals("threshold")) {
               Level var4 = OptionConverter.toLevel((String)var3, this.hierarchy.getThreshold());
               this.hierarchy.setThreshold(var4);
            }

         } else {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke the setter of " + this.dClassName + " with null attribute name");
         }
      } else {
         throw new RuntimeOperationsException(new IllegalArgumentException("Attribute cannot be null"), "Cannot invoke a setter of " + this.dClassName + " with null attribute");
      }
   }
}
