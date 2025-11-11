package lotto.controller

import lotto.service.LottoGameService
import lotto.view.LottoGameView

class LottoGameController {
    private val service = LottoGameService()
    private val view = LottoGameView()

    fun execute() {
        val purchaseMoney = inputPurchaseMoney()
        val purchaseCount = service.getPurchaseLottoCount(purchaseMoney)
        view.printPurchaseCount(purchaseCount)
        val manualLottoCount = inputManualLottoCount(purchaseCount)
    }

    private fun inputPurchaseMoney(): Int {
        while (true) {
            view.printInputPurchaseMoney()
            val inputPurchaseMoney = readln()
            try {
                val purchaseMoney = inputPurchaseMoney.toInt()
                service.validatePurchaseMoney(purchaseMoney)
                println()
                return purchaseMoney
            } catch (e: NumberFormatException) {
                view.printNumberFormatError()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun inputManualLottoCount(purchaseCount: Int): Int {
        while (true) {
            view.printManualLottoCount(purchaseCount)
            val inputManualLottoCount = readln()
            try {
                val manualLottoCount = inputManualLottoCount.toInt()
                service.validateManualLottoCount(manualLottoCount, purchaseCount)
                println()
                return manualLottoCount
            } catch (e: NumberFormatException) {
                view.printNumberFormatError()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}