package androidx.constraintlayout.solver;

import java.util.Arrays;

public class SolverVariable {
   private static final boolean INTERNAL_DEBUG = false;
   static final int MAX_STRENGTH = 7;
   public static final int STRENGTH_BARRIER = 7;
   public static final int STRENGTH_EQUALITY = 5;
   public static final int STRENGTH_FIXED = 6;
   public static final int STRENGTH_HIGH = 3;
   public static final int STRENGTH_HIGHEST = 4;
   public static final int STRENGTH_LOW = 1;
   public static final int STRENGTH_MEDIUM = 2;
   public static final int STRENGTH_NONE = 0;
   private static int uniqueConstantId;
   private static int uniqueErrorId;
   private static int uniqueId;
   private static int uniqueSlackId;
   private static int uniqueUnrestrictedId;
   public float computedValue;
   int definitionId = -1;
   public int id = -1;
   ArrayRow[] mClientEquations = new ArrayRow[8];
   int mClientEquationsCount = 0;
   private String mName;
   Type mType;
   public int strength = 0;
   float[] strengthVector = new float[7];
   public int usageInRowCount = 0;

   public SolverVariable(Type var1, String var2) {
      this.mType = var1;
   }

   public SolverVariable(String var1, Type var2) {
      this.mName = var1;
      this.mType = var2;
   }

   private static String getUniqueName(Type var0, String var1) {
      if (var1 != null) {
         return var1 + uniqueErrorId;
      } else {
         int var2 = null.$SwitchMap$androidx$constraintlayout$solver$SolverVariable$Type[var0.ordinal()];
         StringBuilder var3;
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 == 5) {
                        var3 = (new StringBuilder()).append("V");
                        var2 = uniqueId + 1;
                        uniqueId = var2;
                        return var3.append(var2).toString();
                     } else {
                        throw new AssertionError(var0.name());
                     }
                  } else {
                     var3 = (new StringBuilder()).append("e");
                     var2 = uniqueErrorId + 1;
                     uniqueErrorId = var2;
                     return var3.append(var2).toString();
                  }
               } else {
                  var3 = (new StringBuilder()).append("S");
                  var2 = uniqueSlackId + 1;
                  uniqueSlackId = var2;
                  return var3.append(var2).toString();
               }
            } else {
               var3 = (new StringBuilder()).append("C");
               var2 = uniqueConstantId + 1;
               uniqueConstantId = var2;
               return var3.append(var2).toString();
            }
         } else {
            var3 = (new StringBuilder()).append("U");
            var2 = uniqueUnrestrictedId + 1;
            uniqueUnrestrictedId = var2;
            return var3.append(var2).toString();
         }
      }
   }

   static void increaseErrorId() {
      ++uniqueErrorId;
   }

   public final void addToRow(ArrayRow var1) {
      int var2 = 0;

      while(true) {
         int var3 = this.mClientEquationsCount;
         if (var2 >= var3) {
            ArrayRow[] var4 = this.mClientEquations;
            if (var3 >= var4.length) {
               this.mClientEquations = (ArrayRow[])Arrays.copyOf(var4, var4.length * 2);
            }

            var4 = this.mClientEquations;
            var2 = this.mClientEquationsCount;
            var4[var2] = var1;
            this.mClientEquationsCount = var2 + 1;
            return;
         }

         if (this.mClientEquations[var2] == var1) {
            return;
         }

         ++var2;
      }
   }

   void clearStrengths() {
      for(int var1 = 0; var1 < 7; ++var1) {
         this.strengthVector[var1] = 0.0F;
      }

   }

   public String getName() {
      return this.mName;
   }

   public final void removeFromRow(ArrayRow var1) {
      int var4 = this.mClientEquationsCount;
      int var3 = 0;

      for(int var2 = 0; var2 < var4; ++var2) {
         if (this.mClientEquations[var2] == var1) {
            while(var3 < var4 - var2 - 1) {
               ArrayRow[] var6 = this.mClientEquations;
               int var5 = var2 + var3;
               var6[var5] = var6[var5 + 1];
               ++var3;
            }

            --this.mClientEquationsCount;
            return;
         }
      }

   }

   public void reset() {
      this.mName = null;
      this.mType = SolverVariable.Type.UNKNOWN;
      this.strength = 0;
      this.id = -1;
      this.definitionId = -1;
      this.computedValue = 0.0F;
      this.mClientEquationsCount = 0;
      this.usageInRowCount = 0;
   }

   public void setName(String var1) {
      this.mName = var1;
   }

   public void setType(Type var1, String var2) {
      this.mType = var1;
   }

   String strengthsToString() {
      String var6 = this + "[";
      boolean var3 = true;
      int var4 = 0;

      boolean var2;
      for(var2 = false; var4 < this.strengthVector.length; ++var4) {
         var6 = var6 + this.strengthVector[var4];
         float[] var5 = this.strengthVector;
         float var1 = var5[var4];
         if (var1 > 0.0F) {
            var2 = false;
         } else if (var1 < 0.0F) {
            var2 = true;
         }

         if (var1 != 0.0F) {
            var3 = false;
         }

         if (var4 < var5.length - 1) {
            var6 = var6 + ", ";
         } else {
            var6 = var6 + "] ";
         }
      }

      String var7 = var6;
      if (var2) {
         var7 = var6 + " (-)";
      }

      var6 = var7;
      if (var3) {
         var6 = var7 + " (*)";
      }

      return var6;
   }

   public String toString() {
      return "" + this.mName;
   }

   public final void updateReferencesWithNewDefinition(ArrayRow var1) {
      int var3 = this.mClientEquationsCount;

      for(int var2 = 0; var2 < var3; ++var2) {
         this.mClientEquations[var2].variables.updateFromRow(this.mClientEquations[var2], var1, false);
      }

      this.mClientEquationsCount = 0;
   }

   public static enum Type {
      private static final Type[] $VALUES;
      CONSTANT,
      ERROR,
      SLACK,
      UNKNOWN,
      UNRESTRICTED;

      static {
         Type var1 = new Type("UNRESTRICTED", 0);
         UNRESTRICTED = var1;
         Type var0 = new Type("CONSTANT", 1);
         CONSTANT = var0;
         Type var2 = new Type("SLACK", 2);
         SLACK = var2;
         Type var4 = new Type("ERROR", 3);
         ERROR = var4;
         Type var3 = new Type("UNKNOWN", 4);
         UNKNOWN = var3;
         $VALUES = new Type[]{var1, var0, var2, var4, var3};
      }
   }
}
