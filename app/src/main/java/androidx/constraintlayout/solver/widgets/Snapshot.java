package androidx.constraintlayout.solver.widgets;

import java.util.ArrayList;

public class Snapshot {
   private ArrayList mConnections = new ArrayList();
   private int mHeight;
   private int mWidth;
   private int mX;
   private int mY;

   public Snapshot(ConstraintWidget var1) {
      this.mX = var1.getX();
      this.mY = var1.getY();
      this.mWidth = var1.getWidth();
      this.mHeight = var1.getHeight();
      ArrayList var5 = var1.getAnchors();
      int var3 = var5.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         ConstraintAnchor var4 = (ConstraintAnchor)var5.get(var2);
         this.mConnections.add(new Connection(var4));
      }

   }

   public void applyTo(ConstraintWidget var1) {
      var1.setX(this.mX);
      var1.setY(this.mY);
      var1.setWidth(this.mWidth);
      var1.setHeight(this.mHeight);
      int var3 = this.mConnections.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         ((Connection)this.mConnections.get(var2)).applyTo(var1);
      }

   }

   public void updateFrom(ConstraintWidget var1) {
      this.mX = var1.getX();
      this.mY = var1.getY();
      this.mWidth = var1.getWidth();
      this.mHeight = var1.getHeight();
      int var3 = this.mConnections.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         ((Connection)this.mConnections.get(var2)).updateFrom(var1);
      }

   }

   static class Connection {
      private ConstraintAnchor mAnchor;
      private int mCreator;
      private int mMargin;
      private ConstraintAnchor.Strength mStrengh;
      private ConstraintAnchor mTarget;

      public Connection(ConstraintAnchor var1) {
         this.mAnchor = var1;
         this.mTarget = var1.getTarget();
         this.mMargin = var1.getMargin();
         this.mStrengh = var1.getStrength();
         this.mCreator = var1.getConnectionCreator();
      }

      public void applyTo(ConstraintWidget var1) {
         var1.getAnchor(this.mAnchor.getType()).connect(this.mTarget, this.mMargin, this.mStrengh, this.mCreator);
      }

      public void updateFrom(ConstraintWidget var1) {
         ConstraintAnchor var2 = var1.getAnchor(this.mAnchor.getType());
         this.mAnchor = var2;
         if (var2 != null) {
            this.mTarget = var2.getTarget();
            this.mMargin = this.mAnchor.getMargin();
            this.mStrengh = this.mAnchor.getStrength();
            this.mCreator = this.mAnchor.getConnectionCreator();
         } else {
            this.mTarget = null;
            this.mMargin = 0;
            this.mStrengh = ConstraintAnchor.Strength.STRONG;
            this.mCreator = 0;
         }

      }
   }
}
