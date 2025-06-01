package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import java.util.ArrayList;

public class WidgetContainer extends ConstraintWidget {
   protected ArrayList mChildren = new ArrayList();

   public WidgetContainer() {
   }

   public WidgetContainer(int var1, int var2) {
      super(var1, var2);
   }

   public WidgetContainer(int var1, int var2, int var3, int var4) {
      super(var1, var2, var3, var4);
   }

   public static Rectangle getBounds(ArrayList var0) {
      Rectangle var11 = new Rectangle();
      if (var0.size() == 0) {
         return var11;
      } else {
         int var10 = var0.size();
         int var9 = Integer.MAX_VALUE;
         int var2 = 0;
         int var3 = 0;
         int var1 = var3;

         int var5;
         int var8;
         for(var5 = Integer.MAX_VALUE; var2 < var10; var1 = var8) {
            ConstraintWidget var12 = (ConstraintWidget)var0.get(var2);
            int var4 = var9;
            if (var12.getX() < var9) {
               var4 = var12.getX();
            }

            int var6 = var5;
            if (var12.getY() < var5) {
               var6 = var12.getY();
            }

            int var7 = var3;
            if (var12.getRight() > var3) {
               var7 = var12.getRight();
            }

            var8 = var1;
            if (var12.getBottom() > var1) {
               var8 = var12.getBottom();
            }

            ++var2;
            var9 = var4;
            var5 = var6;
            var3 = var7;
         }

         var11.setBounds(var9, var5, var3 - var9, var1 - var5);
         return var11;
      }
   }

   public void add(ConstraintWidget var1) {
      this.mChildren.add(var1);
      if (var1.getParent() != null) {
         ((WidgetContainer)var1.getParent()).remove(var1);
      }

      var1.setParent(this);
   }

   public void add(ConstraintWidget... var1) {
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         this.add(var1[var2]);
      }

   }

   public ConstraintWidget findWidget(float var1, float var2) {
      int var4 = this.getDrawX();
      int var6 = this.getDrawY();
      int var5 = this.getWidth();
      int var3 = this.getHeight();
      WidgetContainer var9;
      if (var1 >= (float)var4 && var1 <= (float)(var5 + var4) && var2 >= (float)var6 && var2 <= (float)(var3 + var6)) {
         var9 = this;
      } else {
         var9 = null;
      }

      var3 = 0;
      var4 = this.mChildren.size();

      Object var10;
      Object var12;
      for(var10 = var9; var3 < var4; var10 = var12) {
         ConstraintWidget var11 = (ConstraintWidget)this.mChildren.get(var3);
         if (var11 instanceof WidgetContainer) {
            var11 = ((WidgetContainer)var11).findWidget(var1, var2);
            var12 = var10;
            if (var11 != null) {
               var12 = var11;
            }
         } else {
            int var7 = var11.getDrawX();
            int var8 = var11.getDrawY();
            var6 = var11.getWidth();
            var5 = var11.getHeight();
            var12 = var10;
            if (var1 >= (float)var7) {
               var12 = var10;
               if (var1 <= (float)(var6 + var7)) {
                  var12 = var10;
                  if (var2 >= (float)var8) {
                     var12 = var10;
                     if (var2 <= (float)(var5 + var8)) {
                        var12 = var11;
                     }
                  }
               }
            }
         }

         ++var3;
      }

      return (ConstraintWidget)var10;
   }

   public ArrayList findWidgets(int var1, int var2, int var3, int var4) {
      ArrayList var6 = new ArrayList();
      Rectangle var7 = new Rectangle();
      var7.setBounds(var1, var2, var3, var4);
      var2 = this.mChildren.size();

      for(var1 = 0; var1 < var2; ++var1) {
         ConstraintWidget var8 = (ConstraintWidget)this.mChildren.get(var1);
         Rectangle var5 = new Rectangle();
         var5.setBounds(var8.getDrawX(), var8.getDrawY(), var8.getWidth(), var8.getHeight());
         if (var7.intersects(var5)) {
            var6.add(var8);
         }
      }

      return var6;
   }

   public ArrayList getChildren() {
      return this.mChildren;
   }

   public ConstraintWidgetContainer getRootConstraintContainer() {
      ConstraintWidget var2 = this.getParent();
      ConstraintWidgetContainer var1;
      if (this instanceof ConstraintWidgetContainer) {
         var1 = (ConstraintWidgetContainer)this;
      } else {
         var1 = null;
      }

      ConstraintWidget var3;
      for(; var2 != null; var2 = var3) {
         var3 = var2.getParent();
         if (var2 instanceof ConstraintWidgetContainer) {
            var1 = (ConstraintWidgetContainer)var2;
         }
      }

      return var1;
   }

   public void layout() {
      this.updateDrawPosition();
      ArrayList var3 = this.mChildren;
      if (var3 != null) {
         int var2 = var3.size();

         for(int var1 = 0; var1 < var2; ++var1) {
            ConstraintWidget var4 = (ConstraintWidget)this.mChildren.get(var1);
            if (var4 instanceof WidgetContainer) {
               ((WidgetContainer)var4).layout();
            }
         }

      }
   }

   public void remove(ConstraintWidget var1) {
      this.mChildren.remove(var1);
      var1.setParent((ConstraintWidget)null);
   }

   public void removeAllChildren() {
      this.mChildren.clear();
   }

   public void reset() {
      this.mChildren.clear();
      super.reset();
   }

   public void resetSolverVariables(Cache var1) {
      super.resetSolverVariables(var1);
      int var3 = this.mChildren.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         ((ConstraintWidget)this.mChildren.get(var2)).resetSolverVariables(var1);
      }

   }

   public void setOffset(int var1, int var2) {
      super.setOffset(var1, var2);
      var2 = this.mChildren.size();

      for(var1 = 0; var1 < var2; ++var1) {
         ((ConstraintWidget)this.mChildren.get(var1)).setOffset(this.getRootX(), this.getRootY());
      }

   }

   public void updateDrawPosition() {
      super.updateDrawPosition();
      ArrayList var3 = this.mChildren;
      if (var3 != null) {
         int var2 = var3.size();

         for(int var1 = 0; var1 < var2; ++var1) {
            ConstraintWidget var4 = (ConstraintWidget)this.mChildren.get(var1);
            var4.setOffset(this.getDrawX(), this.getDrawY());
            if (!(var4 instanceof ConstraintWidgetContainer)) {
               var4.updateDrawPosition();
            }
         }

      }
   }
}
