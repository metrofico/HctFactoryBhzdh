package jxl.biff;

import jxl.format.Colour;
import jxl.format.RGB;
import jxl.read.biff.Record;

public class PaletteRecord extends WritableRecordData {
   private static final int numColours = 56;
   private boolean dirty;
   private boolean initialized;
   private boolean read;
   private RGB[] rgbColours = new RGB[56];

   public PaletteRecord() {
      super(Type.PALETTE);
      this.initialized = true;
      int var1 = 0;
      this.dirty = false;
      this.read = false;

      for(Colour[] var3 = Colour.getAllColours(); var1 < var3.length; ++var1) {
         Colour var2 = var3[var1];
         this.setColourRGB(var2, var2.getDefaultRGB().getRed(), var2.getDefaultRGB().getGreen(), var2.getDefaultRGB().getBlue());
      }

   }

   public PaletteRecord(Record var1) {
      super(var1);
      this.initialized = false;
      this.dirty = false;
      this.read = true;
   }

   private void initialize() {
      byte[] var6 = this.getRecord().getData();
      int var2 = IntegerHelper.getInt(var6[0], var6[1]);

      for(int var1 = 0; var1 < var2; ++var1) {
         int var5 = var1 * 4 + 2;
         int var4 = IntegerHelper.getInt(var6[var5], (byte)0);
         int var3 = IntegerHelper.getInt(var6[var5 + 1], (byte)0);
         var5 = IntegerHelper.getInt(var6[var5 + 2], (byte)0);
         this.rgbColours[var1] = new RGB(var4, var3, var5);
      }

      this.initialized = true;
   }

   private int setValueRange(int var1, int var2, int var3) {
      return Math.min(Math.max(var1, var2), var3);
   }

   public RGB getColourRGB(Colour var1) {
      int var2 = var1.getValue() - 8;
      if (var2 >= 0 && var2 < 56) {
         if (!this.initialized) {
            this.initialize();
         }

         return this.rgbColours[var2];
      } else {
         return var1.getDefaultRGB();
      }
   }

   public byte[] getData() {
      if (this.read && !this.dirty) {
         return this.getRecord().getData();
      } else {
         byte[] var3 = new byte[226];
         int var1 = 0;
         IntegerHelper.getTwoBytes(56, var3, 0);

         while(var1 < 56) {
            int var2 = var1 * 4 + 2;
            var3[var2] = (byte)this.rgbColours[var1].getRed();
            var3[var2 + 1] = (byte)this.rgbColours[var1].getGreen();
            var3[var2 + 2] = (byte)this.rgbColours[var1].getBlue();
            ++var1;
         }

         return var3;
      }
   }

   public boolean isDirty() {
      return this.dirty;
   }

   public void setColourRGB(Colour var1, int var2, int var3, int var4) {
      int var5 = var1.getValue() - 8;
      if (var5 >= 0 && var5 < 56) {
         if (!this.initialized) {
            this.initialize();
         }

         var2 = this.setValueRange(var2, 0, 255);
         var3 = this.setValueRange(var3, 0, 255);
         var4 = this.setValueRange(var4, 0, 255);
         this.rgbColours[var5] = new RGB(var2, var3, var4);
         this.dirty = true;
      }

   }
}
