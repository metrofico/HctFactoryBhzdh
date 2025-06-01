package com.hzbhd.canbus.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.activity.OnStarActivity;
import com.hzbhd.canbus.interfaces.OnOnStarClickListener;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.util.CommUtil;

public class OnStartPhoneFragment extends BaseFragment implements View.OnClickListener, View.OnLongClickListener {
   public RelativeLayout dialog;
   private TextView gm_onstar_call_type;
   private TextView gm_onstar_input;
   private TextView gm_onstar_input_del;
   private TextView gm_onstar_kb_dial;
   private TextView gm_onstar_kb_hang;
   private TextView gm_onstar_kb_num_0;
   private TextView gm_onstar_kb_num_1;
   private TextView gm_onstar_kb_num_2;
   private TextView gm_onstar_kb_num_3;
   private TextView gm_onstar_kb_num_4;
   private TextView gm_onstar_kb_num_5;
   private TextView gm_onstar_kb_num_6;
   private TextView gm_onstar_kb_num_7;
   private TextView gm_onstar_kb_num_8;
   private TextView gm_onstar_kb_num_9;
   private TextView gm_onstar_kb_num_star;
   private TextView gm_onstar_kb_num_well;
   private TextView gm_onstar_kb_power;
   private TextView gm_onstar_left_icon;
   private TextView gm_onstar_left_num;
   private TextView gm_onstar_mute_icon;
   public RelativeLayout input;
   private OnStarActivity mActivity;
   private String mInputNumStr = "";
   private OnOnStarClickListener mOnOnStarClickListener;
   private View mView;

   private void findViews() {
      this.gm_onstar_left_icon = (TextView)this.mView.findViewById(2131362335);
      this.gm_onstar_left_num = (TextView)this.mView.findViewById(2131362334);
      this.gm_onstar_input = (TextView)this.mView.findViewById(2131362316);
      this.gm_onstar_input_del = (TextView)this.mView.findViewById(2131362318);
      this.gm_onstar_kb_num_1 = (TextView)this.mView.findViewById(2131362321);
      this.gm_onstar_kb_num_2 = (TextView)this.mView.findViewById(2131362322);
      this.gm_onstar_kb_num_3 = (TextView)this.mView.findViewById(2131362323);
      this.gm_onstar_kb_num_4 = (TextView)this.mView.findViewById(2131362324);
      this.gm_onstar_kb_num_5 = (TextView)this.mView.findViewById(2131362325);
      this.gm_onstar_kb_num_6 = (TextView)this.mView.findViewById(2131362326);
      this.gm_onstar_kb_num_7 = (TextView)this.mView.findViewById(2131362327);
      this.gm_onstar_kb_num_8 = (TextView)this.mView.findViewById(2131362328);
      this.gm_onstar_kb_num_9 = (TextView)this.mView.findViewById(2131362329);
      this.gm_onstar_kb_num_star = (TextView)this.mView.findViewById(2131362330);
      this.gm_onstar_kb_num_0 = (TextView)this.mView.findViewById(2131362320);
      this.gm_onstar_kb_num_well = (TextView)this.mView.findViewById(2131362331);
      this.gm_onstar_kb_dial = (TextView)this.mView.findViewById(2131363661);
      this.gm_onstar_kb_hang = (TextView)this.mView.findViewById(2131363660);
      this.gm_onstar_kb_power = (TextView)this.mView.findViewById(2131363659);
      this.gm_onstar_call_type = (TextView)this.mView.findViewById(2131362314);
      this.gm_onstar_mute_icon = (TextView)this.mView.findViewById(2131362336);
      this.input = (RelativeLayout)this.mView.findViewById(2131362317);
      this.dialog = (RelativeLayout)this.mView.findViewById(2131362332);
   }

   private void initData() {
      OnOnStarClickListener var1 = this.mActivity.getUiMgrInterface(this.getActivity()).getOnStartPageUiSet(this.getActivity()).getOnOnStarClickListener();
      this.mOnOnStarClickListener = var1;
      if (var1 != null) {
         var1.init();
      }

   }

   private void initViews() {
      this.gm_onstar_input_del.setOnClickListener(this);
      this.gm_onstar_input_del.setOnLongClickListener(this);
      this.gm_onstar_kb_num_1.setOnClickListener(this);
      this.gm_onstar_kb_num_2.setOnClickListener(this);
      this.gm_onstar_kb_num_3.setOnClickListener(this);
      this.gm_onstar_kb_num_4.setOnClickListener(this);
      this.gm_onstar_kb_num_5.setOnClickListener(this);
      this.gm_onstar_kb_num_6.setOnClickListener(this);
      this.gm_onstar_kb_num_7.setOnClickListener(this);
      this.gm_onstar_kb_num_8.setOnClickListener(this);
      this.gm_onstar_kb_num_9.setOnClickListener(this);
      this.gm_onstar_kb_num_star.setOnClickListener(this);
      this.gm_onstar_kb_num_0.setOnClickListener(this);
      this.gm_onstar_kb_num_well.setOnClickListener(this);
      this.gm_onstar_kb_dial.setOnClickListener(this);
      this.gm_onstar_kb_hang.setOnClickListener(this);
      this.gm_onstar_kb_power.setOnClickListener(this);
   }

