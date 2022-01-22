package com.nuwe.challenge.computerwoman.runner

import com.nuwe.challenge.computerwoman.computing.BallisticComputationsImpl
import com.nuwe.challenge.computerwoman.helpers.checkIfDirectoryExists
import com.nuwe.challenge.computerwoman.helpers.createJsonString
import com.nuwe.challenge.computerwoman.helpers.readJsonInput
import com.nuwe.challenge.computerwoman.helpers.writeJsonFile
import com.nuwe.challenge.computerwoman.model.BallisticInput
import com.nuwe.challenge.computerwoman.model.BallisticOutput
import org.jline.utils.Log
import org.slf4j.Logger
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod


@ShellComponent
class BallisticShell(
    val LOG: Logger,
    val ballisticComputer: BallisticComputationsImpl) {

    var outputPath: String = "${System.getProperty("user.home")}/Documents/nuwe_challenges/results"

    @ShellMethod("Computes the maximum height of the projectile.")
    fun hMax(v0: Int): Double {
        return ballisticComputer.calcMaxHeight(v0)
    }

    @ShellMethod("Computes the maximum traveled distance.")
    fun xMax(v0: Int, alpha: Int): Double {
        return ballisticComputer.calcMaxDistance(v0, alpha)
    }

    @ShellMethod("Computes the maximum height of the projectile and the maximum traveled distance.")
    fun computeManual(v0: Int, alpha: Int, toFile: Boolean): String {
        val input = BallisticInput(v0, alpha)
        return compute(input, toFile)
    }

    @ShellMethod("Computes the maximum height of the projectile and the maximum traveled distance from JSON-file.")
    fun computeFile(pathToFile: String, toFile: Boolean): String {
        val input = readJsonInput<BallisticInput>(pathToFile)
        return compute(input, toFile)
    }

    private fun compute(input: BallisticInput, toFile: Boolean): String {
        val result = ballisticComputer.computeValues(input)

        BallisticOutput(input, result).let {
            return if(toFile){
                writeJsonFile(outputPath, it)
                "$SAVED_AT$outputPath"
            } else {
                createJsonString(it)
            }
        }
    }

    @ShellMethod("Changes the output path for results.")
    fun changePath(path: String) =
        if(checkIfDirectoryExists(path)) {
            outputPath = path
            LOG.debug("Output path changed: ($outputPath)")
            PATH_CHANGED_SUCCESS
        } else {
            LOG.debug("Output path could not be changed: ($path)")
            "$PATH_CHANGED_FAILED$path"
        }
}