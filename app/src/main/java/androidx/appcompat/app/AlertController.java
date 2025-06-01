package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import java.lang.ref.WeakReference;

class AlertController {
   ListAdapter mAdapter;
   private int mAlertDialogLayout;
   private final View.OnClickListener mButtonHandler = new View.OnClickListener(this) {
      final AlertController this$0;

      {
         this.this$0 = var1;
      }

      public void onClick(View var1) {
         Message var2;
         if (var1 == this.this$0.mButtonPositive && this.this$0.mButtonPositiveMessage != null) {
            var2 = Message.obtain(this.this$0.mButtonPositiveMessage);
         } else if (var1 == this.this$0.mButtonNegative && this.this$0.mButtonNegativeMessage != null) {
            var2 = Message.obtain(this.this$0.mButtonNegativeMessage);
         } else if (var1 == this.this$0.mButtonNeutral && this.this$0.mButtonNeutralMessage != null) {
            var2 = Message.obtain(this.this$0.mButtonNeutralMessage);
         } else {
            var2 = null;
         }

         if (var2 != null) {
            var2.sendToTarget();
         }

         this.this$0.mHandler.obtainMessage(1, this.this$0.mDialog).sendToTarget();
      }
   };
   private final int mButtonIconDimen;
   Button mButtonNegative;
   private Drawable mButtonNegativeIcon;
   Message mButtonNegativeMessage;
   private CharSequence mButtonNegativeText;
   Button mButtonNeutral;
   private Drawable mButtonNeutralIcon;
   Message mButtonNeutralMessage;
   private CharSequence mButtonNeutralText;
   private int mButtonPanelLayoutHint = 0;
   private int mButtonPanelSideLayout;
   Button mButtonPositive;
   private Drawable mButtonPositiveIcon;
   Message mButtonPositiveMessage;
   private CharSequence mButtonPositiveText;
   int mCheckedItem = -1;
   private final Context mContext;
   private View mCustomTitleView;
   final AppCompatDialog mDialog;
   Handler mHandler;
   private Drawable mIcon;
   private int mIconId = 0;
   private ImageView mIconView;
   int mListItemLayout;
   int mListLayout;
   ListView mListView;
   private CharSequence mMessage;
   private TextView mMessageView;
   int mMultiChoiceItemLayout;
   NestedScrollView mScrollView;
   private boolean mShowTitle;
   int mSingleChoiceItemLayout;
   private CharSequence mTitle;
   private TextView mTitleView;
   private View mView;
   private int mViewLayoutResId;
   private int mViewSpacingBottom;
   private int mViewSpacingLeft;
   private int mViewSpacingRight;
   private boolean mViewSpacingSpecified = false;
   private int mViewSpacingTop;
   private final Window mWindow;

   public AlertController(Context var1, AppCompatDialog var2, Window var3) {
      this.mContext = var1;
      this.mDialog = var2;
      this.mWindow = var3;
      this.mHandler = new ButtonHandler(var2);
      TypedArray var4 = var1.obtainStyledAttributes((AttributeSet)null, R.styleable.AlertDialog, R.attr.alertDialogStyle, 0);
      this.mAlertDialogLayout = var4.getResourceId(R.styleable.AlertDialog_android_layout, 0);
      this.mButtonPanelSideLayout = var4.getResourceId(R.styleable.AlertDialog_buttonPanelSideLayout, 0);
      this.mListLayout = var4.getResourceId(R.styleable.AlertDialog_listLayout, 0);
      this.mMultiChoiceItemLayout = var4.getResourceId(R.styleable.AlertDialog_multiChoiceItemLayout, 0);
      this.mSingleChoiceItemLayout = var4.getResourceId(R.styleable.AlertDialog_singleChoiceItemLayout, 0);
      this.mListItemLayout = var4.getResourceId(R.styleable.AlertDialog_listItemLayout, 0);
      this.mShowTitle = var4.getBoolean(R.styleable.AlertDialog_showTitle, true);
      this.mButtonIconDimen = var4.getDimensionPixelSize(R.styleable.AlertDialog_buttonIconDimen, 0);
      var4.recycle();
      var2.supportRequestWindowFeature(1);
   }

