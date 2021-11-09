package com.famco.itk_19

import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class MainActivity : AppCompatActivity() {


    // Extra tag for log statement
    companion object {
        val TAG = "TAG"
    }

    private lateinit var web1: ImageView
    private lateinit var web2: ImageView
    private lateinit var web3: ImageView
    private lateinit var help: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** Getting reference for imageview **/
        web1 = findViewById(R.id.web1)
        web2 = findViewById(R.id.web2)
        web3 = findViewById(R.id.web3)
        help = findViewById(R.id.help)

        /** Setting clicklisener and starting new activity**/
        web1.setOnClickListener(View.OnClickListener { view ->
            if (view == web1) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse("http://itkorba.ac.in/"))
                startActivity(intent)
            }

        })
        web2.setOnClickListener(View.OnClickListener { view ->
            if (view == web2) {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        })


        web3.setOnClickListener(View.OnClickListener { view ->
            if (view == web3) {
                val intent = Intent(this, Registration::class.java)
                startActivity(intent)
            }
        })
        help.setOnClickListener(View.OnClickListener { view ->
            if (view == help) {
                Toast.makeText(this, "Soon Available", Toast.LENGTH_SHORT).show()
            }
        })
        // Viewpager function
        viewPagerImages()

        // Setting different viewImages


    }

    private fun viewPagerImages() {
        // Here's code are responsible for viewPager
        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.college1, "IT Korba"))
        imageList.add(
            SlideModel(
                "https://upload.wikimedia.org/wikipedia/commons/0/01/Itkorba.gif",
                "Bird Eye View of IT College Korba"
            )
        )
        imageList.add(
            SlideModel(
                "https://images.bhaskarassets.com/webp/thumb/540x0/web2images/521/2019/12/06/mpcg-ci1099900-large.jpg",
                "IT Korba"
            )
        )
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
        /*-- ViewPage code are ended */
    }

}