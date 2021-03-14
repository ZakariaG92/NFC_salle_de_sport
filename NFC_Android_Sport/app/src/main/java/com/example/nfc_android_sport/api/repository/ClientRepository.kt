import com.example.nfc_android_sport.model.Client
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientRepository {



    constructor(){

    }


    private val service: ClientService
    init {



        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder().apply {
            baseUrl("http://801711caf02e.ngrok.io/api/")
//           client(client)
            addConverterFactory(GsonConverterFactory.create())
        }.build()

        service = retrofit.create(ClientService::class.java)

    }




    fun getClients(): List<Client>? {
        val response = service.listClients("client/id/2").execute();
        var a = response.body()
        return response.body() ?: null
    }



}


