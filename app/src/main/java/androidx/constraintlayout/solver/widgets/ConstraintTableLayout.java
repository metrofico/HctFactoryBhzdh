package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import java.util.ArrayList;

public class ConstraintTableLayout extends ConstraintWidgetContainer {
   public static final int ALIGN_CENTER = 0;
   private static final int ALIGN_FULL = 3;
   public static final int ALIGN_LEFT = 1;
   public static final int ALIGN_RIGHT = 2;
   private ArrayList mHorizontalGuidelines = new ArrayList();
   private ArrayList mHorizontalSlices = new ArrayList();
   private int mNumCols = 0;
   private int mNumRows = 0;
   private int mPadding = 8;
   private boolean mVerticalGrowth = true;
   private ArrayList mVerticalGuidelines = new ArrayList();
   private ArrayList mVerticalSlices = new ArrayList();
   private LinearSystem system = null;

   public ConstraintTableLayout() {
   }

   public ConstraintTableLayout(int var1, int var2) {
      super(var1, var2);
   }

   public ConstraintTableLayout(int var1, int var2, int var3, int var4) {
      super(var1, var2, var3, var4);
   }

   private void setChildrenConnections() {
      int var3 = this.mChildren.size();
      int var1 = 0;

      for(int var2 = 0; var1 < var3; ++var1) {
         ConstraintWidget var6 = (ConstraintWidget)this.mChildren.get(var1);
         var2 += var6.getContainerItemSkip();
         int var4 = this.mNumCols;
         int var5 = var2 / var4;
         HorizontalSlice var11 = (HorizontalSlice)this.mHorizontalSlices.get(var5);
         VerticalSlice var10 = (VerticalSlice)this.mVerticalSlices.get(var2 % var4);
         ConstraintWidget var9 = var10.left;
         ConstraintWidget var7 = var10.right;
         ConstraintWidget var8 = var11.top;
         ConstraintWidget var12 = var11.bottom;
         var6.getAnchor(ConstraintAnchor.Type.LEFT).connect(var9.getAnchor(ConstraintAnchor.Type.LEFT), this.mPadding);
         if (var7 instanceof Guideline) {
            var6.getAnchor(ConstraintAnchor.Type.RIGHT).connect(var7.getAnchor(ConstraintAnchor.Type.LEFT), this.mPadding);
         } else {
            var6.getAnchor(ConstraintAnchor.Type.RIGHT).connect(var7.getAnchor(ConstraintAnchor.Type.RIGHT), this.mPadding);
         }

         var4 = var10.alignment;
         if (var4 != 1) {
            if (var4 != 2) {
               if (var4 == 3) {
                  var6.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
               }
            } else {
               var6.getAnchor(ConstraintAnchor.Type.LEFT).setStrength(ConstraintAnchor.Strength.WEAK);
               var6.getAnchor(ConstraintAnchor.Type.RIGHT).setStrength(ConstraintAnchor.Strength.STRONG);
            }
         } else {
            var6.getAnchor(ConstraintAnchor.Type.LEFT).setStrength(ConstraintAnchor.Strength.STRONG);
            var6.getAnchor(ConstraintAnchor.Type.RIGHT).setStrength(ConstraintAnchor.Strength.WEAK);
         }

         var6.getAnchor(ConstraintAnchor.Type.TOP).connect(var8.getAnchor(ConstraintAnchor.Type.TOP), this.mPadding);
         if (var12 instanceof Guideline) {
            var6.getAnchor(ConstraintAnchor.Type.BOTTOM).connect(var12.getAnchor(ConstraintAnchor.Type.TOP), this.mPadding);
         } else {
            var6.getAnchor(ConstraintAnchor.Type.BOTTOM).connect(var12.getAnchor(ConstraintAnchor.Type.BOTTOM), this.mPadding);
         }

         ++var2;
      }

   }

   private void setHorizontalSlices() {
      this.mHorizontalSlices.clear();
      float var2 = 100.0F / (float)this.mNumRows;
      Object var4 = this;
      int var3 = 0;

      for(float var1 = var2; var3 < this.mNumRows; ++var3) {
         HorizontalSlice var5 = new HorizontalSlice(this);
         var5.top = (ConstraintWidget)var4;
         if (var3 < this.mNumRows - 1) {
            Guideline var6 = new Guideline();
            var6.setOrientation(0);
            var6.setParent(this);
            var6.setGuidePercent((int)var1);
            var1 += var2;
            var5.bottom = var6;
            this.mHorizontalGuidelines.add(var6);
         } else {
            var5.bottom = this;
         }

         var4 = var5.bottom;
         this.mHorizontalSlices.add(var5);
      }

      this.updateDebugSolverNames();
   }

