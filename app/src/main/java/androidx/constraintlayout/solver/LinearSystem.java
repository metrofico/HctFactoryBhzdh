package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;

public class LinearSystem {
   private static final boolean DEBUG = false;
   public static final boolean FULL_DEBUG = false;
   private static int POOL_SIZE;
   public static Metrics sMetrics;
   private int TABLE_SIZE = 32;
   public boolean graphOptimizer = false;
   private boolean[] mAlreadyTestedCandidates = new boolean[32];
   final Cache mCache;
   private Row mGoal;
   private int mMaxColumns = 32;
   private int mMaxRows = 32;
   int mNumColumns = 1;
   int mNumRows = 0;
   private SolverVariable[] mPoolVariables;
   private int mPoolVariablesCount;
   ArrayRow[] mRows = null;
   private final Row mTempGoal;
   private HashMap mVariables = null;
   int mVariablesID = 0;
   private ArrayRow[] tempClientsCopy;

   public LinearSystem() {
      this.mPoolVariables = new SolverVariable[POOL_SIZE];
      this.mPoolVariablesCount = 0;
      this.tempClientsCopy = new ArrayRow[32];
      this.mRows = new ArrayRow[32];
      this.releaseRows();
      Cache var1 = new Cache();
      this.mCache = var1;
      this.mGoal = new GoalRow(var1);
      this.mTempGoal = new ArrayRow(var1);
   }

   private SolverVariable acquireSolverVariable(SolverVariable.Type var1, String var2) {
      SolverVariable var5 = (SolverVariable)this.mCache.solverVariablePool.acquire();
      SolverVariable var6;
      if (var5 == null) {
         var5 = new SolverVariable(var1, var2);
         var5.setType(var1, var2);
         var6 = var5;
      } else {
         var5.reset();
         var5.setType(var1, var2);
         var6 = var5;
      }

      int var4 = this.mPoolVariablesCount;
      int var3 = POOL_SIZE;
      if (var4 >= var3) {
         var3 *= 2;
         POOL_SIZE = var3;
         this.mPoolVariables = (SolverVariable[])Arrays.copyOf(this.mPoolVariables, var3);
      }

      SolverVariable[] var7 = this.mPoolVariables;
      var3 = this.mPoolVariablesCount++;
      var7[var3] = var6;
      return var6;
   }

   private void addError(ArrayRow var1) {
      var1.addError(this, 0);
   }

   private final void addRow(ArrayRow var1) {
      if (this.mRows[this.mNumRows] != null) {
         this.mCache.arrayRowPool.release(this.mRows[this.mNumRows]);
      }

      this.mRows[this.mNumRows] = var1;
      var1.variable.definitionId = this.mNumRows++;
      var1.variable.updateReferencesWithNewDefinition(var1);
   }

   private void addSingleError(ArrayRow var1, int var2) {
      this.addSingleError(var1, var2, 0);
   }

   private void computeValues() {
      for(int var1 = 0; var1 < this.mNumRows; ++var1) {
         ArrayRow var2 = this.mRows[var1];
         var2.variable.computedValue = var2.constantValue;
      }

   }

   public static ArrayRow createRowCentering(LinearSystem var0, SolverVariable var1, SolverVariable var2, int var3, float var4, SolverVariable var5, SolverVariable var6, int var7, boolean var8) {
      ArrayRow var9 = var0.createRow();
      var9.createRowCentering(var1, var2, var3, var4, var5, var6, var7);
      if (var8) {
         var9.addError(var0, 4);
      }

      return var9;
   }

   public static ArrayRow createRowDimensionPercent(LinearSystem var0, SolverVariable var1, SolverVariable var2, SolverVariable var3, float var4, boolean var5) {
      ArrayRow var6 = var0.createRow();
      if (var5) {
         var0.addError(var6);
      }

      return var6.createRowDimensionPercent(var1, var2, var3, var4);
   }

   public static ArrayRow createRowEquals(LinearSystem var0, SolverVariable var1, SolverVariable var2, int var3, boolean var4) {
      ArrayRow var5 = var0.createRow();
      var5.createRowEquals(var1, var2, var3);
      if (var4) {
         var0.addSingleError(var5, 1);
      }

      return var5;
   }

