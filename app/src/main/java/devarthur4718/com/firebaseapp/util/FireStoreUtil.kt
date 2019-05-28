package devarthur4718.com.firebaseapp.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

//https://www.youtube.com/watch?v=eHA9jGT_87Q
object FireStoreUtil {
     private val firestoreInstance : FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

     private val currentUserDocRef : DocumentReference
          get() = firestoreInstance.document("users/${FirebaseAuth.getInstance().uid
               ?: throw  NullPointerException("Uid is null")}")

     fun initCurrentUserIfFirstTime(){

     }

}