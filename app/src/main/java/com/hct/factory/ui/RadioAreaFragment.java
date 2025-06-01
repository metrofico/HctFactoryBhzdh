package com.hct.factory.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.hct.factory.FactoryFun;
import com.hct.factory.Hct_Config;

public class RadioAreaFragment extends Fragment {
   private Hct_Config.ST_FACTORY_CONFIG mConfig = null;
   private FactoryFun mFactoryFun;
   private ListView mRadioZonelist;
   private RadioZoneAdapter mRadioZonelistadapter;

   private void InitRadioZone() {
      this.mRadioZonelistadapter = new RadioZoneAdapter(this, this.getActivity(), this.mConfig.mRadioArea);
      this.mRadioZonelist.setAdapter(this.mRadioZonelistadapter);
      this.mRadioZonelist.setOnItemClickListener(new AdapterView.OnItemClickListener(this) {
         final RadioAreaFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mRadioZonelistadapter.setItem(var3);
            this.this$0.mConfig.mRadioArea = (byte)var3;
         }
      });
   }

   public void onActivityCreated(@Nullable Bundle var1) {
      super.onActivityCreated(var1);
      this.mConfig = this.mFactoryFun.getFactoryConfig();
      this.InitRadioZone();
   }

   public void onAttach(Activity var1) {
      super.onAttach(var1);

      try {
         this.mFactoryFun = (FactoryFun)var1;
      } catch (Exception var2) {
      }

   }

   public void onCreate(@Nullable Bundle var1) {
      super.onCreate(var1);
   }

   public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      View var4 = var1.inflate(2131230738, var2, false);
      this.mRadioZonelist = (ListView)var4.findViewById(2131099830);
      return var4;
   }

   public void onDestroy() {
      super.onDestroy();
   }

   public void onResume() {
      super.onResume();
   }

   private class RadioZoneAdapter extends BaseAdapter {
      private Context mContext;
      private LayoutInflater mInflater;
      private int mSelItem;
      private int[][] radiozonearrary;
      final RadioAreaFragment this$0;

      public RadioZoneAdapter(RadioAreaFragment var1, Context var2, int var3) {
         this.this$0 = var1;
         int[] var8 = new int[]{2131296457, 2131296443};
         int[] var5 = new int[]{2131296458, 2131296444};
         int[] var7 = new int[]{2131296461, 2131296445};
         int[] var9 = new int[]{2131296462, 2131296446};
         int[] var11 = new int[]{2131296454, 2131296447};
         int[] var6 = new int[]{2131296459, 2131296449};
         int[] var10 = new int[]{2131296456, 2131296450};
         int[] var4 = new int[]{2131296460, 2131296451};
         this.radiozonearrary = new int[][]{var8, var5, var7, var9, var11, {2131296455, 2131296448}, var6, var10, var4};
         this.mSelItem = -1;
         this.mContext = var2;
         this.mInflater = LayoutInflater.from(var2);
         this.mSelItem = var3;
      }

      public int getCount() {
         return this.radiozonearrary.length;
      }

      public Object getItem(int var1) {
         return var1;
      }

      public long getItemId(int var1) {
         return (long)var1;
      }

      public View getView(int var1, View var2, ViewGroup var3) {
         RadioHolder var4;
         if (var2 == null) {
            var2 = this.mInflater.inflate(2131230739, (ViewGroup)null);
            var4 = new RadioHolder(this);
            var4.radiozone = (TextView)var2.findViewById(2131099833);
            var4.radiozonesummary = (TextView)var2.findViewById(2131099835);
            var4.radiozonecheck = (RadioButton)var2.findViewById(2131099834);
            var2.setTag(var4);
         } else {
            var4 = (RadioHolder)var2.getTag();
         }

         var4.radiozone.setText(this.radiozonearrary[var1][0]);
         var4.radiozonesummary.setText(this.radiozonearrary[var1][1]);
         if (var1 == this.mSelItem) {
            var4.radiozonecheck.setChecked(true);
         } else {
            var4.radiozonecheck.setChecked(false);
         }

         return var2;
      }

      public void setItem(int var1) {
         if (this.mSelItem != var1) {
            this.mSelItem = var1;
            this.notifyDataSetInvalidated();
         }

      }

      private class RadioHolder {
         TextView radiozone;
         RadioButton radiozonecheck;
         TextView radiozonesummary;
         final RadioZoneAdapter this$1;

         private RadioHolder(RadioZoneAdapter var1) {
            this.this$1 = var1;
         }

         // $FF: synthetic method
         RadioHolder(RadioZoneAdapter var1, Object var2) {
            this(var1);
         }
      }
   }
}
