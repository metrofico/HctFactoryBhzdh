package com.hzbhd.canbus.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.adapter.OriginalBottomBtnLvAdapter;
import com.hzbhd.canbus.adapter.OriginalDataLvItemAdapter;
import com.hzbhd.canbus.adapter.SongListLvAdapter;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.view.OriginalTopBtnItemView;
import com.hzbhd.canbus.view.RowTopBtnView;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class OriginalCarDeviceActivity extends AbstractBaseActivity implements View.OnClickListener, SongListLvAdapter.SongItemClickInterface {
   private static final String TAG = "OriginalCarDeviceActivity";
   private RecyclerView mBottomBtnRv;
   private TextView mCdStatusTv;
   private ImageView mCoverIv;
   private TextView mDiscStatusTv;
   private DividerItemDecoration mDividerItemDecoration;
   private List mIdealList;
   private List mList;
   private OnOriginalCarDeviceBackPressedListener mOnOriginalCarDeviceBackPressedListener;
   private OriginalBottomBtnLvAdapter mOriginalBottomBtnLvAdapter;
   private OriginalDataLvItemAdapter mOriginalDataLvItemAdapter;
   private ProgressBar mPb;
   private TextView mPlayTimeTv;
   private RelativeLayout mRlTimeProgress;
   private String[] mRowBottomBtnAction;
   private String[] mRowBtnAction;
   private TextView mRunStateTv;
   private RecyclerView mRv;
   private List mSecondaryList;
   private OriginalCarDevicePageUiSet mSet;
   private ImageView mShowSongListIv;
   private List mSongList;
   private SongListLvAdapter mSongListLvAdapter;
   private RecyclerView mSongListRv;
   private int mSpanCount;
   private RelativeLayout mTopLayout;
   private RowTopBtnView mTopRbv;
   private TextView mTotalTimeTv;

   private void buildMayChangeViews() {
      String[] var1 = this.mSet.getRowTopBtnAction();
      this.mRowBtnAction = var1;
      if (ArrayUtils.isEmpty(var1)) {
         this.mTopLayout.setVisibility(8);
      } else {
         this.mTopLayout.setVisibility(0);
         this.mTopRbv.clean();
         this.mTopRbv.initButton(this, this.mRowBtnAction, this.mSet.isTopBtnCanScroll(), this.mSet.getOnOriginalTopBtnClickListeners());
      }

      var1 = this.mSet.getRowBottomBtnAction();
      this.mRowBottomBtnAction = var1;
      if (ArrayUtils.isEmpty(var1)) {
         this.mBottomBtnRv.setVisibility(8);
      } else {
         this.mBottomBtnRv.setVisibility(0);
         var1 = this.mRowBottomBtnAction;
         this.mSpanCount = var1.length;
         List var2 = Arrays.asList(var1);
         if (this.getResources().getConfiguration().orientation == 1) {
            var2 = this.getDisplayList();
         }

         OriginalBottomBtnLvAdapter var3 = new OriginalBottomBtnLvAdapter(this, this.mRowBottomBtnAction, var2, this.mSet.getOnOriginalBottomBtnClickListeners());
         this.mOriginalBottomBtnLvAdapter = var3;
         this.mBottomBtnRv.setAdapter(var3);
         GridLayoutManager var4 = new GridLayoutManager(this, this.mSpanCount, 1, false);
         this.mBottomBtnRv.setLayoutManager(var4);
         this.mOriginalBottomBtnLvAdapter.notifyDataSetChanged();
      }

      LinearLayoutManager var5;
      if (this.mSet.isHaveSongList()) {
         this.mShowSongListIv.setVisibility(0);
         this.mSongList = new ArrayList();
         this.mSongListLvAdapter = new SongListLvAdapter(this.mSongList, this, this.mSet.isSongListShowIndex());
         var5 = new LinearLayoutManager(this);
         if (this.mSongListRv.getLayoutManager() == null) {
            this.mSongListRv.setLayoutManager(var5);
         }

         if (this.mDividerItemDecoration == null) {
            this.mDividerItemDecoration = new DividerItemDecoration(this, 1);
         }

         this.mDividerItemDecoration.setDrawable(this.getResources().getDrawable(2131234802, (Resources.Theme)null));
         if (this.mSongListRv.getItemDecorationCount() == 0) {
            this.mSongListRv.addItemDecoration(this.mDividerItemDecoration);
         }

         this.mSongListRv.setAdapter(this.mSongListLvAdapter);
      } else {
         this.mShowSongListIv.setVisibility(4);
         this.mSongListRv.setVisibility(4);
      }

      if (this.mSet.isHavePlayTimeSeekBar()) {
         this.mRlTimeProgress.setVisibility(0);
      } else {
         this.mRlTimeProgress.setVisibility(4);
      }

      this.mList = this.mSet.getItems();
      this.mOriginalDataLvItemAdapter = new OriginalDataLvItemAdapter(this, this.mList);
      var5 = new LinearLayoutManager(this);
      this.mRv.setLayoutManager(var5);
      this.mRv.setAdapter(this.mOriginalDataLvItemAdapter);
   }

   private void findViews() {
      this.mTopLayout = (RelativeLayout)this.findViewById(2131363553);
      this.mRlTimeProgress = (RelativeLayout)this.findViewById(2131363203);
      this.mBottomBtnRv = (RecyclerView)this.findViewById(2131363209);
      this.mRv = (RecyclerView)this.findViewById(2131363212);
      this.mPlayTimeTv = (TextView)this.findViewById(2131363668);
      this.mPb = (ProgressBar)this.findViewById(2131362970);
      this.mTotalTimeTv = (TextView)this.findViewById(2131363711);
      this.mCdStatusTv = (TextView)this.findViewById(2131363599);
      this.mDiscStatusTv = (TextView)this.findViewById(2131363600);
      this.mRunStateTv = (TextView)this.findViewById(2131363687);
      this.mCoverIv = (ImageView)this.findViewById(2131362559);
      this.mSongListRv = (RecyclerView)this.findViewById(2131363225);
      this.mShowSongListIv = (ImageView)this.findViewById(2131362643);
   }

   private OriginalTopBtnItemView getBtnTopItemView(int var1) {
      return this.mTopRbv.getBtnItemView(var1);
   }

   private List getDisplayList() {
      ArrayList var4 = new ArrayList();
      ArrayList var3 = new ArrayList();
      String[] var6 = this.mRowBottomBtnAction;
      int var2 = var6.length;

      for(int var1 = 0; var1 < var2; ++var1) {
         String var5 = var6[var1];
         if (this.mIdealList.contains(var5)) {
            var4.add(var5);
         } else {
            var3.add(var5);
         }
      }

      if (var4.size() > 6) {
         ArrayList var8 = new ArrayList();
         Iterator var9 = var4.iterator();

         while(var9.hasNext()) {
            String var7 = (String)var9.next();
            if (this.mSecondaryList.contains(var7)) {
               var8.add(var7);
            }
         }

         var9 = var8.iterator();

         while(var9.hasNext()) {
            var4.remove((String)var9.next());
         }

         var3.addAll(var8);
      }

      this.mSpanCount = var4.size();
      var4.addAll(var3);
      return var4;
   }

   private void initData() {
      this.mIdealList = Arrays.asList("up", "left", "prev_disc", "play_pause", "play", "pause", "stop", "next_disc", "right", "down");
      this.mSecondaryList = Arrays.asList("prev_disc", "play_pause", "play", "pause", "stop", "next_disc");
   }

   private void initViews() {
      OriginalCarDevicePageUiSet var1 = this.getUiMgrInterface(this).getOriginalCarDevicePageUiSet(this);
      this.mSet = var1;
      if (var1 == null) {
         this.runOnUiThread(new Runnable(this) {
            final OriginalCarDeviceActivity this$0;

            {
               this.this$0 = var1;
            }

            public void run() {
               this.this$0.finish();
            }
         });
      } else {
         View var2;
         if (var1.isTopBtnCanScroll()) {
            var2 = LayoutInflater.from(this).inflate(2131558787, (ViewGroup)null);
         } else {
            var2 = LayoutInflater.from(this).inflate(2131558788, (ViewGroup)null);
         }

         this.mTopLayout.addView(var2);
         this.mTopRbv = (RowTopBtnView)this.findViewById(2131362805);
         this.mCoverIv.setVisibility(8);
         OnOriginalCarDevicePageStatusListener var3 = this.mSet.getOnOriginalCarDevicePageStatusListener();
         if (var3 != null) {
            var3.onStatusChange(0);
         }

         this.mOnOriginalCarDeviceBackPressedListener = this.mSet.getOnOriginalCarDeviceBackPressedListener();
         this.buildMayChangeViews();
         this.refreshUi((Bundle)null);
      }
   }

   private void releaseAudioChannel() {
      CommUtil.releaseAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main);
   }

   private void requestAudioChannel() {
      CommUtil.requestAudioChannel(SourceConstantsDef.SOURCE_ID.CAN, SourceConstantsDef.DISP_ID.disp_main, (Bundle)null);
   }

   public void onBackPressed() {
      super.onBackPressed();
      this.releaseAudioChannel();
      OnOriginalCarDeviceBackPressedListener var1 = this.mOnOriginalCarDeviceBackPressedListener;
      if (var1 != null) {
         var1.onBackPressed();
      }

   }

   public void onClick(View var1) {
      if (var1.getId() == 2131362643) {
         if (this.mSongListRv.getVisibility() == 8) {
            this.mSongListRv.setVisibility(0);
         } else {
            this.mSongListRv.setVisibility(8);
         }
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558687);
      this.findViews();
   }

   protected void onResume() {
      super.onResume();
      this.requestAudioChannel();
      this.initData();
      this.initViews();
      this.refreshUi((Bundle)null);
   }

   public void onSongItemClick(int var1) {
      OnOriginalSongItemClickListener var2 = this.mSet.getOnOriginalSongItemClickListener();
      if (var2 != null) {
         var2.onSongItemClick(var1);
      }

   }

   public void onSongItemLongClick(int var1) {
      OnOriginalSongItemClickListener var2 = this.mSet.getOnOriginalSongItemClickListener();
      if (var2 != null) {
         var2.onSongItemLongClick(var1);
      }

   }

   public void refreshUi(Bundle var1) {
      if (this.isShouldRefreshUi()) {
         if (var1 != null && var1.containsKey("bundle_key_orinal_init_view") && var1.getBoolean("bundle_key_orinal_init_view")) {
            this.buildMayChangeViews();
         }

         String[] var5 = this.mRowBtnAction;
         byte var4 = 0;
         if (var5 != null && GeneralOriginalCarDeviceData.isTopBtnChange) {
            int var3 = 0;

            while(true) {
               var5 = this.mRowBtnAction;
               if (var3 >= var5.length) {
                  break;
               }

               String var6 = var5[var3];
               var6.hashCode();
               switch (var6) {
                  case "select_cd":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.select_cd);
                     break;
                  case "rdm_disc":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.rdm_disc);
                     break;
                  case "rdm_fold":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.rdm_fold);
                     break;
                  case "auto_p":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.auto_p);
                     break;
                  case "exit_cd":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.exit_cd);
                     break;
                  case "folder":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.folder);
                     break;
                  case "insert":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.insert);
                     break;
                  case "rear_cdc":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.rear_cdc);
                     break;
                  case "prev_disc":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.prev_disc);
                     break;
                  case "disc_scan":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.disc_scan);
                     break;
                  case "cd":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.cd);
                     break;
                  case "st":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.st);
                     break;
                  case "dvd":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.dvd);
                     break;
                  case "mp3":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.mp3);
                     break;
                  case "rdm":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.rdm);
                     break;
                  case "rds":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.rds);
                     break;
                  case "rpt":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.rpt);
                     break;
                  case "wma":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.wma);
                     break;
                  case "lock":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.lock);
                     break;
                  case "scan":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.scan);
                     break;
                  case "am_st":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.am_st);
                     break;
                  case "power":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.power);
                     break;
                  case "radio":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.radio);
                     break;
                  case "scane":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.scane);
                     break;
                  case "fold_add":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.fold_add);
                     break;
                  case "fold_sub":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.fold_sub);
                     break;
                  case "rpt_track":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.rpt_track);
                     break;
                  case "rpt_disc":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.rpt_disc);
                     break;
                  case "rpt_fold":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.rpt_fold);
                     break;
                  case "preset_select":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.preset_select);
                     break;
                  case "rdm_off":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.rdm_off);
                     break;
                  case "refresh":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.refresh);
                     break;
                  case "preset_store":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.preset_store);
                     break;
                  case "next_disc":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.next_disc);
                     break;
                  case "rpt_off":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.rpt_off);
                     break;
                  case "aux_insert":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.aux_insert);
                     break;
                  case "play_pause":
                     this.getBtnTopItemView(var3).turn(GeneralOriginalCarDeviceData.play_pause);
               }

               ++var3;
            }
         }

         List var7 = GeneralOriginalCarDeviceData.mList;
         if (var7 != null) {
            for(var2 = var4; var2 < var7.size(); ++var2) {
               ((OriginalCarDevicePageUiSet.Item)this.mList.get(((OriginalCarDeviceUpdateEntity)var7.get(var2)).getIndex())).setValue(((OriginalCarDeviceUpdateEntity)var7.get(var2)).getValue());
            }
         }

         this.mPlayTimeTv.setText(GeneralOriginalCarDeviceData.startTime);
         this.mTotalTimeTv.setText(GeneralOriginalCarDeviceData.endTime);
         this.mCdStatusTv.setText(GeneralOriginalCarDeviceData.cdStatus);
         this.mRunStateTv.setText(GeneralOriginalCarDeviceData.runningState);
         this.mDiscStatusTv.setText(GeneralOriginalCarDeviceData.discStatus);
         this.mPb.setProgress(GeneralOriginalCarDeviceData.progress);
         this.mOriginalDataLvItemAdapter.notifyDataSetChanged();
         if (this.mSet.isHaveSongList()) {
            var7 = GeneralOriginalCarDeviceData.songList;
            if (var7 != null && GeneralOriginalCarDeviceData.isSongListChange) {
               this.mSongList.clear();
               this.mSongList.addAll(var7);
               this.mSongListLvAdapter.notifyDataSetChanged();
            }
         }

      }
   }
}
