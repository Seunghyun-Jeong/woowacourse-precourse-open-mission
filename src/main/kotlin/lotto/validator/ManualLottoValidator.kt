package lotto.validator

class ManualLottoValidator(manualLottoCount: Int, purchaseCount: Int) {
    init {
        if (manualLottoCount < 0 || manualLottoCount > purchaseCount) {
            throw IllegalArgumentException("[ERROR] 0부터 ${purchaseCount} 사이의 숫자로 입력해 주세요.")
        }
    }
}