package com.hzbhd.canbus.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class NumberInputView extends RelativeLayout implements View.OnClickListener {
   private Button btn_0;
   private Button btn_1;
   private Button btn_2;
   private Button btn_3;
   private Button btn_4;
   private Button btn_5;
   private Button btn_6;
   private Button btn_7;
   private Button btn_8;
   private Button btn_9;
   private Button btn_del;
   private Button btn_ok;
   private EditText et_number;
   private String firstPassword;
   private boolean isTwoPassword;
   private Context mContext;
   private OnOkClickListener mOnOkClickListener;
   private View mView;
   private int maxLength;
   private String text;

   public NumberInputView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public NumberInputView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public NumberInputView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.text = "";
      this.maxLength = 18;
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558860, this, true);
      this.initView();
   }

   private void initView() {
      this.et_number = (EditText)this.mView.findViewById(2131362216);
      this.btn_0 = (Button)this.mView.findViewById(2131361994);
      this.btn_1 = (Button)this.mView.findViewById(2131361995);
      this.btn_2 = (Button)this.mView.findViewById(2131361996);
      this.btn_3 = (Button)this.mView.findViewById(2131361997);
      this.btn_4 = (Button)this.mView.findViewById(2131361998);
      this.btn_5 = (Button)this.mView.findViewById(2131361999);
      this.btn_6 = (Button)this.mView.findViewById(2131362000);
      this.btn_7 = (Button)this.mView.findViewById(2131362001);
      this.btn_8 = (Button)this.mView.findViewById(2131362002);
      this.btn_9 = (Button)this.mView.findViewById(2131362003);
      this.btn_ok = (Button)this.mView.findViewById(2131362036);
      this.btn_del = (Button)this.mView.findViewById(2131362018);
      this.btn_0.setOnClickListener(this);
      this.btn_1.setOnClickListener(this);
      this.btn_2.setOnClickListener(this);
      this.btn_3.setOnClickListener(this);
      this.btn_4.setOnClickListener(this);
      this.btn_5.setOnClickListener(this);
      this.btn_6.setOnClickListener(this);
      this.btn_7.setOnClickListener(this);
      this.btn_8.setOnClickListener(this);
      this.btn_9.setOnClickListener(this);
      this.btn_ok.setOnClickListener(this);
      this.btn_del.setOnClickListener(this);
      this.btn_del.setOnLongClickListener(new NumberInputView$$ExternalSyntheticLambda0(this));
   }

   private void setEtText() {
      String var1 = this.text;
      if (var1 != null) {
         this.et_number.setText(var1);
      }

   }

   private void setInputText(String var1) {
      if (this.text.length() < this.maxLength) {
         this.text = this.text + var1;
         this.setEtText();
      }

   }

   // $FF: synthetic method
   boolean lambda$initView$0$com_hzbhd_canbus_view_NumberInputView(View var1) {
      this.text = "";
      this.setEtText();
      return true;
   }

   public void onClick(View var1) {
      int var2 = var1.getId();
      if (var2 != 2131362018) {
         if (var2 != 2131362036) {
            switch (var2) {
               case 2131361994:
                  this.setInputText("0");
                  break;
               case 2131361995:
                  this.setInputText("1");
                  break;
               case 2131361996:
                  this.setInputText("2");
                  break;
               case 2131361997:
                  this.setInputText("3");
                  break;
               case 2131361998:
                  this.setInputText("4");
                  break;
               case 2131361999:
                  this.setInputText("5");
                  break;
               case 2131362000:
                  this.setInputText("6");
                  break;
               case 2131362001:
                  this.setInputText("7");
                  break;
               case 2131362002:
                  this.setInputText("8");
                  break;
               case 2131362003:
                  this.setInputText("9");
            }
         } else {
            if (this.isTwoPassword) {
               this.isTwoPassword = false;
               if (!TextUtils.isEmpty(this.firstPassword) && !TextUtils.isEmpty(this.text) && this.firstPassword.equals(this.text)) {
                  this.et_number.setText("");
                  this.text = "";
                  this.et_number.setHint(2131769943);
               } else {
                  Toast.makeText(this.mContext, 2131769944, 0).show();
               }
            }

            if (!TextUtils.isEmpty(this.text)) {
               OnOkClickListener var3 = this.mOnOkClickListener;
               if (var3 != null) {
                  var3.click(this.text);
               }
            } else {
               Toast.makeText(this.mContext, 2131769943, 0).show();
            }
         }
      } else if (this.text.length() > 0) {
         String var4 = this.text;
         this.text = var4.substring(0, var4.length() - 1);
         this.setEtText();
      }

   }

   public void setBtnImage(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      this.btn_0.setBackgroundResource(var1);
      this.btn_1.setBackgroundResource(var2);
      this.btn_2.setBackgroundResource(var3);
      this.btn_3.setBackgroundResource(var4);
      this.btn_4.setBackgroundResource(var5);
      this.btn_5.setBackgroundResource(var6);
      this.btn_6.setBackgroundResource(var7);
      this.btn_7.setBackgroundResource(var8);
      this.btn_8.setBackgroundResource(var9);
      this.btn_9.setBackgroundResource(var10);
      this.btn_ok.setBackgroundResource(var11);
      this.btn_del.setBackgroundResource(var12);
   }

   public void setOnOkClickListener(boolean var1, String var2, OnOkClickListener var3) {
      this.isTwoPassword = var1;
      this.firstPassword = var2;
      this.mOnOkClickListener = var3;
   }

   public interface OnOkClickListener {
      void click(String var1);
   }
}
