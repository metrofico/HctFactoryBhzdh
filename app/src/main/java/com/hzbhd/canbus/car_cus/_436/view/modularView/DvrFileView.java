package com.hzbhd.canbus.car_cus._436.view.modularView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._436.Interface.MdNotifyListener;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile;
import com.hzbhd.canbus.car_cus._436.util.DvrSender;
import java.util.List;

public class DvrFileView extends LinearLayout implements View.OnClickListener, MdNotifyListener {
   TextView file1_txt;
   TextView file2_txt;
   TextView file3_txt;
   TextView file4_txt;
   TextView file5_txt;
   TextView file6_txt;
   Context mContext;
   ImageButton next_page_imb;
   TextView page_number_txt;
   ImageButton prev_page_imb;
   View view;

   public DvrFileView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public DvrFileView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public DvrFileView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mContext = var1;
      this.view = LayoutInflater.from(var1).inflate(2131558523, this, true);
      this.initData();
   }

   private void cleanData() {
      GeneralDvrFile.getInstance().item1.clear();
      GeneralDvrFile.getInstance().item2.clear();
      GeneralDvrFile.getInstance().item3.clear();
      GeneralDvrFile.getInstance().item4.clear();
      GeneralDvrFile.getInstance().item5.clear();
      GeneralDvrFile.getInstance().item6.clear();
   }

   private void initData() {
      this.file1_txt = (TextView)this.view.findViewById(2131362230);
      this.file2_txt = (TextView)this.view.findViewById(2131362231);
      this.file3_txt = (TextView)this.view.findViewById(2131362232);
      this.file4_txt = (TextView)this.view.findViewById(2131362233);
      this.file5_txt = (TextView)this.view.findViewById(2131362234);
      this.file6_txt = (TextView)this.view.findViewById(2131362235);
      this.prev_page_imb = (ImageButton)this.view.findViewById(2131362995);
      this.page_number_txt = (TextView)this.view.findViewById(2131362956);
      this.next_page_imb = (ImageButton)this.view.findViewById(2131362899);
      this.file1_txt.setOnClickListener(new DvrFileView$$ExternalSyntheticLambda0(this));
      this.file2_txt.setOnClickListener(new DvrFileView$$ExternalSyntheticLambda0(this));
      this.file3_txt.setOnClickListener(new DvrFileView$$ExternalSyntheticLambda0(this));
      this.file4_txt.setOnClickListener(new DvrFileView$$ExternalSyntheticLambda0(this));
      this.file5_txt.setOnClickListener(new DvrFileView$$ExternalSyntheticLambda0(this));
      this.file6_txt.setOnClickListener(new DvrFileView$$ExternalSyntheticLambda0(this));
      this.prev_page_imb.setOnClickListener(new DvrFileView$$ExternalSyntheticLambda0(this));
      this.next_page_imb.setOnClickListener(new DvrFileView$$ExternalSyntheticLambda0(this));
      this.refreshUi();
   }

   private String listToFileNameStr(List var1) {
      StringBuilder var3 = new StringBuilder();
      String var2;
      if ((Integer)var1.get(26) == 1) {
         var2 = "EVE_";
      } else {
         var2 = "REC_";
      }

      StringBuilder var5 = var3.append(var2).append((Integer)var1.get(7) - 48).append("").append((Integer)var1.get(8) - 48).append("").append((Integer)var1.get(9) - 48).append("").append((Integer)var1.get(10) - 48).append("").append((Integer)var1.get(11) - 48).append("").append((Integer)var1.get(12) - 48).append("").append((Integer)var1.get(13) - 48).append("").append((Integer)var1.get(14) - 48).append("_").append((Integer)var1.get(15) - 48).append("").append((Integer)var1.get(16) - 48).append("").append((Integer)var1.get(17) - 48).append("").append((Integer)var1.get(18) - 48).append("").append((Integer)var1.get(19) - 48).append("").append((Integer)var1.get(20) - 48).append("_").append((Integer)var1.get(21) - 48).append("").append((Integer)var1.get(22) - 48).append("").append((Integer)var1.get(23) - 48).append("").append((Integer)var1.get(24) - 48).append("").append((Integer)var1.get(25) - 48).append(".AVI");
      String var4;
      if ((Integer)var1.get(26) == 1) {
         var4 = "(紧急录像文件)";
      } else {
         var4 = "(普通录像文件)";
      }

      return var5.append(var4).toString();
   }

   public void onClick(View var1) {
      int var2 = var1.getId();
      if (var2 != 2131362899) {
         if (var2 == 2131362995) {
            --GeneralDvrFile.pageNumber;
            this.cleanData();
            this.refreshUi();
            DvrSender.send(new byte[]{113, -1});
            this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
            this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
            this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
            this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
            this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
            this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
         }
      } else {
         ++GeneralDvrFile.pageNumber;
         this.cleanData();
         this.refreshUi();
         DvrSender.send(new byte[]{64, 38});
         this.file1_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file2_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file3_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file4_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file5_txt.setBackgroundColor(Color.parseColor("#00000000"));
         this.file6_txt.setBackgroundColor(Color.parseColor("#00000000"));
      }

   }

   public void refreshUi() {
      this.page_number_txt.setText("P " + GeneralDvrFile.pageNumber);
      if (GeneralDvrFile.getInstance().item1.size() > 21) {
         this.file1_txt.setText(this.listToFileNameStr(GeneralDvrFile.getInstance().item1));
         if ((Integer)GeneralDvrFile.getInstance().item1.get(26) == 1) {
            this.file1_txt.setTextColor(Color.parseColor("#FFB60C00"));
         } else {
            this.file1_txt.setTextColor(Color.parseColor("#ffffff"));
         }
      } else {
         this.file1_txt.setText("");
      }

      if (GeneralDvrFile.getInstance().item2.size() > 21) {
         this.file2_txt.setText(this.listToFileNameStr(GeneralDvrFile.getInstance().item2));
         if ((Integer)GeneralDvrFile.getInstance().item2.get(26) == 1) {
            this.file2_txt.setTextColor(Color.parseColor("#FFB60C00"));
         } else {
            this.file2_txt.setTextColor(Color.parseColor("#ffffff"));
         }
      } else {
         this.file2_txt.setText("");
      }

      if (GeneralDvrFile.getInstance().item3.size() > 21) {
         this.file3_txt.setText(this.listToFileNameStr(GeneralDvrFile.getInstance().item3));
         if ((Integer)GeneralDvrFile.getInstance().item3.get(26) == 1) {
            this.file3_txt.setTextColor(Color.parseColor("#FFB60C00"));
         } else {
            this.file3_txt.setTextColor(Color.parseColor("#ffffff"));
         }
      } else {
         this.file3_txt.setText("");
      }

      if (GeneralDvrFile.getInstance().item4.size() > 21) {
         this.file4_txt.setText(this.listToFileNameStr(GeneralDvrFile.getInstance().item4));
         if ((Integer)GeneralDvrFile.getInstance().item4.get(26) == 1) {
            this.file4_txt.setTextColor(Color.parseColor("#FFB60C00"));
         } else {
            this.file4_txt.setTextColor(Color.parseColor("#ffffff"));
         }
      } else {
         this.file4_txt.setText("");
      }

      if (GeneralDvrFile.getInstance().item5.size() > 21) {
         this.file5_txt.setText(this.listToFileNameStr(GeneralDvrFile.getInstance().item5));
         if ((Integer)GeneralDvrFile.getInstance().item5.get(26) == 1) {
            this.file5_txt.setTextColor(Color.parseColor("#FFB60C00"));
         } else {
            this.file5_txt.setTextColor(Color.parseColor("#ffffff"));
         }
      } else {
         this.file5_txt.setText("");
      }

      if (GeneralDvrFile.getInstance().item6.size() > 21) {
         this.file6_txt.setText(this.listToFileNameStr(GeneralDvrFile.getInstance().item6));
         if ((Integer)GeneralDvrFile.getInstance().item6.get(26) == 1) {
            this.file6_txt.setTextColor(Color.parseColor("#FFB60C00"));
         } else {
            this.file6_txt.setTextColor(Color.parseColor("#ffffff"));
         }
      } else {
         this.file6_txt.setText("");
      }

   }

   public void updateUi() {
      this.refreshUi();
   }
}
