package com.nuwe.challenge.computerwoman.helper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Component
import java.io.File

@Component
class JsonHelper(
    val fileHelper: FileHelper,
) {
    val mapper: ObjectMapper = jacksonObjectMapper()

    /**
     * writes an object to a file
     * @param path where the file should be stored
     * @param obj object to store
     */
    fun writeJsonFile(path: String, obj: Any) {
        // TODO: catch exception for weird object type
        val file = fileHelper.createFile(path, "json")
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, obj)
    }

    /**
     * creates a pretty printable json-string of an object
     * @param obj object to convert to json
     * @return json string
     */
    fun <T> convertObjectToJsonString(obj: T): String {
        // TODO: catch exception for weird object type
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj)
    }

    /**
     * reads a json file and converts it to a BallisticInput
     * @param
     */
    final inline fun <reified T> readJsonInput(filePath: String): T {
        // TODO: catch exception for weird object type
        val file = File(filePath)
        val res = mapper.readValue(file, T::class.java)
        return res
    }
}
