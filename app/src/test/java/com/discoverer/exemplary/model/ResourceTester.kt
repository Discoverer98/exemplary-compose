package com.discoverer.exemplary.model

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


const val MOCK_VALUE = "Mock value"
const val MOCK_ERROR_MESSAGE = "Mock error message"


@RunWith(MockitoJUnitRunner::class)
class ResourceTester {

    private val mockData = MockData(MOCK_VALUE)

    @Test
    fun `test success resource`() {
        val resource = Resource.success(mockData)
        assertEquals(Status.SUCCESS, resource.status)
        assertEquals(mockData, resource.data)
        assertNull(resource.message)
    }

    @Test
    fun `test error resource`() {
        val resource = Resource.error(MOCK_ERROR_MESSAGE, mockData)
        assertEquals(Status.ERROR, resource.status)
        assertEquals(MOCK_ERROR_MESSAGE, resource.message)
        assertEquals(mockData, resource.data)
    }

    @Test
    fun `test loading resource`() {
        val resource = Resource.loading(mockData)
        assertEquals(Status.LOADING, resource.status)
        assertEquals(mockData, resource.data)
        assertNull(resource.message)
    }


    class MockData(val mockValue: String)

} // ResourceTester class