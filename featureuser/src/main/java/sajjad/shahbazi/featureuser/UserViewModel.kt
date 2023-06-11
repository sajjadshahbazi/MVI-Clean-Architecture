package sajjad.shahbazi.featureuser

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.selects.select
import sajjad.shahbazi.common.mvibase.BaseMviViewModel
import sajjad.shahbazi.common.mvibase.MviViewModel
import sajjad.shahbazi.featureuser.archmodel.UserIntent
import sajjad.shahbazi.featureuser.archmodel.UserState

class UserViewModel : MviViewModel<
        UserIntent,
        UserState>{

//    fun CoroutineScope.buzz() = produce<String> {
//        while (true) { // sends "Buzz!" every 1000 ms
//            delay(1000)
//            send("Buzz!")
//        }
//    }
//
//    suspend fun selectFizzBuzz(fizz: ReceiveChannel<String>, buzz: ReceiveChannel<String>) {
//        select<Unit> { // <Unit> means that this select expression does not produce any result
//            fizz.onReceive { value ->  // this is the first select clause
//                println("fizz -> '$value'")
//            }
//            buzz.onReceive { value ->  // this is the second select clause
//                println("buzz -> '$value'")
//            }
//        }
//    }

    override val viewState: StateFlow<UserState>
        get() = TODO("Not yet implemented")

    override suspend fun processIntent(intent: UserIntent) {
        TODO("Not yet implemented")
    }
}