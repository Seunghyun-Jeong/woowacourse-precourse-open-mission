package lotto

import lotto.validator.ManualLottoValidator
import org.assertj.core.api.Assertions.assertThatThrownBy
import kotlin.test.Test

class ManualLottoValidatorTest {
    @Test
    fun `수동 로또 개수가 구매 개수보다 크면 예외`() {
        assertThatThrownBy { ManualLottoValidator(5, 3) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `수동 로또 개수가 음수면 예외`() {
        assertThatThrownBy { ManualLottoValidator(-1, 5) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `정상적인 입력은 예외 발생하지 않는다`() {
        ManualLottoValidator(2, 5)
    }
}