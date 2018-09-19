package app.itaycsguy.musiciansdb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class Firebase {
    private val _firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()

    public fun connectByGoogle(acct: GoogleSignInAccount, activitySrc : AppCompatActivity, activityDest : AppCompatActivity) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        this._firebaseAuth.signInWithCredential(credential).addOnCompleteListener(activitySrc) {
            task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                activitySrc.startActivity(Intent(activitySrc,activityDest::class.java))
            } else {
                throw Exception(task.exception)
            }
        }
    }

    public fun disconnect() : Boolean {
        try {
            this._firebaseAuth.signOut()
            return true
        } catch(e : Exception){
            print(e.message)
            return false
        }
    }
}