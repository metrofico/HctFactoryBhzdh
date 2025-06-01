package jxl.biff.drawing;

import java.util.Arrays;

class ChunkType {
   public static ChunkType IEND = new ChunkType(73, 69, 78, 68, "IEND");
   public static ChunkType IHDR = new ChunkType(73, 72, 68, 82, "IHDR");
   public static ChunkType PHYS = new ChunkType(112, 72, 89, 115, "pHYs");
   public static ChunkType UNKNOWN = new ChunkType(255, 255, 255, 255, "UNKNOWN");
   private static ChunkType[] chunkTypes = new ChunkType[0];
   private byte[] id;
   private String name;

   private ChunkType(int var1, int var2, int var3, int var4, String var5) {
      this.id = new byte[]{(byte)var1, (byte)var2, (byte)var3, (byte)var4};
      this.name = var5;
      ChunkType[] var6 = chunkTypes;
      ChunkType[] var7 = new ChunkType[var6.length + 1];
      System.arraycopy(var6, 0, var7, 0, var6.length);
      var7[chunkTypes.length] = this;
      chunkTypes = var7;
   }

   public static ChunkType getChunkType(byte var0, byte var1, byte var2, byte var3) {
      int var4 = 0;
      ChunkType var6 = UNKNOWN;
      boolean var5 = false;

      while(true) {
         ChunkType[] var7 = chunkTypes;
         if (var4 >= var7.length || var5) {
            return var6;
         }

         if (Arrays.equals(var7[var4].id, new byte[]{var0, var1, var2, var3})) {
            var6 = chunkTypes[var4];
            var5 = true;
         }

         ++var4;
      }
   }

   public String getName() {
      return this.name;
   }
}
