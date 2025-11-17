package lotto.service

import camp.nextstep.edu.missionutils.Randoms
import lotto.domain.Lotto
import lotto.domain.WinningLottoType
import lotto.validator.MoneyValidator
import lotto.validator.ManualLottoValidator

class LottoGameService {
    companion object {
        private const val LOTTO_PRICE = 1000
        private const val MIN_NUMBER = 1
        private const val MAX_NUMBER = 45
        private const val LOTTO_NUMBER_COUNT = 6
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

    fun getAutoLottoNumbers(autoLottoCount: Int): List<Lotto> {
        val autoLottoNumbers = mutableListOf<Lotto>()

        repeat(autoLottoCount) {
            val autoLottoNumber = Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, LOTTO_NUMBER_COUNT).toList().sorted()
            autoLottoNumbers.add(Lotto(autoLottoNumber))
        }

        return autoLottoNumbers
    }

    fun getWinningLottoNumbers(): Lotto {
//        val winningLottoNumbers = Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, LOTTO_NUMBER_COUNT).toList().sorted()
//        return Lotto(winningLottoNumbers)
        return Lotto(listOf(1,2,3,4,5,6))
    }

    fun getWinningBonusNumber(winningLottoNumbers: Lotto): Int {
//        while (true) {
//            val bonusNumber = Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER)
//            if (bonusNumber !in winningLottoNumbers.getNumbers()) {
//                return bonusNumber
//            }
//        }
        return 7
    }

    fun matchWinning(winningLotto: Lotto, purchasedLottos: List<Lotto>, bonusNumber: Int): MutableMap<WinningLottoType, Int> {

        val resultMap = WinningLottoType.values().associateWith { 0 }.toMutableMap()

        for (lotto in purchasedLottos) {
            val matchCount = winningLotto.matchCount(lotto)
            val matchBonus = lotto.containsBonusNumber(bonusNumber)

            val type = WinningLottoType.getWinningLottoTypeByMatch(matchCount, matchBonus)
            if (type != null) {
                resultMap[type] = resultMap[type]!! + 1
            }
        }

        return resultMap
    }
}