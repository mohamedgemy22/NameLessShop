package com.example.noname.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.noname.R
import com.example.noname.model.ResurantsModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class FoodFragment : Fragment(R.layout.fragment_food) {

    private val mainSlider : ImageSlider?=null
    private  lateinit var list: ArrayList<SlideModel>
    private val imageSliderCollection = Firebase.firestore.collection("resturant")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainSlider?.findViewById<ImageSlider>(R.id.image_slider);
        list= ArrayList<SlideModel>()

        showSliderFromFirebase()
        uploadDataToDataBase()
    }

    private fun showSliderFromFirebase() {
      imageSliderCollection.get()
          .addOnSuccessListener {result->
              for (document in result.documents ){
                  val image= document.toObject(SlideModel::class.java)
                  image?.let { list.add(it) }

              }
          }
         


    }
    private fun uploadDataToDataBase(){
       val saveResturat= SlideModel("https://bit.ly/2YoJ77H","quite lion")
          saveDataToCorotine(saveResturat)
    }

    private fun saveDataToCorotine(resturant: SlideModel)= CoroutineScope(Dispatchers.IO).launch {
        try {
          imageSliderCollection.add(resturant)
            withContext(Dispatchers.Main){
                Toast.makeText(context,"successfully  saved data", Toast.LENGTH_LONG).show()
                Log.e("gemmy","successfully")
            }
        }catch (e: Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(context,"failed to saved data", Toast.LENGTH_LONG).show()
                Log.e("gemmy","filed.."+e.message)
            }
        }
    }

}