package com.example.buslocator.Credentials

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.buslocator.R
import com.example.buslocator.UserLayouts.ProviderType
import com.example.buslocator.UserLayouts.mapLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CredentialsActivity : AppCompatActivity() {

    var state = 1 //1 - Login, 2 - Sign up, 3 - Forgot

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: CredentialsActivity

    lateinit var cred_tv: TextView
    lateinit var forgot_tv: TextView
    lateinit var signUp_tv: TextView

    lateinit var tilEmail: TextInputLayout
    lateinit var tilPass: TextInputLayout

    lateinit var etEmail: TextInputEditText
    lateinit var etPass: TextInputEditText

    lateinit var buttonAction: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credentials)

        //Firebase

        auth = Firebase.auth


        // Layout components

        cred_tv = findViewById(R.id.cred_tv)
        tilEmail = findViewById(R.id.tilEmail)
        tilPass = findViewById(R.id.tilPass)
        etEmail = findViewById(R.id.etEmail)
        etPass = findViewById(R.id.etPass)

        //

        forgot_tv = findViewById(R.id.forgot_tv)
        signUp_tv = findViewById(R.id.signUp_tv)

        forgot_tv.setOnClickListener {
            if(state==3){
                state = 1

                cred_tv.setText("Inicia Sesión")
                tilPass.hint = "Contraseña"
                forgot_tv.setText("Olvidé mi Contraseña")
                signUp_tv.setText("Registrarse!")
                buttonAction.setText("Iniciar Sesion")
            } else {
                state = 3

                cred_tv.setText("Recuperar Contraseña")
                tilPass.hint = "Confirmar Correo"
                forgot_tv.setText("Recordé mi Contraseña")
                signUp_tv.setText("")
                buttonAction.setText("Enviar Correo")
            }
        }

        signUp_tv.setOnClickListener {
            if(state==2){
                state = 1

                cred_tv.setText("Inicia Sesión")
                tilPass.hint = "Contraseña"
                forgot_tv.setText("Olvidé mi Contraseña")
                signUp_tv.setText("Registrarse!")
                buttonAction.setText("Iniciar Sesion")
            }else {
                state = 2

                cred_tv.setText("Registrate")
                tilPass.hint = "Contraseña"
                forgot_tv.setText("")
                signUp_tv.setText("Iniciar Sesion")
                buttonAction.setText("Registrate")
            }
        }

        buttonAction = findViewById(R.id.bAction)

        buttonAction.setOnClickListener{
            if (state == 1) {
                login()
            } else if (state == 2) {
                signUp()
            } else {
                forgot()
            }
        }
    }

    private fun forgot() {
        buttonAction.setOnClickListener {
            if(etEmail.text!!.isNotEmpty() && etPass.text!!.isNotEmpty() && etEmail.text == etPass.text) {
                FirebaseAuth.getInstance()
                    .sendPasswordResetEmail(
                        etEmail.text.toString()).addOnCompleteListener {
                        if(it.isSuccessful){
                            successAlert()
                        }else{
                            errorAlert()
                        }
                    }
            }
        }
    }

    private fun signUp() {
        buttonAction.setOnClickListener {
            if(etEmail.text!!.isNotEmpty() && etPass.text!!.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        etEmail.text.toString(), etPass.text.toString()).addOnCompleteListener {
                            if(it.isSuccessful){
                                showMap(it.result?.user?.email ?: "", ProviderType.BASIC)
                                successAlert()
                            }else{
                                errorAlert()
                            }
                    }
            }
        }
    }

    private fun login() {
        buttonAction.setOnClickListener {
            if(etEmail.text!!.isNotEmpty() && etPass.text!!.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        etEmail.text.toString(), etPass.text.toString()).addOnCompleteListener {
                        if(it.isSuccessful){
                            showMap(it.result?.user?.email ?: "", ProviderType.BASIC)
                            successAlert()
                        }else{
                            errorAlert()
                        }
                    }
            }
        }
    }


    private fun successAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Reset de Password Correcto")
        builder.setMessage("Verifica tu correo electronico!")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun errorAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showMap(email: String, provider: ProviderType) {

        val mapIntent = Intent(this, mapLayout::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(mapIntent)
    }
}