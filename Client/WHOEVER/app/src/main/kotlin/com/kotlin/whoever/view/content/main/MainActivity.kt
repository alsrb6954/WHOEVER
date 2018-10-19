package com.kotlin.whoever.view.content.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import com.jakewharton.rxbinding2.view.RxView
import com.kotlin.whoever.R
import com.kotlin.whoever.common.AutoClearedDisposable
import com.kotlin.whoever.extensions.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private val fab_open by lazy {
        AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open)
    }
    private val fab_close by lazy {
        AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close)
    }
    private lateinit var toggle: ActionBarDrawerToggle
    private var isFabOpen = false


    private val disposables = AutoClearedDisposable(this)
    private val viewDisposables = AutoClearedDisposable(lifecycleOwner = this, alwaysClearOnStop = false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // LifeCycle.Observer() 함수를 사용하여 AutoClearedDisposable 객체를 옵서버에 등록
        lifecycle += disposables
        lifecycle += viewDisposables

        toggle = object : ActionBarDrawerToggle(this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                menu_icon.apply {
                    speed = -speed
                    setMinAndMaxFrame(0, 50)
                    if (!isAnimating) {
                        playAnimation()
                    }
                }
            }
        }
        toggle.syncState()
        drawer_layout.addDrawerListener(toggle)

        menu_icon.apply {
            useHardwareAcceleration(true)
            speed = -5f
        }
        fab.apply {
            useHardwareAcceleration(true)
            speed = -1f
        }
        viewDisposables += RxView.clicks(menu_icon)
                .map {
                    menu_icon.apply {
                        speed = -speed
                        setMinAndMaxFrame(0, 50)
                    }
                }
                .filter { !it.isAnimating }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it.playAnimation()
                    drawer_layout.openDrawer(GravityCompat.START)
                }
        viewDisposables += RxView.clicks(fab)
                .map {
                    fab.apply {
                        speed = -speed
                        setMinAndMaxFrame(0, 5)
                    }
                }
                .filter { !it.isAnimating }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it.playAnimation()
                    anim()
                }

        fab1.setOnClickListener(this)
        fab2.setOnClickListener(this)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            fab1 -> toast("fab1")
            fab2 -> toast("fab2")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun anim() {
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
