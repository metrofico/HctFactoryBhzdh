package androidx.constraintlayout.solver;

public class ArrayRow implements LinearSystem.Row {
   private static final boolean DEBUG = false;
   private static final float epsilon = 0.001F;
   float constantValue = 0.0F;
   boolean isSimpleDefinition = false;
   boolean used = false;
   SolverVariable variable = null;
   public final ArrayLinkedVariables variables;

   public ArrayRow(Cache var1) {
      this.variables = new ArrayLinkedVariables(this, var1);
   }

   public ArrayRow addError(LinearSystem var1, int var2) {
      this.variables.put(var1.createErrorVariable(var2, "ep"), 1.0F);
      this.variables.put(var1.createErrorVariable(var2, "em"), -1.0F);
      return this;
   }

   public void addError(SolverVariable var1) {
      int var3 = var1.strength;
      float var2 = 1.0F;
      if (var3 != 1) {
         if (var1.strength == 2) {
            var2 = 1000.0F;
         } else if (var1.strength == 3) {
            var2 = 1000000.0F;
         } else if (var1.strength == 4) {
            var2 = 1.0E9F;
         } else if (var1.strength == 5) {
            var2 = 1.0E12F;
         }
      }

      this.variables.put(var1, var2);
   }

   ArrayRow addSingleError(SolverVariable var1, int var2) {
      this.variables.put(var1, (float)var2);
      return this;
   }

   boolean chooseSubject(LinearSystem var1) {
      SolverVariable var3 = this.variables.chooseSubject(var1);
      boolean var2;
      if (var3 == null) {
         var2 = true;
      } else {
         this.pivot(var3);
         var2 = false;
      }

      if (this.variables.currentSize == 0) {
         this.isSimpleDefinition = true;
      }

      return var2;
   }

   public void clear() {
      this.variables.clear();
      this.variable = null;
      this.constantValue = 0.0F;
   }

   ArrayRow createRowCentering(SolverVariable var1, SolverVariable var2, int var3, float var4, SolverVariable var5, SolverVariable var6, int var7) {
      if (var2 == var5) {
         this.variables.put(var1, 1.0F);
         this.variables.put(var6, 1.0F);
         this.variables.put(var2, -2.0F);
         return this;
      } else {
         if (var4 == 0.5F) {
            this.variables.put(var1, 1.0F);
            this.variables.put(var2, -1.0F);
            this.variables.put(var5, -1.0F);
            this.variables.put(var6, 1.0F);
            if (var3 > 0 || var7 > 0) {
               this.constantValue = (float)(-var3 + var7);
            }
         } else if (var4 <= 0.0F) {
            this.variables.put(var1, -1.0F);
            this.variables.put(var2, 1.0F);
            this.constantValue = (float)var3;
         } else if (var4 >= 1.0F) {
            this.variables.put(var5, -1.0F);
            this.variables.put(var6, 1.0F);
            this.constantValue = (float)var7;
         } else {
            ArrayLinkedVariables var9 = this.variables;
            float var8 = 1.0F - var4;
            var9.put(var1, var8 * 1.0F);
            this.variables.put(var2, var8 * -1.0F);
            this.variables.put(var5, -1.0F * var4);
            this.variables.put(var6, 1.0F * var4);
            if (var3 > 0 || var7 > 0) {
               this.constantValue = (float)(-var3) * var8 + (float)var7 * var4;
            }
         }

         return this;
      }
   }

   ArrayRow createRowDefinition(SolverVariable var1, int var2) {
      this.variable = var1;
      float var3 = (float)var2;
      var1.computedValue = var3;
      this.constantValue = var3;
      this.isSimpleDefinition = true;
      return this;
   }

   ArrayRow createRowDimensionPercent(SolverVariable var1, SolverVariable var2, SolverVariable var3, float var4) {
      this.variables.put(var1, -1.0F);
      this.variables.put(var2, 1.0F - var4);
      this.variables.put(var3, var4);
      return this;
   }

   public ArrayRow createRowDimensionRatio(SolverVariable var1, SolverVariable var2, SolverVariable var3, SolverVariable var4, float var5) {
      this.variables.put(var1, -1.0F);
      this.variables.put(var2, 1.0F);
      this.variables.put(var3, var5);
      this.variables.put(var4, -var5);
      return this;
   }

