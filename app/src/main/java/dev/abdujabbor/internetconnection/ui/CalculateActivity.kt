package dev.abdujabbor.internetconnection.ui

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import dev.abdujabbor.internetconnection.databinding.ActivityCalculateBinding
import dev.abdujabbor.internetconnection.databinding.ActivityMainBinding
import dev.abdujabbor.internetconnection.models.MyCurrency
import dev.abdujabbor.internetconnection.utils.MyData

class CalculateActivity : AppCompatActivity() {

    val binding by lazy { ActivityCalculateBinding.inflate(layoutInflater) }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnConvert.setOnClickListener {

        }

       val position =  intent.getIntExtra("position",0)
        val array = MyData.listim[MyData.position]


        binding.textdol.text = array.CcyNm_EN  + "  to  " +"SO'M"
        binding.doltext.text =  "SO'M "+ "  to  " +array.CcyNm_EN


        binding.etFrom.addTextChangedListener {
            if(binding.etFrom.text?.length==0){
                binding.etFrom.setText("1")
            }
            binding.tvResult.text =binding.etFrom.text.toString()+" SO'M ="+((binding.etFrom.text.toString().toDouble()/array.Rate.toDouble())).toString() +"  "+ array.Ccy
            binding.tvResult2.text =binding.etFrom.text.toString()+array.Ccy+ " = "+ ((array.Rate.toDouble())*binding.etFrom.text.toString().toDouble()).toString() + "  SO'M"
        }

        binding.copy.setOnClickListener {
            copyToClipboard(binding.tvResult.text.toString())
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
        }
        binding.copy2.setOnClickListener {
            copyToClipboard(binding.tvResult2   .text.toString())
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
        }

    }

    fun Context.copyToClipboard(text: CharSequence){
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label",text)
        clipboard.setPrimaryClip(clip)
    }
}