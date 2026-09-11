package org.nitri.ors

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.nitri.ors.domain.geocode.GeocodeSearchResponse
import org.nitri.ors.helper.GeocodeHelper
import org.nitri.ors.internal.DefaultOrsClient
import org.nitri.ors.internal.api.OpenRouteServiceApi

class GeocodeTest {

    private lateinit var mockApi: OpenRouteServiceApi
    private lateinit var client: DefaultOrsClient
    private lateinit var mockOrsClient: OrsClient
    private lateinit var helper: GeocodeHelper

    private val testApiKey = "test_constructor_api_key_12345"

    @Before
    fun setUp() {
        mockApi = mock()
        client = DefaultOrsClient(apiKey = testApiKey, api = mockApi)
        mockOrsClient = mock()
        helper = GeocodeHelper()
    }

    @Test
    fun `geocodeSearch passes constructor apiKey to OpenRouteServiceApi`() = runTest {
        val expectedResponse = GeocodeSearchResponse()
        whenever(
            mockApi.geocodeSearch(
                text = any(),
                focusLon = anyOrNull(),
                focusLat = anyOrNull(),
                rectMinLon = anyOrNull(),
                rectMinLat = anyOrNull(),
                rectMaxLon = anyOrNull(),
                rectMaxLat = anyOrNull(),
                circleLon = anyOrNull(),
                circleLat = anyOrNull(),
                circleRadiusMeters = anyOrNull(),
                boundaryGid = anyOrNull(),
                boundaryCountry = anyOrNull(),
                sourcesCsv = anyOrNull(),
                layersCsv = anyOrNull(),
                size = anyOrNull(),
                apiKey = any()
            )
        ).thenReturn(expectedResponse)

        val response = client.geocodeSearch(text = "Heidelberg")

        assertEquals(expectedResponse, response)

        val keyCaptor = argumentCaptor<String>()
        verify(mockApi).geocodeSearch(
            text = eq("Heidelberg"),
            focusLon = anyOrNull(),
            focusLat = anyOrNull(),
            rectMinLon = anyOrNull(),
            rectMinLat = anyOrNull(),
            rectMaxLon = anyOrNull(),
            rectMaxLat = anyOrNull(),
            circleLon = anyOrNull(),
            circleLat = anyOrNull(),
            circleRadiusMeters = anyOrNull(),
            boundaryGid = anyOrNull(),
            boundaryCountry = anyOrNull(),
            sourcesCsv = anyOrNull(),
            layersCsv = anyOrNull(),
            size = eq(10),
            apiKey = keyCaptor.capture()
        )
        assertEquals(testApiKey, keyCaptor.firstValue)
    }

    @Test
    fun `geocodeAutocomplete passes constructor apiKey to OpenRouteServiceApi`() = runTest {
        val expectedResponse = GeocodeSearchResponse()
        whenever(
            mockApi.autocomplete(
                apiKey = any(),
                text = any(),
                focusLon = anyOrNull(),
                focusLat = anyOrNull(),
                rectMinLon = anyOrNull(),
                rectMinLat = anyOrNull(),
                rectMaxLon = anyOrNull(),
                rectMaxLat = anyOrNull(),
                circleLon = anyOrNull(),
                circleLat = anyOrNull(),
                circleRadius = anyOrNull(),
                country = anyOrNull(),
                sources = anyOrNull(),
                layers = anyOrNull(),
                size = anyOrNull()
            )
        ).thenReturn(expectedResponse)

        val response = client.geocodeAutocomplete(text = "Heidelb", size = 5)

        assertEquals(expectedResponse, response)

        val keyCaptor = argumentCaptor<String>()
        verify(mockApi).autocomplete(
            apiKey = keyCaptor.capture(),
            text = eq("Heidelb"),
            focusLon = anyOrNull(),
            focusLat = anyOrNull(),
            rectMinLon = anyOrNull(),
            rectMinLat = anyOrNull(),
            rectMaxLon = anyOrNull(),
            rectMaxLat = anyOrNull(),
            circleLon = anyOrNull(),
            circleLat = anyOrNull(),
            circleRadius = anyOrNull(),
            country = anyOrNull(),
            sources = anyOrNull(),
            layers = anyOrNull(),
            size = eq(5)
        )
        assertEquals(testApiKey, keyCaptor.firstValue)
    }

