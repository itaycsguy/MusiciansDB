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
        val map : HashMap<String,String> = HashMap()
        val username = savedInstanceState.getStringExtra("user_name")
        map.put("user_name",username)
        if(username != null) {
            this._usernameField.append(username.toString())
        }
        val password = savedInstanceState.getStringExtra("password")
        map.put("password",password)
        if(password != null) {
            this._passwordField.append(password.toString())
        }
        val email = savedInstanceState.getStringExtra("email")
        map.put("email",email)
        val authentication_vendor = savedInstanceState.getStringExtra("authentication_vendor")
        map.put("authentication_vendor",authentication_vendor)
        val pathString = savedInstanceState.getStringExtra("pathString")
        if(authentication_vendor != null && pathString != null) {
            this._localAccount = LocalAccount(this)
            this._localAccount.writeDB(pathString, map)
        }
    }

    private fun init(savedInstanceState: Intent){
        // variables
        this._usernameField = findViewById(R.id.text_username) as EditText
        this._passwordField = findViewById(R.id.text_password) as EditText
        this.fillDetails(savedInstanceState)
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