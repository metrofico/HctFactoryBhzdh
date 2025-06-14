package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.internal.view.SupportMenuItem;

public class ActionMenuItem implements SupportMenuItem {
   private static final int CHECKABLE = 1;
   private static final int CHECKED = 2;
   private static final int ENABLED = 16;
   private static final int EXCLUSIVE = 4;
   private static final int HIDDEN = 8;
   private MenuItem.OnMenuItemClickListener mClickListener;
   private CharSequence mContentDescription;
   private Context mContext;
   private int mFlags = 16;
   private final int mGroup;
   private boolean mHasIconTint = false;
   private boolean mHasIconTintMode = false;
   private Drawable mIconDrawable;
   private ColorStateList mIconTintList = null;
   private PorterDuff.Mode mIconTintMode = null;
   private final int mId;
   private Intent mIntent;
   private final int mOrdering;
   private char mShortcutAlphabeticChar;
   private int mShortcutAlphabeticModifiers = 4096;
   private char mShortcutNumericChar;
   private int mShortcutNumericModifiers = 4096;
   private CharSequence mTitle;
   private CharSequence mTitleCondensed;
   private CharSequence mTooltipText;

   public ActionMenuItem(Context var1, int var2, int var3, int var4, int var5, CharSequence var6) {
      this.mContext = var1;
      this.mId = var3;
      this.mGroup = var2;
      this.mOrdering = var5;
      this.mTitle = var6;
   }

   private void applyIconTint() {
      Drawable var1 = this.mIconDrawable;
      if (var1 != null && (this.mHasIconTint || this.mHasIconTintMode)) {
         var1 = DrawableCompat.wrap(var1);
         this.mIconDrawable = var1;
         var1 = var1.mutate();
         this.mIconDrawable = var1;
         if (this.mHasIconTint) {
            DrawableCompat.setTintList(var1, this.mIconTintList);
         }

         if (this.mHasIconTintMode) {
            DrawableCompat.setTintMode(this.mIconDrawable, this.mIconTintMode);
         }
      }

   }

   public boolean collapseActionView() {
      return false;
   }

   public boolean expandActionView() {
      return false;
   }

   public ActionProvider getActionProvider() {
      throw new UnsupportedOperationException();
   }

   public View getActionView() {
      return null;
   }

   public int getAlphabeticModifiers() {
      return this.mShortcutAlphabeticModifiers;
   }

   public char getAlphabeticShortcut() {
      return this.mShortcutAlphabeticChar;
   }

   public CharSequence getContentDescription() {
      return this.mContentDescription;
   }

   public int getGroupId() {
      return this.mGroup;
   }

   public Drawable getIcon() {
      return this.mIconDrawable;
   }

   public ColorStateList getIconTintList() {
      return this.mIconTintList;
   }

   public PorterDuff.Mode getIconTintMode() {
      return this.mIconTintMode;
   }

   public Intent getIntent() {
      return this.mIntent;
   }

   public int getItemId() {
      return this.mId;
   }

   public ContextMenu.ContextMenuInfo getMenuInfo() {
      return null;
   }

   public int getNumericModifiers() {
      return this.mShortcutNumericModifiers;
   }

   public char getNumericShortcut() {
      return this.mShortcutNumericChar;
   }

   public int getOrder() {
      return this.mOrdering;
   }

   public SubMenu getSubMenu() {
      return null;
   }

   public androidx.core.view.ActionProvider getSupportActionProvider() {
      return null;
   }

   public CharSequence getTitle() {
      return this.mTitle;
   }

   public CharSequence getTitleCondensed() {
      CharSequence var1 = this.mTitleCondensed;
      if (var1 == null) {
         var1 = this.mTitle;
      }

      return var1;
   }

   public CharSequence getTooltipText() {
      return this.mTooltipText;
   }

   public boolean hasSubMenu() {
      return false;
   }

   public boolean invoke() {
      MenuItem.OnMenuItemClickListener var1 = this.mClickListener;
      if (var1 != null && var1.onMenuItemClick(this)) {
         return true;
      } else {
         Intent var2 = this.mIntent;
         if (var2 != null) {
            this.mContext.startActivity(var2);
            return true;
         } else {
            return false;
         }
      }
   }

   public boolean isActionViewExpanded() {
      return false;
   }

   public boolean isCheckable() {
      int var1 = this.mFlags;
      boolean var2 = true;
      if ((var1 & 1) == 0) {
         var2 = false;
      }

      return var2;
   }

