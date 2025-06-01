package androidx.constraintlayout.solver;

import java.util.Arrays;

public class ArrayLinkedVariables {
   private static final boolean DEBUG = false;
   private static final boolean FULL_NEW_CHECK = false;
   private static final int NONE = -1;
   private int ROW_SIZE = 8;
   private SolverVariable candidate = null;
   int currentSize = 0;
   private int[] mArrayIndices = new int[8];
   private int[] mArrayNextIndices = new int[8];
   private float[] mArrayValues = new float[8];
   private final Cache mCache;
   private boolean mDidFillOnce = false;
   private int mHead = -1;
   private int mLast = -1;
   private final ArrayRow mRow;

   ArrayLinkedVariables(ArrayRow var1, Cache var2) {
      this.mRow = var1;
      this.mCache = var2;
   }

   private boolean isNew(SolverVariable var1, LinearSystem var2) {
      int var3 = var1.usageInRowCount;
      boolean var4 = true;
      if (var3 > 1) {
         var4 = false;
      }

      return var4;
   }

   final void add(SolverVariable var1, float var2, boolean var3) {
      if (var2 != 0.0F) {
         int var4 = this.mHead;
         int[] var9;
         if (var4 == -1) {
            this.mHead = 0;
            this.mArrayValues[0] = var2;
            this.mArrayIndices[0] = var1.id;
            this.mArrayNextIndices[this.mHead] = -1;
            ++var1.usageInRowCount;
            var1.addToRow(this.mRow);
            ++this.currentSize;
            if (!this.mDidFillOnce) {
               var4 = this.mLast + 1;
               this.mLast = var4;
               var9 = this.mArrayIndices;
               if (var4 >= var9.length) {
                  this.mDidFillOnce = true;
                  this.mLast = var9.length - 1;
               }
            }

         } else {
            int var5 = 0;

            int var7;
            int[] var10;
            for(var7 = -1; var4 != -1 && var5 < this.currentSize; ++var5) {
               if (this.mArrayIndices[var4] == var1.id) {
                  float[] var8 = this.mArrayValues;
                  var2 += var8[var4];
                  var8[var4] = var2;
                  if (var2 == 0.0F) {
                     if (var4 == this.mHead) {
                        this.mHead = this.mArrayNextIndices[var4];
                     } else {
                        var10 = this.mArrayNextIndices;
                        var10[var7] = var10[var4];
                     }

                     if (var3) {
                        var1.removeFromRow(this.mRow);
                     }

                     if (this.mDidFillOnce) {
                        this.mLast = var4;
                     }

                     --var1.usageInRowCount;
                     --this.currentSize;
                  }

                  return;
               }

               if (this.mArrayIndices[var4] < var1.id) {
                  var7 = var4;
               }

               var4 = this.mArrayNextIndices[var4];
            }

            var4 = this.mLast;
            if (this.mDidFillOnce) {
               var10 = this.mArrayIndices;
               if (var10[var4] != -1) {
                  var4 = var10.length;
               }
            } else {
               ++var4;
            }

            var10 = this.mArrayIndices;
            var5 = var4;
            if (var4 >= var10.length) {
               var5 = var4;
               if (this.currentSize < var10.length) {
                  int var6 = 0;

                  while(true) {
                     var10 = this.mArrayIndices;
                     var5 = var4;
                     if (var6 >= var10.length) {
                        break;
                     }

                     if (var10[var6] == -1) {
                        var5 = var6;
                        break;
                     }

                     ++var6;
                  }
               }
            }

            var10 = this.mArrayIndices;
            var4 = var5;
            if (var5 >= var10.length) {
               var4 = var10.length;
               var5 = this.ROW_SIZE * 2;
               this.ROW_SIZE = var5;
               this.mDidFillOnce = false;
               this.mLast = var4 - 1;
               this.mArrayValues = Arrays.copyOf(this.mArrayValues, var5);
               this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
               this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
            }

            this.mArrayIndices[var4] = var1.id;
            this.mArrayValues[var4] = var2;
            if (var7 != -1) {
               var10 = this.mArrayNextIndices;
               var10[var4] = var10[var7];
               var10[var7] = var4;
            } else {
               this.mArrayNextIndices[var4] = this.mHead;
               this.mHead = var4;
            }

            ++var1.usageInRowCount;
            var1.addToRow(this.mRow);
            ++this.currentSize;
            if (!this.mDidFillOnce) {
               ++this.mLast;
            }

            var4 = this.mLast;
            var9 = this.mArrayIndices;
            if (var4 >= var9.length) {
               this.mDidFillOnce = true;
               this.mLast = var9.length - 1;
            }

         }
      }
   }