   public static ArrayRow createRowGreaterThan(LinearSystem var0, SolverVariable var1, SolverVariable var2, int var3, boolean var4) {
      SolverVariable var5 = var0.createSlackVariable();
      ArrayRow var6 = var0.createRow();
      var6.createRowGreaterThan(var1, var2, var5, var3);
      if (var4) {
         var0.addSingleError(var6, (int)(var6.variables.get(var5) * -1.0F));
      }

      return var6;
   }

   public static ArrayRow createRowLowerThan(LinearSystem var0, SolverVariable var1, SolverVariable var2, int var3, boolean var4) {
      SolverVariable var5 = var0.createSlackVariable();
      ArrayRow var6 = var0.createRow();
      var6.createRowLowerThan(var1, var2, var5, var3);
      if (var4) {
         var0.addSingleError(var6, (int)(var6.variables.get(var5) * -1.0F));
      }

      return var6;
   }

   private SolverVariable createVariable(String var1, SolverVariable.Type var2) {
      Metrics var4 = sMetrics;
      if (var4 != null) {
         ++var4.variables;
      }

      if (this.mNumColumns + 1 >= this.mMaxColumns) {
         this.increaseTableSize();
      }

      SolverVariable var5 = this.acquireSolverVariable(var2, (String)null);
      var5.setName(var1);
      int var3 = this.mVariablesID + 1;
      this.mVariablesID = var3;
      ++this.mNumColumns;
      var5.id = var3;
      if (this.mVariables == null) {
         this.mVariables = new HashMap();
      }

      this.mVariables.put(var1, var5);
      this.mCache.mIndexedVariables[this.mVariablesID] = var5;
      return var5;
   }

   private void displayRows() {
      this.displaySolverVariables();
      String var2 = "";

      for(int var1 = 0; var1 < this.mNumRows; ++var1) {
         var2 = var2 + this.mRows[var1];
         var2 = var2 + "\n";
      }

      var2 = var2 + this.mGoal + "\n";
      System.out.println(var2);
   }

   private void displaySolverVariables() {
      String var1 = "Display Rows (" + this.mNumRows + "x" + this.mNumColumns + ")\n";
      System.out.println(var1);
   }

   private int enforceBFS(Row var1) throws Exception {
      int var5 = 0;

      boolean var19;
      while(true) {
         if (var5 >= this.mNumRows) {
            var19 = false;
            break;
         }

         if (this.mRows[var5].variable.mType != SolverVariable.Type.UNRESTRICTED && this.mRows[var5].constantValue < 0.0F) {
            var19 = true;
            break;
         }

         ++var5;
      }

      if (var19) {
         boolean var8 = false;

         int var15;
         for(var5 = 0; !var8; var5 = var15) {
            Metrics var17 = sMetrics;
            if (var17 != null) {
               ++var17.bfs;
            }

            var15 = var5 + 1;
            float var2 = Float.MAX_VALUE;
            var5 = -1;
            int var7 = -1;
            int var9 = 0;

            int var11;
            for(int var6 = 0; var9 < this.mNumRows; var6 = var11) {
               ArrayRow var18 = this.mRows[var9];
               float var3;
               int var12;
               int var13;
               if (var18.variable.mType == SolverVariable.Type.UNRESTRICTED) {
                  var3 = var2;
                  var13 = var5;
                  var12 = var7;
                  var11 = var6;
               } else if (var18.isSimpleDefinition) {
                  var3 = var2;
                  var13 = var5;
                  var12 = var7;
                  var11 = var6;
               } else {
                  var3 = var2;
                  var13 = var5;
                  var12 = var7;
                  var11 = var6;
                  if (var18.constantValue < 0.0F) {
                     int var10 = 1;

                     while(true) {
                        var3 = var2;
                        var13 = var5;
                        var12 = var7;
                        var11 = var6;
                        if (var10 >= this.mNumColumns) {
                           break;
                        }

                        SolverVariable var16 = this.mCache.mIndexedVariables[var10];
                        float var4 = var18.variables.get(var16);
                        int var14;
                        if (var4 <= 0.0F) {
                           var3 = var2;
                           var13 = var5;
                           var14 = var7;
                           var12 = var6;
                        } else {
                           byte var20 = 0;
                           var11 = var5;
                           var5 = var20;

                           while(true) {
                              var3 = var2;
                              var13 = var11;
                              var14 = var7;
                              var12 = var6;
                              if (var5 >= 7) {
                                 break;
                              }

                              label118: {
                                 var3 = var16.strengthVector[var5] / var4;
                                 if (!(var3 < var2) || var5 != var6) {
                                    var12 = var6;
                                    if (var5 <= var6) {
                                       break label118;
                                    }
                                 }

                                 var7 = var10;
                                 var12 = var5;
                                 var2 = var3;
                                 var11 = var9;
                              }

                              ++var5;
                              var6 = var12;
                           }
                        }

                        ++var10;
                        var2 = var3;
                        var5 = var13;
                        var7 = var14;
                        var6 = var12;
                     }
                  }
               }

               ++var9;
               var2 = var3;
               var5 = var13;
               var7 = var12;
            }

            if (var5 != -1) {
               ArrayRow var21 = this.mRows[var5];
               var21.variable.definitionId = -1;
               var17 = sMetrics;
               if (var17 != null) {
                  ++var17.pivots;
               }

               var21.pivot(this.mCache.mIndexedVariables[var7]);
               var21.variable.definitionId = var5;
               var21.variable.updateReferencesWithNewDefinition(var21);
            } else {
               var8 = true;
            }

            if (var15 > this.mNumColumns / 2) {
               var8 = true;
            }
         }
      } else {
         var5 = 0;
      }

      return var5;
   }

