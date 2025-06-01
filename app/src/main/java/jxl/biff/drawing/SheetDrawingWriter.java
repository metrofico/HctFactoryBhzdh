package jxl.biff.drawing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import jxl.WorkbookSettings;
import jxl.biff.IntegerHelper;
import jxl.common.Logger;
import jxl.write.biff.File;

public class SheetDrawingWriter {
   private static Logger logger = Logger.getLogger(SheetDrawingWriter.class);
   private Chart[] charts = new Chart[0];
   private ArrayList drawings;
   private boolean drawingsModified;
   private WorkbookSettings workbookSettings;

   public SheetDrawingWriter(WorkbookSettings var1) {
   }

   private void writeUnmodified(File var1) throws IOException {
      if (this.charts.length != 0 || this.drawings.size() != 0) {
         Iterator var19;
         if (this.charts.length == 0 && this.drawings.size() != 0) {
            Iterator var17 = this.drawings.iterator();

            while(var17.hasNext()) {
               DrawingGroupObject var20 = (DrawingGroupObject)var17.next();
               var1.write(var20.getMsoDrawingRecord());
               var20.writeAdditionalRecords(var1);
            }

            var19 = this.drawings.iterator();

            while(var19.hasNext()) {
               ((DrawingGroupObject)var19.next()).writeTailRecords(var1);
            }

         } else {
            int var3 = this.drawings.size();
            int var2 = 0;
            Chart[] var7;
            Chart var18;
            if (var3 == 0 && this.charts.length != 0) {
               while(true) {
                  var7 = this.charts;
                  if (var2 >= var7.length) {
                     return;
                  }

                  var18 = var7[var2];
                  if (var18.getMsoDrawingRecord() != null) {
                     var1.write(var18.getMsoDrawingRecord());
                  }

                  if (var18.getObjRecord() != null) {
                     var1.write(var18.getObjRecord());
                  }

                  var1.write(var18);
                  ++var2;
               }
            } else {
               int var6 = this.drawings.size();
               var7 = this.charts;
               int var5 = var7.length + var6;
               EscherContainer[] var9 = new EscherContainer[var5];
               boolean[] var10 = new boolean[var7.length + var6];
               int var4 = 0;

               EscherContainer var12;
               for(var2 = 0; var4 < var6; var2 = var3) {
                  DrawingGroupObject var8 = (DrawingGroupObject)this.drawings.get(var4);
                  var12 = var8.getSpContainer();
                  var9[var4] = var12;
                  var3 = var2;
                  if (var4 > 0) {
                     var3 = var2 + var12.getLength();
                  }

                  if (var8.isFormObject()) {
                     var10[var4] = true;
                  }

                  ++var4;
               }

               var3 = 0;

               while(true) {
                  var7 = this.charts;
                  if (var3 >= var7.length) {
                     DgContainer var11 = new DgContainer();
                     var11.add(new Dg(this.charts.length + var6));
                     SpgrContainer var13 = new SpgrContainer();
                     SpContainer var14 = new SpContainer();
                     var14.add(new Spgr());
                     var14.add(new Sp(ShapeType.MIN, 1024, 5));
                     var13.add(var14);
                     var13.add(var9[0]);
                     var11.add(var13);
                     byte[] var15 = var11.getData();
                     IntegerHelper.getFourBytes(IntegerHelper.getInt(var15[4], var15[5], var15[6], var15[7]) + var2, var15, 4);
                     IntegerHelper.getFourBytes(IntegerHelper.getInt(var15[28], var15[29], var15[30], var15[31]) + var2, var15, 28);
                     byte[] var16 = var15;
                     if (var10[0]) {
                        var2 = var15.length - 8;
                        var16 = new byte[var2];
                        System.arraycopy(var15, 0, var16, 0, var2);
                     }

                     var1.write(new MsoDrawingRecord(var16));
                     ((DrawingGroupObject)this.drawings.get(0)).writeAdditionalRecords(var1);

                     for(var2 = 1; var2 < var5; ++var2) {
                        var16 = var9[var2].getBytes();
                        var15 = var9[var2].setHeaderData(var16);
                        var16 = var15;
                        if (var10[var2]) {
                           var3 = var15.length - 8;
                           var16 = new byte[var3];
                           System.arraycopy(var15, 0, var16, 0, var3);
                        }

                        var1.write(new MsoDrawingRecord(var16));
                        if (var2 < var6) {
                           ((DrawingGroupObject)this.drawings.get(var2)).writeAdditionalRecords(var1);
                        } else {
                           var18 = this.charts[var2 - var6];
                           var1.write(var18.getObjRecord());
                           var1.write(var18);
                        }
                     }

                     var19 = this.drawings.iterator();

                     while(var19.hasNext()) {
                        ((DrawingGroupObject)var19.next()).writeTailRecords(var1);
                     }

                     return;
                  }

                  var12 = var7[var3].getSpContainer();
                  var9[var3 + var6] = var12;
                  var2 += var12.getLength();
                  ++var3;
               }
            }
         }
      }
   }

   public Chart[] getCharts() {
      return this.charts;
   }

