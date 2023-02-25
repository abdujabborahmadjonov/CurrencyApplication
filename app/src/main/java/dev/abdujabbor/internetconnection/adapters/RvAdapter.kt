package dev.abdujabbor.internetconnection.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.abdujabbor.internetconnection.databinding.RvItemBinding
import dev.abdujabbor.internetconnection.models.MyCurrency


class RvAdapter(var list: List<MyCurrency>,var rvClick: RvClick) :
    RecyclerView.Adapter<RvAdapter.VH>() {
    inner class VH(var itemViewBinding: RvItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(list: MyCurrency, position: Int) {
            itemViewBinding.ratetext.text = list.Rate
            itemViewBinding.textView.text = list.CcyNm_EN
            itemViewBinding.chaning.text = list.Diff

            itemViewBinding.calculate.setOnClickListener {
                rvClick.click(list, position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        return VH(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface RvClick {
    fun click(moview: MyCurrency, position: Int)
}