   public ArrayRow createRowEqualDimension(float var1, float var2, float var3, SolverVariable var4, int var5, SolverVariable var6, int var7, SolverVariable var8, int var9, SolverVariable var10, int var11) {
      if (var2 != 0.0F && var1 != var3) {
         var1 = var1 / var2 / (var3 / var2);
         this.constantValue = (float)(-var5 - var7) + (float)var9 * var1 + (float)var11 * var1;
         this.variables.put(var4, 1.0F);
         this.variables.put(var6, -1.0F);
         this.variables.put(var10, var1);
         this.variables.put(var8, -var1);
      } else {
         this.constantValue = (float)(-var5 - var7 + var9 + var11);
         this.variables.put(var4, 1.0F);
         this.variables.put(var6, -1.0F);
         this.variables.put(var10, 1.0F);
         this.variables.put(var8, -1.0F);
      }

      return this;
   }

   public ArrayRow createRowEqualMatchDimensions(float var1, float var2, float var3, SolverVariable var4, SolverVariable var5, SolverVariable var6, SolverVariable var7) {
      this.constantValue = 0.0F;
      if (var2 != 0.0F && var1 != var3) {
         if (var1 == 0.0F) {
            this.variables.put(var4, 1.0F);
            this.variables.put(var5, -1.0F);
         } else if (var3 == 0.0F) {
            this.variables.put(var6, 1.0F);
            this.variables.put(var7, -1.0F);
         } else {
            var1 = var1 / var2 / (var3 / var2);
            this.variables.put(var4, 1.0F);
            this.variables.put(var5, -1.0F);
            this.variables.put(var7, var1);
            this.variables.put(var6, -var1);
         }
      } else {
         this.variables.put(var4, 1.0F);
         this.variables.put(var5, -1.0F);
         this.variables.put(var7, 1.0F);
         this.variables.put(var6, -1.0F);
      }

      return this;
   }

   public ArrayRow createRowEquals(SolverVariable var1, int var2) {
      if (var2 < 0) {
         this.constantValue = (float)(var2 * -1);
         this.variables.put(var1, 1.0F);
      } else {
         this.constantValue = (float)var2;
         this.variables.put(var1, -1.0F);
      }

      return this;
   }

   public ArrayRow createRowEquals(SolverVariable var1, SolverVariable var2, int var3) {
      boolean var4 = false;
      boolean var5 = false;
      if (var3 != 0) {
         var4 = var5;
         int var6 = var3;
         if (var3 < 0) {
            var6 = var3 * -1;
            var4 = true;
         }

         this.constantValue = (float)var6;
      }

      if (!var4) {
         this.variables.put(var1, -1.0F);
         this.variables.put(var2, 1.0F);
      } else {
         this.variables.put(var1, 1.0F);
         this.variables.put(var2, -1.0F);
      }

      return this;
   }

   public ArrayRow createRowGreaterThan(SolverVariable var1, int var2, SolverVariable var3) {
      this.constantValue = (float)var2;
      this.variables.put(var1, -1.0F);
      return this;
   }

   public ArrayRow createRowGreaterThan(SolverVariable var1, SolverVariable var2, SolverVariable var3, int var4) {
      boolean var5 = false;
      boolean var6 = false;
      if (var4 != 0) {
         var5 = var6;
         int var7 = var4;
         if (var4 < 0) {
            var7 = var4 * -1;
            var5 = true;
         }

         this.constantValue = (float)var7;
      }

      if (!var5) {
         this.variables.put(var1, -1.0F);
         this.variables.put(var2, 1.0F);
         this.variables.put(var3, 1.0F);
      } else {
         this.variables.put(var1, 1.0F);
         this.variables.put(var2, -1.0F);
         this.variables.put(var3, -1.0F);
      }

      return this;
   }

   public ArrayRow createRowLowerThan(SolverVariable var1, SolverVariable var2, SolverVariable var3, int var4) {
      boolean var5 = false;
      boolean var6 = false;
      if (var4 != 0) {
         var5 = var6;
         int var7 = var4;
         if (var4 < 0) {
            var7 = var4 * -1;
            var5 = true;
         }

         this.constantValue = (float)var7;
      }

      if (!var5) {
         this.variables.put(var1, -1.0F);
         this.variables.put(var2, 1.0F);
         this.variables.put(var3, -1.0F);
      } else {
         this.variables.put(var1, 1.0F);
         this.variables.put(var2, -1.0F);
         this.variables.put(var3, 1.0F);
      }

      return this;
   }

