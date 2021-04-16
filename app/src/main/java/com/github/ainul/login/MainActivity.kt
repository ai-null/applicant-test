package com.github.ainul.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonLogin: Button = this.findViewById(R.id.btnLogin)
        buttonLogin.setOnClickListener {
            if (startAuth()) goToDashboard()
            else showSnackBar()
        }
    }

    private fun showSnackBar() {
        val view = findViewById<ConstraintLayout>(R.id.activityMainLayout)

        // hide keyboard to show error message
        val inputManager: InputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)

        // show snackBar
        Snackbar.make(
            this,
            view,
            "Invalid Credentials",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun goToDashboard() {
        val dashboardIntent = Intent(this, Dashboard::class.java)
        startActivity(dashboardIntent)
    }

    private fun startAuth(): Boolean {
        val inputUsername: String = findViewById<TextInputEditText>(R.id.inputUsername).text.toString()
        val inputPassword: String = findViewById<TextInputEditText>(R.id.inputPassword).text.toString()

        // no need to add toLowerCase / toUpperCase since Authentication is case insensitive
        return inputUsername == "user" && inputPassword == 123456.toString()
    }
}