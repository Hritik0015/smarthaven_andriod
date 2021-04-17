package com.prakash.finalassignment.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.prakash.finalassignment.R
import com.prakash.finalassignment.Repository.ProductRepository
import com.prakash.finalassignment.entity.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AddProductActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var name: TextInputEditText
    private lateinit var specs: TextInputEditText
    private lateinit var qty: TextInputEditText
    private lateinit var btnSave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        imageView = findViewById(R.id.imageView)
        name = findViewById(R.id.name)
        specs = findViewById(R.id.etspecs)
        qty=findViewById(R.id.etqty)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            Toast.makeText(this@AddProductActivity, "Uploaded", Toast.LENGTH_LONG)
                    .show()
            saveStudent()
        }

        imageView.setOnClickListener {
            loadPopUpMenu()
        }


    }
    private fun saveStudent() {
        if (checkEmptyValues()) {
                val name = name.text.toString()
                val qty = qty.text.toString()
                val specs = specs.text.toString()


                val product =
                        Product(name = name, qty = qty, specification = specs)

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val studentRepository = ProductRepository()
                        val response = studentRepository.insertProduct(product)

                        if (response.success == true) {
                            if(imageUrl != null){
                                uploadImage(response.data!!._id!!)
                            }
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                        this@AddProductActivity,
                                        "Student Added",
                                        Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                    this@AddProductActivity,
                                    ex.toString(),
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

private fun uploadImage(studentId: String) {
    if (imageUrl != null) {
        val file = File(imageUrl!!)
        val reqFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body =
                MultipartBody.Part.createFormData("file", file.name, reqFile)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository = ProductRepository()
                val response = productRepository.uploadImage(studentId, body)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AddProductActivity, "Uploaded", Toast.LENGTH_LONG)
                                .show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d("Mero Error ", ex.localizedMessage)
                    Toast.makeText(
                            this@AddProductActivity,
                            ex.localizedMessage,
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
private fun loadPopUpMenu() {
    val popupMenu = PopupMenu(this@AddProductActivity, imageView)
    popupMenu.menuInflater.inflate(R.menu.gallery_camera, popupMenu.menu)
    popupMenu.setOnMenuItemClickListener { item ->
        when (item.itemId) {
            R.id.menuCamera ->
                openCamera()
            R.id.menuGallery ->
                openGallery()
        }
        true
    }
    popupMenu.show()
}

private var REQUEST_GALLERY_CODE = 0
private var REQUEST_CAMERA_CODE = 1
private var imageUrl: String? = null

private fun openGallery() {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*"
    startActivityForResult(intent, REQUEST_GALLERY_CODE)
}

private fun openCamera() {
    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
}

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (resultCode == Activity.RESULT_OK) {
        if (requestCode == REQUEST_GALLERY_CODE && data != null) {
            val selectedImage = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val contentResolver = contentResolver
            val cursor =
                    contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
            cursor!!.moveToFirst()
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            imageUrl = cursor.getString(columnIndex)
            imageView.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
            cursor.close()
        } else if (requestCode == REQUEST_CAMERA_CODE && data != null) {
            val imageBitmap = data.extras?.get("data") as Bitmap
            val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
            imageUrl = file!!.absolutePath
            imageView.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
        }
    }
}

private fun bitmapToFile(
        bitmap: Bitmap,
        fileNameToSave: String
): File? {
    var file: File? = null
    return try {
        file = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                        .toString() + File.separator + fileNameToSave
        )
        file.createNewFile()
        //Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
        val bitMapData = bos.toByteArray()
        //write the bytes in file
        val fos = FileOutputStream(file)
        fos.write(bitMapData)
        fos.flush()
        fos.close()
        file
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        file // it will return null
    }
}


    private fun checkEmptyValues(): Boolean {
        var flag = true
        when {
            TextUtils.isEmpty(name.text) -> {
                name.error = "Please enter name"
                name.requestFocus()
                flag = false
            }

            TextUtils.isEmpty(specs.text) -> {
                specs.error = "Please enter specifications"
                specs.requestFocus()
                flag = false
            }

            TextUtils.isEmpty(qty.text) -> {
                qty.error = "Please enter quantity"
                qty.requestFocus()
                flag = false
            }
        }
        return flag
    }
}
