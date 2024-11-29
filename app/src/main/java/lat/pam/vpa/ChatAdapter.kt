import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lat.pam.vpa.ChatMessage
import lat.pam.vpa.R

class ChatAdapter(private val chatMessages: List<ChatMessage>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatMessage = chatMessages[position]
        holder.bind(chatMessage)
    }

    override fun getItemCount(): Int = chatMessages.size

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val leftChat: View = itemView.findViewById(R.id.left_chat)
        private val rightChat: View = itemView.findViewById(R.id.right_chat)
        private val leftTextChat: TextView = itemView.findViewById(R.id.left_text_chat)
        private val rightTextChat: TextView = itemView.findViewById(R.id.right_text_chat)

        fun bind(chatMessage: ChatMessage) {
            if (chatMessage.isUser) {
                // Tampilkan pesan pengguna (right_chat)
                rightChat.visibility = View.VISIBLE
                leftChat.visibility = View.GONE
                rightTextChat.text = chatMessage.message
            } else {
                // Tampilkan pesan bot (left_chat)
                leftChat.visibility = View.VISIBLE
                rightChat.visibility = View.GONE
                leftTextChat.text = chatMessage.message
            }
        }
    }
}