   private String getDisplaySize(int var1) {
      int var3 = var1 * 4;
      int var2 = var3 / 1024;
      var1 = var2 / 1024;
      if (var1 > 0) {
         return "" + var1 + " Mb";
      } else {
         return var2 > 0 ? "" + var2 + " Kb" : "" + var3 + " bytes";
      }
   }

   private String getDisplayStrength(int var1) {
      if (var1 == 1) {
         return "LOW";
      } else if (var1 == 2) {
         return "MEDIUM";
      } else if (var1 == 3) {
         return "HIGH";
      } else if (var1 == 4) {
         return "HIGHEST";
      } else if (var1 == 5) {
         return "EQUALITY";
      } else {
         return var1 == 6 ? "FIXED" : "NONE";
      }
   }

   public static Metrics getMetrics() {
      return sMetrics;
   }

   private void increaseTableSize() {
      int var1 = this.TABLE_SIZE * 2;
      this.TABLE_SIZE = var1;
      this.mRows = (ArrayRow[])Arrays.copyOf(this.mRows, var1);
      Cache var2 = this.mCache;
      var2.mIndexedVariables = (SolverVariable[])Arrays.copyOf(var2.mIndexedVariables, this.TABLE_SIZE);
      var1 = this.TABLE_SIZE;
      this.mAlreadyTestedCandidates = new boolean[var1];
      this.mMaxColumns = var1;
      this.mMaxRows = var1;
      Metrics var3 = sMetrics;
      if (var3 != null) {
         ++var3.tableSizeIncrease;
         var3 = sMetrics;
         var3.maxTableSize = Math.max(var3.maxTableSize, (long)this.TABLE_SIZE);
         var3 = sMetrics;
         var3.lastTableSize = var3.maxTableSize;
      }

   }

