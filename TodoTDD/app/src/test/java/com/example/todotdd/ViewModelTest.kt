package com.example.todotdd

import android.app.Application
import org.junit.Rule
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

internal class ViewModelTest : KoinTest{
    @get:Rule
    val mockitoRule:MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var context: Application

    
}