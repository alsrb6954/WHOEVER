package com.kotlin.whoever.common

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class AutoClearedDisposable(private val lifecycleOwner:AppCompatActivity,
                            private val alwaysClearOnStop:Boolean = true,
                            private val compositeDisposable: CompositeDisposable = CompositeDisposable()):LifecycleObserver {
    // 디스포블 추가
    fun add(disposable: Disposable){
        check(lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED))

        compositeDisposable.add(disposable)
    }

    // onStop() 콜백 함수가 호출되면 함수 호출
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun cleanUp(){
        if(!alwaysClearOnStop && !lifecycleOwner.isFinishing) return

        compositeDisposable.clear()
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachSelf(){
        compositeDisposable.clear()

        lifecycleOwner.lifecycle.removeObserver(this)
    }
}