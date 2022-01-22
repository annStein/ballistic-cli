package com.nuwe.challenge.computerwoman.computing

import com.nuwe.challenge.computerwoman.model.BallisticInput
import com.nuwe.challenge.computerwoman.model.BallisticResult

interface BallisticComputations {
    /**
     * Computes the maximum height of the projectile (h_max).
     */
    fun calcMaxHeight(v0: Int): Double

    /**
     * Computes the maximum traveled distance (x_max).
     */
    fun calcMaxDistance(v0: Int, alpha: Int): Double

    /**
     * Computes the maximum height of the projectile (h_max) and the maximum traveled distance (x_max).
     */
    fun computeValues(ballisticInput: BallisticInput): BallisticResult
    fun computeValues(v0: Int, alpha: Int): BallisticResult
}