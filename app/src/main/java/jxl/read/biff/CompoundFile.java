package jxl.read.biff;

import java.util.ArrayList;
import java.util.Iterator;
import jxl.WorkbookSettings;
import jxl.biff.BaseCompoundFile;
import jxl.biff.IntegerHelper;
import jxl.common.Logger;

public final class CompoundFile extends BaseCompoundFile {
   private static Logger logger = Logger.getLogger(CompoundFile.class);
   private int[] bigBlockChain;
   private int[] bigBlockDepotBlocks;
   private byte[] data;
   private int extensionBlock;
   private int numBigBlockDepotBlocks;
   private int numExtensionBlocks;
   private ArrayList propertySets;
   private byte[] rootEntry;
   private PropertyStorage rootEntryPropertyStorage;
   private int rootStartBlock;
   private int sbdStartBlock;
   private WorkbookSettings settings;
   private int[] smallBlockChain;

   public CompoundFile(byte[] var1, WorkbookSettings var2) throws BiffException {
      this.data = var1;
      this.settings = var2;
      int var8 = 0;

      int var3;
      for(var3 = 0; var3 < IDENTIFIER.length; ++var3) {
         if (this.data[var3] != IDENTIFIER[var3]) {
            throw new BiffException(BiffException.unrecognizedOLEFile);
         }
      }

      this.propertySets = new ArrayList();
      byte[] var9 = this.data;
      this.numBigBlockDepotBlocks = IntegerHelper.getInt(var9[44], var9[45], var9[46], var9[47]);
      var9 = this.data;
      this.sbdStartBlock = IntegerHelper.getInt(var9[60], var9[61], var9[62], var9[63]);
      var9 = this.data;
      this.rootStartBlock = IntegerHelper.getInt(var9[48], var9[49], var9[50], var9[51]);
      var9 = this.data;
      this.extensionBlock = IntegerHelper.getInt(var9[68], var9[69], var9[70], var9[71]);
      var9 = this.data;
      var3 = IntegerHelper.getInt(var9[72], var9[73], var9[74], var9[75]);
      this.numExtensionBlocks = var3;
      int var4 = this.numBigBlockDepotBlocks;
      this.bigBlockDepotBlocks = new int[var4];
      int var7 = 76;
      if (var3 != 0) {
         var4 = 109;
      }

      int var6 = 0;

      while(true) {
         var3 = var4;
         int var5 = var8;
         if (var6 >= var4) {
            while(var5 < this.numExtensionBlocks) {
               var6 = (this.extensionBlock + 1) * 512;
               var8 = Math.min(this.numBigBlockDepotBlocks - var3, 127);
               var4 = var3;

               while(true) {
                  var7 = var3 + var8;
                  if (var4 >= var7) {
                     if (var7 < this.numBigBlockDepotBlocks) {
                        this.extensionBlock = IntegerHelper.getInt(var1[var6], var1[var6 + 1], var1[var6 + 2], var1[var6 + 3]);
                     }

                     ++var5;
                     var3 = var7;
                     break;
                  }

                  this.bigBlockDepotBlocks[var4] = IntegerHelper.getInt(var1[var6], var1[var6 + 1], var1[var6 + 2], var1[var6 + 3]);
                  var6 += 4;
                  ++var4;
               }
            }

            this.readBigBlockDepot();
            this.readSmallBlockDepot();
            this.rootEntry = this.readData(this.rootStartBlock);
            this.readPropertySets();
            return;
         }

         this.bigBlockDepotBlocks[var6] = IntegerHelper.getInt(var1[var7], var1[var7 + 1], var1[var7 + 2], var1[var7 + 3]);
         var7 += 4;
         ++var6;
      }
   }

