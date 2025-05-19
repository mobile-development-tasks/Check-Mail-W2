package com.example.myprofile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CheckmailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkmail)

        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val checkButton = findViewById<Button>(R.id.button)
        val errorTextView = findViewById<TextView>(R.id.errorTextView)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        // Ẩn thông báo lỗi ban đầu
        errorTextView.visibility = View.INVISIBLE

        checkButton.setOnClickListener {
            val email = emailEditText.text.toString()
            
            when {
                email.isEmpty() || email == "null" -> {
                    // Nếu email là null hoặc rỗng
                    errorTextView.text = "Email không hợp lệ"
                    errorTextView.visibility = View.VISIBLE
                    resultTextView.visibility = View.GONE
                }
                !email.contains("@") -> {
                    // Nếu email không chứa "@"
                    errorTextView.text = "Email không đúng định dạng"
                    errorTextView.visibility = View.VISIBLE
                    resultTextView.visibility = View.GONE
                }
                email.count { it == '@' } > 1 -> {
                    // Nếu email chứa nhiều hơn 1 ký tự "@"
                    errorTextView.text = "Email không đúng định dạng"
                    errorTextView.visibility = View.VISIBLE
                    resultTextView.visibility = View.GONE
                }
                !isValidEmail(email) -> {
                    // Email không đúng định dạng (kiểm tra chi tiết)
                    errorTextView.text = "Email không đúng định dạng"
                    errorTextView.visibility = View.VISIBLE
                    resultTextView.visibility = View.GONE
                }
                else -> {
                    // Email hợp lệ
                    errorTextView.visibility = View.INVISIBLE
                    resultTextView.text = "→ Bạn đã nhập email hợp lệ"
                    resultTextView.visibility = View.VISIBLE
                }
            }
        }
    }
    
    private fun isValidEmail(email: String): Boolean {
        // Kiểm tra email có chứa khoảng trắng không
        if (email.contains(" ")) return false
        
        // Kiểm tra cơ bản: phải có ký tự trước và sau "@"
        val parts = email.split("@")
        if (parts.size != 2) return false
        if (parts[0].isEmpty() || parts[1].isEmpty()) return false
        
        // Kiểm tra domain phải có ít nhất một dấu "."
        val domain = parts[1]
        if (!domain.contains(".")) return false
        
        // Kiểm tra trước và sau dấu "." trong tên miền phải có ký tự
        val domainParts = domain.split(".")
        for (part in domainParts) {
            if (part.isEmpty()) return false
        }
        
        return true
    }
}