   SolverVariable chooseSubject(LinearSystem var1) {
      int var8 = this.mHead;
      SolverVariable var17 = null;
      int var7 = 0;
      boolean var12 = false;
      boolean var11 = var12;
      float var6 = 0.0F;
      float var5 = 0.0F;

      float var4;
      SolverVariable var16;
      for(var16 = null; var8 != -1 && var7 < this.currentSize; var5 = var4) {
         float var2;
         float var3;
         SolverVariable var13;
         label68: {
            var3 = this.mArrayValues[var8];
            var13 = this.mCache.mIndexedVariables[this.mArrayIndices[var8]];
            if (var3 < 0.0F) {
               var2 = var3;
               if (!(var3 > -0.001F)) {
                  break label68;
               }

               this.mArrayValues[var8] = 0.0F;
               var13.removeFromRow(this.mRow);
            } else {
               var2 = var3;
               if (!(var3 < 0.001F)) {
                  break label68;
               }

               this.mArrayValues[var8] = 0.0F;
               var13.removeFromRow(this.mRow);
            }

            var2 = 0.0F;
         }

         SolverVariable var14 = var17;
         SolverVariable var15 = var16;
         boolean var9 = var12;
         boolean var10 = var11;
         var3 = var6;
         var4 = var5;
         if (var2 != 0.0F) {
            if (var13.mType == SolverVariable.Type.UNRESTRICTED) {
               label58: {
                  if (var16 == null) {
                     var9 = this.isNew(var13, var1);
                  } else {
                     if (!(var6 > var2)) {
                        var14 = var17;
                        var15 = var16;
                        var9 = var12;
                        var10 = var11;
                        var3 = var6;
                        var4 = var5;
                        if (!var12) {
                           var14 = var17;
                           var15 = var16;
                           var9 = var12;
                           var10 = var11;
                           var3 = var6;
                           var4 = var5;
                           if (this.isNew(var13, var1)) {
                              var9 = true;
                              var14 = var17;
                              var15 = var13;
                              var10 = var11;
                              var3 = var2;
                              var4 = var5;
                           }
                        }
                        break label58;
                     }

                     var9 = this.isNew(var13, var1);
                  }

                  var14 = var17;
                  var15 = var13;
                  var10 = var11;
                  var3 = var2;
                  var4 = var5;
               }
            } else {
               var14 = var17;
               var15 = var16;
               var9 = var12;
               var10 = var11;
               var3 = var6;
               var4 = var5;
               if (var16 == null) {
                  var14 = var17;
                  var15 = var16;
                  var9 = var12;
                  var10 = var11;
                  var3 = var6;
                  var4 = var5;
                  if (var2 < 0.0F) {
                     label51: {
                        if (var17 == null) {
                           var9 = this.isNew(var13, var1);
                        } else {
                           if (!(var5 > var2)) {
                              var14 = var17;
                              var15 = var16;
                              var9 = var12;
                              var10 = var11;
                              var3 = var6;
                              var4 = var5;
                              if (!var11) {
                                 var14 = var17;
                                 var15 = var16;
                                 var9 = var12;
                                 var10 = var11;
                                 var3 = var6;
                                 var4 = var5;
                                 if (this.isNew(var13, var1)) {
                                    var10 = true;
                                    var4 = var2;
                                    var3 = var6;
                                    var9 = var12;
                                    var15 = var16;
                                    var14 = var13;
                                 }
                              }
                              break label51;
                           }

                           var9 = this.isNew(var13, var1);
                        }

                        var10 = var9;
                        var14 = var13;
                        var15 = var16;
                        var9 = var12;
                        var3 = var6;
                        var4 = var2;
                     }
                  }
               }
            }
         }

         var8 = this.mArrayNextIndices[var8];
         ++var7;
         var17 = var14;
         var16 = var15;
         var12 = var9;
         var11 = var10;
         var6 = var3;
      }

      return var16 != null ? var16 : var17;
   }