   private PropertyStorage findPropertyStorage(String var1, PropertyStorage var2) {
      if (var2.child == -1) {
         return null;
      } else {
         var2 = this.getPropertyStorage(var2.child);
         if (var2.name.equalsIgnoreCase(var1)) {
            return var2;
         } else {
            PropertyStorage var3 = var2;

            PropertyStorage var4;
            do {
               if (var3.previous == -1) {
                  var3 = var2;

                  do {
                     if (var3.next == -1) {
                        return this.findPropertyStorage(var1, var2);
                     }

                     var4 = this.getPropertyStorage(var3.next);
                     var3 = var4;
                  } while(!var4.name.equalsIgnoreCase(var1));

                  return var4;
               }

               var4 = this.getPropertyStorage(var3.previous);
               var3 = var4;
            } while(!var4.name.equalsIgnoreCase(var1));

            return var4;
         }
      }
   }

   private byte[] getBigBlockStream(PropertyStorage var1) {
      int var3 = var1.size / 512;
      int var2 = var3;
      if (var1.size % 512 != 0) {
         var2 = var3 + 1;
      }

      byte[] var5 = new byte[var2 * 512];
      var3 = var1.startBlock;

      int var4;
      for(var4 = 0; var3 != -2 && var4 < var2; var3 = this.bigBlockChain[var3]) {
         System.arraycopy(this.data, (var3 + 1) * 512, var5, var4 * 512, 512);
         ++var4;
      }

      if (var3 != -2 && var4 == var2) {
         logger.warn("Property storage size inconsistent with block chain.");
      }

      return var5;
   }

   private PropertyStorage getPropertyStorage(int var1) {
      return (PropertyStorage)this.propertySets.get(var1);
   }

   private PropertyStorage getPropertyStorage(String var1) throws BiffException {
      Iterator var6 = this.propertySets.iterator();
      PropertyStorage var4 = null;
      boolean var2 = false;
      boolean var3 = false;

      while(var6.hasNext()) {
         PropertyStorage var5 = (PropertyStorage)var6.next();
         if (var5.name.equalsIgnoreCase(var1)) {
            if (var3) {
               var2 = true;
            } else {
               var2 = false;
            }

            var4 = var5;
            var3 = true;
         }
      }

      if (var2) {
         logger.warn("found multiple copies of property set " + var1);
      }

      if (var3) {
         return var4;
      } else {
         throw new BiffException(BiffException.streamNotFound);
      }
   }

   private byte[] getSmallBlockStream(PropertyStorage var1) throws BiffException {
      byte[] var6 = this.readData(this.rootEntryPropertyStorage.startBlock);
      byte[] var4 = new byte[0];
      int var2 = var1.startBlock;
      int var3 = 0;

      while(true) {
         int[] var5 = this.smallBlockChain;
         if (var3 > var5.length || var2 == -2) {
            if (var3 <= var5.length) {
               return var4;
            } else {
               throw new BiffException(BiffException.corruptFileFormat);
            }
         }

         byte[] var7 = new byte[var4.length + 64];
         System.arraycopy(var4, 0, var7, 0, var4.length);
         System.arraycopy(var6, var2 * 64, var7, var4.length, 64);
         var2 = this.smallBlockChain[var2];
         if (var2 == -1) {
            logger.warn("Incorrect terminator for small block stream " + var1.name);
            var2 = -2;
         }

         ++var3;
         var4 = var7;
      }
   }

   private void readBigBlockDepot() {
      this.bigBlockChain = new int[this.numBigBlockDepotBlocks * 512 / 4];
      int var1 = 0;

      for(int var3 = 0; var1 < this.numBigBlockDepotBlocks; ++var1) {
         int var4 = (this.bigBlockDepotBlocks[var1] + 1) * 512;

         for(int var2 = 0; var2 < 128; ++var2) {
            int[] var5 = this.bigBlockChain;
            byte[] var6 = this.data;
            var5[var3] = IntegerHelper.getInt(var6[var4], var6[var4 + 1], var6[var4 + 2], var6[var4 + 3]);
            var4 += 4;
            ++var3;
         }
      }

   }

