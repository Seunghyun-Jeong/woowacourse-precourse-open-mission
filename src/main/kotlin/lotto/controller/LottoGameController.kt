package lotto.controller

import lotto.service.LottoGameService
import lotto.validate.MoneyValidator
import lotto.view.LottoGameView

class LottoGameController {
    private val service = LottoGameService()
    private val view = LottoGameView()

    fun execute() {
        val purchaseMoney = inputPurchaseMoney()
    }

    fun inputPurchaseMoney(): Int {
        while (true) {
            println("구입금액을 입력해 주세요.")
            val input = readln()
            try {
                val purchaseMoney = input?.toInt() ?: throw NumberFormatException()
                MoneyValidator(purchaseMoney)
                println()
                return purchaseMoney
            } catch (e: NumberFormatException) {
                println("[ERROR] 숫자만 입력해 주세요.")
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

}