   public boolean isChecked() {
      boolean var1;
      if ((this.mFlags & 2) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isEnabled() {
      boolean var1;
      if ((this.mFlags & 16) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isVisible() {
      boolean var1;
      if ((this.mFlags & 8) == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean requiresActionButton() {
      return true;
   }

   public boolean requiresOverflow() {
      return false;
   }

   public MenuItem setActionProvider(ActionProvider var1) {
      throw new UnsupportedOperationException();
   }

   public SupportMenuItem setActionView(int var1) {
      throw new UnsupportedOperationException();
   }

   public SupportMenuItem setActionView(View var1) {
      throw new UnsupportedOperationException();
   }

   public MenuItem setAlphabeticShortcut(char var1) {
      this.mShortcutAlphabeticChar = Character.toLowerCase(var1);
      return this;
   }

   public MenuItem setAlphabeticShortcut(char var1, int var2) {
      this.mShortcutAlphabeticChar = Character.toLowerCase(var1);
      this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(var2);
      return this;
   }

   public MenuItem setCheckable(boolean var1) {
      this.mFlags = var1 | this.mFlags & -2;
      return this;
   }

   public MenuItem setChecked(boolean var1) {
      int var3 = this.mFlags;
      byte var2;
      if (var1) {
         var2 = 2;
      } else {
         var2 = 0;
      }

      this.mFlags = var2 | var3 & -3;
      return this;
   }

   public SupportMenuItem setContentDescription(CharSequence var1) {
      this.mContentDescription = var1;
      return this;
   }

   public MenuItem setEnabled(boolean var1) {
      int var3 = this.mFlags;
      byte var2;
      if (var1) {
         var2 = 16;
      } else {
         var2 = 0;
      }

      this.mFlags = var2 | var3 & -17;
      return this;
   }

   public ActionMenuItem setExclusiveCheckable(boolean var1) {
      int var3 = this.mFlags;
      byte var2;
      if (var1) {
         var2 = 4;
      } else {
         var2 = 0;
      }

      this.mFlags = var2 | var3 & -5;
      return this;
   }

   public MenuItem setIcon(int var1) {
      this.mIconDrawable = ContextCompat.getDrawable(this.mContext, var1);
      this.applyIconTint();
      return this;
   }

   public MenuItem setIcon(Drawable var1) {
      this.mIconDrawable = var1;
      this.applyIconTint();
      return this;
   }

   public MenuItem setIconTintList(ColorStateList var1) {
      this.mIconTintList = var1;
      this.mHasIconTint = true;
      this.applyIconTint();
      return this;
   }

   public MenuItem setIconTintMode(PorterDuff.Mode var1) {
      this.mIconTintMode = var1;
      this.mHasIconTintMode = true;
      this.applyIconTint();
      return this;
   }

   public MenuItem setIntent(Intent var1) {
      this.mIntent = var1;
      return this;
   }

   public MenuItem setNumericShortcut(char var1) {
      this.mShortcutNumericChar = var1;
      return this;
   }

   public MenuItem setNumericShortcut(char var1, int var2) {
      this.mShortcutNumericChar = var1;
      this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(var2);
      return this;
   }

   public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener var1) {
      throw new UnsupportedOperationException();
   }

   public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener var1) {
      this.mClickListener = var1;
      return this;
   }

   public MenuItem setShortcut(char var1, char var2) {
      this.mShortcutNumericChar = var1;
      this.mShortcutAlphabeticChar = Character.toLowerCase(var2);
      return this;
   }

   public MenuItem setShortcut(char var1, char var2, int var3, int var4) {
      this.mShortcutNumericChar = var1;
      this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(var3);
      this.mShortcutAlphabeticChar = Character.toLowerCase(var2);
      this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(var4);
      return this;
   }

   public void setShowAsAction(int var1) {
   }

   public SupportMenuItem setShowAsActionFlags(int var1) {
      this.setShowAsAction(var1);
      return this;
   }

   public SupportMenuItem setSupportActionProvider(androidx.core.view.ActionProvider var1) {
      throw new UnsupportedOperationException();
   }

   public MenuItem setTitle(int var1) {
      this.mTitle = this.mContext.getResources().getString(var1);
      return this;
   }

   public MenuItem setTitle(CharSequence var1) {
      this.mTitle = var1;
      return this;
   }

   public MenuItem setTitleCondensed(CharSequence var1) {
      this.mTitleCondensed = var1;
      return this;
   }

   public SupportMenuItem setTooltipText(CharSequence var1) {
      this.mTooltipText = var1;
      return this;
   }

   public MenuItem setVisible(boolean var1) {
      int var3 = this.mFlags;
      byte var2 = 8;
      if (var1) {
         var2 = 0;
      }

      this.mFlags = var3 & 8 | var2;
      return this;
   }
}
