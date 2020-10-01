package com.example.demo.domain

import com.example.demo.component.domain.Date
import spock.lang.Specification
import spock.lang.Unroll

class DateTest extends Specification {

    @Unroll
    def "#month月の最後の日は#result"() {
        when:
        def date = new Date(2001, month, 1)

        then:
        date.getLastDay(2001, month) == result

        where:
        month || result
        1     || 31
        2     || 28
        3     || 31
        4     || 30
        5     || 31
        6     || 30
        7     || 31
        8     || 31
        9     || 30
        10    || 31
        11    || 30
        12    || 31

    }

    @Unroll
    def "月#monthの時の例外#result"() {
        when:
        new Date(2000, month, 1)

        then:
        Exception e = thrown()
        e.message == result

        where:
        month || result
        13    || "日付は存在しません。"
        0     || "日付は存在しません。"

    }

    @Unroll
    def "月#monthを入力した時の月#result"() {
        when:
        def date = new Date(2000, month, 15)

        then:
        date.month == result

        where:
        month || result
        1     || 1
        4     || 4
        12    || 12

    }

    @Unroll
    def "大の月#month月#day日の時の例外#result"() {
        when:
        new Date(2000, month, day)

        then:
        Exception e = thrown()
        e.message == result

        where:
        month | day || result
        1     | 0   || "日付は存在しません。"
        1     | 32  || "日付は存在しません。"
//        3     | 32  || "日付は存在しません。"
//        5     | 32  || "日付は存在しません。"
//        7     | 32  || "日付は存在しません。"
//        8     | 32  || "日付は存在しません。"
//        10    | 32  || "日付は存在しません。"
//        12    | 32  || "日付は存在しません。"
    }

    @Unroll
    def "小の月#month月#day日の時の例外#result"() {
        when:
        new Date(2000, month, day)

        then:
        Exception e = thrown()
        e.message == result

        where:
        month | day || result
        4     | 0   || "日付は存在しません。"
        4     | 31  || "日付は存在しません。"
    }

    @Unroll
    def "大の月#month月#day日を入力した時の日#result"() {
        when:
        def date = new Date(2000, month, day)

        then:
        date.day == result

        where:
        month | day || result
        1     | 1   || 1
        1     | 15  || 15
        1     | 31  || 31

    }

    @Unroll
    def "閏年に#month月#day日を入力した時の日は#result"() {
        when:
        def date = new Date(2020, month, day)

        then:
        date.day == result

        where:
        month | day || result
        2     | 29  || 29

    }

    @Unroll
    def "#month月#day日を入力した時の年末年始期間かどうかの判定結果は#result"() {
        when:
        def date = new Date(2000, month, day)

        then:
        date.isNewYear() == result

        where:
        month | day || result
        12    | 20  || false
        12    | 21  || true
        12    | 31  || true
        1     | 1   || true
        1     | 10  || true
        1     | 11  || false

    }
}
