package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityDestinosBinding
import com.example.myapplication.databinding.ItemDestinoBinding
import java.io.Serializable

data class Destino(
    val pais: String,
    val capital: String,
    val hotel: String,
    val telefone: String,
    val foto: Int = R.drawable.ic_launcher_foreground
) : Serializable

class DestinoAdapter(
    context: Context,
    private val destinos: List<Destino>
) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    override fun getCount() = destinos.size
    override fun getItem(position: Int) = destinos[position]
    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemDestinoBinding.inflate(inflater, parent, false)
        val destino = destinos[position]

        binding.tvPais.text = destino.pais
        binding.tvCapital.text = "Capital: ${destino.capital}"

        return binding.root
    }
}

class DestinosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDestinosBinding

    private val destinos = listOf(
        Destino("Japão", "Tóquio", "Shinjuku Hotel", "81351552666", R.drawable.japao),
        Destino("França", "Paris", "Hotel Le Bristol", "33153434300", R.drawable.paris),
        Destino("Itália", "Roma", "Hotel Artemide", "3906489911", R.drawable.roma),
        Destino("Egito", "Cairo", "Marriott Mena House", "20233773222", R.drawable.egito),
        Destino("Austrália", "Camberra", "Hyatt Hotel", "61262701234", R.drawable.australia),
        Destino("Canadá", "Ottawa", "Fairmont Château", "16132411414", R.drawable.canada),
        Destino("Portugal", "Lisboa", "Hotel Lisboa Central", "351218288000", R.drawable.portugal),
        Destino("México", "Cidade do México", "Gran Hotel México", "525510837700", R.drawable.mexico),
        Destino("Grécia", "Atenas", "Hotel Grande Bretagne", "302103330000", R.drawable.grecia),
        Destino("Argentina", "Buenos Aires", "Alvear Palace Hotel", "541148082100", R.drawable.argentina)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDestinosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listViewDestinos.adapter = DestinoAdapter(this, destinos)

        setupListeners()
    }

    private fun setupListeners() {
        binding.listViewDestinos.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetalhesDestinoActivity::class.java)
            intent.putExtra("destino", destinos[position])
            startActivity(intent)
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}