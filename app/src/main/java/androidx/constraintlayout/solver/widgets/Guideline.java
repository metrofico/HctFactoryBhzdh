package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;

public class Guideline extends ConstraintWidget {
   public static final int HORIZONTAL = 0;
   public static final int RELATIVE_BEGIN = 1;
   public static final int RELATIVE_END = 2;
   public static final int RELATIVE_PERCENT = 0;
   public static final int RELATIVE_UNKNWON = -1;
   public static final int VERTICAL = 1;
   private ConstraintAnchor mAnchor;
   private Rectangle mHead;
   private int mHeadSize;
   private boolean mIsPositionRelaxed;
   private int mMinimumPosition;
   private int mOrientation;
   protected int mRelativeBegin = -1;
   protected int mRelativeEnd = -1;
   protected float mRelativePercent = -1.0F;

   public Guideline() {
      this.mAnchor = this.mTop;
      int var1 = 0;
      this.mOrientation = 0;
      this.mIsPositionRelaxed = false;
      this.mMinimumPosition = 0;
      this.mHead = new Rectangle();
      this.mHeadSize = 8;
      this.mAnchors.clear();
      this.mAnchors.add(this.mAnchor);

      for(int var2 = this.mListAnchors.length; var1 < var2; ++var1) {
         this.mListAnchors[var1] = this.mAnchor;
      }

   }