   private byte[] readData(int var1) throws BiffException {
      byte[] var4 = new byte[0];
      int var3 = 0;
      int var2 = var1;
      var1 = var3;

      while(true) {
         int[] var5 = this.bigBlockChain;
         if (var1 > var5.length || var2 == -2) {
            if (var1 <= var5.length) {
               return var4;
            } else {
               throw new BiffException(BiffException.corruptFileFormat);
            }
         }

         byte[] var6 = new byte[var4.length + 512];
         System.arraycopy(var4, 0, var6, 0, var4.length);
         System.arraycopy(this.data, (var2 + 1) * 512, var6, var4.length, 512);
         var3 = this.bigBlockChain[var2];
         if (var3 == var2) {
            throw new BiffException(BiffException.corruptFileFormat);
         }

         ++var1;
         var2 = var3;
         var4 = var6;
      }
   }

   private void readPropertySets() {
      int var1 = 0;

      while(true) {
         byte[] var2 = this.rootEntry;
         if (var1 >= var2.length) {
            if (this.rootEntryPropertyStorage == null) {
               this.rootEntryPropertyStorage = (PropertyStorage)this.propertySets.get(0);
            }

            return;
         }

         byte[] var3 = new byte[128];
         System.arraycopy(var2, var1, var3, 0, 128);
         PropertyStorage var4 = new PropertyStorage(var3);
         if (var4.name == null || var4.name.length() == 0) {
            if (var4.type == 5) {
               var4.name = "Root Entry";
               logger.warn("Property storage name for " + var4.type + " is empty - setting to " + "Root Entry");
            } else if (var4.size != 0) {
               logger.warn("Property storage type " + var4.type + " is non-empty and has no associated name");
            }
         }

         this.propertySets.add(var4);
         if (var4.name.equalsIgnoreCase("Root Entry")) {
            this.rootEntryPropertyStorage = var4;
         }

         var1 += 128;
      }
   }

   private void readSmallBlockDepot() throws BiffException {
      int var2 = this.sbdStartBlock;
      this.smallBlockChain = new int[0];
      if (var2 == -1) {
         logger.warn("invalid small block depot number");
      } else {
         int var1 = 0;
         int var3 = 0;

         while(true) {
            int[] var6 = this.bigBlockChain;
            if (var1 > var6.length || var2 == -2) {
               if (var1 <= var6.length) {
                  return;
               } else {
                  throw new BiffException(BiffException.corruptFileFormat);
               }
            }

            int[] var7 = this.smallBlockChain;
            var6 = new int[var7.length + 128];
            this.smallBlockChain = var6;
            System.arraycopy(var7, 0, var6, 0, var7.length);
            int var5 = (var2 + 1) * 512;

            for(int var4 = 0; var4 < 128; ++var4) {
               var7 = this.smallBlockChain;
               byte[] var8 = this.data;
               var7[var3] = IntegerHelper.getInt(var8[var5], var8[var5 + 1], var8[var5 + 2], var8[var5 + 3]);
               var5 += 4;
               ++var3;
            }

            var2 = this.bigBlockChain[var2];
            ++var1;
         }
      }
   }

   public PropertyStorage findPropertyStorage(String var1) {
      return this.findPropertyStorage(var1, this.rootEntryPropertyStorage);
   }

   public int getNumberOfPropertySets() {
      return this.propertySets.size();
   }

   public PropertyStorage getPropertySet(int var1) {
      return this.getPropertyStorage(var1);
   }

   public byte[] getStream(int var1) throws BiffException {
      PropertyStorage var2 = this.getPropertyStorage(var1);
      return var2.size < 4096 && !var2.name.equalsIgnoreCase("Root Entry") ? this.getSmallBlockStream(var2) : this.getBigBlockStream(var2);
   }

   public byte[] getStream(String var1) throws BiffException {
      PropertyStorage var3 = this.findPropertyStorage(var1, this.rootEntryPropertyStorage);
      PropertyStorage var2 = var3;
      if (var3 == null) {
         var2 = this.getPropertyStorage(var1);
      }

      return var2.size < 4096 && !var1.equalsIgnoreCase("Root Entry") ? this.getSmallBlockStream(var2) : this.getBigBlockStream(var2);
   }
}
