package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;
import java.util.HashSet;

public class ConstraintAnchor {
   private static final boolean ALLOW_BINARY = false;
   public static final int AUTO_CONSTRAINT_CREATOR = 2;
   public static final int SCOUT_CREATOR = 1;
   private static final int UNSET_GONE_MARGIN = -1;
   public static final int USER_CREATOR = 0;
   private int mConnectionCreator;
   private ConnectionType mConnectionType;
   int mGoneMargin = -1;
   public int mMargin = 0;
   final ConstraintWidget mOwner;
   private ResolutionAnchor mResolutionAnchor = new ResolutionAnchor(this);
   SolverVariable mSolverVariable;
   private Strength mStrength;
   ConstraintAnchor mTarget;
   final Type mType;

   public ConstraintAnchor(ConstraintWidget var1, Type var2) {
      this.mStrength = ConstraintAnchor.Strength.NONE;
      this.mConnectionType = ConstraintAnchor.ConnectionType.RELAXED;
      this.mConnectionCreator = 0;
      this.mOwner = var1;
      this.mType = var2;
   }

   private boolean isConnectionToMe(ConstraintWidget var1, HashSet var2) {
      if (var2.contains(var1)) {
         return false;
      } else {
         var2.add(var1);
         if (var1 == this.getOwner()) {
            return true;
         } else {
            ArrayList var6 = var1.getAnchors();
            int var4 = var6.size();

            for(int var3 = 0; var3 < var4; ++var3) {
               ConstraintAnchor var5 = (ConstraintAnchor)var6.get(var3);
               if (var5.isSimilarDimensionConnection(this) && var5.isConnected() && this.isConnectionToMe(var5.getTarget().getOwner(), var2)) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   public boolean connect(ConstraintAnchor var1, int var2) {
      return this.connect(var1, var2, -1, ConstraintAnchor.Strength.STRONG, 0, false);
   }

   public boolean connect(ConstraintAnchor var1, int var2, int var3) {
      return this.connect(var1, var2, -1, ConstraintAnchor.Strength.STRONG, var3, false);
   }

   public boolean connect(ConstraintAnchor var1, int var2, int var3, Strength var4, int var5, boolean var6) {
      if (var1 == null) {
         this.mTarget = null;
         this.mMargin = 0;
         this.mGoneMargin = -1;
         this.mStrength = ConstraintAnchor.Strength.NONE;
         this.mConnectionCreator = 2;
         return true;
      } else if (!var6 && !this.isValidConnection(var1)) {
         return false;
      } else {
         this.mTarget = var1;
         if (var2 > 0) {
            this.mMargin = var2;
         } else {
            this.mMargin = 0;
         }

         this.mGoneMargin = var3;
         this.mStrength = var4;
         this.mConnectionCreator = var5;
         return true;
      }
   }

   public boolean connect(ConstraintAnchor var1, int var2, Strength var3, int var4) {
      return this.connect(var1, var2, -1, var3, var4, false);
   }

   public int getConnectionCreator() {
      return this.mConnectionCreator;
   }

   public ConnectionType getConnectionType() {
      return this.mConnectionType;
   }

   public int getMargin() {
      if (this.mOwner.getVisibility() == 8) {
         return 0;
      } else {
         if (this.mGoneMargin > -1) {
            ConstraintAnchor var1 = this.mTarget;
            if (var1 != null && var1.mOwner.getVisibility() == 8) {
               return this.mGoneMargin;
            }
         }

         return this.mMargin;
      }
   }

   public final ConstraintAnchor getOpposite() {
      switch (null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[this.mType.ordinal()]) {
         case 1:
         case 6:
         case 7:
         case 8:
         case 9:
            return null;
         case 2:
            return this.mOwner.mRight;
         case 3:
            return this.mOwner.mLeft;
         case 4:
            return this.mOwner.mBottom;
         case 5:
            return this.mOwner.mTop;
         default:
            throw new AssertionError(this.mType.name());
      }
   }

   public ConstraintWidget getOwner() {
      return this.mOwner;
   }

   public int getPriorityLevel() {
      switch (null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[this.mType.ordinal()]) {
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
            return 2;
         case 6:
            return 1;
         case 7:
         case 8:
         case 9:
            return 0;
         default:
            throw new AssertionError(this.mType.name());
      }
   }

   public ResolutionAnchor getResolutionNode() {
      return this.mResolutionAnchor;
   }

   public int getSnapPriorityLevel() {
      switch (null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[this.mType.ordinal()]) {
         case 1:
            return 3;
         case 2:
         case 3:
            return 1;
         case 4:
         case 5:
            return 0;
         case 6:
            return 2;
         case 7:
            return 0;
         case 8:
            return 1;
         case 9:
            return 0;
         default:
            throw new AssertionError(this.mType.name());
      }
   }

   public SolverVariable getSolverVariable() {
      return this.mSolverVariable;
   }

   public Strength getStrength() {
      return this.mStrength;
   }

   public ConstraintAnchor getTarget() {
      return this.mTarget;
   }

   public Type getType() {
      return this.mType;
   }

   public boolean isConnected() {
      boolean var1;
      if (this.mTarget != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isConnectionAllowed(ConstraintWidget var1) {
      if (this.isConnectionToMe(var1, new HashSet())) {
         return false;
      } else {
         ConstraintWidget var2 = this.getOwner().getParent();
         if (var2 == var1) {
            return true;
         } else {
            return var1.getParent() == var2;
         }
      }
   }

   public boolean isConnectionAllowed(ConstraintWidget var1, ConstraintAnchor var2) {
      return this.isConnectionAllowed(var1);
   }

   public boolean isSideAnchor() {
      switch (null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[this.mType.ordinal()]) {
         case 1:
         case 6:
         case 7:
         case 8:
         case 9:
            return false;
         case 2:
         case 3:
         case 4:
         case 5:
            return true;
         default:
            throw new AssertionError(this.mType.name());
      }
   }

   public boolean isSimilarDimensionConnection(ConstraintAnchor var1) {
      Type var6 = var1.getType();
      Type var5 = this.mType;
      boolean var4 = true;
      boolean var2 = true;
      boolean var3 = true;
      if (var6 == var5) {
         return true;
      } else {
         switch (null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[this.mType.ordinal()]) {
            case 1:
               if (var6 == ConstraintAnchor.Type.BASELINE) {
                  var2 = false;
               }

               return var2;
            case 2:
            case 3:
            case 7:
               var2 = var4;
               if (var6 != ConstraintAnchor.Type.LEFT) {
                  var2 = var4;
                  if (var6 != ConstraintAnchor.Type.RIGHT) {
                     if (var6 == ConstraintAnchor.Type.CENTER_X) {
                        var2 = var4;
                     } else {
                        var2 = false;
                     }
                  }
               }

               return var2;
            case 4:
            case 5:
            case 6:
            case 8:
               var2 = var3;
               if (var6 != ConstraintAnchor.Type.TOP) {
                  var2 = var3;
                  if (var6 != ConstraintAnchor.Type.BOTTOM) {
                     var2 = var3;
                     if (var6 != ConstraintAnchor.Type.CENTER_Y) {
                        if (var6 == ConstraintAnchor.Type.BASELINE) {
                           var2 = var3;
                        } else {
                           var2 = false;
                        }
                     }
                  }
               }

               return var2;
            case 9:
               return false;
            default:
               throw new AssertionError(this.mType.name());
         }
      }
   }

   public boolean isSnapCompatibleWith(ConstraintAnchor var1) {
      if (this.mType == ConstraintAnchor.Type.CENTER) {
         return false;
      } else if (this.mType == var1.getType()) {
         return true;
      } else {
         int var2;
         switch (null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[this.mType.ordinal()]) {
            case 1:
            case 6:
            case 9:
               return false;
            case 2:
               var2 = null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[var1.getType().ordinal()];
               if (var2 != 3 && var2 != 7) {
                  return false;
               }

               return true;
            case 3:
               var2 = null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[var1.getType().ordinal()];
               if (var2 != 2 && var2 != 7) {
                  return false;
               }

               return true;
            case 4:
               var2 = null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[var1.getType().ordinal()];
               if (var2 != 5 && var2 != 8) {
                  return false;
               }

               return true;
            case 5:
               var2 = null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[var1.getType().ordinal()];
               if (var2 != 4 && var2 != 8) {
                  return false;
               }

               return true;
            case 7:
               var2 = null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[var1.getType().ordinal()];
               if (var2 != 2 && var2 != 3) {
                  return false;
               }

               return true;
            case 8:
               var2 = null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[var1.getType().ordinal()];
               if (var2 != 4 && var2 != 5) {
                  return false;
               }

               return true;
            default:
               throw new AssertionError(this.mType.name());
         }
      }
   }

   public boolean isValidConnection(ConstraintAnchor var1) {
      boolean var5 = false;
      boolean var3 = false;
      boolean var4 = false;
      if (var1 == null) {
         return false;
      } else {
         Type var7 = var1.getType();
         Type var6 = this.mType;
         if (var7 == var6) {
            return var6 != ConstraintAnchor.Type.BASELINE || var1.getOwner().hasBaseline() && this.getOwner().hasBaseline();
         } else {
            boolean var2;
            switch (null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[this.mType.ordinal()]) {
               case 1:
                  var2 = var3;
                  if (var7 != ConstraintAnchor.Type.BASELINE) {
                     var2 = var3;
                     if (var7 != ConstraintAnchor.Type.CENTER_X) {
                        var2 = var3;
                        if (var7 != ConstraintAnchor.Type.CENTER_Y) {
                           var2 = true;
                        }
                     }
                  }

                  return var2;
               case 2:
               case 3:
                  if (var7 != ConstraintAnchor.Type.LEFT && var7 != ConstraintAnchor.Type.RIGHT) {
                     var2 = false;
                  } else {
                     var2 = true;
                  }

                  var3 = var2;
                  if (var1.getOwner() instanceof Guideline) {
                     label76: {
                        if (!var2) {
                           var2 = var5;
                           if (var7 != ConstraintAnchor.Type.CENTER_X) {
                              break label76;
                           }
                        }

                        var2 = true;
                     }

                     var3 = var2;
                  }

                  return var3;
               case 4:
               case 5:
                  if (var7 != ConstraintAnchor.Type.TOP && var7 != ConstraintAnchor.Type.BOTTOM) {
                     var2 = false;
                  } else {
                     var2 = true;
                  }

                  var3 = var2;
                  if (var1.getOwner() instanceof Guideline) {
                     label60: {
                        if (!var2) {
                           var2 = var4;
                           if (var7 != ConstraintAnchor.Type.CENTER_Y) {
                              break label60;
                           }
                        }

                        var2 = true;
                     }

                     var3 = var2;
                  }

                  return var3;
               case 6:
               case 7:
               case 8:
               case 9:
                  return false;
               default:
                  throw new AssertionError(this.mType.name());
            }
         }
      }
   }

   public boolean isVerticalAnchor() {
      switch (null.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[this.mType.ordinal()]) {
         case 1:
         case 2:
         case 3:
         case 7:
            return false;
         case 4:
         case 5:
         case 6:
         case 8:
         case 9:
            return true;
         default:
            throw new AssertionError(this.mType.name());
      }
   }

   public void reset() {
      this.mTarget = null;
      this.mMargin = 0;
      this.mGoneMargin = -1;
      this.mStrength = ConstraintAnchor.Strength.STRONG;
      this.mConnectionCreator = 0;
      this.mConnectionType = ConstraintAnchor.ConnectionType.RELAXED;
      this.mResolutionAnchor.reset();
   }

   public void resetSolverVariable(Cache var1) {
      SolverVariable var2 = this.mSolverVariable;
      if (var2 == null) {
         this.mSolverVariable = new SolverVariable(SolverVariable.Type.UNRESTRICTED, (String)null);
      } else {
         var2.reset();
      }

   }

   public void setConnectionCreator(int var1) {
      this.mConnectionCreator = var1;
   }

   public void setConnectionType(ConnectionType var1) {
      this.mConnectionType = var1;
   }

   public void setGoneMargin(int var1) {
      if (this.isConnected()) {
         this.mGoneMargin = var1;
      }

   }

   public void setMargin(int var1) {
      if (this.isConnected()) {
         this.mMargin = var1;
      }

   }

   public void setStrength(Strength var1) {
      if (this.isConnected()) {
         this.mStrength = var1;
      }

   }

   public String toString() {
      return this.mOwner.getDebugName() + ":" + this.mType.toString();
   }

   public static enum ConnectionType {
      private static final ConnectionType[] $VALUES;
      RELAXED,
      STRICT;

      static {
         ConnectionType var1 = new ConnectionType("RELAXED", 0);
         RELAXED = var1;
         ConnectionType var0 = new ConnectionType("STRICT", 1);
         STRICT = var0;
         $VALUES = new ConnectionType[]{var1, var0};
      }
   }

   public static enum Strength {
      private static final Strength[] $VALUES;
      NONE,
      STRONG,
      WEAK;

      static {
         Strength var0 = new Strength("NONE", 0);
         NONE = var0;
         Strength var1 = new Strength("STRONG", 1);
         STRONG = var1;
         Strength var2 = new Strength("WEAK", 2);
         WEAK = var2;
         $VALUES = new Strength[]{var0, var1, var2};
      }
   }

   public static enum Type {
      private static final Type[] $VALUES;
      BASELINE,
      BOTTOM,
      CENTER,
      CENTER_X,
      CENTER_Y,
      LEFT,
      NONE,
      RIGHT,
      TOP;

      static {
         Type var7 = new Type("NONE", 0);
         NONE = var7;
         Type var6 = new Type("LEFT", 1);
         LEFT = var6;
         Type var4 = new Type("TOP", 2);
         TOP = var4;
         Type var0 = new Type("RIGHT", 3);
         RIGHT = var0;
         Type var2 = new Type("BOTTOM", 4);
         BOTTOM = var2;
         Type var1 = new Type("BASELINE", 5);
         BASELINE = var1;
         Type var3 = new Type("CENTER", 6);
         CENTER = var3;
         Type var8 = new Type("CENTER_X", 7);
         CENTER_X = var8;
         Type var5 = new Type("CENTER_Y", 8);
         CENTER_Y = var5;
         $VALUES = new Type[]{var7, var6, var4, var0, var2, var1, var3, var8, var5};
      }
   }
}