   public void addToSolver(LinearSystem var1) {
      ConstraintWidgetContainer var6 = (ConstraintWidgetContainer)this.getParent();
      if (var6 != null) {
         ConstraintAnchor var4 = var6.getAnchor(ConstraintAnchor.Type.LEFT);
         ConstraintAnchor var5 = var6.getAnchor(ConstraintAnchor.Type.RIGHT);
         ConstraintWidget var7 = this.mParent;
         boolean var3 = true;
         boolean var2;
         if (var7 != null && this.mParent.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (this.mOrientation == 0) {
            var4 = var6.getAnchor(ConstraintAnchor.Type.TOP);
            var5 = var6.getAnchor(ConstraintAnchor.Type.BOTTOM);
            if (this.mParent != null && this.mParent.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
               var2 = var3;
            } else {
               var2 = false;
            }
         }

         SolverVariable var8;
         if (this.mRelativeBegin != -1) {
            var8 = var1.createObjectVariable(this.mAnchor);
            var1.addEquality(var8, var1.createObjectVariable(var4), this.mRelativeBegin, 6);
            if (var2) {
               var1.addGreaterThan(var1.createObjectVariable(var5), var8, 0, 5);
            }
         } else if (this.mRelativeEnd != -1) {
            var8 = var1.createObjectVariable(this.mAnchor);
            SolverVariable var9 = var1.createObjectVariable(var5);
            var1.addEquality(var8, var9, -this.mRelativeEnd, 6);
            if (var2) {
               var1.addGreaterThan(var8, var1.createObjectVariable(var4), 0, 5);
               var1.addGreaterThan(var9, var8, 0, 5);
            }
         } else if (this.mRelativePercent != -1.0F) {
            var1.addConstraint(LinearSystem.createRowDimensionPercent(var1, var1.createObjectVariable(this.mAnchor), var1.createObjectVariable(var4), var1.createObjectVariable(var5), this.mRelativePercent, this.mIsPositionRelaxed));
         }

      }
   }

   public boolean allowedInBarrier() {
      return true;
   }

   public void analyze(int var1) {
      ConstraintWidget var2 = this.getParent();
      if (var2 != null) {
         if (this.getOrientation() == 1) {
            this.mTop.getResolutionNode().dependsOn(1, var2.mTop.getResolutionNode(), 0);
            this.mBottom.getResolutionNode().dependsOn(1, var2.mTop.getResolutionNode(), 0);
            if (this.mRelativeBegin != -1) {
               this.mLeft.getResolutionNode().dependsOn(1, var2.mLeft.getResolutionNode(), this.mRelativeBegin);
               this.mRight.getResolutionNode().dependsOn(1, var2.mLeft.getResolutionNode(), this.mRelativeBegin);
            } else if (this.mRelativeEnd != -1) {
               this.mLeft.getResolutionNode().dependsOn(1, var2.mRight.getResolutionNode(), -this.mRelativeEnd);
               this.mRight.getResolutionNode().dependsOn(1, var2.mRight.getResolutionNode(), -this.mRelativeEnd);
            } else if (this.mRelativePercent != -1.0F && var2.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) {
               var1 = (int)((float)var2.mWidth * this.mRelativePercent);
               this.mLeft.getResolutionNode().dependsOn(1, var2.mLeft.getResolutionNode(), var1);
               this.mRight.getResolutionNode().dependsOn(1, var2.mLeft.getResolutionNode(), var1);
            }
         } else {
            this.mLeft.getResolutionNode().dependsOn(1, var2.mLeft.getResolutionNode(), 0);
            this.mRight.getResolutionNode().dependsOn(1, var2.mLeft.getResolutionNode(), 0);
            if (this.mRelativeBegin != -1) {
               this.mTop.getResolutionNode().dependsOn(1, var2.mTop.getResolutionNode(), this.mRelativeBegin);
               this.mBottom.getResolutionNode().dependsOn(1, var2.mTop.getResolutionNode(), this.mRelativeBegin);
            } else if (this.mRelativeEnd != -1) {
               this.mTop.getResolutionNode().dependsOn(1, var2.mBottom.getResolutionNode(), -this.mRelativeEnd);
               this.mBottom.getResolutionNode().dependsOn(1, var2.mBottom.getResolutionNode(), -this.mRelativeEnd);
            } else if (this.mRelativePercent != -1.0F && var2.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) {
               var1 = (int)((float)var2.mHeight * this.mRelativePercent);
               this.mTop.getResolutionNode().dependsOn(1, var2.mTop.getResolutionNode(), var1);
               this.mBottom.getResolutionNode().dependsOn(1, var2.mTop.getResolutionNode(), var1);
            }
         }

      }
   }

   public void cyclePosition() {
      if (this.mRelativeBegin != -1) {
         this.inferRelativePercentPosition();
      } else if (this.mRelativePercent != -1.0F) {
         this.inferRelativeEndPosition();
      } else if (this.mRelativeEnd != -1) {
         this.inferRelativeBeginPosition();
      }

   }

   public ConstraintAnchor getAnchor() {
      return this.mAnchor;
   }

   public ConstraintAnchor getAnchor(ConstraintAnchor.Type var1) {
      switch (null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[var1.ordinal()]) {
         case 1:
         case 2:
            if (this.mOrientation == 1) {
               return this.mAnchor;
            }
            break;
         case 3:
         case 4:
            if (this.mOrientation == 0) {
               return this.mAnchor;
            }
            break;
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
            return null;
      }

      throw new AssertionError(var1.name());
   }

   public ArrayList getAnchors() {
      return this.mAnchors;
   }

   public Rectangle getHead() {
      Rectangle var5 = this.mHead;
      int var2 = this.getDrawX();
      int var3 = this.mHeadSize;
      int var4 = this.getDrawY();
      int var1 = this.mHeadSize;
      var5.setBounds(var2 - var3, var4 - var1 * 2, var1 * 2, var1 * 2);
      if (this.getOrientation() == 0) {
         var5 = this.mHead;
         var2 = this.getDrawX();
         var4 = this.mHeadSize;
         var3 = this.getDrawY();
         var1 = this.mHeadSize;
         var5.setBounds(var2 - var4 * 2, var3 - var1, var1 * 2, var1 * 2);
      }

      return this.mHead;
   }

   public int getOrientation() {
      return this.mOrientation;
   }

   public int getRelativeBegin() {
      return this.mRelativeBegin;
   }

   public int getRelativeBehaviour() {
      if (this.mRelativePercent != -1.0F) {
         return 0;
      } else if (this.mRelativeBegin != -1) {
         return 1;
      } else {
         return this.mRelativeEnd != -1 ? 2 : -1;
      }
   }

   public int getRelativeEnd() {
      return this.mRelativeEnd;
   }

   public float getRelativePercent() {
      return this.mRelativePercent;
   }

   public String getType() {
      return "Guideline";
   }

   void inferRelativeBeginPosition() {
      int var1 = this.getX();
      if (this.mOrientation == 0) {
         var1 = this.getY();
      }

      this.setGuideBegin(var1);
   }

   void inferRelativeEndPosition() {
      int var1 = this.getParent().getWidth() - this.getX();
      if (this.mOrientation == 0) {
         var1 = this.getParent().getHeight() - this.getY();
      }

      this.setGuideEnd(var1);
   }

   void inferRelativePercentPosition() {
      float var1 = (float)this.getX() / (float)this.getParent().getWidth();
      if (this.mOrientation == 0) {
         var1 = (float)this.getY() / (float)this.getParent().getHeight();
      }

      this.setGuidePercent(var1);
   }

   public void setDrawOrigin(int var1, int var2) {
      if (this.mOrientation == 1) {
         var1 -= this.mOffsetX;
         if (this.mRelativeBegin != -1) {
            this.setGuideBegin(var1);
         } else if (this.mRelativeEnd != -1) {
            this.setGuideEnd(this.getParent().getWidth() - var1);
         } else if (this.mRelativePercent != -1.0F) {
            this.setGuidePercent((float)var1 / (float)this.getParent().getWidth());
         }
      } else {
         var1 = var2 - this.mOffsetY;
         if (this.mRelativeBegin != -1) {
            this.setGuideBegin(var1);
         } else if (this.mRelativeEnd != -1) {
            this.setGuideEnd(this.getParent().getHeight() - var1);
         } else if (this.mRelativePercent != -1.0F) {
            this.setGuidePercent((float)var1 / (float)this.getParent().getHeight());
         }
      }

   }

   public void setGuideBegin(int var1) {
      if (var1 > -1) {
         this.mRelativePercent = -1.0F;
         this.mRelativeBegin = var1;
         this.mRelativeEnd = -1;
      }

   }

   public void setGuideEnd(int var1) {
      if (var1 > -1) {
         this.mRelativePercent = -1.0F;
         this.mRelativeBegin = -1;
         this.mRelativeEnd = var1;
      }

   }

   public void setGuidePercent(float var1) {
      if (var1 > -1.0F) {
         this.mRelativePercent = var1;
         this.mRelativeBegin = -1;
         this.mRelativeEnd = -1;
      }

   }

   public void setGuidePercent(int var1) {
      this.setGuidePercent((float)var1 / 100.0F);
   }

   public void setMinimumPosition(int var1) {
      this.mMinimumPosition = var1;
   }

   public void setOrientation(int var1) {
      if (this.mOrientation != var1) {
         this.mOrientation = var1;
         this.mAnchors.clear();
         if (this.mOrientation == 1) {
            this.mAnchor = this.mLeft;
         } else {
            this.mAnchor = this.mTop;
         }

         this.mAnchors.add(this.mAnchor);
         int var2 = this.mListAnchors.length;

         for(var1 = 0; var1 < var2; ++var1) {
            this.mListAnchors[var1] = this.mAnchor;
         }

      }
   }

   public void setPositionRelaxed(boolean var1) {
      if (this.mIsPositionRelaxed != var1) {
         this.mIsPositionRelaxed = var1;
      }
   }

   public void updateFromSolver(LinearSystem var1) {
      if (this.getParent() != null) {
         int var2 = var1.getObjectVariableValue(this.mAnchor);
         if (this.mOrientation == 1) {
            this.setX(var2);
            this.setY(0);
            this.setHeight(this.getParent().getHeight());
            this.setWidth(0);
         } else {
            this.setX(0);
            this.setY(var2);
            this.setWidth(this.getParent().getWidth());
            this.setHeight(0);
         }

      }
   }
}
