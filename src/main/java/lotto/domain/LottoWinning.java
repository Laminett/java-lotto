package lotto.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoWinning {
    private final Map<LottoRank, Integer> winning;

    public LottoWinning() {
        this.winning = Arrays.stream(LottoRank.values())
                .collect(Collectors.toMap(Function.identity(), value -> 0, (x, y) -> y, LinkedHashMap::new));
    }

    public double sumWinningMoney() {
        double money = 0;
        for (Map.Entry<LottoRank, Integer> entry : winning.entrySet()) {
            LottoRank rank = entry.getKey();
            money += rank.multiply(entry.getValue());
        }
        return money;
    }

    public void put(LottoRank prize) {
        winning.merge(prize, 1, Integer::sum);
    }

    public Integer count(LottoRank prize) {
        return winning.get(prize);
    }
}