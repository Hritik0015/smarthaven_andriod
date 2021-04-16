package com.prakash.finalassignment.Adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prakash.finalassignment.R
import com.prakash.finalassignment.Repository.ProductRepository
import com.prakash.finalassignment.api.ServiceBuilder
import com.prakash.finalassignment.entity.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ProductAdapter(
        private val context: Context,
        private val lstStudents: MutableList<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val tvname: TextView = view.findViewById(R.id.cname)
        val tvqty: TextView = view.findViewById(R.id.cqty)
        val tvspecs: TextView = view.findViewById(R.id.cspecs)
        val btnUpdate: ImageButton = view.findViewById(R.id.btnUpdate)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
        val price:TextView=view.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_productview, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = lstStudents[position]
        holder.tvname.text = product.name
        holder.tvqty.text = product.qty
        holder.tvspecs.text = product.specification
//        holder.price.text=product.price

        val imagePath = ServiceBuilder.loadImagePath() + product.prdimage
        if (!product.prdimage.equals("no-photo.jpg")) {
            Glide.with(context).load(imagePath).fitCenter().into(holder.image)
        }

        holder.btnUpdate.setOnClickListener {
//            val intent = Intent(context, UpdateStudentActivity::class.java)
//            intent.putExtra("student",student)
//            context.startActivity(intent)
        }

        holder.btnDelete.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete student")
            builder.setMessage("Are you sure you want to delete ${product.name} ??")
            builder.setIcon(android.R.drawable.ic_delete)
            builder.setPositiveButton("Yes") { _, _ ->

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val studentRepository = ProductRepository()
                        val response = studentRepository.deleteStudents(product._id!!)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                        context,
                                        "Student Deleted",
                                        Toast.LENGTH_SHORT
                                ).show()
                            }
                            withContext(Main) {
                                lstStudents.remove(product)
                                notifyDataSetChanged()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                    context,
                                    ex.toString(),
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }
            builder.setNegativeButton("No") { _, _ ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return lstStudents.size
    }

//    private fun deleteStudent(id: String?) : Boolean {
//        var flag = false
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val studentRepository = StudentRepository()
//                val response = studentRepository.deleteStudents(id!!)
//                if(response.success == true){
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(
//                            context,
//                            "Student Deleted",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                    flag = true
//                }
//            } catch (ex: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(
//                        context,
//                        ex.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//        return flag
//    }
}

