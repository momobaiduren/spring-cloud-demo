package com.demo.collections;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangLong on 2019/10/30  1:46 下午
 * @version V1.0
 */
public class MapApi {
    public static void main(String[] args) {
        Map<String, StatementAccountDO> myMap = new HashMap<>();
        StatementAccountDO statementAccountA = new StatementAccountDO();
        statementAccountA.setAccountPassagewayAmount(BigDecimal.TEN);
        myMap.put("A", statementAccountA);
        StatementAccountDO statementAccountB = new StatementAccountDO();
        statementAccountA.setAccountPassagewayAmount(BigDecimal.TEN);
        myMap.put("B", statementAccountA);
        StatementAccountDO statementAccountC = new StatementAccountDO();
        statementAccountA.setAccountPassagewayAmount(BigDecimal.TEN);
        myMap.put("C", statementAccountA);

    }
}
