package androidx.emoji2.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.SparseArray;
import androidx.core.os.TraceCompat;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class MetadataRepo {
   private static final int DEFAULT_ROOT_SIZE = 1024;
   private static final String S_TRACE_CREATE_REPO = "EmojiCompat.MetadataRepo.create";
   private final char[] mEmojiCharArray;
   private final MetadataList mMetadataList;
   private final Node mRootNode;
   private final Typeface mTypeface;

   private MetadataRepo(Typeface var1, MetadataList var2) {
      this.mTypeface = var1;
      this.mMetadataList = var2;
      this.mRootNode = new Node(1024);
      this.mEmojiCharArray = new char[var2.listLength() * 2];
      this.constructIndex(var2);
   }

   private void constructIndex(MetadataList var1) {
      int var3 = var1.listLength();

      for(int var2 = 0; var2 < var3; ++var2) {
         EmojiMetadata var4 = new EmojiMetadata(this, var2);
         Character.toChars(var4.getId(), this.mEmojiCharArray, var2 * 2);
         this.put(var4);
      }

   }

   public static MetadataRepo create(AssetManager var0, String var1) throws IOException {
      MetadataRepo var4;
      try {
         TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");
         var4 = new MetadataRepo(Typeface.createFromAsset(var0, var1), MetadataListReader.read(var0, var1));
      } finally {
         TraceCompat.endSection();
      }

      return var4;
   }

   public static MetadataRepo create(Typeface var0) {
      MetadataRepo var4;
      try {
         TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");
         MetadataList var1 = new MetadataList();
         var4 = new MetadataRepo(var0, var1);
      } finally {
         TraceCompat.endSection();
      }

      return var4;
   }

   public static MetadataRepo create(Typeface var0, InputStream var1) throws IOException {
      MetadataRepo var4;
      try {
         TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");
         var4 = new MetadataRepo(var0, MetadataListReader.read(var1));
      } finally {
         TraceCompat.endSection();
      }

      return var4;
   }

   public static MetadataRepo create(Typeface var0, ByteBuffer var1) throws IOException {
      MetadataRepo var4;
      try {
         TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");
         var4 = new MetadataRepo(var0, MetadataListReader.read(var1));
      } finally {
         TraceCompat.endSection();
      }

      return var4;
   }

   public char[] getEmojiCharArray() {
      return this.mEmojiCharArray;
   }

   public MetadataList getMetadataList() {
      return this.mMetadataList;
   }

   int getMetadataVersion() {
      return this.mMetadataList.version();
   }

   Node getRootNode() {
      return this.mRootNode;
   }

   Typeface getTypeface() {
      return this.mTypeface;
   }

   void put(EmojiMetadata var1) {
      Preconditions.checkNotNull(var1, "emoji metadata cannot be null");
      boolean var2;
      if (var1.getCodepointsLength() > 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      Preconditions.checkArgument(var2, "invalid metadata codepoint length");
      this.mRootNode.put(var1, 0, var1.getCodepointsLength() - 1);
   }

   static class Node {
      private final SparseArray mChildren;
      private EmojiMetadata mData;

      private Node() {
         this(1);
      }

      Node(int var1) {
         this.mChildren = new SparseArray(var1);
      }

      Node get(int var1) {
         SparseArray var2 = this.mChildren;
         Node var3;
         if (var2 == null) {
            var3 = null;
         } else {
            var3 = (Node)var2.get(var1);
         }

         return var3;
      }

      final EmojiMetadata getData() {
         return this.mData;
      }

      void put(EmojiMetadata var1, int var2, int var3) {
         Node var5 = this.get(var1.getCodepointAt(var2));
         Node var4 = var5;
         if (var5 == null) {
            var4 = new Node();
            this.mChildren.put(var1.getCodepointAt(var2), var4);
         }

         if (var3 > var2) {
            var4.put(var1, var2 + 1, var3);
         } else {
            var4.mData = var1;
         }

      }
   }
}
