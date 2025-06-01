package com.hzbhd.commontools;

public class ComponentName {
   private String className;
   private String packageName;

   public ComponentName(String var1, String var2) {
      this.packageName = var1;
      this.className = var2;
   }

   public String getClassName() {
      return this.className;
   }

   public String getPackageName() {
      return this.packageName;
   }

   public void setClassName(String var1) {
      this.className = var1;
   }

   public void setPackageName(String var1) {
      this.packageName = var1;
   }
}
