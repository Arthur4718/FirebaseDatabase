package devarthur4718.com.firebaseapp.model



//* Created by Arthur Gomes at 2019-05-28 01:02 - contact me at devarthur4718@gmail.com.br
data class User (val name : String, val bio : String, val profilePath : String?){

    constructor() : this("", "", null)
}