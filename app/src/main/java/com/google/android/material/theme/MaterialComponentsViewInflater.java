package com.google.android.material.theme;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.appcompat.widget.AppCompatButton;
import com.google.android.material.button.MaterialButton;

public class MaterialComponentsViewInflater extends AppCompatViewInflater {
   protected AppCompatButton createButton(Context var1, AttributeSet var2) {
      return new MaterialButton(var1, var2);
   }
}