   private void setVerticalSlices() {
      this.mVerticalSlices.clear();
      float var2 = 100.0F / (float)this.mNumCols;
      int var3 = 0;
      Object var4 = this;

      for(float var1 = var2; var3 < this.mNumCols; ++var3) {
         VerticalSlice var5 = new VerticalSlice(this);
         var5.left = (ConstraintWidget)var4;
         if (var3 < this.mNumCols - 1) {
            Guideline var6 = new Guideline();
            var6.setOrientation(1);
            var6.setParent(this);
            var6.setGuidePercent((int)var1);
            var1 += var2;
            var5.right = var6;
            this.mVerticalGuidelines.add(var6);
         } else {
            var5.right = this;
         }

         var4 = var5.right;
         this.mVerticalSlices.add(var5);
      }

      this.updateDebugSolverNames();
   }

   private void updateDebugSolverNames() {
      if (this.system != null) {
         int var3 = this.mVerticalGuidelines.size();
         byte var2 = 0;

         int var1;
         for(var1 = 0; var1 < var3; ++var1) {
            ((Guideline)this.mVerticalGuidelines.get(var1)).setDebugSolverName(this.system, this.getDebugName() + ".VG" + var1);
         }

         var3 = this.mHorizontalGuidelines.size();

         for(var1 = var2; var1 < var3; ++var1) {
            ((Guideline)this.mHorizontalGuidelines.get(var1)).setDebugSolverName(this.system, this.getDebugName() + ".HG" + var1);
         }

      }
   }

   public void addToSolver(LinearSystem var1) {
      super.addToSolver(var1);
      int var5 = this.mChildren.size();
      if (var5 != 0) {
         this.setTableDimensions();
         if (var1 == this.mSystem) {
            int var3 = this.mVerticalGuidelines.size();
            byte var4 = 0;
            int var2 = 0;

            label40:
            while(true) {
               boolean var7 = true;
               Guideline var8;
               if (var2 >= var3) {
                  int var6 = this.mHorizontalGuidelines.size();
                  var3 = 0;

                  while(true) {
                     var2 = var4;
                     if (var3 >= var6) {
                        while(var2 < var5) {
                           ((ConstraintWidget)this.mChildren.get(var2)).addToSolver(var1);
                           ++var2;
                        }
                        break label40;
                     }

                     var8 = (Guideline)this.mHorizontalGuidelines.get(var3);
                     if (this.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        var7 = true;
                     } else {
                        var7 = false;
                     }

                     var8.setPositionRelaxed(var7);
                     var8.addToSolver(var1);
                     ++var3;
                  }
               }

               var8 = (Guideline)this.mVerticalGuidelines.get(var2);
               if (this.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                  var7 = false;
               }

               var8.setPositionRelaxed(var7);
               var8.addToSolver(var1);
               ++var2;
            }
         }

      }
   }

   public void computeGuidelinesPercentPositions() {
      int var3 = this.mVerticalGuidelines.size();
      byte var2 = 0;

      int var1;
      for(var1 = 0; var1 < var3; ++var1) {
         ((Guideline)this.mVerticalGuidelines.get(var1)).inferRelativePercentPosition();
      }

      var3 = this.mHorizontalGuidelines.size();

      for(var1 = var2; var1 < var3; ++var1) {
         ((Guideline)this.mHorizontalGuidelines.get(var1)).inferRelativePercentPosition();
      }

   }

