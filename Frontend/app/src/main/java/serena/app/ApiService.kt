package serena.app

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class User(val email: String, val password: String)
data class PersonalDetails(val userId: String, val name: String, val age: Int, val onboarding: Int)
data class LoginResponse(val token: String, val userId: String, val onboarding: Int)
data class UserResponse(val token: String)
data class SymptomsRequest(val userId: String, val symptoms: List<String>, val onboarding: Int)
data class PreferencesRequest(val userId: String, val preferences: List<String>, val onboarding: Int)
data class OnboardingStatusResponse(val onboarding: Int)
data class HealthDataRequest(
    val userId: String,
    val month: String,
    val day: Int,
    val data: String, // Binary string
    val notes: String? = null
)

data class HealthDataResponse(
    val success: Boolean,
    val message: String
)
interface ApiService {
    @POST("api/users/login")
    fun login(@Body user: User): Call<LoginResponse>

    @GET("api/onboarding/getOnboardingStatus/{id}")
    fun getOnboardingStatus(@Path("id") userId: String): Call<OnboardingStatusResponse>

    @POST("api/users/signup")
    fun signUp(@Body user: User): Call<UserResponse>

    @POST("api/onboarding/savePersonalDetails")
    fun savePersonalDetails(@Body personalDetails: PersonalDetails): Call<Void>

    @POST("api/onboarding/saveSymptoms")
    fun saveSymptoms(@Body symptomsRequest: SymptomsRequest): Call<Void>

    @POST("api/onboarding/savePreferences")
    fun savePreferences(@Body preferencesRequest: PreferencesRequest): Call<Void>

    @POST("api/daily_form/saveHealthData")
    fun saveHealthData(@Body healthDataRequest: HealthDataRequest): Call<HealthDataResponse>
    @GET("api/daily_form/getHealthData/{userId}/{monthYear}")
    fun getHealthData(@Path("userId") userId: String, @Path("monthYear") monthYear: String): Call<Map<String, Map<String, String>>>
}