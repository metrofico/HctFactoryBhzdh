package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import java.util.ArrayList;

public class PathParser {
   private static final String LOGTAG = "PathParser";

   private PathParser() {
   }

   private static void addNode(ArrayList var0, char var1, float[] var2) {
      var0.add(new PathDataNode(var1, var2));
   }

   public static boolean canMorph(PathDataNode[] var0, PathDataNode[] var1) {
      if (var0 != null && var1 != null) {
         if (var0.length != var1.length) {
            return false;
         } else {
            for(int var2 = 0; var2 < var0.length; ++var2) {
               if (var0[var2].mType != var1[var2].mType || var0[var2].mParams.length != var1[var2].mParams.length) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   static float[] copyOfRange(float[] var0, int var1, int var2) {
      if (var1 <= var2) {
         int var3 = var0.length;
         if (var1 >= 0 && var1 <= var3) {
            var2 -= var1;
            var3 = Math.min(var2, var3 - var1);
            float[] var4 = new float[var2];
            System.arraycopy(var0, var1, var4, 0, var3);
            return var4;
         } else {
            throw new ArrayIndexOutOfBoundsException();
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static PathDataNode[] createNodesFromPathData(String var0) {
      if (var0 == null) {
         return null;
      } else {
         ArrayList var4 = new ArrayList();
         int var2 = 1;

         int var1;
         for(var1 = 0; var2 < var0.length(); var1 = var2++) {
            var2 = nextStart(var0, var2);
            String var3 = var0.substring(var1, var2).trim();
            if (var3.length() > 0) {
               float[] var5 = getFloats(var3);
               addNode(var4, var3.charAt(0), var5);
            }
         }

         if (var2 - var1 == 1 && var1 < var0.length()) {
            addNode(var4, var0.charAt(var1), new float[0]);
         }

         return (PathDataNode[])var4.toArray(new PathDataNode[var4.size()]);
      }
   }

   public static Path createPathFromPathData(String var0) {
      Path var1 = new Path();
      PathDataNode[] var2 = createNodesFromPathData(var0);
      if (var2 != null) {
         try {
            PathParser.PathDataNode.nodesToPath(var2, var1);
            return var1;
         } catch (RuntimeException var3) {
            throw new RuntimeException("Error in parsing " + var0, var3);
         }
      } else {
         return null;
      }
   }

   public static PathDataNode[] deepCopyNodes(PathDataNode[] var0) {
      if (var0 == null) {
         return null;
      } else {
         PathDataNode[] var2 = new PathDataNode[var0.length];

         for(int var1 = 0; var1 < var0.length; ++var1) {
            var2[var1] = new PathDataNode(var0[var1]);
         }

         return var2;
      }
   }

   private static void extract(String var0, int var1, ExtractFloatResult var2) {
      var2.mEndWithNegOrDot = false;
      int var4 = var1;
      boolean var3 = false;
      boolean var6 = false;

      for(boolean var5 = var6; var4 < var0.length(); ++var4) {
         label52: {
            char var7 = var0.charAt(var4);
            if (var7 != ' ') {
               if (var7 == 'E' || var7 == 'e') {
                  var3 = true;
                  break label52;
               }

               switch (var7) {
                  case ',':
                     break;
                  case '-':
                     if (var4 != var1 && !var3) {
                        var2.mEndWithNegOrDot = true;
                        break;
                     }
                  default:
                     var3 = false;
                     break label52;
                  case '.':
                     if (!var6) {
                        var3 = false;
                        var6 = true;
                        break label52;
                     }

                     var2.mEndWithNegOrDot = true;
               }
            }

            var3 = false;
            var5 = true;
         }

         if (var5) {
            break;
         }
      }

      var2.mEndPosition = var4;
   }

   private static float[] getFloats(String var0) {
      if (var0.charAt(0) != 'z' && var0.charAt(0) != 'Z') {
         NumberFormatException var10000;
         label61: {
            int var5;
            ExtractFloatResult var6;
            float[] var7;
            boolean var10001;
            try {
               var7 = new float[var0.length()];
               var6 = new ExtractFloatResult();
               var5 = var0.length();
            } catch (NumberFormatException var12) {
               var10000 = var12;
               var10001 = false;
               break label61;
            }

            int var1 = 1;
            int var3 = 0;

            while(var1 < var5) {
               int var4;
               try {
                  extract(var0, var1, var6);
                  var4 = var6.mEndPosition;
               } catch (NumberFormatException var10) {
                  var10000 = var10;
                  var10001 = false;
                  break label61;
               }

               int var2 = var3;
               if (var1 < var4) {
                  try {
                     var7[var3] = Float.parseFloat(var0.substring(var1, var4));
                  } catch (NumberFormatException var9) {
                     var10000 = var9;
                     var10001 = false;
                     break label61;
                  }

                  var2 = var3 + 1;
               }

               label48: {
                  try {
                     if (var6.mEndWithNegOrDot) {
                        break label48;
                     }
                  } catch (NumberFormatException var11) {
                     var10000 = var11;
                     var10001 = false;
                     break label61;
                  }

                  var1 = var4 + 1;
                  var3 = var2;
                  continue;
               }

               var1 = var4;
               var3 = var2;
            }

            try {
               float[] var14 = copyOfRange(var7, 0, var3);
               return var14;
            } catch (NumberFormatException var8) {
               var10000 = var8;
               var10001 = false;
            }
         }

         NumberFormatException var13 = var10000;
         throw new RuntimeException("error in parsing \"" + var0 + "\"", var13);
      } else {
         return new float[0];
      }
   }

   public static boolean interpolatePathDataNodes(PathDataNode[] var0, PathDataNode[] var1, PathDataNode[] var2, float var3) {
      if (var0 != null && var1 != null && var2 != null) {
         if (var0.length == var1.length && var1.length == var2.length) {
            boolean var5 = canMorph(var1, var2);
            int var4 = 0;
            if (!var5) {
               return false;
            } else {
               while(var4 < var0.length) {
                  var0[var4].interpolatePathDataNode(var1[var4], var2[var4], var3);
                  ++var4;
               }

               return true;
            }
         } else {
            throw new IllegalArgumentException("The nodes to be interpolated and resulting nodes must have the same length");
         }
      } else {
         throw new IllegalArgumentException("The nodes to be interpolated and resulting nodes cannot be null");
      }
   }

   private static int nextStart(String var0, int var1) {
      while(var1 < var0.length()) {
         char var2 = var0.charAt(var1);
         if (((var2 - 65) * (var2 - 90) <= 0 || (var2 - 97) * (var2 - 122) <= 0) && var2 != 'e' && var2 != 'E') {
            return var1;
         }

         ++var1;
      }

      return var1;
   }

   public static void updateNodes(PathDataNode[] var0, PathDataNode[] var1) {
      for(int var2 = 0; var2 < var1.length; ++var2) {
         var0[var2].mType = var1[var2].mType;

         for(int var3 = 0; var3 < var1[var2].mParams.length; ++var3) {
            var0[var2].mParams[var3] = var1[var2].mParams[var3];
         }
      }

   }

   private static class ExtractFloatResult {
      int mEndPosition;
      boolean mEndWithNegOrDot;

      ExtractFloatResult() {
      }
   }

   public static class PathDataNode {
      public float[] mParams;
      public char mType;

      PathDataNode(char var1, float[] var2) {
         this.mType = var1;
         this.mParams = var2;
      }

      PathDataNode(PathDataNode var1) {
         this.mType = var1.mType;
         float[] var2 = var1.mParams;
         this.mParams = PathParser.copyOfRange(var2, 0, var2.length);
      }

      private static void addCommand(Path var0, float[] var1, char var2, char var3, float[] var4) {
         float var5;
         float var6;
         float var7;
         float var8;
         float var9;
         float var10;
         float var11;
         float var12;
         float var13;
         byte var15;
         label193: {
            label192: {
               float var14 = var1[0];
               var13 = var1[1];
               var11 = var1[2];
               var12 = var1[3];
               var10 = var1[4];
               var9 = var1[5];
               var5 = var14;
               var6 = var13;
               var7 = var11;
               var8 = var12;
               switch (var3) {
                  case 'A':
                  case 'a':
                     var15 = 7;
                     break;
                  case 'C':
                  case 'c':
                     var15 = 6;
                     break;
                  case 'H':
                  case 'V':
                  case 'h':
                  case 'v':
                     var15 = 1;
                     var5 = var14;
                     var6 = var13;
                     var7 = var11;
                     var8 = var12;
                     break label193;
                  case 'L':
                  case 'M':
                  case 'T':
                  case 'l':
                  case 'm':
                  case 't':
                     break label192;
                  case 'Q':
                  case 'S':
                  case 'q':
                  case 's':
                     var15 = 4;
                     var5 = var14;
                     var6 = var13;
                     var7 = var11;
                     var8 = var12;
                     break label193;
                  case 'Z':
                  case 'z':
                     var0.close();
                     var0.moveTo(var10, var9);
                     var5 = var10;
                     var7 = var10;
                     var6 = var9;
                     var8 = var9;
                     break label192;
                  default:
                     var8 = var12;
                     var7 = var11;
                     var6 = var13;
                     var5 = var14;
                     break label192;
               }

               var8 = var12;
               var7 = var11;
               var6 = var13;
               var5 = var14;
               break label193;
            }

            var15 = 2;
         }

         int var17 = 0;
         int var16 = var2;
         var11 = var9;
         var12 = var10;

         for(var2 = var17; var2 < var4.length; var16 = var3) {
            label182: {
               boolean var20;
               boolean var21;
               if (var3 != 'A') {
                  int var18;
                  int var19;
                  if (var3 == 'C') {
                     var5 = var4[var2 + 0];
                     var7 = var4[var2 + 1];
                     var16 = var2 + 2;
                     var9 = var4[var16];
                     var17 = var2 + 3;
                     var8 = var4[var17];
                     var19 = var2 + 4;
                     var6 = var4[var19];
                     var18 = var2 + 5;
                     var0.cubicTo(var5, var7, var9, var8, var6, var4[var18]);
                     var5 = var4[var19];
                     var6 = var4[var18];
                     var7 = var4[var16];
                     var8 = var4[var17];
                     break label182;
                  }

                  if (var3 == 'H') {
                     var16 = var2 + 0;
                     var0.lineTo(var4[var16], var6);
                     var5 = var4[var16];
                     break label182;
                  }

                  if (var3 == 'Q') {
                     var19 = var2 + 0;
                     var7 = var4[var19];
                     var17 = var2 + 1;
                     var6 = var4[var17];
                     var16 = var2 + 2;
                     var5 = var4[var16];
                     var18 = var2 + 3;
                     var0.quadTo(var7, var6, var5, var4[var18]);
                     var7 = var4[var19];
                     var8 = var4[var17];
                     var5 = var4[var16];
                     var6 = var4[var18];
                     break label182;
                  }

                  if (var3 == 'V') {
                     var16 = var2 + 0;
                     var0.lineTo(var5, var4[var16]);
                     var6 = var4[var16];
                     break label182;
                  }

                  if (var3 != 'a') {
                     label171: {
                        label170: {
                           label169: {
                              if (var3 != 'c') {
                                 if (var3 == 'h') {
                                    var16 = var2 + 0;
                                    var0.rLineTo(var4[var16], 0.0F);
                                    var5 += var4[var16];
                                    break label182;
                                 }

                                 if (var3 != 'q') {
                                    if (var3 == 'v') {
                                       var16 = var2 + 0;
                                       var0.rLineTo(0.0F, var4[var16]);
                                       var9 = var4[var16];
                                       break label170;
                                    }

                                    if (var3 == 'L') {
                                       var16 = var2 + 0;
                                       var5 = var4[var16];
                                       var17 = var2 + 1;
                                       var0.lineTo(var5, var4[var17]);
                                       var5 = var4[var16];
                                       var6 = var4[var17];
                                       break label182;
                                    }

                                    if (var3 == 'M') {
                                       var5 = var4[var2 + 0];
                                       var6 = var4[var2 + 1];
                                       if (var2 > 0) {
                                          var0.lineTo(var5, var6);
                                          break label182;
                                       }

                                       var0.moveTo(var5, var6);
                                       break label171;
                                    }

                                    if (var3 == 'S') {
                                       label140: {
                                          if (var16 != 99 && var16 != 115 && var16 != 67) {
                                             var10 = var6;
                                             var9 = var5;
                                             if (var16 != 83) {
                                                break label140;
                                             }
                                          }

                                          var9 = var5 * 2.0F - var7;
                                          var10 = var6 * 2.0F - var8;
                                       }

                                       var18 = var2 + 0;
                                       var5 = var4[var18];
                                       var17 = var2 + 1;
                                       var6 = var4[var17];
                                       var19 = var2 + 2;
                                       var7 = var4[var19];
                                       var16 = var2 + 3;
                                       var0.cubicTo(var9, var10, var5, var6, var7, var4[var16]);
                                       var5 = var4[var18];
                                       var7 = var4[var17];
                                       var9 = var4[var19];
                                       var6 = var4[var16];
                                       break label169;
                                    }

                                    if (var3 == 'T') {
                                       label132: {
                                          if (var16 != 113 && var16 != 116 && var16 != 81) {
                                             var10 = var6;
                                             var9 = var5;
                                             if (var16 != 84) {
                                                break label132;
                                             }
                                          }

                                          var9 = var5 * 2.0F - var7;
                                          var10 = var6 * 2.0F - var8;
                                       }

                                       var17 = var2 + 0;
                                       var5 = var4[var17];
                                       var16 = var2 + 1;
                                       var0.quadTo(var9, var10, var5, var4[var16]);
                                       var5 = var4[var17];
                                       var6 = var4[var16];
                                       var8 = var10;
                                       var7 = var9;
                                       break label182;
                                    }

                                    if (var3 == 'l') {
                                       var17 = var2 + 0;
                                       var9 = var4[var17];
                                       var16 = var2 + 1;
                                       var0.rLineTo(var9, var4[var16]);
                                       var5 += var4[var17];
                                       var9 = var4[var16];
                                       break label170;
                                    }

                                    if (var3 == 'm') {
                                       var9 = var4[var2 + 0];
                                       var5 += var9;
                                       var10 = var4[var2 + 1];
                                       var6 += var10;
                                       if (var2 > 0) {
                                          var0.rLineTo(var9, var10);
                                          break label182;
                                       }

                                       var0.rMoveTo(var9, var10);
                                       break label171;
                                    }

                                    if (var3 != 's') {
                                       if (var3 != 't') {
                                          break label182;
                                       }

                                       if (var16 != 113 && var16 != 116 && var16 != 81 && var16 != 84) {
                                          var8 = 0.0F;
                                          var7 = 0.0F;
                                       } else {
                                          var7 = var5 - var7;
                                          var8 = var6 - var8;
                                       }

                                       var16 = var2 + 0;
                                       var9 = var4[var16];
                                       var17 = var2 + 1;
                                       var0.rQuadTo(var7, var8, var9, var4[var17]);
                                       var9 = var5 + var4[var16];
                                       var10 = var6 + var4[var17];
                                       var8 += var6;
                                       var7 += var5;
                                       var6 = var10;
                                       var5 = var9;
                                       break label182;
                                    }

                                    if (var16 != 99 && var16 != 115 && var16 != 67 && var16 != 83) {
                                       var8 = 0.0F;
                                       var7 = 0.0F;
                                    } else {
                                       var9 = var6 - var8;
                                       var8 = var5 - var7;
                                       var7 = var9;
                                    }

                                    var18 = var2 + 0;
                                    var10 = var4[var18];
                                    var16 = var2 + 1;
                                    var9 = var4[var16];
                                    var19 = var2 + 2;
                                    var13 = var4[var19];
                                    var17 = var2 + 3;
                                    var0.rCubicTo(var8, var7, var10, var9, var13, var4[var17]);
                                    var10 = var4[var18] + var5;
                                    var7 = var4[var16] + var6;
                                    var8 = var5 + var4[var19];
                                    var9 = var4[var17];
                                    var5 = var10;
                                 } else {
                                    var17 = var2 + 0;
                                    var9 = var4[var17];
                                    var19 = var2 + 1;
                                    var7 = var4[var19];
                                    var16 = var2 + 2;
                                    var8 = var4[var16];
                                    var18 = var2 + 3;
                                    var0.rQuadTo(var9, var7, var8, var4[var18]);
                                    var10 = var4[var17] + var5;
                                    var7 = var4[var19] + var6;
                                    var8 = var5 + var4[var16];
                                    var9 = var4[var18];
                                    var5 = var10;
                                 }
                              } else {
                                 var13 = var4[var2 + 0];
                                 var9 = var4[var2 + 1];
                                 var18 = var2 + 2;
                                 var7 = var4[var18];
                                 var17 = var2 + 3;
                                 var10 = var4[var17];
                                 var16 = var2 + 4;
                                 var8 = var4[var16];
                                 var19 = var2 + 5;
                                 var0.rCubicTo(var13, var9, var7, var10, var8, var4[var19]);
                                 var10 = var4[var18] + var5;
                                 var7 = var4[var17] + var6;
                                 var8 = var5 + var4[var16];
                                 var9 = var4[var19];
                                 var5 = var10;
                              }

                              var6 += var9;
                              var9 = var8;
                           }

                           var8 = var7;
                           var7 = var5;
                           var5 = var9;
                           break label182;
                        }

                        var6 += var9;
                        break label182;
                     }

                     var11 = var6;
                     var12 = var5;
                     break label182;
                  }

                  var16 = var2 + 5;
                  var8 = var4[var16];
                  var17 = var2 + 6;
                  var10 = var4[var17];
                  var9 = var4[var2 + 0];
                  var7 = var4[var2 + 1];
                  var13 = var4[var2 + 2];
                  if (var4[var2 + 3] != 0.0F) {
                     var20 = true;
                  } else {
                     var20 = false;
                  }

                  if (var4[var2 + 4] != 0.0F) {
                     var21 = true;
                  } else {
                     var21 = false;
                  }

                  drawArc(var0, var5, var6, var8 + var5, var10 + var6, var9, var7, var13, var20, var21);
                  var5 += var4[var16];
                  var6 += var4[var17];
               } else {
                  var16 = var2 + 5;
                  var8 = var4[var16];
                  var17 = var2 + 6;
                  var13 = var4[var17];
                  var9 = var4[var2 + 0];
                  var10 = var4[var2 + 1];
                  var7 = var4[var2 + 2];
                  if (var4[var2 + 3] != 0.0F) {
                     var20 = true;
                  } else {
                     var20 = false;
                  }

                  if (var4[var2 + 4] != 0.0F) {
                     var21 = true;
                  } else {
                     var21 = false;
                  }

                  drawArc(var0, var5, var6, var8, var13, var9, var10, var7, var20, var21);
                  var5 = var4[var16];
                  var6 = var4[var17];
               }

               var8 = var6;
               var7 = var5;
            }

            var2 += var15;
         }

         var1[0] = var5;
         var1[1] = var6;
         var1[2] = var7;
         var1[3] = var8;
         var1[4] = var12;
         var1[5] = var11;
      }

      private static void arcToBezier(Path var0, double var1, double var3, double var5, double var7, double var9, double var11, double var13, double var15, double var17) {
         int var43 = (int)Math.ceil(Math.abs(var17 * 4.0 / Math.PI));
         double var25 = Math.cos(var13);
         double var27 = Math.sin(var13);
         double var23 = Math.cos(var15);
         var13 = Math.sin(var15);
         double var19 = -var5;
         double var37 = var19 * var25;
         double var35 = var7 * var27;
         double var29 = var19 * var27;
         double var39 = var7 * var25;
         var19 = var17 / (double)var43;
         double var21 = var13 * var29 + var23 * var39;
         var7 = var37 * var13 - var35 * var23;
         int var44 = 0;
         var17 = var9;
         var9 = var7;
         var23 = var15;
         var13 = var11;
         var7 = var29;
         var15 = var17;
         var17 = var27;

         for(var11 = var25; var44 < var43; var13 = var25) {
            var29 = var23 + var19;
            double var31 = Math.sin(var29);
            double var41 = Math.cos(var29);
            double var33 = var1 + var5 * var11 * var41 - var35 * var31;
            var25 = var3 + var5 * var17 * var41 + var39 * var31;
            var27 = var37 * var31 - var35 * var41;
            var31 = var31 * var7 + var41 * var39;
            var41 = var29 - var23;
            var23 = Math.tan(var41 / 2.0);
            var23 = Math.sin(var41) * (Math.sqrt(var23 * 3.0 * var23 + 4.0) - 1.0) / 3.0;
            var0.rLineTo(0.0F, 0.0F);
            var0.cubicTo((float)(var15 + var9 * var23), (float)(var13 + var21 * var23), (float)(var33 - var23 * var27), (float)(var25 - var23 * var31), (float)var33, (float)var25);
            ++var44;
            var15 = var33;
            var23 = var29;
            var21 = var31;
            var9 = var27;
         }

      }

      private static void drawArc(Path var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7, boolean var8, boolean var9) {
         double var20 = Math.toRadians((double)var7);
         double var22 = Math.cos(var20);
         double var26 = Math.sin(var20);
         double var18 = (double)var1;
         double var30 = (double)var2;
         double var24 = (double)var5;
         double var10 = (var18 * var22 + var30 * var26) / var24;
         double var12 = (double)(-var1);
         double var28 = (double)var6;
         double var16 = (var12 * var26 + var30 * var22) / var28;
         double var14 = (double)var3;
         var12 = (double)var4;
         double var32 = (var14 * var22 + var12 * var26) / var24;
         double var34 = ((double)(-var3) * var26 + var12 * var22) / var28;
         double var36 = var10 - var32;
         double var38 = var16 - var34;
         var14 = (var10 + var32) / 2.0;
         var12 = (var16 + var34) / 2.0;
         double var42 = var36 * var36 + var38 * var38;
         if (var42 == 0.0) {
            Log.w("PathParser", " Points are coincident");
         } else {
            double var40 = 1.0 / var42 - 0.25;
            if (var40 < 0.0) {
               Log.w("PathParser", "Points are too far apart " + var42);
               float var44 = (float)(Math.sqrt(var42) / 1.99999);
               drawArc(var0, var1, var2, var3, var4, var5 * var44, var6 * var44, var7, var8, var9);
            } else {
               var40 = Math.sqrt(var40);
               var36 *= var40;
               var38 = var40 * var38;
               if (var8 == var9) {
                  var14 -= var38;
                  var12 += var36;
               } else {
                  var14 += var38;
                  var12 -= var36;
               }

               var36 = Math.atan2(var16 - var12, var10 - var14);
               var16 = Math.atan2(var34 - var12, var32 - var14) - var36;
               double var46;
               int var45 = (var46 = var16 - 0.0) == 0.0 ? 0 : (var46 < 0.0 ? -1 : 1);
               if (var45 >= 0) {
                  var8 = true;
               } else {
                  var8 = false;
               }

               var10 = var16;
               if (var9 != var8) {
                  if (var45 > 0) {
                     var10 = var16 - 6.283185307179586;
                  } else {
                     var10 = var16 + 6.283185307179586;
                  }
               }

               var14 *= var24;
               var12 *= var28;
               arcToBezier(var0, var14 * var22 - var12 * var26, var14 * var26 + var12 * var22, var24, var28, var18, var30, var20, var36, var10);
            }
         }
      }

      public static void nodesToPath(PathDataNode[] var0, Path var1) {
         float[] var5 = new float[6];
         char var2 = 'm';

         for(int var3 = 0; var3 < var0.length; ++var3) {
            PathDataNode var4 = var0[var3];
            addCommand(var1, var5, var2, var4.mType, var4.mParams);
            var2 = var0[var3].mType;
         }

      }

      public void interpolatePathDataNode(PathDataNode var1, PathDataNode var2, float var3) {
         this.mType = var1.mType;
         int var4 = 0;

         while(true) {
            float[] var5 = var1.mParams;
            if (var4 >= var5.length) {
               return;
            }

            this.mParams[var4] = var5[var4] * (1.0F - var3) + var2.mParams[var4] * var3;
            ++var4;
         }
      }
   }
}
