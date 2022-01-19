package com.pandaq.uires.widget.numberkeyboard

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.pandaq.uires.R
import com.pandaq.uires.databinding.ViewNumberKeyboardBinding

/**
 * Created by huxinyu on 6/11/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class NumberKeyboard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), View.OnClickListener, TextWatcher {

    private val binding: ViewNumberKeyboardBinding =
        ViewNumberKeyboardBinding.inflate(LayoutInflater.from(context), this, false)
    private var inputView: EditText? = null
    private var inputListener: NumberInputListener? = null
    private var longConfirmListener: OnLongClickListener? = null

    fun bindInput(inputView: EditText) {
        this.inputView = inputView
        this.inputView?.requestFocus()
        this.inputView?.isClickable = false
        this.inputView?.addTextChangedListener(this)
        inputView.text?.let {
            binding.keyConfirm.isEnabled = it.isNotEmpty()
        }
    }

    fun setInputListener(inputListener: NumberInputListener) {
        this.inputListener = inputListener
    }

    init {
        addView(binding.root)
        binding.key0.setOnClickListener(this)
        binding.key1.setOnClickListener(this)
        binding.key2.setOnClickListener(this)
        binding.key3.setOnClickListener(this)
        binding.key4.setOnClickListener(this)
        binding.key5.setOnClickListener(this)
        binding.key6.setOnClickListener(this)
        binding.key7.setOnClickListener(this)
        binding.key8.setOnClickListener(this)
        binding.key9.setOnClickListener(this)
        binding.keyBack.setOnClickListener(this)
        binding.keyBack.setOnLongClickListener {
            inputView?.setText("")
            return@setOnLongClickListener true
        }
        binding.keyConfirm.setOnClickListener(this)
        binding.keyConfirm.setOnLongClickListener {
            longConfirmListener?.onLongClick(it)
            return@setOnLongClickListener true
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.key_1 -> {
                input(1)
            }
            R.id.key_2 -> {
                input(2)
            }
            R.id.key_3 -> {
                input(3)
            }
            R.id.key_4 -> {
                input(4)
            }
            R.id.key_5 -> {
                input(5)
            }
            R.id.key_6 -> {
                input(6)
            }
            R.id.key_7 -> {
                input(7)
            }
            R.id.key_8 -> {
                input(8)
            }
            R.id.key_9 -> {
                input(9)
            }
            R.id.key_back -> {
                inputView?.let {
                    val index = it.selectionStart
                    if (index > 0) {
                        it.text = it.text.delete(index - 1, index)
                        inputListener?.onInputChanged(inputView?.text.toString())
                        inputView?.setSelection(index - 1)
                    } else {
                        it.setText("")
                    }
                }
            }
            R.id.key_0 -> {
                input(0)
            }
            R.id.key_confirm -> {
                inputListener?.onConfirmed(inputView?.text.toString())
            }
        }
    }

    private fun input(value: Int) {
        inputView?.let {
            val index = it.selectionStart
            val editable = it.text
            editable?.insert(index, value.toString())
            inputListener?.onInputChanged(it.text.toString())
        }
    }

    fun setLongConfirmListener(listener: OnLongClickListener) {
        this.longConfirmListener = listener
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setKeyBackground(@DrawableRes drawable: Int) {
        binding.key0.setBackgroundResource(drawable)
        binding.key1.setBackgroundResource(drawable)
        binding.key2.setBackgroundResource(drawable)
        binding.key3.setBackgroundResource(drawable)
        binding.key4.setBackgroundResource(drawable)
        binding.key5.setBackgroundResource(drawable)
        binding.key6.setBackgroundResource(drawable)
        binding.key7.setBackgroundResource(drawable)
        binding.key8.setBackgroundResource(drawable)
        binding.key9.setBackgroundResource(drawable)
        binding.keyBack.setBackgroundResource(drawable)
    }

    fun setConfirmBackground(@DrawableRes drawable: Int) {
        binding.keyConfirm.setBackgroundResource(drawable)
    }

    fun setDeleteText(text: String) {
        binding.keyBack.text = text
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        inputView?.text?.let {
            binding.keyConfirm.isEnabled = it.isNotEmpty()
        }
    }
}