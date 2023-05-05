package sajjad.shahbazi.data.models

import com.squareup.moshi.Json

data class UserServerModel(
    @Json(name = "uid")
    val uid: String?=null,
    @Json(name = "firstName")
    val firstName: String?=null,
    @Json(name = "lastName")
    val lastName: String?=null,
    @Json(name = "age")
    val age: Int?=null
)