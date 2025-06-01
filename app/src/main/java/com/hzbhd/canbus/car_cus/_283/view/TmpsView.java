package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._283.DialogUtils;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.bean.TmpsChooseBean;
import java.util.ArrayList;
import java.util.List;

public class TmpsView extends RelativeLayout implements View.OnClickListener {
   private static final int redColor = 17170454;
   private static final int whiteColor = 17170443;
   private int[] image = new int[]{2131233311, 2131233311, 2131233310};
   private ImageView imageView;
   private LinearLayout linearChooseTmps;
   private List lists = new ArrayList();
   private Context mContext;
   private View mView;
   private int[] strs = new int[]{2131760778, 2131760779, 2131760780};
   private TextView textView;
   private TextView tv_left_front_reality;
   private TextView tv_left_front_reference;
   private TextView tv_left_rear_reality;
   private TextView tv_left_rear_reference;
   private TextView tv_right_front_reality;
   private TextView tv_right_front_reference;
   private TextView tv_right_rear_reality;
   private TextView tv_right_rear_reference;
   private TextView tv_tips;
   private TextView tv_unit;
   private int[] units = new int[]{2131760775, 2131760776, 2131760777};

   public TmpsView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558483, this);
      this.initView();
      (new Handler()).postDelayed(new TmpsView$$ExternalSyntheticLambda1(this), 1000L);
   }

   private String getTips() {
      StringBuilder var1 = new StringBuilder();
      if (GeneralDzData.left_front_tmps_exception) {
         var1.append(this.mContext.getString(2131760782));
         var1.append("\n");
      }

      if (GeneralDzData.right_front_tmps_exception) {
         var1.append(this.mContext.getString(2131760783));
         var1.append("\n");
      }

      if (GeneralDzData.left_rear_tmps_exception) {
         var1.append(this.mContext.getString(2131760784));
         var1.append("\n");
      }

      if (GeneralDzData.right_rear_tmps_exception) {
         var1.append(this.mContext.getString(2131760785));
         var1.append("\n");
      }

      String var2 = var1.toString();
      return !TextUtils.isEmpty(var2) ? var2 : this.mContext.getString(2131760781);
   }

   private int getTipsTextColor() {
      return !GeneralDzData.left_front_tmps_exception && !GeneralDzData.right_front_tmps_exception && !GeneralDzData.left_rear_tmps_exception && !GeneralDzData.right_rear_tmps_exception ? ContextCompat.getColor(this.mContext, 17170443) : ContextCompat.getColor(this.mContext, 17170454);
   }

   private int getTipsTextColor(boolean var1) {
      return var1 ? ContextCompat.getColor(this.mContext, 17170454) : ContextCompat.getColor(this.mContext, 17170443);
   }

   private void initView() {
      this.linearChooseTmps = (LinearLayout)this.mView.findViewById(2131362759);
      this.imageView = (ImageView)this.mView.findViewById(2131362472);
      this.textView = (TextView)this.mView.findViewById(2131363519);
      this.tv_unit = (TextView)this.mView.findViewById(2131363712);
      this.tv_left_front_reality = (TextView)this.mView.findViewById(2131363637);
      this.tv_left_rear_reality = (TextView)this.mView.findViewById(2131363640);
      this.tv_right_front_reality = (TextView)this.mView.findViewById(2131363679);
      this.tv_right_rear_reality = (TextView)this.mView.findViewById(2131363682);
      this.tv_left_front_reference = (TextView)this.mView.findViewById(2131363638);
      this.tv_left_rear_reference = (TextView)this.mView.findViewById(2131363641);
      this.tv_right_front_reference = (TextView)this.mView.findViewById(2131363680);
      this.tv_right_rear_reference = (TextView)this.mView.findViewById(2131363683);
      this.tv_tips = (TextView)this.mView.findViewById(2131363708);
      this.linearChooseTmps.setOnClickListener(this);
      this.lists.add(new TmpsChooseBean(this.image[0], this.mContext.getString(this.strs[0])));
      this.lists.add(new TmpsChooseBean(this.image[1], this.mContext.getString(this.strs[1])));
      this.lists.add(new TmpsChooseBean(this.image[2], this.mContext.getString(this.strs[2])));
   }

   // $FF: synthetic method
   static void lambda$onClick$1(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, 75, 4, (byte)var0});
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car_cus__283_view_TmpsView() {
      this.refreshUi();
   }

   public void onClick(View var1) {
      if (var1.getId() == 2131362759) {
         DialogUtils.chooseTmps(this.mContext, this.linearChooseTmps, this.lists, new TmpsView$$ExternalSyntheticLambda0());
      }

   }

   public void refreshUi() {
      int var1 = GeneralDzData.tmpsChoose;
      if (var1 >= 0 && var1 < this.lists.size()) {
         this.textView.setText(((TmpsChooseBean)this.lists.get(var1)).getText());
         this.imageView.setImageResource(((TmpsChooseBean)this.lists.get(var1)).getImage());
      }

   }

   public void refreshUiData() {
      this.tv_unit.setText(this.units[GeneralDzData.tmpsUnit]);
      this.tv_tips.setText(this.getTips());
      this.tv_tips.setTextColor(this.getTipsTextColor());
      this.tv_left_front_reality.setText(String.valueOf(GeneralDzData.left_front_tmps_reality));
      this.tv_right_front_reality.setText(String.valueOf(GeneralDzData.right_front_tmps_reality));
      this.tv_left_rear_reality.setText(String.valueOf(GeneralDzData.left_rear_tmps_reality));
      this.tv_right_rear_reality.setText(String.valueOf(GeneralDzData.right_rear_tmps_reality));
      this.tv_left_front_reality.setTextColor(this.getTipsTextColor(GeneralDzData.left_front_tmps_exception));
      this.tv_right_front_reality.setTextColor(this.getTipsTextColor(GeneralDzData.right_front_tmps_exception));
      this.tv_left_rear_reality.setTextColor(this.getTipsTextColor(GeneralDzData.left_rear_tmps_exception));
      this.tv_right_rear_reality.setTextColor(this.getTipsTextColor(GeneralDzData.right_rear_tmps_exception));
      this.tv_left_front_reference.setText(String.valueOf(GeneralDzData.left_front_tmps_reference));
      this.tv_right_front_reference.setText(String.valueOf(GeneralDzData.right_front_tmps_reference));
      this.tv_left_rear_reference.setText(String.valueOf(GeneralDzData.left_rear_tmps_reference));
      this.tv_right_rear_reference.setText(String.valueOf(GeneralDzData.right_rear_tmps_reference));
   }
}
