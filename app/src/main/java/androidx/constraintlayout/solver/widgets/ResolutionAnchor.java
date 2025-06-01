package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
import androidx.constraintlayout.solver.SolverVariable;

public class ResolutionAnchor extends ResolutionNode {
   public static final int BARRIER_CONNECTION = 5;
   public static final int CENTER_CONNECTION = 2;
   public static final int CHAIN_CONNECTION = 4;
   public static final int DIRECT_CONNECTION = 1;
   public static final int MATCH_CONNECTION = 3;
   public static final int UNCONNECTED = 0;
   float computedValue;
   private ResolutionDimension dimension = null;
   private int dimensionMultiplier = 1;
   ConstraintAnchor myAnchor;
   float offset;
   private ResolutionAnchor opposite;
   private ResolutionDimension oppositeDimension = null;
   private int oppositeDimensionMultiplier = 1;
   private float oppositeOffset;
   float resolvedOffset;
   ResolutionAnchor resolvedTarget;
   ResolutionAnchor target;
   int type = 0;

   public ResolutionAnchor(ConstraintAnchor var1) {
      this.myAnchor = var1;
   }

   void addResolvedValue(LinearSystem var1) {
      SolverVariable var3 = this.myAnchor.getSolverVariable();
      ResolutionAnchor var2 = this.resolvedTarget;
      if (var2 == null) {
         var1.addEquality(var3, (int)(this.resolvedOffset + 0.5F));
      } else {
         var1.addEquality(var3, var1.createObjectVariable(var2.myAnchor), (int)(this.resolvedOffset + 0.5F), 6);
      }

   }

   public void dependsOn(int var1, ResolutionAnchor var2, int var3) {
      this.type = var1;
      this.target = var2;
      this.offset = (float)var3;
      var2.addDependent(this);
   }

   public void dependsOn(ResolutionAnchor var1, int var2) {
      this.target = var1;
      this.offset = (float)var2;
      var1.addDependent(this);
   }

   public void dependsOn(ResolutionAnchor var1, int var2, ResolutionDimension var3) {
      this.target = var1;
      var1.addDependent(this);
      this.dimension = var3;
      this.dimensionMultiplier = var2;
      var3.addDependent(this);
   }

   public float getResolvedValue() {
      return this.resolvedOffset;
   }

   public void remove(ResolutionDimension var1) {
      ResolutionDimension var2 = this.dimension;
      if (var2 == var1) {
         this.dimension = null;
         this.offset = (float)this.dimensionMultiplier;
      } else if (var2 == this.oppositeDimension) {
         this.oppositeDimension = null;
         this.oppositeOffset = (float)this.oppositeDimensionMultiplier;
      }

      this.resolve();
   }

   public void reset() {
      super.reset();
      this.target = null;
      this.offset = 0.0F;
      this.dimension = null;
      this.dimensionMultiplier = 1;
      this.oppositeDimension = null;
      this.oppositeDimensionMultiplier = 1;
      this.resolvedTarget = null;
      this.resolvedOffset = 0.0F;
      this.computedValue = 0.0F;
      this.opposite = null;
      this.oppositeOffset = 0.0F;
      this.type = 0;
   }

