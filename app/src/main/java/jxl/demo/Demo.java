package jxl.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import jxl.Cell;
import jxl.Range;
import jxl.Workbook;
import jxl.common.Logger;

public class Demo {
   private static final int CSVFormat = 13;
   private static final int XMLFormat = 14;
   private static Logger logger = Logger.getLogger(Demo.class);

   private static void displayHelp() {
      System.err.println("Command format:  Demo [-unicode] [-csv] [-hide] excelfile");
      System.err.println("                 Demo -xml [-format]  excelfile");
      System.err.println("                 Demo -readwrite|-rw excelfile output");
      System.err.println("                 Demo -biffdump | -bd | -wa | -write | -formulas | -features | -escher | -escherdg excelfile");
      System.err.println("                 Demo -ps excelfile [property] [output]");
      System.err.println("                 Demo -version | -logtest | -h | -help");
   }

   private static void findTest(Workbook var0) {
      logger.info("Find test");
      Cell var3 = var0.findCellByName("named1");
      if (var3 != null) {
         logger.info("named1 contents:  " + var3.getContents());
      }

      var3 = var0.findCellByName("named2");
      if (var3 != null) {
         logger.info("named2 contents:  " + var3.getContents());
      }

      var3 = var0.findCellByName("namedrange");
      if (var3 != null) {
         logger.info("named2 contents:  " + var3.getContents());
      }

      Range[] var4 = var0.findByName("namedrange");
      byte var2 = 0;
      if (var4 != null) {
         var3 = var4[0].getTopLeft();
         logger.info("namedrange top left contents:  " + var3.getContents());
         var3 = var4[0].getBottomRight();
         logger.info("namedrange bottom right contents:  " + var3.getContents());
      }

      Range[] var7 = var0.findByName("nonadjacentrange");
      int var1;
      if (var7 != null) {
         for(var1 = 0; var1 < var7.length; ++var1) {
            Cell var6 = var7[var1].getTopLeft();
            logger.info("nonadjacent top left contents:  " + var6.getContents());
            var6 = var7[var1].getBottomRight();
            logger.info("nonadjacent bottom right contents:  " + var6.getContents());
         }
      }

      Range[] var5 = var0.findByName("horizontalnonadjacentrange");
      if (var5 != null) {
         for(var1 = var2; var1 < var5.length; ++var1) {
            var3 = var5[var1].getTopLeft();
            logger.info("horizontalnonadjacent top left contents:  " + var3.getContents());
            var3 = var5[var1].getBottomRight();
            logger.info("horizontalnonadjacent bottom right contents:  " + var3.getContents());
         }
      }

   }

