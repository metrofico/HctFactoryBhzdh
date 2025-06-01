package jxl.write.biff;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import jxl.biff.BaseCompoundFile;
import jxl.biff.IntegerHelper;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.read.biff.BiffException;

final class CompoundFile extends BaseCompoundFile {
   private static Logger logger = Logger.getLogger(CompoundFile.class);
   private int additionalPropertyBlocks;
   private ArrayList additionalPropertySets;
   private int bbdPos;
   private int bbdStartBlock;
   private byte[] bigBlockDepot;
   private ExcelDataOutput excelData;
   private int excelDataBlocks;
   private int excelDataStartBlock;
   private int extensionBlock;
   private int numBigBlockDepotBlocks;
   private int numExtensionBlocks;
   private int numPropertySets;
   private int numRootEntryBlocks;
   private int numSmallBlockDepotBlocks;
   private int numSmallBlockDepotChainBlocks;
   private int numSmallBlocks;
   private OutputStream out;
   private int requiredSize;
   private int rootStartBlock;
   private int sbdStartBlock;
   private int sbdStartBlockChain;
   private int size;
   private HashMap standardPropertySets;

   public CompoundFile(ExcelDataOutput var1, int var2, OutputStream var3, jxl.read.biff.CompoundFile var4) throws CopyAdditionalPropertySetsException, IOException {
      this.size = var2;
      this.excelData = var1;
      this.readAdditionalPropertySets(var4);
      this.numRootEntryBlocks = 1;
      ArrayList var7 = this.additionalPropertySets;
      int var5;
      if (var7 != null) {
         var5 = var7.size();
      } else {
         var5 = 0;
      }

      this.numPropertySets = var5 + 4;
      if (this.additionalPropertySets != null) {
         this.numSmallBlockDepotChainBlocks = this.getBigBlocksRequired(this.numSmallBlocks * 4);
         this.numSmallBlockDepotBlocks = this.getBigBlocksRequired(this.numSmallBlocks * 64);
         this.numRootEntryBlocks += this.getBigBlocksRequired(this.additionalPropertySets.size() * 128);
      }

      var5 = this.getBigBlocksRequired(var2);
      if (var2 < 4096) {
         this.requiredSize = 4096;
      } else {
         this.requiredSize = var5 * 512;
      }

      this.out = var3;
      var2 = this.requiredSize / 512;
      this.excelDataBlocks = var2;
      this.numBigBlockDepotBlocks = 1;
      var5 = var2 + 8 + 8 + this.additionalPropertyBlocks + this.numSmallBlockDepotBlocks + this.numSmallBlockDepotChainBlocks + this.numRootEntryBlocks;
      var2 = (int)Math.ceil((double)(var5 + 1) / 128.0);
      this.numBigBlockDepotBlocks = var2;
      int var6 = (int)Math.ceil((double)(var2 + var5) / 128.0);
      this.numBigBlockDepotBlocks = var6;
      var2 = var5 + var6;
      if (var6 > 108) {
         this.extensionBlock = 0;
         var2 = (int)Math.ceil((double)(var6 - 109 + 1) / 127.0);
         this.numExtensionBlocks = var2;
         var2 = (int)Math.ceil((double)(var2 + var5 + this.numBigBlockDepotBlocks) / 128.0);
         this.numBigBlockDepotBlocks = var2;
         var2 += var5 + this.numExtensionBlocks;
      } else {
         this.extensionBlock = -2;
         this.numExtensionBlocks = 0;
      }

      var5 = this.numExtensionBlocks;
      this.excelDataStartBlock = var5;
      this.sbdStartBlock = -2;
      if (this.additionalPropertySets != null && this.numSmallBlockDepotBlocks != 0) {
         this.sbdStartBlock = this.excelDataBlocks + var5 + this.additionalPropertyBlocks + 16;
      }

      this.sbdStartBlockChain = -2;
      var6 = this.sbdStartBlock;
      if (var6 != -2) {
         this.sbdStartBlockChain = var6 + this.numSmallBlockDepotBlocks;
      }

      var6 = this.sbdStartBlockChain;
      if (var6 != -2) {
         this.bbdStartBlock = var6 + this.numSmallBlockDepotChainBlocks;
      } else {
         this.bbdStartBlock = var5 + this.excelDataBlocks + this.additionalPropertyBlocks + 16;
      }

      var5 = this.bbdStartBlock + this.numBigBlockDepotBlocks;
      this.rootStartBlock = var5;
      if (var2 != var5 + this.numRootEntryBlocks) {
         logger.warn("Root start block and total blocks are inconsistent  generated file may be corrupt");
         logger.warn("RootStartBlock " + this.rootStartBlock + " totalBlocks " + var2);
      }

   }

