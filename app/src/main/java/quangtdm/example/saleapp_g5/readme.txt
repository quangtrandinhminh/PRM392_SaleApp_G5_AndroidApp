//run in cmd/powershell, anywhere
adb reverse tcp:5288 tcp:5288

// Make sure BE is running on port 5288 with HTTP protocol (Not HTTP)
// Log like this
Environment: SaleApp-G5
(No listening on: https://localhost:7xxx) !!!!
[20:08:43 INF] Now listening on: http://localhost:5288
[20:08:44 INF] Application started. Press Ctrl+C to shut down.
[20:08:44 INF] Hosting environment: Development

// Android App Architecture (DI from top to bottom)
// Models: API models for request and response
// Services: interface for API services with HTTP methods and API endpoints (use WebAPIEndpoint for endpoints)
// Utils: get token or show toast
// Repository: fetch data from API, use Services + Retrofit to call API

// API handling
// get data from edittext > new request model (if has) > call API (from repo)
    > override onResponse and onFailure in activity (handle logic, parse to response model show toast, update UI)

// Response model classes
// must have parse method to parse json to object, use Gson
// @SerializedName("data")  private T data; serializedname must be the same as json key
// No need constructor, just getter and setter

// API services interface
// use @GET, @POST, @PUT, @DELETE, @Path, @Query, @Body, @Header
// @Path("id") String id, @Query("name") String name, @Body RequestModel requestModel, @Header("Authorization") String token
// @Headers("Content-Type: application/json")
// Must have Call<ResponseModel> getSomething(
@Path("id") String id, -- [FromRoute]
@Query("name") String name, -- [FromQuery] <=> ?name=abc
@Body RequestModel requestModel, -- [FromBody] <=> { "name": "abc" }
@Header("Authorization") String token) -- [FromHeader] <=> Authorization: Bearer token

// Repository
// fetch data from API, use Services + Retrofit to call API
// use Call<ResponseModel> getSomething(
// use enqueue to call API, onResponse and onFailure to handle response
// use Gson to parse json to object

// Utils
// get token or show toast
// use SharedPreferences to save token
// use Toast.makeText(context, "message", Toast.LENGTH_SHORT).show()




