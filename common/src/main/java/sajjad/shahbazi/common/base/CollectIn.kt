package sajjad.shahbazi.common.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber

inline fun <T> Flow<T>.collectIn(
  owner: LifecycleOwner,
  minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
  crossinline action: suspend (value: T) -> Unit,
): Job =
  owner.lifecycleScope.launch {
    owner.repeatOnLifecycle(state = minActiveState) {
      Timber.d("Start collecting $owner $minActiveState...")
      collect { action(it) }
    }
  }

@Suppress("unused")
inline fun <T> Flow<T>.collectInViewLifecycle(
  fragment: Fragment,
  minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
  crossinline action: suspend (value: T) -> Unit,
): Job =
  collectIn(
    owner = fragment.viewLifecycleOwner,
    minActiveState = minActiveState,
    action = action,
  )
