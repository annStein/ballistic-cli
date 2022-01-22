package com.nuwe.challenge.computerwoman.cli.exception

data class AppException(
    override val cause: Throwable?,
    override val message: String
) : Exception()