package com.nuwe.challenge.computerwoman.helper

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.nuwe.challenge.computerwoman.cli.exception.AppException
import com.nuwe.challenge.computerwoman.config.ERROR_GENERAL
import com.nuwe.challenge.computerwoman.config.ERROR_NO_FILE_OR_DIR
import org.slf4j.Logger
import org.springframework.stereotype.Component
import java.io.File

@Component
class JsonHelper(
    val fileHelper: FileHelper,
    val mapper: ObjectMapper,
    val LOG: Logger
) {

    /**
     * writes an object to a file
     * @param path where the file should be stored
     * @param obj object to store
     */
    @Throws(AppException::class)
    fun writeJsonFile(path: String, obj: Any) {
        val file = fileHelper.createFile(path, "json")
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, obj)
        } catch (e: Exception) {
            LOG.error(e.message, e.cause)
            throw AppException(e.cause, ERROR_GENERAL)
        }
    }

    /**
     * creates a pretty printable json-string of an object
     * @param obj object to convert to json
     * @return json string
     */
    @Throws(AppException::class)
    fun <T> convertObjectToJsonString(obj: T): String {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj)
        } catch (e: JsonProcessingException) {
            LOG.error(e.message, e.cause)
            throw AppException(e.cause, ERROR_GENERAL)
        }
    }

    /**
     * reads a json file and converts it to a BallisticInput
     * @param
     */
    @Throws(AppException::class)
    final inline fun <reified T> readJsonInput(filePath: String): T {
        try {
            val file = File(filePath)
            if (file.exists()) {
                val res = mapper.readValue(file, T::class.java)
                return res
            } else {
                throw AppException(Throwable(), ERROR_NO_FILE_OR_DIR)
            }
        } catch (e: Exception) {
            LOG.error(e.message, e.cause)
            throw AppException(e.cause, ERROR_GENERAL)
        }
    }
}
