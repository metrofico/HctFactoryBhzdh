package com.hzbhd.canbus.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.entity.SyncListUpdateEntity;
import com.hzbhd.canbus.entity.SyncSoftKeyUpdateEntity;
import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.interfaces.OnSyncStateChangeListener;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.view.SyncBtnItemView;
import com.hzbhd.canbus.view.SyncInfoListView;
import com.hzbhd.canbus.view.SyncKeyBoardView;
import com.hzbhd.canbus.view.SyncLeftIconView;
import com.hzbhd.canbus.view.SyncListItemView;
import com.hzbhd.canbus.view.SyncSoftKeyView;
import com.hzbhd.canbus.view.SyncTopIconIconView;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SyncActivity extends AbstractBaseActivity implements SyncLeftIconView.OnLeftIconClickListener, SyncInfoListView.OnListItemClickListener, SyncSoftKeyView.OnSoftKeyClickListener, SyncKeyBoardView.OnKeyBoardBtnClickListener {
   private static final int MSG_EMPTY_TV_TIME = 11;
   private static final int MSG_REFRESH_TV_TIME = 10;
   private static final String TAG = "SyncActivity";
   private final long EMPTY_TV_TIME_DELAY = 5000L;
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final SyncActivity this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         int var2 = var1.what;
         if (var2 != 10) {
            if (var2 == 11) {
               this.this$0.mTvPlayTime.setText("");
            }
         } else {
            this.this$0.mTvPlayTime.setText(GeneralSyncData.mSyncTime);
         }

      }
   };
   private String[][] mKeyboardActions;
   private String[] mLeftIconActionArray;
   private LinearLayout mLlScreemNumber;
   private OnSyncItemClickListener mOnSyncItemClickListener;
   private OnSyncStateChangeListener mOnSyncStateChangeListener;
   private SyncPageUiSet mSet;
   private SyncInfoListView mSyncInfoListView;
   private SyncKeyBoardView mSyncKeyBoardView;
   private SyncLeftIconView mSyncLeftIconView;
   private SyncSoftKeyView mSyncSoftKeyView;
   private SyncTopIconIconView mSyncTopIconIconView;
   private TextView mTvPlayTime;
   private TextView mTvScreemNumber;

   private void findView() {
      this.mSyncLeftIconView = (SyncLeftIconView)this.findViewById(2131362257);
      this.mSyncTopIconIconView = (SyncTopIconIconView)this.findViewById(2131363484);
      this.mSyncInfoListView = (SyncInfoListView)this.findViewById(2131362258);
      this.mSyncSoftKeyView = (SyncSoftKeyView)this.findViewById(2131362259);
      this.mTvPlayTime = (TextView)this.findViewById(2131363622);
      this.mSyncKeyBoardView = (SyncKeyBoardView)this.findViewById(2131362256);
      this.mLlScreemNumber = (LinearLayout)this.findViewById(2131362801);
      this.mTvScreemNumber = (TextView)this.findViewById(2131363691);
   }

   private void initData() {
      SyncPageUiSet var1 = UiMgrFactory.getCanUiMgr(this).getSyncPageUiSet(this);
      this.mSet = var1;
      this.mLeftIconActionArray = var1.getLeftBtnActions();
      this.mOnSyncStateChangeListener = this.mSet.getOnSyncStateChangeListener();
      this.mOnSyncItemClickListener = this.mSet.getOnSyncItemClickListener();
      this.mKeyboardActions = this.mSet.getKeyboardActions();
   }

   private void initView() {
      this.mSyncLeftIconView.initButton(this, this.mLeftIconActionArray, this);
      this.mSyncTopIconIconView.initIcon(this, GeneralSyncData.mSyncTopIconCount);
      this.mSyncInfoListView.initItem(this, 5, this);
      this.mSyncSoftKeyView.initButton(this, 4, this);
      this.mSyncKeyBoardView.initKeyBoard(this, this.mKeyboardActions, this);
      this.setVisible(this.mLlScreemNumber, this.mSet.isShowScreemNumber());
   }

   private void releaseAudioChannel() {
      CommUtil.releaseAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main);
   }

   private void requestAudioChannel() {
      CommUtil.requestAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main, (Bundle)null);
   }

   private void setVisibilityGone(View var1, boolean var2) {
      if (var2) {
         var1.setVisibility(0);
      } else {
         var1.setVisibility(8);
      }

   }

   private void setVisible(View var1, boolean var2) {
      if (var2) {
         var1.setVisibility(0);
      } else {
         var1.setVisibility(4);
      }

   }

   // $FF: synthetic method
   void lambda$onCreate$0$com_hzbhd_canbus_activity_SyncActivity() {
      this.initView();
      this.refreshUi((Bundle)null);
   }

   public void onBackPressed() {
      super.onBackPressed();
      this.releaseAudioChannel();
   }

   public void onBtnClick(String var1) {
      OnSyncItemClickListener var2 = this.mOnSyncItemClickListener;
      if (var2 != null) {
         var2.onKeyboardBtnClick(var1);
      }

   }

   public void onBtnLongClick(String var1) {
      OnSyncItemClickListener var2 = this.mOnSyncItemClickListener;
      if (var2 != null) {
         var2.onKeyboardBtnLongClick(var1);
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558696);
      this.findView();
      this.initData();
      this.mHandler.post(new SyncActivity$$ExternalSyntheticLambda0(this));
   }

   public void onIconClick(String var1) {
      OnSyncItemClickListener var2 = this.mOnSyncItemClickListener;
      if (var2 != null) {
         var2.onLeftIconClick(var1);
      }

   }

   public void onItemClick(int var1) {
      OnSyncItemClickListener var2 = this.mOnSyncItemClickListener;
      if (var2 != null) {
         var2.onListItemClick(var1);
      }

   }

   protected void onResume() {
      super.onResume();
      this.requestAudioChannel();
      OnSyncStateChangeListener var1 = this.mOnSyncStateChangeListener;
      if (var1 != null) {
         var1.onResume();
      }

   }

   public void onSoftKeyClick(int var1) {
      OnSyncItemClickListener var2 = this.mOnSyncItemClickListener;
      if (var2 != null) {
         var2.onSoftKeyClick(var1);
      }

   }

   protected void onStop() {
      super.onStop();
      OnSyncStateChangeListener var1 = this.mOnSyncStateChangeListener;
      if (var1 != null) {
         var1.onStop();
      }

   }

   public void refreshUi(Bundle var1) {
      Log.i("SyncActivity", "refreshUi: in");
      int var2;
      if (!ArrayUtils.isEmpty(GeneralSyncData.mSyncTopIconResIdArray)) {
         for(var2 = 0; var2 < GeneralSyncData.mSyncTopIconCount; ++var2) {
            try {
               this.mSyncTopIconIconView.getItem(var2).setImageResource(GeneralSyncData.mSyncTopIconResIdArray[var2]);
            } catch (NullPointerException | IndexOutOfBoundsException var6) {
               Log.i("SyncActivity", "refreshUi: count:" + GeneralSyncData.mSyncTopIconCount + ", length:" + GeneralSyncData.mSyncTopIconResIdArray.length);
            }
         }
      }

      List var7 = GeneralSyncData.mInfoUpdateEntityList;
      boolean var3;
      Iterator var5;
      SyncListItemView var8;
      if (!ArrayUtils.isEmpty(var7)) {
         for(var5 = var7.iterator(); var5.hasNext(); var8.setSelected(var3)) {
            SyncListUpdateEntity var4 = (SyncListUpdateEntity)var5.next();
            var8 = this.mSyncInfoListView.getItem(var4.getIndex());
            var8.setItem(var4.getLeftIconResId(), var4.getInfo(), var4.getRightIconResId());
            var8.setEnabled(var4.isEnable());
            if (var4.getIndex() == GeneralSyncData.mSelectedLineIndex) {
               var3 = true;
            } else {
               var3 = false;
            }
         }
      }

      var7 = GeneralSyncData.mSoftKeyUpdateEntityList;
      if (!ArrayUtils.isEmpty(var7)) {
         var5 = var7.iterator();

         while(var5.hasNext()) {
            SyncSoftKeyUpdateEntity var9 = (SyncSoftKeyUpdateEntity)var5.next();
            SyncBtnItemView var10 = this.mSyncSoftKeyView.getItem(var9.getIndex());
            var10.setText(var9.getName());
            var10.setImageResource(var9.getImageResId());
            var10.setSelected(var9.isSelected());
            var10.setVisible(var9.isVisible());
         }
      }

      if (GeneralSyncData.mIsSyncTimeChange) {
         GeneralSyncData.mIsSyncTimeChange = false;
         this.mHandler.removeMessages(11);
         this.mHandler.sendEmptyMessage(10);
         this.mHandler.sendEmptyMessageDelayed(11, 5000L);
      }

      for(var2 = 0; var2 < 5; ++var2) {
         var8 = this.mSyncInfoListView.getItem(var2);
         if (var2 < GeneralSyncData.mInfoLineShowAmount) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.setVisibilityGone(var8, var3);
      }

      this.setVisibilityGone(this.mSyncSoftKeyView, GeneralSyncData.mIsShowSoftKey);
      if (!Arrays.equals(this.mKeyboardActions, this.mSet.getKeyboardActions())) {
         this.mKeyboardActions = this.mSet.getKeyboardActions();
         this.mSyncKeyBoardView.removeAllViews();
         this.mSyncKeyBoardView.rebuild(this, this.mKeyboardActions);
      }

      this.mTvScreemNumber.setText(Integer.toString(GeneralSyncData.mSyncScreemNumber));
   }
}
