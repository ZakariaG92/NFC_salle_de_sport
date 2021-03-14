import com.example.nfc_android_sport.model.Client
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName
import retrofit2.http.Url

interface ClientService {

    @GET()
    fun listClients(@Url url: String): Call<List<Client>>

}