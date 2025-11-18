package lotto.controller

import lotto.domain.Lotto
import lotto.service.LottoGameService
import lotto.view.LottoGameView
import camp.nextstep.edu.missionutils.Console

class LottoGameController {
    private val service = LottoGameService()
    private val view = LottoGameView()

    fun execute() {
        val purchaseMoney = inputPurchaseMoney()
        val purchaseCount = service.getPurchaseLottoCount(purchaseMoney)
        view.printPurchaseCount(purchaseCount)

        val manualLottoCount = inputManualLottoCount(purchaseCount)
        val manualLottoNumbers = inputManualLottoNumbers(manualLottoCount)

        val autoLottoCount = purchaseCount - manualLottoCount
        val autoLottoNumbers = service.getAutoLottoNumbers(autoLottoCount)
        view.printAutoLottoNumbers(autoLottoNumbers)

        val allLottoNumbers = manualLottoNumbers + autoLottoNumbers
        view.printAllLottoNumbers(allLottoNumbers)

        val winningLottoNumbers = service.getWinningLottoNumbers()
        val bonusNumber = service.getWinningBonusNumber(winningLottoNumbers)
        view.printWinningLottoNumbersAndBonusNumber(winningLottoNumbers, bonusNumber)

        val resultMap = service.matchWinning(winningLottoNumbers, allLottoNumbers, bonusNumber)

        view.printWinningResult(resultMap, purchaseMoney)
    }

    private fun inputPurchaseMoney(): Int {
        while (true) {
            view.printInputPurchaseMoney()
            val inputPurchaseMoney = Console.readLine()
            try {
                val purchaseMoney = inputPurchaseMoney.toInt()
                service.validatePurchaseMoney(purchaseMoney)
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
            val inputManualLottoCount = Console.readLine()
            try {
                val manualLottoCount = inputManualLottoCount.toInt()
                service.validateManualLottoCount(manualLottoCount, purchaseCount)
                return manualLottoCount
            } catch (e: NumberFormatException) {
                view.printNumberFormatError()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun inputManualLottoNumbers(manualLottoCount: Int): List<Lotto> {
        val manualLottos = mutableListOf<Lotto>()

        if (manualLottoCount == 0) {
            return manualLottos
        }

        view.printManualLottoNumbers()

        repeat(manualLottoCount) {
            while (true) {
                try {
                    val inputManualLottoNumbers = Console.readLine()
                    val manualLottoNumbers = inputManualLottoNumbers.split(",").map {it.trim().toInt()}.sorted()

                    val manualLotto = Lotto(manualLottoNumbers)
                    manualLottos.add(manualLotto)
                    break
                } catch (e: NumberFormatException) {
                    view.printNumberFormatError()
                } catch (e: IllegalArgumentException) {
                    println(e.message)
                }
            }
        }

        return manualLottos
    }
}