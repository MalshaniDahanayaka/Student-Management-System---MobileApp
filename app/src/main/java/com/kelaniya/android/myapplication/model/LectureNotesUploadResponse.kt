package com.kelaniya.android.myapplication.model

data class LectureNotesUploadResponse(    val subjectName:String,
                                          val description:String,
                                          val academic_year:String,
                                          val file_name:String,
                                          val download_url:String,
                                          val file_size:String,
                                          val date:String
                                          )