   public final void clear() {
      int var2 = this.mHead;

      for(int var1 = 0; var2 != -1 && var1 < this.currentSize; ++var1) {
         SolverVariable var3 = this.mCache.mIndexedVariables[this.mArrayIndices[var2]];
         if (var3 != null) {
            var3.removeFromRow(this.mRow);
         }

         var2 = this.mArrayNextIndices[var2];
      }

      this.mHead = -1;
      this.mLast = -1;
      this.mDidFillOnce = false;
      this.currentSize = 0;
   }

   final boolean containsKey(SolverVariable var1) {
      int var3 = this.mHead;
      if (var3 == -1) {
         return false;
      } else {
         for(int var2 = 0; var3 != -1 && var2 < this.currentSize; ++var2) {
            if (this.mArrayIndices[var3] == var1.id) {
               return true;
            }

            var3 = this.mArrayNextIndices[var3];
         }

         return false;
      }
   }

   public void display() {
      int var2 = this.currentSize;
      System.out.print("{ ");

      for(int var1 = 0; var1 < var2; ++var1) {
         SolverVariable var3 = this.getVariable(var1);
         if (var3 != null) {
            System.out.print(var3 + " = " + this.getVariableValue(var1) + " ");
         }
      }

      System.out.println(" }");
   }

   void divideByAmount(float var1) {
      int var3 = this.mHead;

      for(int var2 = 0; var3 != -1 && var2 < this.currentSize; ++var2) {
         float[] var4 = this.mArrayValues;
         var4[var3] /= var1;
         var3 = this.mArrayNextIndices[var3];
      }

   }

   public final float get(SolverVariable var1) {
      int var3 = this.mHead;

      for(int var2 = 0; var3 != -1 && var2 < this.currentSize; ++var2) {
         if (this.mArrayIndices[var3] == var1.id) {
            return this.mArrayValues[var3];
         }

         var3 = this.mArrayNextIndices[var3];
      }

      return 0.0F;
   }

   SolverVariable getPivotCandidate() {
      SolverVariable var3 = this.candidate;
      if (var3 != null) {
         return var3;
      } else {
         int var2 = this.mHead;
         int var1 = 0;

         SolverVariable var4;
         for(var4 = null; var2 != -1 && var1 < this.currentSize; var4 = var3) {
            var3 = var4;
            if (this.mArrayValues[var2] < 0.0F) {
               label24: {
                  SolverVariable var5 = this.mCache.mIndexedVariables[this.mArrayIndices[var2]];
                  if (var4 != null) {
                     var3 = var4;
                     if (var4.strength >= var5.strength) {
                        break label24;
                     }
                  }

                  var3 = var5;
               }
            }

            var2 = this.mArrayNextIndices[var2];
            ++var1;
         }

         return var4;
      }
   }

   SolverVariable getPivotCandidate(boolean[] var1, SolverVariable var2) {
      int var7 = this.mHead;
      int var6 = 0;
      SolverVariable var9 = null;

      float var4;
      for(float var3 = 0.0F; var7 != -1 && var6 < this.currentSize; var3 = var4) {
         SolverVariable var8 = var9;
         var4 = var3;
         if (this.mArrayValues[var7] < 0.0F) {
            label30: {
               SolverVariable var10 = this.mCache.mIndexedVariables[this.mArrayIndices[var7]];
               if (var1 != null) {
                  var8 = var9;
                  var4 = var3;
                  if (var1[var10.id]) {
                     break label30;
                  }
               }

               var8 = var9;
               var4 = var3;
               if (var10 != var2) {
                  label25: {
                     if (var10.mType != SolverVariable.Type.SLACK) {
                        var8 = var9;
                        var4 = var3;
                        if (var10.mType != SolverVariable.Type.ERROR) {
                           break label25;
                        }
                     }

                     float var5 = this.mArrayValues[var7];
                     var8 = var9;
                     var4 = var3;
                     if (var5 < var3) {
                        var8 = var10;
                        var4 = var5;
                     }
                  }
               }
            }
         }

         var7 = this.mArrayNextIndices[var7];
         ++var6;
         var9 = var8;
      }

      return var9;
   }

