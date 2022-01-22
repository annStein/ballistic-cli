package com.nuwe.challenge.computerwoman.helper

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import java.lang.System.currentTimeMillis

/**
 * writes an object to a file
 * @param path where the file should be stored
 * @param obj object to store
 */
fun writeJsonFile(path: String, obj: Any) {
    // TODO: catch exception for weird object type (not data class/could not be written)
    val mapper = jacksonObjectMapper()
    val file = createJsonFile(path)
    mapper.writerWithDefaultPrettyPrinter().writeValue(file, obj)
}

/**
 * creates a pretty printable json-string of an object
 * @param obj object to convert to json
 * @return json string
 */
fun <T> createJsonString(obj: T): String {
    // TODO: catch exception for weird object type (not data class/could not be written)
    val mapper = jacksonObjectMapper()
    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj)
}

/**
 * reads a json file and converts it to a BallisticInput
 * @param
 */
inline fun <reified T> readJsonInput(filePath: String): T {
    // TODO: catch exception for weird object type (not data class/could not be written)
    val mapper = jacksonObjectMapper()
    val file = File(filePath)
    val res = mapper.readValue(file, T::class.java)
    return res
}

/**
 * creates a JSON file and returns its name
 */
private fun createJsonFile(path: String): File {
    val name = "${currentTimeMillis()}.json"
    val file = File(path, name)
    file.createNewFile()
    return file
}