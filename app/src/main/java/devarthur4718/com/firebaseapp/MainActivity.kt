package devarthur4718.com.firebaseapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val navView: BottomNavigationView = findViewById(R.id.nav_view)

        nav_view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_account ->{

                    true
                }
                R.id.navigation_people ->{

                    true
                }

                else -> false
            }
        }
    }
}
