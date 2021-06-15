package com.pandaq.uires.mvp

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.pandaq.appcore.framework.mvp.BasePresenter
import java.util.*

/**
 * Created by huxinyu on 6/15/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
abstract class FragmentStackActivity<P : BasePresenter<*>, VB : ViewBinding> : BaseActivity<P, VB>() {



    // 自己维护的 fragment 回退栈
    private var fragmentTask = LinkedList<Fragment>()

    abstract fun fragmentHolderId(): Int

    override fun initVariable() {
        fragmentTask.clear()
    }

    /**
     * 类似Activity Standard 模式，入栈管理 fragment
     */
    fun pushFragment(@NonNull fragment: Fragment) {
        val current = if (fragmentTask.isEmpty()) {
            null
        } else {
            fragmentTask.last
        }
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        if (current == null) {
            transaction.add(fragmentHolderId(), fragment).commitNow()
        } else {
            transaction.hide(current).add(fragmentHolderId(), fragment).commitNow()
        }
        fragmentTask.add(fragment)
    }

    /**
     * 关闭 fragment 出栈
     *
     * @param fragment 出栈的 fragmen，类似 finish 掉某个 Acyivity。如果为空则栈顶出栈
     * @return 如果返回 true 表示出栈后还有 fragment 可显示，为 false 则表示栈已经出空了
     */
    fun popFragment(fragment: Fragment? = null): Boolean {
        val current = if (fragmentTask.isEmpty()) {
            null
        } else {
            fragmentTask.last
        }
        return if (fragment == null || fragment == current) {
            popFragment()
        } else {
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.remove(fragment).commit()
            fragmentTask.remove(fragment)
            false
        }
    }

    /**
     * 关闭 fragment 出栈
     *
     * @param fragment 出栈的 fragmen，类似 finish 掉某个 Acyivity。如果为空则栈顶出栈
     * @return 如果返回 true 表示出栈后还有 fragment 可显示，为 false 则表示栈已经出空了
     */
    private fun popFragment(): Boolean {
        return if (fragmentTask.size < 2) {
            false
        } else {
            val current = fragmentTask.last
            val last = fragmentTask[fragmentTask.size - 2]
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.show(last).remove(current).commitNow()
            fragmentTask.remove(current)
            fragmentTask.isNotEmpty()
        }
    }

}