   private final int optimize(Row var1, boolean var2) {
      Metrics var11 = sMetrics;
      if (var11 != null) {
         ++var11.optimize;
      }

      int var6;
      for(var6 = 0; var6 < this.mNumColumns; ++var6) {
         this.mAlreadyTestedCandidates[var6] = false;
      }

      boolean var7 = false;
      var6 = 0;

      while(true) {
         while(!var7) {
            var11 = sMetrics;
            if (var11 != null) {
               ++var11.iterations;
            }

            int var10 = var6 + 1;
            if (var10 >= this.mNumColumns * 2) {
               return var10;
            }

            if (var1.getKey() != null) {
               this.mAlreadyTestedCandidates[var1.getKey().id] = true;
            }

            SolverVariable var14 = var1.getPivotCandidate(this, this.mAlreadyTestedCandidates);
            if (var14 != null) {
               if (this.mAlreadyTestedCandidates[var14.id]) {
                  return var10;
               }

               this.mAlreadyTestedCandidates[var14.id] = true;
            }

            if (var14 != null) {
               float var3 = Float.MAX_VALUE;
               var6 = 0;

               int var8;
               int var9;
               for(var8 = -1; var6 < this.mNumRows; var8 = var9) {
                  ArrayRow var12 = this.mRows[var6];
                  float var4;
                  if (var12.variable.mType == SolverVariable.Type.UNRESTRICTED) {
                     var4 = var3;
                     var9 = var8;
                  } else if (var12.isSimpleDefinition) {
                     var4 = var3;
                     var9 = var8;
                  } else {
                     var4 = var3;
                     var9 = var8;
                     if (var12.hasVariable(var14)) {
                        float var5 = var12.variables.get(var14);
                        var4 = var3;
                        var9 = var8;
                        if (var5 < 0.0F) {
                           var5 = -var12.constantValue / var5;
                           var4 = var3;
                           var9 = var8;
                           if (var5 < var3) {
                              var9 = var6;
                              var4 = var5;
                           }
                        }
                     }
                  }

                  ++var6;
                  var3 = var4;
               }

               if (var8 > -1) {
                  ArrayRow var13 = this.mRows[var8];
                  var13.variable.definitionId = -1;
                  Metrics var15 = sMetrics;
                  if (var15 != null) {
                     ++var15.pivots;
                  }

                  var13.pivot(var14);
                  var13.variable.definitionId = var8;
                  var13.variable.updateReferencesWithNewDefinition(var13);
                  var6 = var10;
                  continue;
               }
            }

            var7 = true;
            var6 = var10;
         }

         return var6;
      }
   }

   private void releaseRows() {
      int var1 = 0;

      while(true) {
         ArrayRow[] var2 = this.mRows;
         if (var1 >= var2.length) {
            return;
         }

         ArrayRow var3 = var2[var1];
         if (var3 != null) {
            this.mCache.arrayRowPool.release(var3);
         }

         this.mRows[var1] = null;
         ++var1;
      }
   }

   private final void updateRowFromVariables(ArrayRow var1) {
      if (this.mNumRows > 0) {
         var1.variables.updateFromSystem(var1, this.mRows);
         if (var1.variables.currentSize == 0) {
            var1.isSimpleDefinition = true;
         }
      }

   }

   public void addCenterPoint(ConstraintWidget var1, ConstraintWidget var2, float var3, int var4) {
      SolverVariable var11 = this.createObjectVariable(var1.getAnchor(ConstraintAnchor.Type.LEFT));
      SolverVariable var13 = this.createObjectVariable(var1.getAnchor(ConstraintAnchor.Type.TOP));
      SolverVariable var12 = this.createObjectVariable(var1.getAnchor(ConstraintAnchor.Type.RIGHT));
      SolverVariable var15 = this.createObjectVariable(var1.getAnchor(ConstraintAnchor.Type.BOTTOM));
      SolverVariable var14 = this.createObjectVariable(var2.getAnchor(ConstraintAnchor.Type.LEFT));
      SolverVariable var16 = this.createObjectVariable(var2.getAnchor(ConstraintAnchor.Type.TOP));
      SolverVariable var18 = this.createObjectVariable(var2.getAnchor(ConstraintAnchor.Type.RIGHT));
      SolverVariable var17 = this.createObjectVariable(var2.getAnchor(ConstraintAnchor.Type.BOTTOM));
      ArrayRow var19 = this.createRow();
      double var9 = (double)var3;
      double var5 = Math.sin(var9);
      double var7 = (double)var4;
      var19.createRowWithAngle(var13, var15, var16, var17, (float)(var5 * var7));
      this.addConstraint(var19);
      var19 = this.createRow();
      var19.createRowWithAngle(var11, var12, var14, var18, (float)(Math.cos(var9) * var7));
      this.addConstraint(var19);
   }

