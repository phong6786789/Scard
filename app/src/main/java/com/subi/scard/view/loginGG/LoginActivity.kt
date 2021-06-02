package com.subi.scard.view.loginGG

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private lateinit var dialog: AlertDialog
    private lateinit var fbCallbackManager: CallbackManager

    private val loginViewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)
        printKeyHash(this)
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

        loginViewModel.status.observe(this){
            it?.let {
                when(it){
                    "login" -> {
//                        Toast.makeText(
//                            baseContext,
//                            "Login success",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        Utils.tempNext(this, MainActivity::class.java)
                    }
                    "success" ->{
//                        Toast.makeText(
//                            baseContext,
//                            "Insert User success",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        Utils.tempNext(this, MainActivity::class.java)
                    }
                    "failed" ->{
//                        Toast.makeText(
//                            baseContext,
//                            "Insert User failed",
//                            Toast.LENGTH_SHORT
//                        ).show()
                    }
                }
            }
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
                    Log.d("chauAPI", e.message.toString())
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
//                Toast.makeText(
//                    baseContext,
//                    "Login google failed",
//                    Toast.LENGTH_SHORT
//                ).show()
            }
    }

    private fun updateUI(user: FirebaseUser?){
        user?.let {
            loginViewModel.checkUser(it.uid)
        }
        dialog.dismiss()
    }

    protected fun signInFb() {
        LoginManager.getInstance()
            .logInWithReadPermissions(this, arrayListOf("email"))
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
//                Toast.makeText(
//                    baseContext, "facebook cancel.",
//                    Toast.LENGTH_SHORT
//                ).show()
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
//                    Toast.makeText(
//                        baseContext, "Login facebook failed.",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    updateUI(null)
                }
            }
    }

    companion object{
        const val SNS_REQUEST_CODE_GOOGLE = 1
    }

    fun printKeyHash(context: Activity): String? {
        val packageInfo: PackageInfo
        var key: String? = null
        try {
            //getting application package name, as defined in manifest
            val packageName = context.applicationContext.packageName

            //Retriving package info
            packageInfo = context.packageManager.getPackageInfo(
                packageName,
                PackageManager.GET_SIGNATURES
            )
            Log.e("Package Name=", context.applicationContext.packageName)
            for (signature in packageInfo.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                key = String(Base64.encode(md.digest(), 0))

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key!!)
            }
        } catch (e1: PackageManager.NameNotFoundException) {
            Log.e("Name not found", e1.toString())
        } catch (e: NoSuchAlgorithmException) {
            Log.e("No such an algorithm", e.toString())
        } catch (e: Exception) {
            Log.e("Exception", e.toString())
        }
        return key
    }

    override fun onResume() {
        dialog.dismiss()
        super.onResume()
    }
}