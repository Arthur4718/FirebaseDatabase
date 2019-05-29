package devarthur4718.com.firebaseapp.recyclerviewitem

import android.content.Context
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import devarthur4718.com.firebaseapp.R
import devarthur4718.com.firebaseapp.model.User
import kotlinx.android.synthetic.main.item_person.*


class PersonItem (val person : User,
                  val userId : String,
                  private val context : Context)
    : Item(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView_name.text = person.name
        viewHolder.textView_bio.text = person.bio

//        if(person.profilePath!= null){
//
//        }
    }

    override fun getLayout() = R.layout.item_person
}