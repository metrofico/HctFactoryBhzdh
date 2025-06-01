package jxl.demo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import jxl.Cell;
import jxl.CellFeatures;
import jxl.CellReferenceHelper;
import jxl.Sheet;
import jxl.Workbook;

public class Features {
   public Features(Workbook var1, OutputStream var2, String var3) throws IOException {
      String var7;
      label80: {
         super();
         if (var3 != null) {
            var7 = var3;
            if (var3.equals("UnicodeBig")) {
               break label80;
            }
         }

         var7 = "UTF8";
      }

      UnsupportedEncodingException var10000;
      label83: {
         boolean var10001;
         BufferedWriter var19;
         try {
            OutputStreamWriter var20 = new OutputStreamWriter(var2, var7);
            var19 = new BufferedWriter(var20);
         } catch (UnsupportedEncodingException var17) {
            var10000 = var17;
            var10001 = false;
            break label83;
         }

         int var4 = 0;

         while(true) {
            Sheet var21;
            try {
               if (var4 >= var1.getNumberOfSheets()) {
                  break;
               }

               var21 = var1.getSheet(var4);
               var19.write(var21.getName());
               var19.newLine();
            } catch (UnsupportedEncodingException var13) {
               var10000 = var13;
               var10001 = false;
               break label83;
            }

            int var5 = 0;

            while(true) {
               Cell[] var8;
               try {
                  if (var5 >= var21.getRows()) {
                     break;
                  }

                  var8 = var21.getRow(var5);
               } catch (UnsupportedEncodingException var14) {
                  var10000 = var14;
                  var10001 = false;
                  break label83;
               }

               int var6 = 0;

               while(true) {
                  try {
                     if (var6 >= var8.length) {
                        break;
                     }
                  } catch (UnsupportedEncodingException var15) {
                     var10000 = var15;
                     var10001 = false;
                     break label83;
                  }

                  Cell var9 = var8[var6];

                  try {
                     if (var9.getCellFeatures() != null) {
                        CellFeatures var22 = var9.getCellFeatures();
                        StringBuffer var10 = new StringBuffer();
                        CellReferenceHelper.getCellReference(var9.getColumn(), var9.getRow(), var10);
                        StringBuilder var11 = new StringBuilder();
                        var19.write(var11.append("Cell ").append(var10.toString()).append(" contents:  ").append(var9.getContents()).toString());
                        var19.flush();
                        StringBuilder var23 = new StringBuilder();
                        var19.write(var23.append(" comment: ").append(var22.getComment()).toString());
                        var19.flush();
                        var19.newLine();
                     }
                  } catch (UnsupportedEncodingException var16) {
                     var10000 = var16;
                     var10001 = false;
                     break label83;
                  }

                  ++var6;
               }

               ++var5;
            }

            ++var4;
         }

         try {
            var19.flush();
            var19.close();
            return;
         } catch (UnsupportedEncodingException var12) {
            var10000 = var12;
            var10001 = false;
         }
      }

      UnsupportedEncodingException var18 = var10000;
      System.err.println(var18.toString());
   }
}