   private void checkBbdPos() throws IOException {
      if (this.bbdPos >= 512) {
         this.out.write(this.bigBlockDepot);
         this.bigBlockDepot = new byte[512];
         this.bbdPos = 0;
      }

   }

   private int getBigBlocksRequired(int var1) {
      int var3 = var1 / 512;
      int var2 = var3;
      if (var1 % 512 > 0) {
         var2 = var3 + 1;
      }

      return var2;
   }

   private int getSmallBlocksRequired(int var1) {
      int var3 = var1 / 64;
      int var2 = var3;
      if (var1 % 64 > 0) {
         var2 = var3 + 1;
      }

      return var2;
   }

   private void readAdditionalPropertySets(jxl.read.biff.CompoundFile var1) throws CopyAdditionalPropertySetsException, IOException {
      if (var1 != null) {
         this.additionalPropertySets = new ArrayList();
         this.standardPropertySets = new HashMap();
         int var7 = var1.getNumberOfPropertySets();
         int var4 = 0;

         int var3;
         int var5;
         for(var3 = 0; var4 < var7; var3 = var5) {
            PropertyStorage var10 = var1.getPropertySet(var4);
            boolean var2;
            ReadPropertyStorage var9;
            if (var10.name.equalsIgnoreCase("Root Entry")) {
               var9 = new ReadPropertyStorage(var10, (byte[])null, var4);
               this.standardPropertySets.put("Root Entry", var9);
               var2 = true;
            } else {
               var2 = false;
            }

            boolean var6;
            for(var5 = 0; var5 < STANDARD_PROPERTY_SETS.length && !var2; var2 = var6) {
               var6 = var2;
               if (var10.name.equalsIgnoreCase(STANDARD_PROPERTY_SETS[var5])) {
                  PropertyStorage var18 = var1.findPropertyStorage(var10.name);
                  boolean var8;
                  if (var18 != null) {
                     var8 = true;
                  } else {
                     var8 = false;
                  }

                  Assert.verify(var8);
                  var6 = var2;
                  if (var18 == var10) {
                     var9 = new ReadPropertyStorage(var10, (byte[])null, var4);
                     this.standardPropertySets.put(STANDARD_PROPERTY_SETS[var5], var9);
                     var6 = true;
                  }
               }

               ++var5;
            }

            var5 = var3;
            if (!var2) {
               label96: {
                  BiffException var10000;
                  label94: {
                     boolean var10001;
                     byte[] var19;
                     label82: {
                        try {
                           if (var10.size > 0) {
                              var19 = var1.getStream(var4);
                              break label82;
                           }
                        } catch (BiffException var15) {
                           var10000 = var15;
                           var10001 = false;
                           break label94;
                        }

                        try {
                           var19 = new byte[0];
                        } catch (BiffException var13) {
                           var10000 = var13;
                           var10001 = false;
                           break label94;
                        }
                     }

                     try {
                        ReadPropertyStorage var11 = new ReadPropertyStorage(var10, var19, var4);
                        this.additionalPropertySets.add(var11);
                        if (var19.length > 4096) {
                           var5 = var3 + this.getBigBlocksRequired(var19.length);
                           break label96;
                        }
                     } catch (BiffException var14) {
                        var10000 = var14;
                        var10001 = false;
                        break label94;
                     }

                     try {
                        int var17 = this.getSmallBlocksRequired(var19.length);
                        this.numSmallBlocks += var17;
                     } catch (BiffException var12) {
                        var10000 = var12;
                        var10001 = false;
                        break label94;
                     }

                     var5 = var3;
                     break label96;
                  }

                  BiffException var16 = var10000;
                  logger.error(var16);
                  throw new CopyAdditionalPropertySetsException();
               }
            }

            ++var4;
         }

         this.additionalPropertyBlocks = var3;
      }
   }

