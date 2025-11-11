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
    }

    fun inputPurchaseMoney(): Int {
        while (true) {
            view.printInputPurchaseMoney()
            val input = readln()
            try {
                val purchaseMoney = input.toInt()
                service.validateMoney(purchaseMoney)
                println()
                return purchaseMoney
            } catch (e: NumberFormatException) {
                view.printNumberFormatError()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}