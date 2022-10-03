package step02.domain;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.BDDAssertions.then;

class LottoGradeTest {

    @ParameterizedTest
    @DisplayName("rightCount 값에 일치하는 로또 등급이 반환되는지 테스트.")
    @MethodSource("RightCountByLottoGrade")
    void from(LottoGrade lottoGrade, int rightCount) {
        then(LottoGrade.from(rightCount)).isEqualTo(lottoGrade);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 7})
    @DisplayName("rightCount 범위 밖의 값이 주어졌을 때 값 에러가 발생하는지 테스트.")
    void fromFail(int rightCount) {
        assertThatIllegalArgumentException().isThrownBy(() -> LottoGrade.from(rightCount));
    }

    static Stream<Arguments> RightCountByLottoGrade() {
        return Stream.of(
            Arguments.of(LottoGrade.FIRST, 6),
            Arguments.of(LottoGrade.SECOND, 5),
            Arguments.of(LottoGrade.THIRD, 4),
            Arguments.of(LottoGrade.FOURTH, 3),
            Arguments.of(LottoGrade.OTHER_GRADE, 2),
            Arguments.of(LottoGrade.OTHER_GRADE, 1),
            Arguments.of(LottoGrade.OTHER_GRADE, 0)
        );
    }
}