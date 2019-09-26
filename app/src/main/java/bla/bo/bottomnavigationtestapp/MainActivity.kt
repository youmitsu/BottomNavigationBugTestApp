package bla.bo.bottomnavigationtestapp

import android.os.Bundle
import android.transition.AutoTransition
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import bla.bo.bottomnavigationtestapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: ActivityMainBinding

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                binding.message.text = getString(R.string.home)
                return true
            }
            R.id.nav_notification -> {
                binding.message.text = getString(R.string.notification)
                return true
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.bottomNav.setOnNavigationItemSelectedListener(this)
        binding.bottomNav.fixBlinking()
    }

    private fun BottomNavigationView.fixBlinking() {
        val menuView = getChildAt(0) as BottomNavigationMenuView
        with(menuView::class.java.getDeclaredField("set")) {
            isAccessible = true
            set(menuView, androidx.transition.AutoTransition().apply {
                duration = 0L
            })
        }
    }
}
