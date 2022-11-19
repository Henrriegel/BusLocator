package com.example.buslocator.Credentials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.buslocator.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CredentialsActivity : AppCompatActivity() {

    var state = 1 //1 - Login, 2 - Sign up, 3 - Forgot

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

        // Layout components

        cred_tv = findViewById(R.id.cred_tv)
        tilEmail = findViewById(R.id.tilEmail)
        tilPass = findViewById(R.id.tilPass)
        etEmail = findViewById(R.id.etEmail)
        etPass = findViewById(R.id.etPass)

        //

        forgot_tv = findViewById(R.id.forgot_tv)
        signUp_tv = findViewById(R.id.signUp_tv)

        cred_tv.setOnClickListener {

        }

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

    }

    private fun signUp() {

    }

    private fun login() {

    }
}