package app.itaycsguy.musiciansdb

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


class ProfileActivity : AppCompatActivity() {
    private val _firebase : Firebase = Firebase()
    private lateinit var _authenticationVendor : String
    private lateinit var name: String
    private var email: String? = null
    private var image: Uri? = null
    private var givenName: String? = null
    private var familyName: String? = null

    private fun init(savedInstanceState: Intent){
        this._authenticationVendor = savedInstanceState.getStringExtra("authentication_vendor")
        this.name = savedInstanceState.getStringExtra("user_name")
        if(this._authenticationVendor == "google") {
            this.email = savedInstanceState.getStringExtra("email")
            this.image = Uri.parse(savedInstanceState.getStringExtra("photo"))
            this.givenName = savedInstanceState.getStringExtra("given_name")
            this.familyName = savedInstanceState.getStringExtra("family_name")
        }
        val localImage = findViewById<ImageView>(R.id.my_profile_photo)
        if(this.image != null && this.image != Uri.EMPTY && this.image != Uri.parse("null")) {
            localImage.setImageURI(this.image)
        }
        findViewById<TextView>(R.id.profile_username).append(" ${this.name}")
        findViewById<TextView>(R.id.profile_email).append(" ${this.email}")
        val continueButton = findViewById(R.id.continue_profile_button) as Button
        continueButton.setOnClickListener {
            val intent = Intent(this, MoragActivity::class.java)
            startActivity(intent)
        }
        val signOutButton = findViewById(R.id.sign_out_profile_button) as Button
        signOutButton.setOnClickListener {
            if(this._authenticationVendor == "google") {
                this._firebase.disconnect()
                val text = "Successfully Signed-Out!"
                Toast.makeText(this, text, Toast.LENGTH_LONG).show()
            } else {
                this._firebase.disconnect()
                val text = "Successfully Signed-Out!"
                Toast.makeText(this, text, Toast.LENGTH_LONG).show()
            }
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        this.init(intent)
    }
}