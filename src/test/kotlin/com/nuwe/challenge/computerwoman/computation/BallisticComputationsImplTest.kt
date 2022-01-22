package com.nuwe.challenge.computerwoman.computation

import com.nuwe.challenge.computerwoman.computing.BallisticComputationsImpl
import com.nuwe.challenge.computerwoman.model.BallisticInput
import com.nuwe.challenge.computerwoman.model.BallisticResult
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class BallisticComputationsImplTest {

    private val computerUnderTest = BallisticComputationsImpl()

    @Test
    fun `WHEN calculating max height THEN correct max height is returned`() {
        val expected = 509.683996
        val result = computerUnderTest.calcMaxHeight(100)
        assertEquals(expected, result, 0.0001)
    }

    @Test
    fun `WHEN calculating max distance THEN correct max distance is returned`() {
        val expected = 14.41604
        val result = computerUnderTest.calcMaxDistance(100, 45)
        assertEquals(expected, result, 0.0001)
    }

    @Test
    fun `WHEN computing max height and distance THEN BallisticResult is returned`() {
        val fakeInput = BallisticInput(100, 45)
        val expected = BallisticResult(509.683996, 14.41604)
        val result = computerUnderTest.computeValues(fakeInput)
        assertIs<BallisticResult>(result)
        assertEquals(expected.hMax, result.hMax, 0.0001)
        assertEquals(expected.xMax, result.xMax, 0.0001)
    }
}