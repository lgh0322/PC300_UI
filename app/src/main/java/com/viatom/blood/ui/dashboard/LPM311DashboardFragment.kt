package com.viatom.blood.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.bigkoo.convenientbanner.listener.OnPageChangeListener
import com.viatom.blood.R
import com.viatom.blood.databinding.Lpm311FragmentDashboardBinding
import com.viatom.blood.ui.dashboard.adapter.*

class LPM311DashboardFragment : Fragment() {

    private var _binding: Lpm311FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var dataAdapter: LPMRealTimeDataAdapter

    val textList= listOf<String>(
        "Prepare cotton swabs, alcohol wipes, blood collection needles, test strips, blood collection devices, Code cards, blood lipid detectors.",
        "First: Install batteries;\nSecond: Install CodeCard;\nThird: Long press the \"power on\" button;\nFourth: Insert test strip.",
        "First: Wipe the finger with an alcohol wipe;\nSecond: Wipe off residual alcohol;\nThird: Using a blood collection needle to collect blood from the side of the finger;\nFourth: When the amount of blood is too small, you can press the finger properly.",
        "First: Use blood collection device to collect blood;\nSecond: Drop the blood sample into the test strip at a constant rate;\nThird: Waiting for the test to be completed."
    )

    private fun initHomeImg() {

        val con: ConvenientBanner<BannerBean> = binding.banner as ConvenientBanner<BannerBean>

        val bannerList = listOf<BannerBean>(BannerBean(0), BannerBean(1), BannerBean(2), BannerBean(3))
        val mDotsLayout = binding.dotLayout
        con.setPages(
            object : CBViewHolderCreator {
                override fun createHolder(itemView: View): LPM311BannerHolder {
                    return LPM311BannerHolder(
                        itemView
                    )
                }

                override fun getLayoutId(): Int {
                    return R.layout.item_banner
                }
            }, bannerList
        )
            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
            .setOnPageChangeListener(object : OnPageChangeListener {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    if (newState == 0) {
                        for (k in 0 until 4) {
                            mDotsLayout.getChildAt(k)?.isSelected = k == con.currentItem
                        }
                        binding.sayText.text=textList[con.currentItem]

                    }
                }

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                }

                override fun onPageSelected(index: Int) {

                }

            })

        mDotsLayout.removeAllViews()
        for (k in bannerList.indices) {
            val params = ViewGroup.MarginLayoutParams(20, 20)
            params.leftMargin = 20
            params.rightMargin = 20
            mDotsLayout.addView(dotsItem(k), params)
        }
        mDotsLayout.getChildAt(0)?.isSelected = true


    }

    private fun dotsItem(position: Int): ImageView {
        val inflater =
            requireContext().getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.home_dot_image, null)
        val iv = layout.findViewById<View>(R.id.face_dot) as ImageView
        iv.id = position
        return iv
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val LPM311DashboardViewModel =
            ViewModelProvider(this).get(LPM311DashboardViewModel::class.java)

        _binding = Lpm311FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        LPM311DashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        LPM311DashboardViewModel.changeText("Divice is offline")



        dataAdapter= LPMRealTimeDataAdapter(requireContext())


        binding.leftView.layoutManager = object : GridLayoutManager(requireContext(), 2) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.leftView.adapter =dataAdapter
        binding.leftView.addItemDecoration(SpaceItemDecorationLPM(50))

        initHomeImg()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}