   public void addCentering(SolverVariable var1, SolverVariable var2, int var3, float var4, SolverVariable var5, SolverVariable var6, int var7, int var8) {
      ArrayRow var9 = this.createRow();
      var9.createRowCentering(var1, var2, var3, var4, var5, var6, var7);
      if (var8 != 6) {
         var9.addError(this, var8);
      }

      this.addConstraint(var9);
   }

   public void addConstraint(ArrayRow var1) {
      if (var1 != null) {
         Metrics var4 = sMetrics;
         if (var4 != null) {
            ++var4.constraints;
            if (var1.isSimpleDefinition) {
               var4 = sMetrics;
               ++var4.simpleconstraints;
            }
         }

         int var2 = this.mNumRows;
         boolean var3 = true;
         if (var2 + 1 >= this.mMaxRows || this.mNumColumns + 1 >= this.mMaxColumns) {
            this.increaseTableSize();
         }

         boolean var6 = false;
         if (!var1.isSimpleDefinition) {
            this.updateRowFromVariables(var1);
            if (var1.isEmpty()) {
               return;
            }

            var1.ensurePositiveConstant();
            if (var1.chooseSubject(this)) {
               SolverVariable var7 = this.createExtraVariable();
               var1.variable = var7;
               this.addRow(var1);
               this.mTempGoal.initFromRow(var1);
               this.optimize(this.mTempGoal, true);
               var6 = var3;
               if (var7.definitionId == -1) {
                  if (var1.variable == var7) {
                     var7 = var1.pickPivot(var7);
                     if (var7 != null) {
                        Metrics var5 = sMetrics;
                        if (var5 != null) {
                           ++var5.pivots;
                        }

                        var1.pivot(var7);
                     }
                  }

                  if (!var1.isSimpleDefinition) {
                     var1.variable.updateReferencesWithNewDefinition(var1);
                  }

                  --this.mNumRows;
                  var6 = var3;
               }
            } else {
               var6 = false;
            }

            if (!var1.hasKeyVariable()) {
               return;
            }
         }

         if (!var6) {
            this.addRow(var1);
         }

      }
   }

   public ArrayRow addEquality(SolverVariable var1, SolverVariable var2, int var3, int var4) {
      ArrayRow var5 = this.createRow();
      var5.createRowEquals(var1, var2, var3);
      if (var4 != 6) {
         var5.addError(this, var4);
      }

      this.addConstraint(var5);
      return var5;
   }

   public void addEquality(SolverVariable var1, int var2) {
      int var3 = var1.definitionId;
      ArrayRow var4;
      if (var1.definitionId != -1) {
         var4 = this.mRows[var3];
         if (var4.isSimpleDefinition) {
            var4.constantValue = (float)var2;
         } else if (var4.variables.currentSize == 0) {
            var4.isSimpleDefinition = true;
            var4.constantValue = (float)var2;
         } else {
            var4 = this.createRow();
            var4.createRowEquals(var1, var2);
            this.addConstraint(var4);
         }
      } else {
         var4 = this.createRow();
         var4.createRowDefinition(var1, var2);
         this.addConstraint(var4);
      }

   }

   public void addEquality(SolverVariable var1, int var2, int var3) {
      int var4 = var1.definitionId;
      ArrayRow var5;
      if (var1.definitionId != -1) {
         var5 = this.mRows[var4];
         if (var5.isSimpleDefinition) {
            var5.constantValue = (float)var2;
         } else {
            var5 = this.createRow();
            var5.createRowEquals(var1, var2);
            var5.addError(this, var3);
            this.addConstraint(var5);
         }
      } else {
         var5 = this.createRow();
         var5.createRowDefinition(var1, var2);
         var5.addError(this, var3);
         this.addConstraint(var5);
      }

   }

   public void addGreaterBarrier(SolverVariable var1, SolverVariable var2, boolean var3) {
      ArrayRow var4 = this.createRow();
      SolverVariable var5 = this.createSlackVariable();
      var5.strength = 0;
      var4.createRowGreaterThan(var1, var2, var5, 0);
      if (var3) {
         this.addSingleError(var4, (int)(var4.variables.get(var5) * -1.0F), 1);
      }

      this.addConstraint(var4);
   }

