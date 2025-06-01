package com.android.ex.editstyledtext;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.Html;
import android.text.Layout;
import android.text.NoCopySpan;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.Layout.Alignment;
import android.text.method.ArrowKeyMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.ParagraphStyle;
import android.text.style.QuoteSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class EditStyledText extends EditText {
   private static final boolean DBG = true;
   public static final int DEFAULT_FOREGROUND_COLOR = -16777216;
   public static final int DEFAULT_TRANSPARENT_COLOR = 16777215;
   public static final int HINT_MSG_BIG_SIZE_ERROR = 5;
   public static final int HINT_MSG_COPY_BUF_BLANK = 1;
   public static final int HINT_MSG_END_COMPOSE = 7;
   public static final int HINT_MSG_END_PREVIEW = 6;
   public static final int HINT_MSG_NULL = 0;
   public static final int HINT_MSG_PUSH_COMPETE = 4;
   public static final int HINT_MSG_SELECT_END = 3;
   public static final int HINT_MSG_SELECT_START = 2;
   private static final int ID_CLEARSTYLES = 16776962;
   private static final int ID_COPY = 16908321;
   private static final int ID_CUT = 16908320;
   private static final int ID_HIDEEDIT = 16776964;
   private static final int ID_HORIZONTALLINE = 16776961;
   private static final int ID_PASTE = 16908322;
   private static final int ID_SELECT_ALL = 16908319;
   private static final int ID_SHOWEDIT = 16776963;
   private static final int ID_START_SELECTING_TEXT = 16908328;
   private static final int ID_STOP_SELECTING_TEXT = 16908329;
   public static final char IMAGECHAR = '￼';
   private static final int MAXIMAGEWIDTHDIP = 300;
   public static final int MODE_ALIGN = 6;
   public static final int MODE_BGCOLOR = 16;
   public static final int MODE_CANCEL = 18;
   public static final int MODE_CLEARSTYLES = 14;
   public static final int MODE_COLOR = 4;
   public static final int MODE_COPY = 1;
   public static final int MODE_CUT = 7;
   public static final int MODE_END_EDIT = 21;
   public static final int MODE_HORIZONTALLINE = 12;
   public static final int MODE_IMAGE = 15;
   public static final int MODE_MARQUEE = 10;
   public static final int MODE_NOTHING = 0;
   public static final int MODE_PASTE = 2;
   public static final int MODE_PREVIEW = 17;
   public static final int MODE_RESET = 22;
   public static final int MODE_SELECT = 5;
   public static final int MODE_SELECTALL = 11;
   public static final int MODE_SHOW_MENU = 23;
   public static final int MODE_SIZE = 3;
   public static final int MODE_START_EDIT = 20;
   public static final int MODE_STOP_SELECT = 13;
   public static final int MODE_SWING = 9;
   public static final int MODE_TELOP = 8;
   public static final int MODE_TEXTVIEWFUNCTION = 19;
   private static final int PRESSED = 16777233;
   private static final NoCopySpan.Concrete SELECTING = new NoCopySpan.Concrete();
   public static final int STATE_SELECTED = 2;
   public static final int STATE_SELECT_FIX = 3;
   public static final int STATE_SELECT_OFF = 0;
   public static final int STATE_SELECT_ON = 1;
   private static CharSequence STR_CLEARSTYLES;
   private static CharSequence STR_HORIZONTALLINE;
   private static CharSequence STR_PASTE;
   private static final String TAG = "EditStyledText";
   public static final char ZEROWIDTHCHAR = '\u2060';
   private StyledTextConverter mConverter;
   private Drawable mDefaultBackground;
   private StyledTextDialog mDialog;
   private ArrayList mESTNotifiers;
   private InputConnection mInputConnection;
   private EditorManager mManager;
   private float mPaddingScale = 0.0F;

   public EditStyledText(Context var1) {
      super(var1);
      this.init();
   }

   public EditStyledText(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.init();
   }

   public EditStyledText(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.init();
   }

   private void cancelViewManagers() {
      ArrayList var1 = this.mESTNotifiers;
      if (var1 != null) {
         Iterator var2 = var1.iterator();

         while(var2.hasNext()) {
            ((EditStyledTextNotifier)var2.next()).cancelViewManager();
         }
      }

   }

   private int dipToPx(int var1) {
      if (this.mPaddingScale <= 0.0F) {
         this.mPaddingScale = this.getContext().getResources().getDisplayMetrics().density;
      }

      return (int)((double)((float)var1 * this.getPaddingScale()) + 0.5);
   }

   private void finishComposingText() {
      if (this.mInputConnection != null && !this.mManager.mTextIsFinishedFlag) {
         this.mInputConnection.finishComposingText();
         this.mManager.mTextIsFinishedFlag = true;
      }

   }

   private int getMaxImageWidthDip() {
      return 300;
   }

   private int getMaxImageWidthPx() {
      return this.dipToPx(300);
   }

   private float getPaddingScale() {
      if (this.mPaddingScale <= 0.0F) {
         this.mPaddingScale = this.getContext().getResources().getDisplayMetrics().density;
      }

      return this.mPaddingScale;
   }

   private void init() {
      this.mConverter = new StyledTextConverter(this, this, new StyledTextHtmlStandard(this));
      this.mDialog = new StyledTextDialog(this);
      this.mManager = new EditorManager(this, this, this.mDialog);
      this.setMovementMethod(new StyledTextArrowKeyMethod(this.mManager));
      this.mDefaultBackground = this.getBackground();
      this.requestFocus();
   }

   private void notifyStateChanged(int var1, int var2) {
      ArrayList var3 = this.mESTNotifiers;
      if (var3 != null) {
         Iterator var4 = var3.iterator();

         while(var4.hasNext()) {
            ((EditStyledTextNotifier)var4.next()).onStateChanged(var1, var2);
         }
      }

   }

   private void onRefreshStyles() {
      this.mManager.onRefreshStyles();
   }

   private void onRefreshZeoWidthChar() {
      this.mManager.onRefreshZeoWidthChar();
   }

   private void sendHintMessage(int var1) {
      ArrayList var2 = this.mESTNotifiers;
      if (var2 != null) {
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            ((EditStyledTextNotifier)var3.next()).sendHintMsg(var1);
         }
      }

   }

   private void sendOnTouchEvent(MotionEvent var1) {
      ArrayList var2 = this.mESTNotifiers;
      if (var2 != null) {
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            ((EditStyledTextNotifier)var3.next()).sendOnTouchEvent(var1);
         }
      }

   }

   private void showInsertImageSelectAlertDialog() {
      ArrayList var1 = this.mESTNotifiers;
      if (var1 != null) {
         Iterator var2 = var1.iterator();

         while(var2.hasNext() && !((EditStyledTextNotifier)var2.next()).showInsertImageSelectAlertDialog()) {
         }
      }

   }

   private void showMenuAlertDialog() {
      ArrayList var1 = this.mESTNotifiers;
      if (var1 != null) {
         Iterator var2 = var1.iterator();

         while(var2.hasNext() && !((EditStyledTextNotifier)var2.next()).showMenuAlertDialog()) {
         }
      }

   }

   private void showPreview() {
      ArrayList var1 = this.mESTNotifiers;
      if (var1 != null) {
         Iterator var2 = var1.iterator();

         while(var2.hasNext() && !((EditStyledTextNotifier)var2.next()).showPreview()) {
         }
      }

   }

   private static void startSelecting(View var0, Spannable var1) {
      var1.setSpan(SELECTING, 0, 0, 16777233);
   }

   private static void stopSelecting(View var0, Spannable var1) {
      var1.removeSpan(SELECTING);
   }

   public void addAction(int var1, EditModeActions.EditModeActionBase var2) {
      this.mManager.addAction(var1, var2);
   }

   public void addEditStyledTextListener(EditStyledTextNotifier var1) {
      if (this.mESTNotifiers == null) {
         this.mESTNotifiers = new ArrayList();
      }

      this.mESTNotifiers.add(var1);
   }

   public void addInputExtra(boolean var1, String var2) {
      Bundle var3 = super.getInputExtras(var1);
      if (var3 != null) {
         var3.putBoolean(var2, true);
      }

   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      EditorManager var1 = this.mManager;
      if (var1 != null) {
         var1.onRefreshStyles();
      }

   }

   public int getBackgroundColor() {
      return this.mManager.getBackgroundColor();
   }

   public int getEditMode() {
      return this.mManager.getEditMode();
   }

   public EditorManager getEditStyledTextManager() {
      return this.mManager;
   }

   public int getForegroundColor(int var1) {
      if (var1 >= 0 && var1 <= this.getText().length()) {
         ForegroundColorSpan[] var2 = (ForegroundColorSpan[])this.getText().getSpans(var1, var1, ForegroundColorSpan.class);
         return var2.length > 0 ? var2[0].getForegroundColor() : -16777216;
      } else {
         return -16777216;
      }
   }

   public String getHtml() {
      return this.mConverter.getHtml(true);
   }

   public String getHtml(ArrayList var1, boolean var2) {
      this.mConverter.getUriArray(var1, this.getText());
      return this.mConverter.getHtml(var2);
   }

   public String getHtml(boolean var1) {
      return this.mConverter.getHtml(var1);
   }

   public String getPreviewHtml() {
      return this.mConverter.getPreviewHtml();
   }

   public int getSelectState() {
      return this.mManager.getSelectState();
   }

   public boolean isButtonsFocused() {
      boolean var2 = false;
      boolean var1 = false;
      ArrayList var3 = this.mESTNotifiers;
      if (var3 != null) {
         Iterator var4 = var3.iterator();

         while(true) {
            var2 = var1;
            if (!var4.hasNext()) {
               break;
            }

            var1 |= ((EditStyledTextNotifier)var4.next()).isButtonsFocused();
         }
      }

      return var2;
   }

   public boolean isEditting() {
      return this.mManager.isEditting();
   }

   public boolean isSoftKeyBlocked() {
      return this.mManager.isSoftKeyBlocked();
   }

   public boolean isStyledText() {
      return this.mManager.isStyledText();
   }

   public void onBlockSoftKey() {
      this.mManager.blockSoftKey();
   }

   public void onCancelViewManagers() {
      this.mManager.onCancelViewManagers();
   }

   public void onClearStyles() {
      this.mManager.onClearStyles();
   }

   protected void onCreateContextMenu(ContextMenu var1) {
      super.onCreateContextMenu(var1);
      MenuHandler var2 = new MenuHandler(this);
      CharSequence var3 = STR_HORIZONTALLINE;
      if (var3 != null) {
         var1.add(0, 16776961, 0, var3).setOnMenuItemClickListener(var2);
      }

      if (this.isStyledText()) {
         var3 = STR_CLEARSTYLES;
         if (var3 != null) {
            var1.add(0, 16776962, 0, var3).setOnMenuItemClickListener(var2);
         }
      }

      if (this.mManager.canPaste()) {
         var1.add(0, 16908322, 0, STR_PASTE).setOnMenuItemClickListener(var2).setAlphabeticShortcut('v');
      }

   }

   public InputConnection onCreateInputConnection(EditorInfo var1) {
      this.mInputConnection = new StyledTextInputConnection(super.onCreateInputConnection(var1), this);
      return this.mInputConnection;
   }

   public void onEndEdit() {
      this.mManager.onAction(21);
   }

   public void onFixSelectedItem() {
      this.mManager.onFixSelectedItem();
   }

   protected void onFocusChanged(boolean var1, int var2, Rect var3) {
      super.onFocusChanged(var1, var2, var3);
      if (var1) {
         this.onStartEdit();
      } else if (!this.isButtonsFocused()) {
         this.onEndEdit();
      }

   }

   public void onInsertHorizontalLine() {
      this.mManager.onAction(12);
   }

   public void onInsertImage() {
      this.mManager.onAction(15);
   }

   public void onInsertImage(int var1) {
      this.mManager.onInsertImage(var1);
   }

   public void onInsertImage(Uri var1) {
      this.mManager.onInsertImage(var1);
   }

   public void onResetEdit() {
      this.mManager.onAction(22);
   }

   public void onRestoreInstanceState(Parcelable var1) {
      if (!(var1 instanceof SavedStyledTextState)) {
         super.onRestoreInstanceState(var1);
      } else {
         SavedStyledTextState var2 = (SavedStyledTextState)var1;
         super.onRestoreInstanceState(var2.getSuperState());
         this.setBackgroundColor(var2.mBackgroundColor);
      }
   }

   public Parcelable onSaveInstanceState() {
      SavedStyledTextState var1 = new SavedStyledTextState(super.onSaveInstanceState());
      var1.mBackgroundColor = this.mManager.getBackgroundColor();
      return var1;
   }

   public void onStartAction(int var1, boolean var2) {
      this.mManager.onAction(var1, var2);
   }

   public void onStartAlign() {
      this.mManager.onAction(6);
   }

   public void onStartBackgroundColor() {
      this.mManager.onAction(16);
   }

   public void onStartColor() {
      this.mManager.onAction(4);
   }

   public void onStartCopy() {
      this.mManager.onAction(1);
   }

   public void onStartCut() {
      this.mManager.onAction(7);
   }

   public void onStartEdit() {
      this.mManager.onAction(20);
   }

   public void onStartMarquee() {
      this.mManager.onAction(10);
   }

   public void onStartPaste() {
      this.mManager.onAction(2);
   }

   public void onStartSelect() {
      this.mManager.onStartSelect(true);
   }

   public void onStartSelectAll() {
      this.mManager.onStartSelectAll(true);
   }

   public void onStartShowMenuAlertDialog() {
      this.mManager.onStartShowMenuAlertDialog();
   }

   public void onStartShowPreview() {
      this.mManager.onAction(17);
   }

   public void onStartSize() {
      this.mManager.onAction(3);
   }

   public void onStartSwing() {
      this.mManager.onAction(9);
   }

   public void onStartTelop() {
      this.mManager.onAction(8);
   }

   protected void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
      EditorManager var5 = this.mManager;
      if (var5 != null) {
         var5.updateSpanNextToCursor(this.getText(), var2, var3, var4);
         this.mManager.updateSpanPreviousFromCursor(this.getText(), var2, var3, var4);
         if (var4 > var3) {
            this.mManager.setTextComposingMask(var2, var2 + var4);
         } else if (var3 < var4) {
            this.mManager.unsetTextComposingMask();
         }

         if (this.mManager.isWaitInput()) {
            if (var4 > var3) {
               this.mManager.onCursorMoved();
               this.onFixSelectedItem();
            } else if (var4 < var3) {
               this.mManager.onAction(22);
            }
         }
      }

      super.onTextChanged(var1, var2, var3, var4);
   }

   public boolean onTextContextMenuItem(int var1) {
      boolean var2;
      if (this.getSelectionStart() != this.getSelectionEnd()) {
         var2 = true;
      } else {
         var2 = false;
      }

      switch (var1) {
         case 16776961:
            this.onInsertHorizontalLine();
            return true;
         case 16776962:
            this.onClearStyles();
            return true;
         case 16776963:
            this.onStartEdit();
            return true;
         case 16776964:
            this.onEndEdit();
            return true;
         default:
            switch (var1) {
               case 16908319:
                  this.onStartSelectAll();
                  return true;
               case 16908320:
                  if (var2) {
                     this.onStartCut();
                  } else {
                     this.mManager.onStartSelectAll(false);
                     this.onStartCut();
                  }

                  return true;
               case 16908321:
                  if (var2) {
                     this.onStartCopy();
                  } else {
                     this.mManager.onStartSelectAll(false);
                     this.onStartCopy();
                  }

                  return true;
               case 16908322:
                  this.onStartPaste();
                  return true;
               default:
                  switch (var1) {
                     case 16908328:
                        this.onStartSelect();
                        this.mManager.blockSoftKey();
                        break;
                     case 16908329:
                        this.onFixSelectedItem();
                  }

                  return super.onTextContextMenuItem(var1);
            }
      }
   }

   public boolean onTouchEvent(MotionEvent var1) {
      boolean var4;
      if (var1.getAction() == 1) {
         this.cancelLongPress();
         boolean var5 = this.isEditting();
         if (!var5) {
            this.onStartEdit();
         }

         int var3 = Selection.getSelectionStart(this.getText());
         int var2 = Selection.getSelectionEnd(this.getText());
         var4 = super.onTouchEvent(var1);
         if (this.isFocused() && this.getSelectState() == 0) {
            if (var5) {
               this.mManager.showSoftKey(Selection.getSelectionStart(this.getText()), Selection.getSelectionEnd(this.getText()));
            } else {
               this.mManager.showSoftKey(var3, var2);
            }
         }

         this.mManager.onCursorMoved();
         this.mManager.unsetTextComposingMask();
      } else {
         var4 = super.onTouchEvent(var1);
      }

      this.sendOnTouchEvent(var1);
      return var4;
   }

   public void onUnblockSoftKey() {
      this.mManager.unblockSoftKey();
   }

   public void removeEditStyledTextListener(EditStyledTextNotifier var1) {
      ArrayList var3 = this.mESTNotifiers;
      if (var3 != null) {
         int var2 = var3.indexOf(var1);
         if (var2 > 0) {
            this.mESTNotifiers.remove(var2);
         }
      }

   }

   public void setAlignAlertParams(CharSequence var1, CharSequence[] var2) {
      this.mDialog.setAlignAlertParams(var1, var2);
   }

   public void setAlignment(Alignment var1) {
      this.mManager.setAlignment(var1);
   }

   public void setBackgroundColor(int var1) {
      if (var1 != 16777215) {
         super.setBackgroundColor(var1);
      } else {
         this.setBackgroundDrawable(this.mDefaultBackground);
      }

      this.mManager.setBackgroundColor(var1);
      this.onRefreshStyles();
   }

   public void setBuilder(AlertDialog.Builder var1) {
      this.mDialog.setBuilder(var1);
   }

   public void setColorAlertParams(CharSequence var1, CharSequence[] var2, CharSequence[] var3, CharSequence var4) {
      this.mDialog.setColorAlertParams(var1, var2, var3, var4);
   }

   public void setContextMenuStrings(CharSequence var1, CharSequence var2, CharSequence var3) {
      STR_HORIZONTALLINE = var1;
      STR_CLEARSTYLES = var2;
      STR_PASTE = var3;
   }

   public void setHtml(String var1) {
      this.mConverter.SetHtml(var1);
   }

   public void setItemColor(int var1) {
      this.mManager.setItemColor(var1, true);
   }

   public void setItemSize(int var1) {
      this.mManager.setItemSize(var1, true);
   }

   public void setMarquee(int var1) {
      this.mManager.setMarquee(var1);
   }

   public void setMarqueeAlertParams(CharSequence var1, CharSequence[] var2) {
      this.mDialog.setMarqueeAlertParams(var1, var2);
   }

   public void setSizeAlertParams(CharSequence var1, CharSequence[] var2, CharSequence[] var3, CharSequence[] var4) {
      this.mDialog.setSizeAlertParams(var1, var2, var3, var4);
   }

   public void setStyledTextHtmlConverter(StyledTextHtmlConverter var1) {
      this.mConverter.setStyledTextHtmlConverter(var1);
   }

   public static class ColorPaletteDrawable extends ShapeDrawable {
      private Rect mRect;

      public ColorPaletteDrawable(int var1, int var2, int var3, int var4) {
         super(new RectShape());
         this.mRect = new Rect(var4, var4, var2 - var4, var3 - var4);
         this.getPaint().setColor(var1);
      }

      public void draw(Canvas var1) {
         var1.drawRect(this.mRect, this.getPaint());
      }
   }

   public class EditModeActions {
      private static final boolean DBG = true;
      private static final String TAG = "EditModeActions";
      private HashMap mActionMap;
      private AlignAction mAlignAction;
      private BackgroundColorAction mBackgroundColorAction;
      private CancelAction mCancelEditAction;
      private ClearStylesAction mClearStylesAction;
      private ColorAction mColorAction;
      private CopyAction mCopyAction;
      private CutAction mCutAction;
      private StyledTextDialog mDialog;
      private EditStyledText mEST;
      private EndEditAction mEndEditAction;
      private HorizontalLineAction mHorizontalLineAction;
      private ImageAction mImageAction;
      private EditorManager mManager;
      private MarqueeDialogAction mMarqueeDialogAction;
      private int mMode;
      private NothingAction mNothingAction;
      private PasteAction mPasteAction;
      private PreviewAction mPreviewAction;
      private ResetAction mResetAction;
      private SelectAction mSelectAction;
      private SelectAllAction mSelectAllAction;
      private ShowMenuAction mShowMenuAction;
      private SizeAction mSizeAction;
      private StartEditAction mStartEditAction;
      private StopSelectionAction mStopSelectionAction;
      private SwingAction mSwingAction;
      private TelopAction mTelopAction;
      private TextViewAction mTextViewAction;
      final EditStyledText this$0;

      EditModeActions(EditStyledText var1, EditStyledText var2, EditorManager var3, StyledTextDialog var4) {
         this.this$0 = var1;
         this.mMode = 0;
         this.mActionMap = new HashMap();
         this.mNothingAction = new NothingAction(this);
         this.mCopyAction = new CopyAction(this);
         this.mPasteAction = new PasteAction(this);
         this.mSelectAction = new SelectAction(this);
         this.mCutAction = new CutAction(this);
         this.mSelectAllAction = new SelectAllAction(this);
         this.mHorizontalLineAction = new HorizontalLineAction(this);
         this.mStopSelectionAction = new StopSelectionAction(this);
         this.mClearStylesAction = new ClearStylesAction(this);
         this.mImageAction = new ImageAction(this);
         this.mBackgroundColorAction = new BackgroundColorAction(this);
         this.mPreviewAction = new PreviewAction(this);
         this.mCancelEditAction = new CancelAction(this);
         this.mTextViewAction = new TextViewAction(this);
         this.mStartEditAction = new StartEditAction(this);
         this.mEndEditAction = new EndEditAction(this);
         this.mResetAction = new ResetAction(this);
         this.mShowMenuAction = new ShowMenuAction(this);
         this.mAlignAction = new AlignAction(this);
         this.mTelopAction = new TelopAction(this);
         this.mSwingAction = new SwingAction(this);
         this.mMarqueeDialogAction = new MarqueeDialogAction(this);
         this.mColorAction = new ColorAction(this);
         this.mSizeAction = new SizeAction(this);
         this.mEST = var2;
         this.mManager = var3;
         this.mDialog = var4;
         this.mActionMap.put(0, this.mNothingAction);
         this.mActionMap.put(1, this.mCopyAction);
         this.mActionMap.put(2, this.mPasteAction);
         this.mActionMap.put(5, this.mSelectAction);
         this.mActionMap.put(7, this.mCutAction);
         this.mActionMap.put(11, this.mSelectAllAction);
         this.mActionMap.put(12, this.mHorizontalLineAction);
         this.mActionMap.put(13, this.mStopSelectionAction);
         this.mActionMap.put(14, this.mClearStylesAction);
         this.mActionMap.put(15, this.mImageAction);
         this.mActionMap.put(16, this.mBackgroundColorAction);
         this.mActionMap.put(17, this.mPreviewAction);
         this.mActionMap.put(18, this.mCancelEditAction);
         this.mActionMap.put(19, this.mTextViewAction);
         this.mActionMap.put(20, this.mStartEditAction);
         this.mActionMap.put(21, this.mEndEditAction);
         this.mActionMap.put(22, this.mResetAction);
         this.mActionMap.put(23, this.mShowMenuAction);
         this.mActionMap.put(6, this.mAlignAction);
         this.mActionMap.put(8, this.mTelopAction);
         this.mActionMap.put(9, this.mSwingAction);
         this.mActionMap.put(10, this.mMarqueeDialogAction);
         this.mActionMap.put(4, this.mColorAction);
         this.mActionMap.put(3, this.mSizeAction);
      }

      private EditModeActionBase getAction(int var1) {
         return this.mActionMap.containsKey(var1) ? (EditModeActionBase)this.mActionMap.get(var1) : null;
      }

      public void addAction(int var1, EditModeActionBase var2) {
         this.mActionMap.put(var1, var2);
      }

      public boolean doNext() {
         return this.doNext(this.mMode);
      }

      public boolean doNext(int var1) {
         StringBuilder var2 = new StringBuilder();
         var2.append("--- do the next action: ");
         var2.append(var1);
         var2.append(",");
         var2.append(this.mManager.getSelectState());
         Log.d("EditModeActions", var2.toString());
         EditModeActionBase var3 = this.getAction(var1);
         if (var3 == null) {
            Log.e("EditModeActions", "--- invalid action error.");
            return false;
         } else {
            var1 = this.mManager.getSelectState();
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        return false;
                     } else {
                        return this.mManager.isWaitInput() ? var3.doSelectionIsFixedAndWaitingInput() : var3.doSelectionIsFixed();
                     }
                  } else {
                     return var3.doEndPosIsSelected();
                  }
               } else {
                  return var3.doStartPosIsSelected();
               }
            } else {
               return var3.doNotSelected();
            }
         }
      }

      public void onAction(int var1) {
         this.onAction(var1, (Object[])null);
      }

      public void onAction(int var1, Object var2) {
         this.onAction(var1, new Object[]{var2});
      }

      public void onAction(int var1, Object[] var2) {
         this.getAction(var1).addParams(var2);
         this.mMode = var1;
         this.doNext(var1);
      }

      public void onSelectAction() {
         this.doNext(5);
      }

      public class AlignAction extends SetSpanActionBase {
         final EditModeActions this$1;

         public AlignAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doSelectionIsFixed() {
            if (super.doSelectionIsFixed()) {
               return true;
            } else {
               this.this$1.mDialog.onShowAlignAlertDialog();
               return true;
            }
         }
      }

      public class BackgroundColorAction extends EditModeActionBase {
         final EditModeActions this$1;

         public BackgroundColorAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            this.this$1.mDialog.onShowBackgroundColorAlertDialog();
            return true;
         }
      }

      public class CancelAction extends EditModeActionBase {
         final EditModeActions this$1;

         public CancelAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            this.this$1.mEST.cancelViewManagers();
            return true;
         }
      }

      public class ClearStylesAction extends EditModeActionBase {
         final EditModeActions this$1;

         public ClearStylesAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            this.this$1.mManager.clearStyles();
            return true;
         }
      }

      public class ColorAction extends SetSpanActionBase {
         final EditModeActions this$1;

         public ColorAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doSelectionIsFixed() {
            if (super.doSelectionIsFixed()) {
               return true;
            } else {
               this.this$1.mDialog.onShowForegroundColorAlertDialog();
               return true;
            }
         }

         protected boolean doSelectionIsFixedAndWaitingInput() {
            if (super.doSelectionIsFixedAndWaitingInput()) {
               return true;
            } else {
               int var1 = this.this$1.mManager.getSizeWaitInput();
               this.this$1.mManager.setItemColor(this.this$1.mManager.getColorWaitInput(), false);
               if (!this.this$1.mManager.isWaitInput()) {
                  this.this$1.mManager.setItemSize(var1, false);
                  this.this$1.mManager.resetEdit();
               } else {
                  this.fixSelection();
                  this.this$1.mDialog.onShowForegroundColorAlertDialog();
               }

               return true;
            }
         }
      }

      public class CopyAction extends TextViewActionBase {
         final EditModeActions this$1;

         public CopyAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doEndPosIsSelected() {
            if (super.doEndPosIsSelected()) {
               return true;
            } else {
               this.this$1.mManager.copyToClipBoard();
               this.this$1.mManager.resetEdit();
               return true;
            }
         }
      }

      public class CutAction extends TextViewActionBase {
         final EditModeActions this$1;

         public CutAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doEndPosIsSelected() {
            if (super.doEndPosIsSelected()) {
               return true;
            } else {
               this.this$1.mManager.cutToClipBoard();
               this.this$1.mManager.resetEdit();
               return true;
            }
         }
      }

      public class EditModeActionBase {
         private Object[] mParams;
         final EditModeActions this$1;

         public EditModeActionBase(EditModeActions var1) {
            this.this$1 = var1;
         }

         protected void addParams(Object[] var1) {
            this.mParams = var1;
         }

         protected boolean canOverWrap() {
            return false;
         }

         protected boolean canSelect() {
            return false;
         }

         protected boolean canWaitInput() {
            return false;
         }

         protected boolean doEndPosIsSelected() {
            return this.doStartPosIsSelected();
         }

         protected boolean doNotSelected() {
            return false;
         }

         protected boolean doSelectionIsFixed() {
            return this.doEndPosIsSelected();
         }

         protected boolean doSelectionIsFixedAndWaitingInput() {
            return this.doEndPosIsSelected();
         }

         protected boolean doStartPosIsSelected() {
            return this.doNotSelected();
         }

         protected boolean fixSelection() {
            this.this$1.mEST.finishComposingText();
            this.this$1.mManager.setSelectState(3);
            return true;
         }

         protected Object getParam(int var1) {
            Object[] var2 = this.mParams;
            if (var2 != null && var1 <= var2.length) {
               return var2[var1];
            } else {
               Log.d("EditModeActions", "--- Number of the parameter is out of bound.");
               return null;
            }
         }

         protected boolean isLine() {
            return false;
         }

         protected boolean needSelection() {
            return false;
         }
      }

      public class EndEditAction extends EditModeActionBase {
         final EditModeActions this$1;

         public EndEditAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            this.this$1.mManager.endEdit();
            return true;
         }
      }

      public class HorizontalLineAction extends EditModeActionBase {
         final EditModeActions this$1;

         public HorizontalLineAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            this.this$1.mManager.insertHorizontalLine();
            return true;
         }
      }

      public class ImageAction extends EditModeActionBase {
         final EditModeActions this$1;

         public ImageAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            Object var1 = this.getParam(0);
            if (var1 != null) {
               if (var1 instanceof Uri) {
                  this.this$1.mManager.insertImageFromUri((Uri)var1);
               } else if (var1 instanceof Integer) {
                  this.this$1.mManager.insertImageFromResId((Integer)var1);
               }
            } else {
               this.this$1.mEST.showInsertImageSelectAlertDialog();
            }

            return true;
         }
      }

      public class MarqueeDialogAction extends SetSpanActionBase {
         final EditModeActions this$1;

         public MarqueeDialogAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doSelectionIsFixed() {
            if (super.doSelectionIsFixed()) {
               return true;
            } else {
               this.this$1.mDialog.onShowMarqueeAlertDialog();
               return true;
            }
         }
      }

      public class NothingAction extends EditModeActionBase {
         final EditModeActions this$1;

         public NothingAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }
      }

      public class PasteAction extends EditModeActionBase {
         final EditModeActions this$1;

         public PasteAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            this.this$1.mManager.pasteFromClipboard();
            this.this$1.mManager.resetEdit();
            return true;
         }
      }

      public class PreviewAction extends EditModeActionBase {
         final EditModeActions this$1;

         public PreviewAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            this.this$1.mEST.showPreview();
            return true;
         }
      }

      public class ResetAction extends EditModeActionBase {
         final EditModeActions this$1;

         public ResetAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            this.this$1.mManager.resetEdit();
            return true;
         }
      }

      public class SelectAction extends EditModeActionBase {
         final EditModeActions this$1;

         public SelectAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            if (this.this$1.mManager.isTextSelected()) {
               Log.e("EditModeActions", "Selection is off, but selected");
            }

            this.this$1.mManager.setSelectStartPos();
            this.this$1.mEST.sendHintMessage(3);
            return true;
         }

         protected boolean doSelectionIsFixed() {
            return false;
         }

         protected boolean doStartPosIsSelected() {
            if (this.this$1.mManager.isTextSelected()) {
               Log.e("EditModeActions", "Selection now start, but selected");
            }

            this.this$1.mManager.setSelectEndPos();
            this.this$1.mEST.sendHintMessage(4);
            if (this.this$1.mManager.getEditMode() != 5) {
               EditModeActions var1 = this.this$1;
               var1.doNext(var1.mManager.getEditMode());
            }

            return true;
         }
      }

      public class SelectAllAction extends EditModeActionBase {
         final EditModeActions this$1;

         public SelectAllAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            this.this$1.mManager.selectAll();
            return true;
         }
      }

      public class SetSpanActionBase extends EditModeActionBase {
         final EditModeActions this$1;

         public SetSpanActionBase(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doEndPosIsSelected() {
            if (this.this$1.mManager.getEditMode() != 0 && this.this$1.mManager.getEditMode() != 5) {
               return this.doStartPosIsSelected();
            } else {
               this.this$1.mManager.setEditMode(this.this$1.mMode);
               this.fixSelection();
               this.this$1.doNext();
               return true;
            }
         }

         protected boolean doNotSelected() {
            if (this.this$1.mManager.getEditMode() != 0 && this.this$1.mManager.getEditMode() != 5) {
               if (this.this$1.mManager.getEditMode() != this.this$1.mMode) {
                  StringBuilder var1 = new StringBuilder();
                  var1.append("--- setspanactionbase");
                  var1.append(this.this$1.mManager.getEditMode());
                  var1.append(",");
                  var1.append(this.this$1.mMode);
                  Log.d("EditModeActions", var1.toString());
                  if (!this.this$1.mManager.isWaitInput()) {
                     this.this$1.mManager.resetEdit();
                     this.this$1.mManager.setEditMode(this.this$1.mMode);
                  } else {
                     this.this$1.mManager.setEditMode(0);
                     this.this$1.mManager.setSelectState(0);
                  }

                  this.this$1.doNext();
                  return true;
               } else {
                  return false;
               }
            } else {
               this.this$1.mManager.setEditMode(this.this$1.mMode);
               this.this$1.mManager.setInternalSelection(this.this$1.mEST.getSelectionStart(), this.this$1.mEST.getSelectionEnd());
               this.fixSelection();
               this.this$1.doNext();
               return true;
            }
         }

         protected boolean doSelectionIsFixed() {
            if (this.doEndPosIsSelected()) {
               return true;
            } else {
               this.this$1.mEST.sendHintMessage(0);
               return false;
            }
         }

         protected boolean doStartPosIsSelected() {
            if (this.this$1.mManager.getEditMode() != 0 && this.this$1.mManager.getEditMode() != 5) {
               return this.doNotSelected();
            } else {
               this.this$1.mManager.setEditMode(this.this$1.mMode);
               this.this$1.onSelectAction();
               return true;
            }
         }
      }

      public class ShowMenuAction extends EditModeActionBase {
         final EditModeActions this$1;

         public ShowMenuAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            this.this$1.mEST.showMenuAlertDialog();
            return true;
         }
      }

      public class SizeAction extends SetSpanActionBase {
         final EditModeActions this$1;

         public SizeAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doSelectionIsFixed() {
            if (super.doSelectionIsFixed()) {
               return true;
            } else {
               this.this$1.mDialog.onShowSizeAlertDialog();
               return true;
            }
         }

         protected boolean doSelectionIsFixedAndWaitingInput() {
            if (super.doSelectionIsFixedAndWaitingInput()) {
               return true;
            } else {
               int var1 = this.this$1.mManager.getColorWaitInput();
               this.this$1.mManager.setItemSize(this.this$1.mManager.getSizeWaitInput(), false);
               if (!this.this$1.mManager.isWaitInput()) {
                  this.this$1.mManager.setItemColor(var1, false);
                  this.this$1.mManager.resetEdit();
               } else {
                  this.fixSelection();
                  this.this$1.mDialog.onShowSizeAlertDialog();
               }

               return true;
            }
         }
      }

      public class StartEditAction extends EditModeActionBase {
         final EditModeActions this$1;

         public StartEditAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            this.this$1.mManager.startEdit();
            return true;
         }
      }

      public class StopSelectionAction extends EditModeActionBase {
         final EditModeActions this$1;

         public StopSelectionAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doNotSelected() {
            this.this$1.mManager.fixSelectionAndDoNextAction();
            return true;
         }
      }

      public class SwingAction extends SetSpanActionBase {
         final EditModeActions this$1;

         public SwingAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doSelectionIsFixed() {
            if (super.doSelectionIsFixed()) {
               return true;
            } else {
               this.this$1.mManager.setSwing();
               return true;
            }
         }
      }

      public class TelopAction extends SetSpanActionBase {
         final EditModeActions this$1;

         public TelopAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doSelectionIsFixed() {
            if (super.doSelectionIsFixed()) {
               return true;
            } else {
               this.this$1.mManager.setTelop();
               return true;
            }
         }
      }

      public class TextViewAction extends TextViewActionBase {
         final EditModeActions this$1;

         public TextViewAction(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doEndPosIsSelected() {
            if (super.doEndPosIsSelected()) {
               return true;
            } else {
               Object var1 = this.getParam(0);
               if (var1 != null && var1 instanceof Integer) {
                  this.this$1.mEST.onTextContextMenuItem((Integer)var1);
               }

               this.this$1.mManager.resetEdit();
               return true;
            }
         }
      }

      public class TextViewActionBase extends EditModeActionBase {
         final EditModeActions this$1;

         public TextViewActionBase(EditModeActions var1) {
            super(var1);
            this.this$1 = var1;
         }

         protected boolean doEndPosIsSelected() {
            if (this.this$1.mManager.getEditMode() != 0 && this.this$1.mManager.getEditMode() != 5) {
               if (this.this$1.mManager.getEditMode() != this.this$1.mMode) {
                  this.this$1.mManager.resetEdit();
                  this.this$1.mManager.setEditMode(this.this$1.mMode);
                  this.this$1.doNext();
                  return true;
               } else {
                  return false;
               }
            } else {
               this.this$1.mManager.setEditMode(this.this$1.mMode);
               this.fixSelection();
               this.this$1.doNext();
               return true;
            }
         }

         protected boolean doNotSelected() {
            if (this.this$1.mManager.getEditMode() != 0 && this.this$1.mManager.getEditMode() != 5) {
               return false;
            } else {
               this.this$1.mManager.setEditMode(this.this$1.mMode);
               this.this$1.onSelectAction();
               return true;
            }
         }
      }
   }

   public interface EditStyledTextNotifier {
      void cancelViewManager();

      boolean isButtonsFocused();

      void onStateChanged(int var1, int var2);

      void sendHintMsg(int var1);

      boolean sendOnTouchEvent(MotionEvent var1);

      boolean showInsertImageSelectAlertDialog();

      boolean showMenuAlertDialog();

      boolean showPreview();
   }

   public static class EditStyledTextSpans {
      private static final String LOG_TAG = "EditStyledTextSpan";

      public static class HorizontalLineDrawable extends ShapeDrawable {
         private static boolean DBG_HL = false;
         private Spannable mSpannable;
         private int mWidth;

         public HorizontalLineDrawable(int var1, int var2, Spannable var3) {
            super(new RectShape());
            this.mSpannable = var3;
            this.mWidth = var2;
            this.renewColor(var1);
            this.renewBounds(var2);
         }

         private HorizontalLineSpan getParentSpan() {
            Spannable var3 = this.mSpannable;
            int var2 = var3.length();
            int var1 = 0;
            HorizontalLineSpan[] var4 = (HorizontalLineSpan[])var3.getSpans(0, var2, HorizontalLineSpan.class);
            if (var4.length > 0) {
               for(var2 = var4.length; var1 < var2; ++var1) {
                  HorizontalLineSpan var5 = var4[var1];
                  if (var5.getDrawable() == this) {
                     return var5;
                  }
               }
            }

            Log.e("EditStyledTextSpan", "---renewBounds: Couldn't find");
            return null;
         }

         private void renewColor() {
            HorizontalLineSpan var2 = this.getParentSpan();
            Spannable var1 = this.mSpannable;
            ForegroundColorSpan[] var3 = (ForegroundColorSpan[])var1.getSpans(var1.getSpanStart(var2), var1.getSpanEnd(var2), ForegroundColorSpan.class);
            if (DBG_HL) {
               StringBuilder var4 = new StringBuilder();
               var4.append("--- renewColor:");
               var4.append(var3.length);
               Log.d("EditStyledTextSpan", var4.toString());
            }

            if (var3.length > 0) {
               this.renewColor(var3[var3.length - 1].getForegroundColor());
            }

         }

         private void renewColor(int var1) {
            if (DBG_HL) {
               StringBuilder var2 = new StringBuilder();
               var2.append("--- renewColor:");
               var2.append(var1);
               Log.d("EditStyledTextSpan", var2.toString());
            }

            this.getPaint().setColor(var1);
         }

         public void draw(Canvas var1) {
            this.renewColor();
            var1.drawRect(new Rect(0, 9, this.mWidth, 11), this.getPaint());
         }

         public void renewBounds(int var1) {
            if (DBG_HL) {
               StringBuilder var3 = new StringBuilder();
               var3.append("--- renewBounds:");
               var3.append(var1);
               Log.d("EditStyledTextSpan", var3.toString());
            }

            int var2 = var1;
            if (var1 > 20) {
               var2 = var1 - 20;
            }

            this.mWidth = var2;
            this.setBounds(0, 0, var2, 20);
         }
      }

      public static class HorizontalLineSpan extends DynamicDrawableSpan {
         HorizontalLineDrawable mDrawable;

         public HorizontalLineSpan(int var1, int var2, Spannable var3) {
            super(0);
            this.mDrawable = new HorizontalLineDrawable(var1, var2, var3);
         }

         public int getColor() {
            return this.mDrawable.getPaint().getColor();
         }

         public Drawable getDrawable() {
            return this.mDrawable;
         }

         public void resetWidth(int var1) {
            this.mDrawable.renewBounds(var1);
         }
      }

      public static class MarqueeSpan extends CharacterStyle {
         public static final int ALTERNATE = 1;
         public static final int NOTHING = 2;
         public static final int SCROLL = 0;
         private int mMarqueeColor;
         private int mType;

         public MarqueeSpan(int var1) {
            this(var1, 16777215);
         }

         public MarqueeSpan(int var1, int var2) {
            this.mType = var1;
            this.checkType(var1);
            this.mMarqueeColor = this.getMarqueeColor(var1, var2);
         }

         private boolean checkType(int var1) {
            if (var1 != 0 && var1 != 1) {
               Log.e("EditStyledTextSpan", "--- Invalid type of MarqueeSpan");
               return false;
            } else {
               return true;
            }
         }

         private int getMarqueeColor(int var1, int var2) {
            int var6 = Color.alpha(var2);
            int var5 = Color.red(var2);
            int var4 = Color.green(var2);
            int var7 = Color.blue(var2);
            int var3 = var6;
            if (var6 == 0) {
               var3 = 128;
            }

            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     Log.e("EditStyledText", "--- getMarqueeColor: got illigal marquee ID.");
                     return 16777215;
                  }

                  return 16777215;
               }

               if (var4 > 128) {
                  var2 = var4 / 2;
                  var1 = var5;
               } else {
                  var2 = (255 - var4) / 2;
                  var1 = var5;
               }
            } else if (var5 > 128) {
               var1 = var5 / 2;
               var2 = var4;
            } else {
               var1 = (255 - var5) / 2;
               var2 = var4;
            }

            return Color.argb(var3, var1, var2, var7);
         }

         public int getType() {
            return this.mType;
         }

         public void resetColor(int var1) {
            this.mMarqueeColor = this.getMarqueeColor(this.mType, var1);
         }

         public void updateDrawState(TextPaint var1) {
            var1.bgColor = this.mMarqueeColor;
         }
      }

      public static class RescalableImageSpan extends ImageSpan {
         private final int MAXWIDTH;
         Uri mContentUri;
         private Context mContext;
         private Drawable mDrawable;
         public int mIntrinsicHeight = -1;
         public int mIntrinsicWidth = -1;

         public RescalableImageSpan(Context var1, int var2, int var3) {
            super(var1, var2);
            this.mContext = var1;
            this.MAXWIDTH = var3;
         }

         public RescalableImageSpan(Context var1, Uri var2, int var3) {
            super(var1, var2);
            this.mContext = var1;
            this.mContentUri = var2;
            this.MAXWIDTH = var3;
         }

         private void rescaleBigImage(Drawable var1) {
            Log.d("EditStyledTextSpan", "--- rescaleBigImage:");
            if (this.MAXWIDTH >= 0) {
               int var5 = var1.getIntrinsicWidth();
               int var4 = var1.getIntrinsicHeight();
               StringBuilder var7 = new StringBuilder();
               var7.append("--- rescaleBigImage:");
               var7.append(var5);
               var7.append(",");
               var7.append(var4);
               var7.append(",");
               var7.append(this.MAXWIDTH);
               Log.d("EditStyledTextSpan", var7.toString());
               int var6 = this.MAXWIDTH;
               int var3 = var5;
               int var2 = var4;
               if (var5 > var6) {
                  var3 = this.MAXWIDTH;
                  var2 = var6 * var4 / var3;
               }

               var1.setBounds(0, 0, var3, var2);
            }
         }

         public Uri getContentUri() {
            return this.mContentUri;
         }

         public Drawable getDrawable() {
            Drawable var3 = this.mDrawable;
            if (var3 != null) {
               return var3;
            } else if (this.mContentUri != null) {
               System.gc();

               Exception var10000;
               label47: {
                  label46: {
                     int var1;
                     int var2;
                     InputStream var4;
                     Bitmap var14;
                     boolean var10001;
                     label58: {
                        try {
                           var4 = this.mContext.getContentResolver().openInputStream(this.mContentUri);
                           BitmapFactory.Options var12 = new BitmapFactory.Options();
                           var12.inJustDecodeBounds = true;
                           BitmapFactory.decodeStream(var4, (Rect)null, var12);
                           var4.close();
                           var4 = this.mContext.getContentResolver().openInputStream(this.mContentUri);
                           var1 = var12.outWidth;
                           var2 = var12.outHeight;
                           this.mIntrinsicWidth = var1;
                           this.mIntrinsicHeight = var2;
                           if (var12.outWidth > this.MAXWIDTH) {
                              var1 = this.MAXWIDTH;
                              var2 = this.MAXWIDTH * var2 / var12.outWidth;
                              Rect var13 = new Rect(0, 0, var1, var2);
                              var14 = BitmapFactory.decodeStream(var4, var13, (BitmapFactory.Options)null);
                              break label58;
                           }
                        } catch (Exception var10) {
                           var10000 = var10;
                           var10001 = false;
                           break label47;
                        } catch (OutOfMemoryError var11) {
                           var10001 = false;
                           break label46;
                        }

                        try {
                           var14 = BitmapFactory.decodeStream(var4);
                        } catch (Exception var8) {
                           var10000 = var8;
                           var10001 = false;
                           break label47;
                        } catch (OutOfMemoryError var9) {
                           var10001 = false;
                           break label46;
                        }
                     }

                     try {
                        BitmapDrawable var5 = new BitmapDrawable(this.mContext.getResources(), var14);
                        this.mDrawable = var5;
                        this.mDrawable.setBounds(0, 0, var1, var2);
                        var4.close();
                        return this.mDrawable;
                     } catch (Exception var6) {
                        var10000 = var6;
                        var10001 = false;
                        break label47;
                     } catch (OutOfMemoryError var7) {
                        var10001 = false;
                     }
                  }

                  Log.e("EditStyledTextSpan", "OutOfMemoryError");
                  return null;
               }

               Exception var15 = var10000;
               StringBuilder var16 = new StringBuilder();
               var16.append("Failed to loaded content ");
               var16.append(this.mContentUri);
               Log.e("EditStyledTextSpan", var16.toString(), var15);
               return null;
            } else {
               this.mDrawable = super.getDrawable();
               this.rescaleBigImage(this.mDrawable);
               this.mIntrinsicWidth = this.mDrawable.getIntrinsicWidth();
               this.mIntrinsicHeight = this.mDrawable.getIntrinsicHeight();
               return this.mDrawable;
            }
         }

         public boolean isOverSize() {
            boolean var1;
            if (this.getDrawable().getIntrinsicWidth() > this.MAXWIDTH) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }
      }
   }

   private class EditorManager {
      private static final String LOG_TAG = "EditStyledText.EditorManager";
      private EditModeActions mActions;
      private int mBackgroundColor;
      private int mColorWaitInput;
      private BackgroundColorSpan mComposingTextMask;
      private SpannableStringBuilder mCopyBuffer;
      private int mCurEnd;
      private int mCurStart;
      private EditStyledText mEST;
      private boolean mEditFlag;
      private boolean mKeepNonLineSpan;
      private int mMode;
      private int mSizeWaitInput;
      private SoftKeyReceiver mSkr;
      private boolean mSoftKeyBlockFlag;
      private int mState;
      private boolean mTextIsFinishedFlag;
      private boolean mWaitInputFlag;
      final EditStyledText this$0;

      EditorManager(EditStyledText var1, EditStyledText var2, StyledTextDialog var3) {
         this.this$0 = var1;
         this.mEditFlag = false;
         this.mSoftKeyBlockFlag = false;
         this.mKeepNonLineSpan = false;
         this.mWaitInputFlag = false;
         this.mTextIsFinishedFlag = false;
         this.mMode = 0;
         this.mState = 0;
         this.mCurStart = 0;
         this.mCurEnd = 0;
         this.mColorWaitInput = 16777215;
         this.mSizeWaitInput = 0;
         this.mBackgroundColor = 16777215;
         this.mEST = var2;
         this.mActions = var1.new EditModeActions(var1, this.mEST, this, var3);
         this.mSkr = new SoftKeyReceiver(this.mEST);
      }

      private void addMarquee(int var1) {
         StringBuilder var2 = new StringBuilder();
         var2.append("--- addMarquee:");
         var2.append(var1);
         Log.d("EditStyledText.EditorManager", var2.toString());
         this.setLineStyledTextSpan(new EditStyledTextSpans.MarqueeSpan(var1, this.mEST.getBackgroundColor()));
      }

      private void addSwing() {
         this.addMarquee(0);
      }

      private void addTelop() {
         this.addMarquee(1);
      }

      private void changeAlign(Alignment var1) {
         this.setLineStyledTextSpan(new AlignmentSpan.Standard(var1));
      }

      private void changeColorSelectedText(int var1) {
         if (this.mCurStart != this.mCurEnd) {
            this.setStyledTextSpan(new ForegroundColorSpan(var1), this.mCurStart, this.mCurEnd);
         } else {
            Log.e("EditStyledText.EditorManager", "---changeColor: Size of the span is zero");
         }

      }

      private void changeSizeSelectedText(int var1) {
         if (this.mCurStart != this.mCurEnd) {
            this.setStyledTextSpan(new AbsoluteSizeSpan(var1), this.mCurStart, this.mCurEnd);
         } else {
            Log.e("EditStyledText.EditorManager", "---changeSize: Size of the span is zero");
         }

      }

      private void clearStyles() {
         Log.d("EditStyledText.EditorManager", "--- onClearStyles");
         this.clearStyles(this.mEST.getText());
         EditStyledText var1 = this.mEST;
         var1.setBackgroundDrawable(var1.mDefaultBackground);
         this.mBackgroundColor = 16777215;
         this.onRefreshZeoWidthChar();
      }

      private void clearStyles(CharSequence var1) {
         Log.d("EditStyledText", "--- onClearStyles");
         int var3 = var1.length();
         if (var1 instanceof Editable) {
            Editable var4 = (Editable)var1;
            int var2 = 0;
            Object[] var5 = var4.getSpans(0, var3, Object.class);

            for(var3 = var5.length; var2 < var3; ++var2) {
               Object var6 = var5[var2];
               if (var6 instanceof ParagraphStyle || var6 instanceof QuoteSpan || var6 instanceof CharacterStyle && !(var6 instanceof UnderlineSpan)) {
                  if (var6 instanceof ImageSpan || var6 instanceof EditStyledTextSpans.HorizontalLineSpan) {
                     var4.replace(var4.getSpanStart(var6), var4.getSpanEnd(var6), "");
                  }

                  var4.removeSpan(var6);
               }
            }
         }

      }

      private void copyToClipBoard() {
         int var1 = Math.min(this.getSelectionStart(), this.getSelectionEnd());
         int var2 = Math.max(this.getSelectionStart(), this.getSelectionEnd());
         this.mCopyBuffer = (SpannableStringBuilder)this.mEST.getText().subSequence(var1, var2);
         SpannableStringBuilder var3 = this.removeImageChar(this.mCopyBuffer);
         ((ClipboardManager)this.this$0.getContext().getSystemService("clipboard")).setText(var3);
         this.dumpSpannableString(var3);
         this.dumpSpannableString(this.mCopyBuffer);
      }

      private void cutToClipBoard() {
         this.copyToClipBoard();
         int var2 = Math.min(this.getSelectionStart(), this.getSelectionEnd());
         int var1 = Math.max(this.getSelectionStart(), this.getSelectionEnd());
         this.mEST.getText().delete(var2, var1);
      }

      private void dumpSpannableString(CharSequence var1) {
         if (var1 instanceof Spannable) {
            Spannable var7 = (Spannable)var1;
            int var3 = var7.length();
            StringBuilder var4 = new StringBuilder();
            var4.append("--- dumpSpannableString, txt:");
            var4.append(var7);
            var4.append(", len:");
            var4.append(var3);
            Log.d("EditStyledText", var4.toString());
            int var2 = 0;
            Object[] var8 = var7.getSpans(0, var3, Object.class);

            for(var3 = var8.length; var2 < var3; ++var2) {
               Object var6 = var8[var2];
               StringBuilder var5 = new StringBuilder();
               var5.append("--- dumpSpannableString, class:");
               var5.append(var6);
               var5.append(",");
               var5.append(var7.getSpanStart(var6));
               var5.append(",");
               var5.append(var7.getSpanEnd(var6));
               var5.append(",");
               var5.append(var7.getSpanFlags(var6));
               Log.d("EditStyledText", var5.toString());
            }
         }

      }

      private void endEdit() {
         Log.d("EditStyledText.EditorManager", "--- handleCancel");
         this.mMode = 0;
         this.mState = 0;
         this.mEditFlag = false;
         this.mColorWaitInput = 16777215;
         this.mSizeWaitInput = 0;
         this.mWaitInputFlag = false;
         this.mSoftKeyBlockFlag = false;
         this.mKeepNonLineSpan = false;
         this.mTextIsFinishedFlag = false;
         this.unsetSelect();
         this.mEST.setOnClickListener((OnClickListener)null);
         this.unblockSoftKey();
      }

      private int findLineEnd(Editable var1, int var2) {
         int var3 = var2;

         int var4;
         while(true) {
            var4 = var3;
            if (var3 >= var1.length()) {
               break;
            }

            if (var1.charAt(var3) == '\n') {
               var4 = var3 + 1;
               break;
            }

            ++var3;
         }

         StringBuilder var5 = new StringBuilder();
         var5.append("--- findLineEnd:");
         var5.append(var2);
         var5.append(",");
         var5.append(var1.length());
         var5.append(",");
         var5.append(var4);
         Log.d("EditStyledText.EditorManager", var5.toString());
         return var4;
      }

      private int findLineStart(Editable var1, int var2) {
         int var3;
         for(var3 = var2; var3 > 0 && var1.charAt(var3 - 1) != '\n'; --var3) {
         }

         StringBuilder var4 = new StringBuilder();
         var4.append("--- findLineStart:");
         var4.append(var2);
         var4.append(",");
         var4.append(var1.length());
         var4.append(",");
         var4.append(var3);
         Log.d("EditStyledText.EditorManager", var4.toString());
         return var3;
      }

      private void fixSelectionAndDoNextAction() {
         StringBuilder var1 = new StringBuilder();
         var1.append("--- handleComplete:");
         var1.append(this.mCurStart);
         var1.append(",");
         var1.append(this.mCurEnd);
         Log.d("EditStyledText.EditorManager", var1.toString());
         if (this.mEditFlag) {
            if (this.mCurStart == this.mCurEnd) {
               var1 = new StringBuilder();
               var1.append("--- cancel handle complete:");
               var1.append(this.mCurStart);
               Log.d("EditStyledText.EditorManager", var1.toString());
               this.resetEdit();
            } else {
               if (this.mState == 2) {
                  this.mState = 3;
               }

               this.mActions.doNext(this.mMode);
               EditStyledText var2 = this.mEST;
               EditStyledText.stopSelecting(var2, var2.getText());
            }
         }
      }

      private void handleSelectAll() {
         if (this.mEditFlag) {
            this.mActions.onAction(11);
         }
      }

      private void insertHorizontalLine() {
         Log.d("EditStyledText.EditorManager", "--- onInsertHorizontalLine:");
         int var2 = this.mEST.getSelectionStart();
         int var1 = var2;
         if (var2 > 0) {
            var1 = var2;
            if (this.mEST.getText().charAt(var2 - 1) != '\n') {
               this.mEST.getText().insert(var2, "\n");
               var1 = var2 + 1;
            }
         }

         EditStyledTextSpans.HorizontalLineSpan var3 = new EditStyledTextSpans.HorizontalLineSpan(-16777216, this.mEST.getWidth(), this.mEST.getText());
         var2 = var1 + 1;
         this.insertImageSpan(var3, var1);
         this.mEST.getText().insert(var2, "\n");
         this.mEST.setSelection(var2 + 1);
         this.mEST.notifyStateChanged(this.mMode, this.mState);
      }

      private void insertImageFromResId(int var1) {
         this.insertImageSpan(new EditStyledTextSpans.RescalableImageSpan(this.mEST.getContext(), var1, this.mEST.getMaxImageWidthDip()), this.mEST.getSelectionStart());
      }

      private void insertImageFromUri(Uri var1) {
         this.insertImageSpan(new EditStyledTextSpans.RescalableImageSpan(this.mEST.getContext(), var1, this.mEST.getMaxImageWidthPx()), this.mEST.getSelectionStart());
      }

      private void insertImageSpan(DynamicDrawableSpan var1, int var2) {
         Log.d("EditStyledText.EditorManager", "--- insertImageSpan:");
         if (var1 != null && var1.getDrawable() != null) {
            this.mEST.getText().insert(var2, "￼");
            this.mEST.getText().setSpan(var1, var2, var2 + 1, 33);
            this.mEST.notifyStateChanged(this.mMode, this.mState);
         } else {
            Log.e("EditStyledText.EditorManager", "--- insertImageSpan: null span was inserted");
            this.mEST.sendHintMessage(5);
         }

      }

      private boolean isClipBoardChanged(CharSequence var1) {
         StringBuilder var4 = new StringBuilder();
         var4.append("--- isClipBoardChanged:");
         var4.append(var1);
         Log.d("EditStyledText", var4.toString());
         if (this.mCopyBuffer == null) {
            return true;
         } else {
            int var3 = var1.length();
            SpannableStringBuilder var5 = this.removeImageChar(this.mCopyBuffer);
            var4 = new StringBuilder();
            var4.append("--- clipBoard:");
            var4.append(var3);
            var4.append(",");
            var4.append(var5);
            var4.append(var1);
            Log.d("EditStyledText", var4.toString());
            if (var3 != var5.length()) {
               return true;
            } else {
               for(int var2 = 0; var2 < var3; ++var2) {
                  if (var1.charAt(var2) != var5.charAt(var2)) {
                     return true;
                  }
               }

               return false;
            }
         }
      }

      private boolean isTextSelected() {
         int var1 = this.mState;
         boolean var2;
         if (var1 != 2 && var1 != 3) {
            var2 = false;
         } else {
            var2 = true;
         }

         return var2;
      }

      private boolean isWaitingNextAction() {
         StringBuilder var1 = new StringBuilder();
         var1.append("--- waitingNext:");
         var1.append(this.mCurStart);
         var1.append(",");
         var1.append(this.mCurEnd);
         var1.append(",");
         var1.append(this.mState);
         Log.d("EditStyledText.EditorManager", var1.toString());
         if (this.mCurStart == this.mCurEnd && this.mState == 3) {
            this.waitSelection();
            return true;
         } else {
            this.resumeSelection();
            return false;
         }
      }

      private void pasteFromClipboard() {
         int var2 = Math.min(this.mEST.getSelectionStart(), this.mEST.getSelectionEnd());
         int var1 = Math.max(this.mEST.getSelectionStart(), this.mEST.getSelectionEnd());
         Selection.setSelection(this.mEST.getText(), var1);
         ClipboardManager var5 = (ClipboardManager)this.this$0.getContext().getSystemService("clipboard");
         this.mKeepNonLineSpan = true;
         this.mEST.getText().replace(var2, var1, var5.getText());
         if (!this.isClipBoardChanged(var5.getText())) {
            Log.d("EditStyledText", "--- handlePaste: startPasteImage");
            SpannableStringBuilder var7 = this.mCopyBuffer;
            int var3 = var7.length();
            var1 = 0;
            DynamicDrawableSpan[] var8 = (DynamicDrawableSpan[])var7.getSpans(0, var3, DynamicDrawableSpan.class);

            for(var3 = var8.length; var1 < var3; ++var1) {
               DynamicDrawableSpan var6 = var8[var1];
               int var4 = this.mCopyBuffer.getSpanStart(var6);
               if (var6 instanceof EditStyledTextSpans.HorizontalLineSpan) {
                  this.insertImageSpan(new EditStyledTextSpans.HorizontalLineSpan(-16777216, this.mEST.getWidth(), this.mEST.getText()), var2 + var4);
               } else if (var6 instanceof EditStyledTextSpans.RescalableImageSpan) {
                  this.insertImageSpan(new EditStyledTextSpans.RescalableImageSpan(this.mEST.getContext(), ((EditStyledTextSpans.RescalableImageSpan)var6).getContentUri(), this.mEST.getMaxImageWidthPx()), var2 + var4);
               }
            }
         }

      }

      private SpannableStringBuilder removeImageChar(SpannableStringBuilder var1) {
         var1 = new SpannableStringBuilder(var1);
         int var3 = var1.length();
         int var2 = 0;
         DynamicDrawableSpan[] var5 = (DynamicDrawableSpan[])var1.getSpans(0, var3, DynamicDrawableSpan.class);

         for(var3 = var5.length; var2 < var3; ++var2) {
            DynamicDrawableSpan var4 = var5[var2];
            if (var4 instanceof EditStyledTextSpans.HorizontalLineSpan || var4 instanceof EditStyledTextSpans.RescalableImageSpan) {
               var1.replace(var1.getSpanStart(var4), var1.getSpanEnd(var4), "");
            }
         }

         return var1;
      }

      private void resetEdit() {
         this.endEdit();
         this.mEditFlag = true;
         this.mEST.notifyStateChanged(this.mMode, this.mState);
      }

      private void resumeSelection() {
         Log.d("EditStyledText.EditorManager", "--- resumeSelection");
         this.mWaitInputFlag = false;
         this.mState = 3;
         EditStyledText var1 = this.mEST;
         EditStyledText.stopSelecting(var1, var1.getText());
      }

      private void selectAll() {
         Selection.selectAll(this.mEST.getText());
         this.mCurStart = this.mEST.getSelectionStart();
         this.mCurEnd = this.mEST.getSelectionEnd();
         this.mMode = 5;
         this.mState = 3;
      }

      private void setEditMode(int var1) {
         this.mMode = var1;
      }

      private void setInternalSelection(int var1, int var2) {
         this.mCurStart = var1;
         this.mCurEnd = var2;
      }

      private void setLineStyledTextSpan(Object var1) {
         int var3 = Math.min(this.mCurStart, this.mCurEnd);
         int var4 = Math.max(this.mCurStart, this.mCurEnd);
         int var2 = this.mEST.getSelectionStart();
         var3 = this.findLineStart(this.mEST.getText(), var3);
         var4 = this.findLineEnd(this.mEST.getText(), var4);
         if (var3 == var4) {
            this.mEST.getText().insert(var4, "\n");
            this.setStyledTextSpan(var1, var3, var4 + 1);
         } else {
            this.setStyledTextSpan(var1, var3, var4);
         }

         Selection.setSelection(this.mEST.getText(), var2);
      }

      private void setSelectEndPos() {
         if (this.mEST.getSelectionEnd() == this.mCurStart) {
            this.setEndPos(this.mEST.getSelectionStart());
         } else {
            this.setEndPos(this.mEST.getSelectionEnd());
         }

      }

      private void setSelectStartPos() {
         Log.d("EditStyledText.EditorManager", "--- setSelectStartPos");
         this.mCurStart = this.mEST.getSelectionStart();
         this.mState = 1;
      }

      private void setSelectState(int var1) {
         this.mState = var1;
      }

      private void setSelection() {
         StringBuilder var3 = new StringBuilder();
         var3.append("--- onSelect:");
         var3.append(this.mCurStart);
         var3.append(",");
         var3.append(this.mCurEnd);
         Log.d("EditStyledText.EditorManager", var3.toString());
         int var1 = this.mCurStart;
         if (var1 >= 0 && var1 <= this.mEST.getText().length()) {
            var1 = this.mCurEnd;
            if (var1 >= 0 && var1 <= this.mEST.getText().length()) {
               int var2 = this.mCurStart;
               var1 = this.mCurEnd;
               if (var2 < var1) {
                  this.mEST.setSelection(var2, var1);
                  this.mState = 2;
                  return;
               } else {
                  if (var2 > var1) {
                     this.mEST.setSelection(var1, var2);
                     this.mState = 2;
                  } else {
                     this.mState = 1;
                  }

                  return;
               }
            }
         }

         var3 = new StringBuilder();
         var3.append("Select is on, but cursor positions are illigal.:");
         var3.append(this.mEST.getText().length());
         var3.append(",");
         var3.append(this.mCurStart);
         var3.append(",");
         var3.append(this.mCurEnd);
         Log.e("EditStyledText.EditorManager", var3.toString());
      }

      private void setStyledTextSpan(Object var1, int var2, int var3) {
         StringBuilder var5 = new StringBuilder();
         var5.append("--- setStyledTextSpan:");
         var5.append(this.mMode);
         var5.append(",");
         var5.append(var2);
         var5.append(",");
         var5.append(var3);
         Log.d("EditStyledText.EditorManager", var5.toString());
         int var4 = Math.min(var2, var3);
         var2 = Math.max(var2, var3);
         this.mEST.getText().setSpan(var1, var4, var2, 33);
         Selection.setSelection(this.mEST.getText(), var2);
      }

      private void startEdit() {
         this.resetEdit();
         this.showSoftKey();
      }

      private void unsetSelect() {
         Log.d("EditStyledText.EditorManager", "--- offSelect");
         EditStyledText var2 = this.mEST;
         EditStyledText.stopSelecting(var2, var2.getText());
         int var1 = this.mEST.getSelectionStart();
         this.mEST.setSelection(var1, var1);
         this.mState = 0;
      }

      private void waitSelection() {
         Log.d("EditStyledText.EditorManager", "--- waitSelection");
         this.mWaitInputFlag = true;
         if (this.mCurStart == this.mCurEnd) {
            this.mState = 1;
         } else {
            this.mState = 2;
         }

         EditStyledText var1 = this.mEST;
         EditStyledText.startSelecting(var1, var1.getText());
      }

      public void addAction(int var1, EditModeActions.EditModeActionBase var2) {
         this.mActions.addAction(var1, var2);
      }

      public void blockSoftKey() {
         Log.d("EditStyledText.EditorManager", "--- blockSoftKey:");
         this.hideSoftKey();
         this.mSoftKeyBlockFlag = true;
      }

      public boolean canPaste() {
         SpannableStringBuilder var2 = this.mCopyBuffer;
         boolean var1;
         if (var2 != null && var2.length() > 0 && this.removeImageChar(this.mCopyBuffer).length() == 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public int getBackgroundColor() {
         return this.mBackgroundColor;
      }

      public int getColorWaitInput() {
         return this.mColorWaitInput;
      }

      public int getEditMode() {
         return this.mMode;
      }

      public int getSelectState() {
         return this.mState;
      }

      public int getSelectionEnd() {
         return this.mCurEnd;
      }

      public int getSelectionStart() {
         return this.mCurStart;
      }

      public int getSizeWaitInput() {
         return this.mSizeWaitInput;
      }

      public void hideSoftKey() {
         Log.d("EditStyledText.EditorManager", "--- hidesoftkey");
         if (this.mEST.isFocused()) {
            this.mSkr.mNewStart = Selection.getSelectionStart(this.mEST.getText());
            this.mSkr.mNewEnd = Selection.getSelectionEnd(this.mEST.getText());
            ((InputMethodManager)this.mEST.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mEST.getWindowToken(), 0, this.mSkr);
         }
      }

      public boolean isEditting() {
         return this.mEditFlag;
      }

      public boolean isSoftKeyBlocked() {
         return this.mSoftKeyBlockFlag;
      }

      public boolean isStyledText() {
         Editable var2 = this.mEST.getText();
         int var1 = var2.length();
         return ((ParagraphStyle[])var2.getSpans(0, var1, ParagraphStyle.class)).length > 0 || ((QuoteSpan[])var2.getSpans(0, var1, QuoteSpan.class)).length > 0 || ((CharacterStyle[])var2.getSpans(0, var1, CharacterStyle.class)).length > 0 || this.mBackgroundColor != 16777215;
      }

      public boolean isWaitInput() {
         return this.mWaitInputFlag;
      }

      public void onAction(int var1) {
         this.onAction(var1, true);
      }

      public void onAction(int var1, boolean var2) {
         this.mActions.onAction(var1);
         if (var2) {
            this.mEST.notifyStateChanged(this.mMode, this.mState);
         }

      }

      public void onCancelViewManagers() {
         this.mActions.onAction(18);
      }

      public void onClearStyles() {
         this.mActions.onAction(14);
      }

      public void onCursorMoved() {
         Log.d("EditStyledText.EditorManager", "--- onClickView");
         int var1 = this.mState;
         if (var1 == 1 || var1 == 2) {
            this.mActions.onSelectAction();
            this.mEST.notifyStateChanged(this.mMode, this.mState);
         }

      }

      public void onFixSelectedItem() {
         Log.d("EditStyledText.EditorManager", "--- onFixSelectedItem");
         this.fixSelectionAndDoNextAction();
         this.mEST.notifyStateChanged(this.mMode, this.mState);
      }

      public void onInsertImage(int var1) {
         this.mActions.onAction(15, (Object)var1);
         this.mEST.notifyStateChanged(this.mMode, this.mState);
      }

      public void onInsertImage(Uri var1) {
         this.mActions.onAction(15, (Object)var1);
         this.mEST.notifyStateChanged(this.mMode, this.mState);
      }

      public void onRefreshStyles() {
         Log.d("EditStyledText.EditorManager", "--- onRefreshStyles");
         Editable var5 = this.mEST.getText();
         int var2 = var5.length();
         int var3 = this.mEST.getWidth();
         EditStyledTextSpans.HorizontalLineSpan[] var7 = (EditStyledTextSpans.HorizontalLineSpan[])var5.getSpans(0, var2, EditStyledTextSpans.HorizontalLineSpan.class);
         int var4 = var7.length;

         int var1;
         for(var1 = 0; var1 < var4; ++var1) {
            var7[var1].resetWidth(var3);
         }

         EditStyledTextSpans.MarqueeSpan[] var6 = (EditStyledTextSpans.MarqueeSpan[])var5.getSpans(0, var2, EditStyledTextSpans.MarqueeSpan.class);
         var2 = var6.length;

         for(var1 = 0; var1 < var2; ++var1) {
            var6[var1].resetColor(this.mEST.getBackgroundColor());
         }

         if (var7.length > 0) {
            StringBuilder var8 = new StringBuilder();
            var8.append("");
            var8.append(var5.charAt(0));
            var5.replace(0, 1, var8.toString());
         }

      }

      public void onRefreshZeoWidthChar() {
         Editable var3 = this.mEST.getText();

         int var2;
         for(int var1 = 0; var1 < var3.length(); var1 = var2 + 1) {
            var2 = var1;
            if (var3.charAt(var1) == 8288) {
               var3.replace(var1, var1 + 1, "");
               var2 = var1 - 1;
            }
         }

      }

      public void onStartSelect(boolean var1) {
         Log.d("EditStyledText.EditorManager", "--- onClickSelect");
         this.mMode = 5;
         if (this.mState == 0) {
            this.mActions.onSelectAction();
         } else {
            this.unsetSelect();
            this.mActions.onSelectAction();
         }

         if (var1) {
            this.mEST.notifyStateChanged(this.mMode, this.mState);
         }

      }

      public void onStartSelectAll(boolean var1) {
         Log.d("EditStyledText.EditorManager", "--- onClickSelectAll");
         this.handleSelectAll();
         if (var1) {
            this.mEST.notifyStateChanged(this.mMode, this.mState);
         }

      }

      public void onStartShowMenuAlertDialog() {
         this.mActions.onAction(23);
      }

      public void setAlignment(Alignment var1) {
         int var2 = this.mState;
         if (var2 == 2 || var2 == 3) {
            this.changeAlign(var1);
            this.resetEdit();
         }

      }

      public void setBackgroundColor(int var1) {
         this.mBackgroundColor = var1;
      }

      public void setEndPos(int var1) {
         StringBuilder var2 = new StringBuilder();
         var2.append("--- setSelectedEndPos:");
         var2.append(var1);
         Log.d("EditStyledText.EditorManager", var2.toString());
         this.mCurEnd = var1;
         this.setSelection();
      }

      public void setItemColor(int var1, boolean var2) {
         Log.d("EditStyledText.EditorManager", "--- setItemColor");
         if (this.isWaitingNextAction()) {
            this.mColorWaitInput = var1;
         } else {
            int var3 = this.mState;
            if (var3 == 2 || var3 == 3) {
               if (var1 != 16777215) {
                  this.changeColorSelectedText(var1);
               }

               if (var2) {
                  this.resetEdit();
               }
            }
         }

      }

      public void setItemSize(int var1, boolean var2) {
         Log.d("EditStyledText.EditorManager", "--- setItemSize");
         if (this.isWaitingNextAction()) {
            this.mSizeWaitInput = var1;
         } else {
            int var3 = this.mState;
            if (var3 == 2 || var3 == 3) {
               if (var1 > 0) {
                  this.changeSizeSelectedText(var1);
               }

               if (var2) {
                  this.resetEdit();
               }
            }
         }

      }

      public void setMarquee(int var1) {
         int var2 = this.mState;
         if (var2 == 2 || var2 == 3) {
            this.addMarquee(var1);
            this.resetEdit();
         }

      }

      public void setSwing() {
         int var1 = this.mState;
         if (var1 == 2 || var1 == 3) {
            this.addSwing();
            this.resetEdit();
         }

      }

      public void setTelop() {
         int var1 = this.mState;
         if (var1 == 2 || var1 == 3) {
            this.addTelop();
            this.resetEdit();
         }

      }

      public void setTextComposingMask(int var1, int var2) {
         StringBuilder var5 = new StringBuilder();
         var5.append("--- setTextComposingMask:");
         var5.append(var1);
         var5.append(",");
         var5.append(var2);
         Log.d("EditStyledText", var5.toString());
         int var3 = Math.min(var1, var2);
         var2 = Math.max(var1, var2);
         if (this.isWaitInput() && this.mColorWaitInput != 16777215) {
            var1 = this.mColorWaitInput;
         } else {
            var1 = this.mEST.getForegroundColor(var3);
         }

         int var4 = this.mEST.getBackgroundColor();
         var5 = new StringBuilder();
         var5.append("--- fg:");
         var5.append(Integer.toHexString(var1));
         var5.append(",bg:");
         var5.append(Integer.toHexString(var4));
         var5.append(",");
         var5.append(this.isWaitInput());
         var5.append(",,");
         var5.append(this.mMode);
         Log.d("EditStyledText", var5.toString());
         if (var1 == var4) {
            var1 = Integer.MIN_VALUE | ~(-16777216 | var4);
            BackgroundColorSpan var6 = this.mComposingTextMask;
            if (var6 == null || var6.getBackgroundColor() != var1) {
               this.mComposingTextMask = new BackgroundColorSpan(var1);
            }

            this.mEST.getText().setSpan(this.mComposingTextMask, var3, var2, 33);
         }

      }

      public void showSoftKey() {
         this.showSoftKey(this.mEST.getSelectionStart(), this.mEST.getSelectionEnd());
      }

      public void showSoftKey(int var1, int var2) {
         Log.d("EditStyledText.EditorManager", "--- showsoftkey");
         if (this.mEST.isFocused() && !this.isSoftKeyBlocked()) {
            this.mSkr.mNewStart = Selection.getSelectionStart(this.mEST.getText());
            this.mSkr.mNewEnd = Selection.getSelectionEnd(this.mEST.getText());
            if (((InputMethodManager)this.this$0.getContext().getSystemService("input_method")).showSoftInput(this.mEST, 0, this.mSkr) && this.mSkr != null) {
               Selection.setSelection(this.this$0.getText(), var1, var2);
            }

         }
      }

      public void unblockSoftKey() {
         Log.d("EditStyledText.EditorManager", "--- unblockSoftKey:");
         this.mSoftKeyBlockFlag = false;
      }

      public void unsetTextComposingMask() {
         Log.d("EditStyledText", "--- unsetTextComposingMask");
         if (this.mComposingTextMask != null) {
            this.mEST.getText().removeSpan(this.mComposingTextMask);
            this.mComposingTextMask = null;
         }

      }

      public void updateSpanNextToCursor(Editable var1, int var2, int var3, int var4) {
         StringBuilder var11 = new StringBuilder();
         var11.append("updateSpanNext:");
         var11.append(var2);
         var11.append(",");
         var11.append(var3);
         var11.append(",");
         var11.append(var4);
         Log.d("EditStyledText.EditorManager", var11.toString());
         int var6 = var2 + var4;
         int var7 = Math.min(var2, var6);
         var2 = Math.max(var2, var6);
         Object[] var12 = var1.getSpans(var2, var2, Object.class);
         int var8 = var12.length;
         int var5 = 0;

         for(var2 = var6; var5 < var8; ++var5) {
            Object var13 = var12[var5];
            if (!(var13 instanceof EditStyledTextSpans.MarqueeSpan) && !(var13 instanceof AlignmentSpan)) {
               if (var13 instanceof EditStyledTextSpans.HorizontalLineSpan && var1.getSpanStart(var13) == var2 && var2 > 0 && this.mEST.getText().charAt(var2 - 1) != '\n') {
                  this.mEST.getText().insert(var2, "\n");
                  this.mEST.setSelection(var2);
               }
            } else {
               int var9 = var1.getSpanStart(var13);
               int var10 = var1.getSpanEnd(var13);
               var11 = new StringBuilder();
               var11.append("spantype:");
               var11.append(var13.getClass());
               var11.append(",");
               var11.append(var10);
               Log.d("EditStyledText.EditorManager", var11.toString());
               var6 = var7;
               if (var13 instanceof EditStyledTextSpans.MarqueeSpan || var13 instanceof AlignmentSpan) {
                  var6 = this.findLineStart(this.mEST.getText(), var7);
               }

               if (var6 < var9 && var3 > var4) {
                  var1.removeSpan(var13);
               } else if (var9 > var7) {
                  var1.setSpan(var13, var7, var10, 33);
               }
            }
         }

      }

      public void updateSpanPreviousFromCursor(Editable var1, int var2, int var3, int var4) {
         StringBuilder var11 = new StringBuilder();
         var11.append("updateSpanPrevious:");
         var11.append(var2);
         var11.append(",");
         var11.append(var3);
         var11.append(",");
         var11.append(var4);
         Log.d("EditStyledText.EditorManager", var11.toString());
         int var8 = var2 + var4;
         int var5 = Math.min(var2, var8);
         int var6 = Math.max(var2, var8);
         Object[] var12 = var1.getSpans(var5, var5, Object.class);
         int var9 = var12.length;

         for(var5 = 0; var5 < var9; ++var5) {
            Object var14 = var12[var5];
            int var7;
            if (!(var14 instanceof ForegroundColorSpan) && !(var14 instanceof AbsoluteSizeSpan) && !(var14 instanceof EditStyledTextSpans.MarqueeSpan) && !(var14 instanceof AlignmentSpan)) {
               if (var14 instanceof EditStyledTextSpans.HorizontalLineSpan) {
                  var7 = var1.getSpanStart(var14);
                  var2 = var1.getSpanEnd(var14);
                  if (var3 > var4) {
                     var1.replace(var7, var2, "");
                     var1.removeSpan(var14);
                  } else if (var2 == var8 && var8 < var1.length() && this.mEST.getText().charAt(var8) != '\n') {
                     this.mEST.getText().insert(var8, "\n");
                  }
               }
            } else {
               int var10 = var1.getSpanStart(var14);
               var7 = var1.getSpanEnd(var14);
               StringBuilder var13 = new StringBuilder();
               var13.append("spantype:");
               var13.append(var14.getClass());
               var13.append(",");
               var13.append(var10);
               Log.d("EditStyledText.EditorManager", var13.toString());
               var2 = var6;
               if (!(var14 instanceof EditStyledTextSpans.MarqueeSpan) && !(var14 instanceof AlignmentSpan)) {
                  if (this.mKeepNonLineSpan) {
                     var2 = var7;
                  }
               } else {
                  var2 = this.findLineEnd(this.mEST.getText(), var6);
               }

               if (var7 < var2) {
                  Log.d("EditStyledText.EditorManager", "updateSpanPrevious: extend span");
                  var1.setSpan(var14, var10, var2, 33);
               }
            }
         }

      }
   }

   private class MenuHandler implements MenuItem.OnMenuItemClickListener {
      final EditStyledText this$0;

      private MenuHandler(EditStyledText var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      MenuHandler(EditStyledText var1, Object var2) {
         this(var1);
      }

      public boolean onMenuItemClick(MenuItem var1) {
         return this.this$0.onTextContextMenuItem(var1.getItemId());
      }
   }

   public static class SavedStyledTextState extends BaseSavedState {
      public int mBackgroundColor;

      SavedStyledTextState(Parcelable var1) {
         super(var1);
      }

      public String toString() {
         StringBuilder var1 = new StringBuilder();
         var1.append("EditStyledText.SavedState{");
         var1.append(Integer.toHexString(System.identityHashCode(this)));
         var1.append(" bgcolor=");
         var1.append(this.mBackgroundColor);
         var1.append("}");
         return var1.toString();
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeInt(this.mBackgroundColor);
      }
   }

   private static class SoftKeyReceiver extends ResultReceiver {
      EditStyledText mEST;
      int mNewEnd;
      int mNewStart;

      SoftKeyReceiver(EditStyledText var1) {
         super(var1.getHandler());
         this.mEST = var1;
      }

      protected void onReceiveResult(int var1, Bundle var2) {
         if (var1 != 2) {
            Selection.setSelection(this.mEST.getText(), this.mNewStart, this.mNewEnd);
         }

      }
   }

   private static class StyledTextArrowKeyMethod extends ArrowKeyMovementMethod {
      String LOG_TAG = "StyledTextArrowKeyMethod";
      EditorManager mManager;

      StyledTextArrowKeyMethod(EditorManager var1) {
         this.mManager = var1;
      }

      private boolean executeDown(TextView var1, Spannable var2, int var3) {
         String var6 = this.LOG_TAG;
         StringBuilder var5 = new StringBuilder();
         var5.append("--- executeDown: ");
         var5.append(var3);
         Log.d(var6, var5.toString());
         boolean var4 = false;
         switch (var3) {
            case 19:
               var4 = false | this.up(var1, var2);
               break;
            case 20:
               var4 = false | this.down(var1, var2);
               break;
            case 21:
               var4 = false | this.left(var1, var2);
               break;
            case 22:
               var4 = false | this.right(var1, var2);
               break;
            case 23:
               this.mManager.onFixSelectedItem();
               var4 = true;
         }

         return var4;
      }

      private int getEndPos(TextView var1) {
         int var2;
         if (var1.getSelectionStart() == this.mManager.getSelectionStart()) {
            var2 = var1.getSelectionEnd();
         } else {
            var2 = var1.getSelectionStart();
         }

         return var2;
      }

      protected boolean down(TextView var1, Spannable var2) {
         Log.d(this.LOG_TAG, "--- down:");
         Layout var5 = var1.getLayout();
         int var4 = this.getEndPos(var1);
         int var3 = var5.getLineForOffset(var4);
         if (var3 < var5.getLineCount() - 1) {
            if (var5.getParagraphDirection(var3) == var5.getParagraphDirection(var3 + 1)) {
               var3 = var5.getOffsetForHorizontal(var3 + 1, var5.getPrimaryHorizontal(var4));
            } else {
               var3 = var5.getLineStart(var3 + 1);
            }

            this.mManager.setEndPos(var3);
            this.mManager.onCursorMoved();
         }

         return true;
      }

      protected boolean left(TextView var1, Spannable var2) {
         Log.d(this.LOG_TAG, "--- left:");
         int var3 = var1.getLayout().getOffsetToLeftOf(this.getEndPos(var1));
         this.mManager.setEndPos(var3);
         this.mManager.onCursorMoved();
         return true;
      }

      public boolean onKeyDown(TextView var1, Spannable var2, int var3, KeyEvent var4) {
         String var5 = this.LOG_TAG;
         StringBuilder var6 = new StringBuilder();
         var6.append("---onkeydown:");
         var6.append(var3);
         Log.d(var5, var6.toString());
         this.mManager.unsetTextComposingMask();
         return this.mManager.getSelectState() != 1 && this.mManager.getSelectState() != 2 ? super.onKeyDown(var1, var2, var3, var4) : this.executeDown(var1, var2, var3);
      }

      protected boolean right(TextView var1, Spannable var2) {
         Log.d(this.LOG_TAG, "--- right:");
         int var3 = var1.getLayout().getOffsetToRightOf(this.getEndPos(var1));
         this.mManager.setEndPos(var3);
         this.mManager.onCursorMoved();
         return true;
      }

      protected boolean up(TextView var1, Spannable var2) {
         Log.d(this.LOG_TAG, "--- up:");
         Layout var5 = var1.getLayout();
         int var3 = this.getEndPos(var1);
         int var4 = var5.getLineForOffset(var3);
         if (var4 > 0) {
            if (var5.getParagraphDirection(var4) == var5.getParagraphDirection(var4 - 1)) {
               var3 = var5.getOffsetForHorizontal(var4 - 1, var5.getPrimaryHorizontal(var3));
            } else {
               var3 = var5.getLineStart(var4 - 1);
            }

            this.mManager.setEndPos(var3);
            this.mManager.onCursorMoved();
         }

         return true;
      }
   }

   private class StyledTextConverter {
      private EditStyledText mEST;
      private StyledTextHtmlConverter mHtml;
      final EditStyledText this$0;

      public StyledTextConverter(EditStyledText var1, EditStyledText var2, StyledTextHtmlConverter var3) {
         this.this$0 = var1;
         this.mEST = var2;
         this.mHtml = var3;
      }

      public void SetHtml(String var1) {
         Spanned var2 = this.mHtml.fromHtml(var1, new Html.ImageGetter(this) {
            final StyledTextConverter this$1;

            {
               this.this$1 = var1;
            }

            public Drawable getDrawable(String var1) {
               StringBuilder var4 = new StringBuilder();
               var4.append("--- sethtml: src=");
               var4.append(var1);
               Log.d("EditStyledText", var4.toString());
               if (var1.startsWith("content://")) {
                  Uri var17 = Uri.parse(var1);

                  Exception var10000;
                  label42: {
                     label41: {
                        int var2;
                        int var3;
                        InputStream var5;
                        Bitmap var15;
                        boolean var10001;
                        label48: {
                           try {
                              System.gc();
                              var5 = this.this$1.mEST.getContext().getContentResolver().openInputStream(var17);
                              BitmapFactory.Options var13 = new BitmapFactory.Options();
                              var13.inJustDecodeBounds = true;
                              BitmapFactory.decodeStream(var5, (Rect)null, var13);
                              var5.close();
                              var5 = this.this$1.mEST.getContext().getContentResolver().openInputStream(var17);
                              var2 = var13.outWidth;
                              var3 = var13.outHeight;
                              if (var13.outWidth > this.this$1.this$0.getMaxImageWidthPx()) {
                                 var2 = this.this$1.this$0.getMaxImageWidthPx();
                                 var3 = this.this$1.this$0.getMaxImageWidthPx() * var3 / var13.outWidth;
                                 Rect var14 = new Rect(0, 0, var2, var3);
                                 var15 = BitmapFactory.decodeStream(var5, var14, (BitmapFactory.Options)null);
                                 break label48;
                              }
                           } catch (Exception var11) {
                              var10000 = var11;
                              var10001 = false;
                              break label42;
                           } catch (OutOfMemoryError var12) {
                              var10001 = false;
                              break label41;
                           }

                           try {
                              var15 = BitmapFactory.decodeStream(var5);
                           } catch (Exception var9) {
                              var10000 = var9;
                              var10001 = false;
                              break label42;
                           } catch (OutOfMemoryError var10) {
                              var10001 = false;
                              break label41;
                           }
                        }

                        try {
                           BitmapDrawable var6 = new BitmapDrawable(this.this$1.mEST.getContext().getResources(), var15);
                           var6.setBounds(0, 0, var2, var3);
                           var5.close();
                           return var6;
                        } catch (Exception var7) {
                           var10000 = var7;
                           var10001 = false;
                           break label42;
                        } catch (OutOfMemoryError var8) {
                           var10001 = false;
                        }
                     }

                     Log.e("EditStyledText", "OutOfMemoryError");
                     this.this$1.mEST.setHint(5);
                     return null;
                  }

                  Exception var16 = var10000;
                  StringBuilder var18 = new StringBuilder();
                  var18.append("--- set html: Failed to loaded content ");
                  var18.append(var17);
                  Log.e("EditStyledText", var18.toString(), var16);
                  return null;
               } else {
                  return null;
               }
            }
         }, (Html.TagHandler)null);
         this.mEST.setText(var2);
      }

      public String getHtml(boolean var1) {
         this.mEST.clearComposingText();
         this.mEST.onRefreshZeoWidthChar();
         String var3 = this.mHtml.toHtml(this.mEST.getText(), var1);
         StringBuilder var2 = new StringBuilder();
         var2.append("--- getHtml:");
         var2.append(var3);
         Log.d("EditStyledText", var2.toString());
         return var3;
      }

      public String getPreviewHtml() {
         this.mEST.clearComposingText();
         this.mEST.onRefreshZeoWidthChar();
         String var2 = this.mHtml.toHtml(this.mEST.getText(), true, this.this$0.getMaxImageWidthDip(), this.this$0.getPaddingScale());
         int var1 = this.mEST.getBackgroundColor();
         String var3 = String.format("<body bgcolor=\"#%02X%02X%02X\">%s</body>", Color.red(var1), Color.green(var1), Color.blue(var1), var2);
         StringBuilder var4 = new StringBuilder();
         var4.append("--- getPreviewHtml:");
         var4.append(var3);
         var4.append(",");
         var4.append(this.mEST.getWidth());
         Log.d("EditStyledText", var4.toString());
         return var3;
      }

      public void getUriArray(ArrayList var1, Editable var2) {
         var1.clear();
         Log.d("EditStyledText", "--- getUriArray:");
         int var5 = var2.length();

         int var4;
         for(int var3 = 0; var3 < var2.length(); var3 = var4) {
            var4 = var2.nextSpanTransition(var3, var5, ImageSpan.class);
            ImageSpan[] var7 = (ImageSpan[])var2.getSpans(var3, var4, ImageSpan.class);

            for(var3 = 0; var3 < var7.length; ++var3) {
               StringBuilder var6 = new StringBuilder();
               var6.append("--- getUriArray: foundArray");
               var6.append(var7[var3].getSource());
               Log.d("EditStyledText", var6.toString());
               var1.add(Uri.parse(var7[var3].getSource()));
            }
         }

      }

      public void setStyledTextHtmlConverter(StyledTextHtmlConverter var1) {
         this.mHtml = var1;
      }
   }

   private static class StyledTextDialog {
      private static final int TYPE_BACKGROUND = 1;
      private static final int TYPE_FOREGROUND = 0;
      private AlertDialog mAlertDialog;
      private CharSequence[] mAlignNames;
      private CharSequence mAlignTitle;
      private AlertDialog.Builder mBuilder;
      private CharSequence mColorDefaultMessage;
      private CharSequence[] mColorInts;
      private CharSequence[] mColorNames;
      private CharSequence mColorTitle;
      private EditStyledText mEST;
      private CharSequence[] mMarqueeNames;
      private CharSequence mMarqueeTitle;
      private CharSequence[] mSizeDisplayInts;
      private CharSequence[] mSizeNames;
      private CharSequence[] mSizeSendInts;
      private CharSequence mSizeTitle;

      public StyledTextDialog(EditStyledText var1) {
         this.mEST = var1;
      }

      private void buildAndShowColorDialogue(int var1, CharSequence var2, int[] var3) {
         int var6 = this.mEST.dipToPx(50);
         int var5 = this.mEST.dipToPx(2);
         int var4 = this.mEST.dipToPx(15);
         this.mBuilder.setTitle(var2);
         this.mBuilder.setIcon(0);
         this.mBuilder.setPositiveButton((CharSequence)null, (DialogInterface.OnClickListener)null);
         this.mBuilder.setNegativeButton(17039360, new DialogInterface.OnClickListener(this) {
            final StyledTextDialog this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               this.this$0.mEST.onStartEdit();
            }
         });
         this.mBuilder.setItems((CharSequence[])null, (DialogInterface.OnClickListener)null);
         LinearLayout var7 = new LinearLayout(this.mEST.getContext());
         var7.setOrientation(1);
         var7.setGravity(1);
         var7.setPadding(var4, var4, var4, var4);
         LinearLayout var9 = null;

         for(var4 = 0; var4 < var3.length; ++var4) {
            if (var4 % 5 == 0) {
               var9 = new LinearLayout(this.mEST.getContext());
               var7.addView(var9);
            }

            Button var8 = new Button(this.mEST.getContext());
            var8.setHeight(var6);
            var8.setWidth(var6);
            var8.setBackgroundDrawable(new ColorPaletteDrawable(var3[var4], var6, var6, var5));
            var8.setDrawingCacheBackgroundColor(var3[var4]);
            if (var1 == 0) {
               var8.setOnClickListener(new OnClickListener(this) {
                  final StyledTextDialog this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void onClick(View var1) {
                     this.this$0.mEST.setItemColor(var1.getDrawingCacheBackgroundColor());
                     if (this.this$0.mAlertDialog != null) {
                        this.this$0.mAlertDialog.setView((View)null);
                        this.this$0.mAlertDialog.dismiss();
                        this.this$0.mAlertDialog = null;
                     } else {
                        Log.e("EditStyledText", "--- buildAndShowColorDialogue: can't find alertDialog");
                     }

                  }
               });
            } else if (var1 == 1) {
               var8.setOnClickListener(new OnClickListener(this) {
                  final StyledTextDialog this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void onClick(View var1) {
                     this.this$0.mEST.setBackgroundColor(var1.getDrawingCacheBackgroundColor());
                     if (this.this$0.mAlertDialog != null) {
                        this.this$0.mAlertDialog.setView((View)null);
                        this.this$0.mAlertDialog.dismiss();
                        this.this$0.mAlertDialog = null;
                     } else {
                        Log.e("EditStyledText", "--- buildAndShowColorDialogue: can't find alertDialog");
                     }

                  }
               });
            }

            var9.addView(var8);
         }

         if (var1 == 1) {
            this.mBuilder.setPositiveButton(this.mColorDefaultMessage, new DialogInterface.OnClickListener(this) {
               final StyledTextDialog this$0;

               {
                  this.this$0 = var1;
               }

               public void onClick(DialogInterface var1, int var2) {
                  this.this$0.mEST.setBackgroundColor(16777215);
               }
            });
         } else if (var1 == 0) {
            this.mBuilder.setPositiveButton(this.mColorDefaultMessage, new DialogInterface.OnClickListener(this) {
               final StyledTextDialog this$0;

               {
                  this.this$0 = var1;
               }

               public void onClick(DialogInterface var1, int var2) {
                  this.this$0.mEST.setItemColor(-16777216);
               }
            });
         }

         this.mBuilder.setView(var7);
         this.mBuilder.setCancelable(true);
         this.mBuilder.setOnCancelListener(new DialogInterface.OnCancelListener(this) {
            final StyledTextDialog this$0;

            {
               this.this$0 = var1;
            }

            public void onCancel(DialogInterface var1) {
               this.this$0.mEST.onStartEdit();
            }
         });
         this.mAlertDialog = this.mBuilder.show();
      }

      private void buildDialogue(CharSequence var1, CharSequence[] var2, DialogInterface.OnClickListener var3) {
         this.mBuilder.setTitle(var1);
         this.mBuilder.setIcon(0);
         this.mBuilder.setPositiveButton((CharSequence)null, (DialogInterface.OnClickListener)null);
         this.mBuilder.setNegativeButton(17039360, new DialogInterface.OnClickListener(this) {
            final StyledTextDialog this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               this.this$0.mEST.onStartEdit();
            }
         });
         this.mBuilder.setItems(var2, var3);
         this.mBuilder.setView((View)null);
         this.mBuilder.setCancelable(true);
         this.mBuilder.setOnCancelListener(new DialogInterface.OnCancelListener(this) {
            final StyledTextDialog this$0;

            {
               this.this$0 = var1;
            }

            public void onCancel(DialogInterface var1) {
               Log.d("EditStyledText", "--- oncancel");
               this.this$0.mEST.onStartEdit();
            }
         });
         this.mBuilder.show();
      }

      private boolean checkAlignAlertParams() {
         Log.d("EditStyledText", "--- checkAlignAlertParams");
         if (this.mBuilder == null) {
            Log.e("EditStyledText", "--- builder is null.");
            return false;
         } else if (this.mAlignTitle == null) {
            Log.e("EditStyledText", "--- align alert params are null.");
            return false;
         } else {
            return true;
         }
      }

      private boolean checkColorAlertParams() {
         Log.d("EditStyledText", "--- checkParams");
         if (this.mBuilder == null) {
            Log.e("EditStyledText", "--- builder is null.");
            return false;
         } else {
            if (this.mColorTitle != null) {
               CharSequence[] var2 = this.mColorNames;
               if (var2 != null) {
                  CharSequence[] var1 = this.mColorInts;
                  if (var1 != null) {
                     if (var2.length != var1.length) {
                        Log.e("EditStyledText", "--- the length of color alert params are different.");
                        return false;
                     }

                     return true;
                  }
               }
            }

            Log.e("EditStyledText", "--- color alert params are null.");
            return false;
         }
      }

      private boolean checkMarqueeAlertParams() {
         Log.d("EditStyledText", "--- checkMarqueeAlertParams");
         if (this.mBuilder == null) {
            Log.e("EditStyledText", "--- builder is null.");
            return false;
         } else if (this.mMarqueeTitle == null) {
            Log.e("EditStyledText", "--- Marquee alert params are null.");
            return false;
         } else {
            return true;
         }
      }

      private boolean checkSizeAlertParams() {
         Log.d("EditStyledText", "--- checkParams");
         if (this.mBuilder == null) {
            Log.e("EditStyledText", "--- builder is null.");
            return false;
         } else {
            if (this.mSizeTitle != null) {
               CharSequence[] var3 = this.mSizeNames;
               if (var3 != null) {
                  CharSequence[] var1 = this.mSizeDisplayInts;
                  if (var1 != null) {
                     CharSequence[] var2 = this.mSizeSendInts;
                     if (var2 != null) {
                        if (var3.length != var1.length && var2.length != var1.length) {
                           Log.e("EditStyledText", "--- the length of size alert params are different.");
                           return false;
                        }

                        return true;
                     }
                  }
               }
            }

            Log.e("EditStyledText", "--- size alert params are null.");
            return false;
         }
      }

      private void onShowAlignAlertDialog() {
         Log.d("EditStyledText", "--- onShowAlignAlertDialog");
         if (this.checkAlignAlertParams()) {
            this.buildDialogue(this.mAlignTitle, this.mAlignNames, new DialogInterface.OnClickListener(this) {
               final StyledTextDialog this$0;

               {
                  this.this$0 = var1;
               }

               public void onClick(DialogInterface var1, int var2) {
                  Alignment var3 = Alignment.ALIGN_NORMAL;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 != 2) {
                           Log.e("EditStyledText", "--- onShowAlignAlertDialog: got illigal align.");
                        } else {
                           var3 = Alignment.ALIGN_OPPOSITE;
                        }
                     } else {
                        var3 = Alignment.ALIGN_CENTER;
                     }
                  } else {
                     var3 = Alignment.ALIGN_NORMAL;
                  }

                  this.this$0.mEST.setAlignment(var3);
               }
            });
         }
      }

      private void onShowBackgroundColorAlertDialog() {
         Log.d("EditStyledText", "--- onShowBackgroundColorAlertDialog");
         if (this.checkColorAlertParams()) {
            int[] var2 = new int[this.mColorInts.length];

            for(int var1 = 0; var1 < var2.length; ++var1) {
               var2[var1] = Integer.parseInt((String)this.mColorInts[var1], 16) - 16777216;
            }

            this.buildAndShowColorDialogue(1, this.mColorTitle, var2);
         }
      }

      private void onShowForegroundColorAlertDialog() {
         Log.d("EditStyledText", "--- onShowForegroundColorAlertDialog");
         if (this.checkColorAlertParams()) {
            int[] var2 = new int[this.mColorInts.length];

            for(int var1 = 0; var1 < var2.length; ++var1) {
               var2[var1] = Integer.parseInt((String)this.mColorInts[var1], 16) - 16777216;
            }

            this.buildAndShowColorDialogue(0, this.mColorTitle, var2);
         }
      }

      private void onShowMarqueeAlertDialog() {
         Log.d("EditStyledText", "--- onShowMarqueeAlertDialog");
         if (this.checkMarqueeAlertParams()) {
            this.buildDialogue(this.mMarqueeTitle, this.mMarqueeNames, new DialogInterface.OnClickListener(this) {
               final StyledTextDialog this$0;

               {
                  this.this$0 = var1;
               }

               public void onClick(DialogInterface var1, int var2) {
                  StringBuilder var3 = new StringBuilder();
                  var3.append("mBuilder.onclick:");
                  var3.append(var2);
                  Log.d("EditStyledText", var3.toString());
                  this.this$0.mEST.setMarquee(var2);
               }
            });
         }
      }

      private void onShowSizeAlertDialog() {
         Log.d("EditStyledText", "--- onShowSizeAlertDialog");
         if (this.checkSizeAlertParams()) {
            this.buildDialogue(this.mSizeTitle, this.mSizeNames, new DialogInterface.OnClickListener(this) {
               final StyledTextDialog this$0;

               {
                  this.this$0 = var1;
               }

               public void onClick(DialogInterface var1, int var2) {
                  StringBuilder var3 = new StringBuilder();
                  var3.append("mBuilder.onclick:");
                  var3.append(var2);
                  Log.d("EditStyledText", var3.toString());
                  var2 = this.this$0.mEST.dipToPx(Integer.parseInt((String)this.this$0.mSizeDisplayInts[var2]));
                  this.this$0.mEST.setItemSize(var2);
               }
            });
         }
      }

      public void setAlignAlertParams(CharSequence var1, CharSequence[] var2) {
         this.mAlignTitle = var1;
         this.mAlignNames = var2;
      }

      public void setBuilder(AlertDialog.Builder var1) {
         this.mBuilder = var1;
      }

      public void setColorAlertParams(CharSequence var1, CharSequence[] var2, CharSequence[] var3, CharSequence var4) {
         this.mColorTitle = var1;
         this.mColorNames = var2;
         this.mColorInts = var3;
         this.mColorDefaultMessage = var4;
      }

      public void setMarqueeAlertParams(CharSequence var1, CharSequence[] var2) {
         this.mMarqueeTitle = var1;
         this.mMarqueeNames = var2;
      }

      public void setSizeAlertParams(CharSequence var1, CharSequence[] var2, CharSequence[] var3, CharSequence[] var4) {
         this.mSizeTitle = var1;
         this.mSizeNames = var2;
         this.mSizeDisplayInts = var3;
         this.mSizeSendInts = var4;
      }
   }

   public interface StyledTextHtmlConverter {
      Spanned fromHtml(String var1);

      Spanned fromHtml(String var1, Html.ImageGetter var2, Html.TagHandler var3);

      String toHtml(Spanned var1);

      String toHtml(Spanned var1, boolean var2);

      String toHtml(Spanned var1, boolean var2, int var3, float var4);
   }

   private class StyledTextHtmlStandard implements StyledTextHtmlConverter {
      final EditStyledText this$0;

      private StyledTextHtmlStandard(EditStyledText var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      StyledTextHtmlStandard(EditStyledText var1, Object var2) {
         this(var1);
      }

      public Spanned fromHtml(String var1) {
         return Html.fromHtml(var1);
      }

      public Spanned fromHtml(String var1, Html.ImageGetter var2, Html.TagHandler var3) {
         return Html.fromHtml(var1, var2, var3);
      }

      public String toHtml(Spanned var1) {
         return Html.toHtml(var1);
      }

      public String toHtml(Spanned var1, boolean var2) {
         return Html.toHtml(var1);
      }

      public String toHtml(Spanned var1, boolean var2, int var3, float var4) {
         return Html.toHtml(var1);
      }
   }

   public static class StyledTextInputConnection extends InputConnectionWrapper {
      EditStyledText mEST;

      public StyledTextInputConnection(InputConnection var1, EditStyledText var2) {
         super(var1, true);
         this.mEST = var2;
      }

      public boolean commitText(CharSequence var1, int var2) {
         Log.d("EditStyledText", "--- commitText:");
         this.mEST.mManager.unsetTextComposingMask();
         return super.commitText(var1, var2);
      }

      public boolean finishComposingText() {
         Log.d("EditStyledText", "--- finishcomposing:");
         if (!this.mEST.isSoftKeyBlocked() && !this.mEST.isButtonsFocused() && !this.mEST.isEditting()) {
            this.mEST.onEndEdit();
         }

         return super.finishComposingText();
      }
   }
}
