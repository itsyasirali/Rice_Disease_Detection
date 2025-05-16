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
import com.itsyasirali.ricediseasedetection.R
import com.itsyasirali.ricediseasedetection.util.ImageClassifier

class ActivityCheckRice : BaseActivity() {
    private lateinit var imageClassifier: ImageClassifier
    private lateinit var imageView: ImageView
    private lateinit var resultTextView: TextView
    private val SELECT_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_rice)

        imageClassifier = ImageClassifier(this)
        imageView = findViewById(R.id.imageView)
        resultTextView = findViewById(R.id.resultTextView)

        val selectImageButton: Button = findViewById(R.id.selectImageButton)
        selectImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, SELECT_IMAGE_REQUEST)
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