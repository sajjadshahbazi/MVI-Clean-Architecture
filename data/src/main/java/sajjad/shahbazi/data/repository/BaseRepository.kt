//package sajjad.shahbazi.data.repository
//
//import sajjad.shahbazi.data.models.RetrofitResult
//
//class BaseRepository {
//    fun <T> execute(response: RetrofitResult<T>): APIResult<T> {
//        return if (response is RetrofitResult.RetrofitSuccess) {
//            if (response.data?.succeeded == true) {
//                APIResult.Success(response.data.result)
//            } else {
//                val statsMessage = ErrorHandler.getErrorMessage(response.data?.error, response.appType)
//                APIResult.Error(response.data?.error?.code ?: 0, Exception(statsMessage))
//            }
//        } else if (response is RetrofitResult.RetrofitFailure) {
//            APIResult.Error(response.statusCode, response.e)
//        } else {
//            tryWithReport {
//                ErrorLoggerImpl.getInstance().sendExceptionLog(
//                    exc = Exception("خطای عمومی"),
//                    extraInfo = "fun BaseRepository - execute -> is not RetrofitSuccess Or RetrofitFailure"
//                )
//            }
//            APIResult.Error(0, Exception("خطای عمومی"))
//        }
//    }
//}