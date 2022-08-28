package com.viatom.blood.ui.history

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.viatom.blood.BleServer.dataScope
import com.viatom.blood.net.NetCmd
import com.viatom.blood.ui.history.detail.LPM311HistoryDetailActivity
import com.viatom.blood.databinding.Lpm311FragmentHistoryBinding
import com.viatom.blood.room.LPM311AppDatabase
import com.viatom.blood.room.LPM311Data
import com.viatom.blood.ui.history.adapter.LPM311HistoryAdapter
import com.viatom.blood.ui.history.adapter.PoctorTopAdapter
import com.viatom.blood.utils.PathUtil
import kotlinx.coroutines.*
import org.json.JSONArray
import java.io.File
import java.lang.Exception

class LPM311HistoryFragment : Fragment(),NetCmd.OnDownloadListener{

    private var _binding: Lpm311FragmentHistoryBinding? = null
    companion object {
        val currentSelect=MutableLiveData<LPM311Data>()
    }

    val dataList=MutableLiveData<List<LPM311Data>>()

    val updateItem=MutableLiveData<Int>()

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
                val item=leftAdapter.mData[position]
                currentSelect.postValue(item)
                val file= File(PathUtil.getPathX(item.name))
                if(file.exists()){
                    startActivity(Intent(requireActivity(), LPM311HistoryDetailActivity::class.java))
                }else {
                    dataScope.launch {
                        NetCmd.getFile(NetCmd.getFileUrl(item.name),item.name,this@LPM311HistoryFragment,item.size,position)
                    }
                }

            }
        }

        dataScope.launch {
            dataList.postValue(LPM311AppDatabase.lpmDb.lpmDao().getAllR())
        }


        dataList.observe(viewLifecycleOwner){
            leftAdapter.addAll(it)
        }

        binding.refresh.setOnRefreshListener {
            dataScope.launch {

                try {
                    val data= NetCmd.getInfo();
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

        updateItem.observe(viewLifecycleOwner){
            leftAdapter.notifyItemChanged(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDownloadStart() {

    }

    override fun onDownloadSuccess(index:Int) {
        updateItem.postValue(index)
    }

    override fun onDownloadSuccessBytes(byteArray: ByteArray?) {

    }

    override fun onDownloading(progress: Int) {
        Log.e("gaga",progress.toString())
    }

    override fun onDownloadFailed() {

    }


}