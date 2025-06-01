package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectInstance;

@Deprecated(
   level = DeprecationLevel.ERROR,
   message = "This is internal API and may be removed in the future releases"
)
@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000è\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0017\u0018\u00002\u00020X2\u00020\u00172\u00030\u0081\u00012\u00030Å\u0001:\u0006Ô\u0001Õ\u0001Ö\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J+\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0019\u0010\u0015\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001e\u001a\u0004\u0018\u00010\u0005H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001f\u001a\u0004\u0018\u00010\u0005H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u001dJ\u0019\u0010!\u001a\u00020\u00012\b\u0010 \u001a\u0004\u0018\u00010\rH\u0017¢\u0006\u0004\b!\u0010\"J\u001f\u0010!\u001a\u00020\u00112\u000e\u0010 \u001a\n\u0018\u00010#j\u0004\u0018\u0001`$H\u0016¢\u0006\u0004\b!\u0010%J\u0017\u0010&\u001a\u00020\u00012\b\u0010 \u001a\u0004\u0018\u00010\r¢\u0006\u0004\b&\u0010\"J\u0019\u0010)\u001a\u00020\u00012\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0004\b'\u0010(J\u0017\u0010*\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\rH\u0016¢\u0006\u0004\b*\u0010+J\u001b\u0010,\u001a\u0004\u0018\u00010\u00052\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b,\u0010-J\u0017\u0010.\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\rH\u0002¢\u0006\u0004\b.\u0010\"J\u000f\u00100\u001a\u00020/H\u0014¢\u0006\u0004\b0\u00101J\u0017\u00102\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\rH\u0016¢\u0006\u0004\b2\u0010\"J!\u00105\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b5\u00106J)\u0010;\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u0002072\u0006\u00109\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b;\u0010<J\u0019\u0010=\u001a\u00020\r2\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b=\u0010>J(\u0010C\u001a\u00020@2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010/2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\rH\u0080\b¢\u0006\u0004\bA\u0010BJ#\u0010D\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0014\u001a\u0002072\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\bD\u0010EJ\u0019\u0010F\u001a\u0004\u0018\u0001082\u0006\u0010\u0014\u001a\u000203H\u0002¢\u0006\u0004\bF\u0010GJ\u0011\u0010H\u001a\u00060#j\u0002`$¢\u0006\u0004\bH\u0010IJ\u0013\u0010J\u001a\u00060#j\u0002`$H\u0016¢\u0006\u0004\bJ\u0010IJ\u0011\u0010M\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0004\bK\u0010LJ\u000f\u0010N\u001a\u0004\u0018\u00010\r¢\u0006\u0004\bN\u0010OJ'\u0010P\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0014\u001a\u0002072\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002¢\u0006\u0004\bP\u0010QJ\u0019\u0010R\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0014\u001a\u000203H\u0002¢\u0006\u0004\bR\u0010SJ\u0017\u0010U\u001a\u00020\u00012\u0006\u0010T\u001a\u00020\rH\u0014¢\u0006\u0004\bU\u0010\"J\u0017\u0010W\u001a\u00020\u00112\u0006\u0010T\u001a\u00020\rH\u0010¢\u0006\u0004\bV\u0010+J\u0019\u0010\\\u001a\u00020\u00112\b\u0010Y\u001a\u0004\u0018\u00010XH\u0000¢\u0006\u0004\bZ\u0010[JF\u0010e\u001a\u00020d2\u0006\u0010]\u001a\u00020\u00012\u0006\u0010^\u001a\u00020\u00012'\u0010c\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b`\u0012\b\ba\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00110_j\u0002`b¢\u0006\u0004\be\u0010fJ6\u0010e\u001a\u00020d2'\u0010c\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b`\u0012\b\ba\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00110_j\u0002`b¢\u0006\u0004\be\u0010gJ\u0013\u0010h\u001a\u00020\u0011H\u0086@ø\u0001\u0000¢\u0006\u0004\bh\u0010\u001dJ\u000f\u0010i\u001a\u00020\u0001H\u0002¢\u0006\u0004\bi\u0010jJ\u0013\u0010k\u001a\u00020\u0011H\u0082@ø\u0001\u0000¢\u0006\u0004\bk\u0010\u001dJ&\u0010n\u001a\u00020m2\u0014\u0010l\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00110_H\u0082\b¢\u0006\u0004\bn\u0010oJ\u001b\u0010p\u001a\u0004\u0018\u00010\u00052\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\bp\u0010-J\u0019\u0010r\u001a\u00020\u00012\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0004\bq\u0010(J\u001b\u0010t\u001a\u0004\u0018\u00010\u00052\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0004\bs\u0010-JD\u0010u\u001a\u0006\u0012\u0002\b\u00030\t2'\u0010c\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b`\u0012\b\ba\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00110_j\u0002`b2\u0006\u0010]\u001a\u00020\u0001H\u0002¢\u0006\u0004\bu\u0010vJ\u000f\u0010x\u001a\u00020/H\u0010¢\u0006\u0004\bw\u00101J\u001f\u0010y\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\rH\u0002¢\u0006\u0004\by\u0010zJ2\u0010|\u001a\u00020\u0011\"\u000e\b\u0000\u0010{\u0018\u0001*\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\b\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\rH\u0082\b¢\u0006\u0004\b|\u0010zJ\u0019\u0010]\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010\rH\u0014¢\u0006\u0004\b]\u0010+J\u0019\u0010}\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0014¢\u0006\u0004\b}\u0010\u0016J\u0010\u0010\u0080\u0001\u001a\u00020\u0011H\u0010¢\u0006\u0004\b~\u0010\u007fJ\u001a\u0010\u0083\u0001\u001a\u00020\u00112\b\u0010\u0082\u0001\u001a\u00030\u0081\u0001¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J\u001b\u0010\u0086\u0001\u001a\u00020\u00112\u0007\u0010\u0014\u001a\u00030\u0085\u0001H\u0002¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u001e\u0010\u0088\u0001\u001a\u00020\u00112\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\tH\u0002¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001JI\u0010\u008e\u0001\u001a\u00020\u0011\"\u0005\b\u0000\u0010\u008a\u00012\u000e\u0010\u008c\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u008b\u00012\u001d\u0010l\u001a\u0019\b\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00000\u008d\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00050_ø\u0001\u0000¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001JX\u0010\u0093\u0001\u001a\u00020\u0011\"\u0004\b\u0000\u0010{\"\u0005\b\u0001\u0010\u008a\u00012\u000e\u0010\u008c\u0001\u001a\t\u0012\u0004\u0012\u00028\u00010\u008b\u00012$\u0010l\u001a \b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00010\u008d\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0090\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u0091\u0001\u0010\u0092\u0001J\u001e\u0010\u0095\u0001\u001a\u00020\u00112\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\tH\u0000¢\u0006\u0006\b\u0094\u0001\u0010\u0089\u0001JX\u0010\u0097\u0001\u001a\u00020\u0011\"\u0004\b\u0000\u0010{\"\u0005\b\u0001\u0010\u008a\u00012\u000e\u0010\u008c\u0001\u001a\t\u0012\u0004\u0012\u00028\u00010\u008b\u00012$\u0010l\u001a \b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00010\u008d\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0090\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u0096\u0001\u0010\u0092\u0001J\u000f\u0010\u0098\u0001\u001a\u00020\u0001¢\u0006\u0005\b\u0098\u0001\u0010jJ\u001d\u0010\u009a\u0001\u001a\u00030\u0099\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J\u001c\u0010\u009c\u0001\u001a\u00020/2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b\u009c\u0001\u0010\u009d\u0001J\u0011\u0010\u009e\u0001\u001a\u00020/H\u0007¢\u0006\u0005\b\u009e\u0001\u00101J\u0011\u0010\u009f\u0001\u001a\u00020/H\u0016¢\u0006\u0005\b\u009f\u0001\u00101J$\u0010 \u0001\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b \u0001\u0010¡\u0001J\"\u0010¢\u0001\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u0002032\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0006\b¢\u0001\u0010£\u0001J(\u0010¤\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u00052\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b¤\u0001\u0010¥\u0001J&\u0010¦\u0001\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0014\u001a\u0002032\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b¦\u0001\u0010§\u0001J-\u0010¨\u0001\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u0002072\u0006\u0010\u0018\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0082\u0010¢\u0006\u0006\b¨\u0001\u0010©\u0001J\u0019\u0010«\u0001\u001a\u0004\u0018\u000108*\u00030ª\u0001H\u0002¢\u0006\u0006\b«\u0001\u0010¬\u0001J\u001f\u0010\u00ad\u0001\u001a\u00020\u0011*\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0005\b\u00ad\u0001\u0010zJ&\u0010®\u0001\u001a\u00060#j\u0002`$*\u00020\r2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010/H\u0004¢\u0006\u0006\b®\u0001\u0010¯\u0001R\u001d\u0010³\u0001\u001a\t\u0012\u0004\u0012\u00020X0°\u00018F@\u0006¢\u0006\b\u001a\u0006\b±\u0001\u0010²\u0001R\u001a\u0010µ\u0001\u001a\u0004\u0018\u00010\r8D@\u0004X\u0084\u0004¢\u0006\u0007\u001a\u0005\b´\u0001\u0010OR\u0018\u0010·\u0001\u001a\u00020\u00018D@\u0004X\u0084\u0004¢\u0006\u0007\u001a\u0005\b¶\u0001\u0010jR\u0018\u0010¹\u0001\u001a\u00020\u00018P@\u0010X\u0090\u0004¢\u0006\u0007\u001a\u0005\b¸\u0001\u0010jR\u0018\u0010º\u0001\u001a\u00020\u00018V@\u0016X\u0096\u0004¢\u0006\u0007\u001a\u0005\bº\u0001\u0010jR\u0015\u0010»\u0001\u001a\u00020\u00018F@\u0006¢\u0006\u0007\u001a\u0005\b»\u0001\u0010jR\u0015\u0010¼\u0001\u001a\u00020\u00018F@\u0006¢\u0006\u0007\u001a\u0005\b¼\u0001\u0010jR\u0015\u0010½\u0001\u001a\u00020\u00018F@\u0006¢\u0006\u0007\u001a\u0005\b½\u0001\u0010jR\u0018\u0010¾\u0001\u001a\u00020\u00018T@\u0014X\u0094\u0004¢\u0006\u0007\u001a\u0005\b¾\u0001\u0010jR\u001b\u0010Â\u0001\u001a\u0007\u0012\u0002\b\u00030¿\u00018F@\u0006¢\u0006\b\u001a\u0006\bÀ\u0001\u0010Á\u0001R\u0018\u0010Ä\u0001\u001a\u00020\u00018P@\u0010X\u0090\u0004¢\u0006\u0007\u001a\u0005\bÃ\u0001\u0010jR\u0017\u0010È\u0001\u001a\u00030Å\u00018F@\u0006¢\u0006\b\u001a\u0006\bÆ\u0001\u0010Ç\u0001R.\u0010Î\u0001\u001a\u0004\u0018\u00010\u00192\t\u0010É\u0001\u001a\u0004\u0018\u00010\u00198@@@X\u0080\u000e¢\u0006\u0010\u001a\u0006\bÊ\u0001\u0010Ë\u0001\"\u0006\bÌ\u0001\u0010Í\u0001R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00058@@\u0000X\u0080\u0004¢\u0006\u0007\u001a\u0005\bÏ\u0001\u0010LR \u0010Ñ\u0001\u001a\u0004\u0018\u00010\r*\u0004\u0018\u00010\u00058B@\u0002X\u0082\u0004¢\u0006\u0007\u001a\u0005\bÐ\u0001\u0010>R\u001d\u0010Ò\u0001\u001a\u00020\u0001*\u0002038B@\u0002X\u0082\u0004¢\u0006\b\u001a\u0006\bÒ\u0001\u0010Ó\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006×\u0001"},
   d2 = {"Lkotlinx/coroutines/JobSupport;", "", "active", "<init>", "(Z)V", "", "expect", "Lkotlinx/coroutines/NodeList;", "list", "Lkotlinx/coroutines/JobNode;", "node", "addLastAtomic", "(Ljava/lang/Object;Lkotlinx/coroutines/NodeList;Lkotlinx/coroutines/JobNode;)Z", "", "rootCause", "", "exceptions", "", "addSuppressedExceptions", "(Ljava/lang/Throwable;Ljava/util/List;)V", "state", "afterCompletion", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/ChildJob;", "child", "Lkotlinx/coroutines/ChildHandle;", "attachChild", "(Lkotlinx/coroutines/ChildJob;)Lkotlinx/coroutines/ChildHandle;", "awaitInternal$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitInternal", "awaitSuspend", "cause", "cancel", "(Ljava/lang/Throwable;)Z", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "cancelCoroutine", "cancelImpl$kotlinx_coroutines_core", "(Ljava/lang/Object;)Z", "cancelImpl", "cancelInternal", "(Ljava/lang/Throwable;)V", "cancelMakeCompleting", "(Ljava/lang/Object;)Ljava/lang/Object;", "cancelParent", "", "cancellationExceptionMessage", "()Ljava/lang/String;", "childCancelled", "Lkotlinx/coroutines/Incomplete;", "update", "completeStateFinalization", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)V", "Lkotlinx/coroutines/JobSupport$Finishing;", "Lkotlinx/coroutines/ChildHandleNode;", "lastChild", "proposedUpdate", "continueCompleting", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "createCauseException", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "message", "Lkotlinx/coroutines/JobCancellationException;", "defaultCancellationException$kotlinx_coroutines_core", "(Ljava/lang/String;Ljava/lang/Throwable;)Lkotlinx/coroutines/JobCancellationException;", "defaultCancellationException", "finalizeFinishingState", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/lang/Object;)Ljava/lang/Object;", "firstChild", "(Lkotlinx/coroutines/Incomplete;)Lkotlinx/coroutines/ChildHandleNode;", "getCancellationException", "()Ljava/util/concurrent/CancellationException;", "getChildJobCancellationCause", "getCompletedInternal$kotlinx_coroutines_core", "()Ljava/lang/Object;", "getCompletedInternal", "getCompletionExceptionOrNull", "()Ljava/lang/Throwable;", "getFinalRootCause", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/util/List;)Ljava/lang/Throwable;", "getOrPromoteCancellingList", "(Lkotlinx/coroutines/Incomplete;)Lkotlinx/coroutines/NodeList;", "exception", "handleJobException", "handleOnCompletionException$kotlinx_coroutines_core", "handleOnCompletionException", "Lkotlinx/coroutines/Job;", "parent", "initParentJobInternal$kotlinx_coroutines_core", "(Lkotlinx/coroutines/Job;)V", "initParentJobInternal", "onCancelling", "invokeImmediately", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "handler", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnCompletion", "(ZZLkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "join", "joinInternal", "()Z", "joinSuspend", "block", "", "loopOnState", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Void;", "makeCancelling", "makeCompleting$kotlinx_coroutines_core", "makeCompleting", "makeCompletingOnce$kotlinx_coroutines_core", "makeCompletingOnce", "makeNode", "(Lkotlin/jvm/functions/Function1;Z)Lkotlinx/coroutines/JobNode;", "nameString$kotlinx_coroutines_core", "nameString", "notifyCancelling", "(Lkotlinx/coroutines/NodeList;Ljava/lang/Throwable;)V", "T", "notifyHandlers", "onCompletionInternal", "onStartInternal$kotlinx_coroutines_core", "()V", "onStartInternal", "Lkotlinx/coroutines/ParentJob;", "parentJob", "parentCancelled", "(Lkotlinx/coroutines/ParentJob;)V", "Lkotlinx/coroutines/Empty;", "promoteEmptyToNodeList", "(Lkotlinx/coroutines/Empty;)V", "promoteSingleToNodeList", "(Lkotlinx/coroutines/JobNode;)V", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlin/coroutines/Continuation;", "registerSelectClause0", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "Lkotlin/Function2;", "registerSelectClause1Internal$kotlinx_coroutines_core", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "registerSelectClause1Internal", "removeNode$kotlinx_coroutines_core", "removeNode", "selectAwaitCompletion$kotlinx_coroutines_core", "selectAwaitCompletion", "start", "", "startInternal", "(Ljava/lang/Object;)I", "stateString", "(Ljava/lang/Object;)Ljava/lang/String;", "toDebugString", "toString", "tryFinalizeSimpleState", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)Z", "tryMakeCancelling", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Throwable;)Z", "tryMakeCompleting", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "tryMakeCompletingSlowPath", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)Ljava/lang/Object;", "tryWaitForChild", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)Z", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "nextChild", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/ChildHandleNode;", "notifyCompletion", "toCancellationException", "(Ljava/lang/Throwable;Ljava/lang/String;)Ljava/util/concurrent/CancellationException;", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "children", "getCompletionCause", "completionCause", "getCompletionCauseHandled", "completionCauseHandled", "getHandlesException$kotlinx_coroutines_core", "handlesException", "isActive", "isCancelled", "isCompleted", "isCompletedExceptionally", "isScopedCoroutine", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "key", "getOnCancelComplete$kotlinx_coroutines_core", "onCancelComplete", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "onJoin", "value", "getParentHandle$kotlinx_coroutines_core", "()Lkotlinx/coroutines/ChildHandle;", "setParentHandle$kotlinx_coroutines_core", "(Lkotlinx/coroutines/ChildHandle;)V", "parentHandle", "getState$kotlinx_coroutines_core", "getExceptionOrNull", "exceptionOrNull", "isCancelling", "(Lkotlinx/coroutines/Incomplete;)Z", "AwaitContinuation", "ChildCompletion", "Finishing", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public class JobSupport implements Job, ChildJob, ParentJob, SelectClause0 {
   private static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_state");
   private volatile Object _parentHandle;
   private volatile Object _state;

   public JobSupport(boolean var1) {
      Empty var2;
      if (var1) {
         var2 = JobSupportKt.access$getEMPTY_ACTIVE$p();
      } else {
         var2 = JobSupportKt.access$getEMPTY_NEW$p();
      }

      this._state = var2;
      this._parentHandle = null;
   }

   // $FF: synthetic method
   public static final String access$cancellationExceptionMessage(JobSupport var0) {
      return var0.cancellationExceptionMessage();
   }

   private final boolean addLastAtomic(Object var1, NodeList var2, JobNode var3) {
      LockFreeLinkedListNode var7 = (LockFreeLinkedListNode)var2;
      LockFreeLinkedListNode var8 = (LockFreeLinkedListNode)var3;
      LockFreeLinkedListNode.CondAddOp var6 = (LockFreeLinkedListNode.CondAddOp)(new LockFreeLinkedListNode.CondAddOp(var8, var8, this, var1) {
         final Object $expect$inlined;
         final LockFreeLinkedListNode $node;
         final JobSupport this$0;

         public {
            this.$node = var1;
            this.this$0 = var3;
            this.$expect$inlined = var4;
         }

         public Object prepare(LockFreeLinkedListNode var1) {
            boolean var2;
            if (this.this$0.getState$kotlinx_coroutines_core() == this.$expect$inlined) {
               var2 = true;
            } else {
               var2 = false;
            }

            Object var3;
            if (var2) {
               var3 = null;
            } else {
               var3 = LockFreeLinkedListKt.getCONDITION_FALSE();
            }

            return var3;
         }
      });

      boolean var5;
      while(true) {
         int var4 = var7.getPrevNode().tryCondAddNext(var8, var7, var6);
         var5 = true;
         if (var4 == 1) {
            break;
         }

         if (var4 == 2) {
            var5 = false;
            break;
         }
      }

      return var5;
   }

   private final void addSuppressedExceptions(Throwable var1, List var2) {
      if (var2.size() > 1) {
         Set var4 = Collections.newSetFromMap((Map)(new IdentityHashMap(var2.size())));
         Throwable var3;
         if (!DebugKt.getRECOVER_STACK_TRACES()) {
            var3 = var1;
         } else {
            var3 = StackTraceRecoveryKt.unwrapImpl(var1);
         }

         Iterator var5 = var2.iterator();

         while(var5.hasNext()) {
            Throwable var6 = (Throwable)var5.next();
            if (DebugKt.getRECOVER_STACK_TRACES()) {
               var6 = StackTraceRecoveryKt.unwrapImpl(var6);
            }

            if (var6 != var1 && var6 != var3 && !(var6 instanceof CancellationException) && var4.add(var6)) {
               kotlin.ExceptionsKt.addSuppressed(var1, var6);
            }
         }

      }
   }

   private final Object cancelMakeCompleting(Object var1) {
      Object var2;
      do {
         var2 = this.getState$kotlinx_coroutines_core();
         if (!(var2 instanceof Incomplete) || var2 instanceof Finishing && ((Finishing)var2).isCompleting()) {
            return JobSupportKt.access$getCOMPLETING_ALREADY$p();
         }

         var2 = this.tryMakeCompleting(var2, new CompletedExceptionally(this.createCauseException(var1), false, 2, (DefaultConstructorMarker)null));
      } while(var2 == JobSupportKt.access$getCOMPLETING_RETRY$p());

      return var2;
   }

   private final boolean cancelParent(Throwable var1) {
      boolean var2 = this.isScopedCoroutine();
      boolean var3 = true;
      if (var2) {
         return true;
      } else {
         boolean var4 = var1 instanceof CancellationException;
         ChildHandle var5 = this.getParentHandle$kotlinx_coroutines_core();
         if (var5 != null && var5 != NonDisposableHandle.INSTANCE) {
            var2 = var3;
            if (!var5.childCancelled(var1)) {
               if (var4) {
                  var2 = var3;
               } else {
                  var2 = false;
               }
            }

            return var2;
         } else {
            return var4;
         }
      }
   }

   private final void completeStateFinalization(Incomplete var1, Object var2) {
      ChildHandle var4 = this.getParentHandle$kotlinx_coroutines_core();
      if (var4 != null) {
         var4.dispose();
         this.setParentHandle$kotlinx_coroutines_core((ChildHandle)NonDisposableHandle.INSTANCE);
      }

      boolean var3 = var2 instanceof CompletedExceptionally;
      var4 = null;
      if (!var3) {
         var2 = null;
      }

      CompletedExceptionally var5 = (CompletedExceptionally)var2;
      Throwable var9 = var4;
      if (var5 != null) {
         var9 = var5.cause;
      }

      if (var1 instanceof JobNode) {
         try {
            ((JobNode)var1).invoke(var9);
         } catch (Throwable var7) {
            this.handleOnCompletionException$kotlinx_coroutines_core((Throwable)(new CompletionHandlerException("Exception in completion handler " + var1 + " for " + this, var7)));
            return;
         }
      } else {
         NodeList var8 = var1.getList();
         if (var8 != null) {
            this.notifyCompletion(var8, var9);
         }
      }

   }

   private final void continueCompleting(Finishing var1, ChildHandleNode var2, Object var3) {
      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var4;
         if (this.getState$kotlinx_coroutines_core() == var1) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (!var4) {
            throw (Throwable)(new AssertionError());
         }
      }

      var2 = this.nextChild((LockFreeLinkedListNode)var2);
      if (var2 == null || !this.tryWaitForChild(var1, var2, var3)) {
         this.afterCompletion(this.finalizeFinishingState(var1, var3));
      }
   }

   private final Throwable createCauseException(Object var1) {
      boolean var2;
      if (var1 != null) {
         var2 = var1 instanceof Throwable;
      } else {
         var2 = true;
      }

      Throwable var3;
      if (var2) {
         if (var1 != null) {
            var3 = (Throwable)var1;
         } else {
            String var4 = (String)null;
            var3 = (Throwable)null;
            var3 = (Throwable)(new JobCancellationException(access$cancellationExceptionMessage(this), (Throwable)null, (Job)this));
         }
      } else {
         if (var1 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
         }

         var3 = (Throwable)((ParentJob)var1).getChildJobCancellationCause();
      }

      return var3;
   }

   // $FF: synthetic method
   public static JobCancellationException defaultCancellationException$kotlinx_coroutines_core$default(JobSupport var0, String var1, Throwable var2, int var3, Object var4) {
      if (var4 == null) {
         if ((var3 & 1) != 0) {
            var1 = (String)null;
            var1 = null;
         }

         if ((var3 & 2) != 0) {
            var2 = (Throwable)null;
            var2 = null;
         }

         if (var1 == null) {
            var1 = access$cancellationExceptionMessage(var0);
         }

         return new JobCancellationException(var1, var2, (Job)var0);
      } else {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defaultCancellationException");
      }
   }

   private final Object finalizeFinishingState(Finishing var1, Object var2) {
      boolean var5 = DebugKt.getASSERTIONS_ENABLED();
      boolean var4 = true;
      boolean var3;
      if (var5) {
         if (this.getState$kotlinx_coroutines_core() == var1) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (!var3) {
            throw (Throwable)(new AssertionError());
         }
      }

      if (DebugKt.getASSERTIONS_ENABLED() && !(var1.isSealed() ^ true)) {
         throw (Throwable)(new AssertionError());
      } else if (DebugKt.getASSERTIONS_ENABLED() && !var1.isCompleting()) {
         throw (Throwable)(new AssertionError());
      } else {
         Object var6;
         if (!(var2 instanceof CompletedExceptionally)) {
            var6 = null;
         } else {
            var6 = var2;
         }

         CompletedExceptionally var16 = (CompletedExceptionally)var6;
         Throwable var17;
         if (var16 != null) {
            var17 = var16.cause;
         } else {
            var17 = null;
         }

         synchronized(var1){}

         Throwable var7;
         label269: {
            Throwable var10000;
            label279: {
               List var8;
               boolean var10001;
               try {
                  var5 = var1.isCancelling();
                  var8 = var1.sealLocked(var17);
                  var7 = this.getFinalRootCause(var1, var8);
               } catch (Throwable var14) {
                  var10000 = var14;
                  var10001 = false;
                  break label279;
               }

               if (var7 == null) {
                  break label269;
               }

               label264:
               try {
                  this.addSuppressedExceptions(var7, var8);
                  break label269;
               } catch (Throwable var13) {
                  var10000 = var13;
                  var10001 = false;
                  break label264;
               }
            }

            Throwable var15 = var10000;
            throw var15;
         }

         if (var7 != null && var7 != var17) {
            var2 = new CompletedExceptionally(var7, false, 2, (DefaultConstructorMarker)null);
         }

         if (var7 != null) {
            var3 = var4;
            if (!this.cancelParent(var7)) {
               if (this.handleJobException(var7)) {
                  var3 = var4;
               } else {
                  var3 = false;
               }
            }

            if (var3) {
               if (var2 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
               }

               ((CompletedExceptionally)var2).makeHandled();
            }
         }

         if (!var5) {
            this.onCancelling(var7);
         }

         this.onCompletionInternal(var2);
         var5 = AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var1, JobSupportKt.boxIncomplete(var2));
         if (DebugKt.getASSERTIONS_ENABLED() && !var5) {
            throw (Throwable)(new AssertionError());
         } else {
            this.completeStateFinalization((Incomplete)var1, var2);
            return var2;
         }
      }
   }

   private final ChildHandleNode firstChild(Incomplete var1) {
      boolean var2 = var1 instanceof ChildHandleNode;
      Object var4 = null;
      Incomplete var3;
      if (!var2) {
         var3 = null;
      } else {
         var3 = var1;
      }

      ChildHandleNode var6 = (ChildHandleNode)var3;
      ChildHandleNode var5;
      if (var6 != null) {
         var5 = var6;
      } else {
         NodeList var7 = var1.getList();
         var5 = (ChildHandleNode)var4;
         if (var7 != null) {
            var5 = this.nextChild((LockFreeLinkedListNode)var7);
         }
      }

      return var5;
   }

   private final Throwable getExceptionOrNull(Object var1) {
      boolean var2 = var1 instanceof CompletedExceptionally;
      Object var3 = null;
      if (!var2) {
         var1 = null;
      }

      CompletedExceptionally var4 = (CompletedExceptionally)var1;
      Throwable var5 = (Throwable)var3;
      if (var4 != null) {
         var5 = var4.cause;
      }

      return var5;
   }

   private final Throwable getFinalRootCause(Finishing var1, List var2) {
      boolean var4 = var2.isEmpty();
      Object var5 = null;
      Throwable var9;
      if (var4) {
         if (var1.isCancelling()) {
            String var11 = (String)null;
            var9 = (Throwable)null;
            return (Throwable)(new JobCancellationException(access$cancellationExceptionMessage(this), (Throwable)null, (Job)this));
         } else {
            return null;
         }
      } else {
         Iterable var6 = (Iterable)var2;
         Iterator var7 = var6.iterator();

         Object var8;
         do {
            if (!var7.hasNext()) {
               var8 = null;
               break;
            }

            var8 = var7.next();
         } while(!((Throwable)var8 instanceof CancellationException ^ true));

         var9 = (Throwable)var8;
         if (var9 != null) {
            return var9;
         } else {
            Throwable var10 = (Throwable)var2.get(0);
            if (var10 instanceof TimeoutCancellationException) {
               Iterator var12 = var6.iterator();

               boolean var3;
               do {
                  var8 = var5;
                  if (!var12.hasNext()) {
                     break;
                  }

                  var8 = var12.next();
                  Throwable var13 = (Throwable)var8;
                  if (var13 != var10 && var13 instanceof TimeoutCancellationException) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }
               } while(!var3);

               var9 = (Throwable)var8;
               if (var9 != null) {
                  return var9;
               }
            }

            return var10;
         }
      }
   }

   private final NodeList getOrPromoteCancellingList(Incomplete var1) {
      NodeList var2 = var1.getList();
      NodeList var3;
      if (var2 != null) {
         var3 = var2;
      } else if (var1 instanceof Empty) {
         var3 = new NodeList();
      } else {
         if (!(var1 instanceof JobNode)) {
            throw (Throwable)(new IllegalStateException(("State should have list: " + var1).toString()));
         }

         this.promoteSingleToNodeList((JobNode)var1);
         var3 = null;
      }

      return var3;
   }

   private final boolean isCancelling(Incomplete var1) {
      boolean var2;
      if (var1 instanceof Finishing && ((Finishing)var1).isCancelling()) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private final boolean joinInternal() {
      Object var1;
      do {
         var1 = this.getState$kotlinx_coroutines_core();
         if (!(var1 instanceof Incomplete)) {
            return false;
         }
      } while(this.startInternal(var1) < 0);

      return true;
   }

   private final Void loopOnState(Function1 var1) {
      while(true) {
         var1.invoke(this.getState$kotlinx_coroutines_core());
      }
   }

   private final Object makeCancelling(Object var1) {
      Object var5 = null;
      Throwable var3 = (Throwable)null;
      var3 = null;

      while(true) {
         Object var6 = this.getState$kotlinx_coroutines_core();
         if (var6 instanceof Finishing) {
            synchronized(var6){}

            Throwable var38;
            Throwable var10000;
            label561: {
               boolean var10001;
               try {
                  if (((Finishing)var6).isSealed()) {
                     Symbol var39 = JobSupportKt.access$getTOO_LATE_TO_CANCEL$p();
                     return var39;
                  }
               } catch (Throwable var37) {
                  var10000 = var37;
                  var10001 = false;
                  break label561;
               }

               boolean var2;
               try {
                  var2 = ((Finishing)var6).isCancelling();
               } catch (Throwable var36) {
                  var10000 = var36;
                  var10001 = false;
                  break label561;
               }

               if (var1 != null || !var2) {
                  if (var3 == null) {
                     try {
                        var3 = this.createCauseException(var1);
                     } catch (Throwable var35) {
                        var10000 = var35;
                        var10001 = false;
                        break label561;
                     }
                  }

                  try {
                     ((Finishing)var6).addExceptionLocked(var3);
                  } catch (Throwable var34) {
                     var10000 = var34;
                     var10001 = false;
                     break label561;
                  }
               }

               try {
                  var3 = ((Finishing)var6).getRootCause();
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label561;
               }

               var38 = (Throwable)var5;
               if (var2 ^ true) {
                  var38 = var3;
               }

               if (var38 != null) {
                  this.notifyCancelling(((Finishing)var6).getList(), var38);
               }

               return JobSupportKt.access$getCOMPLETING_ALREADY$p();
            }

            var38 = var10000;
            throw var38;
         }

         if (!(var6 instanceof Incomplete)) {
            return JobSupportKt.access$getTOO_LATE_TO_CANCEL$p();
         }

         Throwable var4;
         if (var3 != null) {
            var4 = var3;
         } else {
            var4 = this.createCauseException(var1);
         }

         Incomplete var7 = (Incomplete)var6;
         if (var7.isActive()) {
            var3 = var4;
            if (this.tryMakeCancelling(var7, var4)) {
               return JobSupportKt.access$getCOMPLETING_ALREADY$p();
            }
         } else {
            Object var40 = this.tryMakeCompleting(var6, new CompletedExceptionally(var4, false, 2, (DefaultConstructorMarker)null));
            if (var40 == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
               throw (Throwable)(new IllegalStateException(("Cannot happen in " + var6).toString()));
            }

            if (var40 != JobSupportKt.access$getCOMPLETING_RETRY$p()) {
               return var40;
            }

            var3 = var4;
         }
      }
   }

   private final JobNode makeNode(Function1 var1, boolean var2) {
      boolean var3 = true;
      boolean var4 = true;
      JobSupport var6 = null;
      Function1 var5 = null;
      JobNode var9;
      if (var2) {
         if (var1 instanceof JobCancellingNode) {
            var5 = var1;
         }

         JobCancellingNode var8;
         label61: {
            JobCancellingNode var10 = (JobCancellingNode)var5;
            if (var10 != null) {
               if (DebugKt.getASSERTIONS_ENABLED()) {
                  Job var7 = var10.job;
                  var6 = (JobSupport)this;
                  if (var7 == this) {
                     var3 = var4;
                  } else {
                     var3 = false;
                  }

                  if (!var3) {
                     throw (Throwable)(new AssertionError());
                  }
               }

               if (var10 != null) {
                  var8 = var10;
                  break label61;
               }
            }

            var8 = (JobCancellingNode)(new InvokeOnCancelling((Job)this, var1));
         }

         var9 = (JobNode)var8;
      } else {
         if (!(var1 instanceof JobNode)) {
            var5 = var6;
         } else {
            var5 = var1;
         }

         JobNode var12 = (JobNode)var5;
         if (var12 != null) {
            if (DebugKt.getASSERTIONS_ENABLED()) {
               Job var11 = var12.job;
               JobSupport var13 = (JobSupport)this;
               if (var11 != this || var12 instanceof JobCancellingNode) {
                  var3 = false;
               }

               if (!var3) {
                  throw (Throwable)(new AssertionError());
               }
            }

            if (var12 != null) {
               var9 = var12;
               return var9;
            }
         }

         var9 = (JobNode)(new InvokeOnCompletion((Job)this, var1));
      }

      return var9;
   }

   private final ChildHandleNode nextChild(LockFreeLinkedListNode var1) {
      while(true) {
         LockFreeLinkedListNode var2 = var1;
         if (!var1.isRemoved()) {
            while(true) {
               while(true) {
                  var1 = var2.getNextNode();
                  if (var1.isRemoved()) {
                     var2 = var1;
                  } else {
                     if (var1 instanceof ChildHandleNode) {
                        return (ChildHandleNode)var1;
                     }

                     var2 = var1;
                     if (var1 instanceof NodeList) {
                        return null;
                     }
                  }
               }
            }
         }

         var1 = var1.getPrevNode();
      }
   }

   private final void notifyCancelling(NodeList var1, Throwable var2) {
      this.onCancelling(var2);
      Throwable var3 = null;
      Throwable var4 = (Throwable)null;
      LockFreeLinkedListHead var5 = (LockFreeLinkedListHead)var1;
      Object var9 = var5.getNext();
      if (var9 == null) {
         throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
      } else {
         for(LockFreeLinkedListNode var10 = (LockFreeLinkedListNode)var9; Intrinsics.areEqual((Object)var10, (Object)var5) ^ true; var3 = var4) {
            var4 = var3;
            if (var10 instanceof JobCancellingNode) {
               label86: {
                  JobNode var13 = (JobNode)var10;

                  try {
                     var13.invoke(var2);
                  } catch (Throwable var8) {
                     if (var3 != null) {
                        kotlin.ExceptionsKt.addSuppressed(var3, var8);
                        if (var3 != null) {
                           var4 = var3;
                           break label86;
                        }
                     }

                     JobSupport var11 = (JobSupport)this;
                     var4 = (Throwable)(new CompletionHandlerException("Exception in completion handler " + var13 + " for " + this, var8));
                     Unit var12 = Unit.INSTANCE;
                     break label86;
                  }

                  var4 = var3;
               }
            }

            var10 = var10.getNextNode();
         }

         if (var3 != null) {
            this.handleOnCompletionException$kotlinx_coroutines_core(var3);
         }

         this.cancelParent(var2);
      }
   }

   private final void notifyCompletion(NodeList var1, Throwable var2) {
      Throwable var3 = null;
      Throwable var4 = (Throwable)null;
      LockFreeLinkedListHead var5 = (LockFreeLinkedListHead)var1;
      Object var9 = var5.getNext();
      if (var9 == null) {
         throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
      } else {
         for(LockFreeLinkedListNode var10 = (LockFreeLinkedListNode)var9; Intrinsics.areEqual((Object)var10, (Object)var5) ^ true; var3 = var4) {
            var4 = var3;
            if (var10 instanceof JobNode) {
               label86: {
                  JobNode var13 = (JobNode)var10;

                  try {
                     var13.invoke(var2);
                  } catch (Throwable var8) {
                     if (var3 != null) {
                        kotlin.ExceptionsKt.addSuppressed(var3, var8);
                        if (var3 != null) {
                           var4 = var3;
                           break label86;
                        }
                     }

                     JobSupport var11 = (JobSupport)this;
                     var4 = (Throwable)(new CompletionHandlerException("Exception in completion handler " + var13 + " for " + this, var8));
                     Unit var12 = Unit.INSTANCE;
                     break label86;
                  }

                  var4 = var3;
               }
            }

            var10 = var10.getNextNode();
         }

         if (var3 != null) {
            this.handleOnCompletionException$kotlinx_coroutines_core(var3);
         }

      }
   }

   // $FF: synthetic method
   private final void notifyHandlers(NodeList var1, Throwable var2) {
      Throwable var3 = null;
      Throwable var4 = (Throwable)null;
      LockFreeLinkedListHead var5 = (LockFreeLinkedListHead)var1;
      Object var9 = var5.getNext();
      if (var9 == null) {
         throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
      } else {
         for(LockFreeLinkedListNode var10 = (LockFreeLinkedListNode)var9; Intrinsics.areEqual((Object)var10, (Object)var5) ^ true; var3 = var4) {
            Intrinsics.reifiedOperationMarker(3, "T");
            var4 = var3;
            if (var10 instanceof LockFreeLinkedListNode) {
               label86: {
                  JobNode var6 = (JobNode)var10;

                  try {
                     var6.invoke(var2);
                  } catch (Throwable var8) {
                     if (var3 != null) {
                        kotlin.ExceptionsKt.addSuppressed(var3, var8);
                        if (var3 != null) {
                           var4 = var3;
                           break label86;
                        }
                     }

                     JobSupport var11 = (JobSupport)this;
                     var4 = (Throwable)(new CompletionHandlerException("Exception in completion handler " + var6 + " for " + this, var8));
                     Unit var12 = Unit.INSTANCE;
                     break label86;
                  }

                  var4 = var3;
               }
            }

            var10 = var10.getNextNode();
         }

         if (var3 != null) {
            this.handleOnCompletionException$kotlinx_coroutines_core(var3);
         }

      }
   }

   private final void promoteEmptyToNodeList(Empty var1) {
      NodeList var2 = new NodeList();
      Incomplete var3;
      if (var1.isActive()) {
         var3 = (Incomplete)var2;
      } else {
         var3 = (Incomplete)(new InactiveNodeList(var2));
      }

      AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var1, var3);
   }

   private final void promoteSingleToNodeList(JobNode var1) {
      var1.addOneIfEmpty((LockFreeLinkedListNode)(new NodeList()));
      LockFreeLinkedListNode var2 = var1.getNextNode();
      AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var1, var2);
   }

   private final int startInternal(Object var1) {
      if (var1 instanceof Empty) {
         if (((Empty)var1).isActive()) {
            return 0;
         } else if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var1, JobSupportKt.access$getEMPTY_ACTIVE$p())) {
            return -1;
         } else {
            this.onStartInternal$kotlinx_coroutines_core();
            return 1;
         }
      } else if (var1 instanceof InactiveNodeList) {
         if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var1, ((InactiveNodeList)var1).getList())) {
            return -1;
         } else {
            this.onStartInternal$kotlinx_coroutines_core();
            return 1;
         }
      } else {
         return 0;
      }
   }

   private final String stateString(Object var1) {
      boolean var2 = var1 instanceof Finishing;
      String var3 = "Active";
      String var5;
      if (var2) {
         Finishing var4 = (Finishing)var1;
         if (var4.isCancelling()) {
            var5 = "Cancelling";
         } else {
            var5 = var3;
            if (var4.isCompleting()) {
               var5 = "Completing";
            }
         }
      } else if (var1 instanceof Incomplete) {
         if (((Incomplete)var1).isActive()) {
            var5 = var3;
         } else {
            var5 = "New";
         }
      } else if (var1 instanceof CompletedExceptionally) {
         var5 = "Cancelled";
      } else {
         var5 = "Completed";
      }

      return var5;
   }

   // $FF: synthetic method
   public static CancellationException toCancellationException$default(JobSupport var0, Throwable var1, String var2, int var3, Object var4) {
      if (var4 == null) {
         if ((var3 & 1) != 0) {
            var2 = null;
            String var5 = (String)null;
         }

         return var0.toCancellationException(var1, var2);
      } else {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
      }
   }

   private final boolean tryFinalizeSimpleState(Incomplete var1, Object var2) {
      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var3;
         if (!(var1 instanceof Empty) && !(var1 instanceof JobNode)) {
            var3 = false;
         } else {
            var3 = true;
         }

         if (!var3) {
            throw (Throwable)(new AssertionError());
         }
      }

      if (DebugKt.getASSERTIONS_ENABLED() && !(var2 instanceof CompletedExceptionally ^ true)) {
         throw (Throwable)(new AssertionError());
      } else if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var1, JobSupportKt.boxIncomplete(var2))) {
         return false;
      } else {
         this.onCancelling((Throwable)null);
         this.onCompletionInternal(var2);
         this.completeStateFinalization(var1, var2);
         return true;
      }
   }

   private final boolean tryMakeCancelling(Incomplete var1, Throwable var2) {
      if (DebugKt.getASSERTIONS_ENABLED() && !(var1 instanceof Finishing ^ true)) {
         throw (Throwable)(new AssertionError());
      } else if (DebugKt.getASSERTIONS_ENABLED() && !var1.isActive()) {
         throw (Throwable)(new AssertionError());
      } else {
         NodeList var4 = this.getOrPromoteCancellingList(var1);
         if (var4 != null) {
            Finishing var3 = new Finishing(var4, false, var2);
            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var1, var3)) {
               return false;
            } else {
               this.notifyCancelling(var4, var2);
               return true;
            }
         } else {
            return false;
         }
      }
   }

   private final Object tryMakeCompleting(Object var1, Object var2) {
      if (!(var1 instanceof Incomplete)) {
         return JobSupportKt.access$getCOMPLETING_ALREADY$p();
      } else if ((var1 instanceof Empty || var1 instanceof JobNode) && !(var1 instanceof ChildHandleNode) && !(var2 instanceof CompletedExceptionally)) {
         return this.tryFinalizeSimpleState((Incomplete)var1, var2) ? var2 : JobSupportKt.access$getCOMPLETING_RETRY$p();
      } else {
         return this.tryMakeCompletingSlowPath((Incomplete)var1, var2);
      }
   }

   private final Object tryMakeCompletingSlowPath(Incomplete var1, Object var2) {
      NodeList var8 = this.getOrPromoteCancellingList(var1);
      if (var8 != null) {
         boolean var3 = var1 instanceof Finishing;
         Unit var6 = null;
         Incomplete var4;
         if (!var3) {
            var4 = null;
         } else {
            var4 = var1;
         }

         Finishing var123 = (Finishing)var4;
         if (var123 == null) {
            var123 = new Finishing(var8, false, (Throwable)null);
         }

         Throwable var5 = (Throwable)null;
         synchronized(var123){}

         Throwable var10000;
         label1320: {
            boolean var10001;
            Symbol var119;
            label1308: {
               try {
                  if (!var123.isCompleting()) {
                     break label1308;
                  }

                  var119 = JobSupportKt.access$getCOMPLETING_ALREADY$p();
               } catch (Throwable var118) {
                  var10000 = var118;
                  var10001 = false;
                  break label1320;
               }

               return var119;
            }

            try {
               var123.setCompleting(true);
            } catch (Throwable var117) {
               var10000 = var117;
               var10001 = false;
               break label1320;
            }

            if (var123 != var1) {
               try {
                  if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var1, var123)) {
                     var119 = JobSupportKt.access$getCOMPLETING_RETRY$p();
                     return var119;
                  }
               } catch (Throwable var116) {
                  var10000 = var116;
                  var10001 = false;
                  break label1320;
               }
            }

            label1321: {
               label1292:
               try {
                  if (DebugKt.getASSERTIONS_ENABLED() && !(var123.isSealed() ^ true)) {
                     break label1292;
                  }
                  break label1321;
               } catch (Throwable var115) {
                  var10000 = var115;
                  var10001 = false;
                  break label1320;
               }

               try {
                  AssertionError var122 = new AssertionError();
                  throw (Throwable)var122;
               } catch (Throwable var109) {
                  var10000 = var109;
                  var10001 = false;
                  break label1320;
               }
            }

            Object var124;
            label1284: {
               label1283: {
                  try {
                     var3 = var123.isCancelling();
                     if (!(var2 instanceof CompletedExceptionally)) {
                        break label1283;
                     }
                  } catch (Throwable var114) {
                     var10000 = var114;
                     var10001 = false;
                     break label1320;
                  }

                  var124 = var2;
                  break label1284;
               }

               var124 = null;
            }

            CompletedExceptionally var125;
            try {
               var125 = (CompletedExceptionally)var124;
            } catch (Throwable var113) {
               var10000 = var113;
               var10001 = false;
               break label1320;
            }

            if (var125 != null) {
               try {
                  var123.addExceptionLocked(var125.cause);
               } catch (Throwable var112) {
                  var10000 = var112;
                  var10001 = false;
                  break label1320;
               }
            }

            Throwable var7;
            try {
               var7 = var123.getRootCause();
            } catch (Throwable var111) {
               var10000 = var111;
               var10001 = false;
               break label1320;
            }

            var5 = var6;
            if (true ^ var3) {
               var5 = var7;
            }

            try {
               var6 = Unit.INSTANCE;
            } catch (Throwable var110) {
               var10000 = var110;
               var10001 = false;
               break label1320;
            }

            if (var5 != null) {
               this.notifyCancelling(var8, var5);
            }

            ChildHandleNode var120 = this.firstChild(var1);
            if (var120 != null && this.tryWaitForChild(var123, var120, var2)) {
               return JobSupportKt.COMPLETING_WAITING_CHILDREN;
            }

            return this.finalizeFinishingState(var123, var2);
         }

         Throwable var121 = var10000;
         throw var121;
      } else {
         return JobSupportKt.access$getCOMPLETING_RETRY$p();
      }
   }

   private final boolean tryWaitForChild(Finishing var1, ChildHandleNode var2, Object var3) {
      do {
         if (Job.DefaultImpls.invokeOnCompletion$default((Job)var2.childJob, false, false, (Function1)((CompletionHandlerBase)(new ChildCompletion(this, var1, var2, var3))), 1, (Object)null) != NonDisposableHandle.INSTANCE) {
            return true;
         }

         var2 = this.nextChild((LockFreeLinkedListNode)var2);
      } while(var2 != null);

      return false;
   }

   protected void afterCompletion(Object var1) {
   }

   public final ChildHandle attachChild(ChildJob var1) {
      DisposableHandle var2 = Job.DefaultImpls.invokeOnCompletion$default(this, true, false, (Function1)((CompletionHandlerBase)(new ChildHandleNode(this, var1))), 2, (Object)null);
      if (var2 != null) {
         return (ChildHandle)var2;
      } else {
         throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.ChildHandle");
      }
   }

   public final Object awaitInternal$kotlinx_coroutines_core(Continuation var1) {
      Object var2;
      do {
         var2 = this.getState$kotlinx_coroutines_core();
         if (!(var2 instanceof Incomplete)) {
            if (var2 instanceof CompletedExceptionally) {
               Throwable var3 = ((CompletedExceptionally)var2).cause;
               if (DebugKt.getRECOVER_STACK_TRACES()) {
                  if (!(var1 instanceof CoroutineStackFrame)) {
                     throw var3;
                  }

                  throw StackTraceRecoveryKt.access$recoverFromStackFrame(var3, (CoroutineStackFrame)var1);
               }

               throw var3;
            }

            return JobSupportKt.unboxState(var2);
         }
      } while(this.startInternal(var2) < 0);

      return this.awaitSuspend(var1);
   }

   // $FF: synthetic method
   final Object awaitSuspend(Continuation var1) {
      AwaitContinuation var2 = new AwaitContinuation(IntrinsicsKt.intercepted(var1), this);
      CancellableContinuationKt.disposeOnCancellation((CancellableContinuation)var2, this.invokeOnCompletion((Function1)((CompletionHandlerBase)(new ResumeAwaitOnCompletion(this, (CancellableContinuationImpl)var2)))));
      Object var3 = var2.getResult();
      if (var3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      return var3;
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public void cancel() {
      Job.DefaultImpls.cancel(this);
   }

   public void cancel(CancellationException var1) {
      if (var1 == null) {
         String var2 = (String)null;
         Throwable var3 = (Throwable)null;
         var1 = (CancellationException)(new JobCancellationException(access$cancellationExceptionMessage(this), (Throwable)null, (Job)this));
      }

      this.cancelInternal((Throwable)var1);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Added since 1.2.0 for binary compatibility with versions <= 1.1.x"
   )
   public boolean cancel(Throwable var1) {
      CancellationException var2;
      label12: {
         if (var1 != null) {
            var2 = toCancellationException$default(this, var1, (String)null, 1, (Object)null);
            if (var2 != null) {
               break label12;
            }
         }

         String var3 = (String)null;
         var1 = (Throwable)null;
         var2 = (CancellationException)(new JobCancellationException(access$cancellationExceptionMessage(this), (Throwable)null, (Job)this));
      }

      this.cancelInternal((Throwable)var2);
      return true;
   }

   public final boolean cancelCoroutine(Throwable var1) {
      return this.cancelImpl$kotlinx_coroutines_core(var1);
   }

   public final boolean cancelImpl$kotlinx_coroutines_core(Object var1) {
      Object var4 = JobSupportKt.access$getCOMPLETING_ALREADY$p();
      boolean var3 = this.getOnCancelComplete$kotlinx_coroutines_core();
      boolean var2 = true;
      Object var5;
      if (var3) {
         var5 = this.cancelMakeCompleting(var1);
         var4 = var5;
         if (var5 == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
         }
      }

      var5 = var4;
      if (var4 == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
         var5 = this.makeCancelling(var1);
      }

      if (var5 != JobSupportKt.access$getCOMPLETING_ALREADY$p() && var5 != JobSupportKt.COMPLETING_WAITING_CHILDREN) {
         if (var5 == JobSupportKt.access$getTOO_LATE_TO_CANCEL$p()) {
            var2 = false;
         } else {
            this.afterCompletion(var5);
         }
      }

      return var2;
   }

   public void cancelInternal(Throwable var1) {
      this.cancelImpl$kotlinx_coroutines_core(var1);
   }

   protected String cancellationExceptionMessage() {
      return "Job was cancelled";
   }

   public boolean childCancelled(Throwable var1) {
      boolean var3 = var1 instanceof CancellationException;
      boolean var2 = true;
      if (var3) {
         return true;
      } else {
         if (!this.cancelImpl$kotlinx_coroutines_core(var1) || !this.getHandlesException$kotlinx_coroutines_core()) {
            var2 = false;
         }

         return var2;
      }
   }

   public final JobCancellationException defaultCancellationException$kotlinx_coroutines_core(String var1, Throwable var2) {
      if (var1 == null) {
         var1 = access$cancellationExceptionMessage(this);
      }

      return new JobCancellationException(var1, var2, (Job)this);
   }

   public Object fold(Object var1, Function2 var2) {
      return Job.DefaultImpls.fold(this, var1, var2);
   }

   public Element get(CoroutineContext.Key var1) {
      return Job.DefaultImpls.get(this, var1);
   }

   public final CancellationException getCancellationException() {
      Object var1 = this.getState$kotlinx_coroutines_core();
      CancellationException var3;
      if (var1 instanceof Finishing) {
         Throwable var2 = ((Finishing)var1).getRootCause();
         if (var2 == null) {
            throw (Throwable)(new IllegalStateException(("Job is still new or active: " + this).toString()));
         }

         var3 = this.toCancellationException(var2, DebugStringsKt.getClassSimpleName(this) + " is cancelling");
         if (var3 == null) {
            throw (Throwable)(new IllegalStateException(("Job is still new or active: " + this).toString()));
         }
      } else {
         if (var1 instanceof Incomplete) {
            throw (Throwable)(new IllegalStateException(("Job is still new or active: " + this).toString()));
         }

         if (var1 instanceof CompletedExceptionally) {
            var3 = toCancellationException$default(this, ((CompletedExceptionally)var1).cause, (String)null, 1, (Object)null);
         } else {
            var3 = (CancellationException)(new JobCancellationException(DebugStringsKt.getClassSimpleName(this) + " has completed normally", (Throwable)null, (Job)this));
         }
      }

      return var3;
   }

   public CancellationException getChildJobCancellationCause() {
      Object var4 = this.getState$kotlinx_coroutines_core();
      boolean var1 = var4 instanceof Finishing;
      Throwable var3 = null;
      Throwable var2;
      if (var1) {
         var2 = ((Finishing)var4).getRootCause();
      } else if (var4 instanceof CompletedExceptionally) {
         var2 = ((CompletedExceptionally)var4).cause;
      } else {
         if (var4 instanceof Incomplete) {
            throw (Throwable)(new IllegalStateException(("Cannot be cancelling child in this state: " + var4).toString()));
         }

         var2 = null;
      }

      if (var2 instanceof CancellationException) {
         var3 = var2;
      }

      CancellationException var6 = (CancellationException)var3;
      CancellationException var5;
      if (var6 != null) {
         var5 = var6;
      } else {
         var5 = (CancellationException)(new JobCancellationException("Parent job is " + this.stateString(var4), var2, (Job)this));
      }

      return var5;
   }

   public final Sequence getChildren() {
      return SequencesKt.sequence((Function2)(new Function2(this, (Continuation)null) {
         Object L$0;
         Object L$1;
         Object L$2;
         Object L$3;
         Object L$4;
         Object L$5;
         int label;
         private SequenceScope p$;
         final JobSupport this$0;

         {
            this.this$0 = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.this$0, var2);
            var3.p$ = (SequenceScope)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var14 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            LockFreeLinkedListNode var4;
            SequenceScope var5;
            LockFreeLinkedListHead var6;
            Object var7;
            NodeList var8;
            Object var9;
            NodeList var10;
            LockFreeLinkedListHead var11;
            SequenceScope var12;
            LockFreeLinkedListNode var16;
            <undefinedtype> var18;
            if (var2 != 0) {
               if (var2 == 1) {
                  SequenceScope var19 = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure(var1);
                  return Unit.INSTANCE;
               }

               if (var2 != 2) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               ChildHandleNode var3 = (ChildHandleNode)this.L$5;
               var4 = (LockFreeLinkedListNode)this.L$4;
               var6 = (LockFreeLinkedListHead)this.L$3;
               var8 = (NodeList)this.L$2;
               var9 = this.L$1;
               var12 = (SequenceScope)this.L$0;
               ResultKt.throwOnFailure(var1);
               var16 = var4.getNextNode();
               var11 = var6;
               var10 = var8;
               var7 = var9;
               var5 = var12;
               var18 = this;
            } else {
               ResultKt.throwOnFailure(var1);
               var5 = this.p$;
               var7 = this.this$0.getState$kotlinx_coroutines_core();
               if (var7 instanceof ChildHandleNode) {
                  ChildJob var17 = ((ChildHandleNode)var7).childJob;
                  this.L$0 = var5;
                  this.L$1 = var7;
                  this.label = 1;
                  if (var5.yield(var17, this) == var14) {
                     return var14;
                  }

                  return Unit.INSTANCE;
               }

               if (!(var7 instanceof Incomplete)) {
                  return Unit.INSTANCE;
               }

               var10 = ((Incomplete)var7).getList();
               if (var10 == null) {
                  return Unit.INSTANCE;
               }

               var11 = (LockFreeLinkedListHead)var10;
               var1 = var11.getNext();
               if (var1 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
               }

               var16 = (LockFreeLinkedListNode)var1;
               var18 = this;
            }

            while(Intrinsics.areEqual((Object)var16, (Object)var11) ^ true) {
               var4 = var16;
               var6 = var11;
               var8 = var10;
               var9 = var7;
               var12 = var5;
               <undefinedtype> var13 = var18;
               if (var16 instanceof ChildHandleNode) {
                  ChildHandleNode var20 = (ChildHandleNode)var16;
                  ChildJob var15 = var20.childJob;
                  var18.L$0 = var5;
                  var18.L$1 = var7;
                  var18.L$2 = var10;
                  var18.L$3 = var11;
                  var18.L$4 = var16;
                  var18.L$5 = var20;
                  var18.label = 2;
                  var4 = var16;
                  var6 = var11;
                  var8 = var10;
                  var9 = var7;
                  var12 = var5;
                  var13 = var18;
                  if (var5.yield(var15, var18) == var14) {
                     return var14;
                  }
               }

               var16 = var4.getNextNode();
               var11 = var6;
               var10 = var8;
               var7 = var9;
               var5 = var12;
               var18 = var13;
            }

            return Unit.INSTANCE;
         }
      }));
   }

   public final Object getCompletedInternal$kotlinx_coroutines_core() {
      Object var1 = this.getState$kotlinx_coroutines_core();
      if (var1 instanceof Incomplete ^ true) {
         if (!(var1 instanceof CompletedExceptionally)) {
            return JobSupportKt.unboxState(var1);
         } else {
            throw ((CompletedExceptionally)var1).cause;
         }
      } else {
         throw (Throwable)(new IllegalStateException("This job has not completed yet".toString()));
      }
   }

   protected final Throwable getCompletionCause() {
      Object var1 = this.getState$kotlinx_coroutines_core();
      Throwable var2;
      if (var1 instanceof Finishing) {
         var2 = ((Finishing)var1).getRootCause();
         if (var2 == null) {
            throw (Throwable)(new IllegalStateException(("Job is still new or active: " + this).toString()));
         }
      } else {
         if (var1 instanceof Incomplete) {
            throw (Throwable)(new IllegalStateException(("Job is still new or active: " + this).toString()));
         }

         if (var1 instanceof CompletedExceptionally) {
            var2 = ((CompletedExceptionally)var1).cause;
         } else {
            var2 = null;
         }
      }

      return var2;
   }

   protected final boolean getCompletionCauseHandled() {
      Object var2 = this.getState$kotlinx_coroutines_core();
      boolean var1;
      if (var2 instanceof CompletedExceptionally && ((CompletedExceptionally)var2).getHandled()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final Throwable getCompletionExceptionOrNull() {
      Object var1 = this.getState$kotlinx_coroutines_core();
      if (var1 instanceof Incomplete ^ true) {
         return this.getExceptionOrNull(var1);
      } else {
         throw (Throwable)(new IllegalStateException("This job has not completed yet".toString()));
      }
   }

   public boolean getHandlesException$kotlinx_coroutines_core() {
      return true;
   }

   public final CoroutineContext.Key getKey() {
      return (CoroutineContext.Key)Job.Key;
   }

   public boolean getOnCancelComplete$kotlinx_coroutines_core() {
      return false;
   }

   public final SelectClause0 getOnJoin() {
      return (SelectClause0)this;
   }

   public final ChildHandle getParentHandle$kotlinx_coroutines_core() {
      return (ChildHandle)this._parentHandle;
   }

   public final Object getState$kotlinx_coroutines_core() {
      while(true) {
         Object var1 = this._state;
         if (!(var1 instanceof OpDescriptor)) {
            return var1;
         }

         ((OpDescriptor)var1).perform(this);
      }
   }

   protected boolean handleJobException(Throwable var1) {
      return false;
   }

   public void handleOnCompletionException$kotlinx_coroutines_core(Throwable var1) {
      throw var1;
   }

   public final void initParentJobInternal$kotlinx_coroutines_core(Job var1) {
      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var2;
         if (this.getParentHandle$kotlinx_coroutines_core() == null) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (!var2) {
            throw (Throwable)(new AssertionError());
         }
      }

      if (var1 == null) {
         this.setParentHandle$kotlinx_coroutines_core((ChildHandle)NonDisposableHandle.INSTANCE);
      } else {
         var1.start();
         ChildHandle var3 = var1.attachChild((ChildJob)this);
         this.setParentHandle$kotlinx_coroutines_core(var3);
         if (this.isCompleted()) {
            var3.dispose();
            this.setParentHandle$kotlinx_coroutines_core((ChildHandle)NonDisposableHandle.INSTANCE);
         }

      }
   }

   public final DisposableHandle invokeOnCompletion(Function1 var1) {
      return this.invokeOnCompletion(false, true, var1);
   }

   public final DisposableHandle invokeOnCompletion(boolean var1, boolean var2, Function1 var3) {
      Object var9 = null;
      JobNode var5 = (JobNode)null;
      var5 = null;

      while(true) {
         while(true) {
            while(true) {
               Object var10 = this.getState$kotlinx_coroutines_core();
               JobNode var87;
               if (!(var10 instanceof Empty)) {
                  if (!(var10 instanceof Incomplete)) {
                     if (var2) {
                        Object var90 = var10;
                        if (!(var10 instanceof CompletedExceptionally)) {
                           var90 = null;
                        }

                        CompletedExceptionally var93 = (CompletedExceptionally)var90;
                        Throwable var92 = (Throwable)var9;
                        if (var93 != null) {
                           var92 = var93.cause;
                        }

                        var3.invoke(var92);
                     }

                     return (DisposableHandle)NonDisposableHandle.INSTANCE;
                  }

                  NodeList var12 = ((Incomplete)var10).getList();
                  if (var12 != null) {
                     Throwable var89 = (Throwable)null;
                     DisposableHandle var8 = (DisposableHandle)NonDisposableHandle.INSTANCE;
                     if (var1 && var10 instanceof Finishing) {
                        label1330: {
                           synchronized(var10){}

                           Throwable var10000;
                           label1331: {
                              Throwable var11;
                              boolean var10001;
                              try {
                                 var11 = ((Finishing)var10).getRootCause();
                              } catch (Throwable var84) {
                                 var10000 = var84;
                                 var10001 = false;
                                 break label1331;
                              }

                              JobNode var7;
                              DisposableHandle var91;
                              label1332: {
                                 if (var11 != null) {
                                    var7 = var5;
                                    var91 = var8;

                                    try {
                                       if (!(var3 instanceof ChildHandleNode)) {
                                          break label1332;
                                       }
                                    } catch (Throwable var83) {
                                       var10000 = var83;
                                       var10001 = false;
                                       break label1331;
                                    }

                                    var7 = var5;
                                    var91 = var8;

                                    try {
                                       if (((Finishing)var10).isCompleting()) {
                                          break label1332;
                                       }
                                    } catch (Throwable var82) {
                                       var10000 = var82;
                                       var10001 = false;
                                       break label1331;
                                    }
                                 }

                                 if (var5 == null) {
                                    try {
                                       var5 = this.makeNode(var3, var1);
                                    } catch (Throwable var81) {
                                       var10000 = var81;
                                       var10001 = false;
                                       break label1331;
                                    }
                                 }

                                 boolean var4;
                                 try {
                                    var4 = this.addLastAtomic(var10, var12, var5);
                                 } catch (Throwable var80) {
                                    var10000 = var80;
                                    var10001 = false;
                                    break label1331;
                                 }

                                 if (!var4) {
                                    continue;
                                 }

                                 if (var11 == null) {
                                    try {
                                       DisposableHandle var85 = (DisposableHandle)var5;
                                       return var85;
                                    } catch (Throwable var77) {
                                       var10000 = var77;
                                       var10001 = false;
                                       break label1331;
                                    }
                                 }

                                 try {
                                    var91 = (DisposableHandle)var5;
                                 } catch (Throwable var79) {
                                    var10000 = var79;
                                    var10001 = false;
                                    break label1331;
                                 }

                                 var7 = var5;
                              }

                              try {
                                 Unit var88 = Unit.INSTANCE;
                              } catch (Throwable var78) {
                                 var10000 = var78;
                                 var10001 = false;
                                 break label1331;
                              }

                              var5 = var7;
                              var8 = var91;
                              var89 = var11;
                              break label1330;
                           }

                           Throwable var86 = var10000;
                           throw var86;
                        }
                     } else {
                        var89 = null;
                     }

                     if (var89 != null) {
                        if (var2) {
                           var3.invoke(var89);
                        }

                        return var8;
                     }

                     if (var5 != null) {
                        var87 = var5;
                     } else {
                        var87 = this.makeNode(var3, var1);
                     }

                     var5 = var87;
                     if (this.addLastAtomic(var10, var12, var87)) {
                        return (DisposableHandle)var87;
                     }
                  } else {
                     if (var10 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.JobNode<*>");
                     }

                     this.promoteSingleToNodeList((JobNode)var10);
                  }
               } else {
                  Empty var6 = (Empty)var10;
                  if (var6.isActive()) {
                     if (var5 != null) {
                        var87 = var5;
                     } else {
                        var87 = this.makeNode(var3, var1);
                     }

                     var5 = var87;
                     if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var10, var87)) {
                        return (DisposableHandle)var87;
                     }
                  } else {
                     this.promoteEmptyToNodeList(var6);
                  }
               }
            }
         }
      }
   }

   public boolean isActive() {
      Object var2 = this.getState$kotlinx_coroutines_core();
      boolean var1;
      if (var2 instanceof Incomplete && ((Incomplete)var2).isActive()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final boolean isCancelled() {
      Object var2 = this.getState$kotlinx_coroutines_core();
      boolean var1;
      if (var2 instanceof CompletedExceptionally || var2 instanceof Finishing && ((Finishing)var2).isCancelling()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final boolean isCompleted() {
      return this.getState$kotlinx_coroutines_core() instanceof Incomplete ^ true;
   }

   public final boolean isCompletedExceptionally() {
      return this.getState$kotlinx_coroutines_core() instanceof CompletedExceptionally;
   }

   protected boolean isScopedCoroutine() {
      return false;
   }

   public final Object join(Continuation var1) {
      if (!this.joinInternal()) {
         YieldKt.checkCompletion(var1.getContext());
         return Unit.INSTANCE;
      } else {
         Object var2 = this.joinSuspend(var1);
         return var2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var2 : Unit.INSTANCE;
      }
   }

   // $FF: synthetic method
   final Object joinSuspend(Continuation var1) {
      CancellableContinuationImpl var3 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var1), 1);
      var3.initCancellability();
      CancellableContinuation var2 = (CancellableContinuation)var3;
      CancellableContinuationKt.disposeOnCancellation(var2, this.invokeOnCompletion((Function1)((CompletionHandlerBase)(new ResumeOnCompletion((Job)this, (Continuation)var2)))));
      Object var4 = var3.getResult();
      if (var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      return var4;
   }

   public final boolean makeCompleting$kotlinx_coroutines_core(Object var1) {
      Object var2;
      do {
         var2 = this.tryMakeCompleting(this.getState$kotlinx_coroutines_core(), var1);
         if (var2 == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
            return false;
         }

         if (var2 == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
         }
      } while(var2 == JobSupportKt.access$getCOMPLETING_RETRY$p());

      this.afterCompletion(var2);
      return true;
   }

   public final Object makeCompletingOnce$kotlinx_coroutines_core(Object var1) {
      while(true) {
         Object var2 = this.tryMakeCompleting(this.getState$kotlinx_coroutines_core(), var1);
         if (var2 != JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
            if (var2 == JobSupportKt.access$getCOMPLETING_RETRY$p()) {
               continue;
            }

            return var2;
         }

         throw (Throwable)(new IllegalStateException("Job " + this + " is already complete or completing, " + "but is being completed with " + var1, this.getExceptionOrNull(var1)));
      }
   }

   public CoroutineContext minusKey(CoroutineContext.Key var1) {
      return Job.DefaultImpls.minusKey(this, var1);
   }

   public String nameString$kotlinx_coroutines_core() {
      return DebugStringsKt.getClassSimpleName(this);
   }

   protected void onCancelling(Throwable var1) {
   }

   protected void onCompletionInternal(Object var1) {
   }

   public void onStartInternal$kotlinx_coroutines_core() {
   }

   public final void parentCancelled(ParentJob var1) {
      this.cancelImpl$kotlinx_coroutines_core(var1);
   }

   public CoroutineContext plus(CoroutineContext var1) {
      return Job.DefaultImpls.plus(this, (CoroutineContext)var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`."
   )
   public Job plus(Job var1) {
      return Job.DefaultImpls.plus(this, (Job)var1);
   }

   public final void registerSelectClause0(SelectInstance var1, Function1 var2) {
      Object var3;
      do {
         var3 = this.getState$kotlinx_coroutines_core();
         if (var1.isSelected()) {
            return;
         }

         if (!(var3 instanceof Incomplete)) {
            if (var1.trySelect()) {
               UndispatchedKt.startCoroutineUnintercepted(var2, var1.getCompletion());
            }

            return;
         }
      } while(this.startInternal(var3) != 0);

      var1.disposeOnSelect(this.invokeOnCompletion((Function1)((CompletionHandlerBase)(new SelectJoinOnCompletion(this, var1, var2)))));
   }

   public final void registerSelectClause1Internal$kotlinx_coroutines_core(SelectInstance var1, Function2 var2) {
      Object var3;
      do {
         var3 = this.getState$kotlinx_coroutines_core();
         if (var1.isSelected()) {
            return;
         }

         if (!(var3 instanceof Incomplete)) {
            if (var1.trySelect()) {
               if (var3 instanceof CompletedExceptionally) {
                  var1.resumeSelectWithException(((CompletedExceptionally)var3).cause);
               } else {
                  UndispatchedKt.startCoroutineUnintercepted(var2, JobSupportKt.unboxState(var3), var1.getCompletion());
               }
            }

            return;
         }
      } while(this.startInternal(var3) != 0);

      var1.disposeOnSelect(this.invokeOnCompletion((Function1)((CompletionHandlerBase)(new SelectAwaitOnCompletion(this, var1, var2)))));
   }

   public final void removeNode$kotlinx_coroutines_core(JobNode var1) {
      while(true) {
         Object var2 = this.getState$kotlinx_coroutines_core();
         if (var2 instanceof JobNode) {
            if (var2 != var1) {
               return;
            }

            if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, var2, JobSupportKt.access$getEMPTY_ACTIVE$p())) {
               continue;
            }

            return;
         }

         if (var2 instanceof Incomplete && ((Incomplete)var2).getList() != null) {
            var1.remove();
         }

         return;
      }
   }

   public final void selectAwaitCompletion$kotlinx_coroutines_core(SelectInstance var1, Function2 var2) {
      Object var3 = this.getState$kotlinx_coroutines_core();
      if (var3 instanceof CompletedExceptionally) {
         var1.resumeSelectWithException(((CompletedExceptionally)var3).cause);
      } else {
         CancellableKt.startCoroutineCancellable$default(var2, JobSupportKt.unboxState(var3), var1.getCompletion(), (Function1)null, 4, (Object)null);
      }

   }

   public final void setParentHandle$kotlinx_coroutines_core(ChildHandle var1) {
      this._parentHandle = var1;
   }

   public final boolean start() {
      while(true) {
         int var1 = this.startInternal(this.getState$kotlinx_coroutines_core());
         if (var1 != 0) {
            if (var1 != 1) {
               continue;
            }

            return true;
         }

         return false;
      }
   }

   protected final CancellationException toCancellationException(Throwable var1, String var2) {
      Throwable var3;
      if (!(var1 instanceof CancellationException)) {
         var3 = null;
      } else {
         var3 = var1;
      }

      CancellationException var5 = (CancellationException)var3;
      CancellationException var4;
      if (var5 != null) {
         var4 = var5;
      } else {
         if (var2 == null) {
            var2 = access$cancellationExceptionMessage(this);
         }

         var4 = (CancellationException)(new JobCancellationException(var2, var1, (Job)this));
      }

      return var4;
   }

   public final String toDebugString() {
      return this.nameString$kotlinx_coroutines_core() + '{' + this.stateString(this.getState$kotlinx_coroutines_core()) + '}';
   }

   public String toString() {
      return this.toDebugString() + '@' + DebugStringsKt.getHexAddress(this);
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"},
      d2 = {"Lkotlinx/coroutines/JobSupport$AwaitContinuation;", "T", "Lkotlinx/coroutines/CancellableContinuationImpl;", "delegate", "Lkotlin/coroutines/Continuation;", "job", "Lkotlinx/coroutines/JobSupport;", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/JobSupport;)V", "getContinuationCancellationCause", "", "parent", "Lkotlinx/coroutines/Job;", "nameString", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class AwaitContinuation extends CancellableContinuationImpl {
      private final JobSupport job;

      public AwaitContinuation(Continuation var1, JobSupport var2) {
         super(var1, 1);
         this.job = var2;
      }

      public Throwable getContinuationCancellationCause(Job var1) {
         Object var3 = this.job.getState$kotlinx_coroutines_core();
         if (var3 instanceof Finishing) {
            Throwable var2 = ((Finishing)var3).getRootCause();
            if (var2 != null) {
               return var2;
            }
         }

         return var3 instanceof CompletedExceptionally ? ((CompletedExceptionally)var3).cause : (Throwable)var1.getCancellationException();
      }

      protected String nameString() {
         return "AwaitContinuation";
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"},
      d2 = {"Lkotlinx/coroutines/JobSupport$ChildCompletion;", "Lkotlinx/coroutines/JobNode;", "Lkotlinx/coroutines/Job;", "parent", "Lkotlinx/coroutines/JobSupport;", "state", "Lkotlinx/coroutines/JobSupport$Finishing;", "child", "Lkotlinx/coroutines/ChildHandleNode;", "proposedUpdate", "", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class ChildCompletion extends JobNode {
      private final ChildHandleNode child;
      private final JobSupport parent;
      private final Object proposedUpdate;
      private final Finishing state;

      public ChildCompletion(JobSupport var1, Finishing var2, ChildHandleNode var3, Object var4) {
         super((Job)var3.childJob);
         this.parent = var1;
         this.state = var2;
         this.child = var3;
         this.proposedUpdate = var4;
      }

      public void invoke(Throwable var1) {
         this.parent.continueCompleting(this.state, this.child, this.proposedUpdate);
      }

      public String toString() {
         return "ChildCompletion[" + this.child + ", " + this.proposedUpdate + ']';
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u00002\u00060\u0018j\u0002`,2\u00020-B!\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00050\rj\b\u0012\u0004\u0012\u00020\u0005`\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R(\u0010\u001e\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00188B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u00038V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0013\u0010!\u001a\u00020\u00038F@\u0006¢\u0006\u0006\u001a\u0004\b!\u0010 R$\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010 \"\u0004\b\"\u0010#R\u0013\u0010$\u001a\u00020\u00038F@\u0006¢\u0006\u0006\u001a\u0004\b$\u0010 R\u001c\u0010\u0002\u001a\u00020\u00018\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0002\u0010%\u001a\u0004\b&\u0010'R(\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010\f¨\u0006+"},
      d2 = {"Lkotlinx/coroutines/JobSupport$Finishing;", "Lkotlinx/coroutines/NodeList;", "list", "", "isCompleting", "", "rootCause", "<init>", "(Lkotlinx/coroutines/NodeList;ZLjava/lang/Throwable;)V", "exception", "", "addExceptionLocked", "(Ljava/lang/Throwable;)V", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "allocateList", "()Ljava/util/ArrayList;", "proposedException", "", "sealLocked", "(Ljava/lang/Throwable;)Ljava/util/List;", "", "toString", "()Ljava/lang/String;", "", "value", "getExceptionsHolder", "()Ljava/lang/Object;", "setExceptionsHolder", "(Ljava/lang/Object;)V", "exceptionsHolder", "isActive", "()Z", "isCancelling", "setCompleting", "(Z)V", "isSealed", "Lkotlinx/coroutines/NodeList;", "getList", "()Lkotlinx/coroutines/NodeList;", "getRootCause", "()Ljava/lang/Throwable;", "setRootCause", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/Incomplete;"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class Finishing implements Incomplete {
      private volatile Object _exceptionsHolder;
      private volatile int _isCompleting;
      private volatile Object _rootCause;
      private final NodeList list;

      public Finishing(NodeList var1, boolean var2, Throwable var3) {
         this.list = var1;
         this._isCompleting = var2;
         this._rootCause = var3;
         this._exceptionsHolder = null;
      }

      private final ArrayList allocateList() {
         return new ArrayList(4);
      }

      private final Object getExceptionsHolder() {
         return this._exceptionsHolder;
      }

      private final void setExceptionsHolder(Object var1) {
         this._exceptionsHolder = var1;
      }

      public final void addExceptionLocked(Throwable var1) {
         Throwable var2 = this.getRootCause();
         if (var2 == null) {
            this.setRootCause(var1);
         } else if (var1 != var2) {
            Object var3 = this.getExceptionsHolder();
            if (var3 == null) {
               this.setExceptionsHolder(var1);
            } else if (var3 instanceof Throwable) {
               if (var1 == var3) {
                  return;
               }

               ArrayList var5 = this.allocateList();
               var5.add(var3);
               var5.add(var1);
               Unit var4 = Unit.INSTANCE;
               this.setExceptionsHolder(var5);
            } else {
               if (!(var3 instanceof ArrayList)) {
                  throw (Throwable)(new IllegalStateException(("State is " + var3).toString()));
               }

               ((ArrayList)var3).add(var1);
            }

         }
      }

      public NodeList getList() {
         return this.list;
      }

      public final Throwable getRootCause() {
         return (Throwable)this._rootCause;
      }

      public boolean isActive() {
         boolean var1;
         if (this.getRootCause() == null) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public final boolean isCancelling() {
         boolean var1;
         if (this.getRootCause() != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public final boolean isCompleting() {
         return (boolean)this._isCompleting;
      }

      public final boolean isSealed() {
         boolean var1;
         if (this.getExceptionsHolder() == JobSupportKt.access$getSEALED$p()) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public final List sealLocked(Throwable var1) {
         Object var3 = this.getExceptionsHolder();
         ArrayList var2;
         if (var3 == null) {
            var2 = this.allocateList();
         } else if (var3 instanceof Throwable) {
            var2 = this.allocateList();
            var2.add(var3);
         } else {
            if (!(var3 instanceof ArrayList)) {
               throw (Throwable)(new IllegalStateException(("State is " + var3).toString()));
            }

            var2 = (ArrayList)var3;
         }

         Throwable var4 = this.getRootCause();
         if (var4 != null) {
            var2.add(0, var4);
         }

         if (var1 != null && Intrinsics.areEqual((Object)var1, (Object)var4) ^ true) {
            var2.add(var1);
         }

         this.setExceptionsHolder(JobSupportKt.access$getSEALED$p());
         return (List)var2;
      }

      public final void setCompleting(boolean var1) {
         this._isCompleting = var1;
      }

      public final void setRootCause(Throwable var1) {
         this._rootCause = var1;
      }

      public String toString() {
         return "Finishing[cancelling=" + this.isCancelling() + ", completing=" + this.isCompleting() + ", rootCause=" + this.getRootCause() + ", exceptions=" + this.getExceptionsHolder() + ", list=" + this.getList() + ']';
      }
   }
}
