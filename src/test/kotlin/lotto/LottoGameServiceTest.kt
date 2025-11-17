package lotto

import lotto.domain.Lotto
import lotto.domain.WinningLottoType
import lotto.service.LottoGameService
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class LottoGameServiceTest {
    private val service = LottoGameService()

    @Test
    fun `3개 일치하면 THREE`() {
        val winning = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val purchased = listOf(Lotto(listOf(1, 2, 3, 10, 11, 12)))

        val result = service.matchWinning(winning, purchased, bonusNumber = 45)

        assertEquals(1, result[WinningLottoType.THREE])
    }

    @Test
    fun `4개 일치하면 FOUR`() {
        val winning = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val purchased = listOf(Lotto(listOf(1, 2, 3, 4, 10, 11)))

        val result = service.matchWinning(winning, purchased, bonusNumber = 45)

        assertEquals(1, result[WinningLottoType.FOUR])
    }

    @Test
    fun `5개 일치하면 FIVE`() {
        val winning = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val purchased = listOf(Lotto(listOf(1, 2, 3, 4, 5, 45)))

        val result = service.matchWinning(winning, purchased, bonusNumber = 40)

        assertEquals(1, result[WinningLottoType.FIVE])
    }

    @Test
    fun `5개 일치 + 보너스 번호 일치하면 FIVE_BONUS`() {
        val winning = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val purchased = listOf(Lotto(listOf(1, 2, 3, 4, 5, 45)))

        val result = service.matchWinning(winning, purchased, bonusNumber = 45)

        assertEquals(1, result[WinningLottoType.FIVE_BONUS])
    }

    @Test
    fun `6개 일치하면 SIX`() {
        val winning = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val purchased = listOf(Lotto(listOf(1, 2, 3, 4, 5, 6)))

        val result = service.matchWinning(winning, purchased, bonusNumber = 45)

        assertEquals(1, result[WinningLottoType.SIX])
    }

    @Test
    fun `일치 개수가 3개 미만이면 당첨되지 않음`() {
        val winning = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val purchased = listOf(Lotto(listOf(10, 11, 12, 13, 14, 15)))

        val result = service.matchWinning(winning, purchased, bonusNumber = 45)

        // 모든 타입이 0
        assertEquals(0, result[WinningLottoType.THREE])
        assertEquals(0, result[WinningLottoType.FOUR])
        assertEquals(0, result[WinningLottoType.FIVE])
        assertEquals(0, result[WinningLottoType.FIVE_BONUS])
        assertEquals(0, result[WinningLottoType.SIX])
    }

    @Test
    fun `여러 개 구매하여 여러 타입이 함께 존재하는 경우`() {
        val service = LottoGameService()

        val winning = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = 45

        val purchased = listOf(
            Lotto(listOf(1, 2, 3, 10, 11, 12)),
            Lotto(listOf(1, 2, 3, 4, 20, 21)),
            Lotto(listOf(1, 2, 3, 4, 5, 7)),
            Lotto(listOf(1, 2, 3, 4, 5, 45)),
            Lotto(listOf(10, 20, 30, 40, 41, 42))
        )

        val result = service.matchWinning(winning, purchased, bonus)

        assert(result[WinningLottoType.THREE] == 1)
        assert(result[WinningLottoType.FOUR] == 1)
        assert(result[WinningLottoType.FIVE] == 1)
        assert(result[WinningLottoType.FIVE_BONUS] == 1)
        assert(result[WinningLottoType.SIX] == 0)
    }
}