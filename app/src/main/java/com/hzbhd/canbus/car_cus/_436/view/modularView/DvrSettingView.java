package com.hzbhd.canbus.car_cus._436.view.modularView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener;
import com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrSetting;
import com.hzbhd.canbus.car_cus._436.util.DvrSender;
import com.hzbhd.canbus.car_cus._436.view.childView.ItemDialog2;
import com.hzbhd.canbus.car_cus._436.view.childView.ItemDialog3;
import com.hzbhd.canbus.car_cus._436.view.childView.ItemDialog4;
import com.hzbhd.canbus.car_cus._436.view.childView.ItemDialogAlert;
import java.util.ArrayList;
import java.util.List;

public class DvrSettingView extends LinearLayout implements View.OnClickListener, MdNotifyListener {
   List list1;
   List list2;
   List list3;
   List list4;
   List list5;
   List list6;
   List list7;
   List list8;
   Context mContext;
   TextView s1_item_txt;
   TextView s1_title_txt;
   TextView s2_item_txt;
   TextView s2_title_txt;
   TextView s3_item_txt;
   TextView s3_title_txt;
   TextView s4_item_txt;
   TextView s4_title_txt;
   TextView s5_item_txt;
   TextView s5_title_txt;
   TextView s6_item_txt;
   TextView s6_title_txt;
   TextView s7_item_txt;
   TextView s7_title_txt;
   TextView s8_item_txt;
   TextView s8_title_txt;
   TextView s9_item_txt;
   TextView s9_title_txt;
   View view;

