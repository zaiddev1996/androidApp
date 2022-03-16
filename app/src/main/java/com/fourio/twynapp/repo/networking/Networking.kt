package com.fourio.twynapp.repo.networking

import br.com.twyn.sdk.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


class Networking {

    companion object{
        private const val NETWORK_CALL_TIMEOUT = 60
        private var retrofit: Retrofit? = null
        private const val BASE_URL = "https://192.81.212.133:30010/api/"

        fun getRetrofitInstance(): ApiServices? {
            if (retrofit == null) {
                val gson = GsonBuilder()
                        .setLenient()
                        .serializeNulls()
                        .create()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(
                       unSafeOkHttpClient()
                            .addInterceptor(
                                HttpLoggingInterceptor()
                                    .apply {
                                        level = if (BuildConfig.DEBUG)
                                            HttpLoggingInterceptor.Level.BODY
                                        else
                                            HttpLoggingInterceptor.Level.NONE
                                    })
                            .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                            .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                            .build()
                    )
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit?.create(ApiServices::class.java)
        }
        fun unSafeOkHttpClient() :OkHttpClient.Builder {
            val okHttpClient = OkHttpClient.Builder()
            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts:  Array<TrustManager> = arrayOf(object : X509TrustManager {
                    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?){}
                    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                    override fun getAcceptedIssuers(): Array<X509Certificate>  = arrayOf()
                })

                // Install the all-trusting trust manager
                val  sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())

                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory
                if (trustAllCerts.isNotEmpty() &&  trustAllCerts.first() is X509TrustManager) {
                    okHttpClient.sslSocketFactory(sslSocketFactory, trustAllCerts.first() as X509TrustManager)
                    okHttpClient.hostnameVerifier { s: String, sslSession: SSLSession ->
                        true
                    }
                }

                return okHttpClient
            } catch (e: Exception) {
                return okHttpClient
            }
        }
    }



}