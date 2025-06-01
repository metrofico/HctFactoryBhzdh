package jxl.write.biff;

import java.util.ArrayList;
import java.util.Iterator;
import jxl.biff.IntegerHelper;
import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class SSTContinueRecord extends WritableRecordData {
   private static int maxBytes;
   private int byteCount = 0;
   private byte[] data;
   private String firstString;
   private int firstStringLength;
   private boolean includeLength;
   private ArrayList stringLengths = new ArrayList(50);
   private ArrayList strings = new ArrayList(50);

   public SSTContinueRecord() {
      super(Type.CONTINUE);
   }

   public int add(String var1) {
      int var3 = var1.length() * 2 + 3;
      if (this.byteCount >= maxBytes - 5) {
         return var1.length();
      } else {
         this.stringLengths.add(new Integer(var1.length()));
         int var2 = this.byteCount;
         int var4 = maxBytes;
         if (var3 + var2 < var4) {
            this.strings.add(var1);
            this.byteCount += var3;
            return 0;
         } else {
            var2 = var4 - 3 - var2;
            if (var2 % 2 != 0) {
               --var2;
            }

            var2 /= 2;
            this.strings.add(var1.substring(0, var2));
            this.byteCount += var2 * 2 + 3;
            return var1.length() - var2;
         }
      }
   }

   public byte[] getData() {
      byte[] var5 = new byte[this.byteCount];
      this.data = var5;
      boolean var4 = this.includeLength;
      int var2 = 0;
      int var1;
      if (var4) {
         IntegerHelper.getTwoBytes(this.firstStringLength, var5, 0);
         this.data[2] = 1;
         var1 = 3;
      } else {
         var5[0] = 1;
         var1 = 1;
      }

      StringHelper.getUnicodeBytes(this.firstString, this.data, var1);
      int var3 = var1 + this.firstString.length() * 2;
      Iterator var6 = this.strings.iterator();
      var1 = var2;

      for(var2 = var3; var6.hasNext(); ++var1) {
         String var8 = (String)var6.next();
         IntegerHelper.getTwoBytes((Integer)this.stringLengths.get(var1), this.data, var2);
         byte[] var7 = this.data;
         var7[var2 + 2] = 1;
         StringHelper.getUnicodeBytes(var8, var7, var2 + 3);
         var2 += var8.length() * 2 + 3;
      }

      return this.data;
   }

   public int getOffset() {
      return this.byteCount;
   }

   public int setFirstString(String var1, boolean var2) {
      this.includeLength = var2;
      this.firstStringLength = var1.length();
      int var3;
      if (!this.includeLength) {
         var3 = var1.length() * 2 + 1;
      } else {
         var3 = var1.length() * 2 + 3;
      }

      int var4 = maxBytes;
      if (var3 <= var4) {
         this.firstString = var1;
         this.byteCount += var3;
         return 0;
      } else {
         if (this.includeLength) {
            var3 = var4 - 4;
         } else {
            var3 = var4 - 2;
         }

         var3 /= 2;
         this.firstString = var1.substring(0, var3);
         this.byteCount = maxBytes - 1;
         return var1.length() - var3;
      }
   }
}