   static boolean canTextInput(View var0) {
      if (var0.onCheckIsTextEditor()) {
         return true;
      } else if (!(var0 instanceof ViewGroup)) {
         return false;
      } else {
         ViewGroup var3 = (ViewGroup)var0;
         int var1 = var3.getChildCount();

         int var2;
         do {
            if (var1 <= 0) {
               return false;
            }

            var2 = var1 - 1;
            var1 = var2;
         } while(!canTextInput(var3.getChildAt(var2)));

         return true;
      }
   }

   private void centerButton(Button var1) {
      LinearLayout.LayoutParams var2 = (LinearLayout.LayoutParams)var1.getLayoutParams();
      var2.gravity = 1;
      var2.weight = 0.5F;
      var1.setLayoutParams(var2);
   }

   static void manageScrollIndicators(View var0, View var1, View var2) {
      byte var4 = 0;
      byte var3;
      if (var1 != null) {
         if (var0.canScrollVertically(-1)) {
            var3 = 0;
         } else {
            var3 = 4;
         }

         var1.setVisibility(var3);
      }

      if (var2 != null) {
         if (var0.canScrollVertically(1)) {
            var3 = var4;
         } else {
            var3 = 4;
         }

         var2.setVisibility(var3);
      }

   }

   private ViewGroup resolvePanel(View var1, View var2) {
      if (var1 == null) {
         var1 = var2;
         if (var2 instanceof ViewStub) {
            var1 = ((ViewStub)var2).inflate();
         }

         return (ViewGroup)var1;
      } else {
         if (var2 != null) {
            ViewParent var3 = var2.getParent();
            if (var3 instanceof ViewGroup) {
               ((ViewGroup)var3).removeView(var2);
            }
         }

         var2 = var1;
         if (var1 instanceof ViewStub) {
            var2 = ((ViewStub)var1).inflate();
         }

         return (ViewGroup)var2;
      }
   }

   private int selectContentView() {
      int var1 = this.mButtonPanelSideLayout;
      if (var1 == 0) {
         return this.mAlertDialogLayout;
      } else {
         return this.mButtonPanelLayoutHint == 1 ? var1 : this.mAlertDialogLayout;
      }
   }

