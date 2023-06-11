package sajjad.shahbazi.common.mvibase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MviProcessor<A : MviAction, R : MviResult> {
    fun actionProcessor(actions : Flow<A>) : Flow<R>
}

