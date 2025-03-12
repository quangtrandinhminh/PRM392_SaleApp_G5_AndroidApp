//run in cmd/powershell, anywhere
adb reverse tcp:5288 tcp:5288

// Make sure BE is running on port 5288 with http protocol
// Log like this
Environment: SaleApp-G5
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


