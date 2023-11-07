package sajjad.shahbazi.common.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import sajjad.shahbazi.common.mvibase.MviIntent
import sajjad.shahbazi.common.mvibase.MviState


abstract class BaseActivity<
        I : MviIntent,
        S : MviState,
        VM : BaseViewModel<I, S>,
        >:
    AppCompatActivity(), MviView<I, S> {
    abstract val viewModel: VM

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        bindVM()
    }

    private fun bindVM() {
        viewModel?.viewModelScope?.launch {
            viewModel?.viewState?.collectIn(this@BaseActivity) { render(it) }
            intents().onEach {
                viewModel.processIntent(it)
            }.launchIn(lifecycleScope)
        }
    }

    protected abstract fun setupViews()
}