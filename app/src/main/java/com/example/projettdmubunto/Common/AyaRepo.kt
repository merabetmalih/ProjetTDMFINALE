import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projettdmubunto.Interface.RetrofitService
import com.example.shakil.kotlinjsonexample.Retrofit.RetrofitClient
import com.example.projettdmubunto.MainActivity
import com.example.td4_exo4.Response.AyahDetail
import com.example.tdm_exo8_s5.Model.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object AyaRepo {
    private val BASE_URL = "http://api.alquran.cloud"

    val retrofitService: RetrofitService
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)// create method for the interface that we created
}