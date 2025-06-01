package jxl.read.biff;

import jxl.WorkbookSettings;
import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.biff.StringHelper;
import jxl.common.Assert;

class SSTRecord extends RecordData {
   private int[] continuationBreaks;
   private String[] strings;
   private int totalStrings;
   private int uniqueStrings;

   public SSTRecord(Record var1, Record[] var2, WorkbookSettings var3) {
      super(var1);
      int var5 = 0;

      int var4;
      for(var4 = 0; var5 < var2.length; ++var5) {
         var4 += var2[var5].getLength();
      }

      byte[] var7 = new byte[var4 + this.getRecord().getLength()];
      System.arraycopy(this.getRecord().getData(), 0, var7, 0, this.getRecord().getLength());
      var5 = this.getRecord().getLength() + 0;
      this.continuationBreaks = new int[var2.length];

      for(var4 = 0; var4 < var2.length; ++var4) {
         Record var6 = var2[var4];
         System.arraycopy(var6.getData(), 0, var7, var5, var6.getLength());
         this.continuationBreaks[var4] = var5;
         var5 += var6.getLength();
      }

      this.totalStrings = IntegerHelper.getInt(var7[0], var7[1], var7[2], var7[3]);
      var4 = IntegerHelper.getInt(var7[4], var7[5], var7[6], var7[7]);
      this.uniqueStrings = var4;
      this.strings = new String[var4];
      this.readStrings(var7, 8, var3);
   }

   private int getChars(byte[] var1, ByteArrayHolder var2, int var3, BooleanHolder var4, int var5) {
      if (var4.value) {
         var2.bytes = new byte[var5];
      } else {
         var2.bytes = new byte[var5 * 2];
      }

      boolean var8 = false;
      int var7 = 0;

      while(true) {
         int[] var9 = this.continuationBreaks;
         if (var7 >= var9.length || var8) {
            if (!var8) {
               System.arraycopy(var1, var3, var2.bytes, 0, var2.bytes.length);
               return var2.bytes.length;
            } else {
               int var10 = var9[var7];
               byte[] var11 = var2.bytes;
               var10 -= var3;
               System.arraycopy(var1, var3, var11, 0, var10);
               if (var4.value) {
                  var3 = var10;
               } else {
                  var3 = var10 / 2;
               }

               return var10 + this.getContinuedString(var1, var2, var10, var7, var4, var5 - var3);
            }
         }

         boolean var6;
         if (var3 <= var9[var7] && var2.bytes.length + var3 > this.continuationBreaks[var7]) {
            var6 = true;
         } else {
            var6 = false;
         }

         var8 = var6;
         if (!var6) {
            ++var7;
            var8 = var6;
         }
      }
   }

