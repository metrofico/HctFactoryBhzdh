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
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.OptionHandler;

public class LayoutDynamicMBean extends AbstractDynamicMBean {
   private static Logger cat;
   static Class class$java$lang$String;
   static Class class$org$apache$log4j$Level;
   static Class class$org$apache$log4j$Priority;
   static Class class$org$apache$log4j$jmx$LayoutDynamicMBean;
   private Vector dAttributes = new Vector();
   private String dClassName = this.getClass().getName();
   private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
   private String dDescription = "This MBean acts as a management facade for log4j layouts.";
   private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[1];
   private Hashtable dynamicProps = new Hashtable(5);
   private Layout layout;

   static {
      Class var1 = class$org$apache$log4j$jmx$LayoutDynamicMBean;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.jmx.LayoutDynamicMBean");
         class$org$apache$log4j$jmx$LayoutDynamicMBean = var0;
      }

      cat = Logger.getLogger(var0);
   }

   public LayoutDynamicMBean(Layout var1) throws IntrospectionException {
      this.layout = var1;
      this.buildDynamicMBeanInfo();
   }

   private void buildDynamicMBeanInfo() throws IntrospectionException {
      Constructor[] var4 = this.getClass().getConstructors();
      this.dConstructors[0] = new MBeanConstructorInfo("LayoutDynamicMBean(): Constructs a LayoutDynamicMBean instance", var4[0]);
      PropertyDescriptor[] var7 = Introspector.getBeanInfo(this.layout.getClass()).getPropertyDescriptors();
      int var2 = var7.length;

      for(int var1 = 0; var1 < var2; ++var1) {
         String var9 = var7[var1].getName();
         Method var8 = var7[var1].getReadMethod();
         Method var6 = var7[var1].getWriteMethod();
         if (var8 != null) {
            Class var10 = var8.getReturnType();
            if (this.isSupportedType(var10)) {
               Class var5 = class$org$apache$log4j$Level;
               Class var11 = var5;
               if (var5 == null) {
                  var11 = class$("org.apache.log4j.Level");
                  class$org$apache$log4j$Level = var11;
               }

               String var12;
               if (var10.isAssignableFrom(var11)) {
                  var12 = "java.lang.String";
               } else {
                  var12 = var10.getName();
               }

               Vector var13 = this.dAttributes;
               boolean var3;
               if (var6 != null) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var13.add(new MBeanAttributeInfo(var9, var12, "Dynamic", true, var3, false));
               this.dynamicProps.put(var9, new MethodUnion(var8, var6));
            }
         }
      }

      this.dOperations[0] = new MBeanOperationInfo("activateOptions", "activateOptions(): add an layout", new MBeanParameterInfo[0], "void", 1);
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
            var3 = class$org$apache$log4j$Level;
            var2 = var3;
            if (var3 == null) {
               var2 = class$("org.apache.log4j.Level");
               class$org$apache$log4j$Level = var2;
            }

            return var1.isAssignableFrom(var2);
         }
      }
   }

   public Object getAttribute(String var1) throws AttributeNotFoundException, MBeanException, ReflectionException {
      if (var1 == null) {
         throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke a getter of " + this.dClassName + " with null attribute name");
      } else {
         MethodUnion var2 = (MethodUnion)this.dynamicProps.get(var1);
         cat.debug("----name=" + var1 + ", mu=" + var2);
         if (var2 != null && var2.readMethod != null) {
            Object var4 = null;

            Object var5;
            try {
               var5 = var2.readMethod.invoke(this.layout, (Object[])null);
            } catch (Exception var3) {
               return var4;
            }

            var4 = var5;
            return var4;
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
         Layout var4 = this.layout;
         if (var4 instanceof OptionHandler) {
            var4.activateOptions();
            return "Options activated.";
         }
      }

      return null;
   }

   public void setAttribute(Attribute var1) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
      if (var1 != null) {
         String var5 = var1.getName();
         Object var3 = var1.getValue();
         if (var5 != null) {
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
                  var4.writeMethod.invoke(this.layout, var9);
               } catch (Exception var7) {
                  cat.error("FIXME", var7);
               }

            } else {
               throw new AttributeNotFoundException("Attribute " + var5 + " not found in " + this.getClass().getName());
            }
         } else {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke the setter of " + this.dClassName + " with null attribute name");
         }
      } else {
         throw new RuntimeOperationsException(new IllegalArgumentException("Attribute cannot be null"), "Cannot invoke a setter of " + this.dClassName + " with null attribute");
      }
   }
}
