package com.itsyasirali.ricediseasedetection.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.itsyasirali.ricediseasedetection.R
import com.itsyasirali.ricediseasedetection.models.DiseaseModel
import com.itsyasirali.ricediseasedetection.util.ImageClassifier
import com.itsyasirali.ricediseasedetection.util.SharedPrefManager
import com.itsyasirali.ricediseasedetection.viewmodel.DiseaseViewModel
import com.itsyasirali.ricediseasedetection.viewmodel.UserViewModel

class ActivityCheckRice : BaseActivity() {
    private lateinit var imageClassifier: ImageClassifier
    private lateinit var imageView: ImageView
    private lateinit var resultTextView: TextView
    private val SELECT_IMAGE_REQUEST = 1

    private val diseaseViewModel: DiseaseViewModel by viewModels()
    private lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_rice)

        sharedPrefManager = SharedPrefManager(this)

        imageClassifier = ImageClassifier(this)
        imageView = findViewById(R.id.imageView)
        resultTextView = findViewById(R.id.resultTextView)

        val selectImageButton: Button = findViewById(R.id.selectImageButton)
        selectImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, SELECT_IMAGE_REQUEST)
        }

        diseaseViewModel.saveStatus.observe(this) { status ->
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        }

        diseaseViewModel.error.observe(this) { error ->
            Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val bitmap = uriToBitmap(uri)
                imageView.setImageBitmap(bitmap)
                val result = imageClassifier.classifyImage(bitmap)
                resultTextView.text = result

                val diseases = DiseaseModel(
                    userId = sharedPrefManager.getUser()!!.uid,
                    name = result
                )
                diseaseViewModel.addDisease(diseases)
            }
        }
    }

    private fun uriToBitmap(uri: Uri): Bitmap {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(contentResolver, uri)
            ImageDecoder.decodeBitmap(source) { decoder, _, _ ->
                decoder.setAllocator(ImageDecoder.ALLOCATOR_SOFTWARE)
            }
        } else {
            MediaStore.Images.Media.getBitmap(contentResolver, uri)
        }
    }

}