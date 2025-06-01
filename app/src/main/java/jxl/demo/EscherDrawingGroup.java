package jxl.demo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import jxl.Workbook;
import jxl.biff.drawing.DrawingGroup;
import jxl.biff.drawing.EscherDisplay;
import jxl.read.biff.WorkbookParser;

public class EscherDrawingGroup {
   public EscherDrawingGroup(Workbook var1, OutputStream var2, String var3) throws IOException {
      String var4;
      label36: {
         super();
         if (var3 != null) {
            var4 = var3;
            if (var3.equals("UnicodeBig")) {
               break label36;
            }
         }

         var4 = "UTF8";
      }

      UnsupportedEncodingException var10000;
      label39: {
         DrawingGroup var8;
         BufferedWriter var10;
         boolean var10001;
         try {
            OutputStreamWriter var11 = new OutputStreamWriter(var2, var4);
            var10 = new BufferedWriter(var11);
            var8 = ((WorkbookParser)var1).getDrawingGroup();
         } catch (UnsupportedEncodingException var7) {
            var10000 = var7;
            var10001 = false;
            break label39;
         }

         if (var8 != null) {
            try {
               EscherDisplay var12 = new EscherDisplay(var8, var10);
               var12.display();
            } catch (UnsupportedEncodingException var6) {
               var10000 = var6;
               var10001 = false;
               break label39;
            }
         }

         try {
            var10.newLine();
            var10.newLine();
            var10.flush();
            var10.close();
            return;
         } catch (UnsupportedEncodingException var5) {
            var10000 = var5;
            var10001 = false;
         }
      }

      UnsupportedEncodingException var9 = var10000;
      System.err.println(var9.toString());
   }
}
