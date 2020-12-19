package lotto.domain;

import lotto.util.StringUtil;
import lotto.util.StringSplitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    private LottoMachine lottoMachine = LottoMachine.instance();
    private LottoNumbers winningLottoNumbers;
    private Set<LottoTicket> lottoTickets = new HashSet<>();

    @BeforeEach
    void init() {
        winningLottoNumbers = new LottoNumbers(StringUtil.textToInt("1,2,3,4,5,6".split(",")));
    }

    @BeforeEach
    void setLottoTicket() {
        lottoTickets.add(lottoMachine.generateManualLottoNumbers(new LottoNumbers(StringSplitter.splitText("1,2,3,4,5,6"))));
        lottoTickets.add(lottoMachine.generateManualLottoNumbers(new LottoNumbers(StringSplitter.splitText("1,2,3,4,5,45"))));
        lottoTickets.add(lottoMachine.generateManualLottoNumbers(new LottoNumbers(StringSplitter.splitText("1,2,3,4,5,7"))));
        lottoTickets.add(lottoMachine.generateManualLottoNumbers(new LottoNumbers(StringSplitter.splitText("1,2,3,4,44,45"))));
        lottoTickets.add(lottoMachine.generateManualLottoNumbers(new LottoNumbers(StringSplitter.splitText("1,2,3,43,44,45"))));
    }

    @ParameterizedTest
    @CsvSource(value = {"FIRST", "SECOND", "THIRD", "FORTH", "FIFTH"})
    @DisplayName("맞은 번호 수에 따른 등수 테스트 ")
    void analyze_lotto_rank(WinningPrize rank) {
        // given
        WinningLotto winningLotto = new WinningLotto.Builder()
                .winningLottoTicket(lottoMachine.generateManualLottoNumbers(winningLottoNumbers))
                .bonusBall(7)
                .build();

        // when
        LottoResult lottoResult = LottoResult.getInstance();
        lottoResult.analyzeLottoRank(lottoTickets, winningLotto);

        // then
        assertThat(lottoResult.getLottoResult().get(rank)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률 계산 테스트")
    void calculate_prize_rate() {
        // given
        WinningLotto winningLotto = new WinningLotto.Builder()
                .winningLottoTicket(lottoMachine.generateManualLottoNumbers(winningLottoNumbers))
                .bonusBall(7)
                .build();

        // when
        LottoResult lottoResult = LottoResult.getInstance();
        lottoResult.analyzeLottoRank(lottoTickets, winningLotto);

        // then
        assertThat(lottoResult.calculatePrizeRate(new LottoMoney(5000))).isEqualTo(BigDecimal.valueOf(406311.00).setScale(2, RoundingMode.FLOOR));
    }
}