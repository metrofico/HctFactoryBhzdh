package jxl.demo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class CSV {
   public CSV(Workbook var1, OutputStream var2, String var3, boolean var4) throws IOException {
      String var8;
      label119: {
         super();
         if (var3 != null) {
            var8 = var3;
            if (var3.equals("UnicodeBig")) {
               break label119;
            }
         }

         var8 = "UTF8";
      }

      UnsupportedEncodingException var10000;
      label122: {
         boolean var10001;
         BufferedWriter var22;
         try {
            OutputStreamWriter var23 = new OutputStreamWriter(var2, var8);
            var22 = new BufferedWriter(var23);
         } catch (UnsupportedEncodingException var20) {
            var10000 = var20;
            var10001 = false;
            break label122;
         }

         int var5 = 0;

         while(true) {
            Sheet var24;
            try {
               if (var5 >= var1.getNumberOfSheets()) {
                  break;
               }

               var24 = var1.getSheet(var5);
            } catch (UnsupportedEncodingException var14) {
               var10000 = var14;
               var10001 = false;
               break label122;
            }

            label124: {
               if (var4) {
                  try {
                     if (var24.getSettings().isHidden()) {
                        break label124;
                     }
                  } catch (UnsupportedEncodingException var19) {
                     var10000 = var19;
                     var10001 = false;
                     break label122;
                  }
               }

               try {
                  StringBuilder var25 = new StringBuilder();
                  var22.write(var25.append("*** ").append(var24.getName()).append(" ****").toString());
                  var22.newLine();
               } catch (UnsupportedEncodingException var13) {
                  var10000 = var13;
                  var10001 = false;
                  break label122;
               }

               int var6 = 0;

               while(true) {
                  label126: {
                     Cell[] var26;
                     try {
                        if (var6 >= var24.getRows()) {
                           break;
                        }

                        var26 = var24.getRow(var6);
                        if (var26.length <= 0) {
                           break label126;
                        }
                     } catch (UnsupportedEncodingException var16) {
                        var10000 = var16;
                        var10001 = false;
                        break label122;
                     }

                     label95: {
                        if (var4) {
                           try {
                              if (var26[0].isHidden()) {
                                 break label95;
                              }
                           } catch (UnsupportedEncodingException var18) {
                              var10000 = var18;
                              var10001 = false;
                              break label122;
                           }
                        }

                        try {
                           var22.write(var26[0].getContents());
                        } catch (UnsupportedEncodingException var12) {
                           var10000 = var12;
                           var10001 = false;
                           break label122;
                        }
                     }

                     int var7 = 1;

                     while(true) {
                        try {
                           if (var7 >= var26.length) {
                              break;
                           }

                           var22.write(44);
                        } catch (UnsupportedEncodingException var15) {
                           var10000 = var15;
                           var10001 = false;
                           break label122;
                        }

                        label86: {
                           if (var4) {
                              try {
                                 if (var26[var7].isHidden()) {
                                    break label86;
                                 }
                              } catch (UnsupportedEncodingException var17) {
                                 var10000 = var17;
                                 var10001 = false;
                                 break label122;
                              }
                           }

                           try {
                              var22.write(var26[var7].getContents());
                           } catch (UnsupportedEncodingException var11) {
                              var10000 = var11;
                              var10001 = false;
                              break label122;
                           }
                        }

                        ++var7;
                     }
                  }

                  try {
                     var22.newLine();
                  } catch (UnsupportedEncodingException var10) {
                     var10000 = var10;
                     var10001 = false;
                     break label122;
                  }

                  ++var6;
               }
            }

            ++var5;
         }

         try {
            var22.flush();
            var22.close();
            return;
         } catch (UnsupportedEncodingException var9) {
            var10000 = var9;
            var10001 = false;
         }
      }

      UnsupportedEncodingException var21 = var10000;
      System.err.println(var21.toString());
   }
}
