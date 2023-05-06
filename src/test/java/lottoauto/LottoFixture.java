package lottoauto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lottoauto.model.Lotto;
import lottoauto.model.Lottos;

public class LottoFixture {

    public static Lotto lottoFixture(Integer... numbers) {
        return new Lotto(new ArrayList<>(Arrays.asList(numbers)));
    }

    public static Lottos lottosFixture(Lotto... lottos) {
        return new Lottos(new ArrayList<>(Arrays.asList(lottos)));
    }

    public static Lottos lottosFixture(Integer... numbers) {
        return new Lottos(new ArrayList<>(Arrays.asList(lottoFixture(numbers))));
    }

    public static List<Integer> intListFixture(int... numbers) {
        return Arrays.stream(numbers).boxed().collect(Collectors.toList());
    }
}
