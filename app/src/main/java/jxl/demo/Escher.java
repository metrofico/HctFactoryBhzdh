package jxl.demo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import jxl.Workbook;
import jxl.biff.drawing.DrawingData;
import jxl.biff.drawing.EscherDisplay;
import jxl.read.biff.SheetImpl;

public class Escher {
   public Escher(Workbook var1, OutputStream var2, String var3) throws IOException {
      String var5;
      label55: {
         super();
         if (var3 != null) {
            var5 = var3;
            if (var3.equals("UnicodeBig")) {
               break label55;
            }
         }

         var5 = "UTF8";
      }

      UnsupportedEncodingException var10000;
      label58: {
         BufferedWriter var12;
         boolean var10001;
         try {
            OutputStreamWriter var13 = new OutputStreamWriter(var2, var5);
            var12 = new BufferedWriter(var13);
         } catch (UnsupportedEncodingException var10) {
            var10000 = var10;
            var10001 = false;
            break label58;
         }

         int var4 = 0;

         while(true) {
            DrawingData var15;
            try {
               if (var4 >= var1.getNumberOfSheets()) {
                  break;
               }

               SheetImpl var14 = (SheetImpl)var1.getSheet(var4);
               var12.write(var14.getName());
               var12.newLine();
               var12.newLine();
               var15 = var14.getDrawingData();
            } catch (UnsupportedEncodingException var9) {
               var10000 = var9;
               var10001 = false;
               break label58;
            }

            if (var15 != null) {
               try {
                  EscherDisplay var16 = new EscherDisplay(var15, var12);
                  var16.display();
               } catch (UnsupportedEncodingException var8) {
                  var10000 = var8;
                  var10001 = false;
                  break label58;
               }
            }

            try {
               var12.newLine();
               var12.newLine();
               var12.flush();
            } catch (UnsupportedEncodingException var7) {
               var10000 = var7;
               var10001 = false;
               break label58;
            }

            ++var4;
         }

         try {
            var12.flush();
            var12.close();
            return;
         } catch (UnsupportedEncodingException var6) {
            var10000 = var6;
            var10001 = false;
         }
      }

      UnsupportedEncodingException var11 = var10000;
      System.err.println(var11.toString());
   }
}