   private void writeAdditionalPropertySetBlockChains() throws IOException {
      ArrayList var3 = this.additionalPropertySets;
      if (var3 != null) {
         int var1 = this.excelDataStartBlock + this.excelDataBlocks + 16;
         Iterator var4 = var3.iterator();

         while(var4.hasNext()) {
            ReadPropertyStorage var5 = (ReadPropertyStorage)var4.next();
            if (var5.data.length > 4096) {
               int var2 = this.getBigBlocksRequired(var5.data.length);
               this.writeBlockChain(var1, var2);
               var1 += var2;
            }
         }

      }
   }

   private void writeAdditionalPropertySets() throws IOException {
      ArrayList var2 = this.additionalPropertySets;
      if (var2 != null) {
         Iterator var4 = var2.iterator();

         while(var4.hasNext()) {
            byte[] var3 = ((ReadPropertyStorage)var4.next()).data;
            if (var3.length > 4096) {
               int var1 = this.getBigBlocksRequired(var3.length);
               this.out.write(var3, 0, var3.length);
               var1 = var1 * 512 - var3.length;
               var3 = new byte[var1];
               this.out.write(var3, 0, var1);
            }
         }

      }
   }

   private void writeBigBlockDepot() throws IOException {
      this.bigBlockDepot = new byte[512];
      byte var2 = 0;
      this.bbdPos = 0;

      int var1;
      for(var1 = 0; var1 < this.numExtensionBlocks; ++var1) {
         IntegerHelper.getFourBytes(-3, this.bigBlockDepot, this.bbdPos);
         this.bbdPos += 4;
         this.checkBbdPos();
      }

      this.writeBlockChain(this.excelDataStartBlock, this.excelDataBlocks);
      int var3 = this.excelDataStartBlock + this.excelDataBlocks + this.additionalPropertyBlocks;
      var1 = var3;

      while(var1 < var3 + 7) {
         ++var1;
         IntegerHelper.getFourBytes(var1, this.bigBlockDepot, this.bbdPos);
         this.bbdPos += 4;
         this.checkBbdPos();
      }

      IntegerHelper.getFourBytes(-2, this.bigBlockDepot, this.bbdPos);
      this.bbdPos += 4;
      this.checkBbdPos();
      var1 = var3 + 8;

      while(var1 < var3 + 15) {
         ++var1;
         IntegerHelper.getFourBytes(var1, this.bigBlockDepot, this.bbdPos);
         this.bbdPos += 4;
         this.checkBbdPos();
      }

      IntegerHelper.getFourBytes(-2, this.bigBlockDepot, this.bbdPos);
      this.bbdPos += 4;
      this.checkBbdPos();
      this.writeAdditionalPropertySetBlockChains();
      var3 = this.sbdStartBlock;
      var1 = var2;
      if (var3 != -2) {
         this.writeBlockChain(var3, this.numSmallBlockDepotBlocks);
         this.writeBlockChain(this.sbdStartBlockChain, this.numSmallBlockDepotChainBlocks);
         var1 = var2;
      }

      while(var1 < this.numBigBlockDepotBlocks) {
         IntegerHelper.getFourBytes(-3, this.bigBlockDepot, this.bbdPos);
         this.bbdPos += 4;
         this.checkBbdPos();
         ++var1;
      }

      this.writeBlockChain(this.rootStartBlock, this.numRootEntryBlocks);
      var1 = this.bbdPos;
      if (var1 != 0) {
         while(var1 < 512) {
            this.bigBlockDepot[var1] = -1;
            ++var1;
         }

         this.out.write(this.bigBlockDepot);
      }

   }

