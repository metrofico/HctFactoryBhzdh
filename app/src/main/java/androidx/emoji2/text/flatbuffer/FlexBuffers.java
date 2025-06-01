package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class FlexBuffers {
   static final boolean $assertionsDisabled = false;
   private static final ReadBuf EMPTY_BB = new ArrayReadWriteBuf(new byte[]{0}, 1);
   public static final int FBT_BLOB = 25;
   public static final int FBT_BOOL = 26;
   public static final int FBT_FLOAT = 3;
   public static final int FBT_INDIRECT_FLOAT = 8;
   public static final int FBT_INDIRECT_INT = 6;
   public static final int FBT_INDIRECT_UINT = 7;
   public static final int FBT_INT = 1;
   public static final int FBT_KEY = 4;
   public static final int FBT_MAP = 9;
   public static final int FBT_NULL = 0;
   public static final int FBT_STRING = 5;
   public static final int FBT_UINT = 2;
   public static final int FBT_VECTOR = 10;
   public static final int FBT_VECTOR_BOOL = 36;
   public static final int FBT_VECTOR_FLOAT = 13;
   public static final int FBT_VECTOR_FLOAT2 = 18;
   public static final int FBT_VECTOR_FLOAT3 = 21;
   public static final int FBT_VECTOR_FLOAT4 = 24;
   public static final int FBT_VECTOR_INT = 11;
   public static final int FBT_VECTOR_INT2 = 16;
   public static final int FBT_VECTOR_INT3 = 19;
   public static final int FBT_VECTOR_INT4 = 22;
   public static final int FBT_VECTOR_KEY = 14;
   public static final int FBT_VECTOR_STRING_DEPRECATED = 15;
   public static final int FBT_VECTOR_UINT = 12;
   public static final int FBT_VECTOR_UINT2 = 17;
   public static final int FBT_VECTOR_UINT3 = 20;
   public static final int FBT_VECTOR_UINT4 = 23;

   public static Reference getRoot(ReadBuf var0) {
      int var2 = var0.limit() - 1;
      byte var1 = var0.get(var2);
      --var2;
      return new Reference(var0, var2 - var1, var1, FlexBuffers.Unsigned.byteToUnsignedInt(var0.get(var2)));
   }

   @Deprecated
   public static Reference getRoot(ByteBuffer var0) {
      java.lang.Object var1;
      if (var0.hasArray()) {
         var1 = new ArrayReadWriteBuf(var0.array(), var0.limit());
      } else {
         var1 = new ByteBufferReadWriteBuf(var0);
      }

      return getRoot((ReadBuf)var1);
   }

   private static int indirect(ReadBuf var0, int var1, int var2) {
      return (int)((long)var1 - readUInt(var0, var1, var2));
   }

   static boolean isTypeInline(int var0) {
      boolean var1;
      if (var0 > 3 && var0 != 26) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   static boolean isTypedVector(int var0) {
      boolean var1;
      if ((var0 < 11 || var0 > 15) && var0 != 36) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   static boolean isTypedVectorElementType(int var0) {
      boolean var2 = true;
      boolean var1;
      if (var0 >= 1) {
         var1 = var2;
         if (var0 <= 4) {
            return var1;
         }
      }

      if (var0 == 26) {
         var1 = var2;
      } else {
         var1 = false;
      }

      return var1;
   }

   private static double readDouble(ReadBuf var0, int var1, int var2) {
      if (var2 != 4) {
         return var2 != 8 ? -1.0 : var0.getDouble(var1);
      } else {
         return (double)var0.getFloat(var1);
      }
   }

   private static int readInt(ReadBuf var0, int var1, int var2) {
      return (int)readLong(var0, var1, var2);
   }

   private static long readLong(ReadBuf var0, int var1, int var2) {
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 4) {
               if (var2 != 8) {
                  return -1L;
               }

               return var0.getLong(var1);
            }

            var1 = var0.getInt(var1);
         } else {
            var1 = var0.getShort(var1);
         }
      } else {
         var1 = var0.get(var1);
      }

      return (long)var1;
   }

   private static long readUInt(ReadBuf var0, int var1, int var2) {
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 4) {
               return var2 != 8 ? -1L : var0.getLong(var1);
            } else {
               return FlexBuffers.Unsigned.intToUnsignedLong(var0.getInt(var1));
            }
         } else {
            return (long)FlexBuffers.Unsigned.shortToUnsignedInt(var0.getShort(var1));
         }
      } else {
         return (long)FlexBuffers.Unsigned.byteToUnsignedInt(var0.get(var1));
      }
   }

   static int toTypedVector(int var0, int var1) {
      if (var1 != 0) {
         if (var1 != 2) {
            if (var1 != 3) {
               return var1 != 4 ? 0 : var0 - 1 + 22;
            } else {
               return var0 - 1 + 19;
            }
         } else {
            return var0 - 1 + 16;
         }
      } else {
         return var0 - 1 + 11;
      }
   }

   static int toTypedVectorElementType(int var0) {
      return var0 - 11 + 1;
   }

   public static class Blob extends Sized {
      static final boolean $assertionsDisabled = false;
      static final Blob EMPTY;

      static {
         EMPTY = new Blob(FlexBuffers.EMPTY_BB, 1, 1);
      }

      Blob(ReadBuf var1, int var2, int var3) {
         super(var1, var2, var3);
      }

      public static Blob empty() {
         return EMPTY;
      }

      public ByteBuffer data() {
         ByteBuffer var1 = ByteBuffer.wrap(this.bb.data());
         var1.position(this.end);
         var1.limit(this.end + this.size());
         return var1.asReadOnlyBuffer().slice();
      }

      public byte get(int var1) {
         return this.bb.get(this.end + var1);
      }

      public byte[] getBytes() {
         int var2 = this.size();
         byte[] var3 = new byte[var2];

         for(int var1 = 0; var1 < var2; ++var1) {
            var3[var1] = this.bb.get(this.end + var1);
         }

         return var3;
      }

      public String toString() {
         return this.bb.getString(this.end, this.size());
      }

      public StringBuilder toString(StringBuilder var1) {
         var1.append('"');
         var1.append(this.bb.getString(this.end, this.size()));
         return var1.append('"');
      }
   }

   public static class FlexBufferException extends RuntimeException {
      FlexBufferException(String var1) {
         super(var1);
      }
   }

   public static class Key extends Object {
      private static final Key EMPTY;

      static {
         EMPTY = new Key(FlexBuffers.EMPTY_BB, 0, 0);
      }

      Key(ReadBuf var1, int var2, int var3) {
         super(var1, var2, var3);
      }

      public static Key empty() {
         return EMPTY;
      }

      int compareTo(byte[] var1) {
         int var3 = this.end;
         int var2 = 0;

         byte var4;
         byte var5;
         do {
            var5 = this.bb.get(var3);
            var4 = var1[var2];
            if (var5 == 0) {
               return var5 - var4;
            }

            ++var3;
            ++var2;
            if (var2 == var1.length) {
               return var5 - var4;
            }
         } while(var5 == var4);

         return var5 - var4;
      }

      public boolean equals(java.lang.Object var1) {
         boolean var2 = var1 instanceof Key;
         boolean var3 = false;
         if (!var2) {
            return false;
         } else {
            Key var4 = (Key)var1;
            var2 = var3;
            if (var4.end == this.end) {
               var2 = var3;
               if (var4.byteWidth == this.byteWidth) {
                  var2 = true;
               }
            }

            return var2;
         }
      }

      public int hashCode() {
         return this.end ^ this.byteWidth;
      }

      public String toString() {
         int var1;
         for(var1 = this.end; this.bb.get(var1) != 0; ++var1) {
         }

         int var2 = this.end;
         return this.bb.getString(this.end, var1 - var2);
      }

      public StringBuilder toString(StringBuilder var1) {
         return var1.append(this.toString());
      }
   }

   public static class KeyVector {
      private final TypedVector vec;

      KeyVector(TypedVector var1) {
         this.vec = var1;
      }

      public Key get(int var1) {
         if (var1 >= this.size()) {
            return FlexBuffers.Key.EMPTY;
         } else {
            int var2 = this.vec.end;
            int var3 = this.vec.byteWidth;
            return new Key(this.vec.bb, FlexBuffers.indirect(this.vec.bb, var2 + var1 * var3, this.vec.byteWidth), 1);
         }
      }

      public int size() {
         return this.vec.size();
      }

      public String toString() {
         StringBuilder var2 = new StringBuilder();
         var2.append('[');

         for(int var1 = 0; var1 < this.vec.size(); ++var1) {
            this.vec.get(var1).toString(var2);
            if (var1 != this.vec.size() - 1) {
               var2.append(", ");
            }
         }

         return var2.append("]").toString();
      }
   }

   public static class Map extends Vector {
      private static final Map EMPTY_MAP;

      static {
         EMPTY_MAP = new Map(FlexBuffers.EMPTY_BB, 1, 1);
      }

      Map(ReadBuf var1, int var2, int var3) {
         super(var1, var2, var3);
      }

      private int binarySearch(KeyVector var1, byte[] var2) {
         int var4 = var1.size() - 1;
         int var3 = 0;

         while(var3 <= var4) {
            int var5 = var3 + var4 >>> 1;
            int var6 = var1.get(var5).compareTo(var2);
            if (var6 < 0) {
               var3 = var5 + 1;
            } else {
               if (var6 <= 0) {
                  return var5;
               }

               var4 = var5 - 1;
            }
         }

         return -(var3 + 1);
      }

      public static Map empty() {
         return EMPTY_MAP;
      }

      public Reference get(String var1) {
         return this.get(var1.getBytes(StandardCharsets.UTF_8));
      }

      public Reference get(byte[] var1) {
         KeyVector var4 = this.keys();
         int var2 = var4.size();
         int var3 = this.binarySearch(var4, var1);
         return var3 >= 0 && var3 < var2 ? this.get(var3) : FlexBuffers.Reference.NULL_REFERENCE;
      }

      public KeyVector keys() {
         int var1 = this.end - this.byteWidth * 3;
         return new KeyVector(new TypedVector(this.bb, FlexBuffers.indirect(this.bb, var1, this.byteWidth), FlexBuffers.readInt(this.bb, var1 + this.byteWidth, this.byteWidth), 4));
      }

      public StringBuilder toString(StringBuilder var1) {
         var1.append("{ ");
         KeyVector var5 = this.keys();
         int var3 = this.size();
         Vector var4 = this.values();

         for(int var2 = 0; var2 < var3; ++var2) {
            var1.append('"').append(var5.get(var2).toString()).append("\" : ");
            var1.append(var4.get(var2).toString());
            if (var2 != var3 - 1) {
               var1.append(", ");
            }
         }

         var1.append(" }");
         return var1;
      }

      public Vector values() {
         return new Vector(this.bb, this.end, this.byteWidth);
      }
   }

   private abstract static class Object {
      ReadBuf bb;
      int byteWidth;
      int end;

      Object(ReadBuf var1, int var2, int var3) {
         this.bb = var1;
         this.end = var2;
         this.byteWidth = var3;
      }

      public String toString() {
         return this.toString(new StringBuilder(128)).toString();
      }

      public abstract StringBuilder toString(StringBuilder var1);
   }

   public static class Reference {
      private static final Reference NULL_REFERENCE;
      private ReadBuf bb;
      private int byteWidth;
      private int end;
      private int parentWidth;
      private int type;

      static {
         NULL_REFERENCE = new Reference(FlexBuffers.EMPTY_BB, 0, 1, 0);
      }

      Reference(ReadBuf var1, int var2, int var3, int var4) {
         this(var1, var2, var3, 1 << (var4 & 3), var4 >> 2);
      }

      Reference(ReadBuf var1, int var2, int var3, int var4, int var5) {
         this.bb = var1;
         this.end = var2;
         this.parentWidth = var3;
         this.byteWidth = var4;
         this.type = var5;
      }

      public Blob asBlob() {
         if (!this.isBlob() && !this.isString()) {
            return FlexBuffers.Blob.EMPTY;
         } else {
            ReadBuf var1 = this.bb;
            return new Blob(var1, FlexBuffers.indirect(var1, this.end, this.parentWidth), this.byteWidth);
         }
      }

      public boolean asBoolean() {
         boolean var3 = this.isBoolean();
         boolean var2 = true;
         boolean var1 = true;
         if (var3) {
            if (this.bb.get(this.end) == 0) {
               var1 = false;
            }

            return var1;
         } else {
            if (this.asUInt() != 0L) {
               var1 = var2;
            } else {
               var1 = false;
            }

            return var1;
         }
      }

      public double asFloat() {
         int var1 = this.type;
         if (var1 == 3) {
            return FlexBuffers.readDouble(this.bb, this.end, this.parentWidth);
         } else if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 5) {
                  return Double.parseDouble(this.asString());
               }

               ReadBuf var2;
               if (var1 == 6) {
                  var2 = this.bb;
                  return (double)FlexBuffers.readInt(var2, FlexBuffers.indirect(var2, this.end, this.parentWidth), this.byteWidth);
               }

               if (var1 == 7) {
                  var2 = this.bb;
                  return (double)FlexBuffers.readUInt(var2, FlexBuffers.indirect(var2, this.end, this.parentWidth), this.byteWidth);
               }

               if (var1 == 8) {
                  var2 = this.bb;
                  return FlexBuffers.readDouble(var2, FlexBuffers.indirect(var2, this.end, this.parentWidth), this.byteWidth);
               }

               if (var1 == 10) {
                  return (double)this.asVector().size();
               }

               if (var1 != 26) {
                  return 0.0;
               }
            }

            return (double)FlexBuffers.readUInt(this.bb, this.end, this.parentWidth);
         } else {
            return (double)FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
         }
      }

      public int asInt() {
         int var1 = this.type;
         if (var1 == 1) {
            return FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
         } else if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 5) {
                  ReadBuf var2;
                  if (var1 != 6) {
                     if (var1 != 7) {
                        if (var1 != 8) {
                           if (var1 != 10) {
                              return var1 != 26 ? 0 : FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
                           } else {
                              return this.asVector().size();
                           }
                        } else {
                           var2 = this.bb;
                           return (int)FlexBuffers.readDouble(var2, FlexBuffers.indirect(var2, this.end, this.parentWidth), this.byteWidth);
                        }
                     } else {
                        var2 = this.bb;
                        return (int)FlexBuffers.readUInt(var2, FlexBuffers.indirect(var2, this.end, this.parentWidth), this.parentWidth);
                     }
                  } else {
                     var2 = this.bb;
                     return FlexBuffers.readInt(var2, FlexBuffers.indirect(var2, this.end, this.parentWidth), this.byteWidth);
                  }
               } else {
                  return Integer.parseInt(this.asString());
               }
            } else {
               return (int)FlexBuffers.readDouble(this.bb, this.end, this.parentWidth);
            }
         } else {
            return (int)FlexBuffers.readUInt(this.bb, this.end, this.parentWidth);
         }
      }

      public Key asKey() {
         if (this.isKey()) {
            ReadBuf var1 = this.bb;
            return new Key(var1, FlexBuffers.indirect(var1, this.end, this.parentWidth), this.byteWidth);
         } else {
            return FlexBuffers.Key.EMPTY;
         }
      }

      public long asLong() {
         int var1 = this.type;
         if (var1 == 1) {
            return FlexBuffers.readLong(this.bb, this.end, this.parentWidth);
         } else if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 5) {
                  ReadBuf var4;
                  if (var1 != 6) {
                     if (var1 != 7) {
                        if (var1 != 8) {
                           if (var1 != 10) {
                              return var1 != 26 ? 0L : (long)FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
                           } else {
                              return (long)this.asVector().size();
                           }
                        } else {
                           var4 = this.bb;
                           return (long)FlexBuffers.readDouble(var4, FlexBuffers.indirect(var4, this.end, this.parentWidth), this.byteWidth);
                        }
                     } else {
                        var4 = this.bb;
                        return FlexBuffers.readUInt(var4, FlexBuffers.indirect(var4, this.end, this.parentWidth), this.parentWidth);
                     }
                  } else {
                     var4 = this.bb;
                     return FlexBuffers.readLong(var4, FlexBuffers.indirect(var4, this.end, this.parentWidth), this.byteWidth);
                  }
               } else {
                  try {
                     long var2 = Long.parseLong(this.asString());
                     return var2;
                  } catch (NumberFormatException var5) {
                     return 0L;
                  }
               }
            } else {
               return (long)FlexBuffers.readDouble(this.bb, this.end, this.parentWidth);
            }
         } else {
            return FlexBuffers.readUInt(this.bb, this.end, this.parentWidth);
         }
      }

      public Map asMap() {
         if (this.isMap()) {
            ReadBuf var1 = this.bb;
            return new Map(var1, FlexBuffers.indirect(var1, this.end, this.parentWidth), this.byteWidth);
         } else {
            return FlexBuffers.Map.EMPTY_MAP;
         }
      }

      public String asString() {
         int var1;
         int var2;
         if (this.isString()) {
            var1 = FlexBuffers.indirect(this.bb, this.end, this.parentWidth);
            ReadBuf var3 = this.bb;
            var2 = this.byteWidth;
            var2 = (int)FlexBuffers.readUInt(var3, var1 - var2, var2);
            return this.bb.getString(var1, var2);
         } else if (!this.isKey()) {
            return "";
         } else {
            var2 = FlexBuffers.indirect(this.bb, this.end, this.byteWidth);

            for(var1 = var2; this.bb.get(var1) != 0; ++var1) {
            }

            return this.bb.getString(var2, var1 - var2);
         }
      }

      public long asUInt() {
         int var1 = this.type;
         if (var1 == 2) {
            return FlexBuffers.readUInt(this.bb, this.end, this.parentWidth);
         } else if (var1 != 1) {
            if (var1 != 3) {
               if (var1 != 10) {
                  if (var1 != 26) {
                     if (var1 != 5) {
                        ReadBuf var2;
                        if (var1 != 6) {
                           if (var1 != 7) {
                              if (var1 != 8) {
                                 return 0L;
                              } else {
                                 var2 = this.bb;
                                 return (long)FlexBuffers.readDouble(var2, FlexBuffers.indirect(var2, this.end, this.parentWidth), this.parentWidth);
                              }
                           } else {
                              var2 = this.bb;
                              return FlexBuffers.readUInt(var2, FlexBuffers.indirect(var2, this.end, this.parentWidth), this.byteWidth);
                           }
                        } else {
                           var2 = this.bb;
                           return FlexBuffers.readLong(var2, FlexBuffers.indirect(var2, this.end, this.parentWidth), this.byteWidth);
                        }
                     } else {
                        return Long.parseLong(this.asString());
                     }
                  } else {
                     return (long)FlexBuffers.readInt(this.bb, this.end, this.parentWidth);
                  }
               } else {
                  return (long)this.asVector().size();
               }
            } else {
               return (long)FlexBuffers.readDouble(this.bb, this.end, this.parentWidth);
            }
         } else {
            return FlexBuffers.readLong(this.bb, this.end, this.parentWidth);
         }
      }

      public Vector asVector() {
         ReadBuf var2;
         if (this.isVector()) {
            var2 = this.bb;
            return new Vector(var2, FlexBuffers.indirect(var2, this.end, this.parentWidth), this.byteWidth);
         } else {
            int var1 = this.type;
            if (var1 == 15) {
               var2 = this.bb;
               return new TypedVector(var2, FlexBuffers.indirect(var2, this.end, this.parentWidth), this.byteWidth, 4);
            } else if (FlexBuffers.isTypedVector(var1)) {
               var2 = this.bb;
               return new TypedVector(var2, FlexBuffers.indirect(var2, this.end, this.parentWidth), this.byteWidth, FlexBuffers.toTypedVectorElementType(this.type));
            } else {
               return FlexBuffers.Vector.EMPTY_VECTOR;
            }
         }
      }

      public int getType() {
         return this.type;
      }

      public boolean isBlob() {
         boolean var1;
         if (this.type == 25) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean isBoolean() {
         boolean var1;
         if (this.type == 26) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean isFloat() {
         int var1 = this.type;
         boolean var2;
         if (var1 != 3 && var1 != 8) {
            var2 = false;
         } else {
            var2 = true;
         }

         return var2;
      }

      public boolean isInt() {
         int var1 = this.type;
         boolean var3 = true;
         boolean var2 = var3;
         if (var1 != 1) {
            if (var1 == 6) {
               var2 = var3;
            } else {
               var2 = false;
            }
         }

         return var2;
      }

      public boolean isIntOrUInt() {
         boolean var1;
         if (!this.isInt() && !this.isUInt()) {
            var1 = false;
         } else {
            var1 = true;
         }

         return var1;
      }

      public boolean isKey() {
         boolean var1;
         if (this.type == 4) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean isMap() {
         boolean var1;
         if (this.type == 9) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean isNull() {
         boolean var1;
         if (this.type == 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean isNumeric() {
         boolean var1;
         if (!this.isIntOrUInt() && !this.isFloat()) {
            var1 = false;
         } else {
            var1 = true;
         }

         return var1;
      }

      public boolean isString() {
         boolean var1;
         if (this.type == 5) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean isTypedVector() {
         return FlexBuffers.isTypedVector(this.type);
      }

      public boolean isUInt() {
         int var1 = this.type;
         boolean var2;
         if (var1 != 2 && var1 != 7) {
            var2 = false;
         } else {
            var2 = true;
         }

         return var2;
      }

      public boolean isVector() {
         int var1 = this.type;
         boolean var2;
         if (var1 != 10 && var1 != 9) {
            var2 = false;
         } else {
            var2 = true;
         }

         return var2;
      }

      public String toString() {
         return this.toString(new StringBuilder(128)).toString();
      }

      StringBuilder toString(StringBuilder var1) {
         int var2 = this.type;
         if (var2 != 36) {
            switch (var2) {
               case 0:
                  return var1.append("null");
               case 1:
               case 6:
                  return var1.append(this.asLong());
               case 2:
               case 7:
                  return var1.append(this.asUInt());
               case 3:
               case 8:
                  return var1.append(this.asFloat());
               case 4:
                  return this.asKey().toString(var1.append('"')).append('"');
               case 5:
                  return var1.append('"').append(this.asString()).append('"');
               case 9:
                  return this.asMap().toString(var1);
               case 10:
                  return this.asVector().toString(var1);
               case 11:
               case 12:
               case 13:
               case 14:
               case 15:
                  break;
               case 16:
               case 17:
               case 18:
               case 19:
               case 20:
               case 21:
               case 22:
               case 23:
               case 24:
                  throw new FlexBufferException("not_implemented:" + this.type);
               case 25:
                  return this.asBlob().toString(var1);
               case 26:
                  return var1.append(this.asBoolean());
               default:
                  return var1;
            }
         }

         return var1.append(this.asVector());
      }
   }

   private abstract static class Sized extends Object {
      protected final int size;

      Sized(ReadBuf var1, int var2, int var3) {
         super(var1, var2, var3);
         this.size = FlexBuffers.readInt(this.bb, var2 - var3, var3);
      }

      public int size() {
         return this.size;
      }
   }

   public static class TypedVector extends Vector {
      private static final TypedVector EMPTY_VECTOR;
      private final int elemType;

      static {
         EMPTY_VECTOR = new TypedVector(FlexBuffers.EMPTY_BB, 1, 1, 1);
      }

      TypedVector(ReadBuf var1, int var2, int var3, int var4) {
         super(var1, var2, var3);
         this.elemType = var4;
      }

      public static TypedVector empty() {
         return EMPTY_VECTOR;
      }

      public Reference get(int var1) {
         if (var1 >= this.size()) {
            return FlexBuffers.Reference.NULL_REFERENCE;
         } else {
            int var3 = this.end;
            int var2 = this.byteWidth;
            return new Reference(this.bb, var3 + var1 * var2, this.byteWidth, 1, this.elemType);
         }
      }

      public int getElemType() {
         return this.elemType;
      }

      public boolean isEmptyVector() {
         boolean var1;
         if (this == EMPTY_VECTOR) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }
   }

   static class Unsigned {
      static int byteToUnsignedInt(byte var0) {
         return var0 & 255;
      }

      static long intToUnsignedLong(int var0) {
         return (long)var0 & 4294967295L;
      }

      static int shortToUnsignedInt(short var0) {
         return var0 & '\uffff';
      }
   }

   public static class Vector extends Sized {
      private static final Vector EMPTY_VECTOR;

      static {
         EMPTY_VECTOR = new Vector(FlexBuffers.EMPTY_BB, 1, 1);
      }

      Vector(ReadBuf var1, int var2, int var3) {
         super(var1, var2, var3);
      }

      public static Vector empty() {
         return EMPTY_VECTOR;
      }

      public Reference get(int var1) {
         long var5 = (long)this.size();
         long var7 = (long)var1;
         if (var7 >= var5) {
            return FlexBuffers.Reference.NULL_REFERENCE;
         } else {
            int var4 = FlexBuffers.Unsigned.byteToUnsignedInt(this.bb.get((int)((long)this.end + var5 * (long)this.byteWidth + var7)));
            int var2 = this.end;
            int var3 = this.byteWidth;
            return new Reference(this.bb, var2 + var1 * var3, this.byteWidth, var4);
         }
      }

      public boolean isEmpty() {
         boolean var1;
         if (this == EMPTY_VECTOR) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public StringBuilder toString(StringBuilder var1) {
         var1.append("[ ");
         int var3 = this.size();

         for(int var2 = 0; var2 < var3; ++var2) {
            this.get(var2).toString(var1);
            if (var2 != var3 - 1) {
               var1.append(", ");
            }
         }

         var1.append(" ]");
         return var1;
      }
   }
}
