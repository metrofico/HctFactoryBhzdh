package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.Set;

public final class MediaMetadataCompat implements Parcelable {
   public static final Parcelable.Creator CREATOR;
   static final ArrayMap METADATA_KEYS_TYPE;
   public static final String METADATA_KEY_ADVERTISEMENT = "android.media.metadata.ADVERTISEMENT";
   public static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
   public static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
   public static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
   public static final String METADATA_KEY_ALBUM_ART_URI = "android.media.metadata.ALBUM_ART_URI";
   public static final String METADATA_KEY_ART = "android.media.metadata.ART";
   public static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
   public static final String METADATA_KEY_ART_URI = "android.media.metadata.ART_URI";
   public static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
   public static final String METADATA_KEY_BT_FOLDER_TYPE = "android.media.metadata.BT_FOLDER_TYPE";
   public static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
   public static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
   public static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
   public static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
   public static final String METADATA_KEY_DISPLAY_DESCRIPTION = "android.media.metadata.DISPLAY_DESCRIPTION";
   public static final String METADATA_KEY_DISPLAY_ICON = "android.media.metadata.DISPLAY_ICON";
   public static final String METADATA_KEY_DISPLAY_ICON_URI = "android.media.metadata.DISPLAY_ICON_URI";
   public static final String METADATA_KEY_DISPLAY_SUBTITLE = "android.media.metadata.DISPLAY_SUBTITLE";
   public static final String METADATA_KEY_DISPLAY_TITLE = "android.media.metadata.DISPLAY_TITLE";
   public static final String METADATA_KEY_DOWNLOAD_STATUS = "android.media.metadata.DOWNLOAD_STATUS";
   public static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
   public static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
   public static final String METADATA_KEY_MEDIA_ID = "android.media.metadata.MEDIA_ID";
   public static final String METADATA_KEY_MEDIA_URI = "android.media.metadata.MEDIA_URI";
   public static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";
   public static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
   public static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
   public static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
   public static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
   public static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
   public static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";
   static final int METADATA_TYPE_BITMAP = 2;
   static final int METADATA_TYPE_LONG = 0;
   static final int METADATA_TYPE_RATING = 3;
   static final int METADATA_TYPE_TEXT = 1;
   private static final String[] PREFERRED_BITMAP_ORDER;
   private static final String[] PREFERRED_DESCRIPTION_ORDER;
   private static final String[] PREFERRED_URI_ORDER;
   private static final String TAG = "MediaMetadata";
   final Bundle mBundle;
   private MediaDescriptionCompat mDescription;
   private Object mMetadataObj;

