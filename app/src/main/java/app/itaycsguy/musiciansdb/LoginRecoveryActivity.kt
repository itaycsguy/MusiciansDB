package app.itaycsguy.musiciansdb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginRecoveryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_recovery)

        val sendButton = findViewById(R.id.send_button) as Button
        sendButton.setOnClickListener {
            val emailAddrText = (findViewById(R.id.email_address_text) as EditText).text.toString()
            if(emailAddrText.length > 0) {
                this.sendEmail(emailAddrText)
                val text = "An email is send to " + emailAddrText + "!\nCheck your email inbox."
                Toast.makeText(this@LoginRecoveryActivity, text, Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val text = "Missing email address."
                Toast.makeText(this@LoginRecoveryActivity, text, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun sendEmail(addr: String){

    }
}