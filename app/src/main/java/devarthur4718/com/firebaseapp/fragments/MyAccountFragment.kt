package devarthur4718.com.firebaseapp.fragments


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.auth.AuthUI

import devarthur4718.com.firebaseapp.R
import devarthur4718.com.firebaseapp.SignInActivity
import devarthur4718.com.firebaseapp.util.FireStoreUtil
import devarthur4718.com.firebaseapp.util.StorageUtil
import kotlinx.android.synthetic.main.fragment_my_account.*
import kotlinx.android.synthetic.main.fragment_my_account.view.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.newTask
import org.jetbrains.anko.support.v4.intentFor
import java.io.ByteArrayOutputStream


class MyAccountFragment : Fragment() {

    private val RC_SELECT_IMAGE = 2
    private lateinit var selectedImageBytes : ByteArray
    private var pictureJustChaged = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_account, container, false)

        view.apply {
           imageView_profile_picture.setOnClickListener {
               val intent = Intent().apply{
                   type = "image/*"
                   action = Intent.ACTION_GET_CONTENT
                   putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
               }
               startActivityForResult(Intent.createChooser(intent, "Select Image"), RC_SELECT_IMAGE)
           }

            btn_save.setOnClickListener {
                if(::selectedImageBytes.isInitialized){

                    StorageUtil.uploadProfilePhoto(selectedImageBytes){imagePath ->
                        FireStoreUtil.updateCurrentUser(editText_name.text.toString(), editText_bio.text.toString(), imagePath)
                    }
                }else{
                    FireStoreUtil.updateCurrentUser(editText_name.text.toString(), editText_bio.text.toString(), null)
                }
            }

            btn_sign_out.setOnClickListener {
                AuthUI.getInstance()
                    .signOut(this@MyAccountFragment.context!!)
                    .addOnCompleteListener {
                         startActivity(intentFor<SignInActivity>().newTask().clearTask())
                    }
            }
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        FireStoreUtil.getCurrentUser {user ->
            if(this@MyAccountFragment.isVisible){
                 editText_name.setText(user.name)
                 editText_bio.setText(user.bio)
                //Todo update image for the user later...

                // if(!pictureJustChaged && user.profilePath != null)


            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         if(requestCode == RC_SELECT_IMAGE && resultCode == Activity.RESULT_OK &&
                 data != null && data.data != null){
             val selectedImagePath = data.data
             val selectedImageBmp = MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectedImagePath)

             val outputStream = ByteArrayOutputStream()
             selectedImageBmp.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
             selectedImageBytes = outputStream.toByteArray()

             //Todo : Load Picture
             pictureJustChaged = true

         }
    }
}