   public void cycleColumnAlignment(int var1) {
      VerticalSlice var2 = (VerticalSlice)this.mVerticalSlices.get(var1);
      var1 = var2.alignment;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               var2.alignment = 1;
            }
         } else {
            var2.alignment = 0;
         }
      } else {
         var2.alignment = 2;
      }

      this.setChildrenConnections();
   }

   public String getColumnAlignmentRepresentation(int var1) {
      VerticalSlice var2 = (VerticalSlice)this.mVerticalSlices.get(var1);
      if (var2.alignment == 1) {
         return "L";
      } else if (var2.alignment == 0) {
         return "C";
      } else if (var2.alignment == 3) {
         return "F";
      } else {
         return var2.alignment == 2 ? "R" : "!";
      }
   }

   public String getColumnsAlignmentRepresentation() {
      int var2 = this.mVerticalSlices.size();
      String var4 = "";

      String var3;
      for(int var1 = 0; var1 < var2; var4 = var3) {
         VerticalSlice var5 = (VerticalSlice)this.mVerticalSlices.get(var1);
         if (var5.alignment == 1) {
            var3 = var4 + "L";
         } else if (var5.alignment == 0) {
            var3 = var4 + "C";
         } else if (var5.alignment == 3) {
            var3 = var4 + "F";
         } else {
            var3 = var4;
            if (var5.alignment == 2) {
               var3 = var4 + "R";
            }
         }

         ++var1;
      }

      return var4;
   }

   public ArrayList getHorizontalGuidelines() {
      return this.mHorizontalGuidelines;
   }

   public int getNumCols() {
      return this.mNumCols;
   }

   public int getNumRows() {
      return this.mNumRows;
   }

   public int getPadding() {
      return this.mPadding;
   }

   public String getType() {
      return "ConstraintTableLayout";
   }

   public ArrayList getVerticalGuidelines() {
      return this.mVerticalGuidelines;
   }

   public boolean handlesInternalConstraints() {
      return true;
   }

   public boolean isVerticalGrowth() {
      return this.mVerticalGrowth;
   }

   public void setColumnAlignment(int var1, int var2) {
      if (var1 < this.mVerticalSlices.size()) {
         ((VerticalSlice)this.mVerticalSlices.get(var1)).alignment = var2;
         this.setChildrenConnections();
      }

   }

   public void setColumnAlignment(String var1) {
      int var3 = var1.length();

      for(int var2 = 0; var2 < var3; ++var2) {
         char var4 = var1.charAt(var2);
         if (var4 == 'L') {
            this.setColumnAlignment(var2, 1);
         } else if (var4 == 'C') {
            this.setColumnAlignment(var2, 0);
         } else if (var4 == 'F') {
            this.setColumnAlignment(var2, 3);
         } else if (var4 == 'R') {
            this.setColumnAlignment(var2, 2);
         } else {
            this.setColumnAlignment(var2, 0);
         }
      }

   }

   public void setDebugSolverName(LinearSystem var1, String var2) {
      this.system = var1;
      super.setDebugSolverName(var1, var2);
      this.updateDebugSolverNames();
   }

   public void setNumCols(int var1) {
      if (this.mVerticalGrowth && this.mNumCols != var1) {
         this.mNumCols = var1;
         this.setVerticalSlices();
         this.setTableDimensions();
      }

   }

   public void setNumRows(int var1) {
      if (!this.mVerticalGrowth && this.mNumCols != var1) {
         this.mNumRows = var1;
         this.setHorizontalSlices();
         this.setTableDimensions();
      }

   }

   public void setPadding(int var1) {
      if (var1 > 1) {
         this.mPadding = var1;
      }

   }

   public void setTableDimensions() {
      int var3 = this.mChildren.size();
      int var2 = 0;

      int var1;
      for(var1 = 0; var2 < var3; ++var2) {
         var1 += ((ConstraintWidget)this.mChildren.get(var2)).getContainerItemSkip();
      }

      var3 += var1;
      int var4;
      if (this.mVerticalGrowth) {
         if (this.mNumCols == 0) {
            this.setNumCols(1);
         }

         var4 = this.mNumCols;
         var2 = var3 / var4;
         var1 = var2;
         if (var4 * var2 < var3) {
            var1 = var2 + 1;
         }

         if (this.mNumRows == var1 && this.mVerticalGuidelines.size() == this.mNumCols - 1) {
            return;
         }

         this.mNumRows = var1;
         this.setHorizontalSlices();
      } else {
         if (this.mNumRows == 0) {
            this.setNumRows(1);
         }

         var4 = this.mNumRows;
         var2 = var3 / var4;
         var1 = var2;
         if (var4 * var2 < var3) {
            var1 = var2 + 1;
         }

         if (this.mNumCols == var1 && this.mHorizontalGuidelines.size() == this.mNumRows - 1) {
            return;
         }

         this.mNumCols = var1;
         this.setVerticalSlices();
      }

      this.setChildrenConnections();
   }

   public void setVerticalGrowth(boolean var1) {
      this.mVerticalGrowth = var1;
   }

   public void updateFromSolver(LinearSystem var1) {
      super.updateFromSolver(var1);
      if (var1 == this.mSystem) {
         int var4 = this.mVerticalGuidelines.size();
         byte var3 = 0;

         int var2;
         for(var2 = 0; var2 < var4; ++var2) {
            ((Guideline)this.mVerticalGuidelines.get(var2)).updateFromSolver(var1);
         }

         var4 = this.mHorizontalGuidelines.size();

         for(var2 = var3; var2 < var4; ++var2) {
            ((Guideline)this.mHorizontalGuidelines.get(var2)).updateFromSolver(var1);
         }
      }

   }

   class HorizontalSlice {
      ConstraintWidget bottom;
      int padding;
      final ConstraintTableLayout this$0;
      ConstraintWidget top;

      HorizontalSlice(ConstraintTableLayout var1) {
         this.this$0 = var1;
      }
   }

   class VerticalSlice {
      int alignment;
      ConstraintWidget left;
      int padding;
      ConstraintWidget right;
      final ConstraintTableLayout this$0;

      VerticalSlice(ConstraintTableLayout var1) {
         this.this$0 = var1;
         this.alignment = 1;
      }
   }
}
