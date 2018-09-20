package app.itaycsguy.musiciansdb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class LoginActivity : AppCompatActivity() {
    // google
    private lateinit var _googleAccount : GoogleAccount
    // firebase
    private lateinit var _firebase : Firebase
    // local
    private lateinit var _localAccount : LocalAccount
    private lateinit var _usernameField : EditText
    private lateinit var _passwordField : EditText

    private fun fillDetails(savedInstanceState : Intent) {
        val email = savedInstanceState.getStringExtra("email")
        if(email != null) {
            this._usernameField.append(email.toString())
        }
        val password = savedInstanceState.getStringExtra("password")
        if(password != null) {
            this._passwordField.append(password.toString())
        }
    }

    private fun init(savedInstanceState: Intent){
        // variables
        this._usernameField = findViewById(R.id.text_welcome_email) as EditText
        this._passwordField = findViewById(R.id.text_welcome_password) as EditText
        val from_activity = savedInstanceState.getStringExtra("from_activity")
        if(from_activity != null && from_activity == "SignUpActivity") {
            val photo = savedInstanceState.getStringExtra("photo")
            this.fillDetails(savedInstanceState)
            // TODO: need to seperate this case to different issues
        }
        // firebase
        this._firebase = Firebase()
        // google
        this._googleAccount = GoogleAccount(this)
        // local
        this._localAccount = LocalAccount(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // page building
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // initialize variables
        this.init(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == this._googleAccount.getRequestCode()){
            this._googleAccount.handleResults(data)
            this._firebase.connectByGoogle(this._googleAccount.getGoogleResult().signInAccount!!,this,ProfileActivity())
            this._googleAccount.updateUI(this._googleAccount.SIGNED_IN)
        } else {
            this._googleAccount.updateUI(this._googleAccount.ERROR)
        }
    }
}