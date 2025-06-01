package com.hzbhd.canbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.adapter.SettingLeftLvAdapter;
import com.hzbhd.canbus.adapter.SettingRightLvAdapter;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSetTextListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DialogUtil;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SettingActivity extends AbstractBaseActivity implements SettingLeftLvAdapter.LeftItemClickInterface, SettingRightLvAdapter.RightItemClickInterface, SettingRightLvAdapter.RightItemTouchInterface {
   public static final int INVALID_INDEX = -1;
   private final String TAG = "SettingActivity";
   private int mCurLeftIndex;
   private List mLeftList;
   private RecyclerView mLeftRecyclerView;
   private SettingLeftLvAdapter mLeftSettingLvAdapter;
   private OnConfirmDialogListener mOnSettingConfirmDialogListener;
   private OnSettingItemClickListener mOnSettingItemClickListener;
   private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener;
   private OnSettingItemSeekbarSetTextListener mOnSettingItemSeekbarSetTextListener;
   private OnSettingItemSelectListener mOnSettingItemSelectListener;
   private OnSettingItemSwitchListener mOnSettingItemSwitchListener;
   private OnSettingItemTouchListener mOnSettingItemTouchListener;
   private OnSettingPageStatusListener mOnSettingPageStatusListener;
   private List mRightList;
   private RecyclerView mRightRecyclerView;
   private SettingRightLvAdapter mRightSettingLvAdapter;

   private void findViews() {
      this.mLeftRecyclerView = (RecyclerView)this.findViewById(2131363219);
      this.mRightRecyclerView = (RecyclerView)this.findViewById(2131363222);
   }

   private void goToThePosition(Intent var1) {
      int var2 = var1.getIntExtra("left_index ", 0);
      int var3 = var1.getIntExtra("right_index", 0);
      this.onLeftItemClick(var2);
      this.mLeftRecyclerView.scrollToPosition(var2);
      this.mRightRecyclerView.scrollToPosition(var3);
   }

   private void initViews() {
      SettingPageUiSet var1 = this.getUiMgrInterface(this).getSettingUiSet(this);
      this.mOnSettingItemSelectListener = var1.getOnSettingItemSelectListener();
      this.mOnSettingPageStatusListener = var1.getOnSettingPageStatusListener();
      this.mOnSettingItemSeekbarSelectListener = var1.getOnSettingItemSeekbarSelectListener();
      this.mOnSettingItemClickListener = var1.getOnSettingItemClickListener();
      this.mOnSettingItemTouchListener = var1.getOnSettingItemTouchListener();
      this.mOnSettingConfirmDialogListener = var1.getOnSettingConfirmDialogListener();
      this.mOnSettingItemSwitchListener = var1.getOnSettingItemSwitchListener();
      this.mOnSettingItemSeekbarSetTextListener = var1.getOnSettingItemSeekbarSetTextListener();
      this.mLeftList = new ArrayList();
      this.mLeftSettingLvAdapter = new SettingLeftLvAdapter(this, this.mLeftList, this);
      LinearLayoutManager var2 = new LinearLayoutManager(this);
      this.mLeftRecyclerView.setLayoutManager(var2);
      this.mLeftRecyclerView.setAdapter(this.mLeftSettingLvAdapter);
      this.mRightList = new ArrayList();
      this.mRightSettingLvAdapter = new SettingRightLvAdapter(this, this.mRightList, this, this);
      var2 = new LinearLayoutManager(this);
      this.mRightRecyclerView.setLayoutManager(var2);
      this.mRightRecyclerView.setAdapter(this.mRightSettingLvAdapter);
      this.mLeftList.addAll(var1.getList());
      Iterator var3 = this.mLeftList.iterator();

      while(var3.hasNext()) {
         ((SettingPageUiSet.ListBean)var3.next()).setIsSelected(false);
      }

      ((SettingPageUiSet.ListBean)this.mLeftList.get(0)).setIsSelected(true);
      this.mLeftSettingLvAdapter.notifyDataSetChanged();
      this.mRightList.addAll(((SettingPageUiSet.ListBean)this.mLeftList.get(0)).getItemList());
      this.mRightSettingLvAdapter.notifyDataSetChanged();
   }

   private boolean isNotError(SettingUpdateEntity var1) {
      return var1.getLeftListIndex() != -1 && var1.getRightListIndex() != -1 && var1.getLeftListIndex() < this.mLeftList.size() && var1.getRightListIndex() < ((SettingPageUiSet.ListBean)this.mLeftList.get(var1.getLeftListIndex())).getItemList().size();
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558692);
      this.findViews();
      this.initViews();
      Intent var2 = this.getIntent();
      if (var2 != null && "setting_open_target_page".equals(var2.getAction())) {
         this.goToThePosition(var2);
      }

   }

   public void onLeftItemClick(int var1) {
      this.mCurLeftIndex = var1;

      for(int var2 = 0; var2 < this.mLeftList.size(); ++var2) {
         SettingPageUiSet.ListBean var4 = (SettingPageUiSet.ListBean)this.mLeftList.get(var2);
         boolean var3;
         if (var2 == var1) {
            var3 = true;
         } else {
            var3 = false;
         }

         var4.setIsSelected(var3);
      }

      this.mLeftSettingLvAdapter.notifyDataSetChanged();
      this.mRightList.clear();
      this.mRightList.addAll(((SettingPageUiSet.ListBean)this.mLeftList.get(var1)).getItemList());
      this.mRightSettingLvAdapter.notifyDataSetChanged();
      this.refreshUi((Bundle)null);
      OnSettingPageStatusListener var5 = this.mOnSettingPageStatusListener;
      if (var5 != null) {
         var5.onStatusChange(var1);
      }

   }

   protected void onNewIntent(Intent var1) {
      super.onNewIntent(var1);
      if ("setting_open_target_page".equals(var1.getAction())) {
         this.goToThePosition(var1);
      }

   }

   protected void onResume() {
      super.onResume();
      LogUtil.showLog("Setting onResume");
      OnSettingPageStatusListener var1 = this.mOnSettingPageStatusListener;
      if (var1 != null) {
         var1.onStatusChange(-1);
         this.mOnSettingPageStatusListener.onStatusChange(0);
      } else {
         LogUtil.showLog("mOnSettingPageStatusListener==null");
      }

      this.refreshUi((Bundle)null);
   }

   public void onRightItemClick(int var1) {
      OnSettingItemClickListener var7 = this.mOnSettingItemClickListener;
      if (var7 != null) {
         var7.onClickItem(this.mCurLeftIndex, var1);
      }

      int var3 = ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var1)).getStyle();
      int var2 = 1;
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               if (var3 == 4 && this.mOnSettingItemSwitchListener != null) {
                  if (((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var1)).getValue() instanceof Integer) {
                     var2 = 1 - (Integer)((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var1)).getValue();
                  }

                  this.mOnSettingItemSwitchListener.onSwitch(this.mCurLeftIndex, var1, var2);
               }
            } else {
               String var10 = ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var1)).getConfirmTis();
               DialogUtil.getInstance().showConfirmDialog(this, var10, this.mCurLeftIndex, var1, this.mOnSettingConfirmDialogListener);
            }
         } else {
            int var4 = ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var1)).getMax();
            var2 = ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var1)).getMin();
            var3 = ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var1)).getProgress();
            boolean var6 = ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var1)).isProgressDraggable();
            DialogUtil.getInstance().showSeekDialog(this, var2, var4, var3, var6, new DialogUtil.SeekbarDialogCallBak(this, var1) {
               final SettingActivity this$0;
               final int val$rightPosition;

               {
                  this.this$0 = var1;
                  this.val$rightPosition = var2;
               }

               public void callBack(int var1) {
                  if (this.this$0.mOnSettingItemSeekbarSelectListener != null) {
                     this.this$0.mOnSettingItemSeekbarSelectListener.onClickItem(this.this$0.mCurLeftIndex, this.val$rightPosition, var1);
                  }

               }
            }, new DialogUtil.SeekbarSetTextListener(this, var1) {
               final SettingActivity this$0;
               final int val$rightPosition;

               {
                  this.this$0 = var1;
                  this.val$rightPosition = var2;
               }

               public String onSetText(int var1) {
                  return this.this$0.mOnSettingItemSeekbarSetTextListener == null ? String.valueOf(var1) : this.this$0.mOnSettingItemSeekbarSetTextListener.onSetText(this.this$0.mCurLeftIndex, this.val$rightPosition, var1);
               }
            });
         }
      } else {
         SettingPageUiSet.ListBean.ItemListBean var11 = (SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var1);
         List var8 = var11.getValueSrnArray();
         byte var9 = 0;
         String[] var12 = (String[])var8.toArray(new String[0]);
         int var5 = var11.getValueSrnArray().indexOf(var11.getValue());
         var2 = var5;
         var3 = var9;
         if (var5 < 0) {
            var2 = 0;
            var3 = var9;
         }

         while(var3 < var12.length) {
            var12[var3] = CommUtil.getStrByResId(this, var12[var3]);
            ++var3;
         }

         DialogUtil.getInstance().showListDialog(this, var12, var2, new DialogUtil.ListDialogCallBak(this, var1) {
            final SettingActivity this$0;
            final int val$rightPosition;

            {
               this.this$0 = var1;
               this.val$rightPosition = var2;
            }

            public void callBack(int var1) {
               if (this.this$0.mOnSettingItemSelectListener != null) {
                  this.this$0.mOnSettingItemSelectListener.onClickItem(this.this$0.mCurLeftIndex, this.val$rightPosition, var1);
               }

            }
         });
      }

   }

   public void onRightItemTouch(int var1, View var2, MotionEvent var3) {
      OnSettingItemTouchListener var4 = this.mOnSettingItemTouchListener;
      if (var4 != null) {
         var4.onTouchItem(this.mCurLeftIndex, var1, var2, var3);
      }

   }

   public void refreshUi(Bundle var1) {
      if (this.isShouldRefreshUi()) {
         LogUtil.showLog("Setting refreshUi:" + GeneralSettingData.dataList.size());
         if (GeneralSettingData.dataList != null) {
            Iterator var4 = (new ArrayList(GeneralSettingData.dataList)).iterator();

            while(var4.hasNext()) {
               SettingUpdateEntity var5 = (SettingUpdateEntity)var4.next();
               if (var5 != null) {
                  if (this.mCurLeftIndex == var5.getLeftListIndex() && this.isNotError(var5)) {
                     int var2 = ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var5.getRightListIndex())).getStyle();
                     if (var2 != 0) {
                        String var6;
                        if (var2 != 1) {
                           if (var2 != 2) {
                              if (var2 != 3) {
                                 if (var2 == 4) {
                                    ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var5.getRightListIndex())).setValue(var5.getValue());
                                 }
                              } else {
                                 ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var5.getRightListIndex())).setValue(var5.getValue());
                              }
                           } else {
                              String var3 = String.valueOf(var5.getValue());
                              var6 = var3;
                              if (this.mOnSettingItemSeekbarSetTextListener != null) {
                                 var6 = var3;
                                 if (var5.getValue() instanceof Integer) {
                                    var6 = this.mOnSettingItemSeekbarSetTextListener.onSetText(var5.getLeftListIndex(), var5.getRightListIndex(), (Integer)var5.getValue());
                                 }
                              }

                              ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var5.getRightListIndex())).setValue(var6);
                              ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var5.getRightListIndex())).setProgress(var5.getProgress());
                           }
                        } else if (var5.getValue() instanceof Integer) {
                           List var7 = ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var5.getRightListIndex())).getValueSrnArray();
                           if ((Integer)var5.getValue() < var7.size() && (Integer)var5.getValue() >= 0) {
                              var6 = (String)var7.get((Integer)var5.getValue());
                              ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var5.getRightListIndex())).setValue(var6);
                           }
                        }
                     } else {
                        ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var5.getRightListIndex())).setValue(var5.getValue());
                        ((SettingPageUiSet.ListBean.ItemListBean)this.mRightList.get(var5.getRightListIndex())).setValueStr(var5.isValueStr());
                     }
                  }

                  if (this.isNotError(var5)) {
                     ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.mLeftList.get(var5.getLeftListIndex())).getItemList().get(var5.getRightListIndex())).setEnable(var5.isEnable());
                  }
               }
            }

            this.mRightSettingLvAdapter.notifyDataSetChanged();
            this.mLeftSettingLvAdapter.notifyDataSetChanged();
         }
      }
   }
}