    @Test
    fun `geocodeStructured passes constructor apiKey to OpenRouteServiceApi`() = runTest {
        val expectedResponse = GeocodeSearchResponse()
        whenever(
            mockApi.geocodeStructured(
                apiKey = any(),
                address = anyOrNull(),
                neighbourhood = anyOrNull(),
                borough = anyOrNull(),
                locality = anyOrNull(),
                county = anyOrNull(),
                region = anyOrNull(),
                country = anyOrNull(),
                postalcode = anyOrNull(),
                focusLon = anyOrNull(),
                focusLat = anyOrNull(),
                rectMinLon = anyOrNull(),
                rectMinLat = anyOrNull(),
                rectMaxLon = anyOrNull(),
                rectMaxLat = anyOrNull(),
                circleLon = anyOrNull(),
                circleLat = anyOrNull(),
                circleRadiusMeters = anyOrNull(),
                boundaryCountry = anyOrNull(),
                layers = anyOrNull(),
                sources = anyOrNull(),
                size = anyOrNull()
            )
        ).thenReturn(expectedResponse)

        val response = client.geocodeStructured(locality = "Heidelberg", country = "Germany", size = 5)

        assertEquals(expectedResponse, response)

        val keyCaptor = argumentCaptor<String>()
        verify(mockApi).geocodeStructured(
            apiKey = keyCaptor.capture(),
            address = anyOrNull(),
            neighbourhood = anyOrNull(),
            borough = anyOrNull(),
            locality = eq("Heidelberg"),
            county = anyOrNull(),
            region = anyOrNull(),
            country = eq("Germany"),
            postalcode = anyOrNull(),
            focusLon = anyOrNull(),
            focusLat = anyOrNull(),
            rectMinLon = anyOrNull(),
            rectMinLat = anyOrNull(),
            rectMaxLon = anyOrNull(),
            rectMaxLat = anyOrNull(),
            circleLon = anyOrNull(),
            circleLat = anyOrNull(),
            circleRadiusMeters = anyOrNull(),
            boundaryCountry = anyOrNull(),
            layers = anyOrNull(),
            sources = anyOrNull(),
            size = eq(5)
        )
        assertEquals(testApiKey, keyCaptor.firstValue)
    }

    @Test
    fun `geocodeReverse passes constructor apiKey to OpenRouteServiceApi`() = runTest {
        val expectedResponse = GeocodeSearchResponse()
        whenever(
            mockApi.geocodeReverse(
                apiKey = any(),
                lon = any(),
                lat = any(),
                radiusKm = anyOrNull(),
                size = anyOrNull(),
                layers = anyOrNull(),
                sources = anyOrNull(),
                boundaryCountry = anyOrNull()
            )
        ).thenReturn(expectedResponse)

        val response = client.geocodeReverse(lon = 8.681495, lat = 49.41461, size = 5)

        assertEquals(expectedResponse, response)

        val keyCaptor = argumentCaptor<String>()
        verify(mockApi).geocodeReverse(
            apiKey = keyCaptor.capture(),
            lon = eq(8.681495),
            lat = eq(49.41461),
            radiusKm = anyOrNull(),
            size = eq(5),
            layers = anyOrNull(),
            sources = anyOrNull(),
            boundaryCountry = anyOrNull()
        )
        assertEquals(testApiKey, keyCaptor.firstValue)
    }

    @Test
    fun `GeocodeHelper delegates search to OrsClient without apiKey`() = runTest {
        val expectedResponse = GeocodeSearchResponse()
        whenever(
            mockOrsClient.geocodeSearch(
                text = any(),
                focusLon = anyOrNull(),
                focusLat = anyOrNull(),
                rectMinLon = anyOrNull(),
                rectMinLat = anyOrNull(),
                rectMaxLon = anyOrNull(),
                rectMaxLat = anyOrNull(),
                circleLon = anyOrNull(),
                circleLat = anyOrNull(),
                circleRadiusMeters = anyOrNull(),
                boundaryGid = anyOrNull(),
                boundaryCountry = anyOrNull(),
                sourcesCsv = anyOrNull(),
                layersCsv = anyOrNull(),
                size = anyOrNull()
            )
        ).thenReturn(expectedResponse)

        val response = with(helper) { mockOrsClient.search(text = "Heidelberg") }

        assertEquals(expectedResponse, response)
        verify(mockOrsClient).geocodeSearch(
            text = eq("Heidelberg"),
            focusLon = anyOrNull(),
            focusLat = anyOrNull(),
            rectMinLon = anyOrNull(),
            rectMinLat = anyOrNull(),
            rectMaxLon = anyOrNull(),
            rectMaxLat = anyOrNull(),
            circleLon = anyOrNull(),
            circleLat = anyOrNull(),
            circleRadiusMeters = anyOrNull(),
            boundaryGid = anyOrNull(),
            boundaryCountry = anyOrNull(),
            sourcesCsv = anyOrNull(),
            layersCsv = anyOrNull(),
            size = eq(10)
        )
    }

