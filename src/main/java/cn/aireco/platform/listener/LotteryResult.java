package cn.aireco.platform.listener;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LotteryResult {

    private String uId;

    private String lottery;

    private Date data;

}
