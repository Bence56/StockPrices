package com.example.stockprices

import androidx.test.filters.SmallTest
import com.example.stockprices.model.Stock
import com.example.stockprices.persistance.AppDatabase
import com.example.stockprices.persistance.StockDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class PersistanceTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    @Named("test_db")
    lateinit var database:AppDatabase
    private lateinit var dao:StockDao

    @Before
    fun init(){
        hiltRule.inject()
        dao = database.stockDao()
    }

    @Test
    fun insertStock() = runTest{

        val stock:Stock = Stock("ABC",123.45 )
        dao.insertStock(stock = stock)
        val resultFlow = dao.getStocksTest()

        assertTrue(resultFlow.contains(stock))
    }


    @Test
    fun deleteStock() = runTest {
        val stock1 = Stock("ABC", 123.45)
        val stock2 = Stock("XYZ", 987.65)
        dao.insertStock(stock1)
        dao.insertStock(stock2)
        dao.deleteStock(stock1)

        val resultFlow = dao.getStocksTest()


        assertTrue(resultFlow.contains(stock2))
        assertFalse(resultFlow.contains(stock1))
    }

    @Test
    fun updateStock() = runTest {
        val stock1 = Stock("ABC", 123.45)
        val stock2 = Stock("XYZ", 987.65)
        val stock1_ = Stock("ABC", 0.0)
        val stock2_ = Stock("XYZ", 1.2)


            dao.insertStock(stock1)
            dao.insertStock(stock2)
            dao.updatePrice(stock1_)
            dao.updatePrice(stock2_)
            val resultFlow = dao.getStocksTest()


        assertFalse(resultFlow.contains(stock2))
        assertFalse(resultFlow.contains(stock1))

        assertTrue(resultFlow.contains(stock2_))
        assertTrue(resultFlow.contains(stock1_))
    }

    @After
    fun after(){
        database.close()
    }
}