package cn.aireco.platform.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {

    private String userId;

}
