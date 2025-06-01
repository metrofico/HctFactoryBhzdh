package androidx.core.graphics;

import android.graphics.Rect;

public final class Insets {
   public static final Insets NONE = new Insets(0, 0, 0, 0);
   public final int bottom;
   public final int left;
   public final int right;
   public final int top;

   private Insets(int var1, int var2, int var3, int var4) {
      this.left = var1;
      this.top = var2;
      this.right = var3;
      this.bottom = var4;
   }

   public static Insets add(Insets var0, Insets var1) {
      return of(var0.left + var1.left, var0.top + var1.top, var0.right + var1.right, var0.bottom + var1.bottom);
   }

   public static Insets max(Insets var0, Insets var1) {
      return of(Math.max(var0.left, var1.left), Math.max(var0.top, var1.top), Math.max(var0.right, var1.right), Math.max(var0.bottom, var1.bottom));
   }

   public static Insets min(Insets var0, Insets var1) {
      return of(Math.min(var0.left, var1.left), Math.min(var0.top, var1.top), Math.min(var0.right, var1.right), Math.min(var0.bottom, var1.bottom));
   }

   public static Insets of(int var0, int var1, int var2, int var3) {
      return var0 == 0 && var1 == 0 && var2 == 0 && var3 == 0 ? NONE : new Insets(var0, var1, var2, var3);
   }

   public static Insets of(Rect var0) {
      return of(var0.left, var0.top, var0.right, var0.bottom);
   }

   public static Insets subtract(Insets var0, Insets var1) {
      return of(var0.left - var1.left, var0.top - var1.top, var0.right - var1.right, var0.bottom - var1.bottom);
   }

   public static Insets toCompatInsets(android.graphics.Insets var0) {
      return of(var0.left, var0.top, var0.right, var0.bottom);
   }

   @Deprecated
   public static Insets wrap(android.graphics.Insets var0) {
      return toCompatInsets(var0);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Insets var2 = (Insets)var1;
         if (this.bottom != var2.bottom) {
            return false;
         } else if (this.left != var2.left) {
            return false;
         } else if (this.right != var2.right) {
            return false;
         } else {
            return this.top == var2.top;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return ((this.left * 31 + this.top) * 31 + this.right) * 31 + this.bottom;
   }

   public android.graphics.Insets toPlatformInsets() {
      return android.graphics.Insets.of(this.left, this.top, this.right, this.bottom);
   }

   public String toString() {
      return "Insets{left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + '}';
   }
}