   private void writeBlockChain(int var1, int var2) throws IOException {
      --var2;
      int var3 = var1 + 1;
      var1 = var2;
      var2 = var3;

      while(var1 > 0) {
         int var4 = Math.min(var1, (512 - this.bbdPos) / 4);

         for(var3 = 0; var3 < var4; ++var3) {
            IntegerHelper.getFourBytes(var2, this.bigBlockDepot, this.bbdPos);
            this.bbdPos += 4;
            ++var2;
         }

         var1 -= var4;
         this.checkBbdPos();
      }

      IntegerHelper.getFourBytes(-2, this.bigBlockDepot, this.bbdPos);
      this.bbdPos += 4;
      this.checkBbdPos();
   }

   private void writeDocumentSummaryData() throws IOException {
      byte[] var1 = new byte[4096];
      this.out.write(var1);
   }

   private void writeExcelData() throws IOException {
      this.excelData.writeData(this.out);
      byte[] var1 = new byte[this.requiredSize - this.size];
      this.out.write(var1);
   }

   private void writeHeader() throws IOException {
      byte[] var7 = new byte[512];
      int var5 = this.numExtensionBlocks * 512;
      byte[] var8 = new byte[var5];
      System.arraycopy(IDENTIFIER, 0, var7, 0, IDENTIFIER.length);
      var7[24] = 62;
      var7[26] = 3;
      var7[28] = -2;
      var7[29] = -1;
      var7[30] = 9;
      var7[32] = 6;
      var7[57] = 16;
      IntegerHelper.getFourBytes(this.numBigBlockDepotBlocks, var7, 44);
      IntegerHelper.getFourBytes(this.sbdStartBlockChain, var7, 60);
      IntegerHelper.getFourBytes(this.numSmallBlockDepotChainBlocks, var7, 64);
      IntegerHelper.getFourBytes(this.extensionBlock, var7, 68);
      IntegerHelper.getFourBytes(this.numExtensionBlocks, var7, 72);
      IntegerHelper.getFourBytes(this.rootStartBlock, var7, 48);
      int var6 = Math.min(this.numBigBlockDepotBlocks, 109);
      int var2 = 76;
      int var3 = 0;
      int var1 = 0;

      while(true) {
         int var4 = var2;
         if (var3 >= var6) {
            while(var4 < 512) {
               var7[var4] = -1;
               ++var4;
            }

            this.out.write(var7);
            var2 = 0;
            byte var9 = 0;
            var3 = var1;
            var1 = var9;

            while(true) {
               var4 = this.numExtensionBlocks;
               if (var2 >= var4) {
                  if (var4 > 0) {
                     while(var1 < var5) {
                        var8[var1] = -1;
                        ++var1;
                     }

                     this.out.write(var8);
                  }

                  return;
               }

               var6 = Math.min(this.numBigBlockDepotBlocks - var3, 127);

               for(var4 = 0; var4 < var6; ++var4) {
                  IntegerHelper.getFourBytes(this.bbdStartBlock + var3 + var4, var8, var1);
                  var1 += 4;
               }

               var4 = var3 + var6;
               if (var4 == this.numBigBlockDepotBlocks) {
                  var3 = -2;
               } else {
                  var3 = var2 + 1;
               }

               IntegerHelper.getFourBytes(var3, var8, var1);
               var1 += 4;
               ++var2;
               var3 = var4;
            }
         }

         IntegerHelper.getFourBytes(this.bbdStartBlock + var3, var7, var2);
         var2 += 4;
         ++var1;
         ++var3;
      }
   }

