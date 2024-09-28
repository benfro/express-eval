package net.benfro.expreval.parser2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractGoOnVoter implements GoOnVoter {

    boolean vote() {
        return false;
    }
}