   public ArrayRow createRowWithAngle(SolverVariable var1, SolverVariable var2, SolverVariable var3, SolverVariable var4, float var5) {
      this.variables.put(var3, 0.5F);
      this.variables.put(var4, 0.5F);
      this.variables.put(var1, -0.5F);
      this.variables.put(var2, -0.5F);
      this.constantValue = -var5;
      return this;
   }

   void ensurePositiveConstant() {
      float var1 = this.constantValue;
      if (var1 < 0.0F) {
         this.constantValue = var1 * -1.0F;
         this.variables.invert();
      }

   }

   public SolverVariable getKey() {
      return this.variable;
   }

   public SolverVariable getPivotCandidate(LinearSystem var1, boolean[] var2) {
      return this.variables.getPivotCandidate(var2, (SolverVariable)null);
   }

   boolean hasKeyVariable() {
      SolverVariable var2 = this.variable;
      boolean var1;
      if (var2 == null || var2.mType != SolverVariable.Type.UNRESTRICTED && this.constantValue < 0.0F) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   boolean hasVariable(SolverVariable var1) {
      return this.variables.containsKey(var1);
   }

   public void initFromRow(LinearSystem.Row var1) {
      if (var1 instanceof ArrayRow) {
         ArrayRow var4 = (ArrayRow)var1;
         this.variable = null;
         this.variables.clear();

         for(int var3 = 0; var3 < var4.variables.currentSize; ++var3) {
            SolverVariable var5 = var4.variables.getVariable(var3);
            float var2 = var4.variables.getVariableValue(var3);
            this.variables.add(var5, var2, true);
         }
      }

   }

   public boolean isEmpty() {
      boolean var1;
      if (this.variable == null && this.constantValue == 0.0F && this.variables.currentSize == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   SolverVariable pickPivot(SolverVariable var1) {
      return this.variables.getPivotCandidate((boolean[])null, var1);
   }

   void pivot(SolverVariable var1) {
      SolverVariable var3 = this.variable;
      if (var3 != null) {
         this.variables.put(var3, -1.0F);
         this.variable = null;
      }

      float var2 = this.variables.remove(var1, true) * -1.0F;
      this.variable = var1;
      if (var2 != 1.0F) {
         this.constantValue /= var2;
         this.variables.divideByAmount(var2);
      }
   }

   public void reset() {
      this.variable = null;
      this.variables.clear();
      this.constantValue = 0.0F;
      this.isSimpleDefinition = false;
   }

   int sizeInBytes() {
      byte var1;
      if (this.variable != null) {
         var1 = 4;
      } else {
         var1 = 0;
      }

      return var1 + 4 + 4 + this.variables.sizeInBytes();
   }

   String toReadableString() {
      String var7;
      if (this.variable == null) {
         var7 = "" + "0";
      } else {
         var7 = "" + this.variable;
      }

      var7 = var7 + " = ";
      float var1 = this.constantValue;
      int var4 = 0;
      boolean var3;
      if (var1 != 0.0F) {
         var7 = var7 + this.constantValue;
         var3 = true;
      } else {
         var3 = false;
      }

      String var10;
      for(int var5 = this.variables.currentSize; var4 < var5; ++var4) {
         SolverVariable var8 = this.variables.getVariable(var4);
         if (var8 != null) {
            float var2 = this.variables.getVariableValue(var4);
            float var11;
            int var6 = (var11 = var2 - 0.0F) == 0.0F ? 0 : (var11 < 0.0F ? -1 : 1);
            if (var6 != 0) {
               String var9;
               label51: {
                  var9 = var8.toString();
                  if (!var3) {
                     var10 = var7;
                     var1 = var2;
                     if (!(var2 < 0.0F)) {
                        break label51;
                     }

                     var10 = var7 + "- ";
                  } else {
                     if (var6 > 0) {
                        var10 = var7 + " + ";
                        var1 = var2;
                        break label51;
                     }

                     var10 = var7 + " - ";
                  }

                  var1 = var2 * -1.0F;
               }

               if (var1 == 1.0F) {
                  var7 = var10 + var9;
               } else {
                  var7 = var10 + var1 + " " + var9;
               }

               var3 = true;
            }
         }
      }

      var10 = var7;
      if (!var3) {
         var10 = var7 + "0.0";
      }

      return var10;
   }

   public String toString() {
      return this.toReadableString();
   }
}
