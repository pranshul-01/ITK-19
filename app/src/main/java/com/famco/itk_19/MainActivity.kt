package com.famco.itk_19

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.famco.itk_19.utills.DEFAULT_ICONS
import com.famco.itk_19.utills.DataModel
import kotlin.math.min

class MainActivity : AppCompatActivity() {


    private lateinit var web1: ImageView
    private lateinit var web2: ImageView
    private lateinit var web3: ImageView
    private lateinit var help: ImageView

   private var dataList = mutableListOf<DataModel>()
   private lateinit var rvBoard: RecyclerView
   private lateinit var title: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rvBoard = findViewById(R.id.rvBoard)
       // title = findViewById(R.id.title)!!
        val chosenImages = DEFAULT_ICONS
        rvBoard.adapter = CollegeBoardAdapter(this,4,chosenImages)
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this,2)

        dataList.add(DataModel("College"))
        dataList.add(DataModel("College"))
        dataList.add(DataModel("College"))
        dataList.add(DataModel("College"))

        /** Getting reference for imageview **/
//        web1 = findViewById(R.id.web1)
//        web2 = findViewById(R.id.web2)
//        web3 = findViewById(R.id.web3)
//        help = findViewById(R.id.help)

        /** Setting clickListener and starting new activity**/
//        web1.setOnClickListener(View.OnClickListener { view ->
//            if (view == web1) {
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.setData(Uri.parse("http://itkorba.ac.in/"))
//                startActivity(intent)
//            }
//
//        })
//        web2.setOnClickListener(View.OnClickListener { view ->
//            if (view == web2) {
//                val intent = Intent(this, Login::class.java)
//                startActivity(intent)
//            }
//        })
//
//
//        web3.setOnClickListener(View.OnClickListener { view ->
//            if (view == web3) {
//                val intent = Intent(this, Registration::class.java)
//                startActivity(intent)
//            }
//        })
//        help.setOnClickListener(View.OnClickListener { view ->
//            if (view == help) {
//                Toast.makeText(this, "Soon Available", Toast.LENGTH_SHORT).show()
//            }
//        })
        // Viewpager function
        viewPagerImages()
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

/**
 * Creating a class for recycler view function
 */

class CollegeBoardAdapter(

    private val context: Context,
    private val numPieces: Int,
    private val cardImages: List<Int>,
   // title: TextView,
   // dataList: MutableList<DataModel>,
    ) :
    RecyclerView.Adapter<CollegeBoardAdapter.ViewHolder>() {
    var dataList = emptyList<DataModel>()

//    internal fun setDataList(dataList: List<DataModel>){
//        this.dataList = dataList
//    }
    companion object{
        private const val MARGIN_SIZE =10
        private const val TAG = "CollegeBoardAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val cardWidth = parent.width/2 -(2  * MARGIN_SIZE)
        val cardHeight = parent.height/2 - (2 * MARGIN_SIZE)
        val cardSideLength = min(cardWidth,cardHeight)
        val view =  LayoutInflater.from(context).inflate(R.layout.college_card,parent,false)
        val layoutParams = view.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width = cardSideLength
        layoutParams.height = cardSideLength
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = numPieces


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       // var data = dataList[position]
        private val cc_imageButton = itemView.findViewById<ImageView>(R.id.cc_imageButton)
       // private val title = itemView.findViewById<TextView>(R.id.title)
        fun bind(position: Int) {

       //     title.text = data.title
            cc_imageButton.setImageResource(cardImages[position])
            cc_imageButton.setOnClickListener {
                Log.i(TAG,"clicked on position $position")
            }
        }
    }
}

