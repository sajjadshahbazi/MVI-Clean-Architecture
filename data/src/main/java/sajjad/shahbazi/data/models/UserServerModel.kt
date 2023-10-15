package sajjad.shahbazi.data.models

import com.google.gson.annotations.SerializedName

data class UserServerModel(
    @SerializedName("uid")
    val uid: String?=null,
    @SerializedName("firstName")
    val firstName: String?=null,
    @SerializedName("lastName")
    val lastName: String?=null,
    @SerializedName("age")
    val age: Int?=null
)