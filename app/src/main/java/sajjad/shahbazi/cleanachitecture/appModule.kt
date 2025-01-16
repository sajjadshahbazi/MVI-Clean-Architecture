package sajjad.shahbazi.cleanachitecture


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import sajjad.shahbazi.common.mvibase.MviProcessor
import sajjad.shahbazi.companyinfo.CompanyNewsProcessor
import sajjad.shahbazi.companyinfo.CompanyNewsViewModel
import sajjad.shahbazi.companyinfo.archmodel.NewsAction
import sajjad.shahbazi.companyinfo.archmodel.NewsResult


val viewModelModules: Module = module {
    viewModel{
        CompanyNewsViewModel(get())
    }
}

val mviProcessorModules : Module = module {
    factory<MviProcessor<NewsAction, NewsResult>> { CompanyNewsProcessor(get()) }
}