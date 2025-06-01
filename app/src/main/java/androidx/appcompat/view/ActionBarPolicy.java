package androidx.appcompat.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import androidx.appcompat.R;

public class ActionBarPolicy {
   private Context mContext;

   private ActionBarPolicy(Context var1) {
      this.mContext = var1;
   }

   public static ActionBarPolicy get(Context var0) {
      return new ActionBarPolicy(var0);
   }

   public boolean enableHomeButtonByDefault() {
      boolean var1;
      if (this.mContext.getApplicationInfo().targetSdkVersion < 14) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public int getEmbeddedMenuWidthLimit() {
      return this.mContext.getResources().getDisplayMetrics().widthPixels / 2;
   }

   public int getMaxActionButtons() {
      Configuration var3 = this.mContext.getResources().getConfiguration();
      int var2 = var3.screenWidthDp;
      int var1 = var3.screenHeightDp;
      if (var3.smallestScreenWidthDp <= 600 && var2 <= 600 && (var2 <= 960 || var1 <= 720) && (var2 <= 720 || var1 <= 960)) {
         if (var2 < 500 && (var2 <= 640 || var1 <= 480) && (var2 <= 480 || var1 <= 640)) {
            return var2 >= 360 ? 3 : 2;
         } else {
            return 4;
         }
      } else {
         return 5;
      }
   }

   public int getStackedTabMaxWidth() {
      return this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
   }

   public int getTabContainerHeight() {
      TypedArray var3 = this.mContext.obtainStyledAttributes((AttributeSet)null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
      int var2 = var3.getLayoutDimension(R.styleable.ActionBar_height, 0);
      Resources var4 = this.mContext.getResources();
      int var1 = var2;
      if (!this.hasEmbeddedTabs()) {
         var1 = Math.min(var2, var4.getDimensionPixelSize(R.dimen.abc_action_bar_stacked_max_height));
      }

      var3.recycle();
      return var1;
   }

   public boolean hasEmbeddedTabs() {
      return this.mContext.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs);
   }

   public boolean showsOverflowMenuButton() {
      return VERSION.SDK_INT >= 19 ? true : ViewConfiguration.get(this.mContext).hasPermanentMenuKey() ^ true;
   }
}