   public static void main(String[] var0) {
      int var1 = var0.length;
      if (var1 == 0) {
         displayHelp();
         System.exit(1);
      }

      if (var0[0].equals("-help") || var0[0].equals("-h")) {
         displayHelp();
         System.exit(1);
      }

      if (var0[0].equals("-version")) {
         System.out.println("v" + Workbook.getVersion());
         System.exit(0);
      }

      if (var0[0].equals("-logtest")) {
         logger.debug("A sample \"debug\" message");
         logger.info("A sample \"info\" message");
         logger.warn("A sample \"warning\" message");
         logger.error("A sample \"error\" message");
         logger.fatal("A sample \"fatal\" message");
         System.exit(0);
      }

      boolean var2;
      boolean var3;
      boolean var4;
      boolean var260;
      boolean var5;
      boolean var6;
      boolean var7;
      boolean var8;
      boolean var9;
      boolean var12;
      String var14;
      String var15;
      String var16;
      String var17;
      label3731: {
         var12 = var0[0].equals("-write");
         var15 = null;
         var17 = null;
         if (var12) {
            var14 = var0[1];
            var2 = false;
            var3 = false;
            var4 = var3;
            var5 = var3;
            var6 = var3;
            var7 = var3;
            var260 = true;
         } else if (var0[0].equals("-formulas")) {
            var14 = var0[1];
            var260 = false;
            var3 = false;
            var4 = var3;
            var5 = var3;
            var6 = var3;
            var7 = var3;
            var2 = true;
         } else if (var0[0].equals("-features")) {
            var14 = var0[1];
            var260 = false;
            var2 = false;
            var3 = var2;
            var4 = var2;
            var6 = var2;
            var7 = var2;
            var5 = true;
         } else if (var0[0].equals("-escher")) {
            var14 = var0[1];
            var260 = false;
            var2 = false;
            var3 = var2;
            var4 = var2;
            var5 = var2;
            var7 = var2;
            var6 = true;
         } else if (var0[0].equals("-escherdg")) {
            var14 = var0[1];
            var260 = false;
            var2 = false;
            var3 = var2;
            var4 = var2;
            var5 = var2;
            var6 = var2;
            var7 = true;
         } else if (!var0[0].equals("-biffdump") && !var0[0].equals("-bd")) {
            if (var0[0].equals("-wa")) {
               var14 = var0[1];
               var260 = false;
               var2 = false;
               var3 = var2;
               var5 = var2;
               var6 = var2;
               var7 = var2;
               var4 = true;
            } else {
               if (var0[0].equals("-ps")) {
                  var14 = var0[1];
                  if (var0.length > 2) {
                     var16 = var0[2];
                  } else {
                     var16 = null;
                  }

                  var15 = var17;
                  if (var0.length == 4) {
                     var15 = var0[3];
                  }

                  var260 = false;
                  var8 = false;
                  var2 = var8;
                  var3 = var8;
                  var4 = var8;
                  var5 = var8;
                  var6 = var8;
                  var7 = var8;
                  var9 = true;
                  break label3731;
               }

               if (var0[0].equals("-readwrite") || var0[0].equals("-rw")) {
                  var14 = var0[1];
                  var15 = var0[2];
                  var260 = false;
                  var2 = false;
                  var3 = var2;
                  var4 = var2;
                  var9 = var2;
                  var5 = var2;
                  var6 = var2;
                  var7 = var2;
                  var8 = true;
                  var16 = null;
                  break label3731;
               }

               var14 = var0[var0.length - 1];
               var260 = false;
               var2 = false;
               var3 = var2;
               var4 = var2;
               var5 = var2;
               var6 = var2;
               var7 = var2;
            }
         } else {
            var14 = var0[1];
            var260 = false;
            var2 = false;
            var4 = var2;
            var5 = var2;
            var6 = var2;
            var7 = var2;
            var3 = true;
         }

         var9 = false;
         var8 = false;
         var16 = null;
      }

      var17 = "UTF8";
      String var258;
      byte var10;
      boolean var13;
      if (!var260 && !var8 && !var2 && !var3 && !var4 && !var9 && !var5 && !var6 && !var7) {
         var17 = "UTF8";
         int var11 = 0;
         var13 = false;
         var12 = false;

         for(var10 = 13; var11 < var0.length - 1; ++var11) {
            if (var0[var11].equals("-unicode")) {
               var17 = "UnicodeBig";
            } else if (var0[var11].equals("-xml")) {
               var10 = 14;
            } else if (var0[var11].equals("-csv")) {
               var10 = 13;
            } else if (var0[var11].equals("-format")) {
               var12 = true;
            } else if (var0[var11].equals("-hide")) {
               var13 = true;
            } else {
               System.err.println("Command format:  CSV [-unicode] [-xml|-csv] excelfile");
               System.exit(1);
            }
         }

         var258 = var17;
      } else {
         var12 = false;
         var13 = false;
         var10 = 13;
         var258 = var17;
      }

      Throwable var10000;
      boolean var10001;
      if (var260) {
         label3618:
         try {
            Write var259 = new Write(var14);
            var259.write();
            return;
         } catch (Throwable var243) {
            var10000 = var243;
            var10001 = false;
            break label3618;
         }
      } else {
         label3741: {
            if (var8) {
               try {
                  ReadWrite var261 = new ReadWrite(var14, var15);
                  var261.readWrite();
               } catch (Throwable var244) {
                  var10000 = var244;
                  var10001 = false;
                  break label3741;
               }
            } else {
               File var265;
               Workbook var266;
               if (var2) {
                  try {
                     var265 = new File(var14);
                     var266 = Workbook.getWorkbook(var265);
                     new Formulas(var266, System.out, var258);
                     var266.close();
                  } catch (Throwable var245) {
                     var10000 = var245;
                     var10001 = false;
                     break label3741;
                  }
               } else {
                  if (var5) {
                     try {
                        var265 = new File(var14);
                        var266 = Workbook.getWorkbook(var265);
                        new Features(var266, System.out, var258);
                        var266.close();
                     } catch (Throwable var246) {
                        var10000 = var246;
                        var10001 = false;
                        break label3741;
                     }
                  } else {
                     if (var6) {
                        try {
                           var265 = new File(var14);
                           var266 = Workbook.getWorkbook(var265);
                           new Escher(var266, System.out, var258);
                           var266.close();
                        } catch (Throwable var247) {
                           var10000 = var247;
                           var10001 = false;
                           break label3741;
                        }
                     } else {
                        if (var7) {
                           try {
                              var265 = new File(var14);
                              var266 = Workbook.getWorkbook(var265);
                              new EscherDrawingGroup(var266, System.out, var258);
                              var266.close();
                           } catch (Throwable var248) {
                              var10000 = var248;
                              var10001 = false;
                              break label3741;
                           }
                        } else {
                           File var262;
                           if (var3) {
                              try {
                                 var262 = new File(var14);
                                 new BiffDump(var262, System.out);
                              } catch (Throwable var249) {
                                 var10000 = var249;
                                 var10001 = false;
                                 break label3741;
                              }
                           } else {
                              if (var4) {
                                 try {
                                    var262 = new File(var14);
                                    new WriteAccess(var262);
                                 } catch (Throwable var250) {
                                    var10000 = var250;
                                    var10001 = false;
                                    break label3741;
                                 }
                              } else {
                                 if (var9) {
                                    Object var263;
                                    try {
                                       var263 = System.out;
                                    } catch (Throwable var253) {
                                       var10000 = var253;
                                       var10001 = false;
                                       break label3741;
                                    }

                                    if (var15 != null) {
                                       try {
                                          var263 = new FileOutputStream(var15);
                                       } catch (Throwable var252) {
                                          var10000 = var252;
                                          var10001 = false;
                                          break label3741;
                                       }
                                    }

                                    try {
                                       var265 = new File(var14);
                                       new PropertySetsReader(var265, var16, (OutputStream)var263);
                                    } catch (Throwable var251) {
                                       var10000 = var251;
                                       var10001 = false;
                                       break label3741;
                                    }
                                 } else {
                                    try {
                                       var265 = new File(var14);
                                       var266 = Workbook.getWorkbook(var265);
                                    } catch (Throwable var257) {
                                       var10000 = var257;
                                       var10001 = false;
                                       break label3741;
                                    }

                                    if (var10 == 13) {
                                       try {
                                          new CSV(var266, System.out, var258, var13);
                                       } catch (Throwable var256) {
                                          var10000 = var256;
                                          var10001 = false;
                                          break label3741;
                                       }
                                    } else if (var10 == 14) {
                                       try {
                                          new XML(var266, System.out, var258, var12);
                                       } catch (Throwable var255) {
                                          var10000 = var255;
                                          var10001 = false;
                                          break label3741;
                                       }
                                    }

                                    try {
                                       var266.close();
                                    } catch (Throwable var254) {
                                       var10000 = var254;
                                       var10001 = false;
                                       break label3741;
                                    }
                                 }

                                 return;
                              }

                              return;
                           }

                           return;
                        }

                        return;
                     }

                     return;
                  }

                  return;
               }

               return;
            }

            return;
         }
      }

      Throwable var264 = var10000;
      System.out.println(var264.toString());
      var264.printStackTrace();
   }
}
