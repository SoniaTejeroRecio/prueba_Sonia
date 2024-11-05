package com.example.pruebaandroid1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebaandroid1.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnIrListaCompra.setOnClickListener {
            val intent = Intent(this, ShoppingListActivity::class.java)
            startActivity(intent)
        }

        binding.btnIrTareas.setOnClickListener {
            val intent = Intent(this, TareaActivity::class.java)
            startActivity(intent)
        }

        binding.btnCambiarIdioma.setOnClickListener {
            cambiarIdioma("es")
        }
    }

    private fun cambiarIdioma(idioma: String) {
        val config = resources.configuration
        val locale = Locale(idioma)
        Locale.setDefault(locale)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        recreate()
    }
}
