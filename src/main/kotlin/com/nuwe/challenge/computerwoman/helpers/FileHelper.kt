package com.nuwe.challenge.computerwoman.helpers

import java.io.File

fun checkIfDirectoryExists(path: String) =
    File(path).let { it.exists() && it.isDirectory }