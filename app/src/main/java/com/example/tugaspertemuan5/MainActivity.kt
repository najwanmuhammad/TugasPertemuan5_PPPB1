package com.example.tugaspertemuan5

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugaspertemuan5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val jenis_kelamin = resources.getStringArray(R.array.jenis_kelamin)
        val adapter = ArrayAdapter (this, android.R.layout.simple_spinner_item, jenis_kelamin)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(binding){
            inputJenisKelamin.adapter = adapter

            txtLogin.setOnClickListener {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            buttonRegister.setOnClickListener {
                val name = inputName.text.toString()
                val email = inputEmail.text.toString()
                val nohp = inputNohp.text.toString()
                val jenisKelamin = inputJenisKelamin.selectedItem?.toString() ?: ""
                val password = inputPassword.text.toString()
                val confirmPassword =inputConfirmPassword.text.toString()

                val userData = mapOf(
                    "Nama" to name,
                    "Email" to email,
                    "Nomor Handphone" to nohp,
                    "Jenis Kelamin" to jenisKelamin,
                    "Password" to password,
                    "Konfirmasi Password" to confirmPassword
                )

                var canSubmit = true

                userData.forEach { key, value ->
                    if (value.length == 0) {
                        Toast.makeText(this@MainActivity, key + " harus diisi!", Toast.LENGTH_SHORT).show()
                        canSubmit = false
                    }
                }

                if (password != confirmPassword) {
                    Toast.makeText(this@MainActivity, "Konfirmasi Password Salah!", Toast.LENGTH_SHORT).show()
                    canSubmit = false
                }

                if (canSubmit) {
                    Toast.makeText(this@MainActivity, "Registrasi berhasil", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    intent.putExtra("EXTRA_NAMA", name)
                    intent.putExtra("EXTRA_EMAIL", email)
                    intent.putExtra("EXTRA_NOHP", nohp)
                    intent.putExtra("EXTRA_JENIS_KELAMIN", jenisKelamin)
                    intent.putExtra("EXTRA_PASSWORD", password)
                    startActivity(intent)
                }

            }
        }

    }
}