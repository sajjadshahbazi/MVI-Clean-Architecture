package sajjad.shahbazi.common.base

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import sajjad.shahbazi.common.R
import sajjad.shahbazi.common.mvibase.MviIntent
import sajjad.shahbazi.common.mvibase.MviState
import javax.inject.Inject


abstract class BaseActivity<I : MviIntent, S : MviState, V : BaseViewModel<I, S>> : AppCompatActivity(), MviView<I, S> {
    var viewModelFactory: ViewModelProvider.Factory? = null
    var viewModel: V? = null

    override fun onStart() {
        super.onStart()
        bind()
    }

    override fun onStop() {
        loading.dismiss()
        super.onStop()
    }

    private fun bind() {
        viewModel?.viewModelScope?.launch{
            viewModel?.viewState?.collect(this@BaseActivity::render)
            flow<MviIntent> {
                viewModel?.processIntent(intents())
            }
        }
    }

    val loading: AlertDialog by lazy {
        AlertDialog.Builder(this, R.style.LoadingTheme)
            .setView(R.layout.dialog_loading)
            .setOnDismissListener {
            }
            .setCancelable(false)
            .create()
    }



//    @Inject
//    fun injectViewModelFactory(factory: ViewModelProvider.Factory) {
//        viewModelFactory = factory
//    }
//
//    inline fun <I : MviIntent, S : MviState, reified V : BaseViewModel<I, S>> BaseActivity<I, S, V>.createViewModel() {
//        createViewModel(V::class.java)
//    }

    fun createViewModel(clazz: Class<V>) {

        viewModel = clazz by viewModel()

//        viewModelFactory?.let {
//            viewModel = ViewModelProvider(this,it)[clazz]
//        }
    }

    fun showLoading() {
        loading.show()
    }

    fun dismissLoading() {
        loading.dismiss()
    }

}

fun <I : MviIntent, S : MviState, V : BaseViewModel<I, S>> BaseActivity<I, S, V>.renderLoading(show : Boolean) {
    if (show) {
        showLoading()
    } else {
        dismissLoading()
    }
}