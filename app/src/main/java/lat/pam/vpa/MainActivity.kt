package lat.pam.vpa

import ChatAdapter
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var chatAdapter: ChatAdapter
    private val chatMessages = mutableListOf<ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Handle window insets for proper padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Bind views
        val rvChat = findViewById<RecyclerView>(R.id.rvChat)
        val inputPrompt = findViewById<EditText>(R.id.inputPrompt)
        val sendButton = findViewById<ImageButton>(R.id.sendButton)

        // Set up RecyclerView
        chatAdapter = ChatAdapter(chatMessages)
        rvChat.adapter = chatAdapter
        rvChat.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true // Agar item terbaru muncul di bawah
        }

        // Handle send button click
        sendButton.setOnClickListener {
            val userInput = inputPrompt.text.toString().trim()
            if (!TextUtils.isEmpty(userInput)) {
                addMessage(userInput, isUser = true) // Tambah pesan pengguna
                inputPrompt.text.clear()

                // Simulasi respon bot
                simulateBotResponse(userInput)
            }
        }
    }

    private fun addMessage(message: String, isUser: Boolean) {
        chatMessages.add(ChatMessage(message, isUser))
        chatAdapter.notifyItemInserted(chatMessages.size - 1)
        scrollToBottom()
    }

    private fun simulateBotResponse(userMessage: String) {
        // Simulasi delay respon bot
        val botResponse = "Bot: Anda berkata '$userMessage'. Saya di sini untuk membantu!"
        findViewById<RecyclerView>(R.id.rvChat).postDelayed({
            addMessage(botResponse, isUser = false)
        }, 1000) // Delay 1 detik untuk memberi kesan "proses berpikir bot"
    }

    private fun scrollToBottom() {
        findViewById<RecyclerView>(R.id.rvChat).scrollToPosition(chatMessages.size - 1)
    }
}
