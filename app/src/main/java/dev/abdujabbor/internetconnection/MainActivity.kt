package dev.abdujabbor.internetconnection

import CheckConnection
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.abdujabbor.internetconnection.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val checkConnection by lazy { CheckConnection(application) }
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            checkConnection.observe(this@MainActivity) {
                if (it) {
                    txt1.visibility = View.VISIBLE
                    txt2.visibility = View.GONE
                    img1.visibility = View.VISIBLE
                    img2.visibility = View.GONE
                }else{
                    img1.visibility = View.GONE
                    img2.visibility = View.VISIBLE
                    txt2.visibility = View.VISIBLE
                    txt1.visibility = View.GONE
                }
            }
        }

    }
}