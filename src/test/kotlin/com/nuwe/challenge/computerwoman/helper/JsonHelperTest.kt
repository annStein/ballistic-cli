package com.nuwe.challenge.computerwoman.helper

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.nhaarman.mockitokotlin2.*
import com.nuwe.challenge.computerwoman.cli.exception.AppException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.slf4j.Logger
import java.io.File
import java.io.IOException
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
@RunWith(JUnitPlatform::class)
class JsonHelperTest {
    @Mock
    private lateinit var mockMapper: ObjectMapper

    @Mock
    private lateinit var mockObjectWriter: ObjectWriter

    @Mock
    private lateinit var mockFileHelper: FileHelper

    @Mock
    private lateinit var log: Logger

    private lateinit var helperUnderTest: JsonHelper

    @BeforeEach
    fun setup() {
        helperUnderTest = JsonHelper(mockFileHelper, mockMapper, log)
    }

    @Test
    fun `WHEN write Json object to file THEN mapper is invoked`() {
        whenever(mockMapper.writerWithDefaultPrettyPrinter()).thenReturn(mockObjectWriter)
        whenever(mockFileHelper.createFile(any(), any())).thenReturn(mock())

        helperUnderTest.writeJsonFile("path", DummyClass())
        verify(mockFileHelper).createFile(any(), any())
        verify(mockMapper).writerWithDefaultPrettyPrinter()
        verify(mockObjectWriter).writeValue(any<File>(), any())
    }

    @Test
    fun `WHEN write Json object to file AND something goes wrong THEN AppException is thrown`() {
        whenever(mockFileHelper.createFile(any(), any())).thenReturn(mock())
        whenever(mockMapper.writerWithDefaultPrettyPrinter()).thenReturn(mockObjectWriter)
        whenever(mockObjectWriter.writeValue(any<File>(), any())).thenThrow(IOException())

        assertThrows<AppException> { helperUnderTest.writeJsonFile("path", DummyClass()) }
    }

    @Test
    fun `WHEN write convert object to json string THEN mapper is invoked`() {
        val expected = "blabliblubb"
        whenever(mockMapper.writerWithDefaultPrettyPrinter()).thenReturn(mockObjectWriter)
        whenever(mockObjectWriter.writeValueAsString(any())).thenReturn(expected)

        val result = helperUnderTest.convertObjectToJsonString(DummyClass())
        verify(mockMapper).writerWithDefaultPrettyPrinter()
        verify(mockObjectWriter).writeValueAsString(any())
        assertEquals(expected, result)
    }

    @Test
    fun `WHEN write convert object to json string AND something goes wrong THEN AppException is thrown`() {
        whenever(mockMapper.writerWithDefaultPrettyPrinter()).thenReturn(mockObjectWriter)
        whenever(mockObjectWriter.writeValueAsString(any())).thenThrow(JsonProcessingException::class.java)

        assertThrows<AppException> { helperUnderTest.convertObjectToJsonString(DummyClass()) }
    }

    @Test
    fun `WHEN read Json input from file THEN object with data from that input is returned`() {
        val expected = DummyClass()
        whenever(mockMapper.readValue(any<File>(), eq(DummyClass::class.java))).thenReturn(expected)

        val result = helperUnderTest.readJsonInput<DummyClass>("src/test/resources/test_input.json")
        assertEquals(expected, result)
    }

    @Test
    fun `WHEN read Json input from file AND something goes wrong THEN object with data from that input is returned`() {
        whenever(mockMapper.readValue(any<File>(), eq(DummyClass::class.java))).thenThrow(IOException())

        assertThrows<AppException> { helperUnderTest.readJsonInput<DummyClass>("src/test/resources/test_input.json") }
    }

    @Test
    fun `WHEN read Json input from file AND file does not exist THEN object with data from that input is returned`() {
        assertThrows<AppException> { helperUnderTest.readJsonInput<DummyClass>("blabliblubb") }
    }

    class DummyClass
}