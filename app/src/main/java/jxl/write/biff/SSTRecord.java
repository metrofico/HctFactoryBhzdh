package jxl.write.biff;

import java.util.ArrayList;
import java.util.Iterator;
import jxl.biff.IntegerHelper;
import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class SSTRecord extends WritableRecordData {
   private static int maxBytes;
   private int byteCount;
   private byte[] data;
   private int numReferences;
   private int numStrings;
   private ArrayList stringLengths;
   private ArrayList strings;

   public SSTRecord(int var1, int var2) {
      super(Type.SST);
      this.numReferences = var1;
      this.numStrings = var2;
      this.byteCount = 0;
      this.strings = new ArrayList(50);
      this.stringLengths = new ArrayList(50);
   }

   public int add(String var1) {
      int var2 = var1.length() * 2 + 3;
      if (this.byteCount >= maxBytes - 5) {
         if (var1.length() > 0) {
            var2 = var1.length();
         } else {
            var2 = -1;
         }

         return var2;
      } else {
         this.stringLengths.add(new Integer(var1.length()));
         int var4 = this.byteCount;
         int var3 = maxBytes;
         if (var2 + var4 < var3) {
            this.strings.add(var1);
            this.byteCount += var2;
            return 0;
         } else {
            var2 = var3 - 3 - var4;
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
      int var1 = this.byteCount;
      int var2 = 8;
      byte[] var4 = new byte[var1 + 8];
      this.data = var4;
      int var3 = this.numReferences;
      var1 = 0;
      IntegerHelper.getFourBytes(var3, var4, 0);
      IntegerHelper.getFourBytes(this.numStrings, this.data, 4);

      for(Iterator var6 = this.strings.iterator(); var6.hasNext(); ++var1) {
         String var7 = (String)var6.next();
         IntegerHelper.getTwoBytes((Integer)this.stringLengths.get(var1), this.data, var2);
         byte[] var5 = this.data;
         var5[var2 + 2] = 1;
         StringHelper.getUnicodeBytes(var7, var5, var2 + 3);
         var2 += var7.length() * 2 + 3;
      }

      return this.data;
   }

   public int getOffset() {
      return this.byteCount + 8;
   }
}
