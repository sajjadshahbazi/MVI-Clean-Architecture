package sajjad.shahbazi.common.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import sajjad.shahbazi.common.R
import sajjad.shahbazi.common.mvibase.MviIntent
import sajjad.shahbazi.common.mvibase.MviState


abstract class BaseActivity<
        I : MviIntent,
        S : MviState,
        VM : BaseViewModel<I, S>,
        >(
    @LayoutRes contentLayoutId: Int,
) :
    AppCompatActivity(contentLayoutId), MviView<I, S> {
    abstract val viewModel: VM

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        bindVM()
    }

//    override fun onStop() {
//        loading.dismiss()
//        super.onStop()
//    }

    private fun bindVM() {
        viewModel?.viewModelScope?.launch{
            viewModel?.viewState?.collect(this@BaseActivity::render)
            flow<MviIntent> {
                viewModel?.processIntent(intents())
            }
        }
    }

    protected abstract fun setupViews()
//        val loading: AlertDialog by lazy {
//        AlertDialog.Builder(this, R.style.LoadingTheme)
//            .setView(R.layout.dialog_loading)
//            .setOnDismissListener {
//            }
//            .setCancelable(false)
//            .create()
//    }
}







//<I : MviIntent, S : MviState, V : BaseViewModel<I, S>> : AppCompatActivity(), MviView<I, S> {
////    var viewModelFactory: ViewModelProvider.Factory? = null
//    lateinit var viewModel: V
//
//    override fun onStart() {
//        super.onStart()
//        bind()
//    }
//
//    override fun onStop() {
//        loading.dismiss()
//        super.onStop()
//    }
//
//    private fun bind() {
//        viewModel?.viewModelScope?.launch{
//            viewModel?.viewState?.collect(this@BaseActivity::render)
//            flow<MviIntent> {
//                viewModel?.processIntent(intents())
//            }
//        }
//    }
//
//    val loading: AlertDialog by lazy {
//        AlertDialog.Builder(this, R.style.LoadingTheme)
//            .setView(R.layout.dialog_loading)
//            .setOnDismissListener {
//            }
//            .setCancelable(false)
//            .create()
//    }
//
//
//
////    @Inject
////    fun injectViewModelFactory(factory: ViewModelProvider.Factory) {
////        viewModelFactory = factory
////    }
////
////    inline fun <I : MviIntent, S : MviState, reified V : BaseViewModel<I, S>> BaseActivity<I, S, V>.createViewModel() {
////        createViewModel(V::class.java)
////    }
//
//    inline fun createViewModel(clazz: KClass<V>) {
//
//
//        val rfrfrf : clazz by viewModel()
////        viewModelFactory = ViewModelProvider.AndroidViewModelFactory(application)
////        viewModel = viewModelFactory?.create(clazz)
//
//        val viewModeel: UserViewModel by viewModel()
////        viewModel = clazz by viewModel()
////        bind()
////        viewModelFactory?.let {
////            viewModel = ViewModelProvider(this,it)[clazz]
////        }
//    }
//
//    fun showLoading() {
//        loading.show()
//    }
//
//    fun dismissLoading() {
//        loading.dismiss()
//    }
//
//}
//
//inline fun <I : MviIntent, S : MviState, reified V : BaseViewModel<I, S>> BaseActivity<I, S, V>.createViewModel() {
//    createViewModel(V::class.java)
//}
//
//fun <I : MviIntent, S : MviState, V : BaseViewModel<I, S>> BaseActivity<I, S, V>.renderLoading(show : Boolean) {
//    if (show) {
//        showLoading()
//    } else {
//        dismissLoading()
//    }
//}