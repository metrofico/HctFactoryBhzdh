package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import com.hzbhd.canbus.car_cus._446.Interface.CanInfoObserver;
import com.hzbhd.canbus.car_cus._446.data.WmCarData;
import com.hzbhd.canbus.car_cus._446.util.SendUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;

public class Page2View extends LinearLayout implements CanInfoObserver {
   private static final String KEY_TIAN_CHUANG_TAG = "KEY_TIAN_CHUANG_TAG";
   private SeekBar back_door_seekbar;
   private Context context;
   private SwitchView switchView1;
   private TextView up_back_door;
   private View view;

   public Page2View(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public Page2View(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public Page2View(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.context = var1;
      View var4 = LayoutInflater.from(var1).inflate(2131558594, this, true);
      this.view = var4;
      this.back_door_seekbar = (SeekBar)var4.findViewById(2131361942);
      this.up_back_door = (TextView)this.view.findViewById(2131363727);
      this.switchView1 = (SwitchView)this.view.findViewById(2131363443);
      this.initData();
      this.initAction();
   }

   private void initAction() {
      this.back_door_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(this) {
         final Page2View this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
            WmCarData.backDoorValue = var1.getProgress() + 40;
            SendUtil.send(new byte[]{22, 0, 0, 3, 127, (byte)DataHandleUtils.setOneBit(WmCarData.backDoorValue << 1, 0, 1), 0, 0, 0, 112, 0, 0, 0});
            SharePreUtil.setIntValue(this.this$0.context, "KEY_TIAN_CHUANG_TAG", WmCarData.backDoorValue);
         }
      });
      this.up_back_door.setOnClickListener(new View.OnClickListener(this) {
         final Page2View this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 0, 0, 0, 0, 0, 8, 106});
            SendUtil.send(new byte[]{22, 0, 0, 1, 110, 0, 0, 0, 0, 0, 0, 0, 106});
         }
      });
      this.switchView1.getAction(new ActionDo(this) {
         final Page2View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            if (WmCarData.suoCheTianChaungZiDongGuanBi) {
               SendUtil.send(new byte[]{22, 0, 0, 3, 127, 0, 0, 0, 0, 112, 0, 1, 0});
            } else {
               SendUtil.send(new byte[]{22, 0, 0, 3, 127, 0, 0, 0, 0, 112, 0, 2, 0});
            }

         }
      });
   }

   private void initData() {
      this.updateUi();
      this.back_door_seekbar.setMax(60);
      this.back_door_seekbar.setProgress(SharePreUtil.getIntValue(this.context, "KEY_TIAN_CHUANG_TAG", 100) - 40);
   }

   public void dataChange() {
      this.updateUi();
   }

   public void updateUi() {
      this.switchView1.select(WmCarData.suoCheTianChaungZiDongGuanBi);
   }
}
