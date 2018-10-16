package com.kotlin.whoever.view.content.main

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import com.kotlin.whoever.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val fab_open by lazy {
        AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open)
    }
    private val fab_close by lazy {
        AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close)
    }
    private var isFabOpen = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menu_icon.apply {
            useHardwareAcceleration(true)
            speed = -1f
        }
        menu_icon.setOnClickListener {
                menu_icon.apply {
                    speed = -speed
                    setMinAndMaxFrame(0, 50)
                    if (!isAnimating) { playAnimation() }
                }
        }
        fab.apply {
            useHardwareAcceleration(true)
            speed = -1f
        }
        fab.setOnClickListener {
            anim()
            fab.apply {
                speed = -speed
                setMinAndMaxFrame(0, 5)
                if (!isAnimating) {
                    playAnimation()
                }
            }
        }
        fab1.setOnClickListener(this)
        fab2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            fab1 -> toast("fab1")
            fab2 -> toast("fab2")
        }
    }
    fun anim(){
        if (isFabOpen) {
            fab1.apply {
                startAnimation(fab_close)
                isClickable = false
            }
            fab2.apply {
                startAnimation(fab_close)
                isClickable = false
            }
            isFabOpen = false
        } else {
            fab1.apply {
                startAnimation(fab_open)
                isClickable = true
            }
            fab2.apply {
                startAnimation(fab_open)
                isClickable = true
            }
            isFabOpen = true
        }
    }
}
