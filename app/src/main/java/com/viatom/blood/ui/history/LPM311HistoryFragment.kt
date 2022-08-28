package com.viatom.blood.ui.history

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.viatom.blood.BleServer.dataScope
import com.viatom.blood.MainApplication
import com.viatom.blood.NetCmd
import com.viatom.blood.ui.history.detail.LPM311HistoryDetailActivity
import com.viatom.blood.databinding.Lpm311FragmentHistoryBinding
import com.viatom.blood.room.LPM311AppDatabase
import com.viatom.blood.room.LPM311Data
import com.viatom.blood.ui.history.adapter.LPM311HistoryAdapter
import com.viatom.blood.ui.history.adapter.PoctorTopAdapter
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class LPM311HistoryFragment : Fragment(){

    private var _binding: Lpm311FragmentHistoryBinding? = null
    companion object {
        val currentSelect=MutableLiveData<LPM311Data>()
    }

    val dataList=MutableLiveData<List<LPM311Data>>()

    private lateinit var topAdapter: PoctorTopAdapter
    private val binding get() = _binding!!
    var currentIndex = 0
    lateinit var navController: NavController
    private lateinit var leftAdapter: LPM311HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = Lpm311FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        leftAdapter=LPM311HistoryAdapter(requireContext())

        topAdapter = PoctorTopAdapter(requireContext())


        topAdapter.click=object :PoctorTopAdapter.Click{
            override fun clickItem(position: Int) {
                Log.e("gaf",position.toString())

            }
        }

        binding.topView.layoutManager =
            object : LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false) {
                override fun canScrollHorizontally(): Boolean {
                    return false
                }
            }
        binding.topView.adapter = topAdapter


        binding.leftView.layoutManager = object :  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
        binding.leftView.adapter =leftAdapter
        leftAdapter.click=object:LPM311HistoryAdapter.Click{
            override fun clickItem(position: Int) {
                currentSelect.postValue(leftAdapter.mData[position])
                startActivity(Intent(requireActivity(), LPM311HistoryDetailActivity::class.java))
            }
        }



        dataList.observe(viewLifecycleOwner){
            leftAdapter.addAll(it)
        }

        binding.refresh.setOnRefreshListener {
            dataScope.launch {

                val data=NetCmd.getInfo();
                try {
                    val a=JSONArray(String(data!!))
                    val len=a.length()
                    for(k in 0 until len){
                        val b=a.getJSONObject(k)
                        val name=b.getString("name")
                        val size=b.getInt("size")
                        Log.e("gaga",name+"   "+size)
                        LPM311AppDatabase.saveLPM(name,size,System.currentTimeMillis());
                        delay(20);
                    }
                    dataList.postValue(LPM311AppDatabase.lpmDb.lpmDao().getAllR())

                }catch (e:Exception){

                }
                withContext(Dispatchers.Main){
                    delay(1000)
                    binding.refresh.isRefreshing=false
                }


            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}