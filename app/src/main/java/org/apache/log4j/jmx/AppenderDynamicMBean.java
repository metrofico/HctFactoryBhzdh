package org.apache.log4j.jmx;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Hashtable;
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
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.OptionHandler;

public class AppenderDynamicMBean extends AbstractDynamicMBean {
   private static Logger cat;
   static Class class$java$lang$String;
   static Class class$org$apache$log4j$Layout;
   static Class class$org$apache$log4j$Priority;
   static Class class$org$apache$log4j$jmx$AppenderDynamicMBean;
   private Appender appender;
   private Vector dAttributes = new Vector();
   private String dClassName = this.getClass().getName();
   private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
   private String dDescription = "This MBean acts as a management facade for log4j appenders.";
   private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[2];
   private Hashtable dynamicProps = new Hashtable(5);

   static {
      Class var1 = class$org$apache$log4j$jmx$AppenderDynamicMBean;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.jmx.AppenderDynamicMBean");
         class$org$apache$log4j$jmx$AppenderDynamicMBean = var0;
      }

      cat = Logger.getLogger(var0);
   }

   public AppenderDynamicMBean(Appender var1) throws IntrospectionException {
      this.appender = var1;
      this.buildDynamicMBeanInfo();
   }

   private void buildDynamicMBeanInfo() throws IntrospectionException {
      Constructor[] var4 = this.getClass().getConstructors();
      this.dConstructors[0] = new MBeanConstructorInfo("AppenderDynamicMBean(): Constructs a AppenderDynamicMBean instance", var4[0]);
      PropertyDescriptor[] var7 = Introspector.getBeanInfo(this.appender.getClass()).getPropertyDescriptors();
      int var2 = var7.length;
      int var1 = 0;

      while(true) {
         String var5 = "java.lang.String";
         boolean var3 = true;
         if (var1 >= var2) {
            this.dOperations[0] = new MBeanOperationInfo("activateOptions", "activateOptions(): add an appender", new MBeanParameterInfo[0], "void", 1);
            MBeanParameterInfo var15 = new MBeanParameterInfo("layout class", "java.lang.String", "layout class");
            this.dOperations[1] = new MBeanOperationInfo("setLayout", "setLayout(): add a layout", new MBeanParameterInfo[]{var15}, "void", 1);
            return;
         }

         String var8 = var7[var1].getName();
         Method var10 = var7[var1].getReadMethod();
         Method var9 = var7[var1].getWriteMethod();
         if (var10 != null) {
            Class var11 = var10.getReturnType();
            if (this.isSupportedType(var11)) {
               Class var6 = class$org$apache$log4j$Priority;
               Class var12 = var6;
               if (var6 == null) {
                  var12 = class$("org.apache.log4j.Priority");
                  class$org$apache$log4j$Priority = var12;
               }

               String var13;
               if (var11.isAssignableFrom(var12)) {
                  var13 = var5;
               } else {
                  var13 = var11.getName();
               }

               Vector var14 = this.dAttributes;
               if (var9 == null) {
                  var3 = false;
               }

               var14.add(new MBeanAttributeInfo(var8, var13, "Dynamic", true, var3, false));
               this.dynamicProps.put(var8, new MethodUnion(var10, var9));
            }
         }

         ++var1;
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

   private boolean isSupportedType(Class var1) {
      if (var1.isPrimitive()) {
         return true;
      } else {
         Class var3 = class$java$lang$String;
         Class var2 = var3;
         if (var3 == null) {
            var2 = class$("java.lang.String");
            class$java$lang$String = var2;
         }

         if (var1 == var2) {
            return true;
         } else {
            var3 = class$org$apache$log4j$Priority;
            var2 = var3;
            if (var3 == null) {
               var2 = class$("org.apache.log4j.Priority");
               class$org$apache$log4j$Priority = var2;
            }

            return var1.isAssignableFrom(var2);
         }
      }
   }

   public Object getAttribute(String var1) throws AttributeNotFoundException, MBeanException, ReflectionException {
      if (var1 == null) {
         throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke a getter of " + this.dClassName + " with null attribute name");
      } else {
         cat.debug("getAttribute called with [" + var1 + "].");
         if (var1.startsWith("appender=" + this.appender.getName() + ",layout")) {
            try {
               StringBuffer var7 = new StringBuffer();
               ObjectName var8 = new ObjectName(var7.append("log4j:").append(var1).toString());
               return var8;
            } catch (Exception var4) {
               cat.error("attributeName", var4);
            }
         }

         MethodUnion var2 = (MethodUnion)this.dynamicProps.get(var1);
         if (var2 != null && var2.readMethod != null) {
            Object var5 = null;

            Object var6;
            try {
               var6 = var2.readMethod.invoke(this.appender, (Object[])null);
            } catch (Exception var3) {
               return var5;
            }

            var5 = var6;
            return var5;
         } else {
            throw new AttributeNotFoundException("Cannot find " + var1 + " attribute in " + this.dClassName);
         }
      }
   }

   protected Logger getLogger() {
      return cat;
   }

   public MBeanInfo getMBeanInfo() {
      cat.debug("getMBeanInfo called.");
      MBeanAttributeInfo[] var1 = new MBeanAttributeInfo[this.dAttributes.size()];
      this.dAttributes.toArray(var1);
      return new MBeanInfo(this.dClassName, this.dDescription, var1, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
   }

   public Object invoke(String var1, Object[] var2, String[] var3) throws MBeanException, ReflectionException {
      if (var1.equals("activateOptions")) {
         Appender var7 = this.appender;
         if (var7 instanceof OptionHandler) {
            ((OptionHandler)var7).activateOptions();
            return "Options activated.";
         }
      }

      if (var1.equals("setLayout")) {
         String var8 = (String)var2[0];
         Class var5 = class$org$apache$log4j$Layout;
         Class var4 = var5;
         if (var5 == null) {
            var4 = class$("org.apache.log4j.Layout");
            class$org$apache$log4j$Layout = var4;
         }

         Layout var6 = (Layout)OptionConverter.instantiateByClassName(var8, var4, (Object)null);
         this.appender.setLayout(var6);
         this.registerLayoutMBean(var6);
      }

      return null;
   }

   public ObjectName preRegister(MBeanServer var1, ObjectName var2) {
      cat.debug("preRegister called. Server=" + var1 + ", name=" + var2);
      super.server = var1;
      this.registerLayoutMBean(this.appender.getLayout());
      return var2;
   }

   void registerLayoutMBean(Layout var1) {
      if (var1 != null) {
         String var2 = this.appender.getName() + ",layout=" + var1.getClass().getName();
         cat.debug("Adding LayoutMBean:" + var2);

         try {
            LayoutDynamicMBean var3 = new LayoutDynamicMBean(var1);
            StringBuffer var4 = new StringBuffer();
            ObjectName var7 = new ObjectName(var4.append("log4j:appender=").append(var2).toString());
            super.server.registerMBean(var3, var7);
            Vector var8 = this.dAttributes;
            var4 = new StringBuffer();
            String var5 = var4.append("appender=").append(var2).toString();
            var4 = new StringBuffer();
            MBeanAttributeInfo var9 = new MBeanAttributeInfo(var5, "javax.management.ObjectName", var4.append("The ").append(var2).append(" layout.").toString(), true, true, false);
            var8.add(var9);
         } catch (Exception var6) {
            cat.error("Could not add DynamicLayoutMBean for [" + var2 + "].", var6);
         }

      }
   }

   public void setAttribute(Attribute var1) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
      if (var1 != null) {
         String var5 = var1.getName();
         Object var3 = var1.getValue();
         if (var5 == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke the setter of " + this.dClassName + " with null attribute name");
         } else {
            MethodUnion var4 = (MethodUnion)this.dynamicProps.get(var5);
            if (var4 != null && var4.writeMethod != null) {
               Class var6 = var4.writeMethod.getParameterTypes()[0];
               Class var2 = class$org$apache$log4j$Priority;
               Class var8 = var2;
               if (var2 == null) {
                  var8 = class$("org.apache.log4j.Priority");
                  class$org$apache$log4j$Priority = var8;
               }

               Object var9 = var3;
               if (var6 == var8) {
                  var9 = OptionConverter.toLevel((String)var3, (Level)this.getAttribute(var5));
               }

               try {
                  var4.writeMethod.invoke(this.appender, var9);
               } catch (Exception var7) {
                  cat.error("FIXME", var7);
               }
            } else if (!var5.endsWith(".layout")) {
               throw new AttributeNotFoundException("Attribute " + var5 + " not found in " + this.getClass().getName());
            }

         }
      } else {
         throw new RuntimeOperationsException(new IllegalArgumentException("Attribute cannot be null"), "Cannot invoke a setter of " + this.dClassName + " with null attribute");
      }
   }
}
