package lotto

import lotto.validator.MoneyValidator
import org.assertj.core.api.Assertions.assertThatThrownBy
import kotlin.test.Test

class MoneyValidatorTest {
    @Test
    fun `1000원 단위가 아니면 예외`() {
        assertThatThrownBy { MoneyValidator(2500) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("[ERROR] 1,000원 단위로 입력해 주세요.")
    }

    @Test
    fun `정상적인 금액이면 예외가 발생하지 않는다`() {
        MoneyValidator(5000) // 예외 없음
    }
}