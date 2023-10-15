package sajjad.shahbazi.common.base

import android.os.Looper
import android.view.View
import android.widget.EditText
import androidx.annotation.CheckResult
import androidx.appcompat.widget.SearchView
import androidx.core.widget.doOnTextChanged
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.take
import timber.log.Timber

internal fun checkMainThread() {
  check(Looper.myLooper() == Looper.getMainLooper()) {
    "Expected to be called on the main thread but was " + Thread.currentThread().name
  }
}

@CheckResult
fun EditText.firstChange(): Flow<Unit> =
  callbackFlow {
    checkMainThread()

    val listener = doOnTextChanged { _, _, _, _ -> trySend(Unit) }
    awaitClose {
      Dispatchers.Main.dispatch(EmptyCoroutineContext) {
        removeTextChangedListener(listener)
        Timber.d("removeTextChangedListener $listener $this")
      }
    }
  }.take(1)

@CheckResult
fun View.clicks(): Flow<View> =
  callbackFlow {
    checkMainThread()

    setOnClickListener { trySend(it) }
    awaitClose { setOnClickListener(null) }
  }

data class SearchViewQueryTextEvent(
  val view: SearchView,
  val query: CharSequence,
  val isSubmitted: Boolean,
)

@CheckResult
fun SearchView.queryTextEvents(): Flow<SearchViewQueryTextEvent> =
  callbackFlow {
    checkMainThread()

    setOnQueryTextListener(
      object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
          trySend(
            SearchViewQueryTextEvent(
              view = this@queryTextEvents,
              query = query,
              isSubmitted = true,
            ),
          )
          return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
          trySend(
            SearchViewQueryTextEvent(
              view = this@queryTextEvents,
              query = newText,
              isSubmitted = false,
            ),
          )
          return true
        }
      },
    )
  }
