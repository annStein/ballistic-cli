package com.nuwe.challenge.computerwoman.helper

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
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
@RunWith(JUnitPlatform::class)
class FileHelperTest {

    @Mock
    private lateinit var log: Logger

    private lateinit var helperUnderTest: FileHelper

    @BeforeEach
    fun setup() {
        helperUnderTest = FileHelper(log)
    }

    @Test
    fun `WHEN checking if dir exists AND dir exists THEN return True`() {
        val result = helperUnderTest.checkIfDirectoryExists("src/test")
        assertTrue(result)
    }

    @Test
    fun `WHEN checking if dir exists AND dir does not exist THEN return False`() {
        val result = helperUnderTest.checkIfDirectoryExists("src/thisDoesNotExist")
        assertFalse(result)
    }

    @Test
    fun `WHEN creating file THEN file with correct suffix is created`() {
        val suffix = ".txt"
        val createdFile = helperUnderTest.createFile("src/test/resources", suffix)
        val result = createdFile.exists()
        try {
            assertTrue(result)
            assertTrue(createdFile.name.endsWith(suffix))
        } finally {
            // cleanup
            createdFile.delete()
        }
    }

    @Test
    fun `WHEN creating file in not existing dir THEN AppException is thrown`() {
        assertThrows<AppException> {
            helperUnderTest.createFile("src/test/resources/thisdoesnotexist", ".txt")
        }
    }
}