   private void keyEventHandler(String var1) {
      String var2 = this.mInputNumStr;
      if (var2 != null && var2.length() > 19) {
         var2 = this.mInputNumStr;
         this.mInputNumStr = var2.substring(var2.length() - 19, this.mInputNumStr.length());
      }

      var1 = this.mInputNumStr + var1;
      this.mInputNumStr = var1;
      this.gm_onstar_input.setText(var1);
   }

   private void numberClick(int var1) {
      OnOnStarClickListener var2 = this.mOnOnStarClickListener;
      if (var2 != null) {
         var2.numberClick(var1);
      }

   }

   public void onClick(View var1) {
      int var2 = var1.getId();
      if (var2 != 2131362318) {
         switch (var2) {
            case 2131362320:
               this.keyEventHandler("0");
               this.numberClick(0);
               break;
            case 2131362321:
               this.keyEventHandler("1");
               this.numberClick(1);
               break;
            case 2131362322:
               this.keyEventHandler("2");
               this.numberClick(2);
               break;
            case 2131362323:
               this.keyEventHandler("3");
               this.numberClick(3);
               break;
            case 2131362324:
               this.keyEventHandler("4");
               this.numberClick(4);
               break;
            case 2131362325:
               this.keyEventHandler("5");
               this.numberClick(5);
               break;
            case 2131362326:
               this.keyEventHandler("6");
               this.numberClick(6);
               break;
            case 2131362327:
               this.keyEventHandler("7");
               this.numberClick(7);
               break;
            case 2131362328:
               this.keyEventHandler("8");
               this.numberClick(8);
               break;
            case 2131362329:
               this.keyEventHandler("9");
               this.numberClick(9);
               break;
            case 2131362330:
               this.keyEventHandler("*");
               this.numberClick(10);
               break;
            case 2131362331:
               this.keyEventHandler("#");
               this.numberClick(11);
               break;
            default:
               OnOnStarClickListener var3;
               switch (var2) {
                  case 2131363659:
                     var3 = this.mOnOnStarClickListener;
                     if (var3 != null) {
                        var3.exit();
                     }
                     break;
                  case 2131363660:
                     var3 = this.mOnOnStarClickListener;
                     if (var3 != null) {
                        var3.handOff();
                     }
                     break;
                  case 2131363661:
                     var3 = this.mOnOnStarClickListener;
                     if (var3 != null) {
                        var3.handOn(this.mInputNumStr);
                     }
               }
         }
      } else {
         String var4 = this.mInputNumStr;
         if (var4 != null && var4.length() > 0) {
            var4 = this.mInputNumStr;
            var4 = var4.substring(0, var4.length() - 1);
            this.mInputNumStr = var4;
            this.gm_onstar_input.setText(var4);
         }
      }

   }

   public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      if (this.mView == null) {
         this.mView = var1.inflate(2131558737, var2, false);
         this.mActivity = (OnStarActivity)this.getActivity();
         this.findViews();
         this.initViews();
      }

      this.initData();
      ViewGroup var4 = (ViewGroup)this.mView.getParent();
      if (var4 != null) {
         var4.removeView(this.mView);
      }

      return this.mView;
   }

   public boolean onLongClick(View var1) {
      if (var1.getId() == 2131362318) {
         TextView var2 = this.gm_onstar_input;
         if (var2 != null) {
            var2.setText("");
         }

         this.mInputNumStr = "";
      }

      CommUtil.playBeep();
      return true;
   }

   public void refreshUi(Bundle var1) {
      this.gm_onstar_call_type.setText(GeneralOnStartData.mOnStarCallType);
      if (GeneralOnStartData.mOnStarCallMuteSt == 1) {
         this.gm_onstar_mute_icon.setBackgroundResource(2131234456);
      } else {
         this.gm_onstar_mute_icon.setBackgroundColor(0);
      }

      int var2 = GeneralOnStartData.mOnStarStatus;
      if (var2 != 2) {
         if (var2 != 3) {
            if (var2 != 4) {
               this.gm_onstar_left_icon.setBackgroundColor(0);
               this.gm_onstar_left_num.setText(" ");
               this.gm_onstar_call_type.setText("");
               this.gm_onstar_mute_icon.setBackgroundColor(0);
            } else {
               this.gm_onstar_left_icon.setBackgroundResource(2131233784);
               this.gm_onstar_left_num.setText(GeneralOnStartData.mOnStarPhoneNum);
            }
         } else {
            this.gm_onstar_left_icon.setBackgroundResource(2131233783);
            this.gm_onstar_left_num.setText(GeneralOnStartData.mOnStarPhoneNum);
         }
      } else {
         this.gm_onstar_left_icon.setBackgroundResource(2131233782);
         this.gm_onstar_left_num.setText(GeneralOnStartData.mOnStarPhoneNum);
      }

   }
}
