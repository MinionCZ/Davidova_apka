package com.example.cookieclicker

import java.io.Serializable
import kotlin.math.pow

class CookieData : Serializable {
    var cookiesCounter: Long = 0
    var clickValue: Long = 1
    var cookiesPerSecond: Long = 0
    var clickUpgradeLevel: Long = 1
    var autoClickerUpgradeLevel: Long = 0
    var workersUpgradeLevel: Long = 0
    var bakeriesUpgradeLevel: Long = 0
    private val clickUpgradeStartPrice: Long = 20
    private val priceIncreaseCoef: Double = 2.5
    private val increaseUpgradeValue: Double = 2.0
    private val autoClickerPriceCoef: Double = 5.0 / 4.0
    private val autoclickValue = 1
    private val workerValue = 4
    private val bakeriesValue = 10
    var autoClickerPrice = 500
    var workersPrice = 2000
    var bakeriesPrice = 5000
    private var NewBakery = false
    private val BakeryBonusCoef = 6


    override fun toString(): String {
        var buffer = "{ cookiesCounter: $cookiesCounter, "
        buffer += "clickValue: $clickValue, "
        buffer += "cookiesPerSecond $cookiesPerSecond, "
        buffer += "clickUpgradeLevel: $clickUpgradeLevel, "
        buffer += "autoClickerUpgradeLevel: $autoClickerUpgradeLevel }"
        return buffer
    }

    fun getClickUpgPrice(): Long {
        var upgradeValue =
            clickUpgradeStartPrice * priceIncreaseCoef.pow((clickUpgradeLevel - 1).toDouble())
        return upgradeValue.toLong()
    }

    fun calculateClickValue() {
        clickValue *= 2
    }

    fun updateAutoClicker() {
        cookiesCounter -= autoClickerPrice
        var tmp = autoClickerPrice.toDouble()
        tmp *= autoClickerPriceCoef
        autoClickerPrice = tmp.toInt()
        autoClickerUpgradeLevel++
        cookiesPerSecond += autoclickValue
    }

    fun updateWorkers() {
        cookiesCounter -= workersPrice
        var tmp = workersPrice.toDouble()
        tmp *= autoClickerPriceCoef
        workersPrice = tmp.toInt()
        workersUpgradeLevel++
        cookiesPerSecond += workerValue
    }

    fun updateBakeries() {
        cookiesCounter -= bakeriesPrice
        var tmp = bakeriesPrice.toDouble()
        tmp *= autoClickerPriceCoef
        bakeriesPrice = tmp.toInt()
        bakeriesUpgradeLevel++
        cookiesPerSecond += bakeriesValue
        NewBakery = true
    }

    fun SetBakeryBonus() {
        if (NewBakery) {
            cookiesPerSecond *= BakeryBonusCoef
            clickValue *= BakeryBonusCoef
            var bonusTimer = Runnable {
                cookiesPerSecond /= BakeryBonusCoef
                clickValue /= BakeryBonusCoef
            }
        }
        NewBakery = false
    }
}