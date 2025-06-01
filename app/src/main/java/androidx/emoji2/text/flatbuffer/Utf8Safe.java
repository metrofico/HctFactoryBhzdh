package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class Utf8Safe extends Utf8 {
   private static int computeEncodedLength(CharSequence var0) {
      int var4 = var0.length();

      int var2;
      for(var2 = 0; var2 < var4 && var0.charAt(var2) < 128; ++var2) {
      }

      int var1 = var4;

      int var3;
      while(true) {
         var3 = var1;
         if (var2 >= var4) {
            break;
         }

         char var5 = var0.charAt(var2);
         if (var5 >= 2048) {
            var3 = var1 + encodedLengthGeneral(var0, var2);
            break;
         }

         var1 += 127 - var5 >>> 31;
         ++var2;
      }

      if (var3 >= var4) {
         return var3;
      } else {
         throw new IllegalArgumentException("UTF-8 length does not fit in int: " + ((long)var3 + 4294967296L));
      }
   }

   public static String decodeUtf8Array(byte[] var0, int var1, int var2) {
      if ((var1 | var2 | var0.length - var1 - var2) < 0) {
         throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", var0.length, var1, var2));
      } else {
         int var6 = var1 + var2;
         char[] var7 = new char[var2];

         byte var3;
         for(var2 = 0; var1 < var6; ++var2) {
            var3 = var0[var1];
            if (!Utf8.DecodeUtil.isOneByte(var3)) {
               break;
            }

            ++var1;
            Utf8.DecodeUtil.handleOneByte(var3, var7, var2);
         }

         int var5 = var2;
         var2 = var1;
         var1 = var5;

         while(true) {
            while(var2 < var6) {
               var5 = var2 + 1;
               byte var4 = var0[var2];
               if (Utf8.DecodeUtil.isOneByte(var4)) {
                  var2 = var1 + 1;
                  Utf8.DecodeUtil.handleOneByte(var4, var7, var1);
                  var1 = var2;

                  for(var2 = var5; var2 < var6; ++var1) {
                     var3 = var0[var2];
                     if (!Utf8.DecodeUtil.isOneByte(var3)) {
                        break;
                     }

                     ++var2;
                     Utf8.DecodeUtil.handleOneByte(var3, var7, var1);
                  }
               } else if (Utf8.DecodeUtil.isTwoBytes(var4)) {
                  if (var5 >= var6) {
                     throw new IllegalArgumentException("Invalid UTF-8");
                  }

                  Utf8.DecodeUtil.handleTwoBytes(var4, var0[var5], var7, var1);
                  var2 = var5 + 1;
                  ++var1;
               } else if (Utf8.DecodeUtil.isThreeBytes(var4)) {
                  if (var5 >= var6 - 1) {
                     throw new IllegalArgumentException("Invalid UTF-8");
                  }

                  var2 = var5 + 1;
                  Utf8.DecodeUtil.handleThreeBytes(var4, var0[var5], var0[var2], var7, var1);
                  ++var2;
                  ++var1;
               } else {
                  if (var5 >= var6 - 2) {
                     throw new IllegalArgumentException("Invalid UTF-8");
                  }

                  var2 = var5 + 1;
                  var3 = var0[var5];
                  var5 = var2 + 1;
                  Utf8.DecodeUtil.handleFourBytes(var4, var3, var0[var2], var0[var5], var7, var1);
                  var2 = var5 + 1;
                  var1 = var1 + 1 + 1;
               }
            }

            return new String(var7, 0, var1);
         }
      }
   }

   public static String decodeUtf8Buffer(ByteBuffer var0, int var1, int var2) {
      if ((var1 | var2 | var0.limit() - var1 - var2) < 0) {
         throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", var0.limit(), var1, var2));
      } else {
         int var6 = var1 + var2;
         char[] var7 = new char[var2];

         byte var3;
         for(var2 = 0; var1 < var6; ++var2) {
            var3 = var0.get(var1);
            if (!Utf8.DecodeUtil.isOneByte(var3)) {
               break;
            }

            ++var1;
            Utf8.DecodeUtil.handleOneByte(var3, var7, var2);
         }

         int var5 = var2;
         var2 = var1;
         var1 = var5;

         while(true) {
            while(var2 < var6) {
               var5 = var2 + 1;
               var3 = var0.get(var2);
               if (Utf8.DecodeUtil.isOneByte(var3)) {
                  var2 = var1 + 1;
                  Utf8.DecodeUtil.handleOneByte(var3, var7, var1);
                  var1 = var2;

                  for(var2 = var5; var2 < var6; ++var1) {
                     var3 = var0.get(var2);
                     if (!Utf8.DecodeUtil.isOneByte(var3)) {
                        break;
                     }

                     ++var2;
                     Utf8.DecodeUtil.handleOneByte(var3, var7, var1);
                  }
               } else if (Utf8.DecodeUtil.isTwoBytes(var3)) {
                  if (var5 >= var6) {
                     throw new IllegalArgumentException("Invalid UTF-8");
                  }

                  Utf8.DecodeUtil.handleTwoBytes(var3, var0.get(var5), var7, var1);
                  var2 = var5 + 1;
                  ++var1;
               } else if (Utf8.DecodeUtil.isThreeBytes(var3)) {
                  if (var5 >= var6 - 1) {
                     throw new IllegalArgumentException("Invalid UTF-8");
                  }

                  var2 = var5 + 1;
                  Utf8.DecodeUtil.handleThreeBytes(var3, var0.get(var5), var0.get(var2), var7, var1);
                  ++var2;
                  ++var1;
               } else {
                  if (var5 >= var6 - 2) {
                     throw new IllegalArgumentException("Invalid UTF-8");
                  }

                  var2 = var5 + 1;
                  byte var4 = var0.get(var5);
                  var5 = var2 + 1;
                  Utf8.DecodeUtil.handleFourBytes(var3, var4, var0.get(var2), var0.get(var5), var7, var1);
                  var2 = var5 + 1;
                  var1 = var1 + 1 + 1;
               }
            }

            return new String(var7, 0, var1);
         }
      }
   }

   private static int encodeUtf8Array(CharSequence var0, byte[] var1, int var2, int var3) {
      int var7 = var0.length();
      int var8 = var3 + var2;

      int var6;
      int var9;
      for(var3 = 0; var3 < var7; ++var3) {
         var6 = var3 + var2;
         if (var6 >= var8) {
            break;
         }

         var9 = var0.charAt(var3);
         if (var9 >= 128) {
            break;
         }

         var1[var6] = (byte)var9;
      }

      if (var3 == var7) {
         return var2 + var7;
      } else {
         var6 = var2 + var3;
         var2 = var3;

         while(true) {
            if (var2 >= var7) {
               return var6;
            }

            char var4 = var0.charAt(var2);
            if (var4 < 128 && var6 < var8) {
               var3 = var6 + 1;
               var1[var6] = (byte)var4;
            } else if (var4 < 2048 && var6 <= var8 - 2) {
               var9 = var6 + 1;
               var1[var6] = (byte)(var4 >>> 6 | 960);
               var3 = var9 + 1;
               var1[var9] = (byte)(var4 & 63 | 128);
            } else if ((var4 < '\ud800' || '\udfff' < var4) && var6 <= var8 - 3) {
               var3 = var6 + 1;
               var1[var6] = (byte)(var4 >>> 12 | 480);
               var6 = var3 + 1;
               var1[var3] = (byte)(var4 >>> 6 & 63 | 128);
               var3 = var6 + 1;
               var1[var6] = (byte)(var4 & 63 | 128);
            } else {
               if (var6 > var8 - 4) {
                  if ('\ud800' <= var4 && var4 <= '\udfff') {
                     var3 = var2 + 1;
                     if (var3 == var0.length() || !Character.isSurrogatePair(var4, var0.charAt(var3))) {
                        throw new UnpairedSurrogateException(var2, var7);
                     }
                  }

                  throw new ArrayIndexOutOfBoundsException("Failed writing " + var4 + " at index " + var6);
               }

               var3 = var2 + 1;
               if (var3 == var0.length()) {
                  break;
               }

               char var5 = var0.charAt(var3);
               if (!Character.isSurrogatePair(var4, var5)) {
                  var2 = var3;
                  break;
               }

               var2 = Character.toCodePoint(var4, var5);
               var9 = var6 + 1;
               var1[var6] = (byte)(var2 >>> 18 | 240);
               var6 = var9 + 1;
               var1[var9] = (byte)(var2 >>> 12 & 63 | 128);
               var9 = var6 + 1;
               var1[var6] = (byte)(var2 >>> 6 & 63 | 128);
               var6 = var9 + 1;
               var1[var9] = (byte)(var2 & 63 | 128);
               var2 = var3;
               var3 = var6;
            }

            ++var2;
            var6 = var3;
         }

         throw new UnpairedSurrogateException(var2 - 1, var7);
      }
   }

   private static void encodeUtf8Buffer(CharSequence var0, ByteBuffer var1) {
      int var10 = var0.length();
      int var6 = var1.position();
      int var5 = 0;

      int var7;
      int var8;
      label217: {
         label216:
         while(true) {
            int var9;
            boolean var10001;
            if (var5 < var10) {
               var8 = var6;
               var7 = var5;

               try {
                  var9 = var0.charAt(var5);
               } catch (IndexOutOfBoundsException var23) {
                  var10001 = false;
                  break;
               }

               if (var9 < 128) {
                  var8 = var6;
                  var7 = var5;

                  try {
                     var1.put(var6 + var5, (byte)var9);
                  } catch (IndexOutOfBoundsException var13) {
                     var10001 = false;
                     break;
                  }

                  ++var5;
                  continue;
               }
            }

            if (var5 == var10) {
               var8 = var6;
               var7 = var5;

               try {
                  var1.position(var6 + var5);
                  return;
               } catch (IndexOutOfBoundsException var14) {
                  var10001 = false;
                  break;
               }
            }

            var6 += var5;

            while(true) {
               if (var5 < var10) {
                  var8 = var6;
                  var7 = var5;

                  char var4;
                  try {
                     var4 = var0.charAt(var5);
                  } catch (IndexOutOfBoundsException var22) {
                     var10001 = false;
                     break label216;
                  }

                  label207: {
                     label226: {
                        label233: {
                           if (var4 < 128) {
                              var8 = var6;
                              var7 = var5;

                              try {
                                 var1.put(var6, (byte)var4);
                              } catch (IndexOutOfBoundsException var21) {
                                 var10001 = false;
                                 break label216;
                              }
                           } else {
                              byte var2;
                              if (var4 < 2048) {
                                 var8 = var6 + 1;
                                 var2 = (byte)(var4 >>> 6 | 192);
                                 var7 = var8;

                                 try {
                                    var1.put(var6, var2);
                                 } catch (IndexOutOfBoundsException var32) {
                                    var10001 = false;
                                    break label226;
                                 }

                                 var7 = var8;

                                 try {
                                    var1.put(var8, (byte)(var4 & 63 | 128));
                                 } catch (IndexOutOfBoundsException var31) {
                                    var10001 = false;
                                    break label226;
                                 }

                                 var6 = var8;
                              } else if (var4 >= '\ud800' && '\udfff' >= var4) {
                                 label229: {
                                    var7 = var5 + 1;
                                    if (var7 == var10) {
                                       break label233;
                                    }

                                    var5 = var6;

                                    char var3;
                                    try {
                                       var3 = var0.charAt(var7);
                                    } catch (IndexOutOfBoundsException var26) {
                                       var10001 = false;
                                       break label207;
                                    }

                                    var5 = var6;

                                    label230: {
                                       try {
                                          if (!Character.isSurrogatePair(var4, var3)) {
                                             break label230;
                                          }
                                       } catch (IndexOutOfBoundsException var33) {
                                          var10001 = false;
                                          break label207;
                                       }

                                       var5 = var6;

                                       int var11;
                                       try {
                                          var11 = Character.toCodePoint(var4, var3);
                                       } catch (IndexOutOfBoundsException var25) {
                                          var10001 = false;
                                          break label207;
                                       }

                                       var8 = var6 + 1;
                                       var2 = (byte)(var11 >>> 18 | 240);
                                       var5 = var8;

                                       try {
                                          var1.put(var6, var2);
                                       } catch (IndexOutOfBoundsException var29) {
                                          var10001 = false;
                                          break label207;
                                       }

                                       var9 = var8 + 1;
                                       var2 = (byte)(var11 >>> 12 & 63 | 128);
                                       var5 = var9;

                                       try {
                                          var1.put(var8, var2);
                                       } catch (IndexOutOfBoundsException var24) {
                                          var10001 = false;
                                          break label207;
                                       }

                                       var6 = var9 + 1;
                                       var2 = (byte)(var11 >>> 6 & 63 | 128);
                                       var5 = var6;

                                       try {
                                          var1.put(var9, var2);
                                       } catch (IndexOutOfBoundsException var28) {
                                          var10001 = false;
                                          break label207;
                                       }

                                       var5 = var6;

                                       try {
                                          var1.put(var6, (byte)(var11 & 63 | 128));
                                       } catch (IndexOutOfBoundsException var27) {
                                          var10001 = false;
                                          break label207;
                                       }

                                       var5 = var7;
                                       break label229;
                                    }

                                    var5 = var7;
                                    break label233;
                                 }
                              } else {
                                 var9 = var6 + 1;
                                 var2 = (byte)(var4 >>> 12 | 224);
                                 var7 = var9;

                                 try {
                                    var1.put(var6, var2);
                                 } catch (IndexOutOfBoundsException var30) {
                                    var10001 = false;
                                    break label226;
                                 }

                                 var6 = var9 + 1;
                                 var2 = (byte)(var4 >>> 6 & 63 | 128);
                                 var8 = var6;
                                 var7 = var5;

                                 try {
                                    var1.put(var9, var2);
                                 } catch (IndexOutOfBoundsException var20) {
                                    var10001 = false;
                                    break label216;
                                 }

                                 var8 = var6;
                                 var7 = var5;

                                 try {
                                    var1.put(var6, (byte)(var4 & 63 | 128));
                                 } catch (IndexOutOfBoundsException var19) {
                                    var10001 = false;
                                    break label216;
                                 }
                              }
                           }

                           ++var5;
                           ++var6;
                           continue;
                        }

                        var8 = var6;
                        var7 = var5;

                        UnpairedSurrogateException var12;
                        try {
                           var12 = new UnpairedSurrogateException;
                        } catch (IndexOutOfBoundsException var18) {
                           var10001 = false;
                           break label216;
                        }

                        var8 = var6;
                        var7 = var5;

                        try {
                           var12.<init>(var5, var10);
                        } catch (IndexOutOfBoundsException var17) {
                           var10001 = false;
                           break label216;
                        }

                        var8 = var6;
                        var7 = var5;

                        try {
                           throw var12;
                        } catch (IndexOutOfBoundsException var15) {
                           var10001 = false;
                           break label216;
                        }
                     }

                     var8 = var7;
                     break label217;
                  }

                  var8 = var5;
                  var5 = var7;
                  break label217;
               }

               var8 = var6;
               var7 = var5;

               try {
                  var1.position(var6);
                  return;
               } catch (IndexOutOfBoundsException var16) {
                  var10001 = false;
                  break label216;
               }
            }
         }

         var5 = var7;
      }

      var6 = var1.position();
      var7 = Math.max(var5, var8 - var1.position() + 1);
      throw new ArrayIndexOutOfBoundsException("Failed writing " + var0.charAt(var5) + " at index " + (var6 + var7));
   }

   private static int encodedLengthGeneral(CharSequence var0, int var1) {
      int var5 = var0.length();

      int var2;
      int var3;
      for(var2 = 0; var1 < var5; var1 = var3 + 1) {
         char var6 = var0.charAt(var1);
         if (var6 < 2048) {
            var2 += 127 - var6 >>> 31;
            var3 = var1;
         } else {
            int var4 = var2 + 2;
            var2 = var4;
            var3 = var1;
            if ('\ud800' <= var6) {
               var2 = var4;
               var3 = var1;
               if (var6 <= '\udfff') {
                  if (Character.codePointAt(var0, var1) < 65536) {
                     throw new UnpairedSurrogateException(var1, var5);
                  }

                  var3 = var1 + 1;
                  var2 = var4;
               }
            }
         }
      }

      return var2;
   }

   public String decodeUtf8(ByteBuffer var1, int var2, int var3) throws IllegalArgumentException {
      return var1.hasArray() ? decodeUtf8Array(var1.array(), var1.arrayOffset() + var2, var3) : decodeUtf8Buffer(var1, var2, var3);
   }

   public void encodeUtf8(CharSequence var1, ByteBuffer var2) {
      if (var2.hasArray()) {
         int var3 = var2.arrayOffset();
         var2.position(encodeUtf8Array(var1, var2.array(), var2.position() + var3, var2.remaining()) - var3);
      } else {
         encodeUtf8Buffer(var1, var2);
      }

   }

   public int encodedLength(CharSequence var1) {
      return computeEncodedLength(var1);
   }

   static class UnpairedSurrogateException extends IllegalArgumentException {
      UnpairedSurrogateException(int var1, int var2) {
         super("Unpaired surrogate at index " + var1 + " of " + var2);
      }
   }
}
