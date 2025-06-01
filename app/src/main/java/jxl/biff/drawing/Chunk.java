package jxl.biff.drawing;

class Chunk {
   private byte[] data;
   private int length;
   private int pos;
   private ChunkType type;

   public Chunk(int var1, int var2, ChunkType var3, byte[] var4) {
      this.pos = var1;
      this.length = var2;
      this.type = var3;
      byte[] var5 = new byte[var2];
      this.data = var5;
      System.arraycopy(var4, var1, var5, 0, var2);
   }

   public byte[] getData() {
      return this.data;
   }
}