   public void resolve() {
      int var5 = this.state;
      boolean var6 = true;
      if (var5 != 1) {
         if (this.type != 4) {
            ResolutionDimension var9 = this.dimension;
            if (var9 != null) {
               if (var9.state != 1) {
                  return;
               }

               this.offset = (float)this.dimensionMultiplier * this.dimension.value;
            }

            var9 = this.oppositeDimension;
            if (var9 != null) {
               if (var9.state != 1) {
                  return;
               }

               this.oppositeOffset = (float)this.oppositeDimensionMultiplier * this.oppositeDimension.value;
            }

            ResolutionAnchor var14;
            if (this.type == 1) {
               var14 = this.target;
               if (var14 == null || var14.state == 1) {
                  var14 = this.target;
                  if (var14 == null) {
                     this.resolvedTarget = this;
                     this.resolvedOffset = this.offset;
                  } else {
                     this.resolvedTarget = var14.resolvedTarget;
                     this.resolvedOffset = var14.resolvedOffset + this.offset;
                  }

                  this.didResolve();
                  return;
               }
            }

            Metrics var16;
            if (this.type == 2) {
               var14 = this.target;
               if (var14 != null && var14.state == 1) {
                  var14 = this.opposite;
                  if (var14 != null) {
                     var14 = var14.target;
                     if (var14 != null && var14.state == 1) {
                        if (LinearSystem.getMetrics() != null) {
                           var16 = LinearSystem.getMetrics();
                           ++var16.centerConnectionResolved;
                        }

                        this.resolvedTarget = this.target.resolvedTarget;
                        var14 = this.opposite;
                        var14.resolvedTarget = var14.target.resolvedTarget;
                        ConstraintAnchor.Type var15 = this.myAnchor.mType;
                        ConstraintAnchor.Type var17 = ConstraintAnchor.Type.RIGHT;
                        int var7 = 0;
                        boolean var12 = var6;
                        if (var15 != var17) {
                           if (this.myAnchor.mType == ConstraintAnchor.Type.BOTTOM) {
                              var12 = var6;
                           } else {
                              var12 = false;
                           }
                        }

                        float var1;
                        float var2;
                        if (var12) {
                           var1 = this.target.resolvedOffset;
                           var2 = this.opposite.target.resolvedOffset;
                        } else {
                           var1 = this.opposite.target.resolvedOffset;
                           var2 = this.target.resolvedOffset;
                        }

                        var1 -= var2;
                        if (this.myAnchor.mType != ConstraintAnchor.Type.LEFT && this.myAnchor.mType != ConstraintAnchor.Type.RIGHT) {
                           var2 = var1 - (float)this.myAnchor.mOwner.getHeight();
                           var1 = this.myAnchor.mOwner.mVerticalBiasPercent;
                        } else {
                           var2 = var1 - (float)this.myAnchor.mOwner.getWidth();
                           var1 = this.myAnchor.mOwner.mHorizontalBiasPercent;
                        }

                        int var8 = this.myAnchor.getMargin();
                        int var13 = this.opposite.myAnchor.getMargin();
                        if (this.myAnchor.getTarget() == this.opposite.myAnchor.getTarget()) {
                           var1 = 0.5F;
                           var13 = 0;
                        } else {
                           var7 = var8;
                        }

                        float var3 = (float)var7;
                        float var4 = (float)var13;
                        var2 = var2 - var3 - var4;
                        if (var12) {
                           var14 = this.opposite;
                           var14.resolvedOffset = var14.target.resolvedOffset + var4 + var2 * var1;
                           this.resolvedOffset = this.target.resolvedOffset - var3 - var2 * (1.0F - var1);
                        } else {
                           this.resolvedOffset = this.target.resolvedOffset + var3 + var2 * var1;
                           var14 = this.opposite;
                           var14.resolvedOffset = var14.target.resolvedOffset - var4 - var2 * (1.0F - var1);
                        }

                        this.didResolve();
                        this.opposite.didResolve();
                        return;
                     }
                  }
               }
            }

            if (this.type == 3) {
               var14 = this.target;
               if (var14 != null && var14.state == 1) {
                  var14 = this.opposite;
                  if (var14 != null) {
                     var14 = var14.target;
                     if (var14 != null && var14.state == 1) {
                        if (LinearSystem.getMetrics() != null) {
                           var16 = LinearSystem.getMetrics();
                           ++var16.matchConnectionResolved;
                        }

                        ResolutionAnchor var11 = this.target;
                        this.resolvedTarget = var11.resolvedTarget;
                        ResolutionAnchor var10 = this.opposite;
                        var14 = var10.target;
                        var10.resolvedTarget = var14.resolvedTarget;
                        this.resolvedOffset = var11.resolvedOffset + this.offset;
                        var10.resolvedOffset = var14.resolvedOffset + var10.offset;
                        this.didResolve();
                        this.opposite.didResolve();
                        return;
                     }
                  }
               }
            }

            if (this.type == 5) {
               this.myAnchor.mOwner.resolve();
            }

         }
      }
   }

   public void resolve(ResolutionAnchor var1, float var2) {
      if (this.state == 0 || this.resolvedTarget != var1 && this.resolvedOffset != var2) {
         this.resolvedTarget = var1;
         this.resolvedOffset = var2;
         if (this.state == 1) {
            this.invalidate();
         }

         this.didResolve();
      }

   }

   String sType(int var1) {
      if (var1 == 1) {
         return "DIRECT";
      } else if (var1 == 2) {
         return "CENTER";
      } else if (var1 == 3) {
         return "MATCH";
      } else if (var1 == 4) {
         return "CHAIN";
      } else {
         return var1 == 5 ? "BARRIER" : "UNCONNECTED";
      }
   }

   public void setOpposite(ResolutionAnchor var1, float var2) {
      this.opposite = var1;
      this.oppositeOffset = var2;
   }

   public void setOpposite(ResolutionAnchor var1, int var2, ResolutionDimension var3) {
      this.opposite = var1;
      this.oppositeDimension = var3;
      this.oppositeDimensionMultiplier = var2;
   }

   public void setType(int var1) {
      this.type = var1;
   }

   public String toString() {
      if (this.state == 1) {
         return this.resolvedTarget == this ? "[" + this.myAnchor + ", RESOLVED: " + this.resolvedOffset + "]  type: " + this.sType(this.type) : "[" + this.myAnchor + ", RESOLVED: " + this.resolvedTarget + ":" + this.resolvedOffset + "] type: " + this.sType(this.type);
      } else {
         return "{ " + this.myAnchor + " UNRESOLVED} type: " + this.sType(this.type);
      }
   }

   public void update() {
      ConstraintAnchor var3 = this.myAnchor.getTarget();
      if (var3 != null) {
         if (var3.getTarget() == this.myAnchor) {
            this.type = 4;
            var3.getResolutionNode().type = 4;
         }

         int var1;
         label16: {
            int var2 = this.myAnchor.getMargin();
            if (this.myAnchor.mType != ConstraintAnchor.Type.RIGHT) {
               var1 = var2;
               if (this.myAnchor.mType != ConstraintAnchor.Type.BOTTOM) {
                  break label16;
               }
            }

            var1 = -var2;
         }

         this.dependsOn(var3.getResolutionNode(), var1);
      }
   }
}
