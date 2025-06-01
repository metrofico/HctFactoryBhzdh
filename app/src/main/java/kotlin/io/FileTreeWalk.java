package kotlin.io;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(
   d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001a\u001b\u001cB\u0019\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0089\u0001\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b\u00128\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0017H\u0096\u0002J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0014J\u001a\u0010\u0007\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\bJ \u0010\f\u001a\u00020\u00002\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\rJ\u001a\u0010\n\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R@\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"},
   d2 = {"Lkotlin/io/FileTreeWalk;", "Lkotlin/sequences/Sequence;", "Ljava/io/File;", "start", "direction", "Lkotlin/io/FileWalkDirection;", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;)V", "onEnter", "Lkotlin/Function1;", "", "onLeave", "", "onFail", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "f", "Ljava/io/IOException;", "e", "maxDepth", "", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;I)V", "iterator", "", "depth", "function", "DirectoryState", "FileTreeWalkIterator", "WalkState", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class FileTreeWalk implements Sequence {
   private final FileWalkDirection direction;
   private final int maxDepth;
   private final Function1 onEnter;
   private final Function2 onFail;
   private final Function1 onLeave;
   private final File start;

   public FileTreeWalk(File var1, FileWalkDirection var2) {
      Intrinsics.checkNotNullParameter(var1, "start");
      Intrinsics.checkNotNullParameter(var2, "direction");
      this(var1, var2, (Function1)null, (Function1)null, (Function2)null, 0, 32, (DefaultConstructorMarker)null);
   }

   // $FF: synthetic method
   public FileTreeWalk(File var1, FileWalkDirection var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = FileWalkDirection.TOP_DOWN;
      }

      this(var1, var2);
   }

   private FileTreeWalk(File var1, FileWalkDirection var2, Function1 var3, Function1 var4, Function2 var5, int var6) {
      super();
      this.start = var1;
      this.direction = var2;
      this.onEnter = var3;
      this.onLeave = var4;
      this.onFail = var5;
      this.maxDepth = var6;
   }

   // $FF: synthetic method
   FileTreeWalk(File var1, FileWalkDirection var2, Function1 var3, Function1 var4, Function2 var5, int var6, int var7, DefaultConstructorMarker var8) {
      if ((var7 & 2) != 0) {
         var2 = FileWalkDirection.TOP_DOWN;
      }

      if ((var7 & 32) != 0) {
         var6 = Integer.MAX_VALUE;
      }

      this(var1, var2, var3, var4, var5, var6);
   }

   public Iterator iterator() {
      return (Iterator)(new FileTreeWalkIterator(this));
   }

   public final FileTreeWalk maxDepth(int var1) {
      if (var1 > 0) {
         return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, this.onFail, var1);
      } else {
         throw new IllegalArgumentException("depth must be positive, but was " + var1 + '.');
      }
   }

   public final FileTreeWalk onEnter(Function1 var1) {
      Intrinsics.checkNotNullParameter(var1, "function");
      return new FileTreeWalk(this.start, this.direction, var1, this.onLeave, this.onFail, this.maxDepth);
   }

   public final FileTreeWalk onFail(Function2 var1) {
      Intrinsics.checkNotNullParameter(var1, "function");
      return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, var1, this.maxDepth);
   }

   public final FileTreeWalk onLeave(Function1 var1) {
      Intrinsics.checkNotNullParameter(var1, "function");
      return new FileTreeWalk(this.start, this.direction, this.onEnter, var1, this.onFail, this.maxDepth);
   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"},
      d2 = {"Lkotlin/io/FileTreeWalk$DirectoryState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootDir", "Ljava/io/File;", "(Ljava/io/File;)V", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private abstract static class DirectoryState extends WalkState {
      public DirectoryState(File var1) {
         Intrinsics.checkNotNullParameter(var1, "rootDir");
         super(var1);
      }
   }

   @Metadata(
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\r\u000e\u000fB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0082\u0010R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"},
      d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;", "Lkotlin/collections/AbstractIterator;", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk;)V", "state", "Ljava/util/ArrayDeque;", "Lkotlin/io/FileTreeWalk$WalkState;", "computeNext", "", "directoryState", "Lkotlin/io/FileTreeWalk$DirectoryState;", "root", "gotoNext", "BottomUpDirectoryState", "SingleFileState", "TopDownDirectoryState", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private final class FileTreeWalkIterator extends AbstractIterator {
      private final ArrayDeque state;
      final FileTreeWalk this$0;

      public FileTreeWalkIterator(FileTreeWalk var1) {
         this.this$0 = var1;
         ArrayDeque var2 = new ArrayDeque();
         this.state = var2;
         if (var1.start.isDirectory()) {
            var2.push(this.directoryState(var1.start));
         } else if (var1.start.isFile()) {
            var2.push(new SingleFileState(this, var1.start));
         } else {
            this.done();
         }

      }

      private final DirectoryState directoryState(File var1) {
         FileWalkDirection var3 = this.this$0.direction;
         int var2 = WhenMappings.$EnumSwitchMapping$0[var3.ordinal()];
         DirectoryState var4;
         if (var2 != 1) {
            if (var2 != 2) {
               throw new NoWhenBranchMatchedException();
            }

            var4 = (DirectoryState)(new BottomUpDirectoryState(this, var1));
         } else {
            var4 = (DirectoryState)(new TopDownDirectoryState(this, var1));
         }

         return var4;
      }

      private final File gotoNext() {
         while(true) {
            WalkState var1 = (WalkState)this.state.peek();
            if (var1 == null) {
               return null;
            }

            File var2 = var1.step();
            if (var2 == null) {
               this.state.pop();
            } else {
               if (Intrinsics.areEqual((Object)var2, (Object)var1.getRoot()) || !var2.isDirectory() || this.state.size() >= this.this$0.maxDepth) {
                  return var2;
               }

               this.state.push(this.directoryState(var2));
            }
         }
      }

      protected void computeNext() {
         File var1 = this.gotoNext();
         if (var1 != null) {
            this.setNext(var1);
         } else {
            this.done();
         }

      }

      @Metadata(
         d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\r\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"},
         d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$BottomUpDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "failed", "", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "step", "kotlin-stdlib"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      private final class BottomUpDirectoryState extends DirectoryState {
         private boolean failed;
         private int fileIndex;
         private File[] fileList;
         private boolean rootVisited;
         final FileTreeWalkIterator this$0;

         public BottomUpDirectoryState(FileTreeWalkIterator var1, File var2) {
            Intrinsics.checkNotNullParameter(var2, "rootDir");
            this.this$0 = var1;
            super(var2);
         }

         public File step() {
            Function1 var3;
            File[] var5;
            if (!this.failed && this.fileList == null) {
               var3 = this.this$0.this$0.onEnter;
               boolean var2 = false;
               boolean var1 = var2;
               if (var3 != null) {
                  var1 = var2;
                  if (!(Boolean)var3.invoke(this.getRoot())) {
                     var1 = true;
                  }
               }

               if (var1) {
                  return null;
               }

               var5 = this.getRoot().listFiles();
               this.fileList = var5;
               if (var5 == null) {
                  Function2 var6 = this.this$0.this$0.onFail;
                  if (var6 != null) {
                     var6.invoke(this.getRoot(), new AccessDeniedException(this.getRoot(), (File)null, "Cannot list files in a directory", 2, (DefaultConstructorMarker)null));
                  }

                  this.failed = true;
               }
            }

            var5 = this.fileList;
            if (var5 != null) {
               int var4 = this.fileIndex;
               Intrinsics.checkNotNull(var5);
               if (var4 < var5.length) {
                  var5 = this.fileList;
                  Intrinsics.checkNotNull(var5);
                  var4 = this.fileIndex++;
                  return var5[var4];
               }
            }

            if (!this.rootVisited) {
               this.rootVisited = true;
               return this.getRoot();
            } else {
               var3 = this.this$0.this$0.onLeave;
               if (var3 != null) {
                  var3.invoke(this.getRoot());
               }

               return null;
            }
         }
      }

      @Metadata(
         d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"},
         d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$SingleFileState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootFile", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "visited", "", "step", "kotlin-stdlib"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      private final class SingleFileState extends WalkState {
         final FileTreeWalkIterator this$0;
         private boolean visited;

         public SingleFileState(FileTreeWalkIterator var1, File var2) {
            Intrinsics.checkNotNullParameter(var2, "rootFile");
            this.this$0 = var1;
            super(var2);
         }

         public File step() {
            if (this.visited) {
               return null;
            } else {
               this.visited = true;
               return this.getRoot();
            }
         }
      }

      @Metadata(
         d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"},
         d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$TopDownDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "", "step", "kotlin-stdlib"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      private final class TopDownDirectoryState extends DirectoryState {
         private int fileIndex;
         private File[] fileList;
         private boolean rootVisited;
         final FileTreeWalkIterator this$0;

         public TopDownDirectoryState(FileTreeWalkIterator var1, File var2) {
            Intrinsics.checkNotNullParameter(var2, "rootDir");
            this.this$0 = var1;
            super(var2);
         }

         public File step() {
            Function1 var6;
            if (!this.rootVisited) {
               var6 = this.this$0.this$0.onEnter;
               boolean var2 = false;
               boolean var4 = var2;
               if (var6 != null) {
                  var4 = var2;
                  if (!(Boolean)var6.invoke(this.getRoot())) {
                     var4 = true;
                  }
               }

               if (var4) {
                  return null;
               } else {
                  this.rootVisited = true;
                  return this.getRoot();
               }
            } else {
               File[] var3 = this.fileList;
               int var1;
               if (var3 != null) {
                  var1 = this.fileIndex;
                  Intrinsics.checkNotNull(var3);
                  if (var1 >= var3.length) {
                     var6 = this.this$0.this$0.onLeave;
                     if (var6 != null) {
                        var6.invoke(this.getRoot());
                     }

                     return null;
                  }
               }

               label47: {
                  if (this.fileList == null) {
                     var3 = this.getRoot().listFiles();
                     this.fileList = var3;
                     if (var3 == null) {
                        Function2 var5 = this.this$0.this$0.onFail;
                        if (var5 != null) {
                           var5.invoke(this.getRoot(), new AccessDeniedException(this.getRoot(), (File)null, "Cannot list files in a directory", 2, (DefaultConstructorMarker)null));
                        }
                     }

                     var3 = this.fileList;
                     if (var3 == null) {
                        break label47;
                     }

                     Intrinsics.checkNotNull(var3);
                     if (var3.length == 0) {
                        break label47;
                     }
                  }

                  var3 = this.fileList;
                  Intrinsics.checkNotNull(var3);
                  var1 = this.fileIndex++;
                  return var3[var1];
               }

               var6 = this.this$0.this$0.onLeave;
               if (var6 != null) {
                  var6.invoke(this.getRoot());
               }

               return null;
            }
         }
      }

      @Metadata(
         k = 3,
         mv = {1, 7, 1},
         xi = 48
      )
      public final class WhenMappings {
         public static final int[] $EnumSwitchMapping$0;

         static {
            int[] var0 = new int[FileWalkDirection.values().length];
            var0[FileWalkDirection.TOP_DOWN.ordinal()] = 1;
            var0[FileWalkDirection.BOTTOM_UP.ordinal()] = 2;
            $EnumSwitchMapping$0 = var0;
         }
      }
   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0003H&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"},
      d2 = {"Lkotlin/io/FileTreeWalk$WalkState;", "", "root", "Ljava/io/File;", "(Ljava/io/File;)V", "getRoot", "()Ljava/io/File;", "step", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private abstract static class WalkState {
      private final File root;

      public WalkState(File var1) {
         Intrinsics.checkNotNullParameter(var1, "root");
         super();
         this.root = var1;
      }

      public final File getRoot() {
         return this.root;
      }

      public abstract File step();
   }
}