   private void setScrollIndicators(ViewGroup var1, View var2, int var3, int var4) {
      View var5 = this.mWindow.findViewById(R.id.scrollIndicatorUp);
      View var6 = this.mWindow.findViewById(R.id.scrollIndicatorDown);
      if (VERSION.SDK_INT >= 23) {
         ViewCompat.setScrollIndicators(var2, var3, var4);
         if (var5 != null) {
            var1.removeView(var5);
         }

         if (var6 != null) {
            var1.removeView(var6);
         }
      } else {
         var2 = var5;
         if (var5 != null) {
            var2 = var5;
            if ((var3 & 1) == 0) {
               var1.removeView(var5);
               var2 = null;
            }
         }

         var5 = var6;
         if (var6 != null) {
            var5 = var6;
            if ((var3 & 2) == 0) {
               var1.removeView(var6);
               var5 = null;
            }
         }

         if (var2 != null || var5 != null) {
            if (this.mMessage != null) {
               this.mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener(this, var2, var5) {
                  final AlertController this$0;
                  final View val$bottom;
                  final View val$top;

                  {
                     this.this$0 = var1;
                     this.val$top = var2;
                     this.val$bottom = var3;
                  }

                  public void onScrollChange(NestedScrollView var1, int var2, int var3, int var4, int var5) {
                     AlertController.manageScrollIndicators(var1, this.val$top, this.val$bottom);
                  }
               });
               this.mScrollView.post(new Runnable(this, var2, var5) {
                  final AlertController this$0;
                  final View val$bottom;
                  final View val$top;

                  {
                     this.this$0 = var1;
                     this.val$top = var2;
                     this.val$bottom = var3;
                  }

                  public void run() {
                     AlertController.manageScrollIndicators(this.this$0.mScrollView, this.val$top, this.val$bottom);
                  }
               });
            } else {
               ListView var7 = this.mListView;
               if (var7 != null) {
                  var7.setOnScrollListener(new AbsListView.OnScrollListener(this, var2, var5) {
                     final AlertController this$0;
                     final View val$bottom;
                     final View val$top;

                     {
                        this.this$0 = var1;
                        this.val$top = var2;
                        this.val$bottom = var3;
                     }

                     public void onScroll(AbsListView var1, int var2, int var3, int var4) {
                        AlertController.manageScrollIndicators(var1, this.val$top, this.val$bottom);
                     }

                     public void onScrollStateChanged(AbsListView var1, int var2) {
                     }
                  });
                  this.mListView.post(new Runnable(this, var2, var5) {
                     final AlertController this$0;
                     final View val$bottom;
                     final View val$top;

                     {
                        this.this$0 = var1;
                        this.val$top = var2;
                        this.val$bottom = var3;
                     }

                     public void run() {
                        AlertController.manageScrollIndicators(this.this$0.mListView, this.val$top, this.val$bottom);
                     }
                  });
               } else {
                  if (var2 != null) {
                     var1.removeView(var2);
                  }

                  if (var5 != null) {
                     var1.removeView(var5);
                  }
               }
            }
         }
      }

   }

   private void setupButtons(ViewGroup var1) {
      Button var6 = (Button)var1.findViewById(16908313);
      this.mButtonPositive = var6;
      var6.setOnClickListener(this.mButtonHandler);
      boolean var5 = TextUtils.isEmpty(this.mButtonPositiveText);
      boolean var3 = true;
      int var2;
      Drawable var8;
      if (var5 && this.mButtonPositiveIcon == null) {
         this.mButtonPositive.setVisibility(8);
         var2 = 0;
      } else {
         this.mButtonPositive.setText(this.mButtonPositiveText);
         var8 = this.mButtonPositiveIcon;
         if (var8 != null) {
            var2 = this.mButtonIconDimen;
            var8.setBounds(0, 0, var2, var2);
            this.mButtonPositive.setCompoundDrawables(this.mButtonPositiveIcon, (Drawable)null, (Drawable)null, (Drawable)null);
         }

         this.mButtonPositive.setVisibility(0);
         var2 = 1;
      }

      var6 = (Button)var1.findViewById(16908314);
      this.mButtonNegative = var6;
      var6.setOnClickListener(this.mButtonHandler);
      int var4;
      if (TextUtils.isEmpty(this.mButtonNegativeText) && this.mButtonNegativeIcon == null) {
         this.mButtonNegative.setVisibility(8);
      } else {
         this.mButtonNegative.setText(this.mButtonNegativeText);
         var8 = this.mButtonNegativeIcon;
         if (var8 != null) {
            var4 = this.mButtonIconDimen;
            var8.setBounds(0, 0, var4, var4);
            this.mButtonNegative.setCompoundDrawables(this.mButtonNegativeIcon, (Drawable)null, (Drawable)null, (Drawable)null);
         }

         this.mButtonNegative.setVisibility(0);
         var2 |= 2;
      }

      var6 = (Button)var1.findViewById(16908315);
      this.mButtonNeutral = var6;
      var6.setOnClickListener(this.mButtonHandler);
      if (TextUtils.isEmpty(this.mButtonNeutralText) && this.mButtonNeutralIcon == null) {
         this.mButtonNeutral.setVisibility(8);
      } else {
         this.mButtonNeutral.setText(this.mButtonNeutralText);
         var8 = this.mButtonNeutralIcon;
         if (var8 != null) {
            var4 = this.mButtonIconDimen;
            var8.setBounds(0, 0, var4, var4);
            this.mButtonNeutral.setCompoundDrawables(this.mButtonNeutralIcon, (Drawable)null, (Drawable)null, (Drawable)null);
         }

         this.mButtonNeutral.setVisibility(0);
         var2 |= 4;
      }

      if (shouldCenterSingleButton(this.mContext)) {
         if (var2 == 1) {
            this.centerButton(this.mButtonPositive);
         } else if (var2 == 2) {
            this.centerButton(this.mButtonNegative);
         } else if (var2 == 4) {
            this.centerButton(this.mButtonNeutral);
         }
      }

      boolean var7;
      if (var2 != 0) {
         var7 = var3;
      } else {
         var7 = false;
      }

      if (!var7) {
         var1.setVisibility(8);
      }

   }

   private void setupContent(ViewGroup var1) {
      NestedScrollView var3 = (NestedScrollView)this.mWindow.findViewById(R.id.scrollView);
      this.mScrollView = var3;
      var3.setFocusable(false);
      this.mScrollView.setNestedScrollingEnabled(false);
      TextView var4 = (TextView)var1.findViewById(16908299);
      this.mMessageView = var4;
      if (var4 != null) {
         CharSequence var5 = this.mMessage;
         if (var5 != null) {
            var4.setText(var5);
         } else {
            var4.setVisibility(8);
            this.mScrollView.removeView(this.mMessageView);
            if (this.mListView != null) {
               var1 = (ViewGroup)this.mScrollView.getParent();
               int var2 = var1.indexOfChild(this.mScrollView);
               var1.removeViewAt(var2);
               var1.addView(this.mListView, var2, new ViewGroup.LayoutParams(-1, -1));
            } else {
               var1.setVisibility(8);
            }
         }

      }
   }

   private void setupCustomContent(ViewGroup var1) {
      View var3 = this.mView;
      boolean var2 = false;
      if (var3 == null) {
         if (this.mViewLayoutResId != 0) {
            var3 = LayoutInflater.from(this.mContext).inflate(this.mViewLayoutResId, var1, false);
         } else {
            var3 = null;
         }
      }

      if (var3 != null) {
         var2 = true;
      }

      if (!var2 || !canTextInput(var3)) {
         this.mWindow.setFlags(131072, 131072);
      }

      if (var2) {
         FrameLayout var4 = (FrameLayout)this.mWindow.findViewById(R.id.custom);
         var4.addView(var3, new ViewGroup.LayoutParams(-1, -1));
         if (this.mViewSpacingSpecified) {
            var4.setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
         }

         if (this.mListView != null) {
            ((LinearLayoutCompat.LayoutParams)var1.getLayoutParams()).weight = 0.0F;
         }
      } else {
         var1.setVisibility(8);
      }

   }

   private void setupTitle(ViewGroup var1) {
      if (this.mCustomTitleView != null) {
         ViewGroup.LayoutParams var3 = new ViewGroup.LayoutParams(-1, -2);
         var1.addView(this.mCustomTitleView, 0, var3);
         this.mWindow.findViewById(R.id.title_template).setVisibility(8);
      } else {
         this.mIconView = (ImageView)this.mWindow.findViewById(16908294);
         if (TextUtils.isEmpty(this.mTitle) ^ true && this.mShowTitle) {
            TextView var4 = (TextView)this.mWindow.findViewById(R.id.alertTitle);
            this.mTitleView = var4;
            var4.setText(this.mTitle);
            int var2 = this.mIconId;
            if (var2 != 0) {
               this.mIconView.setImageResource(var2);
            } else {
               Drawable var5 = this.mIcon;
               if (var5 != null) {
                  this.mIconView.setImageDrawable(var5);
               } else {
                  this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
                  this.mIconView.setVisibility(8);
               }
            }
         } else {
            this.mWindow.findViewById(R.id.title_template).setVisibility(8);
            this.mIconView.setVisibility(8);
            var1.setVisibility(8);
         }
      }

   }

   private void setupView() {
      View var5 = this.mWindow.findViewById(R.id.parentPanel);
      View var7 = var5.findViewById(R.id.topPanel);
      View var6 = var5.findViewById(R.id.contentPanel);
      View var8 = var5.findViewById(R.id.buttonPanel);
      ViewGroup var13 = (ViewGroup)var5.findViewById(R.id.customPanel);
      this.setupCustomContent(var13);
      View var11 = var13.findViewById(R.id.topPanel);
      View var10 = var13.findViewById(R.id.contentPanel);
      View var9 = var13.findViewById(R.id.buttonPanel);
      ViewGroup var17 = this.resolvePanel(var11, var7);
      ViewGroup var15 = this.resolvePanel(var10, var6);
      ViewGroup var20 = this.resolvePanel(var9, var8);
      this.setupContent(var15);
      this.setupButtons(var20);
      this.setupTitle(var17);
      byte var2 = 0;
      boolean var1;
      if (var13 != null && var13.getVisibility() != 8) {
         var1 = true;
      } else {
         var1 = false;
      }

      byte var3;
      if (var17 != null && var17.getVisibility() != 8) {
         var3 = 1;
      } else {
         var3 = 0;
      }

      boolean var4;
      if (var20 != null && var20.getVisibility() != 8) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (!var4 && var15 != null) {
         var5 = var15.findViewById(R.id.textSpacerNoButtons);
         if (var5 != null) {
            var5.setVisibility(0);
         }
      }

      if (var3 != 0) {
         NestedScrollView var14 = this.mScrollView;
         if (var14 != null) {
            var14.setClipToPadding(true);
         }

         var5 = null;
         if (this.mMessage != null || this.mListView != null) {
            var5 = var17.findViewById(R.id.titleDividerNoCustom);
         }

         if (var5 != null) {
            var5.setVisibility(0);
         }
      } else if (var15 != null) {
         var5 = var15.findViewById(R.id.textSpacerNoTitle);
         if (var5 != null) {
            var5.setVisibility(0);
         }
      }

      ListView var18 = this.mListView;
      if (var18 instanceof RecycleListView) {
         ((RecycleListView)var18).setHasDecor((boolean)var3, var4);
      }

      int var12;
      if (!var1) {
         Object var19 = this.mListView;
         if (var19 == null) {
            var19 = this.mScrollView;
         }

         if (var19 != null) {
            var12 = var2;
            if (var4) {
               var12 = 2;
            }

            this.setScrollIndicators(var15, (View)var19, var3 | var12, 3);
         }
      }

      var18 = this.mListView;
      if (var18 != null) {
         ListAdapter var16 = this.mAdapter;
         if (var16 != null) {
            var18.setAdapter(var16);
            var12 = this.mCheckedItem;
            if (var12 > -1) {
               var18.setItemChecked(var12, true);
               var18.setSelection(var12);
            }
         }
      }

   }

   private static boolean shouldCenterSingleButton(Context var0) {
      TypedValue var3 = new TypedValue();
      Resources.Theme var4 = var0.getTheme();
      int var1 = R.attr.alertDialogCenterButtons;
      boolean var2 = true;
      var4.resolveAttribute(var1, var3, true);
      if (var3.data == 0) {
         var2 = false;
      }

      return var2;
   }

   public Button getButton(int var1) {
      if (var1 != -3) {
         if (var1 != -2) {
            return var1 != -1 ? null : this.mButtonPositive;
         } else {
            return this.mButtonNegative;
         }
      } else {
         return this.mButtonNeutral;
      }
   }

   public int getIconAttributeResId(int var1) {
      TypedValue var2 = new TypedValue();
      this.mContext.getTheme().resolveAttribute(var1, var2, true);
      return var2.resourceId;
   }

   public ListView getListView() {
      return this.mListView;
   }

   public void installContent() {
      int var1 = this.selectContentView();
      this.mDialog.setContentView(var1);
      this.setupView();
   }

   public boolean onKeyDown(int var1, KeyEvent var2) {
      NestedScrollView var4 = this.mScrollView;
      boolean var3;
      if (var4 != null && var4.executeKeyEvent(var2)) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public boolean onKeyUp(int var1, KeyEvent var2) {
      NestedScrollView var4 = this.mScrollView;
      boolean var3;
      if (var4 != null && var4.executeKeyEvent(var2)) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public void setButton(int var1, CharSequence var2, DialogInterface.OnClickListener var3, Message var4, Drawable var5) {
      Message var6 = var4;
      if (var4 == null) {
         var6 = var4;
         if (var3 != null) {
            var6 = this.mHandler.obtainMessage(var1, var3);
         }
      }

      if (var1 != -3) {
         if (var1 != -2) {
            if (var1 != -1) {
               throw new IllegalArgumentException("Button does not exist");
            }

            this.mButtonPositiveText = var2;
            this.mButtonPositiveMessage = var6;
            this.mButtonPositiveIcon = var5;
         } else {
            this.mButtonNegativeText = var2;
            this.mButtonNegativeMessage = var6;
            this.mButtonNegativeIcon = var5;
         }
      } else {
         this.mButtonNeutralText = var2;
         this.mButtonNeutralMessage = var6;
         this.mButtonNeutralIcon = var5;
      }

   }

   public void setButtonPanelLayoutHint(int var1) {
      this.mButtonPanelLayoutHint = var1;
   }

   public void setCustomTitle(View var1) {
      this.mCustomTitleView = var1;
   }

   public void setIcon(int var1) {
      this.mIcon = null;
      this.mIconId = var1;
      ImageView var2 = this.mIconView;
      if (var2 != null) {
         if (var1 != 0) {
            var2.setVisibility(0);
            this.mIconView.setImageResource(this.mIconId);
         } else {
            var2.setVisibility(8);
         }
      }

   }

   public void setIcon(Drawable var1) {
      this.mIcon = var1;
      this.mIconId = 0;
      ImageView var2 = this.mIconView;
      if (var2 != null) {
         if (var1 != null) {
            var2.setVisibility(0);
            this.mIconView.setImageDrawable(var1);
         } else {
            var2.setVisibility(8);
         }
      }

   }

   public void setMessage(CharSequence var1) {
      this.mMessage = var1;
      TextView var2 = this.mMessageView;
      if (var2 != null) {
         var2.setText(var1);
      }

   }

   public void setTitle(CharSequence var1) {
      this.mTitle = var1;
      TextView var2 = this.mTitleView;
      if (var2 != null) {
         var2.setText(var1);
      }

   }

   public void setView(int var1) {
      this.mView = null;
      this.mViewLayoutResId = var1;
      this.mViewSpacingSpecified = false;
   }

   public void setView(View var1) {
      this.mView = var1;
      this.mViewLayoutResId = 0;
      this.mViewSpacingSpecified = false;
   }

   public void setView(View var1, int var2, int var3, int var4, int var5) {
      this.mView = var1;
      this.mViewLayoutResId = 0;
      this.mViewSpacingSpecified = true;
      this.mViewSpacingLeft = var2;
      this.mViewSpacingTop = var3;
      this.mViewSpacingRight = var4;
      this.mViewSpacingBottom = var5;
   }

   public static class AlertParams {
      public ListAdapter mAdapter;
      public boolean mCancelable;
      public int mCheckedItem = -1;
      public boolean[] mCheckedItems;
      public final Context mContext;
      public Cursor mCursor;
      public View mCustomTitleView;
      public boolean mForceInverseBackground;
      public Drawable mIcon;
      public int mIconAttrId = 0;
      public int mIconId = 0;
      public final LayoutInflater mInflater;
      public String mIsCheckedColumn;
      public boolean mIsMultiChoice;
      public boolean mIsSingleChoice;
      public CharSequence[] mItems;
      public String mLabelColumn;
      public CharSequence mMessage;
      public Drawable mNegativeButtonIcon;
      public DialogInterface.OnClickListener mNegativeButtonListener;
      public CharSequence mNegativeButtonText;
      public Drawable mNeutralButtonIcon;
      public DialogInterface.OnClickListener mNeutralButtonListener;
      public CharSequence mNeutralButtonText;
      public DialogInterface.OnCancelListener mOnCancelListener;
      public DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
      public DialogInterface.OnClickListener mOnClickListener;
      public DialogInterface.OnDismissListener mOnDismissListener;
      public AdapterView.OnItemSelectedListener mOnItemSelectedListener;
      public DialogInterface.OnKeyListener mOnKeyListener;
      public OnPrepareListViewListener mOnPrepareListViewListener;
      public Drawable mPositiveButtonIcon;
      public DialogInterface.OnClickListener mPositiveButtonListener;
      public CharSequence mPositiveButtonText;
      public boolean mRecycleOnMeasure = true;
      public CharSequence mTitle;
      public View mView;
      public int mViewLayoutResId;
      public int mViewSpacingBottom;
      public int mViewSpacingLeft;
      public int mViewSpacingRight;
      public boolean mViewSpacingSpecified = false;
      public int mViewSpacingTop;

      public AlertParams(Context var1) {
         this.mContext = var1;
         this.mCancelable = true;
         this.mInflater = (LayoutInflater)var1.getSystemService("layout_inflater");
      }

      private void createListView(AlertController var1) {
         RecycleListView var4 = (RecycleListView)this.mInflater.inflate(var1.mListLayout, (ViewGroup)null);
         Object var3;
         if (this.mIsMultiChoice) {
            if (this.mCursor == null) {
               var3 = new ArrayAdapter(this, this.mContext, var1.mMultiChoiceItemLayout, 16908308, this.mItems, var4) {
                  final AlertParams this$0;
                  final RecycleListView val$listView;

                  {
                     this.this$0 = var1;
                     this.val$listView = var6;
                  }

                  public View getView(int var1, View var2, ViewGroup var3) {
                     var2 = super.getView(var1, var2, var3);
                     if (this.this$0.mCheckedItems != null && this.this$0.mCheckedItems[var1]) {
                        this.val$listView.setItemChecked(var1, true);
                     }

                     return var2;
                  }
               };
            } else {
               var3 = new CursorAdapter(this, this.mContext, this.mCursor, false, var4, var1) {
                  private final int mIsCheckedIndex;
                  private final int mLabelIndex;
                  final AlertParams this$0;
                  final AlertController val$dialog;
                  final RecycleListView val$listView;

                  {
                     this.this$0 = var1;
                     this.val$listView = var5;
                     this.val$dialog = var6;
                     Cursor var7 = this.getCursor();
                     this.mLabelIndex = var7.getColumnIndexOrThrow(var1.mLabelColumn);
                     this.mIsCheckedIndex = var7.getColumnIndexOrThrow(var1.mIsCheckedColumn);
                  }

                  public void bindView(View var1, Context var2, Cursor var3) {
                     ((CheckedTextView)var1.findViewById(16908308)).setText(var3.getString(this.mLabelIndex));
                     RecycleListView var7 = this.val$listView;
                     int var4 = var3.getPosition();
                     int var5 = var3.getInt(this.mIsCheckedIndex);
                     boolean var6 = true;
                     if (var5 != 1) {
                        var6 = false;
                     }

                     var7.setItemChecked(var4, var6);
                  }

                  public View newView(Context var1, Cursor var2, ViewGroup var3) {
                     return this.this$0.mInflater.inflate(this.val$dialog.mMultiChoiceItemLayout, var3, false);
                  }
               };
            }
         } else {
            int var2;
            if (this.mIsSingleChoice) {
               var2 = var1.mSingleChoiceItemLayout;
            } else {
               var2 = var1.mListItemLayout;
            }

            if (this.mCursor != null) {
               var3 = new SimpleCursorAdapter(this.mContext, var2, this.mCursor, new String[]{this.mLabelColumn}, new int[]{16908308});
            } else {
               var3 = this.mAdapter;
               if (var3 == null) {
                  var3 = new CheckedItemAdapter(this.mContext, var2, 16908308, this.mItems);
               }
            }
         }

         OnPrepareListViewListener var5 = this.mOnPrepareListViewListener;
         if (var5 != null) {
            var5.onPrepareListView(var4);
         }

         var1.mAdapter = (ListAdapter)var3;
         var1.mCheckedItem = this.mCheckedItem;
         if (this.mOnClickListener != null) {
            var4.setOnItemClickListener(new AdapterView.OnItemClickListener(this, var1) {
               final AlertParams this$0;
               final AlertController val$dialog;

               {
                  this.this$0 = var1;
                  this.val$dialog = var2;
               }

               public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
                  this.this$0.mOnClickListener.onClick(this.val$dialog.mDialog, var3);
                  if (!this.this$0.mIsSingleChoice) {
                     this.val$dialog.mDialog.dismiss();
                  }

               }
            });
         } else if (this.mOnCheckboxClickListener != null) {
            var4.setOnItemClickListener(new AdapterView.OnItemClickListener(this, var4, var1) {
               final AlertParams this$0;
               final AlertController val$dialog;
               final RecycleListView val$listView;

               {
                  this.this$0 = var1;
                  this.val$listView = var2;
                  this.val$dialog = var3;
               }

               public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
                  if (this.this$0.mCheckedItems != null) {
                     this.this$0.mCheckedItems[var3] = this.val$listView.isItemChecked(var3);
                  }

                  this.this$0.mOnCheckboxClickListener.onClick(this.val$dialog.mDialog, var3, this.val$listView.isItemChecked(var3));
               }
            });
         }

         AdapterView.OnItemSelectedListener var6 = this.mOnItemSelectedListener;
         if (var6 != null) {
            var4.setOnItemSelectedListener(var6);
         }

         if (this.mIsSingleChoice) {
            var4.setChoiceMode(1);
         } else if (this.mIsMultiChoice) {
            var4.setChoiceMode(2);
         }

         var1.mListView = var4;
      }

      public void apply(AlertController var1) {
         View var3 = this.mCustomTitleView;
         int var2;
         CharSequence var4;
         if (var3 != null) {
            var1.setCustomTitle(var3);
         } else {
            var4 = this.mTitle;
            if (var4 != null) {
               var1.setTitle(var4);
            }

            Drawable var5 = this.mIcon;
            if (var5 != null) {
               var1.setIcon(var5);
            }

            var2 = this.mIconId;
            if (var2 != 0) {
               var1.setIcon(var2);
            }

            var2 = this.mIconAttrId;
            if (var2 != 0) {
               var1.setIcon(var1.getIconAttributeResId(var2));
            }
         }

         var4 = this.mMessage;
         if (var4 != null) {
            var1.setMessage(var4);
         }

         var4 = this.mPositiveButtonText;
         if (var4 != null || this.mPositiveButtonIcon != null) {
            var1.setButton(-1, var4, this.mPositiveButtonListener, (Message)null, this.mPositiveButtonIcon);
         }

         var4 = this.mNegativeButtonText;
         if (var4 != null || this.mNegativeButtonIcon != null) {
            var1.setButton(-2, var4, this.mNegativeButtonListener, (Message)null, this.mNegativeButtonIcon);
         }

         var4 = this.mNeutralButtonText;
         if (var4 != null || this.mNeutralButtonIcon != null) {
            var1.setButton(-3, var4, this.mNeutralButtonListener, (Message)null, this.mNeutralButtonIcon);
         }

         if (this.mItems != null || this.mCursor != null || this.mAdapter != null) {
            this.createListView(var1);
         }

         var3 = this.mView;
         if (var3 != null) {
            if (this.mViewSpacingSpecified) {
               var1.setView(var3, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            } else {
               var1.setView(var3);
            }
         } else {
            var2 = this.mViewLayoutResId;
            if (var2 != 0) {
               var1.setView(var2);
            }
         }

      }

      public interface OnPrepareListViewListener {
         void onPrepareListView(ListView var1);
      }
   }

   private static final class ButtonHandler extends Handler {
      private static final int MSG_DISMISS_DIALOG = 1;
      private WeakReference mDialog;

      public ButtonHandler(DialogInterface var1) {
         this.mDialog = new WeakReference(var1);
      }

      public void handleMessage(Message var1) {
         int var2 = var1.what;
         if (var2 != -3 && var2 != -2 && var2 != -1) {
            if (var2 == 1) {
               ((DialogInterface)var1.obj).dismiss();
            }
         } else {
            ((DialogInterface.OnClickListener)var1.obj).onClick((DialogInterface)this.mDialog.get(), var1.what);
         }

      }
   }

   private static class CheckedItemAdapter extends ArrayAdapter {
      public CheckedItemAdapter(Context var1, int var2, int var3, CharSequence[] var4) {
         super(var1, var2, var3, var4);
      }

      public long getItemId(int var1) {
         return (long)var1;
      }

      public boolean hasStableIds() {
         return true;
      }
   }

   public static class RecycleListView extends ListView {
      private final int mPaddingBottomNoButtons;
      private final int mPaddingTopNoTitle;

      public RecycleListView(Context var1) {
         this(var1, (AttributeSet)null);
      }

      public RecycleListView(Context var1, AttributeSet var2) {
         super(var1, var2);
         TypedArray var3 = var1.obtainStyledAttributes(var2, R.styleable.RecycleListView);
         this.mPaddingBottomNoButtons = var3.getDimensionPixelOffset(R.styleable.RecycleListView_paddingBottomNoButtons, -1);
         this.mPaddingTopNoTitle = var3.getDimensionPixelOffset(R.styleable.RecycleListView_paddingTopNoTitle, -1);
      }

      public void setHasDecor(boolean var1, boolean var2) {
         if (!var2 || !var1) {
            int var5 = this.getPaddingLeft();
            int var3;
            if (var1) {
               var3 = this.getPaddingTop();
            } else {
               var3 = this.mPaddingTopNoTitle;
            }

            int var6 = this.getPaddingRight();
            int var4;
            if (var2) {
               var4 = this.getPaddingBottom();
            } else {
               var4 = this.mPaddingBottomNoButtons;
            }

            this.setPadding(var5, var3, var6, var4);
         }

      }
   }
}
