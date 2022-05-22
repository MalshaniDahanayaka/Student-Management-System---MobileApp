package com.kelaniya.android.myapplication.api

import com.kelaniya.android.myapplication.model.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {


    @POST("/api/v1/auth/login")
    fun loginUser(@Body user: JwtUserRequest?): Call<JwtResponse>?

    @GET("/api/v1/Courses/{courseID}")
    fun getCourseDetails(@Path("courseID") courseID: String): Call<Courses>?


    @GET("/api/v1/Courses/enrolledCourses")
    fun getEnrollCourseList(): Call<List<StudentsEnrollSubjects>>?

    @GET("/api/v1/Courses/all")
    fun getAllCourses(): Call<List<Courses>>?


    @GET("/api/v1/Courses/department_courses/")
    fun getDepartmentCourseList(): Call<List<Courses>>?

    @POST("/api/v1/student/enroll-subjects")
    fun enrollToCourse(@Body requestEnrollToCourse: EnrollToCourseRequest?): Call<EnrollToCourseRequest>?



    @GET("/api/enrolled_course_detailsRP/{courseID}/{academicYear}")
    fun getCourseLectureNotesRP(@Path("courseID") courseID:String, @Path("academicYear") academicYear:String): Call<List<LecNotes>>?

    @GET("/download_lecture_notes/{fileName}")
    fun downloadLectureFile(@Path("fileName") fileName: String): Call<LecNotes>?

    @GET("/api/student_marks_grades/")
    fun getStudentsMarksAndGrades():Call<List<StudentRecords>>?


    @GET("/api/student/Announcement/{courseID}/{academicYear}")
    fun getAnnouncements(@Path("courseID") courseID: String,@Path("academicYear") academicYear: String):Call<List<Announcement>>?

    @PATCH("/api/students/announcements/mark_as_read/{courseID}/{academicYear}")
    fun markAsNotificationsAreRead(@Path("courseID") courseID: String,@Path("academicYear") academicYear: String):Call<MarkAsNotificationAreReadResponse>?

    @DELETE("/api/v1/student/unroll-from-subjects/{courseID}/{academicYear}")
    fun unenrollFromCourse(@Path("courseID") courseID: String,@Path("academicYear") academicYear: String): Call<UnenrollFromCourseRequest>?

    @POST("api/v1/auth/signup")
    fun userSignup(@Body userSignupRequest: UserSignupRequest?): Call<UserSignupResponse>?

    @POST("api/v1/user/get-otp")
    fun getOTP(@Body otpRequest: OtpRequest?):Call<OtpResponse>?

    @GET("/api/student/getStudentDetails")
    fun getStudentDetails():Call<Student>?

    @POST("/api/v1/student/update-details")
    fun updateDetails(@Body student: Student?):Call<Student>?

    @GET("/lecturer/courses")
    fun getLecturerTeachingCourses():Call<List<Courses>>?

    //delete course module
    @HTTP(method = "DELETE", path = "/drop/course/", hasBody = true)
    fun removeCourseModule(@Body deleteCourseModule: DeleteCourseModule?):Call<DeleteCourseModule>?

    //delete lecturer note
    @DELETE("/delete/lecture_note/{fileName}")
    fun deleteLectureNote(@Path("fileName") fileName: String): Call<DeleteLectureNote>?

    @POST("/lectures/new_course/")
    fun addNewCourseModule(@Body course: Courses?):Call<Courses>?


    @HTTP(method = "DELETE", path = "/lec/delete_announcement/", hasBody = true)
    fun deleteAnnouncement(@Body deleteAnnouncementRequest: DeleteAnnouncementRequest?):Call<DeleteAnnouncementResponse>?



    @POST("/lec/create_announcement/")
    fun makeAnnouncement(@Body createNewAnnouncementRequest: CreateNewAnnouncementRequest?):Call<Announcement>?


    //show enroll students with marks
    @GET("/lecturer/course_marks_and_grades/{course_id}")
    fun getSelectedCourseStudentsMarksAndGrades(@Path("course_id") course_id: String):Call<List<EnrollStudentsMarksAndGradesResponse>>?



    //update marks and grades
    @PATCH("/update/marks_and_grades/")
    fun updateMarksAndGrades(@Body studentRecordsUpdateRequest: StudentRecordsUpdateRequest?):Call<StudentRecordsUpdateRequest>?

}




