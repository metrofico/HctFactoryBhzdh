package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.interfaces.OnSyncStateChangeListener;
import kotlin.Metadata;

@Metadata(
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R*\u0010\b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006\""},
   d2 = {"Lcom/hzbhd/canbus/ui_set/SyncPageUiSet;", "", "()V", "isShowScreemNumber", "", "()Z", "setShowScreemNumber", "(Z)V", "keyboardActions", "", "", "getKeyboardActions", "()[[Ljava/lang/String;", "setKeyboardActions", "([[Ljava/lang/String;)V", "[[Ljava/lang/String;", "leftBtnActions", "getLeftBtnActions", "()[Ljava/lang/String;", "setLeftBtnActions", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "onSyncItemClickListener", "Lcom/hzbhd/canbus/interfaces/OnSyncItemClickListener;", "getOnSyncItemClickListener", "()Lcom/hzbhd/canbus/interfaces/OnSyncItemClickListener;", "setOnSyncItemClickListener", "(Lcom/hzbhd/canbus/interfaces/OnSyncItemClickListener;)V", "onSyncStateChangeListener", "Lcom/hzbhd/canbus/interfaces/OnSyncStateChangeListener;", "getOnSyncStateChangeListener", "()Lcom/hzbhd/canbus/interfaces/OnSyncStateChangeListener;", "setOnSyncStateChangeListener", "(Lcom/hzbhd/canbus/interfaces/OnSyncStateChangeListener;)V", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class SyncPageUiSet {
   private boolean isShowScreemNumber;
   private String[][] keyboardActions;
   private String[] leftBtnActions;
   private OnSyncItemClickListener onSyncItemClickListener;
   private OnSyncStateChangeListener onSyncStateChangeListener;

   public final String[][] getKeyboardActions() {
      return this.keyboardActions;
   }

   public final String[] getLeftBtnActions() {
      return this.leftBtnActions;
   }

   public final OnSyncItemClickListener getOnSyncItemClickListener() {
      return this.onSyncItemClickListener;
   }

   public final OnSyncStateChangeListener getOnSyncStateChangeListener() {
      return this.onSyncStateChangeListener;
   }

   public final boolean isShowScreemNumber() {
      return this.isShowScreemNumber;
   }

   public final void setKeyboardActions(String[][] var1) {
      this.keyboardActions = var1;
   }

   public final void setLeftBtnActions(String[] var1) {
      this.leftBtnActions = var1;
   }

   public final void setOnSyncItemClickListener(OnSyncItemClickListener var1) {
      this.onSyncItemClickListener = var1;
   }

   public final void setOnSyncStateChangeListener(OnSyncStateChangeListener var1) {
      this.onSyncStateChangeListener = var1;
   }

   public final void setShowScreemNumber(boolean var1) {
      this.isShowScreemNumber = var1;
   }
}
