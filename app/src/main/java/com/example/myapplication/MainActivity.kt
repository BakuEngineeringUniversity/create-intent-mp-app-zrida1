package com.example.myapplication

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val phone = binding.phone.text.toString()
        binding.phone.setOnClickListener(){
            intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${phone}"))
            startActivity(intent)
        }

        val email = binding.email.text.toString()
        binding.email.setOnClickListener(){
            intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "${email}", null))
            startActivity(Intent.createChooser(intent, "Send mail.."))
        }

        binding.location.setOnClickListener(){
            intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("geo:35.9078, 127.7669"))
            startActivity(intent)
        }

        val mp = MediaPlayer.create(this, R.raw.krokodila)
        binding.play.setOnClickListener(){

            if(mp.isPlaying) mp.stop()
            else mp.start()
        }

    }
}
