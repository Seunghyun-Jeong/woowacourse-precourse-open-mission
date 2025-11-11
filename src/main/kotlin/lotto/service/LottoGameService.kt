package lotto.service

import lotto.validator.MoneyValidator
import lotto.validator.ManualLottoValidator

class LottoGameService {
    companion object {
        private const val LOTTO_PRICE = 1000
    }

    fun validatePurchaseMoney(purchaseMoney: Int) {
        MoneyValidator(purchaseMoney)
    }

    fun validateManualLottoCount(manualLottoCount: Int, purchaseCount: Int) {
        ManualLottoValidator(manualLottoCount, purchaseCount)
    }

    fun getPurchaseLottoCount(purchaseMoney: Int): Int {
        val purchaseCount = purchaseMoney / LOTTO_PRICE
        return purchaseCount
    }
}