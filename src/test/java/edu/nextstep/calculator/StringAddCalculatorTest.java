package edu.nextstep.calculator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@DisplayName("StringAddCalculator: 문자열 덧셈 계산기 기능 통합 테스트. 코스 본문의 TestCase 소스 코드를 그대로 사용함.")
public class StringAddCalculatorTest {
	@ParameterizedTest
	@NullAndEmptySource
	public void splitAndSum_null_또는_빈문자(String input) {
		int result = StringAddCalculator.splitAndSum(input);
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void splitAndSum_숫자하나() throws Exception {
		int result = StringAddCalculator.splitAndSum("1");
		assertThat(result).isEqualTo(1);
	}

	@Test
	public void splitAndSum_쉼표구분자() throws Exception {
		int result = StringAddCalculator.splitAndSum("1,2");
		assertThat(result).isEqualTo(3);
	}

	@Test
	public void splitAndSum_쉼표_또는_콜론_구분자() throws Exception {
		int result = StringAddCalculator.splitAndSum("1,2:3");
		assertThat(result).isEqualTo(6);
	}

	@Test
	public void splitAndSum_custom_구분자() throws Exception {
		int result = StringAddCalculator.splitAndSum("//;\n1;2;3");
		assertThat(result).isEqualTo(6);
	}

	@Test
	public void splitAndSum_negative() throws Exception {
		assertThatThrownBy(() -> StringAddCalculator.splitAndSum("-1,2,3"))
			.isInstanceOf(RuntimeException.class);
	}
}