   public void addGreaterThan(SolverVariable var1, int var2) {
      ArrayRow var4 = this.createRow();
      SolverVariable var3 = this.createSlackVariable();
      var3.strength = 0;
      var4.createRowGreaterThan(var1, var2, var3);
      this.addConstraint(var4);
   }

   public void addGreaterThan(SolverVariable var1, SolverVariable var2, int var3, int var4) {
      ArrayRow var6 = this.createRow();
      SolverVariable var5 = this.createSlackVariable();
      var5.strength = 0;
      var6.createRowGreaterThan(var1, var2, var5, var3);
      if (var4 != 6) {
         this.addSingleError(var6, (int)(var6.variables.get(var5) * -1.0F), var4);
      }

      this.addConstraint(var6);
   }

   public void addLowerBarrier(SolverVariable var1, SolverVariable var2, boolean var3) {
      ArrayRow var4 = this.createRow();
      SolverVariable var5 = this.createSlackVariable();
      var5.strength = 0;
      var4.createRowLowerThan(var1, var2, var5, 0);
      if (var3) {
         this.addSingleError(var4, (int)(var4.variables.get(var5) * -1.0F), 1);
      }

      this.addConstraint(var4);
   }

   public void addLowerThan(SolverVariable var1, SolverVariable var2, int var3, int var4) {
      ArrayRow var5 = this.createRow();
      SolverVariable var6 = this.createSlackVariable();
      var6.strength = 0;
      var5.createRowLowerThan(var1, var2, var6, var3);
      if (var4 != 6) {
         this.addSingleError(var5, (int)(var5.variables.get(var6) * -1.0F), var4);
      }

      this.addConstraint(var5);
   }

   public void addRatio(SolverVariable var1, SolverVariable var2, SolverVariable var3, SolverVariable var4, float var5, int var6) {
      ArrayRow var7 = this.createRow();
      var7.createRowDimensionRatio(var1, var2, var3, var4, var5);
      if (var6 != 6) {
         var7.addError(this, var6);
      }

      this.addConstraint(var7);
   }

   void addSingleError(ArrayRow var1, int var2, int var3) {
      var1.addSingleError(this.createErrorVariable(var3, (String)null), var2);
   }

   public SolverVariable createErrorVariable(int var1, String var2) {
      Metrics var4 = sMetrics;
      if (var4 != null) {
         ++var4.errors;
      }

      if (this.mNumColumns + 1 >= this.mMaxColumns) {
         this.increaseTableSize();
      }

      SolverVariable var5 = this.acquireSolverVariable(SolverVariable.Type.ERROR, var2);
      int var3 = this.mVariablesID + 1;
      this.mVariablesID = var3;
      ++this.mNumColumns;
      var5.id = var3;
      var5.strength = var1;
      this.mCache.mIndexedVariables[this.mVariablesID] = var5;
      this.mGoal.addError(var5);
      return var5;
   }

   public SolverVariable createExtraVariable() {
      Metrics var2 = sMetrics;
      if (var2 != null) {
         ++var2.extravariables;
      }

      if (this.mNumColumns + 1 >= this.mMaxColumns) {
         this.increaseTableSize();
      }

      SolverVariable var3 = this.acquireSolverVariable(SolverVariable.Type.SLACK, (String)null);
      int var1 = this.mVariablesID + 1;
      this.mVariablesID = var1;
      ++this.mNumColumns;
      var3.id = var1;
      this.mCache.mIndexedVariables[this.mVariablesID] = var3;
      return var3;
   }

