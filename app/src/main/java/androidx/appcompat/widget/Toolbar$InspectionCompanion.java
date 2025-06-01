package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.appcompat.R;

public final class Toolbar$InspectionCompanion implements InspectionCompanion {
   private int mCollapseContentDescriptionId;
   private int mCollapseIconId;
   private int mContentInsetEndId;
   private int mContentInsetEndWithActionsId;
   private int mContentInsetLeftId;
   private int mContentInsetRightId;
   private int mContentInsetStartId;
   private int mContentInsetStartWithNavigationId;
   private int mLogoDescriptionId;
   private int mLogoId;
   private int mMenuId;
   private int mNavigationContentDescriptionId;
   private int mNavigationIconId;
   private int mPopupThemeId;
   private boolean mPropertiesMapped = false;
   private int mSubtitleId;
   private int mTitleId;
   private int mTitleMarginBottomId;
   private int mTitleMarginEndId;
   private int mTitleMarginStartId;
   private int mTitleMarginTopId;

   public void mapProperties(PropertyMapper var1) {
      this.mCollapseContentDescriptionId = var1.mapObject("collapseContentDescription", R.attr.collapseContentDescription);
      this.mCollapseIconId = var1.mapObject("collapseIcon", R.attr.collapseIcon);
      this.mContentInsetEndId = var1.mapInt("contentInsetEnd", R.attr.contentInsetEnd);
      this.mContentInsetEndWithActionsId = var1.mapInt("contentInsetEndWithActions", R.attr.contentInsetEndWithActions);
      this.mContentInsetLeftId = var1.mapInt("contentInsetLeft", R.attr.contentInsetLeft);
      this.mContentInsetRightId = var1.mapInt("contentInsetRight", R.attr.contentInsetRight);
      this.mContentInsetStartId = var1.mapInt("contentInsetStart", R.attr.contentInsetStart);
      this.mContentInsetStartWithNavigationId = var1.mapInt("contentInsetStartWithNavigation", R.attr.contentInsetStartWithNavigation);
      this.mLogoId = var1.mapObject("logo", R.attr.logo);
      this.mLogoDescriptionId = var1.mapObject("logoDescription", R.attr.logoDescription);
      this.mMenuId = var1.mapObject("menu", R.attr.menu);
      this.mNavigationContentDescriptionId = var1.mapObject("navigationContentDescription", R.attr.navigationContentDescription);
      this.mNavigationIconId = var1.mapObject("navigationIcon", R.attr.navigationIcon);
      this.mPopupThemeId = var1.mapResourceId("popupTheme", R.attr.popupTheme);
      this.mSubtitleId = var1.mapObject("subtitle", R.attr.subtitle);
      this.mTitleId = var1.mapObject("title", R.attr.title);
      this.mTitleMarginBottomId = var1.mapInt("titleMarginBottom", R.attr.titleMarginBottom);
      this.mTitleMarginEndId = var1.mapInt("titleMarginEnd", R.attr.titleMarginEnd);
      this.mTitleMarginStartId = var1.mapInt("titleMarginStart", R.attr.titleMarginStart);
      this.mTitleMarginTopId = var1.mapInt("titleMarginTop", R.attr.titleMarginTop);
      this.mPropertiesMapped = true;
   }

   public void readProperties(Toolbar var1, PropertyReader var2) {
      if (this.mPropertiesMapped) {
         var2.readObject(this.mCollapseContentDescriptionId, var1.getCollapseContentDescription());
         var2.readObject(this.mCollapseIconId, var1.getCollapseIcon());
         var2.readInt(this.mContentInsetEndId, var1.getContentInsetEnd());
         var2.readInt(this.mContentInsetEndWithActionsId, var1.getContentInsetEndWithActions());
         var2.readInt(this.mContentInsetLeftId, var1.getContentInsetLeft());
         var2.readInt(this.mContentInsetRightId, var1.getContentInsetRight());
         var2.readInt(this.mContentInsetStartId, var1.getContentInsetStart());
         var2.readInt(this.mContentInsetStartWithNavigationId, var1.getContentInsetStartWithNavigation());
         var2.readObject(this.mLogoId, var1.getLogo());
         var2.readObject(this.mLogoDescriptionId, var1.getLogoDescription());
         var2.readObject(this.mMenuId, var1.getMenu());
         var2.readObject(this.mNavigationContentDescriptionId, var1.getNavigationContentDescription());
         var2.readObject(this.mNavigationIconId, var1.getNavigationIcon());
         var2.readResourceId(this.mPopupThemeId, var1.getPopupTheme());
         var2.readObject(this.mSubtitleId, var1.getSubtitle());
         var2.readObject(this.mTitleId, var1.getTitle());
         var2.readInt(this.mTitleMarginBottomId, var1.getTitleMarginBottom());
         var2.readInt(this.mTitleMarginEndId, var1.getTitleMarginEnd());
         var2.readInt(this.mTitleMarginStartId, var1.getTitleMarginStart());
         var2.readInt(this.mTitleMarginTopId, var1.getTitleMarginTop());
      } else {
         throw new InspectionCompanion.UninitializedPropertyMapException();
      }
   }
}
