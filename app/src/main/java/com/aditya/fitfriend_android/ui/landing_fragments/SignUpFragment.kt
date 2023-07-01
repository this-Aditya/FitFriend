package com.aditya.fitfriend_android.ui.landing_fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import com.aditya.fitfriend_android.R
import com.aditya.fitfriend_android.databinding.FragmentSignUpBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

private const val TAG = "SignUpFragment"
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var resultActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data).result
            account?.let { signInAccount ->
                googleAuthForFirebase(signInAccount)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: SignUpFragment")
        if (auth.currentUser != null){
            view.findNavController().navigate(R.id.action_signUpFragment_to_dashboardFragment)
        }

        binding.btnSignUp.setOnClickListener {
            val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val signInClient = GoogleSignIn.getClient(requireActivity(), options)
            signInClient.signInIntent.also { intent ->
                Log.i(TAG, "Launching sign-in activity")
                val activity = activity;
                if (isAdded && activity != null) {
                    resultActivityLauncher.launch(intent)
                }
            }
        }
    }

    private fun googleAuthForFirebase(account: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                auth.signInWithCredential(credentials).await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Welcome to FitFriend", Toast.LENGTH_SHORT)
                        .show()
                    Log.i(TAG, "Sign up successful")
                    view?.findNavController()?.navigate(R.id.action_signUpFragment_to_dashboardFragment)
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), ex.message, Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Exception while signing up: $ex")
                }
            }
        }
    }


}