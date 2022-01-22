package com.nuwe.challenge.computerwoman.computing

import com.nuwe.challenge.computerwoman.model.BallisticInput
import com.nuwe.challenge.computerwoman.model.BallisticResult
import org.springframework.stereotype.Component
import kotlin.math.sin

@Component
class BallisticComputationsImpl: BallisticComputations {
    override fun calcMaxHeight(v0: Int): Double {
        return (v0 * v0) / (2 * g)
    }

    override fun calcMaxDistance(v0: Int, alpha: Int): Double {
        val alphaRad = alpha * Math.PI / 180
        return 2 * v0 * sin(alphaRad) / (2 * g)
    }

    override fun computeValues(ballisticInput: BallisticInput): BallisticResult {
        return computeValues(ballisticInput.v0, ballisticInput.alpha)
    }

    override fun computeValues(v0: Int, alpha: Int): BallisticResult {
        return BallisticResult(
            calcMaxHeight(v0),
            calcMaxDistance(v0, alpha)
        )
    }
}