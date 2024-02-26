package com.softeer.togeduck.ui.chatting

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import com.softeer.togeduck.data.model.chatting.ChatMessageModel
import com.softeer.togeduck.databinding.ActivityChatRoomBinding

class ChatRoomActivity: AppCompatActivity() {
    private lateinit var binding: ActivityChatRoomBinding
    private lateinit var viewModel: ChatRoomViewModel

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ChatRoomViewModel::class.java]

        val id = intent.getLongExtra("id", 0)
        val roomName = intent.getStringExtra("roomName")

        this.onBackPressedDispatcher.addCallback(this, callback)

        viewModel.init(id)

        observeChatMessages()
        observeNewChatMessage()

        binding.apply {
            setSupportActionBar(chatRoomToolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            chatRoomName.text = roomName

            chatRoomBackButton.setOnClickListener {
                finish()
            }

            rvChatRoomMessage.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
                if (bottom < oldBottom) {
                    rvChatRoomMessage.postDelayed({
                        if (rvChatRoomMessage.adapter!!.itemCount > 0) {
                            rvChatRoomMessage.scrollToPosition(rvChatRoomMessage.adapter!!.itemCount - 1)
                        }
                    }, 100)
                }
            }

            chatMessageSendBtn.setOnClickListener {
                val message = chatMessageEditText.text.toString()

                if (message.isNotEmpty()) {
                    viewModel.sendMessage(message)
                    chatMessageEditText.text.clear()
                } else {
                    chatMessageEditText.requestFocus()
                    keyBordShow()
                    Toast.makeText(this@ChatRoomActivity, "메세지를 입력하세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        viewModel.connectToStomp()
        viewModel.readMessage()
    }

    override fun onPause() {
        super.onPause()
        viewModel.disconnectStomp()
        viewModel.saveMessages()
    }

    private fun keyBordShow() {
        WindowInsetsControllerCompat(window, window.decorView).show(WindowInsetsCompat.Type.ime())
    }

    private fun observeChatMessages() {
        viewModel.chatMessages.observe(this) { messages ->
            binding.apply {
                val adapter = ChatMessageListAdapter(messages as ArrayList<ChatMessageModel>)

                rvChatRoomMessage.adapter = adapter
                rvChatRoomMessage.scrollToPosition(rvChatRoomMessage.adapter!!.itemCount - 1)
            }
        }
    }

    private fun observeNewChatMessage() {
        viewModel.newMessages.observe(this) { messages ->
            binding.apply {
                val adapter = rvChatRoomMessage.adapter as ChatMessageListAdapter

                adapter.addItem(messages.last())
                rvChatRoomMessage.scrollToPosition(rvChatRoomMessage.adapter!!.itemCount - 1)
            }
        }
    }
}