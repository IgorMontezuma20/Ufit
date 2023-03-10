package com.app.ufit.ui.fragments.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.app.ufit.R
import com.app.ufit.adapters.OnBoardingItemsAdapter
import com.app.ufit.databinding.FragmentOnboardingBinding
import com.app.ufit.models.OnBoardingItem

class OnBoardingrFragment : Fragment() {


    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var onBoardingItemsAdapter: OnBoardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)

        binding.btnGetStarted.setOnClickListener {
            //findNavController().navigate(R.id.)
        }

        //(activity as AppCompatActivity).supportActionBar?.hide()

        setOnBoardingItems()
        setupIndicator()
        setCurrentIndicator(0)

        binding.btnGetStarted.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment)
        }

        return binding.root
    }


    private fun setOnBoardingItems() {
        onBoardingItemsAdapter = OnBoardingItemsAdapter(

            listOf(

                OnBoardingItem(
                    onBoardingImage = R.drawable.task,
                    title = "Administre suas tarefas",
                    description = "Administre melhor seus objetivos"
                ),

                OnBoardingItem(
                    onBoardingImage = R.drawable.time,
                    title = "Alcance seus objetivos",
                    description = "Seus objetivos também são os nossos!"
                ),


                OnBoardingItem(
                    onBoardingImage = R.drawable.reminder,
                    title = "Mantenha o foco",
                    description = "Com a gente você sempre irá manter seu foco!"
                )

            )
        )

        val onBoardingViewPager = binding.onBoardingViewPager
        onBoardingViewPager.adapter = onBoardingItemsAdapter
        onBoardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onBoardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

//        view?.findViewById<ImageView>(R.id.ivNext)?.setOnClickListener {
//            if (onBoardingViewPager?.currentItem!! + 1 < onBoardingItemsAdapter.itemCount) {
//                onBoardingViewPager.currentItem = onBoardingViewPager.currentItem + 1
//
//            } else {
//                navigateToHomeActivity()
//            }
//        }
//        view?.findViewById<TextView>(R.id.tvSkip)?.setOnClickListener {
//            navigateToHomeActivity()
//        }
//        view?.findViewById<MaterialButton>(R.id.btnGetStarted)?.setOnClickListener {
//            navigateToHomeActivity()
//        }
    }

//    private fun navigateToHomeActivity() {
//        startActivity(Intent(applicationContext, HomeActivity::class.java))
//        finish()
//    }


    private fun setupIndicator() {
        indicatorsContainer = binding.indicatorsContainer
        val indicators = arrayOfNulls<ImageView>(onBoardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(requireContext())
            indicators[i]?.let {
                it.setImageDrawable(

                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive_background
                    )

                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }

    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_active_background
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }


}