   final SolverVariable getVariable(int var1) {
      int var3 = this.mHead;

      for(int var2 = 0; var3 != -1 && var2 < this.currentSize; ++var2) {
         if (var2 == var1) {
            return this.mCache.mIndexedVariables[this.mArrayIndices[var3]];
         }

         var3 = this.mArrayNextIndices[var3];
      }

      return null;
   }

   final float getVariableValue(int var1) {
      int var3 = this.mHead;

      for(int var2 = 0; var3 != -1 && var2 < this.currentSize; ++var2) {
         if (var2 == var1) {
            return this.mArrayValues[var3];
         }

         var3 = this.mArrayNextIndices[var3];
      }

      return 0.0F;
   }

   boolean hasAtLeastOnePositiveVariable() {
      int var2 = this.mHead;

      for(int var1 = 0; var2 != -1 && var1 < this.currentSize; ++var1) {
         if (this.mArrayValues[var2] > 0.0F) {
            return true;
         }

         var2 = this.mArrayNextIndices[var2];
      }

      return false;
   }

   void invert() {
      int var2 = this.mHead;

      for(int var1 = 0; var2 != -1 && var1 < this.currentSize; ++var1) {
         float[] var3 = this.mArrayValues;
         var3[var2] *= -1.0F;
         var2 = this.mArrayNextIndices[var2];
      }

   }

   public final void put(SolverVariable var1, float var2) {
      if (var2 == 0.0F) {
         this.remove(var1, true);
      } else {
         int var3 = this.mHead;
         int[] var8;
         if (var3 == -1) {
            this.mHead = 0;
            this.mArrayValues[0] = var2;
            this.mArrayIndices[0] = var1.id;
            this.mArrayNextIndices[this.mHead] = -1;
            ++var1.usageInRowCount;
            var1.addToRow(this.mRow);
            ++this.currentSize;
            if (!this.mDidFillOnce) {
               var3 = this.mLast + 1;
               this.mLast = var3;
               var8 = this.mArrayIndices;
               if (var3 >= var8.length) {
                  this.mDidFillOnce = true;
                  this.mLast = var8.length - 1;
               }
            }

         } else {
            int var4 = 0;

            int var6;
            for(var6 = -1; var3 != -1 && var4 < this.currentSize; ++var4) {
               if (this.mArrayIndices[var3] == var1.id) {
                  this.mArrayValues[var3] = var2;
                  return;
               }

               if (this.mArrayIndices[var3] < var1.id) {
                  var6 = var3;
               }

               var3 = this.mArrayNextIndices[var3];
            }

            var3 = this.mLast;
            int[] var7;
            if (this.mDidFillOnce) {
               var7 = this.mArrayIndices;
               if (var7[var3] != -1) {
                  var3 = var7.length;
               }
            } else {
               ++var3;
            }

            var7 = this.mArrayIndices;
            var4 = var3;
            if (var3 >= var7.length) {
               var4 = var3;
               if (this.currentSize < var7.length) {
                  int var5 = 0;

                  while(true) {
                     var7 = this.mArrayIndices;
                     var4 = var3;
                     if (var5 >= var7.length) {
                        break;
                     }

                     if (var7[var5] == -1) {
                        var4 = var5;
                        break;
                     }

                     ++var5;
                  }
               }
            }

            var7 = this.mArrayIndices;
            var3 = var4;
            if (var4 >= var7.length) {
               var3 = var7.length;
               var4 = this.ROW_SIZE * 2;
               this.ROW_SIZE = var4;
               this.mDidFillOnce = false;
               this.mLast = var3 - 1;
               this.mArrayValues = Arrays.copyOf(this.mArrayValues, var4);
               this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
               this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
            }

            this.mArrayIndices[var3] = var1.id;
            this.mArrayValues[var3] = var2;
            if (var6 != -1) {
               var7 = this.mArrayNextIndices;
               var7[var3] = var7[var6];
               var7[var6] = var3;
            } else {
               this.mArrayNextIndices[var3] = this.mHead;
               this.mHead = var3;
            }

            ++var1.usageInRowCount;
            var1.addToRow(this.mRow);
            var3 = this.currentSize + 1;
            this.currentSize = var3;
            if (!this.mDidFillOnce) {
               ++this.mLast;
            }

            var8 = this.mArrayIndices;
            if (var3 >= var8.length) {
               this.mDidFillOnce = true;
            }

            if (this.mLast >= var8.length) {
               this.mDidFillOnce = true;
               this.mLast = var8.length - 1;
            }

         }
      }
   }

