package org.apache.log4j.spi;

import java.io.PrintWriter;
import java.util.Vector;

class VectorWriter extends PrintWriter {
   private Vector v = new Vector();

   VectorWriter() {
      super(new NullWriter());
   }

   public void print(Object var1) {
      this.v.addElement(var1.toString());
   }

   public void print(String var1) {
      this.v.addElement(var1);
   }

   public void print(char[] var1) {
      this.v.addElement(new String(var1));
   }

   public void println(Object var1) {
      this.v.addElement(var1.toString());
   }

   public void println(String var1) {
      this.v.addElement(var1);
   }

   public void println(char[] var1) {
      this.v.addElement(new String(var1));
   }

   public String[] toStringArray() {
      int var2 = this.v.size();
      String[] var3 = new String[var2];

      for(int var1 = 0; var1 < var2; ++var1) {
         var3[var1] = (String)this.v.elementAt(var1);
      }

      return var3;
   }

   public void write(String var1) {
      this.v.addElement(var1);
   }

   public void write(String var1, int var2, int var3) {
      this.v.addElement(var1.substring(var2, var3 + var2));
   }

   public void write(char[] var1) {
      this.v.addElement(new String(var1));
   }

   public void write(char[] var1, int var2, int var3) {
      this.v.addElement(new String(var1, var2, var3));
   }
}
