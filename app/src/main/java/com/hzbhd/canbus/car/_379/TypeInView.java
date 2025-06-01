package com.hzbhd.canbus.car._379;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import java.util.Arrays;

public class TypeInView implements View.OnClickListener {
   static LinearLayout add_page1;
   static LinearLayout add_page2;
   static LinearLayout enter_code;
   static LinearLayout erase_page1;
   static LinearLayout erase_page2;
   static LinearLayout error_code;
   static LinearLayout switch_add_erase;
   EditText Add_page1_edit1;
   EditText Add_page1_edit2;
   EditText Factory_code;
   Button add_page1_enter;
   Button add_page2_close;
   int[] array = new int[5];
   int[] array1 = new int[5];
   int[] array2 = new int[5];
   Activity currentActivity;
   AlertDialog dialog;
   Button enter_code_enter_btn;
   Button enter_code_return_btn;
   Button erase_page1_no;
   Button erase_page1_yes;
   Button erase_page2_close;
   Button error_code_return_btn;
   Context mContext;
   private MsgMgr msgMgr;
   Button switch_add_btn;
   Button switch_cancel;
   Button switch_erase_btn;

   private void AddPassWard() {
      this.TypeInAdd();
      this.TypeInConfirm();
      if (Arrays.equals(this.array1, this.array2)) {
         int[] var4 = this.array2;
         int var1 = var4[0] << 4 | var4[1];
         int var3 = var4[2] << 4 | var4[3];
         int var2 = var4[4] << 4 | 0;
         if (var1 == 0 && var3 == 0 && var2 == 0) {
            Toast.makeText(this.mContext, "The password is too simple", 0);
            return;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, 111, 5, (byte)var1, (byte)var3, (byte)var2});
         add_page1.setVisibility(8);
         add_page2.setVisibility(0);
      } else {
         Toast.makeText(this.mContext, "Passwords do not match", 0);
      }

   }

   private void TypeInAdd() {
      String var4 = this.Add_page1_edit1.getText().toString();
      if (var4.length() < 5) {
         Toast.makeText(this.mContext, "Password error!", 0).show();
      } else {
         int var1 = 0;

         while(true) {
            int[] var5 = this.array1;
            if (var1 >= var5.length) {
               return;
            }

            int var2 = var1 + 1;
            var5[var1] = Integer.parseInt(var4.substring(var1, var2));
            var5 = this.array1;
            int var3 = var5[var1];
            if (var3 % 2 == 1) {
               var5[var1] = var3 + 1;
            }

            if (var5[var1] == 9) {
               var5[var1] = 0;
            }

            var1 = var2;
         }
      }
   }

   private void TypeInConfirm() {
      String var4 = this.Add_page1_edit2.getText().toString();
      if (var4.length() < 5) {
         Toast.makeText(this.mContext, "Password error!", 0).show();
      } else {
         int var1 = 0;

         while(true) {
            int[] var5 = this.array2;
            if (var1 >= var5.length) {
               return;
            }

            int var2 = var1 + 1;
            var5[var1] = Integer.parseInt(var4.substring(var1, var2));
            var5 = this.array2;
            int var3 = var5[var1];
            if (var3 % 2 == 1) {
               var5[var1] = var3 + 1;
            }

            if (var5[var1] == 9) {
               var5[var1] = 0;
            }

            var1 = var2;
         }
      }
   }

   private void TypeInFactory() {
      String var3 = this.Factory_code.getText().toString();
      int var2 = var3.length();
      int var1 = 0;
      if (var2 < 5) {
         Toast.makeText(this.mContext, "Password error!", 0).show();
      } else {
         while(true) {
            int[] var4 = this.array;
            if (var1 >= var4.length) {
               return;
            }

            var2 = var1 + 1;
            var4[var1] = Integer.parseInt(var3.substring(var1, var2));
            var1 = var2;
         }
      }
   }

   private void deletePassword() {
      erase_page1.setVisibility(8);
      erase_page2.setVisibility(0);
      CanbusMsgSender.sendMsg(new byte[]{22, 111, 6, 0, 0, 0});
   }

   private void dismiss() {
      this.dialog.dismiss();
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(this.mContext);
      }

      return this.msgMgr;
   }

   private void initView(View var1) {
      enter_code = (LinearLayout)var1.findViewById(2131362213);
      error_code = (LinearLayout)var1.findViewById(2131361818);
      switch_add_erase = (LinearLayout)var1.findViewById(2131363478);
      add_page1 = (LinearLayout)var1.findViewById(2131361794);
      add_page2 = (LinearLayout)var1.findViewById(2131361800);
      erase_page1 = (LinearLayout)var1.findViewById(2131361811);
      erase_page2 = (LinearLayout)var1.findViewById(2131361815);
      Button var2 = (Button)var1.findViewById(2131362214);
      this.enter_code_enter_btn = var2;
      var2.setOnClickListener(this);
      var2 = (Button)var1.findViewById(2131362215);
      this.enter_code_return_btn = var2;
      var2.setOnClickListener(this);
      var2 = (Button)var1.findViewById(2131361821);
      this.error_code_return_btn = var2;
      var2.setOnClickListener(this);
      var2 = (Button)var1.findViewById(2131363566);
      this.switch_add_btn = var2;
      var2.setOnClickListener(this);
      var2 = (Button)var1.findViewById(2131363569);
      this.switch_erase_btn = var2;
      var2.setOnClickListener(this);
      var2 = (Button)var1.findViewById(2131363567);
      this.switch_cancel = var2;
      var2.setOnClickListener(this);
      var2 = (Button)var1.findViewById(2131361795);
      this.add_page1_enter = var2;
      var2.setOnClickListener(this);
      var2 = (Button)var1.findViewById(2131361801);
      this.add_page2_close = var2;
      var2.setOnClickListener(this);
      var2 = (Button)var1.findViewById(2131361814);
      this.erase_page1_yes = var2;
      var2.setOnClickListener(this);
      var2 = (Button)var1.findViewById(2131361813);
      this.erase_page1_no = var2;
      var2.setOnClickListener(this);
      var2 = (Button)var1.findViewById(2131361816);
      this.erase_page2_close = var2;
      var2.setOnClickListener(this);
      this.Factory_code = (EditText)var1.findViewById(2131361823);
      this.Add_page1_edit1 = (EditText)var1.findViewById(2131361798);
      this.Add_page1_edit2 = (EditText)var1.findViewById(2131361799);
   }

   public static void toJudge(int var0) {
      LinearLayout var2 = switch_add_erase;
      if (var2 != null) {
         LinearLayout var1 = error_code;
         if (var1 != null && enter_code != null) {
            if (var0 == 1) {
               var2.setVisibility(0);
            } else {
               var1.setVisibility(0);
            }

            enter_code.setVisibility(8);
         }
      }

   }

   private void verifyPassword() {
      this.TypeInFactory();
      int[] var4 = this.array;
      int var3 = var4[0] << 4 | var4[1];
      int var2 = var4[2] << 4 | var4[3];
      int var1 = var4[4] << 4 | 0;
      if (var3 == 0 && var2 == 0 && var1 == 0) {
         Toast.makeText(this.mContext, "password error！", 0);
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 111, 4, (byte)var3, (byte)var2, (byte)var1});
      }
   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131361795:
            this.AddPassWard();
            break;
         case 2131361801:
         case 2131361816:
         case 2131361821:
         case 2131362215:
            this.dismiss();
            break;
         case 2131361813:
            switch_add_erase.setVisibility(0);
            erase_page1.setVisibility(8);
            break;
         case 2131361814:
            this.deletePassword();
            break;
         case 2131362214:
            this.verifyPassword();
            break;
         case 2131363566:
            switch_add_erase.setVisibility(8);
            add_page1.setVisibility(0);
            break;
         case 2131363567:
            switch_add_erase.setVisibility(8);
            enter_code.setVisibility(0);
            break;
         case 2131363569:
            switch_add_erase.setVisibility(8);
            erase_page1.setVisibility(0);
      }

   }

   public void showDialog(Context var1) {
      View var2 = LayoutInflater.from(var1).inflate(2131558625, (ViewGroup)null, true);
      this.dialog = (new AlertDialog.Builder(var1)).setView(var2).create();
      this.initView(var2);
      this.mContext = var1;
      this.dialog.getWindow().setBackgroundDrawableResource(17170445);
      this.dialog.setCancelable(false);
      this.dialog.show();
   }
}
