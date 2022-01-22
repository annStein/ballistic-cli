package com.nuwe.challenge.computerwoman.cli

import com.nhaarman.mockitokotlin2.*
import com.nuwe.challenge.computerwoman.computing.BallisticComputationsImpl
import com.nuwe.challenge.computerwoman.helper.FileHelper
import com.nuwe.challenge.computerwoman.helper.JsonHelper
import com.nuwe.challenge.computerwoman.model.BallisticInput
import com.nuwe.challenge.computerwoman.model.BallisticResult
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.slf4j.Logger
import kotlin.test.assertEquals


@ExtendWith(MockitoExtension::class)
@RunWith(JUnitPlatform::class)
class BallisticShellTest {

    @Mock
    private lateinit var ballisticComputer: BallisticComputationsImpl

    @Mock
    private lateinit var jsonHelper: JsonHelper

    @Mock
    private lateinit var fileHelper: FileHelper

    @Mock
    private lateinit var log: Logger

    private val fakeV0 = 100
    private val fakeAlpha = 45
    private val fakeValue = 12.5
    private val fakeJson = """{"v0": 100,"alpha": 45}"""
    private val fakeInput = BallisticInput(fakeV0, fakeAlpha)


    private lateinit var shellUnderTest: BallisticShell

    @BeforeEach
    fun setup() {
        shellUnderTest = BallisticShell(ballisticComputer, jsonHelper, fileHelper, log)
    }

    @Test
    fun `WHEN computing hmax THEN hmax is returned`() {
        whenever(ballisticComputer.calcMaxHeight(any())).thenReturn(fakeValue)
        val result = shellUnderTest.hMax(1)
        val expected = fakeValue
        assertEquals(expected, result)
    }

    @Test
    fun `WHEN computing xmax THEN xmax is returned`() {
        whenever(ballisticComputer.calcMaxDistance(any(), any())).thenReturn(fakeValue)
        val result = shellUnderTest.xMax(1, 1)
        val expected = fakeValue
        assertEquals(expected, result)
    }

    @Test
    fun `WHEN compute values from manual input THEN values are computed`() {
        val shellUnderTest: BallisticShell = spy(BallisticShell(ballisticComputer, jsonHelper, fileHelper, log))
        doReturn(fakeJson).`when`(shellUnderTest).compute(fakeInput, false)

        val result = shellUnderTest.computeManual(fakeV0, fakeAlpha, false)
        assertEquals(fakeJson, result)
    }

//    @Test
//    fun `WHEN compute values from file input THEN values are computed`() {
//        val testInputPath = "resources/test_input.json"
//        val shellUnderTest: BallisticShell = spy(BallisticShell(ballisticComputer, jsonHelper, log))
//        doReturn(fakeJson).`when`(shellUnderTest).compute(fakeInput, false)
//        whenever<BallisticShell>(jsonHelper.readJsonInput()).then()
//
//        val result = shellUnderTest.computeFile(testInputPath, false)
//        assertEquals(fakeJson, result)
//    }

    @Test
    fun `WHEN compute BallisticInput AND should be written to file THEN invoke writing file`() {
        val ballisticResult = BallisticResult(fakeValue, fakeValue)
        whenever(ballisticComputer.computeValues(eq(fakeInput))).thenReturn(ballisticResult)
        shellUnderTest.compute(fakeInput, true)
        verify(jsonHelper).writeJsonFile(any(), any())
    }

    @Test
    fun `WHEN compute BallisticInput AND should NOT be written to file THEN invoke writing file`() {
        val ballisticResult = BallisticResult(fakeValue, fakeValue)
        whenever(ballisticComputer.computeValues(eq(fakeInput))).thenReturn(ballisticResult)
        shellUnderTest.compute(fakeInput, false)
        verify(jsonHelper, never()).writeJsonFile(any(), any())
    }

}