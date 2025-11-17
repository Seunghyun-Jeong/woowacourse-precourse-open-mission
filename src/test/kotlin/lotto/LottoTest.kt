package lotto

import lotto.domain.Lotto
import org.assertj.core.api.Assertions.assertThatThrownBy
import kotlin.test.Test

class LottoTest {
    @Test
    fun `로또 번호가 6개보다 많으면 예외가 발생한다`() {
        assertThatThrownBy { Lotto(listOf(1, 2, 3, 4, 5, 6, 7)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `로또 번호에 중복 숫자가 있으면 예외가 발생한다`() {
        assertThatThrownBy { Lotto(listOf(1, 2, 3, 4, 5, 5)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `로또 번호가 1부터 45 범위를 벗어나면 예외 발생`() {
        assertThatThrownBy { Lotto(listOf(0, 2, 3, 4, 5, 6)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `로또 번호가 6개 미만이면 예외 발생`() {
        assertThatThrownBy { Lotto(listOf(1, 2, 3, 4, 5)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}