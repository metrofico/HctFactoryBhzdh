package com.hzbhd.canbus.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hzbhd.canbus.activity.OnStarActivity;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.view.VerticalProgressBar;

public class OnStartNavigationFragment extends BaseFragment implements View.OnClickListener {
   public static final int BUICK_GL8_2017_XINBAS = 71;
   public static final int GM_XINBAS = 62;
   private TextView gm_onstar_navi_cancel;
   private TextView gm_onstar_navi_destination;
   private TextView gm_onstar_navi_direction;
   private TextView gm_onstar_navi_next_status;
   private TextView gm_onstar_navi_prev_status;
   private TextView gm_onstar_navi_preview;
   private VerticalProgressBar gm_onstar_navi_progress;
   private TextView gm_onstar_navi_repeat;
   private View gm_onstar_navi_travel_distance_bg;
   private TextView gm_onstar_navi_travel_distance_next;
   private TextView gm_onstar_navi_travel_distance_range;
   private View gm_onstar_navi_travel_info_bg;
   private TextView gm_onstar_navi_travel_info_end;
   private TextView gm_onstar_navi_travel_info_normal;
   private TextView gm_onstar_navi_update;
   private TextView gm_onstar_navi_xinbus_distance;
   private View gm_onstar_navi_xinbus_distance_bg;
   private TextView gm_onstar_navi_xinbus_road_info;
   private View gm_onstar_navi_xinbus_road_info_bg;
   private int[] loadImg = new int[]{2131233786, 2131233787, 2131233803, 2131233805, 2131233806, 2131233807, 2131233808, 2131233809, 2131233810, 2131233811, 2131233812, 2131233813, 2131233815, 2131233816, 2131233817, 2131233818, 2131233788, 2131233789, 2131233790, 2131233791, 2131233792, 2131233793, 2131233794, 2131233795, 2131233818, 2131233796, 2131233797, 2131233798, 2131233799, 2131233800, 2131233801, 2131233802, 2131233804};
   private OnStarActivity mActivity;
   private int mCanType;
   private View mView;

   private void findViews() {
      this.gm_onstar_navi_travel_info_bg = this.mView.findViewById(2131362352);
      this.gm_onstar_navi_travel_distance_bg = this.mView.findViewById(2131362349);
      this.gm_onstar_navi_xinbus_road_info_bg = this.mView.findViewById(2131362359);
      this.gm_onstar_navi_xinbus_distance_bg = this.mView.findViewById(2131362357);
      this.gm_onstar_navi_direction = (TextView)this.mView.findViewById(2131362341);
      this.gm_onstar_navi_progress = (VerticalProgressBar)this.mView.findViewById(2131362346);
      this.gm_onstar_navi_travel_info_end = (TextView)this.mView.findViewById(2131362353);
      this.gm_onstar_navi_travel_info_normal = (TextView)this.mView.findViewById(2131362354);
      this.gm_onstar_navi_travel_distance_next = (TextView)this.mView.findViewById(2131362350);
      this.gm_onstar_navi_travel_distance_range = (TextView)this.mView.findViewById(2131362351);
      this.gm_onstar_navi_repeat = (TextView)this.mView.findViewById(2131362348);
      this.gm_onstar_navi_preview = (TextView)this.mView.findViewById(2131362345);
      this.gm_onstar_navi_prev_status = (TextView)this.mView.findViewById(2131362344);
      this.gm_onstar_navi_next_status = (TextView)this.mView.findViewById(2131362343);
      this.gm_onstar_navi_destination = (TextView)this.mView.findViewById(2131362340);
      this.gm_onstar_navi_update = (TextView)this.mView.findViewById(2131362355);
      this.gm_onstar_navi_cancel = (TextView)this.mView.findViewById(2131362339);
      this.gm_onstar_navi_xinbus_road_info = (TextView)this.mView.findViewById(2131362358);
      this.gm_onstar_navi_xinbus_distance = (TextView)this.mView.findViewById(2131362356);
   }

   private void initViews() {
      this.mActivity = (OnStarActivity)this.getActivity();
      this.mCanType = CanbusConfig.INSTANCE.getCanType();
      this.gm_onstar_navi_repeat.setOnClickListener(this);
      this.gm_onstar_navi_preview.setOnClickListener(this);
      this.gm_onstar_navi_prev_status.setOnClickListener(this);
      this.gm_onstar_navi_next_status.setOnClickListener(this);
      this.gm_onstar_navi_destination.setOnClickListener(this);
      this.gm_onstar_navi_update.setOnClickListener(this);
      this.gm_onstar_navi_cancel.setOnClickListener(this);
      int var1 = this.mCanType;
      if (var1 != 62 && var1 != 71) {
         this.gm_onstar_navi_travel_info_bg.setVisibility(0);
         this.gm_onstar_navi_travel_distance_bg.setVisibility(0);
         this.gm_onstar_navi_xinbus_road_info_bg.setVisibility(8);
         this.gm_onstar_navi_xinbus_distance_bg.setVisibility(8);
      } else {
         this.gm_onstar_navi_travel_info_bg.setVisibility(8);
         this.gm_onstar_navi_travel_distance_bg.setVisibility(8);
         this.gm_onstar_navi_xinbus_road_info_bg.setVisibility(0);
         this.gm_onstar_navi_xinbus_distance_bg.setVisibility(0);
      }

      this.refreshUi((Bundle)null);
   }

   public void onClick(View var1) {
   }

   public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      if (this.mView == null) {
         this.mView = var1.inflate(2131558736, var2, false);
         this.findViews();
         this.initViews();
      }

      ViewGroup var4 = (ViewGroup)this.mView.getParent();
      if (var4 != null) {
         var4.removeView(this.mView);
      }

      return this.mView;
   }

   public void refreshUi(Bundle var1) {
   }
}
