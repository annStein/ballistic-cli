package com.nuwe.challenge.computerwoman.helper

import com.nuwe.challenge.computerwoman.cli.exception.AppException
import com.nuwe.challenge.computerwoman.config.ERROR_NO_FILE_OR_DIR
import org.slf4j.Logger
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException

@Component
class FileHelper(val LOG: Logger) {
    fun checkIfDirectoryExists(path: String) =
        File(path).let { it.exists() && it.isDirectory }

    /**
     * creates a file with timestamp in ms as name and returns it
     * @param path - where the file should be created
     * @param fileSuffix - suffix to determine file type
     */
    @Throws(AppException::class)
    fun createFile(path: String, fileSuffix: String): File {
        val name = "${System.currentTimeMillis()}.$fileSuffix"
        try {
            val file = File(path, name)
            file.createNewFile()
            return file
        } catch (e: IOException) {
            LOG.error(e.message, e.cause)
            throw AppException(cause = e.cause, message = ERROR_NO_FILE_OR_DIR)
        }
    }
}