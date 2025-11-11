package lotto.view

class LottoGameView {
    companion object {
        private const val ERROR_MESSEGE = "[ERROR]"
    }

    fun printInputPurchaseMoney() {
        println("구입금액을 입력해 주세요.")
    }

    fun printNumberFormatError() {
        println("$ERROR_MESSEGE 숫자만 입력해 주세요.")
    }

    fun printPurchaseCount(purchaseCount: Int) {
        println("${purchaseCount}개를 구매합니다.")
    }

    fun printManualLottoCount(purchaseCount: Int) {
        println("수동으로 몇 개를 구매하시겠습니까? (0~${purchaseCount})")
    }
}