   public SolverVariable createObjectVariable(Object var1) {
      SolverVariable var3 = null;
      if (var1 == null) {
         return null;
      } else {
         if (this.mNumColumns + 1 >= this.mMaxColumns) {
            this.increaseTableSize();
         }

         if (var1 instanceof ConstraintAnchor) {
            ConstraintAnchor var4 = (ConstraintAnchor)var1;
            var3 = var4.getSolverVariable();
            SolverVariable var5 = var3;
            if (var3 == null) {
               var4.resetSolverVariable(this.mCache);
               var5 = var4.getSolverVariable();
            }

            if (var5.id != -1 && var5.id <= this.mVariablesID) {
               var3 = var5;
               if (this.mCache.mIndexedVariables[var5.id] != null) {
                  return var3;
               }
            }

            if (var5.id != -1) {
               var5.reset();
            }

            int var2 = this.mVariablesID + 1;
            this.mVariablesID = var2;
            ++this.mNumColumns;
            var5.id = var2;
            var5.mType = SolverVariable.Type.UNRESTRICTED;
            this.mCache.mIndexedVariables[this.mVariablesID] = var5;
            var3 = var5;
         }

         return var3;
      }
   }

   public ArrayRow createRow() {
      ArrayRow var1 = (ArrayRow)this.mCache.arrayRowPool.acquire();
      if (var1 == null) {
         var1 = new ArrayRow(this.mCache);
      } else {
         var1.reset();
      }

      SolverVariable.increaseErrorId();
      return var1;
   }

   public SolverVariable createSlackVariable() {
      Metrics var2 = sMetrics;
      if (var2 != null) {
         ++var2.slackvariables;
      }

      if (this.mNumColumns + 1 >= this.mMaxColumns) {
         this.increaseTableSize();
      }

      SolverVariable var3 = this.acquireSolverVariable(SolverVariable.Type.SLACK, (String)null);
      int var1 = this.mVariablesID + 1;
      this.mVariablesID = var1;
      ++this.mNumColumns;
      var3.id = var1;
      this.mCache.mIndexedVariables[this.mVariablesID] = var3;
      return var3;
   }

   void displayReadableRows() {
      this.displaySolverVariables();
      String var2 = " #  ";

      for(int var1 = 0; var1 < this.mNumRows; ++var1) {
         var2 = var2 + this.mRows[var1].toReadableString();
         var2 = var2 + "\n #  ";
      }

      String var3 = var2;
      if (this.mGoal != null) {
         var3 = var2 + this.mGoal + "\n";
      }

      System.out.println(var3);
   }

   void displaySystemInformations() {
      int var3 = 0;

      int var1;
      int var2;
      ArrayRow var5;
      for(var1 = 0; var3 < this.TABLE_SIZE; var1 = var2) {
         var5 = this.mRows[var3];
         var2 = var1;
         if (var5 != null) {
            var2 = var1 + var5.sizeInBytes();
         }

         ++var3;
      }

      var3 = 0;

      int var4;
      for(var2 = 0; var3 < this.mNumRows; var2 = var4) {
         var5 = this.mRows[var3];
         var4 = var2;
         if (var5 != null) {
            var4 = var2 + var5.sizeInBytes();
         }

         ++var3;
      }

      PrintStream var7 = System.out;
      StringBuilder var6 = (new StringBuilder()).append("Linear System -> Table size: ").append(this.TABLE_SIZE).append(" (");
      var3 = this.TABLE_SIZE;
      var7.println(var6.append(this.getDisplaySize(var3 * var3)).append(") -- row sizes: ").append(this.getDisplaySize(var1)).append(", actual size: ").append(this.getDisplaySize(var2)).append(" rows: ").append(this.mNumRows).append("/").append(this.mMaxRows).append(" cols: ").append(this.mNumColumns).append("/").append(this.mMaxColumns).append(" ").append(0).append(" occupied cells, ").append(this.getDisplaySize(0)).toString());
   }

   public void displayVariablesReadableRows() {
      this.displaySolverVariables();
      String var3 = "";

      String var2;
      for(int var1 = 0; var1 < this.mNumRows; var3 = var2) {
         var2 = var3;
         if (this.mRows[var1].variable.mType == SolverVariable.Type.UNRESTRICTED) {
            var2 = var3 + this.mRows[var1].toReadableString();
            var2 = var2 + "\n";
         }

         ++var1;
      }

      var2 = var3 + this.mGoal + "\n";
      System.out.println(var2);
   }

