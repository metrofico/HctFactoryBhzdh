package kotlin.random;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0000\u0018\u0000 \u00122\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001\u0012B\u0017\b\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007B7\b\u0000\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0016J\b\u0010\u0011\u001a\u00020\u0005H\u0016R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"},
   d2 = {"Lkotlin/random/XorWowRandom;", "Lkotlin/random/Random;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "seed1", "", "seed2", "(II)V", "x", "y", "z", "w", "v", "addend", "(IIIIII)V", "nextBits", "bitCount", "nextInt", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class XorWowRandom extends Random implements Serializable {
   private static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @Deprecated
   private static final long serialVersionUID = 0L;
   private int addend;
   private int v;
   private int w;
   private int x;
   private int y;
   private int z;

   public XorWowRandom(int var1, int var2) {
      this(var1, var2, 0, 0, ~var1, var1 << 10 ^ var2 >>> 4);
   }

   public XorWowRandom(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
      this.w = var4;
      this.v = var5;
      this.addend = var6;
      byte var8 = 0;
      boolean var7;
      if ((var1 | var2 | var3 | var4 | var5) != 0) {
         var7 = true;
      } else {
         var7 = false;
      }

      if (!var7) {
         throw new IllegalArgumentException("Initial state must have at least one non-zero element.".toString());
      } else {
         for(var1 = var8; var1 < 64; ++var1) {
            this.nextInt();
         }

      }
   }

   public int nextBits(int var1) {
      return RandomKt.takeUpperBits(this.nextInt(), var1);
   }

   public int nextInt() {
      int var1 = this.x;
      int var2 = var1 ^ var1 >>> 2;
      this.x = this.y;
      this.y = this.z;
      this.z = this.w;
      var1 = this.v;
      this.w = var1;
      var1 = var2 ^ var2 << 1 ^ var1 ^ var1 << 4;
      this.v = var1;
      var2 = this.addend + 362437;
      this.addend = var2;
      return var1 + var2;
   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
      d2 = {"Lkotlin/random/XorWowRandom$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }
   }
}
