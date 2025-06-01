package com.hzbhd.canbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.util.OpenCanBusBuglyView;

public class CanBusDiagnosisActivity extends AppCompatActivity {
   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558657);
      ((TextView)this.findViewById(2131362072)).setOnClickListener(new View.OnClickListener(this) {
         final CanBusDiagnosisActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            (new OpenCanBusBuglyView()).showDialog(this.this$0);
         }
      });
      ((TextView)this.findViewById(2131362091)).setOnClickListener(new View.OnClickListener(this) {
         final CanBusDiagnosisActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.startActivity(new Intent(this.this$0, TestCanBusActivity.class));
         }
      });
   }
}
