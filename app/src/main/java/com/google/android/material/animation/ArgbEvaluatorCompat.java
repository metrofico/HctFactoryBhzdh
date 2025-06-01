package com.google.android.material.animation;

import android.animation.TypeEvaluator;

public class ArgbEvaluatorCompat implements TypeEvaluator {
   private static final ArgbEvaluatorCompat instance = new ArgbEvaluatorCompat();

   public static ArgbEvaluatorCompat getInstance() {
      return instance;
   }

   public Integer evaluate(float var1, Integer var2, Integer var3) {
      int var12 = var2;
      float var5 = (float)(var12 >> 24 & 255) / 255.0F;
      float var6 = (float)(var12 >> 16 & 255) / 255.0F;
      float var9 = (float)(var12 >> 8 & 255) / 255.0F;
      float var7 = (float)(var12 & 255) / 255.0F;
      var12 = var3;
      float var4 = (float)(var12 >> 24 & 255) / 255.0F;
      float var11 = (float)(var12 >> 16 & 255) / 255.0F;
      float var10 = (float)(var12 >> 8 & 255) / 255.0F;
      float var8 = (float)(var12 & 255) / 255.0F;
      var6 = (float)Math.pow((double)var6, 2.2);
      var9 = (float)Math.pow((double)var9, 2.2);
      var7 = (float)Math.pow((double)var7, 2.2);
      var11 = (float)Math.pow((double)var11, 2.2);
      var10 = (float)Math.pow((double)var10, 2.2);
      var8 = (float)Math.pow((double)var8, 2.2);
      var6 = (float)Math.pow((double)(var6 + (var11 - var6) * var1), 0.45454545454545453);
      var9 = (float)Math.pow((double)(var9 + (var10 - var9) * var1), 0.45454545454545453);
      var7 = (float)Math.pow((double)(var7 + var1 * (var8 - var7)), 0.45454545454545453);
      var12 = Math.round((var5 + (var4 - var5) * var1) * 255.0F);
      return Math.round(var6 * 255.0F) << 16 | var12 << 24 | Math.round(var9 * 255.0F) << 8 | Math.round(var7 * 255.0F);
   }
}