   public void fillMetrics(Metrics var1) {
      sMetrics = var1;
   }

   public Cache getCache() {
      return this.mCache;
   }

   Row getGoal() {
      return this.mGoal;
   }

   public int getMemoryUsed() {
      int var1 = 0;

      int var2;
      int var3;
      for(var2 = 0; var1 < this.mNumRows; var2 = var3) {
         ArrayRow var4 = this.mRows[var1];
         var3 = var2;
         if (var4 != null) {
            var3 = var2 + var4.sizeInBytes();
         }

         ++var1;
      }

      return var2;
   }

   public int getNumEquations() {
      return this.mNumRows;
   }

   public int getNumVariables() {
      return this.mVariablesID;
   }

   public int getObjectVariableValue(Object var1) {
      SolverVariable var2 = ((ConstraintAnchor)var1).getSolverVariable();
      return var2 != null ? (int)(var2.computedValue + 0.5F) : 0;
   }

   ArrayRow getRow(int var1) {
      return this.mRows[var1];
   }

   float getValueFor(String var1) {
      SolverVariable var2 = this.getVariable(var1, SolverVariable.Type.UNRESTRICTED);
      return var2 == null ? 0.0F : var2.computedValue;
   }

   SolverVariable getVariable(String var1, SolverVariable.Type var2) {
      if (this.mVariables == null) {
         this.mVariables = new HashMap();
      }

      SolverVariable var4 = (SolverVariable)this.mVariables.get(var1);
      SolverVariable var3 = var4;
      if (var4 == null) {
         var3 = this.createVariable(var1, var2);
      }

      return var3;
   }

   public void minimize() throws Exception {
      Metrics var3 = sMetrics;
      if (var3 != null) {
         ++var3.minimize;
      }

      if (this.graphOptimizer) {
         var3 = sMetrics;
         if (var3 != null) {
            ++var3.graphOptimizer;
         }

         boolean var2 = false;
         int var1 = 0;

         boolean var4;
         while(true) {
            if (var1 >= this.mNumRows) {
               var4 = true;
               break;
            }

            if (!this.mRows[var1].isSimpleDefinition) {
               var4 = var2;
               break;
            }

            ++var1;
         }

         if (!var4) {
            this.minimizeGoal(this.mGoal);
         } else {
            var3 = sMetrics;
            if (var3 != null) {
               ++var3.fullySolved;
            }

            this.computeValues();
         }
      } else {
         this.minimizeGoal(this.mGoal);
      }

   }

   void minimizeGoal(Row var1) throws Exception {
      Metrics var2 = sMetrics;
      if (var2 != null) {
         ++var2.minimizeGoal;
         var2 = sMetrics;
         var2.maxVariables = Math.max(var2.maxVariables, (long)this.mNumColumns);
         var2 = sMetrics;
         var2.maxRows = Math.max(var2.maxRows, (long)this.mNumRows);
      }

      this.updateRowFromVariables((ArrayRow)var1);
      this.enforceBFS(var1);
      this.optimize(var1, false);
      this.computeValues();
   }

   public void reset() {
      int var1;
      for(var1 = 0; var1 < this.mCache.mIndexedVariables.length; ++var1) {
         SolverVariable var2 = this.mCache.mIndexedVariables[var1];
         if (var2 != null) {
            var2.reset();
         }
      }

      this.mCache.solverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
      this.mPoolVariablesCount = 0;
      Arrays.fill(this.mCache.mIndexedVariables, (Object)null);
      HashMap var3 = this.mVariables;
      if (var3 != null) {
         var3.clear();
      }

      this.mVariablesID = 0;
      this.mGoal.clear();
      this.mNumColumns = 1;

      for(var1 = 0; var1 < this.mNumRows; ++var1) {
         this.mRows[var1].used = false;
      }

      this.releaseRows();
      this.mNumRows = 0;
   }

   interface Row {
      void addError(SolverVariable var1);

      void clear();

      SolverVariable getKey();

      SolverVariable getPivotCandidate(LinearSystem var1, boolean[] var2);

      void initFromRow(Row var1);

      boolean isEmpty();
   }
}
