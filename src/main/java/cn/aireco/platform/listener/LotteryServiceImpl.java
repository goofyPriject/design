package cn.aireco.platform.listener;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class LotteryServiceImpl extends LotteryService {

    private MinibusTargetService minibusTargetService = new MinibusTargetService();

    @Override
    public LotteryResult doDraw(String uId) {
        log.info("LotteryServiceImpl:{}",uId);
        // 摇号
        String lottery = minibusTargetService.lottery(uId);
        // 结果
        return new LotteryResult(uId, lottery, new Date());
    }
}
