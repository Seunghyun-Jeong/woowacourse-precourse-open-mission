package lotto.domain

class Lotto(private val numbers: List<Int>) {
    init {
        validateLottoNumbers(numbers)
    }

    private fun validateLottoNumbers(numbers: List<Int>) {
        if (numbers.size != 6) {
            throw IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.")
        }
        if (numbers.distinct().size != 6) {
            throw IllegalArgumentException("[ERROR] 중복된 번호가 있습니다.")
        }
        if (numbers.any {it !in 1..45}) {
            throw IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.")
        }
    }

    fun getNumbers(): List<Int> = numbers

    fun printNumbers() {
        println(numbers)
    }
}