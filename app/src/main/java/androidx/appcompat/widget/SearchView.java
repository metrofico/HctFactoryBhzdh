package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.MeasureSpec;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.core.view.ViewCompat;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.customview.view.AbsSavedState;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class SearchView extends LinearLayoutCompat implements CollapsibleActionView {
   static final boolean DBG = false;
   private static final String IME_OPTION_NO_MICROPHONE = "nm";
   static final String LOG_TAG = "SearchView";
   static final PreQAutoCompleteTextViewReflector PRE_API_29_HIDDEN_METHOD_INVOKER;
   private Bundle mAppSearchData;
   private boolean mClearingFocus;
   final ImageView mCloseButton;
   private final ImageView mCollapsedIcon;
   private int mCollapsedImeOptions;
   private final CharSequence mDefaultQueryHint;
   private final View mDropDownAnchor;
   private boolean mExpandedInActionView;
   final ImageView mGoButton;
   private boolean mIconified;
   private boolean mIconifiedByDefault;
   private int mMaxWidth;
   private CharSequence mOldQueryText;
   private final View.OnClickListener mOnClickListener;
   private OnCloseListener mOnCloseListener;
   private final TextView.OnEditorActionListener mOnEditorActionListener;
   private final AdapterView.OnItemClickListener mOnItemClickListener;
   private final AdapterView.OnItemSelectedListener mOnItemSelectedListener;
   private OnQueryTextListener mOnQueryChangeListener;
   View.OnFocusChangeListener mOnQueryTextFocusChangeListener;
   private View.OnClickListener mOnSearchClickListener;
   private OnSuggestionListener mOnSuggestionListener;
   private final WeakHashMap mOutsideDrawablesCache;
   private CharSequence mQueryHint;
   private boolean mQueryRefinement;
   private Runnable mReleaseCursorRunnable;
   final ImageView mSearchButton;
   private final View mSearchEditFrame;
   private final Drawable mSearchHintIcon;
   private final View mSearchPlate;
   final SearchAutoComplete mSearchSrcTextView;
   private Rect mSearchSrcTextViewBounds;
   private Rect mSearchSrtTextViewBoundsExpanded;
   SearchableInfo mSearchable;
   private final View mSubmitArea;
   private boolean mSubmitButtonEnabled;
   private final int mSuggestionCommitIconResId;
   private final int mSuggestionRowLayout;
   CursorAdapter mSuggestionsAdapter;
   private int[] mTemp;
   private int[] mTemp2;
   View.OnKeyListener mTextKeyListener;
   private TextWatcher mTextWatcher;
   private UpdatableTouchDelegate mTouchDelegate;
   private final Runnable mUpdateDrawableStateRunnable;
   private CharSequence mUserQuery;
   private final Intent mVoiceAppSearchIntent;
   final ImageView mVoiceButton;
   private boolean mVoiceButtonEnabled;
   private final Intent mVoiceWebSearchIntent;

   static {
      PreQAutoCompleteTextViewReflector var0;
      if (VERSION.SDK_INT < 29) {
         var0 = new PreQAutoCompleteTextViewReflector();
      } else {
         var0 = null;
      }

      PRE_API_29_HIDDEN_METHOD_INVOKER = var0;
   }

   public SearchView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SearchView(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.searchViewStyle);
   }

   public SearchView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mSearchSrcTextViewBounds = new Rect();
      this.mSearchSrtTextViewBoundsExpanded = new Rect();
      this.mTemp = new int[2];
      this.mTemp2 = new int[2];
      this.mUpdateDrawableStateRunnable = new Runnable(this) {
         final SearchView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.updateFocusedState();
         }
      };
      this.mReleaseCursorRunnable = new Runnable(this) {
         final SearchView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            if (this.this$0.mSuggestionsAdapter instanceof SuggestionsAdapter) {
               this.this$0.mSuggestionsAdapter.changeCursor((Cursor)null);
            }

         }
      };
      this.mOutsideDrawablesCache = new WeakHashMap();
      View.OnClickListener var7 = new View.OnClickListener(this) {
         final SearchView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (var1 == this.this$0.mSearchButton) {
               this.this$0.onSearchClicked();
            } else if (var1 == this.this$0.mCloseButton) {
               this.this$0.onCloseClicked();
            } else if (var1 == this.this$0.mGoButton) {
               this.this$0.onSubmitQuery();
            } else if (var1 == this.this$0.mVoiceButton) {
               this.this$0.onVoiceClicked();
            } else if (var1 == this.this$0.mSearchSrcTextView) {
               this.this$0.forceSuggestionQuery();
            }

         }
      };
      this.mOnClickListener = var7;
      this.mTextKeyListener = new View.OnKeyListener(this) {
         final SearchView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onKey(View var1, int var2, KeyEvent var3) {
            if (this.this$0.mSearchable == null) {
               return false;
            } else if (this.this$0.mSearchSrcTextView.isPopupShowing() && this.this$0.mSearchSrcTextView.getListSelection() != -1) {
               return this.this$0.onSuggestionsKey(var1, var2, var3);
            } else if (!this.this$0.mSearchSrcTextView.isEmpty() && var3.hasNoModifiers() && var3.getAction() == 1 && var2 == 66) {
               var1.cancelLongPress();
               SearchView var4 = this.this$0;
               var4.launchQuerySearch(0, (String)null, var4.mSearchSrcTextView.getText().toString());
               return true;
            } else {
               return false;
            }
         }
      };
      TextView.OnEditorActionListener var8 = new TextView.OnEditorActionListener(this) {
         final SearchView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onEditorAction(TextView var1, int var2, KeyEvent var3) {
            this.this$0.onSubmitQuery();
            return true;
         }
      };
      this.mOnEditorActionListener = var8;
      AdapterView.OnItemClickListener var4 = new AdapterView.OnItemClickListener(this) {
         final SearchView this$0;

         {
            this.this$0 = var1;
         }

         public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.onItemClicked(var3, 0, (String)null);
         }
      };
      this.mOnItemClickListener = var4;
      AdapterView.OnItemSelectedListener var6 = new AdapterView.OnItemSelectedListener(this) {
         final SearchView this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.onItemSelected(var3);
         }

         public void onNothingSelected(AdapterView var1) {
         }
      };
      this.mOnItemSelectedListener = var6;
      this.mTextWatcher = new TextWatcher(this) {
         final SearchView this$0;

         {
            this.this$0 = var1;
         }

         public void afterTextChanged(Editable var1) {
         }

         public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
         }

         public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
            this.this$0.onTextChanged(var1);
         }
      };
      TintTypedArray var5 = TintTypedArray.obtainStyledAttributes(var1, var2, R.styleable.SearchView, var3, 0);
      ViewCompat.saveAttributeDataForStyleable(this, var1, R.styleable.SearchView, var2, var5.getWrappedTypeArray(), var3, 0);
      LayoutInflater.from(var1).inflate(var5.getResourceId(R.styleable.SearchView_layout, R.layout.abc_search_view), this, true);
      SearchAutoComplete var15 = (SearchAutoComplete)this.findViewById(R.id.search_src_text);
      this.mSearchSrcTextView = var15;
      var15.setSearchView(this);
      this.mSearchEditFrame = this.findViewById(R.id.search_edit_frame);
      View var14 = this.findViewById(R.id.search_plate);
      this.mSearchPlate = var14;
      View var9 = this.findViewById(R.id.submit_area);
      this.mSubmitArea = var9;
      ImageView var12 = (ImageView)this.findViewById(R.id.search_button);
      this.mSearchButton = var12;
      ImageView var17 = (ImageView)this.findViewById(R.id.search_go_btn);
      this.mGoButton = var17;
      ImageView var11 = (ImageView)this.findViewById(R.id.search_close_btn);
      this.mCloseButton = var11;
      ImageView var13 = (ImageView)this.findViewById(R.id.search_voice_btn);
      this.mVoiceButton = var13;
      ImageView var10 = (ImageView)this.findViewById(R.id.search_mag_icon);
      this.mCollapsedIcon = var10;
      ViewCompat.setBackground(var14, var5.getDrawable(R.styleable.SearchView_queryBackground));
      ViewCompat.setBackground(var9, var5.getDrawable(R.styleable.SearchView_submitBackground));
      var12.setImageDrawable(var5.getDrawable(R.styleable.SearchView_searchIcon));
      var17.setImageDrawable(var5.getDrawable(R.styleable.SearchView_goIcon));
      var11.setImageDrawable(var5.getDrawable(R.styleable.SearchView_closeIcon));
      var13.setImageDrawable(var5.getDrawable(R.styleable.SearchView_voiceIcon));
      var10.setImageDrawable(var5.getDrawable(R.styleable.SearchView_searchIcon));
      this.mSearchHintIcon = var5.getDrawable(R.styleable.SearchView_searchHintIcon);
      TooltipCompat.setTooltipText(var12, this.getResources().getString(R.string.abc_searchview_description_search));
      this.mSuggestionRowLayout = var5.getResourceId(R.styleable.SearchView_suggestionRowLayout, R.layout.abc_search_dropdown_item_icons_2line);
      this.mSuggestionCommitIconResId = var5.getResourceId(R.styleable.SearchView_commitIcon, 0);
      var12.setOnClickListener(var7);
      var11.setOnClickListener(var7);
      var17.setOnClickListener(var7);
      var13.setOnClickListener(var7);
      var15.setOnClickListener(var7);
      var15.addTextChangedListener(this.mTextWatcher);
      var15.setOnEditorActionListener(var8);
      var15.setOnItemClickListener(var4);
      var15.setOnItemSelectedListener(var6);
      var15.setOnKeyListener(this.mTextKeyListener);
      var15.setOnFocusChangeListener(new View.OnFocusChangeListener(this) {
         final SearchView this$0;

         {
            this.this$0 = var1;
         }

         public void onFocusChange(View var1, boolean var2) {
            if (this.this$0.mOnQueryTextFocusChangeListener != null) {
               this.this$0.mOnQueryTextFocusChangeListener.onFocusChange(this.this$0, var2);
            }

         }
      });
      this.setIconifiedByDefault(var5.getBoolean(R.styleable.SearchView_iconifiedByDefault, true));
      var3 = var5.getDimensionPixelSize(R.styleable.SearchView_android_maxWidth, -1);
      if (var3 != -1) {
         this.setMaxWidth(var3);
      }

      this.mDefaultQueryHint = var5.getText(R.styleable.SearchView_defaultQueryHint);
      this.mQueryHint = var5.getText(R.styleable.SearchView_queryHint);
      var3 = var5.getInt(R.styleable.SearchView_android_imeOptions, -1);
      if (var3 != -1) {
         this.setImeOptions(var3);
      }

      var3 = var5.getInt(R.styleable.SearchView_android_inputType, -1);
      if (var3 != -1) {
         this.setInputType(var3);
      }

      this.setFocusable(var5.getBoolean(R.styleable.SearchView_android_focusable, true));
      var5.recycle();
      Intent var18 = new Intent("android.speech.action.WEB_SEARCH");
      this.mVoiceWebSearchIntent = var18;
      var18.addFlags(268435456);
      var18.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
      var18 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
      this.mVoiceAppSearchIntent = var18;
      var18.addFlags(268435456);
      View var16 = this.findViewById(var15.getDropDownAnchor());
      this.mDropDownAnchor = var16;
      if (var16 != null) {
         var16.addOnLayoutChangeListener(new View.OnLayoutChangeListener(this) {
            final SearchView this$0;

            {
               this.this$0 = var1;
            }

            public void onLayoutChange(View var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
               this.this$0.adjustDropDownSizeAndPosition();
            }
         });
      }

      this.updateViewsVisibility(this.mIconifiedByDefault);
      this.updateQueryHint();
   }

   private Intent createIntent(String var1, Uri var2, String var3, String var4, int var5, String var6) {
      Intent var7 = new Intent(var1);
      var7.addFlags(268435456);
      if (var2 != null) {
         var7.setData(var2);
      }

      var7.putExtra("user_query", this.mUserQuery);
      if (var4 != null) {
         var7.putExtra("query", var4);
      }

      if (var3 != null) {
         var7.putExtra("intent_extra_data_key", var3);
      }

      Bundle var8 = this.mAppSearchData;
      if (var8 != null) {
         var7.putExtra("app_data", var8);
      }

      if (var5 != 0) {
         var7.putExtra("action_key", var5);
         var7.putExtra("action_msg", var6);
      }

      var7.setComponent(this.mSearchable.getSearchActivity());
      return var7;
   }

   private Intent createIntentFromSuggestion(Cursor var1, int var2, String var3) {
      RuntimeException var10000;
      label81: {
         String var5;
         boolean var10001;
         try {
            var5 = SuggestionsAdapter.getColumnString(var1, "suggest_intent_action");
         } catch (RuntimeException var16) {
            var10000 = var16;
            var10001 = false;
            break label81;
         }

         String var4 = var5;
         if (var5 == null) {
            try {
               var4 = this.mSearchable.getSuggestIntentAction();
            } catch (RuntimeException var15) {
               var10000 = var15;
               var10001 = false;
               break label81;
            }
         }

         var5 = var4;
         if (var4 == null) {
            var5 = "android.intent.action.SEARCH";
         }

         String var6;
         try {
            var6 = SuggestionsAdapter.getColumnString(var1, "suggest_intent_data");
         } catch (RuntimeException var14) {
            var10000 = var14;
            var10001 = false;
            break label81;
         }

         var4 = var6;
         if (var6 == null) {
            try {
               var4 = this.mSearchable.getSuggestIntentData();
            } catch (RuntimeException var13) {
               var10000 = var13;
               var10001 = false;
               break label81;
            }
         }

         var6 = var4;
         if (var4 != null) {
            String var7;
            try {
               var7 = SuggestionsAdapter.getColumnString(var1, "suggest_intent_data_id");
            } catch (RuntimeException var12) {
               var10000 = var12;
               var10001 = false;
               break label81;
            }

            var6 = var4;
            if (var7 != null) {
               try {
                  StringBuilder var20 = new StringBuilder();
                  var6 = var20.append(var4).append("/").append(Uri.encode(var7)).toString();
               } catch (RuntimeException var11) {
                  var10000 = var11;
                  var10001 = false;
                  break label81;
               }
            }
         }

         Uri var19;
         if (var6 == null) {
            var19 = null;
         } else {
            try {
               var19 = Uri.parse(var6);
            } catch (RuntimeException var10) {
               var10000 = var10;
               var10001 = false;
               break label81;
            }
         }

         try {
            var6 = SuggestionsAdapter.getColumnString(var1, "suggest_intent_query");
            Intent var18 = this.createIntent(var5, var19, SuggestionsAdapter.getColumnString(var1, "suggest_intent_extra_data"), var6, var2, var3);
            return var18;
         } catch (RuntimeException var9) {
            var10000 = var9;
            var10001 = false;
         }
      }

      RuntimeException var17 = var10000;

      try {
         var2 = var1.getPosition();
      } catch (RuntimeException var8) {
         var2 = -1;
      }

      Log.w("SearchView", "Search suggestions cursor at row " + var2 + " returned exception.", var17);
      return null;
   }

   private Intent createVoiceAppSearchIntent(Intent var1, SearchableInfo var2) {
      ComponentName var10 = var2.getSearchActivity();
      Intent var5 = new Intent("android.intent.action.SEARCH");
      var5.setComponent(var10);
      PendingIntent var9 = PendingIntent.getActivity(this.getContext(), 0, var5, 1107296256);
      Bundle var8 = new Bundle();
      Bundle var13 = this.mAppSearchData;
      if (var13 != null) {
         var8.putParcelable("app_data", var13);
      }

      Intent var11 = new Intent(var1);
      int var3 = 1;
      Resources var6 = this.getResources();
      String var12;
      if (var2.getVoiceLanguageModeId() != 0) {
         var12 = var6.getString(var2.getVoiceLanguageModeId());
      } else {
         var12 = "free_form";
      }

      int var4 = var2.getVoicePromptTextId();
      Object var7 = null;
      String var14;
      if (var4 != 0) {
         var14 = var6.getString(var2.getVoicePromptTextId());
      } else {
         var14 = null;
      }

      String var15;
      if (var2.getVoiceLanguageId() != 0) {
         var15 = var6.getString(var2.getVoiceLanguageId());
      } else {
         var15 = null;
      }

      if (var2.getVoiceMaxResults() != 0) {
         var3 = var2.getVoiceMaxResults();
      }

      var11.putExtra("android.speech.extra.LANGUAGE_MODEL", var12);
      var11.putExtra("android.speech.extra.PROMPT", var14);
      var11.putExtra("android.speech.extra.LANGUAGE", var15);
      var11.putExtra("android.speech.extra.MAX_RESULTS", var3);
      if (var10 == null) {
         var12 = (String)var7;
      } else {
         var12 = var10.flattenToShortString();
      }

      var11.putExtra("calling_package", var12);
      var11.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", var9);
      var11.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", var8);
      return var11;
   }

   private Intent createVoiceWebSearchIntent(Intent var1, SearchableInfo var2) {
      Intent var3 = new Intent(var1);
      ComponentName var4 = var2.getSearchActivity();
      String var5;
      if (var4 == null) {
         var5 = null;
      } else {
         var5 = var4.flattenToShortString();
      }

      var3.putExtra("calling_package", var5);
      return var3;
   }

   private void dismissSuggestions() {
      this.mSearchSrcTextView.dismissDropDown();
   }

   private void getChildBoundsWithinSearchView(View var1, Rect var2) {
      var1.getLocationInWindow(this.mTemp);
      this.getLocationInWindow(this.mTemp2);
      int[] var6 = this.mTemp;
      int var3 = var6[1];
      int[] var5 = this.mTemp2;
      var3 -= var5[1];
      int var4 = var6[0] - var5[0];
      var2.set(var4, var3, var1.getWidth() + var4, var1.getHeight() + var3);
   }

   private CharSequence getDecoratedHint(CharSequence var1) {
      if (this.mIconifiedByDefault && this.mSearchHintIcon != null) {
         int var2 = (int)((double)this.mSearchSrcTextView.getTextSize() * 1.25);
         this.mSearchHintIcon.setBounds(0, 0, var2, var2);
         SpannableStringBuilder var3 = new SpannableStringBuilder("   ");
         var3.setSpan(new ImageSpan(this.mSearchHintIcon), 1, 2, 33);
         var3.append(var1);
         return var3;
      } else {
         return var1;
      }
   }

   private int getPreferredHeight() {
      return this.getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_height);
   }

   private int getPreferredWidth() {
      return this.getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
   }

   private boolean hasVoiceSearch() {
      SearchableInfo var3 = this.mSearchable;
      boolean var2 = false;
      boolean var1 = var2;
      if (var3 != null) {
         var1 = var2;
         if (var3.getVoiceSearchEnabled()) {
            Intent var4 = null;
            if (this.mSearchable.getVoiceSearchLaunchWebSearch()) {
               var4 = this.mVoiceWebSearchIntent;
            } else if (this.mSearchable.getVoiceSearchLaunchRecognizer()) {
               var4 = this.mVoiceAppSearchIntent;
            }

            var1 = var2;
            if (var4 != null) {
               var1 = var2;
               if (this.getContext().getPackageManager().resolveActivity(var4, 65536) != null) {
                  var1 = true;
               }
            }
         }
      }

      return var1;
   }

   static boolean isLandscapeMode(Context var0) {
      boolean var1;
      if (var0.getResources().getConfiguration().orientation == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private boolean isSubmitAreaEnabled() {
      boolean var1;
      if ((this.mSubmitButtonEnabled || this.mVoiceButtonEnabled) && !this.isIconified()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private void launchIntent(Intent var1) {
      if (var1 != null) {
         try {
            this.getContext().startActivity(var1);
         } catch (RuntimeException var3) {
            Log.e("SearchView", "Failed launch activity: " + var1, var3);
         }

      }
   }

   private boolean launchSuggestion(int var1, int var2, String var3) {
      Cursor var4 = this.mSuggestionsAdapter.getCursor();
      if (var4 != null && var4.moveToPosition(var1)) {
         this.launchIntent(this.createIntentFromSuggestion(var4, var2, var3));
         return true;
      } else {
         return false;
      }
   }

   private void postUpdateFocusedState() {
      this.post(this.mUpdateDrawableStateRunnable);
   }

   private void rewriteQueryFromSuggestion(int var1) {
      Editable var2 = this.mSearchSrcTextView.getText();
      Cursor var3 = this.mSuggestionsAdapter.getCursor();
      if (var3 != null) {
         if (var3.moveToPosition(var1)) {
            CharSequence var4 = this.mSuggestionsAdapter.convertToString(var3);
            if (var4 != null) {
               this.setQuery(var4);
            } else {
               this.setQuery(var2);
            }
         } else {
            this.setQuery(var2);
         }

      }
   }

   private void setQuery(CharSequence var1) {
      this.mSearchSrcTextView.setText(var1);
      SearchAutoComplete var3 = this.mSearchSrcTextView;
      int var2;
      if (TextUtils.isEmpty(var1)) {
         var2 = 0;
      } else {
         var2 = var1.length();
      }

      var3.setSelection(var2);
   }

   private void updateCloseButton() {
      boolean var5 = TextUtils.isEmpty(this.mSearchSrcTextView.getText());
      boolean var3 = true;
      boolean var4 = var5 ^ true;
      byte var2 = 0;
      boolean var1 = var3;
      if (!var4) {
         if (this.mIconifiedByDefault && !this.mExpandedInActionView) {
            var1 = var3;
         } else {
            var1 = false;
         }
      }

      ImageView var6 = this.mCloseButton;
      byte var8;
      if (var1) {
         var8 = var2;
      } else {
         var8 = 8;
      }

      var6.setVisibility(var8);
      Drawable var7 = this.mCloseButton.getDrawable();
      if (var7 != null) {
         int[] var9;
         if (var4) {
            var9 = ENABLED_STATE_SET;
         } else {
            var9 = EMPTY_STATE_SET;
         }

         var7.setState(var9);
      }

   }

   private void updateQueryHint() {
      CharSequence var2 = this.getQueryHint();
      SearchAutoComplete var3 = this.mSearchSrcTextView;
      Object var1 = var2;
      if (var2 == null) {
         var1 = "";
      }

      var3.setHint(this.getDecoratedHint((CharSequence)var1));
   }

   private void updateSearchAutoComplete() {
      this.mSearchSrcTextView.setThreshold(this.mSearchable.getSuggestThreshold());
      this.mSearchSrcTextView.setImeOptions(this.mSearchable.getImeOptions());
      int var3 = this.mSearchable.getInputType();
      byte var2 = 1;
      int var1 = var3;
      if ((var3 & 15) == 1) {
         var3 &= -65537;
         var1 = var3;
         if (this.mSearchable.getSuggestAuthority() != null) {
            var1 = var3 | 65536 | 524288;
         }
      }

      this.mSearchSrcTextView.setInputType(var1);
      CursorAdapter var4 = this.mSuggestionsAdapter;
      if (var4 != null) {
         var4.changeCursor((Cursor)null);
      }

      if (this.mSearchable.getSuggestAuthority() != null) {
         SuggestionsAdapter var6 = new SuggestionsAdapter(this.getContext(), this, this.mSearchable, this.mOutsideDrawablesCache);
         this.mSuggestionsAdapter = var6;
         this.mSearchSrcTextView.setAdapter(var6);
         var6 = (SuggestionsAdapter)this.mSuggestionsAdapter;
         byte var5 = var2;
         if (this.mQueryRefinement) {
            var5 = 2;
         }

         var6.setQueryRefinement(var5);
      }

   }

   private void updateSubmitArea() {
      byte var1;
      if (!this.isSubmitAreaEnabled() || this.mGoButton.getVisibility() != 0 && this.mVoiceButton.getVisibility() != 0) {
         var1 = 8;
      } else {
         var1 = 0;
      }

      this.mSubmitArea.setVisibility(var1);
   }

   private void updateSubmitButton(boolean var1) {
      byte var2;
      if (!this.mSubmitButtonEnabled || !this.isSubmitAreaEnabled() || !this.hasFocus() || !var1 && this.mVoiceButtonEnabled) {
         var2 = 8;
      } else {
         var2 = 0;
      }

      this.mGoButton.setVisibility(var2);
   }

   private void updateViewsVisibility(boolean var1) {
      this.mIconified = var1;
      byte var3 = 0;
      byte var2;
      if (var1) {
         var2 = 0;
      } else {
         var2 = 8;
      }

      boolean var4 = TextUtils.isEmpty(this.mSearchSrcTextView.getText()) ^ true;
      this.mSearchButton.setVisibility(var2);
      this.updateSubmitButton(var4);
      View var5 = this.mSearchEditFrame;
      if (var1) {
         var2 = 8;
      } else {
         var2 = 0;
      }

      label20: {
         var5.setVisibility(var2);
         if (this.mCollapsedIcon.getDrawable() != null) {
            var2 = var3;
            if (!this.mIconifiedByDefault) {
               break label20;
            }
         }

         var2 = 8;
      }

      this.mCollapsedIcon.setVisibility(var2);
      this.updateCloseButton();
      this.updateVoiceButton(var4 ^ true);
      this.updateSubmitArea();
   }

   private void updateVoiceButton(boolean var1) {
      boolean var4 = this.mVoiceButtonEnabled;
      byte var3 = 8;
      byte var2 = var3;
      if (var4) {
         var2 = var3;
         if (!this.isIconified()) {
            var2 = var3;
            if (var1) {
               this.mGoButton.setVisibility(8);
               var2 = 0;
            }
         }
      }

      this.mVoiceButton.setVisibility(var2);
   }

   void adjustDropDownSizeAndPosition() {
      if (this.mDropDownAnchor.getWidth() > 1) {
         Resources var7 = this.getContext().getResources();
         int var3 = this.mSearchPlate.getPaddingLeft();
         Rect var8 = new Rect();
         boolean var6 = ViewUtils.isLayoutRtl(this);
         int var1;
         if (this.mIconifiedByDefault) {
            var1 = var7.getDimensionPixelSize(R.dimen.abc_dropdownitem_icon_width) + var7.getDimensionPixelSize(R.dimen.abc_dropdownitem_text_padding_left);
         } else {
            var1 = 0;
         }

         this.mSearchSrcTextView.getDropDownBackground().getPadding(var8);
         int var2;
         if (var6) {
            var2 = -var8.left;
         } else {
            var2 = var3 - (var8.left + var1);
         }

         this.mSearchSrcTextView.setDropDownHorizontalOffset(var2);
         var2 = this.mDropDownAnchor.getWidth();
         int var5 = var8.left;
         int var4 = var8.right;
         this.mSearchSrcTextView.setDropDownWidth(var2 + var5 + var4 + var1 - var3);
      }

   }

   public void clearFocus() {
      this.mClearingFocus = true;
      super.clearFocus();
      this.mSearchSrcTextView.clearFocus();
      this.mSearchSrcTextView.setImeVisibility(false);
      this.mClearingFocus = false;
   }

   void forceSuggestionQuery() {
      if (VERSION.SDK_INT >= 29) {
         this.mSearchSrcTextView.refreshAutoCompleteResults();
      } else {
         PreQAutoCompleteTextViewReflector var1 = PRE_API_29_HIDDEN_METHOD_INVOKER;
         var1.doBeforeTextChanged(this.mSearchSrcTextView);
         var1.doAfterTextChanged(this.mSearchSrcTextView);
      }

   }

   public int getImeOptions() {
      return this.mSearchSrcTextView.getImeOptions();
   }

   public int getInputType() {
      return this.mSearchSrcTextView.getInputType();
   }

   public int getMaxWidth() {
      return this.mMaxWidth;
   }

   public CharSequence getQuery() {
      return this.mSearchSrcTextView.getText();
   }

   public CharSequence getQueryHint() {
      CharSequence var1 = this.mQueryHint;
      if (var1 == null) {
         SearchableInfo var2 = this.mSearchable;
         if (var2 != null && var2.getHintId() != 0) {
            var1 = this.getContext().getText(this.mSearchable.getHintId());
         } else {
            var1 = this.mDefaultQueryHint;
         }
      }

      return var1;
   }

   int getSuggestionCommitIconResId() {
      return this.mSuggestionCommitIconResId;
   }

   int getSuggestionRowLayout() {
      return this.mSuggestionRowLayout;
   }

   public CursorAdapter getSuggestionsAdapter() {
      return this.mSuggestionsAdapter;
   }

   public boolean isIconfiedByDefault() {
      return this.mIconifiedByDefault;
   }

   public boolean isIconified() {
      return this.mIconified;
   }

   public boolean isQueryRefinementEnabled() {
      return this.mQueryRefinement;
   }

   public boolean isSubmitButtonEnabled() {
      return this.mSubmitButtonEnabled;
   }

   void launchQuerySearch(int var1, String var2, String var3) {
      Intent var4 = this.createIntent("android.intent.action.SEARCH", (Uri)null, (String)null, var3, var1, var2);
      this.getContext().startActivity(var4);
   }

   public void onActionViewCollapsed() {
      this.setQuery("", false);
      this.clearFocus();
      this.updateViewsVisibility(true);
      this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions);
      this.mExpandedInActionView = false;
   }

   public void onActionViewExpanded() {
      if (!this.mExpandedInActionView) {
         this.mExpandedInActionView = true;
         int var1 = this.mSearchSrcTextView.getImeOptions();
         this.mCollapsedImeOptions = var1;
         this.mSearchSrcTextView.setImeOptions(var1 | 33554432);
         this.mSearchSrcTextView.setText("");
         this.setIconified(false);
      }
   }

   void onCloseClicked() {
      if (TextUtils.isEmpty(this.mSearchSrcTextView.getText())) {
         if (this.mIconifiedByDefault) {
            OnCloseListener var1 = this.mOnCloseListener;
            if (var1 == null || !var1.onClose()) {
               this.clearFocus();
               this.updateViewsVisibility(true);
            }
         }
      } else {
         this.mSearchSrcTextView.setText("");
         this.mSearchSrcTextView.requestFocus();
         this.mSearchSrcTextView.setImeVisibility(true);
      }

   }

   protected void onDetachedFromWindow() {
      this.removeCallbacks(this.mUpdateDrawableStateRunnable);
      this.post(this.mReleaseCursorRunnable);
      super.onDetachedFromWindow();
   }

   boolean onItemClicked(int var1, int var2, String var3) {
      OnSuggestionListener var4 = this.mOnSuggestionListener;
      if (var4 != null && var4.onSuggestionClick(var1)) {
         return false;
      } else {
         this.launchSuggestion(var1, 0, (String)null);
         this.mSearchSrcTextView.setImeVisibility(false);
         this.dismissSuggestions();
         return true;
      }
   }

   boolean onItemSelected(int var1) {
      OnSuggestionListener var2 = this.mOnSuggestionListener;
      if (var2 != null && var2.onSuggestionSelect(var1)) {
         return false;
      } else {
         this.rewriteQueryFromSuggestion(var1);
         return true;
      }
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      super.onLayout(var1, var2, var3, var4, var5);
      if (var1) {
         this.getChildBoundsWithinSearchView(this.mSearchSrcTextView, this.mSearchSrcTextViewBounds);
         this.mSearchSrtTextViewBoundsExpanded.set(this.mSearchSrcTextViewBounds.left, 0, this.mSearchSrcTextViewBounds.right, var5 - var3);
         UpdatableTouchDelegate var6 = this.mTouchDelegate;
         if (var6 == null) {
            var6 = new UpdatableTouchDelegate(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds, this.mSearchSrcTextView);
            this.mTouchDelegate = var6;
            this.setTouchDelegate(var6);
         } else {
            var6.setBounds(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds);
         }
      }

   }

   protected void onMeasure(int var1, int var2) {
      if (this.isIconified()) {
         super.onMeasure(var1, var2);
      } else {
         int var4 = MeasureSpec.getMode(var1);
         int var3 = MeasureSpec.getSize(var1);
         if (var4 != Integer.MIN_VALUE) {
            if (var4 != 0) {
               if (var4 != 1073741824) {
                  var1 = var3;
               } else {
                  var4 = this.mMaxWidth;
                  var1 = var3;
                  if (var4 > 0) {
                     var1 = Math.min(var4, var3);
                  }
               }
            } else {
               var1 = this.mMaxWidth;
               if (var1 <= 0) {
                  var1 = this.getPreferredWidth();
               }
            }
         } else {
            var1 = this.mMaxWidth;
            if (var1 > 0) {
               var1 = Math.min(var1, var3);
            } else {
               var1 = Math.min(this.getPreferredWidth(), var3);
            }
         }

         var3 = MeasureSpec.getMode(var2);
         var2 = MeasureSpec.getSize(var2);
         if (var3 != Integer.MIN_VALUE) {
            if (var3 == 0) {
               var2 = this.getPreferredHeight();
            }
         } else {
            var2 = Math.min(this.getPreferredHeight(), var2);
         }

         super.onMeasure(MeasureSpec.makeMeasureSpec(var1, 1073741824), MeasureSpec.makeMeasureSpec(var2, 1073741824));
      }
   }

   void onQueryRefine(CharSequence var1) {
      this.setQuery(var1);
   }

   protected void onRestoreInstanceState(Parcelable var1) {
      if (!(var1 instanceof SavedState)) {
         super.onRestoreInstanceState(var1);
      } else {
         SavedState var2 = (SavedState)var1;
         super.onRestoreInstanceState(var2.getSuperState());
         this.updateViewsVisibility(var2.isIconified);
         this.requestLayout();
      }
   }

   protected Parcelable onSaveInstanceState() {
      SavedState var1 = new SavedState(super.onSaveInstanceState());
      var1.isIconified = this.isIconified();
      return var1;
   }

   void onSearchClicked() {
      this.updateViewsVisibility(false);
      this.mSearchSrcTextView.requestFocus();
      this.mSearchSrcTextView.setImeVisibility(true);
      View.OnClickListener var1 = this.mOnSearchClickListener;
      if (var1 != null) {
         var1.onClick(this);
      }

   }

   void onSubmitQuery() {
      Editable var1 = this.mSearchSrcTextView.getText();
      if (var1 != null && TextUtils.getTrimmedLength(var1) > 0) {
         OnQueryTextListener var2 = this.mOnQueryChangeListener;
         if (var2 == null || !var2.onQueryTextSubmit(var1.toString())) {
            if (this.mSearchable != null) {
               this.launchQuerySearch(0, (String)null, var1.toString());
            }

            this.mSearchSrcTextView.setImeVisibility(false);
            this.dismissSuggestions();
         }
      }

   }

   boolean onSuggestionsKey(View var1, int var2, KeyEvent var3) {
      if (this.mSearchable == null) {
         return false;
      } else if (this.mSuggestionsAdapter == null) {
         return false;
      } else {
         if (var3.getAction() == 0 && var3.hasNoModifiers()) {
            if (var2 == 66 || var2 == 84 || var2 == 61) {
               return this.onItemClicked(this.mSearchSrcTextView.getListSelection(), 0, (String)null);
            }

            if (var2 == 21 || var2 == 22) {
               if (var2 == 21) {
                  var2 = 0;
               } else {
                  var2 = this.mSearchSrcTextView.length();
               }

               this.mSearchSrcTextView.setSelection(var2);
               this.mSearchSrcTextView.setListSelection(0);
               this.mSearchSrcTextView.clearListSelection();
               this.mSearchSrcTextView.ensureImeVisible();
               return true;
            }

            if (var2 == 19) {
               this.mSearchSrcTextView.getListSelection();
               return false;
            }
         }

         return false;
      }
   }

   void onTextChanged(CharSequence var1) {
      Editable var3 = this.mSearchSrcTextView.getText();
      this.mUserQuery = var3;
      boolean var2 = TextUtils.isEmpty(var3) ^ true;
      this.updateSubmitButton(var2);
      this.updateVoiceButton(var2 ^ true);
      this.updateCloseButton();
      this.updateSubmitArea();
      if (this.mOnQueryChangeListener != null && !TextUtils.equals(var1, this.mOldQueryText)) {
         this.mOnQueryChangeListener.onQueryTextChange(var1.toString());
      }

      this.mOldQueryText = var1.toString();
   }

   void onTextFocusChanged() {
      this.updateViewsVisibility(this.isIconified());
      this.postUpdateFocusedState();
      if (this.mSearchSrcTextView.hasFocus()) {
         this.forceSuggestionQuery();
      }

   }

   void onVoiceClicked() {
      SearchableInfo var1 = this.mSearchable;
      if (var1 != null) {
         try {
            Intent var3;
            if (var1.getVoiceSearchLaunchWebSearch()) {
               var3 = this.createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, var1);
               this.getContext().startActivity(var3);
            } else if (var1.getVoiceSearchLaunchRecognizer()) {
               var3 = this.createVoiceAppSearchIntent(this.mVoiceAppSearchIntent, var1);
               this.getContext().startActivity(var3);
            }
         } catch (ActivityNotFoundException var2) {
            Log.w("SearchView", "Could not find voice search activity");
         }

      }
   }

   public void onWindowFocusChanged(boolean var1) {
      super.onWindowFocusChanged(var1);
      this.postUpdateFocusedState();
   }

   public boolean requestFocus(int var1, Rect var2) {
      if (this.mClearingFocus) {
         return false;
      } else if (!this.isFocusable()) {
         return false;
      } else if (!this.isIconified()) {
         boolean var3 = this.mSearchSrcTextView.requestFocus(var1, var2);
         if (var3) {
            this.updateViewsVisibility(false);
         }

         return var3;
      } else {
         return super.requestFocus(var1, var2);
      }
   }

   public void setAppSearchData(Bundle var1) {
      this.mAppSearchData = var1;
   }

   public void setIconified(boolean var1) {
      if (var1) {
         this.onCloseClicked();
      } else {
         this.onSearchClicked();
      }

   }

   public void setIconifiedByDefault(boolean var1) {
      if (this.mIconifiedByDefault != var1) {
         this.mIconifiedByDefault = var1;
         this.updateViewsVisibility(var1);
         this.updateQueryHint();
      }
   }

   public void setImeOptions(int var1) {
      this.mSearchSrcTextView.setImeOptions(var1);
   }

   public void setInputType(int var1) {
      this.mSearchSrcTextView.setInputType(var1);
   }

   public void setMaxWidth(int var1) {
      this.mMaxWidth = var1;
      this.requestLayout();
   }

   public void setOnCloseListener(OnCloseListener var1) {
      this.mOnCloseListener = var1;
   }

   public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener var1) {
      this.mOnQueryTextFocusChangeListener = var1;
   }

   public void setOnQueryTextListener(OnQueryTextListener var1) {
      this.mOnQueryChangeListener = var1;
   }

   public void setOnSearchClickListener(View.OnClickListener var1) {
      this.mOnSearchClickListener = var1;
   }

   public void setOnSuggestionListener(OnSuggestionListener var1) {
      this.mOnSuggestionListener = var1;
   }

   public void setQuery(CharSequence var1, boolean var2) {
      this.mSearchSrcTextView.setText(var1);
      if (var1 != null) {
         SearchAutoComplete var3 = this.mSearchSrcTextView;
         var3.setSelection(var3.length());
         this.mUserQuery = var1;
      }

      if (var2 && !TextUtils.isEmpty(var1)) {
         this.onSubmitQuery();
      }

   }

   public void setQueryHint(CharSequence var1) {
      this.mQueryHint = var1;
      this.updateQueryHint();
   }

   public void setQueryRefinementEnabled(boolean var1) {
      this.mQueryRefinement = var1;
      CursorAdapter var3 = this.mSuggestionsAdapter;
      if (var3 instanceof SuggestionsAdapter) {
         SuggestionsAdapter var4 = (SuggestionsAdapter)var3;
         byte var2;
         if (var1) {
            var2 = 2;
         } else {
            var2 = 1;
         }

         var4.setQueryRefinement(var2);
      }

   }

   public void setSearchableInfo(SearchableInfo var1) {
      this.mSearchable = var1;
      if (var1 != null) {
         this.updateSearchAutoComplete();
         this.updateQueryHint();
      }

      boolean var2 = this.hasVoiceSearch();
      this.mVoiceButtonEnabled = var2;
      if (var2) {
         this.mSearchSrcTextView.setPrivateImeOptions("nm");
      }

      this.updateViewsVisibility(this.isIconified());
   }

   public void setSubmitButtonEnabled(boolean var1) {
      this.mSubmitButtonEnabled = var1;
      this.updateViewsVisibility(this.isIconified());
   }

   public void setSuggestionsAdapter(CursorAdapter var1) {
      this.mSuggestionsAdapter = var1;
      this.mSearchSrcTextView.setAdapter(var1);
   }

   void updateFocusedState() {
      int[] var1;
      if (this.mSearchSrcTextView.hasFocus()) {
         var1 = FOCUSED_STATE_SET;
      } else {
         var1 = EMPTY_STATE_SET;
      }

      Drawable var2 = this.mSearchPlate.getBackground();
      if (var2 != null) {
         var2.setState(var1);
      }

      var2 = this.mSubmitArea.getBackground();
      if (var2 != null) {
         var2.setState(var1);
      }

      this.invalidate();
   }

   public interface OnCloseListener {
      boolean onClose();
   }

   public interface OnQueryTextListener {
      boolean onQueryTextChange(String var1);

      boolean onQueryTextSubmit(String var1);
   }

   public interface OnSuggestionListener {
      boolean onSuggestionClick(int var1);

      boolean onSuggestionSelect(int var1);
   }

   private static class PreQAutoCompleteTextViewReflector {
      private Method mDoAfterTextChanged = null;
      private Method mDoBeforeTextChanged = null;
      private Method mEnsureImeVisible = null;

      PreQAutoCompleteTextViewReflector() {
         preApi29Check();

         Method var1;
         try {
            var1 = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged");
            this.mDoBeforeTextChanged = var1;
            var1.setAccessible(true);
         } catch (NoSuchMethodException var4) {
         }

         try {
            var1 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged");
            this.mDoAfterTextChanged = var1;
            var1.setAccessible(true);
         } catch (NoSuchMethodException var3) {
         }

         try {
            var1 = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
            this.mEnsureImeVisible = var1;
            var1.setAccessible(true);
         } catch (NoSuchMethodException var2) {
         }

      }

      private static void preApi29Check() {
         if (VERSION.SDK_INT >= 29) {
            throw new UnsupportedClassVersionError("This function can only be used for API Level < 29.");
         }
      }

      void doAfterTextChanged(AutoCompleteTextView var1) {
         preApi29Check();
         Method var2 = this.mDoAfterTextChanged;
         if (var2 != null) {
            try {
               var2.invoke(var1);
            } catch (Exception var3) {
            }
         }

      }

      void doBeforeTextChanged(AutoCompleteTextView var1) {
         preApi29Check();
         Method var2 = this.mDoBeforeTextChanged;
         if (var2 != null) {
            try {
               var2.invoke(var1);
            } catch (Exception var3) {
            }
         }

      }

      void ensureImeVisible(AutoCompleteTextView var1) {
         preApi29Check();
         Method var2 = this.mEnsureImeVisible;
         if (var2 != null) {
            try {
               var2.invoke(var1, true);
            } catch (Exception var3) {
            }
         }

      }
   }

   static class SavedState extends AbsSavedState {
      public static final Parcelable.Creator CREATOR = new Parcelable.ClassLoaderCreator() {
         public SavedState createFromParcel(Parcel var1) {
            return new SavedState(var1, (ClassLoader)null);
         }

         public SavedState createFromParcel(Parcel var1, ClassLoader var2) {
            return new SavedState(var1, var2);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      };
      boolean isIconified;

      public SavedState(Parcel var1, ClassLoader var2) {
         super(var1, var2);
         this.isIconified = (Boolean)var1.readValue((ClassLoader)null);
      }

      SavedState(Parcelable var1) {
         super(var1);
      }

      public String toString() {
         return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.isIconified + "}";
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeValue(this.isIconified);
      }
   }

   public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
      private boolean mHasPendingShowSoftInputRequest;
      final Runnable mRunShowSoftInputIfNecessary;
      private SearchView mSearchView;
      private int mThreshold;

      public SearchAutoComplete(Context var1) {
         this(var1, (AttributeSet)null);
      }

      public SearchAutoComplete(Context var1, AttributeSet var2) {
         this(var1, var2, R.attr.autoCompleteTextViewStyle);
      }

      public SearchAutoComplete(Context var1, AttributeSet var2, int var3) {
         super(var1, var2, var3);
         this.mRunShowSoftInputIfNecessary = new Runnable(this) {
            final SearchAutoComplete this$0;

            {
               this.this$0 = var1;
            }

            public void run() {
               this.this$0.showSoftInputIfNecessary();
            }
         };
         this.mThreshold = this.getThreshold();
      }

      private int getSearchViewTextMinWidthDp() {
         Configuration var3 = this.getResources().getConfiguration();
         int var1 = var3.screenWidthDp;
         int var2 = var3.screenHeightDp;
         if (var1 >= 960 && var2 >= 720 && var3.orientation == 2) {
            return 256;
         } else {
            return var1 >= 600 || var1 >= 640 && var2 >= 480 ? 192 : 160;
         }
      }

      public boolean enoughToFilter() {
         boolean var1;
         if (this.mThreshold > 0 && !super.enoughToFilter()) {
            var1 = false;
         } else {
            var1 = true;
         }

         return var1;
      }

      void ensureImeVisible() {
         if (VERSION.SDK_INT >= 29) {
            this.setInputMethodMode(1);
            if (this.enoughToFilter()) {
               this.showDropDown();
            }
         } else {
            SearchView.PRE_API_29_HIDDEN_METHOD_INVOKER.ensureImeVisible(this);
         }

      }

      boolean isEmpty() {
         boolean var1;
         if (TextUtils.getTrimmedLength(this.getText()) == 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public InputConnection onCreateInputConnection(EditorInfo var1) {
         InputConnection var2 = super.onCreateInputConnection(var1);
         if (this.mHasPendingShowSoftInputRequest) {
            this.removeCallbacks(this.mRunShowSoftInputIfNecessary);
            this.post(this.mRunShowSoftInputIfNecessary);
         }

         return var2;
      }

      protected void onFinishInflate() {
         super.onFinishInflate();
         DisplayMetrics var1 = this.getResources().getDisplayMetrics();
         this.setMinWidth((int)TypedValue.applyDimension(1, (float)this.getSearchViewTextMinWidthDp(), var1));
      }

      protected void onFocusChanged(boolean var1, int var2, Rect var3) {
         super.onFocusChanged(var1, var2, var3);
         this.mSearchView.onTextFocusChanged();
      }

      public boolean onKeyPreIme(int var1, KeyEvent var2) {
         if (var1 == 4) {
            KeyEvent.DispatcherState var3;
            if (var2.getAction() == 0 && var2.getRepeatCount() == 0) {
               var3 = this.getKeyDispatcherState();
               if (var3 != null) {
                  var3.startTracking(var2, this);
               }

               return true;
            }

            if (var2.getAction() == 1) {
               var3 = this.getKeyDispatcherState();
               if (var3 != null) {
                  var3.handleUpEvent(var2);
               }

               if (var2.isTracking() && !var2.isCanceled()) {
                  this.mSearchView.clearFocus();
                  this.setImeVisibility(false);
                  return true;
               }
            }
         }

         return super.onKeyPreIme(var1, var2);
      }

      public void onWindowFocusChanged(boolean var1) {
         super.onWindowFocusChanged(var1);
         if (var1 && this.mSearchView.hasFocus() && this.getVisibility() == 0) {
            this.mHasPendingShowSoftInputRequest = true;
            if (SearchView.isLandscapeMode(this.getContext())) {
               this.ensureImeVisible();
            }
         }

      }

      public void performCompletion() {
      }

      protected void replaceText(CharSequence var1) {
      }

      void setImeVisibility(boolean var1) {
         InputMethodManager var2 = (InputMethodManager)this.getContext().getSystemService("input_method");
         if (!var1) {
            this.mHasPendingShowSoftInputRequest = false;
            this.removeCallbacks(this.mRunShowSoftInputIfNecessary);
            var2.hideSoftInputFromWindow(this.getWindowToken(), 0);
         } else if (var2.isActive(this)) {
            this.mHasPendingShowSoftInputRequest = false;
            this.removeCallbacks(this.mRunShowSoftInputIfNecessary);
            var2.showSoftInput(this, 0);
         } else {
            this.mHasPendingShowSoftInputRequest = true;
         }
      }

      void setSearchView(SearchView var1) {
         this.mSearchView = var1;
      }

      public void setThreshold(int var1) {
         super.setThreshold(var1);
         this.mThreshold = var1;
      }

      void showSoftInputIfNecessary() {
         if (this.mHasPendingShowSoftInputRequest) {
            ((InputMethodManager)this.getContext().getSystemService("input_method")).showSoftInput(this, 0);
            this.mHasPendingShowSoftInputRequest = false;
         }

      }
   }

   private static class UpdatableTouchDelegate extends TouchDelegate {
      private final Rect mActualBounds;
      private boolean mDelegateTargeted;
      private final View mDelegateView;
      private final int mSlop;
      private final Rect mSlopBounds;
      private final Rect mTargetBounds;

      public UpdatableTouchDelegate(Rect var1, Rect var2, View var3) {
         super(var1, var3);
         this.mSlop = ViewConfiguration.get(var3.getContext()).getScaledTouchSlop();
         this.mTargetBounds = new Rect();
         this.mSlopBounds = new Rect();
         this.mActualBounds = new Rect();
         this.setBounds(var1, var2);
         this.mDelegateView = var3;
      }

      public boolean onTouchEvent(MotionEvent var1) {
         int var3;
         int var4;
         boolean var5;
         boolean var6;
         boolean var7;
         boolean var8;
         label44: {
            label47: {
               var3 = (int)var1.getX();
               var4 = (int)var1.getY();
               int var2 = var1.getAction();
               var5 = true;
               var7 = false;
               if (var2 != 0) {
                  if (var2 == 1 || var2 == 2) {
                     var6 = this.mDelegateTargeted;
                     var5 = var6;
                     if (var6) {
                        var5 = var6;
                        if (!this.mSlopBounds.contains(var3, var4)) {
                           var5 = var6;
                           var8 = false;
                           break label44;
                        }
                     }
                     break label47;
                  }

                  if (var2 == 3) {
                     var5 = this.mDelegateTargeted;
                     this.mDelegateTargeted = false;
                     break label47;
                  }
               } else if (this.mTargetBounds.contains(var3, var4)) {
                  this.mDelegateTargeted = true;
                  var8 = true;
                  break label44;
               }

               var8 = true;
               var5 = false;
               break label44;
            }

            var8 = true;
         }

         var6 = var7;
         if (var5) {
            if (var8 && !this.mActualBounds.contains(var3, var4)) {
               var1.setLocation((float)(this.mDelegateView.getWidth() / 2), (float)(this.mDelegateView.getHeight() / 2));
            } else {
               var1.setLocation((float)(var3 - this.mActualBounds.left), (float)(var4 - this.mActualBounds.top));
            }

            var6 = this.mDelegateView.dispatchTouchEvent(var1);
         }

         return var6;
      }

      public void setBounds(Rect var1, Rect var2) {
         this.mTargetBounds.set(var1);
         this.mSlopBounds.set(var1);
         var1 = this.mSlopBounds;
         int var3 = this.mSlop;
         var1.inset(-var3, -var3);
         this.mActualBounds.set(var2);
      }
   }
}
