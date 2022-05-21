package com.kelaniya.android.myapplication.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.model.EnrollStudentsMarksAndGradesResponse
import com.kelaniya.android.myapplication.model.StudentRecords
import com.kelaniya.android.myapplication.model.StudentRecordsUpdateRequest
import com.kelaniya.android.myapplication.model.UserSignupResponse
import com.kelaniya.android.myapplication.utils.GetUserDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class EnrollStudentsMarkAndGradesViewAdapter(private val marksAndGrades: List<EnrollStudentsMarksAndGradesResponse>, private val findNavController: NavController): RecyclerView.Adapter<CustomViewHolderEnrollStudentsMarks>(){



    //number of Items
    override fun getItemCount(): Int {
        return marksAndGrades.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderEnrollStudentsMarks {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.enroll_students_marks_and_grades_view,parent,false)
        return CustomViewHolderEnrollStudentsMarks(cellForRow,findNavController)
    }

    override fun onBindViewHolder(holder: CustomViewHolderEnrollStudentsMarks, position: Int) {
        holder.courseID.text = marksAndGrades.elementAt(position).course_id
        holder.studentID.text  = "  " + marksAndGrades.elementAt(position).student_id
        holder.marks.text = marksAndGrades.elementAt(position).score.toString()
        holder.grades.text = marksAndGrades.elementAt(position).grade
        holder.studentEmail.text = marksAndGrades.elementAt(position).student_email



    }
}

class CustomViewHolderEnrollStudentsMarks(val view: View, private val findNavController: NavController): RecyclerView.ViewHolder(view){
    val courseID: TextView =    itemView.findViewById(R.id.enroll_student_marks_course_id)
    val studentID:TextView = itemView.findViewById(R.id.enroll_student_marks_student_id)
    val marks: TextView = itemView.findViewById(R.id.enroll_student_marks_Marks)
    val grades: TextView = itemView.findViewById(R.id.enroll_student_marks_grades)
    val studentEmail:TextView = itemView.findViewById(R.id.enroll_student_marks_student_email)



    init {
        view.setOnClickListener{

            val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(view.context)
            builder.setTitle("Add Marks")

// Set up the input
            val input = EditText(view.context)
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setHint("Enter Student Marks")
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

// Set up the buttons
            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                // Here you get get input text from the Edittext
                var input = input.text.toString()
                var marksValue = input.toDouble()
                if(marksValue>0.0 && marksValue<100.0){

                    var gradeValue:String = "No"

                    if(marksValue < 40){
                        gradeValue = "D"
                    }else if(marksValue<50){
                        gradeValue = "C-"
                    }else if(marksValue<50){
                        gradeValue = "C"
                    }else if(marksValue<55){
                        gradeValue = "C+"
                    }else if(marksValue<60){
                        gradeValue = "B"
                    }else if(marksValue<65){
                        gradeValue = "B+"
                    }else if(marksValue<70){
                        gradeValue = "A-"
                    }else if(marksValue<75){
                        gradeValue = "A"
                    }else if(marksValue<100){
                        gradeValue = "A+"
                    }

                    val studentRecordsUpdateRequest: StudentRecordsUpdateRequest = StudentRecordsUpdateRequest(
                        studentEmail.text.toString(),courseID.text.toString(),
                        marksValue,gradeValue)


                    val retrofit = RetrofitBuilder()
                    var retrofitData = retrofit.retrofitBuilder.updateMarksAndGrades(studentRecordsUpdateRequest)
                    retrofitData!!.enqueue(object : Callback<StudentRecordsUpdateRequest> {
                        override fun onResponse(call: Call<StudentRecordsUpdateRequest>, response: Response<StudentRecordsUpdateRequest>) {
                            val responseValues = response.body()
                            try{
                                if(responseValues!!.student_email != null){
                                    Toast.makeText(view.context,"Successfully updated", Toast.LENGTH_SHORT).show()

                                }
                            }catch (e: Exception){
                                Log.i("error","empty results")
                            }
                        }
                        override fun onFailure(call: Call<StudentRecordsUpdateRequest>, t: Throwable) {
                            Log.i("FirstFragment", t.message!!)
                        }
                    })

                }
                else{
                    Toast.makeText(view.context,"Insert Valid Value", Toast.LENGTH_SHORT).show()
                }
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

            builder.show()



        }


    }

}