package androidx.appcompat.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuItemWrapperICS;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.ActionProvider;
import androidx.core.view.MenuItemCompat;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SupportMenuInflater extends MenuInflater {
   static final Class[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE;
   static final Class[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
   static final String LOG_TAG = "SupportMenuInflater";
   static final int NO_ID = 0;
   private static final String XML_GROUP = "group";
   private static final String XML_ITEM = "item";
   private static final String XML_MENU = "menu";
   final Object[] mActionProviderConstructorArguments;
   final Object[] mActionViewConstructorArguments;
   Context mContext;
   private Object mRealOwner;

   static {
      Class[] var0 = new Class[]{Context.class};
      ACTION_VIEW_CONSTRUCTOR_SIGNATURE = var0;
      ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = var0;
   }

   public SupportMenuInflater(Context var1) {
      super(var1);
      this.mContext = var1;
      Object[] var2 = new Object[]{var1};
      this.mActionViewConstructorArguments = var2;
      this.mActionProviderConstructorArguments = var2;
   }

   private Object findRealOwner(Object var1) {
      if (var1 instanceof Activity) {
         return var1;
      } else {
         Object var2 = var1;
         if (var1 instanceof ContextWrapper) {
            var2 = this.findRealOwner(((ContextWrapper)var1).getBaseContext());
         }

         return var2;
      }
   }

   private void parseMenu(XmlPullParser var1, AttributeSet var2, Menu var3) throws XmlPullParserException, IOException {
      MenuState var10 = new MenuState(this, var3);
      int var5 = var1.getEventType();

      int var4;
      String var12;
      do {
         if (var5 == 2) {
            var12 = var1.getName();
            if (!var12.equals("menu")) {
               throw new RuntimeException("Expecting menu, got " + var12);
            }

            var4 = var1.next();
            break;
         }

         var4 = var1.next();
         var5 = var4;
      } while(var4 != 1);

      String var9 = null;
      boolean var6 = false;

      int var8;
      for(boolean var14 = false; !var6; var4 = var8) {
         if (var4 == 1) {
            throw new RuntimeException("Unexpected end of document");
         }

         boolean var7;
         boolean var13;
         if (var4 != 2) {
            if (var4 != 3) {
               var7 = var6;
               var13 = var14;
               var12 = var9;
            } else {
               String var11 = var1.getName();
               if (var14 && var11.equals(var9)) {
                  var12 = null;
                  var13 = false;
                  var7 = var6;
               } else if (var11.equals("group")) {
                  var10.resetGroup();
                  var7 = var6;
                  var13 = var14;
                  var12 = var9;
               } else if (var11.equals("item")) {
                  var7 = var6;
                  var13 = var14;
                  var12 = var9;
                  if (!var10.hasAddedItem()) {
                     if (var10.itemActionProvider != null && var10.itemActionProvider.hasSubMenu()) {
                        var10.addSubMenuItem();
                        var7 = var6;
                        var13 = var14;
                        var12 = var9;
                     } else {
                        var10.addItem();
                        var7 = var6;
                        var13 = var14;
                        var12 = var9;
                     }
                  }
               } else {
                  var7 = var6;
                  var13 = var14;
                  var12 = var9;
                  if (var11.equals("menu")) {
                     var7 = true;
                     var13 = var14;
                     var12 = var9;
                  }
               }
            }
         } else if (var14) {
            var7 = var6;
            var13 = var14;
            var12 = var9;
         } else {
            var12 = var1.getName();
            if (var12.equals("group")) {
               var10.readGroup(var2);
               var7 = var6;
               var13 = var14;
               var12 = var9;
            } else if (var12.equals("item")) {
               var10.readItem(var2);
               var7 = var6;
               var13 = var14;
               var12 = var9;
            } else if (var12.equals("menu")) {
               this.parseMenu(var1, var2, var10.addSubMenuItem());
               var7 = var6;
               var13 = var14;
               var12 = var9;
            } else {
               var13 = true;
               var7 = var6;
            }
         }

         var8 = var1.next();
         var6 = var7;
         var14 = var13;
         var9 = var12;
      }

   }

   Object getRealOwner() {
      if (this.mRealOwner == null) {
         this.mRealOwner = this.findRealOwner(this.mContext);
      }

      return this.mRealOwner;
   }

   public void inflate(int param1, Menu param2) {
      // $FF: Couldn't be decompiled
   }

   private static class InflatedOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {
      private static final Class[] PARAM_TYPES = new Class[]{MenuItem.class};
      private Method mMethod;
      private Object mRealOwner;

      public InflatedOnMenuItemClickListener(Object var1, String var2) {
         this.mRealOwner = var1;
         Class var3 = var1.getClass();

         try {
            this.mMethod = var3.getMethod(var2, PARAM_TYPES);
         } catch (Exception var4) {
            InflateException var5 = new InflateException("Couldn't resolve menu item onClick handler " + var2 + " in class " + var3.getName());
            var5.initCause(var4);
            throw var5;
         }
      }

      public boolean onMenuItemClick(MenuItem var1) {
         try {
            if (this.mMethod.getReturnType() == Boolean.TYPE) {
               return (Boolean)this.mMethod.invoke(this.mRealOwner, var1);
            } else {
               this.mMethod.invoke(this.mRealOwner, var1);
               return true;
            }
         } catch (Exception var2) {
            throw new RuntimeException(var2);
         }
      }
   }

   private class MenuState {
      private static final int defaultGroupId = 0;
      private static final int defaultItemCategory = 0;
      private static final int defaultItemCheckable = 0;
      private static final boolean defaultItemChecked = false;
      private static final boolean defaultItemEnabled = true;
      private static final int defaultItemId = 0;
      private static final int defaultItemOrder = 0;
      private static final boolean defaultItemVisible = true;
      private int groupCategory;
      private int groupCheckable;
      private boolean groupEnabled;
      private int groupId;
      private int groupOrder;
      private boolean groupVisible;
      ActionProvider itemActionProvider;
      private String itemActionProviderClassName;
      private String itemActionViewClassName;
      private int itemActionViewLayout;
      private boolean itemAdded;
      private int itemAlphabeticModifiers;
      private char itemAlphabeticShortcut;
      private int itemCategoryOrder;
      private int itemCheckable;
      private boolean itemChecked;
      private CharSequence itemContentDescription;
      private boolean itemEnabled;
      private int itemIconResId;
      private ColorStateList itemIconTintList;
      private PorterDuff.Mode itemIconTintMode;
      private int itemId;
      private String itemListenerMethodName;
      private int itemNumericModifiers;
      private char itemNumericShortcut;
      private int itemShowAsAction;
      private CharSequence itemTitle;
      private CharSequence itemTitleCondensed;
      private CharSequence itemTooltipText;
      private boolean itemVisible;
      private Menu menu;
      final SupportMenuInflater this$0;

      public MenuState(SupportMenuInflater var1, Menu var2) {
         this.this$0 = var1;
         this.itemIconTintList = null;
         this.itemIconTintMode = null;
         this.menu = var2;
         this.resetGroup();
      }

      private char getShortcut(String var1) {
         return var1 == null ? '\u0000' : var1.charAt(0);
      }

      private Object newInstance(String var1, Class[] var2, Object[] var3) {
         try {
            Constructor var5 = Class.forName(var1, false, this.this$0.mContext.getClassLoader()).getConstructor(var2);
            var5.setAccessible(true);
            Object var6 = var5.newInstance(var3);
            return var6;
         } catch (Exception var4) {
            Log.w("SupportMenuInflater", "Cannot instantiate class: " + var1, var4);
            return null;
         }
      }

      private void setItem(MenuItem var1) {
         MenuItem var5 = var1.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled);
         int var3 = this.itemCheckable;
         boolean var2 = false;
         boolean var4;
         if (var3 >= 1) {
            var4 = true;
         } else {
            var4 = false;
         }

         var5.setCheckable(var4).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId);
         var3 = this.itemShowAsAction;
         if (var3 >= 0) {
            var1.setShowAsAction(var3);
         }

         if (this.itemListenerMethodName != null) {
            if (this.this$0.mContext.isRestricted()) {
               throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }

            var1.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(this.this$0.getRealOwner(), this.itemListenerMethodName));
         }

         if (this.itemCheckable >= 2) {
            if (var1 instanceof MenuItemImpl) {
               ((MenuItemImpl)var1).setExclusiveCheckable(true);
            } else if (var1 instanceof MenuItemWrapperICS) {
               ((MenuItemWrapperICS)var1).setExclusiveCheckable(true);
            }
         }

         String var6 = this.itemActionViewClassName;
         if (var6 != null) {
            var1.setActionView((View)this.newInstance(var6, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, this.this$0.mActionViewConstructorArguments));
            var2 = true;
         }

         var3 = this.itemActionViewLayout;
         if (var3 > 0) {
            if (!var2) {
               var1.setActionView(var3);
            } else {
               Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            }
         }

         ActionProvider var7 = this.itemActionProvider;
         if (var7 != null) {
            MenuItemCompat.setActionProvider(var1, var7);
         }

         MenuItemCompat.setContentDescription(var1, this.itemContentDescription);
         MenuItemCompat.setTooltipText(var1, this.itemTooltipText);
         MenuItemCompat.setAlphabeticShortcut(var1, this.itemAlphabeticShortcut, this.itemAlphabeticModifiers);
         MenuItemCompat.setNumericShortcut(var1, this.itemNumericShortcut, this.itemNumericModifiers);
         PorterDuff.Mode var8 = this.itemIconTintMode;
         if (var8 != null) {
            MenuItemCompat.setIconTintMode(var1, var8);
         }

         ColorStateList var9 = this.itemIconTintList;
         if (var9 != null) {
            MenuItemCompat.setIconTintList(var1, var9);
         }

      }

      public void addItem() {
         this.itemAdded = true;
         this.setItem(this.menu.add(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle));
      }

      public SubMenu addSubMenuItem() {
         this.itemAdded = true;
         SubMenu var1 = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
         this.setItem(var1.getItem());
         return var1;
      }

      public boolean hasAddedItem() {
         return this.itemAdded;
      }

      public void readGroup(AttributeSet var1) {
         TypedArray var2 = this.this$0.mContext.obtainStyledAttributes(var1, R.styleable.MenuGroup);
         this.groupId = var2.getResourceId(R.styleable.MenuGroup_android_id, 0);
         this.groupCategory = var2.getInt(R.styleable.MenuGroup_android_menuCategory, 0);
         this.groupOrder = var2.getInt(R.styleable.MenuGroup_android_orderInCategory, 0);
         this.groupCheckable = var2.getInt(R.styleable.MenuGroup_android_checkableBehavior, 0);
         this.groupVisible = var2.getBoolean(R.styleable.MenuGroup_android_visible, true);
         this.groupEnabled = var2.getBoolean(R.styleable.MenuGroup_android_enabled, true);
         var2.recycle();
      }

      public void readItem(AttributeSet var1) {
         TintTypedArray var4 = TintTypedArray.obtainStyledAttributes(this.this$0.mContext, var1, R.styleable.MenuItem);
         this.itemId = var4.getResourceId(R.styleable.MenuItem_android_id, 0);
         this.itemCategoryOrder = var4.getInt(R.styleable.MenuItem_android_menuCategory, this.groupCategory) & -65536 | var4.getInt(R.styleable.MenuItem_android_orderInCategory, this.groupOrder) & '\uffff';
         this.itemTitle = var4.getText(R.styleable.MenuItem_android_title);
         this.itemTitleCondensed = var4.getText(R.styleable.MenuItem_android_titleCondensed);
         this.itemIconResId = var4.getResourceId(R.styleable.MenuItem_android_icon, 0);
         this.itemAlphabeticShortcut = this.getShortcut(var4.getString(R.styleable.MenuItem_android_alphabeticShortcut));
         this.itemAlphabeticModifiers = var4.getInt(R.styleable.MenuItem_alphabeticModifiers, 4096);
         this.itemNumericShortcut = this.getShortcut(var4.getString(R.styleable.MenuItem_android_numericShortcut));
         this.itemNumericModifiers = var4.getInt(R.styleable.MenuItem_numericModifiers, 4096);
         if (var4.hasValue(R.styleable.MenuItem_android_checkable)) {
            this.itemCheckable = var4.getBoolean(R.styleable.MenuItem_android_checkable, false);
         } else {
            this.itemCheckable = this.groupCheckable;
         }

         this.itemChecked = var4.getBoolean(R.styleable.MenuItem_android_checked, false);
         this.itemVisible = var4.getBoolean(R.styleable.MenuItem_android_visible, this.groupVisible);
         this.itemEnabled = var4.getBoolean(R.styleable.MenuItem_android_enabled, this.groupEnabled);
         this.itemShowAsAction = var4.getInt(R.styleable.MenuItem_showAsAction, -1);
         this.itemListenerMethodName = var4.getString(R.styleable.MenuItem_android_onClick);
         this.itemActionViewLayout = var4.getResourceId(R.styleable.MenuItem_actionLayout, 0);
         this.itemActionViewClassName = var4.getString(R.styleable.MenuItem_actionViewClass);
         String var3 = var4.getString(R.styleable.MenuItem_actionProviderClass);
         this.itemActionProviderClassName = var3;
         boolean var2;
         if (var3 != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2 && this.itemActionViewLayout == 0 && this.itemActionViewClassName == null) {
            this.itemActionProvider = (ActionProvider)this.newInstance(var3, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, this.this$0.mActionProviderConstructorArguments);
         } else {
            if (var2) {
               Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
            }

            this.itemActionProvider = null;
         }

         this.itemContentDescription = var4.getText(R.styleable.MenuItem_contentDescription);
         this.itemTooltipText = var4.getText(R.styleable.MenuItem_tooltipText);
         if (var4.hasValue(R.styleable.MenuItem_iconTintMode)) {
            this.itemIconTintMode = DrawableUtils.parseTintMode(var4.getInt(R.styleable.MenuItem_iconTintMode, -1), this.itemIconTintMode);
         } else {
            this.itemIconTintMode = null;
         }

         if (var4.hasValue(R.styleable.MenuItem_iconTint)) {
            this.itemIconTintList = var4.getColorStateList(R.styleable.MenuItem_iconTint);
         } else {
            this.itemIconTintList = null;
         }

         var4.recycle();
         this.itemAdded = false;
      }

      public void resetGroup() {
         this.groupId = 0;
         this.groupCategory = 0;
         this.groupOrder = 0;
         this.groupCheckable = 0;
         this.groupVisible = true;
         this.groupEnabled = true;
      }
   }
}
