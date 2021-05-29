package com.subi.scard.view.loginGG

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.subi.scard.R
import com.subi.scard.databinding.ActivityLoginBinding
import com.subi.scard.utils.Utils
import com.subi.scard.view.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var dialog: AlertDialog
    private lateinit var fbCallbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)

        dialog = Utils.showProgressBar(this, "Loading...")

        instanceGoogleSignIn()
        instanceFacebookSignIn()

        auth.signOut()
        googleSignInClient.signOut()


        binding.buttonLoginGg.setOnClickListener {
            signInGoogle()
        }
        binding.buttonLoginFb.setOnClickListener {
            signInFb()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        fbCallbackManager.onActivityResult(requestCode, resultCode, data)

        dialog.show()
        when (requestCode) {
            SNS_REQUEST_CODE_GOOGLE -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
        }
    }

    private fun instanceGoogleSignIn() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, SNS_REQUEST_CODE_GOOGLE)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnSuccessListener { task ->
                updateUI(task.user)
            }
            .addOnFailureListener {
                Log.d("chauAPI", it.message.toString())
                updateUI(null)
                Toast.makeText(
                    baseContext,
                    "Login google failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun updateUI(user: FirebaseUser?){
        user?.let {
            Toast.makeText(this, it.uid, Toast.LENGTH_SHORT).show()
            Utils.tempNext(this, MainActivity::class.java)
        }
        dialog.dismiss()
    }

    protected fun signInFb() {
        LoginManager.getInstance()
            .logInWithReadPermissions(this, arrayListOf("public_profile", "user_friends"))
    }

    private fun instanceFacebookSignIn() {
        fbCallbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(fbCallbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                updateUI(null)
                Toast.makeText(
                    baseContext, "facebook cancel.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onError(error: FacebookException) {
                Log.d("chauAPI", error.message.toString())
                updateUI(null)
                Toast.makeText(
                    baseContext, "facebook error.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("TAG", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("chauAPI", task.exception?.message.toString())
                    Toast.makeText(
                        baseContext, "Login facebook failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    companion object{
        const val SNS_REQUEST_CODE_GOOGLE = 1
    }
}