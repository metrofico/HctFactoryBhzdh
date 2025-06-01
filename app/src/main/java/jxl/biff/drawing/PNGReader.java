package jxl.biff.drawing;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

public class PNGReader {
   private static byte[] PNG_MAGIC_NUMBER = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};
   private int horizontalResolution;
   private Chunk ihdr;
   private Chunk phys;
   private int pixelHeight;
   private int pixelWidth;
   private byte[] pngData;
   private int resolutionUnit;
   private int verticalResolution;

   public PNGReader(byte[] var1) {
      this.pngData = var1;
   }

   private int getInt(byte var1, byte var2, byte var3, byte var4) {
      return (var1 & 255) << 24 | (var2 & 255) << 16 | (var3 & 255) << 8 | var4 & 255;
   }

   public static void main(String[] var0) {
      try {
         File var1 = new File(var0[0]);
         byte[] var5 = new byte[(int)var1.length()];
         FileInputStream var2 = new FileInputStream(var1);
         var2.read(var5);
         var2.close();
         PNGReader var6 = new PNGReader(var5);
         var6.read();
      } catch (Throwable var4) {
         var4.printStackTrace();
         return;
      }

   }

   public int getHeight() {
      return this.pixelHeight;
   }

   public int getHorizontalResolution() {
      int var1;
      if (this.resolutionUnit == 1) {
         var1 = this.horizontalResolution;
      } else {
         var1 = 0;
      }

      return var1;
   }

   public int getVerticalResolution() {
      int var1;
      if (this.resolutionUnit == 1) {
         var1 = this.verticalResolution;
      } else {
         var1 = 0;
      }

      return var1;
   }

   public int getWidth() {
      return this.pixelWidth;
   }

   void read() {
      int var1 = PNG_MAGIC_NUMBER.length;
      byte[] var3 = new byte[var1];
      System.arraycopy(this.pngData, 0, var3, 0, var1);
      if (Arrays.equals(PNG_MAGIC_NUMBER, var3)) {
         var1 = 8;

         while(true) {
            var3 = this.pngData;
            if (var1 >= var3.length) {
               var3 = this.ihdr.getData();
               this.pixelWidth = this.getInt(var3[0], var3[1], var3[2], var3[3]);
               this.pixelHeight = this.getInt(var3[4], var3[5], var3[6], var3[7]);
               Chunk var5 = this.phys;
               if (var5 != null) {
                  var3 = var5.getData();
                  this.resolutionUnit = var3[8];
                  this.horizontalResolution = this.getInt(var3[0], var3[1], var3[2], var3[3]);
                  this.verticalResolution = this.getInt(var3[4], var3[5], var3[6], var3[7]);
               }

               return;
            }

            int var2 = this.getInt(var3[var1], var3[var1 + 1], var3[var1 + 2], var3[var1 + 3]);
            var3 = this.pngData;
            ChunkType var4 = ChunkType.getChunkType(var3[var1 + 4], var3[var1 + 5], var3[var1 + 6], var3[var1 + 7]);
            if (var4 == ChunkType.IHDR) {
               this.ihdr = new Chunk(var1 + 8, var2, var4, this.pngData);
            } else if (var4 == ChunkType.PHYS) {
               this.phys = new Chunk(var1 + 8, var2, var4, this.pngData);
            }

            var1 += var2 + 12;
         }
      }
   }
}
