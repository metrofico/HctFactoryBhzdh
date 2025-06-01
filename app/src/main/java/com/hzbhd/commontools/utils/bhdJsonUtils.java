package com.hzbhd.commontools.utils;

import android.util.JsonReader;
import com.hzbhd.util.LogUtil;
import java.io.StringReader;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class bhdJsonUtils {
   private static final String TAG = "com.hzbhd.commontools.utils.bhdJsonUtils";
   private JSONObject mJsonObject = null;

   public bhdJsonUtils(String var1) {
      this.init(var1);
   }

   private void init(String var1) {
      try {
         JSONObject var3 = new JSONObject(var1);
         this.mJsonObject = var3;
      } catch (Exception var4) {
         if (LogUtil.log7()) {
            StringBuilder var5 = (new StringBuilder()).append("init: mJsonObject=");
            boolean var2;
            if (this.mJsonObject == null) {
               var2 = true;
            } else {
               var2 = false;
            }

            LogUtil.d(var5.append(var2).append(", error: ").append(var4.toString()).toString());
         }
      }

   }

   public String ObjectToString() {
      if (!this.checkMainJson()) {
         if (LogUtil.log7()) {
            StringBuilder var2 = (new StringBuilder()).append("ObjectToString: mJsonObject=");
            boolean var1;
            if (this.mJsonObject == null) {
               var1 = true;
            } else {
               var1 = false;
            }

            LogUtil.d(var2.append(var1).toString());
         }

         return "";
      } else {
         return this.mJsonObject.toString();
      }
   }

   public boolean checkJsonFull(String var1) {
      boolean var3 = false;

      boolean var2;
      label25: {
         boolean var4;
         try {
            JSONTokener var5 = new JSONTokener(var1);
            Object var7 = var5.nextValue();
            if (var7 instanceof JSONObject) {
               break label25;
            }

            var4 = var7 instanceof JSONArray;
         } catch (Exception var6) {
            var2 = var3;
            if (LogUtil.log5()) {
               LogUtil.d("checkJsonFull: error " + var6.toString());
               var2 = var3;
            }

            return var2;
         }

         var2 = var3;
         if (!var4) {
            return var2;
         }
      }

      var2 = true;
      return var2;
   }

   public boolean checkJsonKey(String var1) {
      JSONObject var3 = this.mJsonObject;
      boolean var2;
      if (var3 != null) {
         var2 = var3.isNull(var1) ^ true;
      } else {
         if (LogUtil.log7()) {
            LogUtil.d("checkJsonKey: mJsonObject=NULL");
         }

         var2 = false;
      }

      return var2;
   }

   public boolean checkJsonKey(String var1, String var2) {
      JsonReader var11 = new JsonReader(new StringReader(var1));
      boolean var6 = false;
      boolean var5 = false;
      boolean var4 = var6;

      boolean var3;
      Exception var10000;
      label45: {
         boolean var10001;
         try {
            var11.beginObject();
         } catch (Exception var8) {
            var10000 = var8;
            var10001 = false;
            break label45;
         }

         while(true) {
            var3 = var5;
            var4 = var6;

            label51: {
               try {
                  if (!var11.hasNext()) {
                     break label51;
                  }
               } catch (Exception var10) {
                  var10000 = var10;
                  var10001 = false;
                  break;
               }

               var4 = var6;

               try {
                  if (!var11.nextName().equals(var2)) {
                     continue;
                  }
               } catch (Exception var9) {
                  var10000 = var9;
                  var10001 = false;
                  break;
               }

               var3 = true;
            }

            var4 = var3;

            try {
               var11.endObject();
               return var3;
            } catch (Exception var7) {
               var10000 = var7;
               var10001 = false;
               break;
            }
         }
      }

      Exception var12 = var10000;
      var3 = var4;
      if (LogUtil.log7()) {
         LogUtil.d("checkJsonKey: " + var12.toString());
         var3 = var4;
      }

      return var3;
   }

   public boolean checkMainJson() {
      boolean var1;
      if (this.mJsonObject != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public JSONArray optArrary(String var1) {
      if (!this.checkMainJson()) {
         if (LogUtil.log7()) {
            StringBuilder var3 = (new StringBuilder()).append("JSONArray: mJsonObject=");
            boolean var2;
            if (this.mJsonObject == null) {
               var2 = true;
            } else {
               var2 = false;
            }

            LogUtil.d(var3.append(var2).toString());
         }

         return new JSONArray();
      } else {
         return this.mJsonObject.optJSONArray(var1);
      }
   }

   public boolean optBoolean(String var1, boolean var2) {
      if (!this.checkMainJson()) {
         if (LogUtil.log7()) {
            StringBuilder var4 = (new StringBuilder()).append("optBoolean: mJsonObject=");
            boolean var3;
            if (this.mJsonObject == null) {
               var3 = true;
            } else {
               var3 = false;
            }

            LogUtil.d(var4.append(var3).toString());
         }

         return var2;
      } else {
         return this.mJsonObject.optBoolean(var1, var2);
      }
   }

   public double optDouble(String var1, double var2) {
      if (!this.checkMainJson()) {
         if (LogUtil.log7()) {
            StringBuilder var5 = (new StringBuilder()).append("optDouble: mJsonObject=");
            boolean var4;
            if (this.mJsonObject == null) {
               var4 = true;
            } else {
               var4 = false;
            }

            LogUtil.d(var5.append(var4).toString());
         }

         return var2;
      } else {
         return this.mJsonObject.optDouble(var1, var2);
      }
   }

   public int optInt(String var1, int var2) {
      if (!this.checkMainJson()) {
         if (LogUtil.log7()) {
            StringBuilder var4 = (new StringBuilder()).append("optInt: mJsonObject=");
            boolean var3;
            if (this.mJsonObject == null) {
               var3 = true;
            } else {
               var3 = false;
            }

            LogUtil.d(var4.append(var3).toString());
         }

         return var2;
      } else {
         return this.mJsonObject.optInt(var1, var2);
      }
   }

   public JSONObject optJsonObject(String var1) {
      if (!this.checkMainJson()) {
         if (LogUtil.log7()) {
            StringBuilder var3 = (new StringBuilder()).append("optObject: mJsonObject=");
            boolean var2;
            if (this.mJsonObject == null) {
               var2 = true;
            } else {
               var2 = false;
            }

            LogUtil.d(var3.append(var2).toString());
         }

         return new JSONObject();
      } else {
         return this.mJsonObject.optJSONObject(var1);
      }
   }

   public ArrayList optList(String var1, Class var2) {
      boolean var5 = this.checkMainJson();
      int var3 = 0;
      boolean var4 = false;
      if (!var5) {
         if (LogUtil.log7()) {
            StringBuilder var8 = (new StringBuilder()).append("optList: mJsonObject=");
            if (this.mJsonObject == null) {
               var4 = true;
            }

            LogUtil.d(var8.append(var4).toString());
         }

         return new ArrayList();
      } else {
         JSONArray var9 = this.mJsonObject.optJSONArray(var1);
         ArrayList var7 = new ArrayList();

         while(true) {
            try {
               if (var3 >= var9.length()) {
                  break;
               }

               var7.add(var9.get(var3));
            } catch (Exception var6) {
               if (LogUtil.log5()) {
                  LogUtil.d("optList: " + var6.toString());
               }
               break;
            }

            ++var3;
         }

         return var7;
      }
   }

   public Long optLong(String var1, int var2) {
      if (!this.checkMainJson()) {
         if (LogUtil.log7()) {
            StringBuilder var4 = (new StringBuilder()).append("optLong: mJsonObject=");
            boolean var3;
            if (this.mJsonObject == null) {
               var3 = true;
            } else {
               var3 = false;
            }

            LogUtil.d(var4.append(var3).toString());
         }

         return (long)var2;
      } else {
         return this.mJsonObject.optLong(var1, (long)var2);
      }
   }

   public Object optObject(String var1) {
      if (!this.checkMainJson()) {
         if (LogUtil.log7()) {
            StringBuilder var3 = (new StringBuilder()).append("optObject: mJsonObject=");
            boolean var2;
            if (this.mJsonObject == null) {
               var2 = true;
            } else {
               var2 = false;
            }

            LogUtil.d(var3.append(var2).toString());
         }

         return new Object();
      } else {
         return this.mJsonObject.opt(var1);
      }
   }

   public String optString(String var1, String var2) {
      if (!this.checkMainJson()) {
         if (LogUtil.log7()) {
            StringBuilder var4 = (new StringBuilder()).append("optString: mJsonObject=");
            boolean var3;
            if (this.mJsonObject == null) {
               var3 = true;
            } else {
               var3 = false;
            }

            LogUtil.d(var4.append(var3).toString());
         }

         return var2;
      } else {
         return this.mJsonObject.optString(var1, var2);
      }
   }

   public void putObject(String var1, Object var2) {
      if (!this.checkMainJson()) {
         if (LogUtil.log7()) {
            StringBuilder var5 = (new StringBuilder()).append("putObject: mJsonObject=");
            boolean var3;
            if (this.mJsonObject == null) {
               var3 = true;
            } else {
               var3 = false;
            }

            LogUtil.d(var5.append(var3).toString());
         }

      } else {
         try {
            this.mJsonObject.put(var1, var2);
         } catch (Exception var4) {
            if (LogUtil.log7()) {
               LogUtil.d("putObject: error--> " + var4.toString());
            }
         }

      }
   }
}
