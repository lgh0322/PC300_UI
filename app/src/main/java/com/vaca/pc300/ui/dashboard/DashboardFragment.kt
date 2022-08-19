package com.vaca.pc300.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.bigkoo.convenientbanner.listener.OnPageChangeListener
import com.vaca.pc300.R
import com.vaca.pc300.databinding.FragmentDashboardBinding
import com.vaca.pc300.ui.dashboard.adapter.*

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val textList= listOf<String>(
        "Prepare cotton swabs, alcohol wipes, blood collection needles, test strips, blood glucose detectors.",
        "First: Install batteries;\nSecond: Insert test strip.",
        "First: Wipe the finger with an alcohol wipe;\nSecond: Wipe off residual alcohol;\nThird: Using a blood collection needle to collect blood from the side of the finger;\nFourth: When the amount of blood is too small, you can press the finger properly.",
        "First: Touch the edge of the test strip to the drop of blood;\nSecond: After the test is completed, the measured value is displayed."
    )

    private fun initHomeImg() {

        val con: ConvenientBanner<BannerBean> = binding.banner as ConvenientBanner<BannerBean>

        val bannerList = listOf<BannerBean>(BannerBean(0), BannerBean(1), BannerBean(2), BannerBean(3))
        val mDotsLayout = binding.dotLayout
        con.setPages(
            object : CBViewHolderCreator {
                override fun createHolder(itemView: View): BannerHolder {
                    return BannerHolder(itemView)
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
        for (k in 0 until bannerList.size) {
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
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        dashboardViewModel.changeText("Divice is offline")



        initHomeImg()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}