   public void setCharts(Chart[] var1) {
      this.charts = var1;
   }

   public void setDrawings(ArrayList var1, boolean var2) {
      this.drawings = var1;
      this.drawingsModified = var2;
   }

   public void write(File var1) throws IOException {
      if (this.drawings.size() != 0 || this.charts.length != 0) {
         boolean var9 = this.drawingsModified;
         int var7 = this.drawings.size();
         Iterator var10 = this.drawings.iterator();

         while(true) {
            boolean var8 = var10.hasNext();
            byte var5 = 1;
            if (!var8 || var9) {
               var8 = var9;
               if (var7 > 0) {
                  var8 = var9;
                  if (!var9) {
                     var8 = var9;
                     if (!((DrawingGroupObject)this.drawings.get(0)).isFirst()) {
                        var8 = true;
                     }
                  }
               }

               var9 = var8;
               if (var7 == 0) {
                  Chart[] var16 = this.charts;
                  var9 = var8;
                  if (var16.length == 1) {
                     var9 = var8;
                     if (var16[0].getMsoDrawingRecord() == null) {
                        var9 = false;
                     }
                  }
               }

               if (!var9) {
                  this.writeUnmodified(var1);
                  return;
               } else {
                  int var6 = this.charts.length + var7;
                  Object[] var13 = new Object[var6];
                  EscherContainer var17 = null;
                  int var4 = 0;

                  int var2;
                  int var3;
                  EscherContainer var11;
                  byte[] var18;
                  for(var2 = 0; var4 < var7; var2 = var3) {
                     EscherContainer var12 = ((DrawingGroupObject)this.drawings.get(var4)).getSpContainer();
                     var11 = var17;
                     var3 = var2;
                     if (var12 != null) {
                        var18 = var12.getData();
                        var13[var4] = var18;
                        if (var4 == 0) {
                           var11 = var12;
                           var3 = var2;
                        } else {
                           var3 = var2 + var18.length;
                           var11 = var17;
                        }
                     }

                     ++var4;
                     var17 = var11;
                  }

                  byte var15 = 0;
                  var3 = var2;
                  var2 = var15;

                  while(true) {
                     Chart[] var21 = this.charts;
                     if (var2 >= var21.length) {
                        DgContainer var22 = new DgContainer();
                        var22.add(new Dg(this.charts.length + var7));
                        SpgrContainer var24 = new SpgrContainer();
                        SpContainer var14 = new SpContainer();
                        var14.add(new Spgr());
                        var14.add(new Sp(ShapeType.MIN, 1024, 5));
                        var24.add(var14);
                        var24.add(var17);
                        var22.add(var24);
                        var18 = var22.getData();
                        IntegerHelper.getFourBytes(IntegerHelper.getInt(var18[4], var18[5], var18[6], var18[7]) + var3, var18, 4);
                        IntegerHelper.getFourBytes(IntegerHelper.getInt(var18[28], var18[29], var18[30], var18[31]) + var3, var18, 28);
                        byte[] var19 = var18;
                        if (var7 > 0) {
                           var19 = var18;
                           if (((DrawingGroupObject)this.drawings.get(0)).isFormObject()) {
                              var2 = var18.length - 8;
                              var19 = new byte[var2];
                              System.arraycopy(var18, 0, var19, 0, var2);
                           }
                        }

                        var1.write(new MsoDrawingRecord(var19));
                        Chart var23;
                        if (var7 > 0) {
                           ((DrawingGroupObject)this.drawings.get(0)).writeAdditionalRecords(var1);
                           var2 = var5;
                        } else {
                           var23 = this.charts[0];
                           var1.write(var23.getObjRecord());
                           var1.write(var23);
                           var2 = var5;
                        }

                        for(; var2 < var6; ++var2) {
                           var18 = (byte[])var13[var2];
                           var19 = var18;
                           if (var2 < var7) {
                              var19 = var18;
                              if (((DrawingGroupObject)this.drawings.get(var2)).isFormObject()) {
                                 var3 = var18.length - 8;
                                 var19 = new byte[var3];
                                 System.arraycopy(var18, 0, var19, 0, var3);
                              }
                           }

                           var1.write(new MsoDrawingRecord(var19));
                           if (var2 < var7) {
                              ((DrawingGroupObject)this.drawings.get(var2)).writeAdditionalRecords(var1);
                           } else {
                              var23 = this.charts[var2 - var7];
                              var1.write(var23.getObjRecord());
                              var1.write(var23);
                           }
                        }

                        var10 = this.drawings.iterator();

                        while(var10.hasNext()) {
                           ((DrawingGroupObject)var10.next()).writeTailRecords(var1);
                        }

                        return;
                     }

                     var11 = var21[var2].getSpContainer();
                     byte[] var20 = var11.setHeaderData(var11.getBytes());
                     var13[var2 + var7] = var20;
                     if (var2 == 0 && var7 == 0) {
                        var17 = var11;
                     } else {
                        var3 += var20.length;
                     }

                     ++var2;
                  }
               }
            }

            if (((DrawingGroupObject)var10.next()).getOrigin() != Origin.READ) {
               var9 = true;
            }
         }
      }
   }
}
