package lotto.service

import lotto.validator.MoneyValidator

class LottoGameService {
    companion object {
        private const val LOTTO_PRICE = 1000
    }

    fun validateMoney(purchaseMoney: Int) {
        MoneyValidator(purchaseMoney)
    }

    fun getPurchaseLottoCount(purchaseMoney: Int): Int {
        val purchaseCount = purchaseMoney / LOTTO_PRICE
        return purchaseCount
    }
}