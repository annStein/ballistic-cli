package com.nuwe.challenge.computerwoman.helper

import java.io.File

fun checkIfDirectoryExists(path: String) =
    File(path).let { it.exists() && it.isDirectory }