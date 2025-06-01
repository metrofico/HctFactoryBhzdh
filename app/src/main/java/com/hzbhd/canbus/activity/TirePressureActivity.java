package com.hzbhd.canbus.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.adapter.TireInfoLvAdapter;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_set.TirePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TirePressureActivity extends AbstractBaseActivity {
   private List mList0;
   private List mList1;
   private List mList2;
   private List mList3;
   private List mList4;
   private RelativeLayout mNoTirePressureInfoRl;
   private OnTirePageStatusListener mOnTirePageStatusListener;
   private ImageView mTire0Iv;
   private ImageView mTire1Iv;
   private ImageView mTire2Iv;
   private ImageView mTire3Iv;
   private ImageView mTire4Iv;
   private RecyclerView mTireInfo0Rv;
   private RecyclerView mTireInfo1Rv;
   private RecyclerView mTireInfo2Rv;
   private RecyclerView mTireInfo3Rv;
   private RecyclerView mTireInfo4Rv;
   private TireInfoLvAdapter mTireInfoLvAdapter0;
   private TireInfoLvAdapter mTireInfoLvAdapter1;
   private TireInfoLvAdapter mTireInfoLvAdapter2;
   private TireInfoLvAdapter mTireInfoLvAdapter3;
   private TireInfoLvAdapter mTireInfoLvAdapter4;
   private TirePageUiSet mTireUiSet;

   private void findViews() {
      this.mTire0Iv = (ImageView)this.findViewById(2131362647);
      this.mTire1Iv = (ImageView)this.findViewById(2131362648);
      this.mTire2Iv = (ImageView)this.findViewById(2131362649);
      this.mTire3Iv = (ImageView)this.findViewById(2131362650);
      this.mTire4Iv = (ImageView)this.findViewById(2131362651);
      this.mTireInfo0Rv = (RecyclerView)this.findViewById(2131363213);
      this.mTireInfo1Rv = (RecyclerView)this.findViewById(2131363214);
      this.mTireInfo2Rv = (RecyclerView)this.findViewById(2131363215);
      this.mTireInfo3Rv = (RecyclerView)this.findViewById(2131363216);
      this.mTireInfo4Rv = (RecyclerView)this.findViewById(2131363217);
      this.mNoTirePressureInfoRl = (RelativeLayout)this.findViewById(2131363197);
   }

   private void initViews() {
      TirePageUiSet var2 = this.getUiMgrInterface(this).getTireUiSet(this);
      this.mTireUiSet = var2;
      OnTirePageStatusListener var6 = var2.getOnTirePageStatusListener();
      this.mOnTirePageStatusListener = var6;
      if (var6 != null) {
         var6.onStatusChange(1);
      } else {
         LogUtil.showLog("mOnTirePageStatusListener==null");
      }

      this.mList0 = new ArrayList();
      TireInfoLvAdapter var7 = new TireInfoLvAdapter(this, this.mList0);
      this.mTireInfoLvAdapter0 = var7;
      this.setListData(this.mTireInfo0Rv, var7);
      this.mList1 = new ArrayList();
      var7 = new TireInfoLvAdapter(this, this.mList1);
      this.mTireInfoLvAdapter1 = var7;
      this.setListData(this.mTireInfo1Rv, var7);
      this.mList2 = new ArrayList();
      var7 = new TireInfoLvAdapter(this, this.mList2);
      this.mTireInfoLvAdapter2 = var7;
      this.setListData(this.mTireInfo2Rv, var7);
      this.mList3 = new ArrayList();
      var7 = new TireInfoLvAdapter(this, this.mList3);
      this.mTireInfoLvAdapter3 = var7;
      this.setListData(this.mTireInfo3Rv, var7);
      this.mList4 = new ArrayList();
      var7 = new TireInfoLvAdapter(this, this.mList4);
      this.mTireInfoLvAdapter4 = var7;
      this.setListData(this.mTireInfo4Rv, var7);
      this.setViewVisibility(this.mTire4Iv, this.mTireUiSet.isHaveSpareTire());
      this.setViewVisibility(this.mTireInfo4Rv, this.mTireUiSet.isHaveSpareTire());
      List var3 = this.mTireUiSet.getTireInfoStrList();
      Iterator var9;
      if (!this.mTireUiSet.isInitResToString()) {
         Iterator var4 = var3.iterator();

         while(var4.hasNext()) {
            var9 = ((TirePageUiSet.TireItem)var4.next()).getList().iterator();

            while(var9.hasNext()) {
               TirePageUiSet.LineItem var5 = (TirePageUiSet.LineItem)var9.next();
               var5.setValue(this.getString(CommUtil.getStrIdByResId(this, var5.getValue())));
            }
         }

         this.mTireUiSet.setInitResToString(true);
      }

      var9 = var3.iterator();

      while(var9.hasNext()) {
         TirePageUiSet.TireItem var8 = (TirePageUiSet.TireItem)var9.next();
         if (var8 == null) {
            return;
         }

         int var1 = var8.getWhichTire();
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 == 4) {
                        this.mList4.clear();
                        this.mList4.addAll(var8.getList());
                        this.mTireInfoLvAdapter4.notifyDataSetChanged();
                     }
                  } else {
                     this.mList3.clear();
                     this.mList3.addAll(var8.getList());
                     this.mTireInfoLvAdapter3.notifyDataSetChanged();
                  }
               } else {
                  this.mList2.clear();
                  this.mList2.addAll(var8.getList());
                  this.mTireInfoLvAdapter2.notifyDataSetChanged();
               }
            } else {
               this.mList1.clear();
               this.mList1.addAll(var8.getList());
               this.mTireInfoLvAdapter1.notifyDataSetChanged();
            }
         } else {
            this.mList0.clear();
            this.mList0.addAll(var8.getList());
            this.mTireInfoLvAdapter0.notifyDataSetChanged();
         }
      }

      this.refreshUi((Bundle)null);
   }

   private void refreshEachList(List var1) {
      if (var1 != null) {
         int var7;
         TireUpdateEntity var8;
         for(Iterator var9 = var1.iterator(); var9.hasNext(); this.setTireStatus(var7, var8.getTireStatus())) {
            var8 = (TireUpdateEntity)var9.next();
            if (var8 == null) {
               return;
            }

            var7 = var8.getWhichTire();
            byte var4 = 0;
            byte var5 = 0;
            byte var6 = 0;
            int var2 = 0;
            byte var3 = 0;
            if (var7 != 0) {
               var2 = var6;
               if (var7 != 1) {
                  var2 = var5;
                  if (var7 != 2) {
                     var2 = var4;
                     if (var7 != 3) {
                        var2 = var3;
                        if (var7 == 4) {
                           while(var2 < var8.getList().size() && var2 < this.mList4.size()) {
                              ((TirePageUiSet.LineItem)this.mList4.get(var2)).setValue((String)var8.getList().get(var2));
                              ++var2;
                           }

                           this.mTireInfoLvAdapter4.notifyDataSetChanged();
                        }
                     } else {
                        while(var2 < var8.getList().size() && var2 < this.mList3.size()) {
                           ((TirePageUiSet.LineItem)this.mList3.get(var2)).setValue((String)var8.getList().get(var2));
                           ++var2;
                        }

                        this.mTireInfoLvAdapter3.notifyDataSetChanged();
                     }
                  } else {
                     while(var2 < var8.getList().size() && var2 < this.mList2.size()) {
                        ((TirePageUiSet.LineItem)this.mList2.get(var2)).setValue((String)var8.getList().get(var2));
                        ++var2;
                     }

                     this.mTireInfoLvAdapter2.notifyDataSetChanged();
                  }
               } else {
                  while(var2 < var8.getList().size() && var2 < this.mList1.size()) {
                     ((TirePageUiSet.LineItem)this.mList1.get(var2)).setValue((String)var8.getList().get(var2));
                     ++var2;
                  }

                  this.mTireInfoLvAdapter1.notifyDataSetChanged();
               }
            } else {
               while(var2 < var8.getList().size() && var2 < this.mList0.size()) {
                  ((TirePageUiSet.LineItem)this.mList0.get(var2)).setValue((String)var8.getList().get(var2));
                  ++var2;
               }

               this.mTireInfoLvAdapter0.notifyDataSetChanged();
            }
         }

      }
   }

   private void setListData(RecyclerView var1, TireInfoLvAdapter var2) {
      var1.setLayoutManager(new LinearLayoutManager(this));
      DividerItemDecoration var3 = new DividerItemDecoration(this, 1);
      var3.setDrawable(this.getResources().getDrawable(2131234903));
      var1.addItemDecoration(var3);
      var1.setAdapter(var2);
   }

   private void setTireStatus(int var1, int var2) {
      ImageView var3;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     var3 = null;
                  } else {
                     var3 = this.mTire4Iv;
                  }
               } else {
                  var3 = this.mTire3Iv;
               }
            } else {
               var3 = this.mTire2Iv;
            }
         } else {
            var3 = this.mTire1Iv;
         }
      } else {
         var3 = this.mTire0Iv;
      }

      if (var3 != null) {
         if (var2 == 0) {
            if (var1 == 4) {
               var3.setImageResource(2131234209);
            } else {
               var3.setImageResource(2131234212);
            }
         } else if (var2 == 1) {
            if (var1 == 4) {
               var3.setImageResource(2131234211);
            } else {
               var3.setImageResource(2131234214);
            }
         }

      }
   }

   private void setViewVisibility(View var1, boolean var2) {
      if (var2) {
         var1.setVisibility(0);
      } else {
         var1.setVisibility(4);
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558699);
      this.findViews();
      this.initViews();
   }

   protected void onResume() {
      super.onResume();
      this.refreshUi((Bundle)null);
   }

   public void refreshUi(Bundle var1) {
      if (this.isShouldRefreshUi()) {
         if (GeneralTireData.isNoTirePressureInfo) {
            this.mNoTirePressureInfoRl.setVisibility(0);
         } else {
            this.mNoTirePressureInfoRl.setVisibility(8);
            this.refreshEachList(GeneralTireData.dataList);
         }

         if (GeneralTireData.isHaveSpareTire) {
            this.mTire4Iv.setVisibility(0);
            this.mTireInfo4Rv.setVisibility(0);
         } else {
            this.mTire4Iv.setVisibility(8);
            this.mTireInfo4Rv.setVisibility(8);
         }

      }
   }
}
