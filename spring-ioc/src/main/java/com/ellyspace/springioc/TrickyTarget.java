package com.ellyspace.springioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class TrickyTarget {

    Foo fooOne;
    Foo fooTwo;
    Bar bar;

    public TrickyTarget() {
        System.err.println("Target.constructor()");
    }

    public TrickyTarget(Foo fooOne) {
        System.err.println("Target(Foo) 호출");
    }

    public TrickyTarget(Foo fooOne, Bar bar) {
        System.err.println("Target(Foo, Bar) 호출");
    }

    @Autowired
    public void setFooOne(Foo fooOne) {
        System.err.println("프로퍼티 fooOne 설정");
    }

    @Autowired
    public void setFooTwo(Foo fooTwo) {
        System.err.println("프로퍼티 fooTwo 설정");
    }

    @Autowired
    public void setBar(Bar bar) {
        System.err.println("프로퍼티 bar 설정");
    }
}
