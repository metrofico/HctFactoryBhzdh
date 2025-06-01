package org.apache.log4j.jmx;

import java.util.Iterator;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanRegistration;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Logger;

public abstract class AbstractDynamicMBean implements DynamicMBean, MBeanRegistration {
   String dClassName;
   MBeanServer server;

   public abstract Object getAttribute(String var1) throws AttributeNotFoundException, MBeanException, ReflectionException;

   public AttributeList getAttributes(String[] var1) {
      if (var1 == null) {
         throw new RuntimeOperationsException(new IllegalArgumentException("attributeNames[] cannot be null"), "Cannot invoke a getter of " + this.dClassName);
      } else {
         AttributeList var3 = new AttributeList();
         if (var1.length == 0) {
            return var3;
         } else {
            for(int var2 = 0; var2 < var1.length; ++var2) {
               try {
                  Object var4 = this.getAttribute(var1[var2]);
                  Attribute var5 = new Attribute(var1[var2], var4);
                  var3.add(var5);
               } catch (Exception var6) {
                  var6.printStackTrace();
               }
            }

            return var3;
         }
      }
   }

   protected abstract Logger getLogger();

   public abstract MBeanInfo getMBeanInfo();

   public abstract Object invoke(String var1, Object[] var2, String[] var3) throws MBeanException, ReflectionException;

   public void postDeregister() {
      this.getLogger().debug("postDeregister is called.");
   }

   public void postRegister(Boolean var1) {
   }

   public void preDeregister() {
      this.getLogger().debug("preDeregister called.");
   }

   public ObjectName preRegister(MBeanServer var1, ObjectName var2) {
      this.getLogger().debug("preRegister called. Server=" + var1 + ", name=" + var2);
      this.server = var1;
      return var2;
   }

   public abstract void setAttribute(Attribute var1) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException;

   public AttributeList setAttributes(AttributeList var1) {
      if (var1 == null) {
         throw new RuntimeOperationsException(new IllegalArgumentException("AttributeList attributes cannot be null"), "Cannot invoke a setter of " + this.dClassName);
      } else {
         AttributeList var2 = new AttributeList();
         if (var1.isEmpty()) {
            return var2;
         } else {
            Iterator var7 = var1.iterator();

            while(var7.hasNext()) {
               Attribute var3 = (Attribute)var7.next();

               try {
                  this.setAttribute(var3);
                  String var5 = var3.getName();
                  Object var8 = this.getAttribute(var5);
                  Attribute var4 = new Attribute(var5, var8);
                  var2.add(var4);
               } catch (Exception var6) {
                  var6.printStackTrace();
               }
            }

            return var2;
         }
      }
   }
}
