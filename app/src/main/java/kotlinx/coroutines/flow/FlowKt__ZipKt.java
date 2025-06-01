package kotlinx.coroutines.flow;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.internal.CombineKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aq\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00030\u00012*\b\u0004\u0010\u0006\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\n\u001ae\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u000b2*\b\u0004\u0010\u0006\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\f\u001aº\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u0011\"\u0004\b\u0005\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00012:\u0010\u0006\u001a6\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0017ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a \u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u000124\u0010\u0006\u001a0\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0019ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u0088\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000120\b\u0001\u0010\u0006\u001a*\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a\u008a\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012F\u0010\u0006\u001aB\b\u0001\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u0011H\u000e¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001dø\u0001\u0000¢\u0006\u0002\u0010\"\u001a\u0082\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00030\u00012;\b\u0005\u0010\u0006\u001a5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001d¢\u0006\u0002\b&H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010'\u001av\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u000b2;\b\u0005\u0010\u0006\u001a5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001d¢\u0006\u0002\b&H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010(\u001aÍ\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u0011\"\u0004\b\u0005\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00012M\b\u0001\u0010\u0006\u001aG\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0)¢\u0006\u0002\b&ø\u0001\u0000¢\u0006\u0002\u0010*\u001a³\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0010\"\u0004\b\u0004\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00100\u00012G\b\u0001\u0010\u0006\u001aA\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0017¢\u0006\u0002\b&ø\u0001\u0000¢\u0006\u0002\u0010+\u001a\u0099\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00012A\b\u0001\u0010\u0006\u001a;\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0019¢\u0006\u0002\b&ø\u0001\u0000¢\u0006\u0002\u0010,\u001a\u009d\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012Y\b\u0001\u0010\u0006\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u0011H\u000e¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001b¢\u0006\u0002\b&ø\u0001\u0000¢\u0006\u0002\u0010-\u001a\u0084\u0001\u0010.\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00030\u00012;\b\u0005\u0010\u0006\u001a5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001d¢\u0006\u0002\b&H\u0082\bø\u0001\u0000¢\u0006\u0004\b/\u0010'\u001as\u00100\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001\"\u0004\b\u0001\u0010\u00022\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00030\u00012*\b\u0004\u0010\u0006\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007H\u0082\bø\u0001\u0000¢\u0006\u0004\b1\u0010\n\u001a!\u00102\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u000503\"\u0004\b\u0000\u0010\u0003H\u0002¢\u0006\u0002\b4\u001a\u008a\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u0002*\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012F\u0010\u0006\u001aB\b\u0001\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u0011H\u000e¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001dH\u0007ø\u0001\u0000¢\u0006\u0004\b5\u0010\"\u001a\u009d\u0001\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u0002*\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012Y\b\u0001\u0010\u0006\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020$\u0012\u0013\u0012\u0011H\r¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u0011H\u000e¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001b¢\u0006\u0002\b&H\u0007ø\u0001\u0000¢\u0006\u0004\b6\u0010-\u001ah\u00107\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0004\b\u0002\u0010\u0002*\b\u0012\u0004\u0012\u0002H\r0\u00012\f\u00108\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00012(\u0010\u0006\u001a$\b\u0001\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001dø\u0001\u0000¢\u0006\u0002\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u00069"},
   d2 = {"combine", "Lkotlinx/coroutines/flow/Flow;", "R", "T", "flows", "", "transform", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "([Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "T1", "T2", "T3", "T4", "T5", "flow", "flow2", "flow3", "flow4", "flow5", "Lkotlin/Function6;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function6;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function5;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function5;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function4;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "a", "b", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "combineTransform", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/ExtensionFunctionType;", "([Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function7;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function7;)Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function6;)Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function5;)Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "combineTransformUnsafe", "combineTransformUnsafe$FlowKt__ZipKt", "combineUnsafe", "combineUnsafe$FlowKt__ZipKt", "nullArrayFactory", "Lkotlin/Function0;", "nullArrayFactory$FlowKt__ZipKt", "flowCombine", "flowCombineTransform", "zip", "other", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__ZipKt {
   // $FF: synthetic method
   public static final Flow combine(Iterable var0, Function2 var1) {
      Object[] var2 = ((Collection)CollectionsKt.toList(var0)).toArray(new Flow[0]);
      if (var2 != null) {
         Flow[] var3 = (Flow[])var2;
         Intrinsics.needClassReification();
         return (Flow)(new Flow(var3, var1) {
            final Flow[] $flowArray$inlined;
            final Function2 $transform$inlined;

            public {
               this.$flowArray$inlined = var1;
               this.$transform$inlined = var2;
            }

            public Object collect(FlowCollector var1, Continuation var2) {
               Flow[] var3 = this.$flowArray$inlined;
               Intrinsics.needClassReification();
               Intrinsics.needClassReification();
               Object var4 = CombineKt.combineInternal(var1, var3, (Function0)(new Function0(this) {
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Object[] invoke() {
                     int var1 = this.this$0.$flowArray$inlined.length;
                     Intrinsics.reifiedOperationMarker(0, "T?");
                     return new Object[var1];
                  }
               }), (Function3)(new Function3((Continuation)null, this) {
                  Object L$0;
                  Object L$1;
                  Object L$2;
                  int label;
                  private FlowCollector p$;
                  private Object[] p$0;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var2;
                  }

                  public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                     Function3 var4 = new <anonymous constructor>(var3, this.this$0);
                     var4.p$ = var1;
                     var4.p$0 = var2;
                     return var4;
                  }

                  public final Object invoke(Object var1, Object var2, Object var3) {
                     return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
                  }

                  public final Object invokeSuspend(Object var1) {
                     Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var2 = this.label;
                     FlowCollector var3;
                     Object[] var4;
                     Object var5;
                     FlowCollector var6;
                     Object[] var9;
                     if (var2 != 0) {
                        if (var2 != 1) {
                           if (var2 != 2) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           var9 = (Object[])this.L$1;
                           var3 = (FlowCollector)this.L$0;
                           ResultKt.throwOnFailure(var1);
                           return Unit.INSTANCE;
                        }

                        var3 = (FlowCollector)this.L$2;
                        var4 = (Object[])this.L$1;
                        var6 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                        var5 = var1;
                     } else {
                        ResultKt.throwOnFailure(var1);
                        FlowCollector var8 = this.p$;
                        var9 = this.p$0;
                        Function2 var10 = this.this$0.$transform$inlined;
                        this.L$0 = var8;
                        this.L$1 = var9;
                        this.L$2 = var8;
                        this.label = 1;
                        var5 = var10.invoke(var9, this);
                        if (var5 == var7) {
                           return var7;
                        }

                        var6 = var8;
                        var4 = var9;
                        var3 = var8;
                     }

                     this.L$0 = var6;
                     this.L$1 = var4;
                     this.label = 2;
                     if (var3.emit(var5, this) == var7) {
                        return var7;
                     } else {
                        return Unit.INSTANCE;
                     }
                  }

                  public final Object invokeSuspend$$forInline(Object var1) {
                     FlowCollector var3 = this.p$;
                     Object[] var2 = this.p$0;
                     Object var4 = this.this$0.$transform$inlined.invoke(var2, this);
                     InlineMarker.mark(0);
                     var3.emit(var4, this);
                     InlineMarker.mark(2);
                     InlineMarker.mark(1);
                     return Unit.INSTANCE;
                  }
               }), var2);
               return var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var4 : Unit.INSTANCE;
            }

            public Object collect$$forInline(FlowCollector var1, Continuation var2) {
               InlineMarker.mark(4);
               ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
                  int label;
                  Object result;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Object invokeSuspend(Object var1) {
                     this.result = var1;
                     this.label |= Integer.MIN_VALUE;
                     return this.this$0.collect((FlowCollector)null, this);
                  }
               };
               InlineMarker.mark(5);
               Flow[] var3 = this.$flowArray$inlined;
               Intrinsics.needClassReification();
               Intrinsics.needClassReification();
               Function0 var4 = (Function0)(new Function0(this) {
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Object[] invoke() {
                     int var1 = this.this$0.$flowArray$inlined.length;
                     Intrinsics.reifiedOperationMarker(0, "T?");
                     return new Object[var1];
                  }
               });
               Function3 var5 = (Function3)(new Function3((Continuation)null, this) {
                  Object L$0;
                  Object L$1;
                  Object L$2;
                  int label;
                  private FlowCollector p$;
                  private Object[] p$0;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var2;
                  }

                  public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                     Function3 var4 = new <anonymous constructor>(var3, this.this$0);
                     var4.p$ = var1;
                     var4.p$0 = var2;
                     return var4;
                  }

                  public final Object invoke(Object var1, Object var2, Object var3) {
                     return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
                  }

                  public final Object invokeSuspend(Object var1) {
                     Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var2 = this.label;
                     FlowCollector var3;
                     Object[] var4;
                     Object var5;
                     FlowCollector var6;
                     Object[] var9;
                     if (var2 != 0) {
                        if (var2 != 1) {
                           if (var2 != 2) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           var9 = (Object[])this.L$1;
                           var3 = (FlowCollector)this.L$0;
                           ResultKt.throwOnFailure(var1);
                           return Unit.INSTANCE;
                        }

                        var3 = (FlowCollector)this.L$2;
                        var4 = (Object[])this.L$1;
                        var6 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                        var5 = var1;
                     } else {
                        ResultKt.throwOnFailure(var1);
                        FlowCollector var8 = this.p$;
                        var9 = this.p$0;
                        Function2 var10 = this.this$0.$transform$inlined;
                        this.L$0 = var8;
                        this.L$1 = var9;
                        this.L$2 = var8;
                        this.label = 1;
                        var5 = var10.invoke(var9, this);
                        if (var5 == var7) {
                           return var7;
                        }

                        var6 = var8;
                        var4 = var9;
                        var3 = var8;
                     }

                     this.L$0 = var6;
                     this.L$1 = var4;
                     this.label = 2;
                     if (var3.emit(var5, this) == var7) {
                        return var7;
                     } else {
                        return Unit.INSTANCE;
                     }
                  }

                  public final Object invokeSuspend$$forInline(Object var1) {
                     FlowCollector var3 = this.p$;
                     Object[] var2 = this.p$0;
                     Object var4 = this.this$0.$transform$inlined.invoke(var2, this);
                     InlineMarker.mark(0);
                     var3.emit(var4, this);
                     InlineMarker.mark(2);
                     InlineMarker.mark(1);
                     return Unit.INSTANCE;
                  }
               });
               InlineMarker.mark(0);
               CombineKt.combineInternal(var1, var3, var4, var5, var2);
               InlineMarker.mark(2);
               InlineMarker.mark(1);
               return Unit.INSTANCE;
            }
         });
      } else {
         throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
      }
   }

   public static final Flow combine(Flow var0, Flow var1, Function3 var2) {
      return FlowKt.flowCombine(var0, var1, var2);
   }

   public static final Flow combine(Flow var0, Flow var1, Flow var2, Function4 var3) {
      return (Flow)(new Flow(new Flow[]{var0, var1, var2}, var3) {
         final Flow[] $flows$inlined;
         final Function4 $transform$inlined$1;

         public {
            this.$flows$inlined = var1;
            this.$transform$inlined$1 = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = CombineKt.combineInternal(var1, this.$flows$inlined, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), (Function3)(new Function3((Continuation)null, this) {
               Object L$0;
               Object L$1;
               Object L$2;
               Object L$3;
               Object L$4;
               int label;
               private FlowCollector p$;
               private Object[] p$0;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var2;
               }

               public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                  Function3 var4 = new <anonymous constructor>(var3, this.this$0);
                  var4.p$ = var1;
                  var4.p$0 = var2;
                  return var4;
               }

               public final Object invoke(Object var1, Object var2, Object var3) {
                  return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  FlowCollector var4;
                  FlowCollector var5;
                  Object[] var6;
                  Object var12;
                  if (var2 != 0) {
                     Object[] var3;
                     if (var2 != 1) {
                        if (var2 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        var3 = (Object[])this.L$1;
                        FlowCollector var14 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                        return Unit.INSTANCE;
                     }

                     var3 = (Object[])this.L$4;
                     Continuation var11 = (Continuation)this.L$3;
                     var4 = (FlowCollector)this.L$2;
                     var6 = (Object[])this.L$1;
                     var5 = (FlowCollector)this.L$0;
                     ResultKt.throwOnFailure(var1);
                     var12 = var1;
                  } else {
                     ResultKt.throwOnFailure(var1);
                     FlowCollector var10 = this.p$;
                     Object[] var13 = this.p$0;
                     Continuation var9 = (Continuation)this;
                     Function4 var15 = this.this$0.$transform$inlined$1;
                     Object var17 = var13[0];
                     Object var16 = var13[1];
                     Object var8 = var13[2];
                     this.L$0 = var10;
                     this.L$1 = var13;
                     this.L$2 = var10;
                     this.L$3 = var9;
                     this.L$4 = var13;
                     this.label = 1;
                     InlineMarker.mark(6);
                     var12 = var15.invoke(var17, var16, var8, this);
                     InlineMarker.mark(7);
                     if (var12 == var7) {
                        return var7;
                     }

                     var5 = var10;
                     var6 = var13;
                     var4 = var10;
                  }

                  this.L$0 = var5;
                  this.L$1 = var6;
                  this.label = 2;
                  if (var4.emit(var12, this) == var7) {
                     return var7;
                  } else {
                     return Unit.INSTANCE;
                  }
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }
      });
   }

   public static final Flow combine(Flow var0, Flow var1, Flow var2, Flow var3, Function5 var4) {
      return (Flow)(new Flow(new Flow[]{var0, var1, var2, var3}, var4) {
         final Flow[] $flows$inlined;
         final Function5 $transform$inlined$1;

         public {
            this.$flows$inlined = var1;
            this.$transform$inlined$1 = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = CombineKt.combineInternal(var1, this.$flows$inlined, (Function0)(new Function0(this) {
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Object[] invoke() {
                  return new Object[this.this$0.$flows$inlined.length];
               }
            }), (Function3)(new Function3((Continuation)null, this) {
               Object L$0;
               Object L$1;
               Object L$2;
               Object L$3;
               Object L$4;
               int label;
               private FlowCollector p$;
               private Object[] p$0;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var2;
               }

               public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                  Function3 var4 = new <anonymous constructor>(var3, this.this$0);
                  var4.p$ = var1;
                  var4.p$0 = var2;
                  return var4;
               }

               public final Object invoke(Object var1, Object var2, Object var3) {
                  return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  FlowCollector var4;
                  Object var5;
                  Object[] var6;
                  FlowCollector var13;
                  if (var2 != 0) {
                     Object[] var3;
                     if (var2 != 1) {
                        if (var2 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        var3 = (Object[])this.L$1;
                        var13 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                        return Unit.INSTANCE;
                     }

                     var3 = (Object[])this.L$4;
                     Continuation var12 = (Continuation)this.L$3;
                     var4 = (FlowCollector)this.L$2;
                     var6 = (Object[])this.L$1;
                     var13 = (FlowCollector)this.L$0;
                     ResultKt.throwOnFailure(var1);
                     var5 = var1;
                  } else {
                     ResultKt.throwOnFailure(var1);
                     FlowCollector var11 = this.p$;
                     Object[] var14 = this.p$0;
                     Continuation var16 = (Continuation)this;
                     Function5 var9 = this.this$0.$transform$inlined$1;
                     Object var15 = var14[0];
                     Object var8 = var14[1];
                     Object var10 = var14[2];
                     var5 = var14[3];
                     this.L$0 = var11;
                     this.L$1 = var14;
                     this.L$2 = var11;
                     this.L$3 = var16;
                     this.L$4 = var14;
                     this.label = 1;
                     InlineMarker.mark(6);
                     var5 = var9.invoke(var15, var8, var10, var5, this);
                     InlineMarker.mark(7);
                     if (var5 == var7) {
                        return var7;
                     }

                     var13 = var11;
                     var6 = var14;
                     var4 = var11;
                  }

                  this.L$0 = var13;
                  this.L$1 = var6;
                  this.label = 2;
                  if (var4.emit(var5, this) == var7) {
                     return var7;
                  } else {
                     return Unit.INSTANCE;
                  }
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }
      });
   }

   public static final Flow combine(Flow var0, Flow var1, Flow var2, Flow var3, Flow var4, Function6 var5) {
      return (Flow)(new Flow(new Flow[]{var0, var1, var2, var3, var4}, var5) {
         final Flow[] $flows$inlined;
         final Function6 $transform$inlined$1;

         public {
            this.$flows$inlined = var1;
            this.$transform$inlined$1 = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = CombineKt.combineInternal(var1, this.$flows$inlined, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), (Function3)(new Function3((Continuation)null, this) {
               Object L$0;
               Object L$1;
               Object L$2;
               Object L$3;
               Object L$4;
               int label;
               private FlowCollector p$;
               private Object[] p$0;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var2;
               }

               public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                  Function3 var4 = new <anonymous constructor>(var3, this.this$0);
                  var4.p$ = var1;
                  var4.p$0 = var2;
                  return var4;
               }

               public final Object invoke(Object var1, Object var2, Object var3) {
                  return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  FlowCollector var4;
                  Object var5;
                  Object[] var6;
                  FlowCollector var14;
                  if (var2 != 0) {
                     Object[] var3;
                     if (var2 != 1) {
                        if (var2 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        var3 = (Object[])this.L$1;
                        var14 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                        return Unit.INSTANCE;
                     }

                     var3 = (Object[])this.L$4;
                     Continuation var13 = (Continuation)this.L$3;
                     var4 = (FlowCollector)this.L$2;
                     var6 = (Object[])this.L$1;
                     var14 = (FlowCollector)this.L$0;
                     ResultKt.throwOnFailure(var1);
                     var5 = var1;
                  } else {
                     ResultKt.throwOnFailure(var1);
                     FlowCollector var12 = this.p$;
                     Object[] var15 = this.p$0;
                     Continuation var8 = (Continuation)this;
                     Function6 var9 = this.this$0.$transform$inlined$1;
                     Object var10 = var15[0];
                     Object var11 = var15[1];
                     Object var16 = var15[2];
                     var5 = var15[3];
                     Object var17 = var15[4];
                     this.L$0 = var12;
                     this.L$1 = var15;
                     this.L$2 = var12;
                     this.L$3 = var8;
                     this.L$4 = var15;
                     this.label = 1;
                     InlineMarker.mark(6);
                     var5 = var9.invoke(var10, var11, var16, var5, var17, this);
                     InlineMarker.mark(7);
                     if (var5 == var7) {
                        return var7;
                     }

                     var14 = var12;
                     var6 = var15;
                     var4 = var12;
                  }

                  this.L$0 = var14;
                  this.L$1 = var6;
                  this.label = 2;
                  if (var4.emit(var5, this) == var7) {
                     return var7;
                  } else {
                     return Unit.INSTANCE;
                  }
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }
      });
   }

   // $FF: synthetic method
   public static final Flow combine(Flow[] var0, Function2 var1) {
      Intrinsics.needClassReification();
      return (Flow)(new Flow(var0, var1) {
         final Flow[] $flows$inlined;
         final Function2 $transform$inlined;

         public {
            this.$flows$inlined = var1;
            this.$transform$inlined = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Flow[] var3 = this.$flows$inlined;
            Intrinsics.needClassReification();
            Intrinsics.needClassReification();
            Object var4 = CombineKt.combineInternal(var1, var3, (Function0)(new Function0(this) {
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Object[] invoke() {
                  int var1 = this.this$0.$flows$inlined.length;
                  Intrinsics.reifiedOperationMarker(0, "T?");
                  return new Object[var1];
               }
            }), (Function3)(new Function3((Continuation)null, this) {
               Object L$0;
               Object L$1;
               Object L$2;
               int label;
               private FlowCollector p$;
               private Object[] p$0;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var2;
               }

               public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                  Function3 var4 = new <anonymous constructor>(var3, this.this$0);
                  var4.p$ = var1;
                  var4.p$0 = var2;
                  return var4;
               }

               public final Object invoke(Object var1, Object var2, Object var3) {
                  return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  FlowCollector var3;
                  FlowCollector var4;
                  Object[] var5;
                  Object var6;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        Object[] var9 = (Object[])this.L$1;
                        var3 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                        return Unit.INSTANCE;
                     }

                     var3 = (FlowCollector)this.L$2;
                     var5 = (Object[])this.L$1;
                     var4 = (FlowCollector)this.L$0;
                     ResultKt.throwOnFailure(var1);
                     var6 = var1;
                  } else {
                     ResultKt.throwOnFailure(var1);
                     FlowCollector var8 = this.p$;
                     var5 = this.p$0;
                     Function2 var10 = this.this$0.$transform$inlined;
                     this.L$0 = var8;
                     this.L$1 = var5;
                     this.L$2 = var8;
                     this.label = 1;
                     var6 = var10.invoke(var5, this);
                     if (var6 == var7) {
                        return var7;
                     }

                     var4 = var8;
                     var3 = var8;
                  }

                  this.L$0 = var4;
                  this.L$1 = var5;
                  this.label = 2;
                  if (var3.emit(var6, this) == var7) {
                     return var7;
                  } else {
                     return Unit.INSTANCE;
                  }
               }

               public final Object invokeSuspend$$forInline(Object var1) {
                  FlowCollector var3 = this.p$;
                  Object[] var2 = this.p$0;
                  Object var4 = this.this$0.$transform$inlined.invoke(var2, this);
                  InlineMarker.mark(0);
                  var3.emit(var4, this);
                  InlineMarker.mark(2);
                  InlineMarker.mark(1);
                  return Unit.INSTANCE;
               }
            }), var2);
            return var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var4 : Unit.INSTANCE;
         }

         public Object collect$$forInline(FlowCollector var1, Continuation var2) {
            InlineMarker.mark(4);
            ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
               int label;
               Object result;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Object invokeSuspend(Object var1) {
                  this.result = var1;
                  this.label |= Integer.MIN_VALUE;
                  return this.this$0.collect((FlowCollector)null, this);
               }
            };
            InlineMarker.mark(5);
            Flow[] var5 = this.$flows$inlined;
            Intrinsics.needClassReification();
            Intrinsics.needClassReification();
            Function0 var3 = (Function0)(new Function0(this) {
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Object[] invoke() {
                  int var1 = this.this$0.$flows$inlined.length;
                  Intrinsics.reifiedOperationMarker(0, "T?");
                  return new Object[var1];
               }
            });
            Function3 var4 = (Function3)(new Function3((Continuation)null, this) {
               Object L$0;
               Object L$1;
               Object L$2;
               int label;
               private FlowCollector p$;
               private Object[] p$0;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var2;
               }

               public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                  Function3 var4 = new <anonymous constructor>(var3, this.this$0);
                  var4.p$ = var1;
                  var4.p$0 = var2;
                  return var4;
               }

               public final Object invoke(Object var1, Object var2, Object var3) {
                  return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  FlowCollector var3;
                  FlowCollector var4;
                  Object[] var5;
                  Object var6;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        Object[] var9 = (Object[])this.L$1;
                        var3 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                        return Unit.INSTANCE;
                     }

                     var3 = (FlowCollector)this.L$2;
                     var5 = (Object[])this.L$1;
                     var4 = (FlowCollector)this.L$0;
                     ResultKt.throwOnFailure(var1);
                     var6 = var1;
                  } else {
                     ResultKt.throwOnFailure(var1);
                     FlowCollector var8 = this.p$;
                     var5 = this.p$0;
                     Function2 var10 = this.this$0.$transform$inlined;
                     this.L$0 = var8;
                     this.L$1 = var5;
                     this.L$2 = var8;
                     this.label = 1;
                     var6 = var10.invoke(var5, this);
                     if (var6 == var7) {
                        return var7;
                     }

                     var4 = var8;
                     var3 = var8;
                  }

                  this.L$0 = var4;
                  this.L$1 = var5;
                  this.label = 2;
                  if (var3.emit(var6, this) == var7) {
                     return var7;
                  } else {
                     return Unit.INSTANCE;
                  }
               }

               public final Object invokeSuspend$$forInline(Object var1) {
                  FlowCollector var3 = this.p$;
                  Object[] var2 = this.p$0;
                  Object var4 = this.this$0.$transform$inlined.invoke(var2, this);
                  InlineMarker.mark(0);
                  var3.emit(var4, this);
                  InlineMarker.mark(2);
                  InlineMarker.mark(1);
                  return Unit.INSTANCE;
               }
            });
            InlineMarker.mark(0);
            CombineKt.combineInternal(var1, var5, var3, var4, var2);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
         }
      });
   }

   // $FF: synthetic method
   public static final Flow combineTransform(Iterable var0, Function3 var1) {
      Object[] var2 = ((Collection)CollectionsKt.toList(var0)).toArray(new Flow[0]);
      if (var2 != null) {
         Flow[] var3 = (Flow[])var2;
         Intrinsics.needClassReification();
         return FlowKt.flow((Function2)(new Function2(var3, var1, (Continuation)null) {
            final Flow[] $flowArray;
            final Function3 $transform;
            Object L$0;
            int label;
            private FlowCollector p$;

            public {
               this.$flowArray = var1;
               this.$transform = var2;
            }

            public final Continuation create(Object var1, Continuation var2) {
               Function2 var3 = new <anonymous constructor>(this.$flowArray, this.$transform, var2);
               var3.p$ = (FlowCollector)var1;
               return var3;
            }

            public final Object invoke(Object var1, Object var2) {
               return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object var1) {
               Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               int var2 = this.label;
               if (var2 != 0) {
                  if (var2 != 1) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  FlowCollector var8 = (FlowCollector)this.L$0;
                  ResultKt.throwOnFailure(var1);
               } else {
                  ResultKt.throwOnFailure(var1);
                  FlowCollector var4 = this.p$;
                  Flow[] var7 = this.$flowArray;
                  Intrinsics.needClassReification();
                  Function0 var6 = (Function0)(new Function0(this) {
                     final <undefinedtype> this$0;

                     public {
                        this.this$0 = var1;
                     }

                     public final Object[] invoke() {
                        int var1 = this.this$0.$flowArray.length;
                        Intrinsics.reifiedOperationMarker(0, "T?");
                        return new Object[var1];
                     }
                  });
                  Function3 var5 = (Function3)(new Function3(this, (Continuation)null) {
                     Object L$0;
                     Object L$1;
                     int label;
                     private FlowCollector p$;
                     private Object[] p$0;
                     final <undefinedtype> this$0;

                     public {
                        this.this$0 = var1;
                     }

                     public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                        Function3 var4 = new <anonymous constructor>(this.this$0, var3);
                        var4.p$ = var1;
                        var4.p$0 = var2;
                        return var4;
                     }

                     public final Object invoke(Object var1, Object var2, Object var3) {
                        return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
                     }

                     public final Object invokeSuspend(Object var1) {
                        Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int var2 = this.label;
                        if (var2 != 0) {
                           if (var2 != 1) {
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           Object[] var7 = (Object[])this.L$1;
                           FlowCollector var8 = (FlowCollector)this.L$0;
                           ResultKt.throwOnFailure(var1);
                        } else {
                           ResultKt.throwOnFailure(var1);
                           FlowCollector var5 = this.p$;
                           Object[] var6 = this.p$0;
                           Function3 var4 = this.this$0.$transform;
                           this.L$0 = var5;
                           this.L$1 = var6;
                           this.label = 1;
                           if (var4.invoke(var5, var6, this) == var3) {
                              return var3;
                           }
                        }

                        return Unit.INSTANCE;
                     }

                     public final Object invokeSuspend$$forInline(Object var1) {
                        FlowCollector var2 = this.p$;
                        Object[] var3 = this.p$0;
                        this.this$0.$transform.invoke(var2, var3, this);
                        return Unit.INSTANCE;
                     }
                  });
                  this.L$0 = var4;
                  this.label = 1;
                  if (CombineKt.combineInternal(var4, var7, var6, var5, this) == var3) {
                     return var3;
                  }
               }

               return Unit.INSTANCE;
            }

            public final Object invokeSuspend$$forInline(Object var1) {
               FlowCollector var4 = this.p$;
               Flow[] var2 = this.$flowArray;
               Intrinsics.needClassReification();
               Function0 var3 = (Function0)(new Function0(this) {
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Object[] invoke() {
                     int var1 = this.this$0.$flowArray.length;
                     Intrinsics.reifiedOperationMarker(0, "T?");
                     return new Object[var1];
                  }
               });
               Function3 var5 = (Function3)(new Function3(this, (Continuation)null) {
                  Object L$0;
                  Object L$1;
                  int label;
                  private FlowCollector p$;
                  private Object[] p$0;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                     Function3 var4 = new <anonymous constructor>(this.this$0, var3);
                     var4.p$ = var1;
                     var4.p$0 = var2;
                     return var4;
                  }

                  public final Object invoke(Object var1, Object var2, Object var3) {
                     return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
                  }

                  public final Object invokeSuspend(Object var1) {
                     Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var2 = this.label;
                     if (var2 != 0) {
                        if (var2 != 1) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        Object[] var7 = (Object[])this.L$1;
                        FlowCollector var8 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                     } else {
                        ResultKt.throwOnFailure(var1);
                        FlowCollector var5 = this.p$;
                        Object[] var6 = this.p$0;
                        Function3 var4 = this.this$0.$transform;
                        this.L$0 = var5;
                        this.L$1 = var6;
                        this.label = 1;
                        if (var4.invoke(var5, var6, this) == var3) {
                           return var3;
                        }
                     }

                     return Unit.INSTANCE;
                  }

                  public final Object invokeSuspend$$forInline(Object var1) {
                     FlowCollector var2 = this.p$;
                     Object[] var3 = this.p$0;
                     this.this$0.$transform.invoke(var2, var3, this);
                     return Unit.INSTANCE;
                  }
               });
               InlineMarker.mark(0);
               CombineKt.combineInternal(var4, var2, var3, var5, this);
               InlineMarker.mark(2);
               InlineMarker.mark(1);
               return Unit.INSTANCE;
            }
         }));
      } else {
         throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
      }
   }

   public static final Flow combineTransform(Flow var0, Flow var1, Function4 var2) {
      return FlowKt.flow((Function2)(new Function2(new Flow[]{var0, var1}, (Continuation)null, var2) {
         final Flow[] $flows;
         final Function4 $transform$inlined;
         Object L$0;
         int label;
         private FlowCollector p$;

         public {
            this.$flows = var1;
            this.$transform$inlined = var3;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$flows, var2, this.$transform$inlined);
            var3.p$ = (FlowCollector)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               FlowCollector var8 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var5 = this.p$;
               Flow[] var6 = this.$flows;
               Function0 var7 = FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt();
               Function3 var4 = (Function3)(new Function3(this, (Continuation)null) {
                  Object L$0;
                  Object L$1;
                  Object L$2;
                  Object L$3;
                  Object L$4;
                  int label;
                  private FlowCollector p$;
                  private Object[] p$0;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                     Function3 var4 = new <anonymous constructor>(this.this$0, var3);
                     var4.p$ = var1;
                     var4.p$0 = var2;
                     return var4;
                  }

                  public final Object invoke(Object var1, Object var2, Object var3) {
                     return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
                  }

                  public final Object invokeSuspend(Object var1) {
                     Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var2 = this.label;
                     if (var2 != 0) {
                        if (var2 != 1) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        FlowCollector var10 = (FlowCollector)this.L$4;
                        Object[] var11 = (Object[])this.L$3;
                        Continuation var12 = (Continuation)this.L$2;
                        var11 = (Object[])this.L$1;
                        var10 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                     } else {
                        ResultKt.throwOnFailure(var1);
                        FlowCollector var7 = this.p$;
                        Object[] var5 = this.p$0;
                        Continuation var4 = (Continuation)this;
                        Function4 var9 = this.this$0.$transform$inlined;
                        Object var8 = var5[0];
                        Object var6 = var5[1];
                        this.L$0 = var7;
                        this.L$1 = var5;
                        this.L$2 = var4;
                        this.L$3 = var5;
                        this.L$4 = var7;
                        this.label = 1;
                        InlineMarker.mark(6);
                        var1 = var9.invoke(var7, var8, var6, this);
                        InlineMarker.mark(7);
                        if (var1 == var3) {
                           return var3;
                        }
                     }

                     return Unit.INSTANCE;
                  }
               });
               this.L$0 = var5;
               this.label = 1;
               if (CombineKt.combineInternal(var5, var6, var7, var4, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }));
   }

   public static final Flow combineTransform(Flow var0, Flow var1, Flow var2, Function5 var3) {
      return FlowKt.flow((Function2)(new Function2(new Flow[]{var0, var1, var2}, (Continuation)null, var3) {
         final Flow[] $flows;
         final Function5 $transform$inlined;
         Object L$0;
         int label;
         private FlowCollector p$;

         public {
            this.$flows = var1;
            this.$transform$inlined = var3;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$flows, var2, this.$transform$inlined);
            var3.p$ = (FlowCollector)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               FlowCollector var8 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var7 = this.p$;
               Flow[] var5 = this.$flows;
               Function0 var4 = FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt();
               Function3 var6 = (Function3)(new Function3(this, (Continuation)null) {
                  Object L$0;
                  Object L$1;
                  Object L$2;
                  Object L$3;
                  Object L$4;
                  int label;
                  private FlowCollector p$;
                  private Object[] p$0;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                     Function3 var4 = new <anonymous constructor>(this.this$0, var3);
                     var4.p$ = var1;
                     var4.p$0 = var2;
                     return var4;
                  }

                  public final Object invoke(Object var1, Object var2, Object var3) {
                     return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
                  }

                  public final Object invokeSuspend(Object var1) {
                     Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var2 = this.label;
                     if (var2 != 0) {
                        if (var2 != 1) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        FlowCollector var11 = (FlowCollector)this.L$4;
                        Object[] var12 = (Object[])this.L$3;
                        Continuation var13 = (Continuation)this.L$2;
                        var12 = (Object[])this.L$1;
                        var11 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                     } else {
                        ResultKt.throwOnFailure(var1);
                        FlowCollector var10 = this.p$;
                        Object[] var4 = this.p$0;
                        Continuation var6 = (Continuation)this;
                        Function5 var7 = this.this$0.$transform$inlined;
                        Object var5 = var4[0];
                        Object var8 = var4[1];
                        Object var9 = var4[2];
                        this.L$0 = var10;
                        this.L$1 = var4;
                        this.L$2 = var6;
                        this.L$3 = var4;
                        this.L$4 = var10;
                        this.label = 1;
                        InlineMarker.mark(6);
                        var1 = var7.invoke(var10, var5, var8, var9, this);
                        InlineMarker.mark(7);
                        if (var1 == var3) {
                           return var3;
                        }
                     }

                     return Unit.INSTANCE;
                  }
               });
               this.L$0 = var7;
               this.label = 1;
               if (CombineKt.combineInternal(var7, var5, var4, var6, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }));
   }

   public static final Flow combineTransform(Flow var0, Flow var1, Flow var2, Flow var3, Function6 var4) {
      return FlowKt.flow((Function2)(new Function2(new Flow[]{var0, var1, var2, var3}, (Continuation)null, var4) {
         final Flow[] $flows;
         final Function6 $transform$inlined;
         Object L$0;
         int label;
         private FlowCollector p$;

         public {
            this.$flows = var1;
            this.$transform$inlined = var3;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$flows, var2, this.$transform$inlined);
            var3.p$ = (FlowCollector)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               FlowCollector var8 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var4 = this.p$;
               Flow[] var7 = this.$flows;
               Function0 var5 = FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt();
               Function3 var6 = (Function3)(new Function3(this, (Continuation)null) {
                  Object L$0;
                  Object L$1;
                  Object L$2;
                  Object L$3;
                  Object L$4;
                  int label;
                  private FlowCollector p$;
                  private Object[] p$0;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                     Function3 var4 = new <anonymous constructor>(this.this$0, var3);
                     var4.p$ = var1;
                     var4.p$0 = var2;
                     return var4;
                  }

                  public final Object invoke(Object var1, Object var2, Object var3) {
                     return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
                  }

                  public final Object invokeSuspend(Object var1) {
                     Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var2 = this.label;
                     if (var2 != 0) {
                        if (var2 != 1) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        FlowCollector var11 = (FlowCollector)this.L$4;
                        Object[] var12 = (Object[])this.L$3;
                        Continuation var13 = (Continuation)this.L$2;
                        var12 = (Object[])this.L$1;
                        var11 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                     } else {
                        ResultKt.throwOnFailure(var1);
                        FlowCollector var8 = this.p$;
                        Object[] var10 = this.p$0;
                        Continuation var9 = (Continuation)this;
                        Function6 var5 = this.this$0.$transform$inlined;
                        var1 = var10[0];
                        Object var4 = var10[1];
                        Object var7 = var10[2];
                        Object var6 = var10[3];
                        this.L$0 = var8;
                        this.L$1 = var10;
                        this.L$2 = var9;
                        this.L$3 = var10;
                        this.L$4 = var8;
                        this.label = 1;
                        InlineMarker.mark(6);
                        var1 = var5.invoke(var8, var1, var4, var7, var6, this);
                        InlineMarker.mark(7);
                        if (var1 == var3) {
                           return var3;
                        }
                     }

                     return Unit.INSTANCE;
                  }
               });
               this.L$0 = var4;
               this.label = 1;
               if (CombineKt.combineInternal(var4, var7, var5, var6, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }));
   }

   public static final Flow combineTransform(Flow var0, Flow var1, Flow var2, Flow var3, Flow var4, Function7 var5) {
      return FlowKt.flow((Function2)(new Function2(new Flow[]{var0, var1, var2, var3, var4}, (Continuation)null, var5) {
         final Flow[] $flows;
         final Function7 $transform$inlined;
         Object L$0;
         int label;
         private FlowCollector p$;

         public {
            this.$flows = var1;
            this.$transform$inlined = var3;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$flows, var2, this.$transform$inlined);
            var3.p$ = (FlowCollector)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               FlowCollector var8 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var4 = this.p$;
               Flow[] var6 = this.$flows;
               Function0 var5 = FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt();
               Function3 var7 = (Function3)(new Function3(this, (Continuation)null) {
                  Object L$0;
                  Object L$1;
                  Object L$2;
                  Object L$3;
                  Object L$4;
                  int label;
                  private FlowCollector p$;
                  private Object[] p$0;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                     Function3 var4 = new <anonymous constructor>(this.this$0, var3);
                     var4.p$ = var1;
                     var4.p$0 = var2;
                     return var4;
                  }

                  public final Object invoke(Object var1, Object var2, Object var3) {
                     return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
                  }

                  public final Object invokeSuspend(Object var1) {
                     Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var2 = this.label;
                     if (var2 != 0) {
                        if (var2 != 1) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        FlowCollector var13 = (FlowCollector)this.L$4;
                        Object[] var14 = (Object[])this.L$3;
                        Continuation var15 = (Continuation)this.L$2;
                        var14 = (Object[])this.L$1;
                        var13 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                     } else {
                        ResultKt.throwOnFailure(var1);
                        FlowCollector var10 = this.p$;
                        Object[] var5 = this.p$0;
                        Continuation var11 = (Continuation)this;
                        Function7 var12 = this.this$0.$transform$inlined;
                        Object var4 = var5[0];
                        Object var8 = var5[1];
                        Object var7 = var5[2];
                        Object var9 = var5[3];
                        Object var6 = var5[4];
                        this.L$0 = var10;
                        this.L$1 = var5;
                        this.L$2 = var11;
                        this.L$3 = var5;
                        this.L$4 = var10;
                        this.label = 1;
                        InlineMarker.mark(6);
                        var1 = var12.invoke(var10, var4, var8, var7, var9, var6, this);
                        InlineMarker.mark(7);
                        if (var1 == var3) {
                           return var3;
                        }
                     }

                     return Unit.INSTANCE;
                  }
               });
               this.L$0 = var4;
               this.label = 1;
               if (CombineKt.combineInternal(var4, var6, var5, var7, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }));
   }

   // $FF: synthetic method
   public static final Flow combineTransform(Flow[] var0, Function3 var1) {
      Intrinsics.needClassReification();
      return FlowKt.flow((Function2)(new Function2(var0, var1, (Continuation)null) {
         final Flow[] $flows;
         final Function3 $transform;
         Object L$0;
         int label;
         private FlowCollector p$;

         public {
            this.$flows = var1;
            this.$transform = var2;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$flows, this.$transform, var2);
            var3.p$ = (FlowCollector)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               FlowCollector var8 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var7 = this.p$;
               Flow[] var4 = this.$flows;
               Intrinsics.needClassReification();
               Function0 var6 = (Function0)(new Function0(this) {
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Object[] invoke() {
                     int var1 = this.this$0.$flows.length;
                     Intrinsics.reifiedOperationMarker(0, "T?");
                     return new Object[var1];
                  }
               });
               Function3 var5 = (Function3)(new Function3(this, (Continuation)null) {
                  Object L$0;
                  Object L$1;
                  int label;
                  private FlowCollector p$;
                  private Object[] p$0;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                     Function3 var4 = new <anonymous constructor>(this.this$0, var3);
                     var4.p$ = var1;
                     var4.p$0 = var2;
                     return var4;
                  }

                  public final Object invoke(Object var1, Object var2, Object var3) {
                     return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
                  }

                  public final Object invokeSuspend(Object var1) {
                     Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var2 = this.label;
                     if (var2 != 0) {
                        if (var2 != 1) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        Object[] var7 = (Object[])this.L$1;
                        FlowCollector var8 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                     } else {
                        ResultKt.throwOnFailure(var1);
                        FlowCollector var4 = this.p$;
                        Object[] var6 = this.p$0;
                        Function3 var5 = this.this$0.$transform;
                        this.L$0 = var4;
                        this.L$1 = var6;
                        this.label = 1;
                        if (var5.invoke(var4, var6, this) == var3) {
                           return var3;
                        }
                     }

                     return Unit.INSTANCE;
                  }

                  public final Object invokeSuspend$$forInline(Object var1) {
                     FlowCollector var3 = this.p$;
                     Object[] var2 = this.p$0;
                     this.this$0.$transform.invoke(var3, var2, this);
                     return Unit.INSTANCE;
                  }
               });
               this.L$0 = var7;
               this.label = 1;
               if (CombineKt.combineInternal(var7, var4, var6, var5, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }

         public final Object invokeSuspend$$forInline(Object var1) {
            FlowCollector var3 = this.p$;
            Flow[] var5 = this.$flows;
            Intrinsics.needClassReification();
            Function0 var4 = (Function0)(new Function0(this) {
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Object[] invoke() {
                  int var1 = this.this$0.$flows.length;
                  Intrinsics.reifiedOperationMarker(0, "T?");
                  return new Object[var1];
               }
            });
            Function3 var2 = (Function3)(new Function3(this, (Continuation)null) {
               Object L$0;
               Object L$1;
               int label;
               private FlowCollector p$;
               private Object[] p$0;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                  Function3 var4 = new <anonymous constructor>(this.this$0, var3);
                  var4.p$ = var1;
                  var4.p$0 = var2;
                  return var4;
               }

               public final Object invoke(Object var1, Object var2, Object var3) {
                  return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     Object[] var7 = (Object[])this.L$1;
                     FlowCollector var8 = (FlowCollector)this.L$0;
                     ResultKt.throwOnFailure(var1);
                  } else {
                     ResultKt.throwOnFailure(var1);
                     FlowCollector var4 = this.p$;
                     Object[] var6 = this.p$0;
                     Function3 var5 = this.this$0.$transform;
                     this.L$0 = var4;
                     this.L$1 = var6;
                     this.label = 1;
                     if (var5.invoke(var4, var6, this) == var3) {
                        return var3;
                     }
                  }

                  return Unit.INSTANCE;
               }

               public final Object invokeSuspend$$forInline(Object var1) {
                  FlowCollector var3 = this.p$;
                  Object[] var2 = this.p$0;
                  this.this$0.$transform.invoke(var3, var2, this);
                  return Unit.INSTANCE;
               }
            });
            InlineMarker.mark(0);
            CombineKt.combineInternal(var3, var5, var4, var2, this);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
         }
      }));
   }

   // $FF: synthetic method
   private static final Flow combineTransformUnsafe$FlowKt__ZipKt(Flow[] var0, Function3 var1) {
      return FlowKt.flow((Function2)(new Function2(var0, var1, (Continuation)null) {
         final Flow[] $flows;
         final Function3 $transform;
         Object L$0;
         int label;
         private FlowCollector p$;

         public {
            this.$flows = var1;
            this.$transform = var2;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$flows, this.$transform, var2);
            var3.p$ = (FlowCollector)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               FlowCollector var8 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var5 = this.p$;
               Flow[] var7 = this.$flows;
               Function0 var6 = FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt();
               Function3 var4 = (Function3)(new Function3(this, (Continuation)null) {
                  Object L$0;
                  Object L$1;
                  int label;
                  private FlowCollector p$;
                  private Object[] p$0;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                     Function3 var4 = new <anonymous constructor>(this.this$0, var3);
                     var4.p$ = var1;
                     var4.p$0 = var2;
                     return var4;
                  }

                  public final Object invoke(Object var1, Object var2, Object var3) {
                     return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
                  }

                  public final Object invokeSuspend(Object var1) {
                     Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var2 = this.label;
                     if (var2 != 0) {
                        if (var2 != 1) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        Object[] var7 = (Object[])this.L$1;
                        FlowCollector var8 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                     } else {
                        ResultKt.throwOnFailure(var1);
                        FlowCollector var5 = this.p$;
                        Object[] var4 = this.p$0;
                        Function3 var6 = this.this$0.$transform;
                        this.L$0 = var5;
                        this.L$1 = var4;
                        this.label = 1;
                        if (var6.invoke(var5, var4, this) == var3) {
                           return var3;
                        }
                     }

                     return Unit.INSTANCE;
                  }

                  public final Object invokeSuspend$$forInline(Object var1) {
                     FlowCollector var2 = this.p$;
                     Object[] var3 = this.p$0;
                     this.this$0.$transform.invoke(var2, var3, this);
                     return Unit.INSTANCE;
                  }
               });
               this.L$0 = var5;
               this.label = 1;
               if (CombineKt.combineInternal(var5, var7, var6, var4, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }

         public final Object invokeSuspend$$forInline(Object var1) {
            FlowCollector var2 = this.p$;
            Flow[] var3 = this.$flows;
            Function0 var4 = FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt();
            Function3 var5 = (Function3)(new Function3(this, (Continuation)null) {
               Object L$0;
               Object L$1;
               int label;
               private FlowCollector p$;
               private Object[] p$0;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                  Function3 var4 = new <anonymous constructor>(this.this$0, var3);
                  var4.p$ = var1;
                  var4.p$0 = var2;
                  return var4;
               }

               public final Object invoke(Object var1, Object var2, Object var3) {
                  return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                     }

                     Object[] var7 = (Object[])this.L$1;
                     FlowCollector var8 = (FlowCollector)this.L$0;
                     ResultKt.throwOnFailure(var1);
                  } else {
                     ResultKt.throwOnFailure(var1);
                     FlowCollector var5 = this.p$;
                     Object[] var4 = this.p$0;
                     Function3 var6 = this.this$0.$transform;
                     this.L$0 = var5;
                     this.L$1 = var4;
                     this.label = 1;
                     if (var6.invoke(var5, var4, this) == var3) {
                        return var3;
                     }
                  }

                  return Unit.INSTANCE;
               }

               public final Object invokeSuspend$$forInline(Object var1) {
                  FlowCollector var2 = this.p$;
                  Object[] var3 = this.p$0;
                  this.this$0.$transform.invoke(var2, var3, this);
                  return Unit.INSTANCE;
               }
            });
            InlineMarker.mark(0);
            CombineKt.combineInternal(var2, var3, var4, var5, this);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
         }
      }));
   }

   // $FF: synthetic method
   private static final Flow combineUnsafe$FlowKt__ZipKt(Flow[] var0, Function2 var1) {
      return (Flow)(new Flow(var0, var1) {
         final Flow[] $flows$inlined;
         final Function2 $transform$inlined;

         public {
            this.$flows$inlined = var1;
            this.$transform$inlined = var2;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Object var3 = CombineKt.combineInternal(var1, this.$flows$inlined, FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt(), (Function3)(new Function3((Continuation)null, this) {
               Object L$0;
               Object L$1;
               Object L$2;
               int label;
               private FlowCollector p$;
               private Object[] p$0;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var2;
               }

               public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                  Function3 var4 = new <anonymous constructor>(var3, this.this$0);
                  var4.p$ = var1;
                  var4.p$0 = var2;
                  return var4;
               }

               public final Object invoke(Object var1, Object var2, Object var3) {
                  return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  FlowCollector var3;
                  Object[] var4;
                  Object var5;
                  FlowCollector var6;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        Object[] var9 = (Object[])this.L$1;
                        var3 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                        return Unit.INSTANCE;
                     }

                     var6 = (FlowCollector)this.L$2;
                     var4 = (Object[])this.L$1;
                     var3 = (FlowCollector)this.L$0;
                     ResultKt.throwOnFailure(var1);
                     var5 = var1;
                  } else {
                     ResultKt.throwOnFailure(var1);
                     FlowCollector var8 = this.p$;
                     var4 = this.p$0;
                     Function2 var10 = this.this$0.$transform$inlined;
                     this.L$0 = var8;
                     this.L$1 = var4;
                     this.L$2 = var8;
                     this.label = 1;
                     var5 = var10.invoke(var4, this);
                     if (var5 == var7) {
                        return var7;
                     }

                     var3 = var8;
                     var6 = var8;
                  }

                  this.L$0 = var3;
                  this.L$1 = var4;
                  this.label = 2;
                  if (var6.emit(var5, this) == var7) {
                     return var7;
                  } else {
                     return Unit.INSTANCE;
                  }
               }

               public final Object invokeSuspend$$forInline(Object var1) {
                  FlowCollector var3 = this.p$;
                  Object[] var2 = this.p$0;
                  Object var4 = this.this$0.$transform$inlined.invoke(var2, this);
                  InlineMarker.mark(0);
                  var3.emit(var4, this);
                  InlineMarker.mark(2);
                  InlineMarker.mark(1);
                  return Unit.INSTANCE;
               }
            }), var2);
            return var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var3 : Unit.INSTANCE;
         }

         public Object collect$$forInline(FlowCollector var1, Continuation var2) {
            InlineMarker.mark(4);
            ContinuationImpl var10001 = new ContinuationImpl(this, var2) {
               int label;
               Object result;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var1;
               }

               public final Object invokeSuspend(Object var1) {
                  this.result = var1;
                  this.label |= Integer.MIN_VALUE;
                  return this.this$0.collect((FlowCollector)null, this);
               }
            };
            InlineMarker.mark(5);
            Flow[] var4 = this.$flows$inlined;
            Function0 var3 = FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt();
            Function3 var5 = (Function3)(new Function3((Continuation)null, this) {
               Object L$0;
               Object L$1;
               Object L$2;
               int label;
               private FlowCollector p$;
               private Object[] p$0;
               final <undefinedtype> this$0;

               public {
                  this.this$0 = var2;
               }

               public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                  Function3 var4 = new <anonymous constructor>(var3, this.this$0);
                  var4.p$ = var1;
                  var4.p$0 = var2;
                  return var4;
               }

               public final Object invoke(Object var1, Object var2, Object var3) {
                  return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  FlowCollector var3;
                  Object[] var4;
                  Object var5;
                  FlowCollector var6;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        Object[] var9 = (Object[])this.L$1;
                        var3 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                        return Unit.INSTANCE;
                     }

                     var6 = (FlowCollector)this.L$2;
                     var4 = (Object[])this.L$1;
                     var3 = (FlowCollector)this.L$0;
                     ResultKt.throwOnFailure(var1);
                     var5 = var1;
                  } else {
                     ResultKt.throwOnFailure(var1);
                     FlowCollector var8 = this.p$;
                     var4 = this.p$0;
                     Function2 var10 = this.this$0.$transform$inlined;
                     this.L$0 = var8;
                     this.L$1 = var4;
                     this.L$2 = var8;
                     this.label = 1;
                     var5 = var10.invoke(var4, this);
                     if (var5 == var7) {
                        return var7;
                     }

                     var3 = var8;
                     var6 = var8;
                  }

                  this.L$0 = var3;
                  this.L$1 = var4;
                  this.label = 2;
                  if (var6.emit(var5, this) == var7) {
                     return var7;
                  } else {
                     return Unit.INSTANCE;
                  }
               }

               public final Object invokeSuspend$$forInline(Object var1) {
                  FlowCollector var3 = this.p$;
                  Object[] var2 = this.p$0;
                  Object var4 = this.this$0.$transform$inlined.invoke(var2, this);
                  InlineMarker.mark(0);
                  var3.emit(var4, this);
                  InlineMarker.mark(2);
                  InlineMarker.mark(1);
                  return Unit.INSTANCE;
               }
            });
            InlineMarker.mark(0);
            CombineKt.combineInternal(var1, var4, var3, var5, var2);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
         }
      });
   }

   public static final Flow flowCombine(Flow var0, Flow var1, Function3 var2) {
      return (Flow)(new Flow(var0, var1, var2) {
         final Flow $flow$inlined;
         final Flow $this_combine$inlined;
         final Function3 $transform$inlined;

         public {
            this.$this_combine$inlined = var1;
            this.$flow$inlined = var2;
            this.$transform$inlined = var3;
         }

         public Object collect(FlowCollector var1, Continuation var2) {
            Flow var3 = this.$this_combine$inlined;
            Flow var5 = this.$flow$inlined;
            Function0 var4 = FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt();
            Function3 var6 = (Function3)(new Function3((Continuation)null, this) {
               Object L$0;
               Object L$1;
               Object L$2;
               int label;
               private FlowCollector p$;
               private Object[] p$0;
               final <undefinedtype> this$0;

               {
                  this.this$0 = var2;
               }

               public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                  Function3 var4 = new <anonymous constructor>(var3, this.this$0);
                  var4.p$ = var1;
                  var4.p$0 = var2;
                  return var4;
               }

               public final Object invoke(Object var1, Object var2, Object var3) {
                  return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
               }

               public final Object invokeSuspend(Object var1) {
                  Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  int var2 = this.label;
                  FlowCollector var3;
                  FlowCollector var4;
                  Object[] var5;
                  Object var6;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 != 2) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        Object[] var9 = (Object[])this.L$1;
                        var3 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                        return Unit.INSTANCE;
                     }

                     var3 = (FlowCollector)this.L$2;
                     var5 = (Object[])this.L$1;
                     var4 = (FlowCollector)this.L$0;
                     ResultKt.throwOnFailure(var1);
                     var6 = var1;
                  } else {
                     ResultKt.throwOnFailure(var1);
                     FlowCollector var8 = this.p$;
                     var5 = this.p$0;
                     Function3 var10 = this.this$0.$transform$inlined;
                     var6 = var5[0];
                     Object var11 = var5[1];
                     this.L$0 = var8;
                     this.L$1 = var5;
                     this.L$2 = var8;
                     this.label = 1;
                     InlineMarker.mark(6);
                     var6 = var10.invoke(var6, var11, this);
                     InlineMarker.mark(7);
                     if (var6 == var7) {
                        return var7;
                     }

                     var4 = var8;
                     var3 = var8;
                  }

                  this.L$0 = var4;
                  this.L$1 = var5;
                  this.label = 2;
                  if (var3.emit(var6, this) == var7) {
                     return var7;
                  } else {
                     return Unit.INSTANCE;
                  }
               }
            });
            Object var7 = CombineKt.combineInternal(var1, new Flow[]{var3, var5}, var4, var6, var2);
            return var7 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var7 : Unit.INSTANCE;
         }
      });
   }

   public static final Flow flowCombineTransform(Flow var0, Flow var1, Function4 var2) {
      return FlowKt.flow((Function2)(new Function2(new Flow[]{var0, var1}, (Continuation)null, var2) {
         final Flow[] $flows;
         final Function4 $transform$inlined;
         Object L$0;
         int label;
         private FlowCollector p$;

         public {
            this.$flows = var1;
            this.$transform$inlined = var3;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$flows, var2, this.$transform$inlined);
            var3.p$ = (FlowCollector)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               FlowCollector var8 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var7 = this.p$;
               Flow[] var5 = this.$flows;
               Function0 var4 = FlowKt__ZipKt.nullArrayFactory$FlowKt__ZipKt();
               Function3 var6 = (Function3)(new Function3(this, (Continuation)null) {
                  Object L$0;
                  Object L$1;
                  Object L$2;
                  Object L$3;
                  Object L$4;
                  int label;
                  private FlowCollector p$;
                  private Object[] p$0;
                  final <undefinedtype> this$0;

                  public {
                     this.this$0 = var1;
                  }

                  public final Continuation create(FlowCollector var1, Object[] var2, Continuation var3) {
                     Function3 var4 = new <anonymous constructor>(this.this$0, var3);
                     var4.p$ = var1;
                     var4.p$0 = var2;
                     return var4;
                  }

                  public final Object invoke(Object var1, Object var2, Object var3) {
                     return ((<undefinedtype>)this.create((FlowCollector)var1, (Object[])var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
                  }

                  public final Object invokeSuspend(Object var1) {
                     Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var2 = this.label;
                     if (var2 != 0) {
                        if (var2 != 1) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        FlowCollector var9 = (FlowCollector)this.L$4;
                        Object[] var10 = (Object[])this.L$3;
                        Continuation var11 = (Continuation)this.L$2;
                        var10 = (Object[])this.L$1;
                        var9 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(var1);
                     } else {
                        ResultKt.throwOnFailure(var1);
                        FlowCollector var6 = this.p$;
                        Object[] var4 = this.p$0;
                        Continuation var7 = (Continuation)this;
                        Function4 var8 = this.this$0.$transform$inlined;
                        Object var5 = var4[0];
                        var1 = var4[1];
                        this.L$0 = var6;
                        this.L$1 = var4;
                        this.L$2 = var7;
                        this.L$3 = var4;
                        this.L$4 = var6;
                        this.label = 1;
                        InlineMarker.mark(6);
                        var1 = var8.invoke(var6, var5, var1, this);
                        InlineMarker.mark(7);
                        if (var1 == var3) {
                           return var3;
                        }
                     }

                     return Unit.INSTANCE;
                  }
               });
               this.L$0 = var7;
               this.label = 1;
               if (CombineKt.combineInternal(var7, var5, var4, var6, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }));
   }

   private static final Function0 nullArrayFactory$FlowKt__ZipKt() {
      return (Function0)null.INSTANCE;
   }

   public static final Flow zip(Flow var0, Flow var1, Function3 var2) {
      return CombineKt.zipImpl(var0, var1, var2);
   }
}