   private int getContinuedString(byte[] var1, ByteArrayHolder var2, int var3, int var4, BooleanHolder var5, int var6) {
      int var9 = this.continuationBreaks[var4];
      int var11 = 0;
      int var7 = var6;
      int var10 = var4;
      int var8 = var3;

      while(var7 > 0) {
         boolean var13;
         if (var10 < this.continuationBreaks.length) {
            var13 = true;
         } else {
            var13 = false;
         }

         Assert.verify(var13, "continuation break index");
         int var12;
         int[] var15;
         if (var5.value && var1[var9] == 0) {
            var15 = this.continuationBreaks;
            if (var10 == var15.length - 1) {
               var3 = var7;
            } else {
               var3 = Math.min(var7, var15[var10 + 1] - var9 - 1);
            }

            System.arraycopy(var1, var9 + 1, var2.bytes, var8, var3);
            var4 = var8 + var3;
            var6 = var11 + var3 + 1;
            var7 -= var3;
            var5.value = true;
            var3 = var4;
            var4 = var7;
         } else if (!var5.value && var1[var9] != 0) {
            var15 = this.continuationBreaks;
            if (var10 == var15.length - 1) {
               var3 = var7 * 2;
            } else {
               var3 = Math.min(var7 * 2, var15[var10 + 1] - var9 - 1);
            }

            System.arraycopy(var1, var9 + 1, var2.bytes, var8, var3);
            var4 = var8 + var3;
            var6 = var11 + var3 + 1;
            var7 -= var3 / 2;
            var5.value = false;
            var3 = var4;
            var4 = var7;
         } else if (!var5.value && var1[var9] == 0) {
            var15 = this.continuationBreaks;
            if (var10 == var15.length - 1) {
               var4 = var7;
            } else {
               var4 = Math.min(var7, var15[var10 + 1] - var9 - 1);
            }

            var6 = 0;

            for(var3 = var8; var6 < var4; ++var6) {
               var2.bytes[var3] = var1[var9 + var6 + 1];
               var3 += 2;
            }

            var6 = var11 + var4 + 1;
            var4 = var7 - var4;
            var5.value = false;
         } else {
            byte[] var14 = var2.bytes;
            var12 = var8 * 2;
            var4 = var7 * 2;
            var2.bytes = new byte[var12 + var4];

            for(var3 = 0; var3 < var8; ++var3) {
               var2.bytes[var3 * 2] = var14[var3];
            }

            var15 = this.continuationBreaks;
            if (var10 == var15.length - 1) {
               var3 = var4;
            } else {
               var3 = Math.min(var4, var15[var10 + 1] - var9 - 1);
            }

            System.arraycopy(var1, var9 + 1, var2.bytes, var12, var3);
            var6 = var11 + var3 + 1;
            var4 = var7 - var3 / 2;
            var5.value = false;
            var3 += var12;
         }

         var12 = var10 + 1;
         var15 = this.continuationBreaks;
         var8 = var3;
         var10 = var12;
         var7 = var4;
         var11 = var6;
         if (var12 < var15.length) {
            var9 = var15[var12];
            var8 = var3;
            var10 = var12;
            var7 = var4;
            var11 = var6;
         }
      }

      return var11;
   }

   private void readStrings(byte[] var1, int var2, WorkbookSettings var3) {
      int var5 = var2;
      int var4 = 0;
      var2 = 0;

      for(int var7 = 0; var7 < this.uniqueStrings; ++var7) {
         int var10 = IntegerHelper.getInt(var1[var5], var1[var5 + 1]);
         var5 += 2;
         byte var11 = var1[var5];
         boolean var12 = true;
         int var9 = var5 + 1;
         boolean var8;
         if ((var11 & 4) != 0) {
            var8 = true;
         } else {
            var8 = false;
         }

         boolean var6;
         if ((var11 & 8) != 0) {
            var6 = true;
         } else {
            var6 = false;
         }

         var5 = var9;
         if (var6) {
            var4 = IntegerHelper.getInt(var1[var9], var1[var9 + 1]);
            var5 = var9 + 2;
         }

         var9 = var5;
         if (var8) {
            var2 = IntegerHelper.getInt(var1[var5], var1[var5 + 1], var1[var5 + 2], var1[var5 + 3]);
            var9 = var5 + 4;
         }

         if ((var11 & 1) != 0) {
            var12 = false;
         }

         ByteArrayHolder var13 = new ByteArrayHolder();
         BooleanHolder var14 = new BooleanHolder();
         var14.value = var12;
         var9 += this.getChars(var1, var13, var9, var14, var10);
         String var16;
         if (var14.value) {
            var16 = StringHelper.getString(var13.bytes, var10, 0, var3);
         } else {
            var16 = StringHelper.getUnicodeString(var13.bytes, var10, 0);
         }

         this.strings[var7] = var16;
         var5 = var9;
         if (var6) {
            var5 = var9 + var4 * 4;
         }

         int var15 = var5;
         if (var8) {
            var15 = var5 + var2;
         }

         var5 = var15;
         if (var15 > var1.length) {
            Assert.verify(false, "pos exceeds record length");
         }
      }

   }

   public String getString(int var1) {
      boolean var2;
      if (var1 < this.uniqueStrings) {
         var2 = true;
      } else {
         var2 = false;
      }

      Assert.verify(var2);
      return this.strings[var1];
   }

   private static class BooleanHolder {
      public boolean value;

      private BooleanHolder() {
      }

      // $FF: synthetic method
      BooleanHolder(Object var1) {
         this();
      }
   }

   private static class ByteArrayHolder {
      public byte[] bytes;

      private ByteArrayHolder() {
      }

      // $FF: synthetic method
      ByteArrayHolder(Object var1) {
         this();
      }
   }
}