   public DvrSettingView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public DvrSettingView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public DvrSettingView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.list1 = new ArrayList();
      this.list2 = new ArrayList();
      this.list3 = new ArrayList();
      this.list4 = new ArrayList();
      this.list5 = new ArrayList();
      this.list6 = new ArrayList();
      this.list7 = new ArrayList();
      this.list8 = new ArrayList();
      this.mContext = var1;
      this.view = LayoutInflater.from(var1).inflate(2131558529, this, true);
      this.initData();
   }

   private void doSetting1() {
      (new ItemDialog3()).showDialog(this.mContext, (String)this.list1.get(0), (String)this.list1.get(1), (String)this.list1.get(2), new MdOnClickListener(this) {
         final DvrSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void clickPosition(int var1) {
            DvrSender.send(new byte[]{80, (byte)var1});
         }
      });
   }

   private void doSetting2() {
      (new ItemDialog3()).showDialog(this.mContext, (String)this.list2.get(0), (String)this.list2.get(1), (String)this.list2.get(2), new MdOnClickListener(this) {
         final DvrSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void clickPosition(int var1) {
            DvrSender.send(new byte[]{81, (byte)var1});
         }
      });
   }

   private void doSetting3() {
      (new ItemDialog4()).showDialog(this.mContext, (String)this.list3.get(0), (String)this.list3.get(1), (String)this.list3.get(2), (String)this.list3.get(3), new MdOnClickListener(this) {
         final DvrSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void clickPosition(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
                        DvrSender.send(new byte[]{82, 5});
                     }
                  } else {
                     DvrSender.send(new byte[]{82, 3});
                  }
               } else {
                  DvrSender.send(new byte[]{82, 1});
               }
            } else {
               DvrSender.send(new byte[]{82, 0});
            }

         }
      });
   }

   private void doSetting4() {
      (new ItemDialog2()).showDialog(this.mContext, (String)this.list4.get(0), (String)this.list4.get(1), new MdOnClickListener(this) {
         final DvrSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void clickPosition(int var1) {
            DvrSender.send(new byte[]{83, (byte)var1});
         }
      });
   }

   private void doSetting5() {
      (new ItemDialog2()).showDialog(this.mContext, (String)this.list5.get(0), (String)this.list5.get(1), new MdOnClickListener(this) {
         final DvrSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void clickPosition(int var1) {
            DvrSender.send(new byte[]{84, (byte)var1});
         }
      });
   }

   private void doSetting6() {
      (new ItemDialog4()).showDialog(this.mContext, (String)this.list6.get(0), (String)this.list6.get(1), (String)this.list6.get(2), (String)this.list6.get(3), new MdOnClickListener(this) {
         final DvrSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void clickPosition(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
                        DvrSender.send(new byte[]{85, 5});
                     }
                  } else {
                     DvrSender.send(new byte[]{85, 3});
                  }
               } else {
                  DvrSender.send(new byte[]{85, 1});
               }
            } else {
               DvrSender.send(new byte[]{85, 0});
            }

         }
      });
   }

   private void doSetting7() {
      (new ItemDialog2()).showDialog(this.mContext, (String)this.list7.get(0), (String)this.list7.get(1), new MdOnClickListener(this) {
         final DvrSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void clickPosition(int var1) {
            DvrSender.send(new byte[]{86, (byte)var1});
         }
      });
   }

   private void doSetting8() {
      (new ItemDialog3()).showDialog(this.mContext, (String)this.list8.get(0), (String)this.list8.get(1), (String)this.list8.get(2), new MdOnClickListener(this) {
         final DvrSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void clickPosition(int var1) {
            DvrSender.send(new byte[]{87, (byte)var1});
         }
      });
   }

   private void doSetting9() {
      ItemDialogAlert var2 = new ItemDialogAlert();
      Context var1 = this.mContext;
      var2.showDialog(var1, var1.getString(2131765877), new MdOnClickListener(this) {
         final DvrSettingView this$0;

         {
            this.this$0 = var1;
         }

         public void clickPosition(int var1) {
            DvrSender.send(new byte[]{92, 0});
         }
      });
   }

   private void initData() {
      this.s1_title_txt = (TextView)this.view.findViewById(2131363228);
      this.s1_item_txt = (TextView)this.view.findViewById(2131363227);
      this.s1_title_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.s1_item_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.list1.add("640x480/30");
      this.list1.add("1280x720/30");
      this.list1.add("1920x1080/30");
      this.s2_title_txt = (TextView)this.view.findViewById(2131363230);
      this.s2_item_txt = (TextView)this.view.findViewById(2131363229);
      this.s2_title_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.s2_item_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.list2.add(this.mContext.getString(2131765850));
      this.list2.add(this.mContext.getString(2131765851));
      this.list2.add(this.mContext.getString(2131765852));
      this.s3_title_txt = (TextView)this.view.findViewById(2131363232);
      this.s3_item_txt = (TextView)this.view.findViewById(2131363231);
      this.s3_title_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.s3_item_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.list3.add(this.mContext.getString(2131765854));
      this.list3.add(this.mContext.getString(2131765855));
      this.list3.add(this.mContext.getString(2131765856));
      this.list3.add(this.mContext.getString(2131765857));
      this.s4_title_txt = (TextView)this.view.findViewById(2131363234);
      this.s4_item_txt = (TextView)this.view.findViewById(2131363233);
      this.s4_title_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.s4_item_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.list4.add(this.mContext.getString(2131765859));
      this.list4.add(this.mContext.getString(2131765860));
      this.s5_title_txt = (TextView)this.view.findViewById(2131363236);
      this.s5_item_txt = (TextView)this.view.findViewById(2131363235);
      this.s5_title_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.s5_item_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.list5.add(this.mContext.getString(2131765862));
      this.list5.add(this.mContext.getString(2131765863));
      this.s6_title_txt = (TextView)this.view.findViewById(2131363238);
      this.s6_item_txt = (TextView)this.view.findViewById(2131363237);
      this.s6_title_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.s6_item_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.list6.add(this.mContext.getString(2131765865));
      this.list6.add(this.mContext.getString(2131765866));
      this.list6.add(this.mContext.getString(2131765867));
      this.list6.add(this.mContext.getString(2131765868));
      this.s7_title_txt = (TextView)this.view.findViewById(2131363240);
      this.s7_item_txt = (TextView)this.view.findViewById(2131363239);
      this.s7_title_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.s7_item_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.list7.add(this.mContext.getString(2131765870));
      this.list7.add(this.mContext.getString(2131765871));
      this.s8_title_txt = (TextView)this.view.findViewById(2131363242);
      this.s8_item_txt = (TextView)this.view.findViewById(2131363241);
      this.s8_title_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.s8_item_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.list8.add(this.mContext.getString(2131765873));
      this.list8.add(this.mContext.getString(2131765874));
      this.list8.add(this.mContext.getString(2131765875));
      this.s9_title_txt = (TextView)this.view.findViewById(2131363244);
      this.s9_item_txt = (TextView)this.view.findViewById(2131363243);
      this.s9_title_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.s9_item_txt.setOnClickListener(new DvrSettingView$$ExternalSyntheticLambda0(this));
      this.refreshUi();
   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131363227:
         case 2131363228:
            this.doSetting1();
            break;
         case 2131363229:
         case 2131363230:
            this.doSetting2();
            break;
         case 2131363231:
         case 2131363232:
            this.doSetting3();
            break;
         case 2131363233:
         case 2131363234:
            this.doSetting4();
            break;
         case 2131363235:
         case 2131363236:
            this.doSetting5();
            break;
         case 2131363237:
         case 2131363238:
            this.doSetting6();
            break;
         case 2131363239:
         case 2131363240:
            this.doSetting7();
            break;
         case 2131363241:
         case 2131363242:
            this.doSetting8();
            break;
         case 2131363243:
         case 2131363244:
            this.doSetting9();
      }

   }

   public void refreshUi() {
      this.s1_item_txt.setText((CharSequence)this.list1.get(GeneralDvrSetting.resolvingPower));
      this.s2_item_txt.setText((CharSequence)this.list2.get(GeneralDvrSetting.timeTag));
      this.s3_item_txt.setText((CharSequence)this.list3.get(GeneralDvrSetting.VideoRecordingSyncTime));
      this.s4_item_txt.setText((CharSequence)this.list4.get(GeneralDvrSetting.VideoRecordingVoice));
      this.s5_item_txt.setText((CharSequence)this.list5.get(GeneralDvrSetting.dvrLanguage));
      this.s6_item_txt.setText((CharSequence)this.list6.get(GeneralDvrSetting.gravitySensor));
      this.s7_item_txt.setText((CharSequence)this.list7.get(GeneralDvrSetting.opticalFrequency));
      this.s8_item_txt.setText((CharSequence)this.list8.get(GeneralDvrSetting.timeFormat));
   }

   public void updateUi() {
      this.refreshUi();
   }
}