   private void writePropertySets() throws IOException {
      int var1 = this.numRootEntryBlocks;
      int var3 = 512;
      byte[] var9 = new byte[var1 * 512];
      ArrayList var7 = this.additionalPropertySets;
      int var4 = 1;
      int[] var13;
      if (var7 != null) {
         int[] var8 = new int[this.numPropertySets];

         for(var1 = 0; var1 < STANDARD_PROPERTY_SETS.length; ++var1) {
            ReadPropertyStorage var12 = (ReadPropertyStorage)this.standardPropertySets.get(STANDARD_PROPERTY_SETS[var1]);
            if (var12 != null) {
               var8[var12.number] = var1;
            } else {
               logger.warn("Standard property set " + STANDARD_PROPERTY_SETS[var1] + " not present in source file");
            }
         }

         var1 = STANDARD_PROPERTY_SETS.length;
         Iterator var10 = this.additionalPropertySets.iterator();

         while(true) {
            var13 = var8;
            if (!var10.hasNext()) {
               break;
            }

            var8[((ReadPropertyStorage)var10.next()).number] = var1++;
         }
      } else {
         var13 = null;
      }

      int var2;
      Iterator var14;
      ReadPropertyStorage var17;
      if (this.additionalPropertySets != null) {
         var1 = this.getBigBlocksRequired(this.requiredSize) * 512 + 0 + this.getBigBlocksRequired(4096) * 512 + this.getBigBlocksRequired(4096) * 512;
         var14 = this.additionalPropertySets.iterator();

         while(true) {
            var2 = var1;
            if (!var14.hasNext()) {
               break;
            }

            var17 = (ReadPropertyStorage)var14.next();
            if (var17.propertyStorage.type != 1) {
               if (var17.propertyStorage.size >= 4096) {
                  var2 = this.getBigBlocksRequired(var17.propertyStorage.size) * 512;
               } else {
                  var2 = this.getSmallBlocksRequired(var17.propertyStorage.size) * 64;
               }

               var1 += var2;
            }
         }
      } else {
         var2 = 0;
      }

      PropertyStorage var15 = new PropertyStorage("Root Entry");
      var15.setType(5);
      var15.setStartBlock(this.sbdStartBlock);
      var15.setSize(var2);
      var15.setPrevious(-1);
      var15.setNext(-1);
      var15.setColour(0);
      if (this.additionalPropertySets != null) {
         var1 = var13[((ReadPropertyStorage)this.standardPropertySets.get("Root Entry")).propertyStorage.child];
      } else {
         var1 = 1;
      }

      var15.setChild(var1);
      System.arraycopy(var15.data, 0, var9, 0, 128);
      var15 = new PropertyStorage("Workbook");
      var15.setType(2);
      var15.setStartBlock(this.excelDataStartBlock);
      var15.setSize(this.requiredSize);
      ArrayList var18 = this.additionalPropertySets;
      int var5 = 3;
      if (var18 != null) {
         var17 = (ReadPropertyStorage)this.standardPropertySets.get("Workbook");
         if (var17.propertyStorage.previous != -1) {
            var1 = var13[var17.propertyStorage.previous];
         } else {
            var1 = -1;
         }

         if (var17.propertyStorage.next != -1) {
            var2 = var13[var17.propertyStorage.next];
         } else {
            var2 = -1;
         }
      } else {
         var2 = -1;
         var1 = 3;
      }

      var15.setPrevious(var1);
      var15.setNext(var2);
      var15.setChild(-1);
      System.arraycopy(var15.data, 0, var9, 128, 128);
      var15 = new PropertyStorage("\u0005SummaryInformation");
      var15.setType(2);
      var15.setStartBlock(this.excelDataStartBlock + this.excelDataBlocks);
      var15.setSize(4096);
      var2 = var4;
      var1 = var5;
      if (this.additionalPropertySets != null) {
         var17 = (ReadPropertyStorage)this.standardPropertySets.get("\u0005SummaryInformation");
         var2 = var4;
         var1 = var5;
         if (var17 != null) {
            if (var17.propertyStorage.previous != -1) {
               var2 = var13[var17.propertyStorage.previous];
            } else {
               var2 = -1;
            }

            if (var17.propertyStorage.next != -1) {
               var1 = var13[var17.propertyStorage.next];
            } else {
               var1 = -1;
            }
         }
      }

      var15.setPrevious(var2);
      var15.setNext(var1);
      var15.setChild(-1);
      System.arraycopy(var15.data, 0, var9, 256, 128);
      var15 = new PropertyStorage("\u0005DocumentSummaryInformation");
      var15.setType(2);
      var15.setStartBlock(this.excelDataStartBlock + this.excelDataBlocks + 8);
      var15.setSize(4096);
      var15.setPrevious(-1);
      var15.setNext(-1);
      var15.setChild(-1);
      System.arraycopy(var15.data, 0, var9, 384, 128);
      ArrayList var16 = this.additionalPropertySets;
      if (var16 == null) {
         this.out.write(var9);
      } else {
         var2 = this.excelDataStartBlock + this.excelDataBlocks + 16;
         var14 = var16.iterator();
         var1 = 0;

         while(var14.hasNext()) {
            ReadPropertyStorage var11 = (ReadPropertyStorage)var14.next();
            if (var11.data.length > 4096) {
               var4 = var2;
            } else {
               var4 = var1;
            }

            PropertyStorage var19 = new PropertyStorage(var11.propertyStorage.name);
            var19.setType(var11.propertyStorage.type);
            var19.setStartBlock(var4);
            var19.setSize(var11.propertyStorage.size);
            if (var11.propertyStorage.previous != -1) {
               var4 = var13[var11.propertyStorage.previous];
            } else {
               var4 = -1;
            }

            if (var11.propertyStorage.next != -1) {
               var5 = var13[var11.propertyStorage.next];
            } else {
               var5 = -1;
            }

            int var6;
            if (var11.propertyStorage.child != -1) {
               var6 = var13[var11.propertyStorage.child];
            } else {
               var6 = -1;
            }

            var19.setPrevious(var4);
            var19.setNext(var5);
            var19.setChild(var6);
            System.arraycopy(var19.data, 0, var9, var3, 128);
            var3 += 128;
            if (var11.data.length > 4096) {
               var2 += this.getBigBlocksRequired(var11.data.length);
            } else {
               var1 += this.getSmallBlocksRequired(var11.data.length);
            }
         }

         this.out.write(var9);
      }
   }

