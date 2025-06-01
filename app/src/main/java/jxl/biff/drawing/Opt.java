package jxl.biff.drawing;

import java.util.ArrayList;
import java.util.Iterator;
import jxl.biff.IntegerHelper;
import jxl.biff.StringHelper;
import jxl.common.Logger;

class Opt extends EscherAtom {
   private static Logger logger = Logger.getLogger(Opt.class);
   private byte[] data;
   private int numProperties;
   private ArrayList properties;

   public Opt() {
      super(EscherRecordType.OPT);
      this.properties = new ArrayList();
      this.setVersion(3);
   }

   public Opt(EscherRecordData var1) {
      super(var1);
      this.numProperties = this.getInstance();
      this.readProperties();
   }

   private void readProperties() {
      this.properties = new ArrayList();
      byte[] var7 = this.getBytes();
      int var2 = 0;

      int var1;
      for(var1 = 0; var2 < this.numProperties; ++var2) {
         int var3 = IntegerHelper.getInt(var7[var1], var7[var1 + 1]);
         int var4 = IntegerHelper.getInt(var7[var1 + 2], var7[var1 + 3], var7[var1 + 4], var7[var1 + 5]);
         boolean var6 = true;
         boolean var5;
         if ((var3 & 16384) != 0) {
            var5 = true;
         } else {
            var5 = false;
         }

         if ((var3 & '耀') == 0) {
            var6 = false;
         }

         Property var8 = new Property(var3 & 16383, var5, var6, var4);
         var1 += 6;
         this.properties.add(var8);
      }

      Iterator var10 = this.properties.iterator();

      while(var10.hasNext()) {
         Property var9 = (Property)var10.next();
         if (var9.complex) {
            var9.stringValue = StringHelper.getUnicodeString(var7, var9.value / 2, var1);
            var1 += var9.value;
         }
      }

   }

   void addProperty(int var1, boolean var2, boolean var3, int var4) {
      Property var5 = new Property(var1, var2, var3, var4);
      this.properties.add(var5);
   }

   void addProperty(int var1, boolean var2, boolean var3, int var4, String var5) {
      Property var6 = new Property(var1, var2, var3, var4, var5);
      this.properties.add(var6);
   }

   byte[] getData() {
      int var1 = this.properties.size();
      this.numProperties = var1;
      this.setInstance(var1);
      this.data = new byte[this.numProperties * 6];
      Iterator var4 = this.properties.iterator();

      for(int var2 = 0; var4.hasNext(); var2 += 6) {
         Property var5 = (Property)var4.next();
         int var3 = var5.id & 16383;
         var1 = var3;
         if (var5.blipId) {
            var1 = var3 | 16384;
         }

         var3 = var1;
         if (var5.complex) {
            var3 = var1 | '耀';
         }

         IntegerHelper.getTwoBytes(var3, this.data, var2);
         IntegerHelper.getFourBytes(var5.value, this.data, var2 + 2);
      }

      Iterator var9 = this.properties.iterator();

      while(var9.hasNext()) {
         Property var6 = (Property)var9.next();
         if (var6.complex && var6.stringValue != null) {
            byte[] var7 = new byte[this.data.length + var6.stringValue.length() * 2];
            byte[] var8 = this.data;
            System.arraycopy(var8, 0, var7, 0, var8.length);
            StringHelper.getUnicodeBytes(var6.stringValue, var7, this.data.length);
            this.data = var7;
         }
      }

      return this.setHeaderData(this.data);
   }

   Property getProperty(int var1) {
      Iterator var6 = this.properties.iterator();
      Property var4 = null;
      boolean var2 = false;
      Property var3 = null;

      while(var6.hasNext() && !var2) {
         Property var5 = (Property)var6.next();
         var3 = var5;
         if (var5.id == var1) {
            var2 = true;
            var3 = var5;
         }
      }

      if (var2) {
         var4 = var3;
      }

      return var4;
   }

   static final class Property {
      boolean blipId;
      boolean complex;
      int id;
      String stringValue;
      int value;

      public Property(int var1, boolean var2, boolean var3, int var4) {
         this.id = var1;
         this.blipId = var2;
         this.complex = var3;
         this.value = var4;
      }

      public Property(int var1, boolean var2, boolean var3, int var4, String var5) {
         this.id = var1;
         this.blipId = var2;
         this.complex = var3;
         this.value = var4;
         this.stringValue = var5;
      }
   }
}
