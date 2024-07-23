package cn.aireco.platform.function.compare;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Compare {

    public static void main(String[] args) {
        ComUser comUser = new ComUser(1L, 1);
        ComUser comUser1 = new ComUser(1L, 2);
        ComUser comUser2 = new ComUser(2L, 1);
        ComUser comUser3 = new ComUser(2L, 2);
        List<ComUser> comUserList = Arrays.asList(comUser, comUser1, comUser3, comUser2);
        Comparator<ComUser> comUserComparator = Comparator.comparing(ComUser::getId).reversed().thenComparingInt(ComUser::getAge).reversed();
        comUserList
                .stream()
                .sorted(comUserComparator)
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }

}
