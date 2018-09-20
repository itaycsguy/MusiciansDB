package app.itaycsguy.musiciansdb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LocalAccount(currentActivity : AppCompatActivity) {
    private val SIGNED_IN : Int = 0
    private val _currentActivity : AppCompatActivity = currentActivity
    private val _localemail: EditText = currentActivity.findViewById(R.id.text_welcome_email)
    private val _localPassword: EditText = currentActivity.findViewById(R.id.text_welcome_password)
    private var _localSignInButton: Button = currentActivity.findViewById(R.id.sign_in_welcome_button) as Button
    private var _localSignUpButton: Button = currentActivity.findViewById(R.id.sign_up_welcome_button) as Button
    private var _localForgotButton: Button = currentActivity.findViewById(R.id.forgot_welcome_button) as Button
    private lateinit var _user : User

    init {
        this._localSignInButton.setOnClickListener {
            val a = this._currentActivity.intent.getStringExtra("photo")
            this.localUserValidate(this._localemail.text.toString(), this._localPassword.text.toString())
        }
        this._localSignUpButton.setOnClickListener {
            val intent = Intent(this._currentActivity, SignUpActivity::class.java)
            this._currentActivity.startActivity(intent)
        }
        this._localForgotButton.setOnClickListener {
            val intent = Intent(this._currentActivity, LoginRecoveryActivity::class.java)
            this._currentActivity.startActivity(intent)
        }
    }

    private fun localUserValidate(username: String,password: String) {
        // TODO: check guest input
        val map : HashMap<String,String> = HashMap()
        map.put("user_name",username)
        this._user = User(map)
        this.updateUI(this.SIGNED_IN)
    }

    public fun updateUI(login_status : Int) {
        val text : String
        when(login_status){
            this.SIGNED_IN -> {
                text = "Successfully Signed-In!"
                Toast.makeText(this._currentActivity, text, Toast.LENGTH_LONG).show()
                moveActivities(ProfileActivity())
            }
        }
    }

    public fun moveActivities(newActivity : AppCompatActivity) {
        val intent = Intent(this._currentActivity, newActivity::class.java)
        intent.putExtra("authentication_vendor","local")
        intent.putExtra("user_name",this._user.getUserName())
        this._currentActivity.startActivity(intent)
    }
}