   static {
      ArrayMap var2 = new ArrayMap();
      METADATA_KEYS_TYPE = var2;
      Integer var0 = 1;
      var2.put("android.media.metadata.TITLE", var0);
      var2.put("android.media.metadata.ARTIST", var0);
      Integer var3 = 0;
      var2.put("android.media.metadata.DURATION", var3);
      var2.put("android.media.metadata.ALBUM", var0);
      var2.put("android.media.metadata.AUTHOR", var0);
      var2.put("android.media.metadata.WRITER", var0);
      var2.put("android.media.metadata.COMPOSER", var0);
      var2.put("android.media.metadata.COMPILATION", var0);
      var2.put("android.media.metadata.DATE", var0);
      var2.put("android.media.metadata.YEAR", var3);
      var2.put("android.media.metadata.GENRE", var0);
      var2.put("android.media.metadata.TRACK_NUMBER", var3);
      var2.put("android.media.metadata.NUM_TRACKS", var3);
      var2.put("android.media.metadata.DISC_NUMBER", var3);
      var2.put("android.media.metadata.ALBUM_ARTIST", var0);
      Integer var1 = 2;
      var2.put("android.media.metadata.ART", var1);
      var2.put("android.media.metadata.ART_URI", var0);
      var2.put("android.media.metadata.ALBUM_ART", var1);
      var2.put("android.media.metadata.ALBUM_ART_URI", var0);
      Integer var4 = 3;
      var2.put("android.media.metadata.USER_RATING", var4);
      var2.put("android.media.metadata.RATING", var4);
      var2.put("android.media.metadata.DISPLAY_TITLE", var0);
      var2.put("android.media.metadata.DISPLAY_SUBTITLE", var0);
      var2.put("android.media.metadata.DISPLAY_DESCRIPTION", var0);
      var2.put("android.media.metadata.DISPLAY_ICON", var1);
      var2.put("android.media.metadata.DISPLAY_ICON_URI", var0);
      var2.put("android.media.metadata.MEDIA_ID", var0);
      var2.put("android.media.metadata.BT_FOLDER_TYPE", var3);
      var2.put("android.media.metadata.MEDIA_URI", var0);
      var2.put("android.media.metadata.ADVERTISEMENT", var3);
      var2.put("android.media.metadata.DOWNLOAD_STATUS", var3);
      PREFERRED_DESCRIPTION_ORDER = new String[]{"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
      PREFERRED_BITMAP_ORDER = new String[]{"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
      PREFERRED_URI_ORDER = new String[]{"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
      CREATOR = new Parcelable.Creator() {
         public MediaMetadataCompat createFromParcel(Parcel var1) {
            return new MediaMetadataCompat(var1);
         }

         public MediaMetadataCompat[] newArray(int var1) {
            return new MediaMetadataCompat[var1];
         }
      };
   }

   MediaMetadataCompat(Bundle var1) {
      var1 = new Bundle(var1);
      this.mBundle = var1;
      MediaSessionCompat.ensureClassLoader(var1);
   }

   MediaMetadataCompat(Parcel var1) {
      this.mBundle = var1.readBundle(MediaSessionCompat.class.getClassLoader());
   }

   public static MediaMetadataCompat fromMediaMetadata(Object var0) {
      if (var0 != null && VERSION.SDK_INT >= 21) {
         Parcel var2 = Parcel.obtain();
         MediaMetadataCompatApi21.writeToParcel(var0, var2, 0);
         var2.setDataPosition(0);
         MediaMetadataCompat var1 = (MediaMetadataCompat)CREATOR.createFromParcel(var2);
         var2.recycle();
         var1.mMetadataObj = var0;
         return var1;
      } else {
         return null;
      }
   }

   public boolean containsKey(String var1) {
      return this.mBundle.containsKey(var1);
   }

   public int describeContents() {
      return 0;
   }

   public Bitmap getBitmap(String var1) {
      Bitmap var3;
      try {
         var3 = (Bitmap)this.mBundle.getParcelable(var1);
      } catch (Exception var2) {
         Log.w("MediaMetadata", "Failed to retrieve a key as Bitmap.", var2);
         var3 = null;
      }

      return var3;
   }

   public Bundle getBundle() {
      return new Bundle(this.mBundle);
   }

   public MediaDescriptionCompat getDescription() {
      MediaDescriptionCompat var4 = this.mDescription;
      if (var4 != null) {
         return var4;
      } else {
         String var7 = this.getString("android.media.metadata.MEDIA_ID");
         CharSequence[] var8 = new CharSequence[3];
         CharSequence var10 = this.getText("android.media.metadata.DISPLAY_TITLE");
         int var1;
         int var2;
         String[] var11;
         if (!TextUtils.isEmpty(var10)) {
            var8[0] = var10;
            var8[1] = this.getText("android.media.metadata.DISPLAY_SUBTITLE");
            var8[2] = this.getText("android.media.metadata.DISPLAY_DESCRIPTION");
         } else {
            int var3 = 0;

            for(var1 = 0; var3 < 3; var3 = var2) {
               var11 = PREFERRED_DESCRIPTION_ORDER;
               if (var1 >= var11.length) {
                  break;
               }

               var10 = this.getText(var11[var1]);
               var2 = var3;
               if (!TextUtils.isEmpty(var10)) {
                  var8[var3] = var10;
                  var2 = var3 + 1;
               }

               ++var1;
            }
         }

         var1 = 0;

         Uri var6;
         Bitmap var13;
         while(true) {
            var11 = PREFERRED_BITMAP_ORDER;
            var2 = var11.length;
            var6 = null;
            if (var1 >= var2) {
               var13 = null;
               break;
            }

            var13 = this.getBitmap(var11[var1]);
            if (var13 != null) {
               break;
            }

            ++var1;
         }

         var1 = 0;

         Uri var14;
         while(true) {
            String[] var5 = PREFERRED_URI_ORDER;
            if (var1 >= var5.length) {
               var14 = null;
               break;
            }

            String var12 = this.getString(var5[var1]);
            if (!TextUtils.isEmpty(var12)) {
               var14 = Uri.parse(var12);
               break;
            }

            ++var1;
         }

         String var9 = this.getString("android.media.metadata.MEDIA_URI");
         if (!TextUtils.isEmpty(var9)) {
            var6 = Uri.parse(var9);
         }

         MediaDescriptionCompat.Builder var16 = new MediaDescriptionCompat.Builder();
         var16.setMediaId(var7);
         var16.setTitle(var8[0]);
         var16.setSubtitle(var8[1]);
         var16.setDescription(var8[2]);
         var16.setIconBitmap(var13);
         var16.setIconUri(var14);
         var16.setMediaUri(var6);
         Bundle var15 = new Bundle();
         if (this.mBundle.containsKey("android.media.metadata.BT_FOLDER_TYPE")) {
            var15.putLong("android.media.extra.BT_FOLDER_TYPE", this.getLong("android.media.metadata.BT_FOLDER_TYPE"));
         }

         if (this.mBundle.containsKey("android.media.metadata.DOWNLOAD_STATUS")) {
            var15.putLong("android.media.extra.DOWNLOAD_STATUS", this.getLong("android.media.metadata.DOWNLOAD_STATUS"));
         }

         if (!var15.isEmpty()) {
            var16.setExtras(var15);
         }

         var4 = var16.build();
         this.mDescription = var4;
         return var4;
      }
   }

   public long getLong(String var1) {
      return this.mBundle.getLong(var1, 0L);
   }

   public Object getMediaMetadata() {
      if (this.mMetadataObj == null && VERSION.SDK_INT >= 21) {
         Parcel var1 = Parcel.obtain();
         this.writeToParcel(var1, 0);
         var1.setDataPosition(0);
         this.mMetadataObj = MediaMetadataCompatApi21.createFromParcel(var1);
         var1.recycle();
      }

      return this.mMetadataObj;
   }

   public RatingCompat getRating(String var1) {
      RatingCompat var3;
      try {
         if (VERSION.SDK_INT >= 19) {
            var3 = RatingCompat.fromRating(this.mBundle.getParcelable(var1));
         } else {
            var3 = (RatingCompat)this.mBundle.getParcelable(var1);
         }
      } catch (Exception var2) {
         Log.w("MediaMetadata", "Failed to retrieve a key as Rating.", var2);
         var3 = null;
      }

      return var3;
   }

   public String getString(String var1) {
      CharSequence var2 = this.mBundle.getCharSequence(var1);
      return var2 != null ? var2.toString() : null;
   }

   public CharSequence getText(String var1) {
      return this.mBundle.getCharSequence(var1);
   }

   public Set keySet() {
      return this.mBundle.keySet();
   }

   public int size() {
      return this.mBundle.size();
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeBundle(this.mBundle);
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface BitmapKey {
   }

   public static final class Builder {
      private final Bundle mBundle;

      public Builder() {
         this.mBundle = new Bundle();
      }

      public Builder(MediaMetadataCompat var1) {
         Bundle var2 = new Bundle(var1.mBundle);
         this.mBundle = var2;
         MediaSessionCompat.ensureClassLoader(var2);
      }

      public Builder(MediaMetadataCompat var1, int var2) {
         this(var1);
         Iterator var5 = this.mBundle.keySet().iterator();

         while(true) {
            String var3;
            Bitmap var6;
            do {
               Object var4;
               do {
                  if (!var5.hasNext()) {
                     return;
                  }

                  var3 = (String)var5.next();
                  var4 = this.mBundle.get(var3);
               } while(!(var4 instanceof Bitmap));

               var6 = (Bitmap)var4;
            } while(var6.getHeight() <= var2 && var6.getWidth() <= var2);

            this.putBitmap(var3, this.scaleBitmap(var6, var2));
         }
      }

      private Bitmap scaleBitmap(Bitmap var1, int var2) {
         float var3 = (float)var2;
         var3 = Math.min(var3 / (float)var1.getWidth(), var3 / (float)var1.getHeight());
         var2 = (int)((float)var1.getHeight() * var3);
         return Bitmap.createScaledBitmap(var1, (int)((float)var1.getWidth() * var3), var2, true);
      }

      public MediaMetadataCompat build() {
         return new MediaMetadataCompat(this.mBundle);
      }

      public Builder putBitmap(String var1, Bitmap var2) {
         if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(var1) && (Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(var1) != 2) {
            throw new IllegalArgumentException("The " + var1 + " key cannot be used to put a Bitmap");
         } else {
            this.mBundle.putParcelable(var1, var2);
            return this;
         }
      }

      public Builder putLong(String var1, long var2) {
         if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(var1) && (Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(var1) != 0) {
            throw new IllegalArgumentException("The " + var1 + " key cannot be used to put a long");
         } else {
            this.mBundle.putLong(var1, var2);
            return this;
         }
      }

      public Builder putRating(String var1, RatingCompat var2) {
         if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(var1) && (Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(var1) != 3) {
            throw new IllegalArgumentException("The " + var1 + " key cannot be used to put a Rating");
         } else {
            if (VERSION.SDK_INT >= 19) {
               this.mBundle.putParcelable(var1, (Parcelable)var2.getRating());
            } else {
               this.mBundle.putParcelable(var1, var2);
            }

            return this;
         }
      }

      public Builder putString(String var1, String var2) {
         if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(var1) && (Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(var1) != 1) {
            throw new IllegalArgumentException("The " + var1 + " key cannot be used to put a String");
         } else {
            this.mBundle.putCharSequence(var1, var2);
            return this;
         }
      }

      public Builder putText(String var1, CharSequence var2) {
         if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(var1) && (Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(var1) != 1) {
            throw new IllegalArgumentException("The " + var1 + " key cannot be used to put a CharSequence");
         } else {
            this.mBundle.putCharSequence(var1, var2);
            return this;
         }
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface LongKey {
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface RatingKey {
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface TextKey {
   }
}
