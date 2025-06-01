package com.hzbhd.canbus.car._161;

import android.content.Context;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.nio.charset.Charset;

public final class UiMgr$$ExternalSyntheticLambda1 implements OnSettingItemSelectListener {
   public final SettingPageUiSet f$0;
   public final UiMgr f$1;
   public final Context f$2;
   public final byte[] f$3;
   public final Charset f$4;

   // $FF: synthetic method
   public UiMgr$$ExternalSyntheticLambda1(SettingPageUiSet var1, UiMgr var2, Context var3, byte[] var4, Charset var5) {
      this.f$0 = var1;
      this.f$1 = var2;
      this.f$2 = var3;
      this.f$3 = var4;
      this.f$4 = var5;
   }

   public final void onClickItem(int var1, int var2, int var3) {
      UiMgr.$r8$lambda$pRR1QwLgKXwwdaSHaIDfhQRZtqo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, var1, var2, var3);
   }
}
