package org.nitri.ors

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test
import org.nitri.ors.domain.elevation.ElevationPointResponse

class ElevationPointResponseTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `parses simple elevation point response`() {
        val payload = """
            {
              "geometry": {
                "coordinates": [8.681495, 49.41461, 112.5],
                "type": "Point"
              },
              "attribution": "openrouteservice.org | OpenStreetMap contributors",
              "timestamp": 1694013510000,
              "version": "0.0.3"
            }
        """.trimIndent()

        val response = json.decodeFromString<ElevationPointResponse>(payload)

        assertEquals("Point", response.geometry.type)
        assertEquals(3, response.geometry.coordinates.size)
        assertEquals(8.681495, response.geometry.coordinates[0], 1e-9)
        assertEquals(49.41461, response.geometry.coordinates[1], 1e-9)
        assertEquals(112.5, response.geometry.coordinates[2], 1e-9)
        assertEquals(
            "openrouteservice.org | OpenStreetMap contributors",
            response.attribution
        )
        assertEquals(1694013510000L, response.timestamp)
        assertEquals("0.0.3", response.version)
    }
}
