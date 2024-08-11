package com.example.task_show_icon

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.task_show_icon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainActivityNavigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            supportFragmentManager.popBackStack()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 1) {
                    supportFragmentManager.popBackStack()
                } else {
                    finish()
                }
            }
        })


        if (savedInstanceState == null) {
            onNavigate(FirstFragment(), true)
        }
    }

    override fun onNavigate(fragment: Fragment, isInitial: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        if (isInitial) {
            transaction.add(R.id.container_view, fragment)
        } else {
            transaction.replace(R.id.container_view, fragment)
        }
        transaction.addToBackStack(fragment.id.toString())
        transaction.setReorderingAllowed(true)
        transaction.commit()
    }
}