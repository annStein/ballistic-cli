package com.nuwe.challenge.computerwoman.cli

import com.nuwe.challenge.computerwoman.computing.BallisticComputationsImpl
import com.nuwe.challenge.computerwoman.config.*
import com.nuwe.challenge.computerwoman.helper.checkIfDirectoryExists
import com.nuwe.challenge.computerwoman.helper.createJsonString
import com.nuwe.challenge.computerwoman.helper.readJsonInput
import com.nuwe.challenge.computerwoman.helper.writeJsonFile
import com.nuwe.challenge.computerwoman.model.BallisticInput
import com.nuwe.challenge.computerwoman.model.BallisticOutput
import org.slf4j.Logger
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption


@ShellComponent
class BallisticShell(
    val LOG: Logger,
    val ballisticComputer: BallisticComputationsImpl) {

    var outputPath: String = "${System.getProperty("user.home")}/Documents/nuwe_challenges/results"

    @ShellMethod(H_MAX)
    fun hMax(@ShellOption(help = V0) v0: Int): Double {
        return ballisticComputer.calcMaxHeight(v0)
    }

    @ShellMethod(X_MAX)
    fun xMax(@ShellOption(help = V0) v0: Int, @ShellOption(help = ALPHA) alpha: Int): Double {
        return ballisticComputer.calcMaxDistance(v0, alpha)
    }

    @ShellMethod(COMPUTE_MANUAL)
    fun computeManual(@ShellOption(help = V0) v0: Int,
                      @ShellOption(help = ALPHA) alpha: Int,
                      @ShellOption(help = TO_FILE) toFile: Boolean): String {
        val input = BallisticInput(v0, alpha)
        return compute(input, toFile)
    }

    @ShellMethod(COMPUTE_FILE)
    fun computeFile(@ShellOption(help = PATH_TO_FILE) pathToFile: String,
                    @ShellOption(help = TO_FILE) toFile: Boolean): String {
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

    @ShellMethod(CHANGE_PATH)
    fun changePath(@ShellOption(help = OUTPUT_PATH) path: String) =
        if(checkIfDirectoryExists(path)) {
            LOG.debug("Output path changes to $path")
            outputPath = path
            PATH_CHANGED_SUCCESS
        } else {
            LOG.debug("Output path could not be changed: ($path)")
            "$PATH_CHANGED_FAILED$path"
        }
}