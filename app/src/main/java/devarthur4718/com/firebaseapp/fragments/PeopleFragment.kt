package devarthur4718.com.firebaseapp.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.ListenerRegistration
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section

import com.xwray.groupie.kotlinandroidextensions.Item

import devarthur4718.com.firebaseapp.R
import devarthur4718.com.firebaseapp.util.FireStoreUtil
import kotlinx.android.synthetic.main.fragment_people.*
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class PeopleFragment : Fragment() {

    private lateinit var userListenerRegistration : ListenerRegistration

    private var shouldInitRecyclerView = true

    private lateinit var peopleSection : Section

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        userListenerRegistration = FireStoreUtil.addUserListener(this.activity!!, this::updateRecyclerView)

        return inflater.inflate(R.layout.fragment_people, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        FireStoreUtil.removeListener(userListenerRegistration)
        shouldInitRecyclerView = true
    }

    private fun updateRecyclerView(items : List<Item>){

        fun init(){
            recycler_view_people.apply {
                layoutManager = LinearLayoutManager(this@PeopleFragment.context)
                adapter = GroupAdapter<ViewHolder>().apply {
                     peopleSection = Section(items)
                     add(peopleSection)
                }
            }
            shouldInitRecyclerView = false
        }

        fun updateItems(){

        }

        if(shouldInitRecyclerView){
            init()
        }else{
           updateItems()
        }
    }


}
