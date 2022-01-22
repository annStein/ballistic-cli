package com.nuwe.challenge.computerwoman.config

// Shell Messages to User
const val SAVED_AT = "Computations saved at "
const val PATH_CHANGED_SUCCESS = "Path changed successfully."
const val PATH_CHANGED_FAILED = "Invalid path: "

// Shell-Descriptions
const val H_MAX = "Computes the maximum height of the projectile."
const val X_MAX = "Computes the maximum traveled distance."
const val COMPUTE = "Computes the maximum height of the projectile and the maximum traveled distance."
const val COMPUTE_MANUAL = "$COMPUTE Input via args."
const val COMPUTE_FILE = "$COMPUTE Input via JSON-File (path)."
const val CHANGE_PATH = "Changes the output path for results."

// Shell-Help for params
const val V0 = "Initial velocity in m/s."
const val ALPHA = "Launch angle in deg."
const val TO_FILE = "Whether the result should be stored in a file."
const val PATH_TO_FILE = "Path of the file with input values. Expects JSON-file (e.g. {\"v0\"=100, \"alpha\"=45}."
const val OUTPUT_PATH = "Path where the computed results should be stored."

// Error Messages for user
const val ERROR_NO_FILE_OR_DIR = "ERROR: Directory or file does not exist. Please try again with another path."