package cn.aireco.platform.util;

public class SnowflakeIdWorker {
    private static final long START_STMP = 1480166465631L;
    private static final long SEQUENCE_BIT = 12;
    private static final long MACHINE_BIT = 5;
    private static final long DATACENTER_BIT = 5;
    private static final long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private static final long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private static final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    private static final long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private static final long TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;
    private long datacenterId;
    private long machineId;
    private long sequence = 0L;
    private long lastStmp = -1L;
    public SnowflakeIdWorker(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
        if (currStmp == lastStmp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            sequence = 0L;
        }
        lastStmp = currStmp;
        return (currStmp - START_STMP) << TIMESTAMP_LEFT 
                | datacenterId << DATACENTER_LEFT 
                | machineId << MACHINE_LEFT 
                | sequence;
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }
}

//这是一个 Snowflake ID 生成器的 Java 实现。具体来说，Snowflake ID 是一种可以生成唯一 ID 的算法，它主要由 64 位数字构成。该算法是 Twitter 公司为了解决分布式系统下全局唯一 ID 的问题而设计的。
//
//该类中，有以下几个常量：
//
//- `START_STMP`：表示起始的时间戳，这里固定为一个长整型数值 1480166465631L，即该算法的诞生时间。
//- `SEQUENCE_BIT`：表示序列号占用的位数，这里设置为 12 位。
//- `MACHINE_BIT`：表示机器 ID 占用的位数，这里设置为 5 位。
//- `DATACENTER_BIT`：表示数据中心 ID 占用的位数，这里设置为 5 位。
//- `MAX_DATACENTER_NUM`：表示数据中心 ID 的最大值，这里计算得到 31。
//- `MAX_MACHINE_NUM`：表示机器 ID 的最大值，这里计算得到 31。
//- `MAX_SEQUENCE`：表示序列号的最大值，这里计算得到 4095，即二进制下 12 个 1 的最大值。
//
//除此之外，还有一些常量表示各部分所占位数和左移位数：
//
//- `MACHINE_LEFT`：表示机器 ID 左移的位数，值为序列号占用的位数 12。
//- `DATACENTER_LEFT`：表示数据中心 ID 左移的位数，值为机器 ID 占用的位数 5，加上序列号占用的位数 12，即总共占用 17 位。
//- `TIMESTAMP_LEFT`：表示时间戳部分左移的位数，值为数据中心 ID 占用的位数 5，加上机器 ID 占用的位数 5，再加上序列号占用的位数 12，共占用 22 位。
//
//在生成器的构造函数中，会传入数据中心 ID 和机器 ID，这两个参数不可为负数且不能大于它们所占位数的最大值。
//
//在 `nextId` 方法中，该算法会首先获取当前时间戳，如果小于上一次生成的时间戳则抛出异常，防止出现时间戳回拨的情况。然后会判断当前时间戳是否跟上一次生成的时间戳相等，如果相等就将序列号加1，以此保证生成的 ID 具有递增的特点和唯一性。若此时序列号已经达到最大值，则将下一毫秒的时间戳作为生成 ID 的开始时间戳。
//
//最后，ID 的生成方式由时间戳部分、数据中心部分、机器部分和序列号部分组成，依次按照预先定义的位移顺序进行左移和或运算就得到了生成的唯一 ID。