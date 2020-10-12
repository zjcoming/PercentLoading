package com.example.percentloading

import android.animation.Animator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //懒加载属性动画
    val downLoadAnimator : ValueAnimator by lazy {
        ValueAnimator.ofFloat(0f,1f).apply {
            duration = 2000
            addUpdateListener {
                mLoading.progress = animatedValue as Float
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start.setOnClickListener{
            showAnim()
        }
        pause.setOnClickListener{
            downLoadAnimator.pause()
        }
    }
    //展示动画的函数
    fun showAnim(){
        if(downLoadAnimator.isPaused){
            downLoadAnimator.resume()
        } else if(downLoadAnimator.isRunning){

        }else{
            downLoadAnimator.start()
        }
    }
}