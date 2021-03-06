package devarthur4718.com.firebaseapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import devarthur4718.com.firebaseapp.fragments.MyAccountFragment
import devarthur4718.com.firebaseapp.fragments.PeopleFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        replaceFragment(PeopleFragment())

        nav_view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_account ->{
                    replaceFragment(MyAccountFragment() )
                    true
                }
                R.id.navigation_people ->{
                    replaceFragment(PeopleFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_layout, fragment)
            commit()
        }

    }
}
