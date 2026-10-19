package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.myapplication.databinding.ActivityDetalhesDestinoBinding

class DetalhesDestinoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesDestinoBinding
    private lateinit var destino: Destino

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesDestinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupViews()
        setupListeners()
    }

    private fun loadData() {
        destino = intent.getSerializableExtra("destino") as Destino
    }

    private fun setupViews() {
        binding.tvPais.text = destino.pais
        binding.tvCapital.text = "Capital: ${destino.capital}"
        binding.tvHotel.text = "Hotel: ${destino.hotel}"
        binding.tvTelefone.text = "Telefone: ${destino.telefone}"
        binding.imgFoto.setImageResource(destino.foto)
    }

    private fun setupListeners() {
        binding.btnLigar.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = "tel:${destino.telefone}".toUri()
            startActivity(intent)
        }

        binding.btnComoChegar.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = "geo:0,0?q=${destino.hotel}".toUri()
            startActivity(intent)
        }

        binding.btnEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = "mailto:contato@ifsp.edu.br".toUri()
            startActivity(intent)
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}
