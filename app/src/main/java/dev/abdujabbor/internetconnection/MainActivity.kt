package dev.abdujabbor.internetconnection

import CheckConnection
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.abdujabbor.internetconnection.adapters.RvAdapter
import dev.abdujabbor.internetconnection.adapters.RvClick
import dev.abdujabbor.internetconnection.databinding.ActivityMainBinding
import dev.abdujabbor.internetconnection.models.MyCurrency
import dev.abdujabbor.internetconnection.ui.CalculateActivity
import dev.abdujabbor.internetconnection.utils.MyData
import org.json.JSONArray

class MainActivity : AppCompatActivity(), RvClick {
    lateinit var adapter: RvAdapter
    var uri = "http://cbu.uz/uzc/arkhiv-kursov-valyut/json/"
    lateinit var requestQueue: RequestQueue
    private val checkConnection by lazy { CheckConnection(application) }
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            checkConnection.observe(this@MainActivity) {
                if (it) {
                    progressBar.visibility = View.GONE
                    tvLoad.visibility = View.GONE
                    getALlCurrency()
                    binding.recyclerview.visibility = View.VISIBLE

                } else {
                    progressBar.visibility = View.VISIBLE
                    tvLoad.visibility = View.VISIBLE
                    binding.recyclerview.visibility = View.GONE

                }
            }
        }

    }

    fun getALlCurrency() {
        requestQueue = Volley.newRequestQueue(this)
        VolleyLog.DEBUG = true
        val requesJsonArrayRequest = JsonArrayRequest(Request.Method.GET, uri, null,
            { response ->
                val type = object : TypeToken<List<MyCurrency>>() {}.type
                val list = Gson().fromJson<List<MyCurrency>>(response.toString(), type)
                adapter = RvAdapter(list,this)
                MyData.listim.addAll(list)
                if (list.isEmpty()){
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvLoad.visibility = View.VISIBLE
                }
                binding.recyclerview.adapter = adapter
            }

        ) {

        }

        requestQueue.add(requesJsonArrayRequest)
    }

    override fun click(moview: MyCurrency, position: Int) {
       startActivity(Intent(this,CalculateActivity::class.java))
        intent.putExtra("position",position)
        MyData.position = position
        intent.putExtra("listim",moview)
    }


}

