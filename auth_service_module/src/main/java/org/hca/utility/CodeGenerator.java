package org.hca.utility;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public class CodeGenerator {
    public static String getActivationCode(){
        String uuid = UUID.randomUUID().toString();
        return Arrays.stream(uuid.split("-")).map(segment -> String.valueOf(segment.charAt(0)))
                .collect(Collectors.joining());
    }
}