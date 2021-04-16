package com.github.ainul.pof

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonLogin: Button = this.findViewById(R.id.btnLogin)
        buttonLogin.setOnClickListener {
            if (startAuth()) goToDashboard()
            else showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val view = findViewById<ConstraintLayout>(R.id.activityMainLayout)

        // hide keyboard to show error message
        val inputManager: InputMethodManager =
            this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)

        // show alertDialog
        val dialog = AlertDialog.Builder(this).create()
        dialog.setMessage(resources.getString(R.string.error_message))
        dialog.setButton(Dialog.BUTTON_POSITIVE, "OK") { e, _ ->
            e.dismiss()
        }

        dialog.show()
    }

    private fun goToDashboard() {
        val dashboardIntent = Intent(this, Dashboard::class.java)
        startActivity(dashboardIntent)
    }

    private fun startAuth(): Boolean {
        val inputUsername: String =
            findViewById<TextInputEditText>(R.id.inputUsername).text.toString()
        val inputPassword: String =
            findViewById<TextInputEditText>(R.id.inputPassword).text.toString()

        // no need to add toLowerCase / toUpperCase since Authentication is case insensitive
        return inputUsername == "gbs_user" && inputPassword == 123456.toString()
    }
}