   private void writeSmallBlockDepot() throws IOException {
      ArrayList var4 = this.additionalPropertySets;
      if (var4 != null) {
         byte[] var3 = new byte[this.numSmallBlockDepotBlocks * 512];
         Iterator var5 = var4.iterator();
         int var1 = 0;

         while(var5.hasNext()) {
            ReadPropertyStorage var6 = (ReadPropertyStorage)var5.next();
            if (var6.data.length <= 4096) {
               int var2 = this.getSmallBlocksRequired(var6.data.length);
               System.arraycopy(var6.data, 0, var3, var1, var6.data.length);
               var1 += var2 * 64;
            }
         }

         this.out.write(var3);
      }
   }

   private void writeSmallBlockDepotChain() throws IOException {
      if (this.sbdStartBlockChain != -2) {
         byte[] var7 = new byte[this.numSmallBlockDepotChainBlocks * 512];
         Iterator var6 = this.additionalPropertySets.iterator();
         int var1 = 0;
         int var2 = 1;

         while(true) {
            ReadPropertyStorage var5;
            do {
               do {
                  if (!var6.hasNext()) {
                     this.out.write(var7);
                     return;
                  }

                  var5 = (ReadPropertyStorage)var6.next();
               } while(var5.data.length > 4096);
            } while(var5.data.length == 0);

            int var4 = this.getSmallBlocksRequired(var5.data.length);

            for(int var3 = 0; var3 < var4 - 1; ++var3) {
               IntegerHelper.getFourBytes(var2, var7, var1);
               var1 += 4;
               ++var2;
            }

            IntegerHelper.getFourBytes(-2, var7, var1);
            var1 += 4;
            ++var2;
         }
      }
   }

   private void writeSummaryData() throws IOException {
      byte[] var1 = new byte[4096];
      this.out.write(var1);
   }

   public void write() throws IOException {
      this.writeHeader();
      this.writeExcelData();
      this.writeDocumentSummaryData();
      this.writeSummaryData();
      this.writeAdditionalPropertySets();
      this.writeSmallBlockDepot();
      this.writeSmallBlockDepotChain();
      this.writeBigBlockDepot();
      this.writePropertySets();
   }

   private static final class ReadPropertyStorage {
      byte[] data;
      int number;
      PropertyStorage propertyStorage;

      ReadPropertyStorage(PropertyStorage var1, byte[] var2, int var3) {
         this.propertyStorage = var1;
         this.data = var2;
         this.number = var3;
      }
   }
}
