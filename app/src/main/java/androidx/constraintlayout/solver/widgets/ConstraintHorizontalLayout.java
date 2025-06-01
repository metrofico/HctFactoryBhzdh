package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;

public class ConstraintHorizontalLayout extends ConstraintWidgetContainer {
   private ContentAlignment mAlignment;

   public ConstraintHorizontalLayout() {
      this.mAlignment = ConstraintHorizontalLayout.ContentAlignment.MIDDLE;
   }

   public ConstraintHorizontalLayout(int var1, int var2) {
      super(var1, var2);
      this.mAlignment = ConstraintHorizontalLayout.ContentAlignment.MIDDLE;
   }

   public ConstraintHorizontalLayout(int var1, int var2, int var3, int var4) {
      super(var1, var2, var3, var4);
      this.mAlignment = ConstraintHorizontalLayout.ContentAlignment.MIDDLE;
   }

   public void addToSolver(LinearSystem var1) {
      if (this.mChildren.size() != 0) {
         int var2 = 0;
         int var3 = this.mChildren.size();

         Object var4;
         ConstraintAnchor.Strength var5;
         ConstraintWidget var6;
         for(var4 = this; var2 < var3; var4 = var6) {
            var6 = (ConstraintWidget)this.mChildren.get(var2);
            if (var4 != this) {
               var6.connect(ConstraintAnchor.Type.LEFT, (ConstraintWidget)var4, ConstraintAnchor.Type.RIGHT);
               ((ConstraintWidget)var4).connect(ConstraintAnchor.Type.RIGHT, var6, ConstraintAnchor.Type.LEFT);
            } else {
               var5 = ConstraintAnchor.Strength.STRONG;
               if (this.mAlignment == ConstraintHorizontalLayout.ContentAlignment.END) {
                  var5 = ConstraintAnchor.Strength.WEAK;
               }

               var6.connect(ConstraintAnchor.Type.LEFT, (ConstraintWidget)var4, ConstraintAnchor.Type.LEFT, 0, var5);
            }

            var6.connect(ConstraintAnchor.Type.TOP, this, ConstraintAnchor.Type.TOP);
            var6.connect(ConstraintAnchor.Type.BOTTOM, this, ConstraintAnchor.Type.BOTTOM);
            ++var2;
         }

         if (var4 != this) {
            var5 = ConstraintAnchor.Strength.STRONG;
            if (this.mAlignment == ConstraintHorizontalLayout.ContentAlignment.BEGIN) {
               var5 = ConstraintAnchor.Strength.WEAK;
            }

            ((ConstraintWidget)var4).connect(ConstraintAnchor.Type.RIGHT, this, ConstraintAnchor.Type.RIGHT, 0, var5);
         }
      }

      super.addToSolver(var1);
   }

   public static enum ContentAlignment {
      private static final ContentAlignment[] $VALUES;
      BEGIN,
      BOTTOM,
      END,
      LEFT,
      MIDDLE,
      RIGHT,
      TOP,
      VERTICAL_MIDDLE;

      static {
         ContentAlignment var6 = new ContentAlignment("BEGIN", 0);
         BEGIN = var6;
         ContentAlignment var3 = new ContentAlignment("MIDDLE", 1);
         MIDDLE = var3;
         ContentAlignment var1 = new ContentAlignment("END", 2);
         END = var1;
         ContentAlignment var7 = new ContentAlignment("TOP", 3);
         TOP = var7;
         ContentAlignment var2 = new ContentAlignment("VERTICAL_MIDDLE", 4);
         VERTICAL_MIDDLE = var2;
         ContentAlignment var0 = new ContentAlignment("BOTTOM", 5);
         BOTTOM = var0;
         ContentAlignment var4 = new ContentAlignment("LEFT", 6);
         LEFT = var4;
         ContentAlignment var5 = new ContentAlignment("RIGHT", 7);
         RIGHT = var5;
         $VALUES = new ContentAlignment[]{var6, var3, var1, var7, var2, var0, var4, var5};
      }
   }
}
