package com.example.sprint6

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sprint6.data.local.PhoneDao
import com.example.sprint6.data.local.PhoneEntity
import com.example.sprint6.data.local.PhonesDataBase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class PhoneRoomDatabaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var phonesDao: PhoneDao
    private lateinit var db: PhonesDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PhonesDataBase::class.java).build()
        phonesDao = db.getPhoneDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun inserPhones_empty() = runBlocking {
        // Given
        val phonesList = listOf<PhoneEntity>()

        // When
        phonesDao.insertPhone(phonesList)

        // Then A
        val it = phonesDao.getPhones().getOrAwaitValue()
        assertThat(it).isNotNull()
        assertThat(it).isEmpty()

        // Then B
        phonesDao.getPhones().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertPhones_happyCase_1element() = runBlocking {
        // Given
        val phonesList = listOf(PhoneEntity(1, "kasjhhas", 198262, "com.example.cl"))

        // When
        phonesDao.insertPhone(phonesList)

        // Then
        phonesDao.getPhones().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertPhones_happyCase_3elements() = runBlocking {
        // Given
        val phonesList = listOf(
            PhoneEntity(1, "aisuttg", 1928763, "com.example.cl"),
            PhoneEntity(2, "asuihbcn", 93432, "com.example.cl"),
            PhoneEntity(3, "jdsnnsdmg", 928372, "com.example.cl")
        )

        // When
        phonesDao.insertPhone(phonesList)

        // Then
        phonesDao.getPhones().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}