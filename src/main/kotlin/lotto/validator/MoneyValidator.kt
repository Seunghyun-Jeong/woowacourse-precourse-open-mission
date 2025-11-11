package lotto.validator

class MoneyValidator(private val purchaseMoney: Int) {
    companion object {
        private const val LOTTO_PRICE = 1000
    }

    init {
        if (purchaseMoney % LOTTO_PRICE != 0) {
            throw IllegalArgumentException("1,000원 단위로 입력해 주세요.")
        }
    }
}