package com.pandaq.uires.widget.marqueeview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.pandaq.uires.R
import com.pandaq.uires.widget.marqueeview.MarqueeView.InnerAdapter.MarqueHolder
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by huxinyu on 2019/5/29.
 * Email : panda.h@foxmail.com
 * Description :跑马灯滚动效果 ViewGroup
 */
class MarqueeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr), LifecycleObserver {

    private var disposable: Disposable? = null
    private var layoutManager: LoopLayoutManager? = null
    private var adapter: InnerAdapter? = null
    private var itemClickListener: OnItemClickListener? = null

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return if (ev.action == MotionEvent.ACTION_MOVE) {
            true
        } else super.dispatchTouchEvent(ev)
    }

    fun <T : IMarqueeItem> setData(items: ArrayList<T>) {
        if (layoutManager == null) {
            layoutManager = LoopLayoutManager(context)
            setLayoutManager(layoutManager)
        }
        if (adapter == null) {
            adapter = InnerAdapter(context)
        }
        setAdapter(adapter)
        adapter?.setData(items)
        adapter?.setItemClickListener(itemClickListener)
        // 重置
        scrollToPosition(0)
    }

    private fun startLoop() {
        Observable.interval(0, 5, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Long) {
                    adapter?.let {
                        if (it.itemCount > 0) {
                            scrollBy(1, 0)
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {

                }

            })
    }

    private fun stopLoop() {
        disposable?.dispose()
    }

    /**
     * 设置 item 的点击事件
     */
    fun setItemClickListener(itemClickListener: OnItemClickListener?) {
        this.itemClickListener = itemClickListener
        adapter?.setItemClickListener(this.itemClickListener)
    }

    /**
     * 界面 onResume 时恢复滚动
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        startLoop()
    }

    /**
     * 界面 onPause 时暂停滚动
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        stopLoop()
    }

    /**
     * Adapter类
     */
    private class InnerAdapter(context: Context?) :
        Adapter<MarqueHolder>() {

        private val mData: MutableList<IMarqueeItem> = arrayListOf()

        private var mItemClickListener: OnItemClickListener? = null
        private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarqueHolder {
            val view = mLayoutInflater.inflate(R.layout.res_marquee_item, parent, false)
            return MarqueHolder(view)
        }

        override fun onBindViewHolder(holder: MarqueHolder, position: Int) {
            holder.tvTag.text = mData[position].getTag()
            holder.tv.text = mData[position].getTitle()
            holder.tv.setOnClickListener {
                mItemClickListener?.onItemClicked(mData[position])
            }
        }

        override fun getItemCount(): Int {
            return mData.size
        }

        /**
         * * ViewHolder类
         */
        private class MarqueHolder(view: View) : ViewHolder(view) {
            var tv: TextView = view.findViewById(R.id.tv_marquee_item)
            var tvTag: TextView = view.findViewById(R.id.tv_marquee_tag)
        }

        fun <T : IMarqueeItem> setData(data: ArrayList<T>) {
            mData.clear()
            mData.addAll(data)
            notifyDataSetChanged()
        }

        /**
         * 设置 item 的点击事件
         */
        fun setItemClickListener(itemClickListener: OnItemClickListener?) {
            mItemClickListener = itemClickListener
        }

    }

    /**
     * item 点击事件
     */
    interface OnItemClickListener {
        fun onItemClicked(marqueeItem: IMarqueeItem?)
    }
}