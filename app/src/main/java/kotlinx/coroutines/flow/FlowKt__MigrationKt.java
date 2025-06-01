package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlinx.coroutines.DelayKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0013\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000\u001a\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001a¸\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\b\"\u0004\b\u0002\u0010\t\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\u000b\"\u0004\b\u0005\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00070\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\n0\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u00032:\u0010\u0010\u001a6\b\u0001\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0011H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a\u009e\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\b\"\u0004\b\u0002\u0010\t\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00070\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\n0\u000324\u0010\u0010\u001a0\b\u0001\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0015H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\u0084\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\b\"\u0004\b\u0002\u0010\t\"\u0004\b\u0003\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00070\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\u00032.\u0010\u0010\u001a*\b\u0001\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0017H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001aj\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\b\"\u0004\b\u0002\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00070\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\u00032(\u0010\u0010\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001aI\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00040\u00032#\u0010\u001c\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00030\u001d¢\u0006\u0002\b\u001eH\u0007\u001a>\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u0002H\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00030\u001dH\u0007\u001a+\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010\"\u001a\u0002H\u0004H\u0007¢\u0006\u0002\u0010#\u001a,\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001a&\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010%\u001a\u00020&H\u0007\u001a&\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010%\u001a\u00020&H\u0007\u001aV\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00040\u00032(\u0010 \u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0004\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00030\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130)H\u0007ø\u0001\u0000¢\u0006\u0002\u0010*\u001a$\u0010+\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u0003H\u0007\u001aS\u0010,\u001a\u00020-\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u000321\u0010.\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130)H\u0007ø\u0001\u0000¢\u0006\u0002\u00101\u001a$\u00102\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u0003H\u0007\u001a&\u00103\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u00104\u001a\u000205H\u0007\u001a,\u00106\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\f\u00107\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001a,\u00108\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\f\u00107\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001a+\u00109\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u00107\u001a\u0002H\u0004H\u0007¢\u0006\u0002\u0010#\u001aA\u00109\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u00107\u001a\u0002H\u00042\u0014\b\u0002\u0010:\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020<0\u001dH\u0007¢\u0006\u0002\u0010=\u001a\u001e\u0010>\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001a&\u0010>\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010?\u001a\u00020@H\u0007\u001a&\u0010A\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u00104\u001a\u000205H\u0007\u001a\u001e\u0010B\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001a&\u0010B\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010?\u001a\u00020@H\u0007\u001a~\u0010C\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010D\u001a\u0002H\u00062H\b\u0001\u0010E\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0006¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(\"\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010G\u001an\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032F\u0010E\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(F\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(\"\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010I\u001a&\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010K\u001a\u00020@H\u0007\u001a+\u0010L\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u0010\"\u001a\u0002H\u0004H\u0007¢\u0006\u0002\u0010#\u001a,\u0010L\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001a\u0018\u0010M\u001a\u00020-\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0007\u001aD\u0010M\u001a\u00020-\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\"\u0010N\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130)H\u0007ø\u0001\u0000¢\u0006\u0002\u00101\u001ah\u0010M\u001a\u00020-\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\"\u0010N\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130)2\"\u0010O\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020;\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130)H\u0007ø\u0001\u0000¢\u0006\u0002\u0010P\u001a&\u0010Q\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00032\u0006\u00104\u001a\u000205H\u0007\u001ae\u0010R\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00040\u000327\u0010\u0010\u001a3\b\u0001\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(\"\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00030\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130)H\u0007ø\u0001\u0000¢\u0006\u0002\u0010*\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006S"},
   d2 = {"noImpl", "", "cache", "Lkotlinx/coroutines/flow/Flow;", "T", "combineLatest", "R", "T1", "T2", "T3", "T4", "T5", "other", "other2", "other3", "other4", "transform", "Lkotlin/Function6;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function6;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function5;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function5;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function4;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function3;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "compose", "transformer", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "concatMap", "mapper", "concatWith", "value", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;)Lkotlinx/coroutines/flow/Flow;", "delayEach", "timeMillis", "", "delayFlow", "flatMap", "Lkotlin/Function2;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "flatten", "forEach", "", "action", "Lkotlin/ParameterName;", "name", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)V", "merge", "observeOn", "context", "Lkotlin/coroutines/CoroutineContext;", "onErrorResume", "fallback", "onErrorResumeNext", "onErrorReturn", "predicate", "", "", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/flow/Flow;", "publish", "bufferSize", "", "publishOn", "replay", "scanFold", "initial", "operation", "accumulator", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "scanReduce", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "skip", "count", "startWith", "subscribe", "onEach", "onError", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "subscribeOn", "switchMap", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__MigrationKt {
   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'cache()' is 'shareIn' with unlimited replay and 'started = SharingStared.Lazily' argument'",
      replaceWith = @ReplaceWith(
   expression = "this.shareIn(scope, Int.MAX_VALUE, started = SharingStared.Lazily)",
   imports = {}
)
   )
   public static final Flow cache(Flow var0) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'combineLatest' is 'combine'",
      replaceWith = @ReplaceWith(
   expression = "this.combine(other, transform)",
   imports = {}
)
   )
   public static final Flow combineLatest(Flow var0, Flow var1, Function3 var2) {
      return FlowKt.combine(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'combineLatest' is 'combine'",
      replaceWith = @ReplaceWith(
   expression = "combine(this, other, other2, transform)",
   imports = {}
)
   )
   public static final Flow combineLatest(Flow var0, Flow var1, Flow var2, Function4 var3) {
      return FlowKt.combine(var0, var1, var2, var3);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'combineLatest' is 'combine'",
      replaceWith = @ReplaceWith(
   expression = "combine(this, other, other2, other3, transform)",
   imports = {}
)
   )
   public static final Flow combineLatest(Flow var0, Flow var1, Flow var2, Flow var3, Function5 var4) {
      return FlowKt.combine(var0, var1, var2, var3, var4);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'combineLatest' is 'combine'",
      replaceWith = @ReplaceWith(
   expression = "combine(this, other, other2, other3, transform)",
   imports = {}
)
   )
   public static final Flow combineLatest(Flow var0, Flow var1, Flow var2, Flow var3, Flow var4, Function6 var5) {
      return FlowKt.combine(var0, var1, var2, var3, var4, var5);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'compose' is 'let'",
      replaceWith = @ReplaceWith(
   expression = "let(transformer)",
   imports = {}
)
   )
   public static final Flow compose(Flow var0, Function1 var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'concatMap' is 'flatMapConcat'",
      replaceWith = @ReplaceWith(
   expression = "flatMapConcat(mapper)",
   imports = {}
)
   )
   public static final Flow concatMap(Flow var0, Function1 var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'concatWith' is 'onCompletion'. Use 'onCompletion { emit(value) }'",
      replaceWith = @ReplaceWith(
   expression = "onCompletion { emit(value) }",
   imports = {}
)
   )
   public static final Flow concatWith(Flow var0, Object var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'concatWith' is 'onCompletion'. Use 'onCompletion { emitAll(other) }'",
      replaceWith = @ReplaceWith(
   expression = "onCompletion { emitAll(other) }",
   imports = {}
)
   )
   public static final Flow concatWith(Flow var0, Flow var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.WARNING,
      message = "Use 'onEach { delay(timeMillis) }'",
      replaceWith = @ReplaceWith(
   expression = "onEach { delay(timeMillis) }",
   imports = {}
)
   )
   public static final Flow delayEach(Flow var0, long var1) {
      return FlowKt.onEach(var0, (Function2)(new Function2(var1, (Continuation)null) {
         final long $timeMillis;
         Object L$0;
         int label;
         private Object p$0;

         {
            this.$timeMillis = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$timeMillis, var2);
            var3.p$0 = var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               var1 = this.p$0;
               long var3 = this.$timeMillis;
               this.L$0 = var1;
               this.label = 1;
               if (DelayKt.delay(var3, this) == var5) {
                  return var5;
               }
            }

            return Unit.INSTANCE;
         }
      }));
   }

   @Deprecated(
      level = DeprecationLevel.WARNING,
      message = "Use 'onStart { delay(timeMillis) }'",
      replaceWith = @ReplaceWith(
   expression = "onStart { delay(timeMillis) }",
   imports = {}
)
   )
   public static final Flow delayFlow(Flow var0, long var1) {
      return FlowKt.onStart(var0, (Function2)(new Function2(var1, (Continuation)null) {
         final long $timeMillis;
         Object L$0;
         int label;
         private FlowCollector p$;

         {
            this.$timeMillis = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$timeMillis, var2);
            var3.p$ = (FlowCollector)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               FlowCollector var6 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var7 = this.p$;
               long var3 = this.$timeMillis;
               this.L$0 = var7;
               this.label = 1;
               if (DelayKt.delay(var3, this) == var5) {
                  return var5;
               }
            }

            return Unit.INSTANCE;
         }
      }));
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue is 'flatMapConcat'",
      replaceWith = @ReplaceWith(
   expression = "flatMapConcat(mapper)",
   imports = {}
)
   )
   public static final Flow flatMap(Flow var0, Function2 var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'flatten' is 'flattenConcat'",
      replaceWith = @ReplaceWith(
   expression = "flattenConcat()",
   imports = {}
)
   )
   public static final Flow flatten(Flow var0) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'forEach' is 'collect'",
      replaceWith = @ReplaceWith(
   expression = "collect(block)",
   imports = {}
)
   )
   public static final void forEach(Flow var0, Function2 var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'merge' is 'flattenConcat'",
      replaceWith = @ReplaceWith(
   expression = "flattenConcat()",
   imports = {}
)
   )
   public static final Flow merge(Flow var0) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   public static final Void noImpl() {
      throw (Throwable)(new UnsupportedOperationException("Not implemented, should not be called"));
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Collect flow in the desired context instead"
   )
   public static final Flow observeOn(Flow var0, CoroutineContext var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emitAll(fallback) }'",
      replaceWith = @ReplaceWith(
   expression = "catch { emitAll(fallback) }",
   imports = {}
)
   )
   public static final Flow onErrorResume(Flow var0, Flow var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emitAll(fallback) }'",
      replaceWith = @ReplaceWith(
   expression = "catch { emitAll(fallback) }",
   imports = {}
)
   )
   public static final Flow onErrorResumeNext(Flow var0, Flow var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { emit(fallback) }'",
      replaceWith = @ReplaceWith(
   expression = "catch { emit(fallback) }",
   imports = {}
)
   )
   public static final Flow onErrorReturn(Flow var0, Object var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'onErrorXxx' is 'catch'. Use 'catch { e -> if (predicate(e)) emit(fallback) else throw e }'",
      replaceWith = @ReplaceWith(
   expression = "catch { e -> if (predicate(e)) emit(fallback) else throw e }",
   imports = {}
)
   )
   public static final Flow onErrorReturn(Flow var0, Object var1, Function1 var2) {
      return FlowKt.catch(var0, (Function3)(new Function3(var2, var1, (Continuation)null) {
         final Object $fallback;
         final Function1 $predicate;
         Object L$0;
         Object L$1;
         int label;
         private FlowCollector p$;
         private Throwable p$0;

         {
            this.$predicate = var1;
            this.$fallback = var2;
         }

         public final Continuation create(FlowCollector var1, Throwable var2, Continuation var3) {
            Function3 var4 = new <anonymous constructor>(this.$predicate, this.$fallback, var3);
            var4.p$ = var1;
            var4.p$0 = var2;
            return var4;
         }

         public final Object invoke(Object var1, Object var2, Object var3) {
            return ((<undefinedtype>)this.create((FlowCollector)var1, (Throwable)var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               Throwable var7 = (Throwable)this.L$1;
               FlowCollector var8 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var4 = this.p$;
               Throwable var6 = this.p$0;
               if (!(Boolean)this.$predicate.invoke(var6)) {
                  throw var6;
               }

               Object var5 = this.$fallback;
               this.L$0 = var4;
               this.L$1 = var6;
               this.label = 1;
               if (var4.emit(var5, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }));
   }

   // $FF: synthetic method
   public static Flow onErrorReturn$default(Flow var0, Object var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = (Function1)null.INSTANCE;
      }

      return FlowKt.onErrorReturn(var0, var1, var2);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'publish()' is 'shareIn'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to 'started = SharingStared.Lazily' argument, \npublish().refCount() translates to 'started = SharingStared.WhileSubscribed()' argument.",
      replaceWith = @ReplaceWith(
   expression = "this.shareIn(scope, 0)",
   imports = {}
)
   )
   public static final Flow publish(Flow var0) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'publish(bufferSize)' is 'buffer' followed by 'shareIn'. \npublish().connect() is the default strategy (no extra call is needed), \npublish().autoConnect() translates to 'started = SharingStared.Lazily' argument, \npublish().refCount() translates to 'started = SharingStared.WhileSubscribed()' argument.",
      replaceWith = @ReplaceWith(
   expression = "this.buffer(bufferSize).shareIn(scope, 0)",
   imports = {}
)
   )
   public static final Flow publish(Flow var0, int var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Collect flow in the desired context instead"
   )
   public static final Flow publishOn(Flow var0, CoroutineContext var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'replay()' is 'shareIn' with unlimited replay. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to 'started = SharingStared.Lazily' argument, \nreplay().refCount() translates to 'started = SharingStared.WhileSubscribed()' argument.",
      replaceWith = @ReplaceWith(
   expression = "this.shareIn(scope, Int.MAX_VALUE)",
   imports = {}
)
   )
   public static final Flow replay(Flow var0) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'replay(bufferSize)' is 'shareIn' with the specified replay parameter. \nreplay().connect() is the default strategy (no extra call is needed), \nreplay().autoConnect() translates to 'started = SharingStared.Lazily' argument, \nreplay().refCount() translates to 'started = SharingStared.WhileSubscribed()' argument.",
      replaceWith = @ReplaceWith(
   expression = "this.shareIn(scope, bufferSize)",
   imports = {}
)
   )
   public static final Flow replay(Flow var0, int var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow has less verbose 'scan' shortcut",
      replaceWith = @ReplaceWith(
   expression = "scan(initial, operation)",
   imports = {}
)
   )
   public static final Flow scanFold(Flow var0, Object var1, Function3 var2) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.WARNING,
      message = "'scanReduce' was renamed to 'runningReduce' to be consistent with Kotlin standard library",
      replaceWith = @ReplaceWith(
   expression = "runningReduce(operation)",
   imports = {}
)
   )
   public static final Flow scanReduce(Flow var0, Function3 var1) {
      return FlowKt.runningReduce(var0, var1);
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'skip' is 'drop'",
      replaceWith = @ReplaceWith(
   expression = "drop(count)",
   imports = {}
)
   )
   public static final Flow skip(Flow var0, int var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'startWith' is 'onStart'. Use 'onStart { emit(value) }'",
      replaceWith = @ReplaceWith(
   expression = "onStart { emit(value) }",
   imports = {}
)
   )
   public static final Flow startWith(Flow var0, Object var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogue of 'startWith' is 'onStart'. Use 'onStart { emitAll(other) }'",
      replaceWith = @ReplaceWith(
   expression = "onStart { emitAll(other) }",
   imports = {}
)
   )
   public static final Flow startWith(Flow var0, Flow var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead"
   )
   public static final void subscribe(Flow var0) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead"
   )
   public static final void subscribe(Flow var0, Function2 var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use 'launchIn' with 'onEach', 'onCompletion' and 'catch' instead"
   )
   public static final void subscribe(Flow var0, Function2 var1, Function2 var2) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use 'flowOn' instead"
   )
   public static final Flow subscribeOn(Flow var0, CoroutineContext var1) {
      FlowKt.noImpl();
      throw new KotlinNothingValueException();
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Flow analogues of 'switchMap' are 'transformLatest', 'flatMapLatest' and 'mapLatest'",
      replaceWith = @ReplaceWith(
   expression = "this.flatMapLatest(transform)",
   imports = {}
)
   )
   public static final Flow switchMap(Flow var0, Function2 var1) {
      return FlowKt.transformLatest(var0, (Function3)(new Function3(var1, (Continuation)null) {
         final Function2 $transform;
         Object L$0;
         Object L$1;
         Object L$2;
         Object L$3;
         int label;
         private FlowCollector p$;
         private Object p$0;

         public {
            this.$transform = var1;
         }

         public final Continuation create(FlowCollector var1, Object var2, Continuation var3) {
            Function3 var4 = new <anonymous constructor>(this.$transform, var3);
            var4.p$ = var1;
            var4.p$0 = var2;
            return var4;
         }

         public final Object invoke(Object var1, Object var2, Object var3) {
            return ((<undefinedtype>)this.create((FlowCollector)var1, var2, (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            FlowCollector var3;
            Object var4;
            FlowCollector var5;
            Object var6;
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  Flow var10 = (Flow)this.L$3;
                  var3 = (FlowCollector)this.L$2;
                  var3 = (FlowCollector)this.L$0;
                  ResultKt.throwOnFailure(var1);
                  return Unit.INSTANCE;
               }

               var5 = (FlowCollector)this.L$2;
               var4 = this.L$1;
               var3 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
               var6 = var1;
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var8 = this.p$;
               var4 = this.p$0;
               Function2 var11 = this.$transform;
               this.L$0 = var8;
               this.L$1 = var4;
               this.L$2 = var8;
               this.label = 1;
               var6 = var11.invoke(var4, this);
               if (var6 == var7) {
                  return var7;
               }

               var3 = var8;
               var5 = var8;
            }

            Flow var9 = (Flow)var6;
            this.L$0 = var3;
            this.L$1 = var4;
            this.L$2 = var5;
            this.L$3 = var9;
            this.label = 2;
            if (var9.collect(var5, this) == var7) {
               return var7;
            } else {
               return Unit.INSTANCE;
            }
         }
      }));
   }
}
