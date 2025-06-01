package androidx.appcompat.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ActionProvider;

public class ShareActionProvider extends ActionProvider {
   private static final int DEFAULT_INITIAL_ACTIVITY_COUNT = 4;
   public static final String DEFAULT_SHARE_HISTORY_FILE_NAME = "share_history.xml";
   final Context mContext;
   private int mMaxShownActivityCount = 4;
   private ActivityChooserModel.OnChooseActivityListener mOnChooseActivityListener;
   private final ShareMenuItemOnMenuItemClickListener mOnMenuItemClickListener = new ShareMenuItemOnMenuItemClickListener(this);
   OnShareTargetSelectedListener mOnShareTargetSelectedListener;
   String mShareHistoryFileName = "share_history.xml";

   public ShareActionProvider(Context var1) {
      super(var1);
      this.mContext = var1;
   }

   private void setActivityChooserPolicyIfNeeded() {
      if (this.mOnShareTargetSelectedListener != null) {
         if (this.mOnChooseActivityListener == null) {
            this.mOnChooseActivityListener = new ShareActivityChooserModelPolicy(this);
         }

         ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName).setOnChooseActivityListener(this.mOnChooseActivityListener);
      }
   }

   public boolean hasSubMenu() {
      return true;
   }

   public View onCreateActionView() {
      ActivityChooserView var2 = new ActivityChooserView(this.mContext);
      if (!var2.isInEditMode()) {
         var2.setActivityChooserModel(ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName));
      }

      TypedValue var1 = new TypedValue();
      this.mContext.getTheme().resolveAttribute(R.attr.actionModeShareDrawable, var1, true);
      var2.setExpandActivityOverflowButtonDrawable(AppCompatResources.getDrawable(this.mContext, var1.resourceId));
      var2.setProvider(this);
      var2.setDefaultActionButtonContentDescription(R.string.abc_shareactionprovider_share_with_application);
      var2.setExpandActivityOverflowButtonContentDescription(R.string.abc_shareactionprovider_share_with);
      return var2;
   }

   public void onPrepareSubMenu(SubMenu var1) {
      var1.clear();
      ActivityChooserModel var6 = ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName);
      PackageManager var5 = this.mContext.getPackageManager();
      int var3 = var6.getActivityCount();
      int var4 = Math.min(var3, this.mMaxShownActivityCount);

      int var2;
      ResolveInfo var7;
      for(var2 = 0; var2 < var4; ++var2) {
         var7 = var6.getActivity(var2);
         var1.add(0, var2, var2, var7.loadLabel(var5)).setIcon(var7.loadIcon(var5)).setOnMenuItemClickListener(this.mOnMenuItemClickListener);
      }

      if (var4 < var3) {
         var1 = var1.addSubMenu(0, var4, var4, this.mContext.getString(R.string.abc_activity_chooser_view_see_all));

         for(var2 = 0; var2 < var3; ++var2) {
            var7 = var6.getActivity(var2);
            var1.add(0, var2, var2, var7.loadLabel(var5)).setIcon(var7.loadIcon(var5)).setOnMenuItemClickListener(this.mOnMenuItemClickListener);
         }
      }

   }

   public void setOnShareTargetSelectedListener(OnShareTargetSelectedListener var1) {
      this.mOnShareTargetSelectedListener = var1;
      this.setActivityChooserPolicyIfNeeded();
   }

   public void setShareHistoryFileName(String var1) {
      this.mShareHistoryFileName = var1;
      this.setActivityChooserPolicyIfNeeded();
   }

   public void setShareIntent(Intent var1) {
      if (var1 != null) {
         String var2 = var1.getAction();
         if ("android.intent.action.SEND".equals(var2) || "android.intent.action.SEND_MULTIPLE".equals(var2)) {
            this.updateIntent(var1);
         }
      }

      ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName).setIntent(var1);
   }

   void updateIntent(Intent var1) {
      if (VERSION.SDK_INT >= 21) {
         var1.addFlags(134742016);
      } else {
         var1.addFlags(524288);
      }

   }

   public interface OnShareTargetSelectedListener {
      boolean onShareTargetSelected(ShareActionProvider var1, Intent var2);
   }

   private class ShareActivityChooserModelPolicy implements ActivityChooserModel.OnChooseActivityListener {
      final ShareActionProvider this$0;

      ShareActivityChooserModelPolicy(ShareActionProvider var1) {
         this.this$0 = var1;
      }

      public boolean onChooseActivity(ActivityChooserModel var1, Intent var2) {
         if (this.this$0.mOnShareTargetSelectedListener != null) {
            this.this$0.mOnShareTargetSelectedListener.onShareTargetSelected(this.this$0, var2);
         }

         return false;
      }
   }

   private class ShareMenuItemOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {
      final ShareActionProvider this$0;

      ShareMenuItemOnMenuItemClickListener(ShareActionProvider var1) {
         this.this$0 = var1;
      }

      public boolean onMenuItemClick(MenuItem var1) {
         Intent var3 = ActivityChooserModel.get(this.this$0.mContext, this.this$0.mShareHistoryFileName).chooseActivity(var1.getItemId());
         if (var3 != null) {
            String var2 = var3.getAction();
            if ("android.intent.action.SEND".equals(var2) || "android.intent.action.SEND_MULTIPLE".equals(var2)) {
               this.this$0.updateIntent(var3);
            }

            this.this$0.mContext.startActivity(var3);
         }

         return true;
      }
   }
}
