package com.example.homework_tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import com.example.homework_tablayout.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val images = listOf(
        R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
        R.drawable.image_4, R.drawable.image_5, R.drawable.image_6,
        R.drawable.image_7, R.drawable.image_8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        val adapter = ImagePagerAdapter(this, images)
        binding.viewPager.adapter = adapter
        binding.viewPager.setPageTransformer(CustomPageTransformer())
    }

    private fun setupTabLayout() {
        val tabLayout = binding.tabLayout

        TabLayoutMediator(tabLayout, binding.viewPager) { tab, position ->
            val imageView = createTabImageView(position)
            tab.customView = imageView
        }.attach()

        addTabSelectionListener(tabLayout)
        setFirstTabFullyVisible(tabLayout)
    }

    private fun createTabImageView(position: Int): ImageView {
        val imageView =
            LayoutInflater.from(this).inflate(R.layout.tab_custom_layout, null) as ImageView
        imageView.setImageResource(images[position])
        return imageView
    }

    private fun addTabSelectionListener(tabLayout: TabLayout) {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.customView?.let { it.alpha = 1.0f }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.customView?.let { it.alpha = 0.2f }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setFirstTabFullyVisible(tabLayout: TabLayout) {
        val firstTab: TabLayout.Tab? = tabLayout.getTabAt(0)
        firstTab?.customView?.let { it.alpha = 1.0f }
    }
}
