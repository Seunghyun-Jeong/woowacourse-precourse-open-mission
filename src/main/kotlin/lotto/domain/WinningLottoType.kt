package lotto.domain

enum class WinningLottoType(val comment: String, val reward: Int, val matchCount: Int, val bonusMatch: Boolean, val multipleReward: Boolean) {
    THREE("3개 일치 (5,000원)", 5_000, 3, false, true),
    FOUR("4개 일치 (50,000원)", 50_000, 4, false, true),
    FIVE("5개 일치 (1,500,000원)", 1_500_000, 5, false, false),
    FIVE_BONUS("5개 + 보너스 번호 일치 (30,000,000원)", 30_000_000, 5, true, false),
    SIX("6개 일치 (2,000,000,000원)", 2_000_000_000, 6, false, false);

    companion object {
        fun getWinningLottoTypeByMatch(matchCount: Int, matchBonus: Boolean): WinningLottoType? {
            return values().firstOrNull { type ->
                type.matchCount == matchCount &&
                        (type.bonusMatch == matchBonus) // 보너스 일치 여부 정확히 확인
            } ?: values().firstOrNull { type ->
                type.matchCount == matchCount && !type.bonusMatch
            }
        }
    }
}