   public final float remove(SolverVariable var1, boolean var2) {
      if (this.candidate == var1) {
         this.candidate = null;
      }

      int var3 = this.mHead;
      if (var3 == -1) {
         return 0.0F;
      } else {
         int var5 = 0;

         int var6;
         for(int var4 = -1; var3 != -1 && var5 < this.currentSize; var3 = var6) {
            if (this.mArrayIndices[var3] == var1.id) {
               if (var3 == this.mHead) {
                  this.mHead = this.mArrayNextIndices[var3];
               } else {
                  int[] var7 = this.mArrayNextIndices;
                  var7[var4] = var7[var3];
               }

               if (var2) {
                  var1.removeFromRow(this.mRow);
               }

               --var1.usageInRowCount;
               --this.currentSize;
               this.mArrayIndices[var3] = -1;
               if (this.mDidFillOnce) {
                  this.mLast = var3;
               }

               return this.mArrayValues[var3];
            }

            var6 = this.mArrayNextIndices[var3];
            ++var5;
            var4 = var3;
         }

         return 0.0F;
      }
   }

   int sizeInBytes() {
      return this.mArrayIndices.length * 4 * 3 + 0 + 36;
   }

   public String toString() {
      int var2 = this.mHead;
      String var3 = "";

      for(int var1 = 0; var2 != -1 && var1 < this.currentSize; ++var1) {
         var3 = var3 + " -> ";
         var3 = var3 + this.mArrayValues[var2] + " : ";
         var3 = var3 + this.mCache.mIndexedVariables[this.mArrayIndices[var2]];
         var2 = this.mArrayNextIndices[var2];
      }

      return var3;
   }

   final void updateFromRow(ArrayRow var1, ArrayRow var2, boolean var3) {
      int var5 = this.mHead;

      label37:
      while(true) {
         for(int var6 = 0; var5 != -1 && var6 < this.currentSize; ++var6) {
            if (this.mArrayIndices[var5] == var2.variable.id) {
               float var4 = this.mArrayValues[var5];
               this.remove(var2.variable, var3);
               ArrayLinkedVariables var7 = var2.variables;
               ArrayLinkedVariables var8 = (ArrayLinkedVariables)var7;
               var6 = var7.mHead;

               for(var5 = 0; var6 != -1 && var5 < var7.currentSize; ++var5) {
                  this.add(this.mCache.mIndexedVariables[var7.mArrayIndices[var6]], var7.mArrayValues[var6] * var4, var3);
                  var6 = var7.mArrayNextIndices[var6];
               }

               var1.constantValue += var2.constantValue * var4;
               if (var3) {
                  var2.variable.removeFromRow(var1);
               }

               var5 = this.mHead;
               continue label37;
            }

            var5 = this.mArrayNextIndices[var5];
         }

         return;
      }
   }

   void updateFromSystem(ArrayRow var1, ArrayRow[] var2) {
      int var4 = this.mHead;

      label35:
      while(true) {
         for(int var5 = 0; var4 != -1 && var5 < this.currentSize; ++var5) {
            SolverVariable var6 = this.mCache.mIndexedVariables[this.mArrayIndices[var4]];
            if (var6.definitionId != -1) {
               float var3 = this.mArrayValues[var4];
               this.remove(var6, true);
               ArrayRow var7 = var2[var6.definitionId];
               if (!var7.isSimpleDefinition) {
                  ArrayLinkedVariables var8 = var7.variables;
                  ArrayLinkedVariables var9 = (ArrayLinkedVariables)var8;
                  var5 = var8.mHead;

                  for(var4 = 0; var5 != -1 && var4 < var8.currentSize; ++var4) {
                     this.add(this.mCache.mIndexedVariables[var8.mArrayIndices[var5]], var8.mArrayValues[var5] * var3, true);
                     var5 = var8.mArrayNextIndices[var5];
                  }
               }

               var1.constantValue += var7.constantValue * var3;
               var7.variable.removeFromRow(var1);
               var4 = this.mHead;
               continue label35;
            }

            var4 = this.mArrayNextIndices[var4];
         }

         return;
      }
   }
}
