package lotto.service

import lotto.validate.MoneyValidator
import kotlin.NumberFormatException

class LottoGameService {
    companion object {
        const val LOTTO_PRICE = 1000
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