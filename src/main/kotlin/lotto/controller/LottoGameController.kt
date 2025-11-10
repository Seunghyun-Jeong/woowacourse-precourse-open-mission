package lotto.controller

import lotto.service.LottoGameService
import lotto.view.LottoGameView

class LottoGameController {
    private val service = LottoGameService()
    private val view = LottoGameView()

    fun execute() {
        val purchaseMoney = service.inputPurchaseMoney()
        val purchaseCount = purchaseMoney / LottoGameService.LOTTO_PRICE
        view.printPurchaseCount(purchaseCount)
    }
}