    @Test
    fun `GeocodeHelper delegates autocomplete to OrsClient without apiKey`() = runTest {
        val expectedResponse = GeocodeSearchResponse()
        whenever(
            mockOrsClient.geocodeAutocomplete(
                text = any(),
                focusLon = anyOrNull(),
                focusLat = anyOrNull(),
                rectMinLon = anyOrNull(),
                rectMinLat = anyOrNull(),
                rectMaxLon = anyOrNull(),
                rectMaxLat = anyOrNull(),
                circleLon = anyOrNull(),
                circleLat = anyOrNull(),
                circleRadius = anyOrNull(),
                country = anyOrNull(),
                sources = anyOrNull(),
                layers = anyOrNull(),
                size = anyOrNull()
            )
        ).thenReturn(expectedResponse)

        val response = with(helper) { mockOrsClient.autocomplete(text = "Heidelb", size = 5) }

        assertEquals(expectedResponse, response)
        verify(mockOrsClient).geocodeAutocomplete(
            text = eq("Heidelb"),
            focusLon = anyOrNull(),
            focusLat = anyOrNull(),
            rectMinLon = anyOrNull(),
            rectMinLat = anyOrNull(),
            rectMaxLon = anyOrNull(),
            rectMaxLat = anyOrNull(),
            circleLon = anyOrNull(),
            circleLat = anyOrNull(),
            circleRadius = anyOrNull(),
            country = anyOrNull(),
            sources = anyOrNull(),
            layers = anyOrNull(),
            size = eq(5)
        )
    }

    @Test
    fun `GeocodeHelper delegates structured to OrsClient without apiKey`() = runTest {
        val expectedResponse = GeocodeSearchResponse()
        whenever(
            mockOrsClient.geocodeStructured(
                address = anyOrNull(),
                neighbourhood = anyOrNull(),
                borough = anyOrNull(),
                locality = anyOrNull(),
                county = anyOrNull(),
                region = anyOrNull(),
                country = anyOrNull(),
                postalcode = anyOrNull(),
                focusLon = anyOrNull(),
                focusLat = anyOrNull(),
                rectMinLon = anyOrNull(),
                rectMinLat = anyOrNull(),
                rectMaxLon = anyOrNull(),
                rectMaxLat = anyOrNull(),
                circleLon = anyOrNull(),
                circleLat = anyOrNull(),
                circleRadiusMeters = anyOrNull(),
                boundaryCountry = anyOrNull(),
                layers = anyOrNull(),
                sources = anyOrNull(),
                size = anyOrNull()
            )
        ).thenReturn(expectedResponse)

        val response = with(helper) { mockOrsClient.structured(locality = "Heidelberg", country = "Germany", size = 5) }

        assertEquals(expectedResponse, response)
        verify(mockOrsClient).geocodeStructured(
            address = anyOrNull(),
            neighbourhood = anyOrNull(),
            borough = anyOrNull(),
            locality = eq("Heidelberg"),
            county = anyOrNull(),
            region = anyOrNull(),
            country = eq("Germany"),
            postalcode = anyOrNull(),
            focusLon = anyOrNull(),
            focusLat = anyOrNull(),
            rectMinLon = anyOrNull(),
            rectMinLat = anyOrNull(),
            rectMaxLon = anyOrNull(),
            rectMaxLat = anyOrNull(),
            circleLon = anyOrNull(),
            circleLat = anyOrNull(),
            circleRadiusMeters = anyOrNull(),
            boundaryCountry = anyOrNull(),
            layers = anyOrNull(),
            sources = anyOrNull(),
            size = eq(5)
        )
    }

    @Test
    fun `GeocodeHelper delegates reverse to OrsClient without apiKey`() = runTest {
        val expectedResponse = GeocodeSearchResponse()
        whenever(
            mockOrsClient.geocodeReverse(
                lon = any(),
                lat = any(),
                radiusKm = anyOrNull(),
                size = anyOrNull(),
                layers = anyOrNull(),
                sources = anyOrNull(),
                boundaryCountry = anyOrNull()
            )
        ).thenReturn(expectedResponse)

        val response = with(helper) { mockOrsClient.reverse(lon = 8.681495, lat = 49.41461, size = 5) }

        assertEquals(expectedResponse, response)
        verify(mockOrsClient).geocodeReverse(
            lon = eq(8.681495),
            lat = eq(49.41461),
            radiusKm = anyOrNull(),
            size = eq(5),
            layers = anyOrNull(),
            sources = anyOrNull(),
            boundaryCountry = anyOrNull()
        )
    }
}
