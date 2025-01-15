package sajjad.shahbazi.companyinfo

import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import sajjad.shahbazi.domain.usecases.GetCompaniesNewsUseCase
import sajjad.shahbazi.data.modules.remoteDataModule
import sajjad.shahbazi.domain.models.ApiResult
import kotlin.test.assertTrue

class CompanyNewsIntegrationTest : KoinTest {

    private lateinit var useCase: GetCompaniesNewsUseCase

    @Before
    fun setUp() {
        startKoin {
            modules(remoteDataModule)
        }
        useCase = GetCompaniesNewsUseCase(get())
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should fetch and process company news successfully`(): Unit = runBlocking {
        val result = useCase(
            _companyReq = "microsoft",
            _dateFrom = "2025-01-13",
            _dateTo = "2025-01-15"
        )
        println("Result type: ${result::class.simpleName}")
        println("Result data: ${result}")
        assertTrue(result is ApiResult.Success, "Result is not of type CompanyNewsRepoModel")
    }
}
