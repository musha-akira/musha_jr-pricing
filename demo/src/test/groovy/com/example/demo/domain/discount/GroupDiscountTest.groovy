package com.example.demo.domain.discount

import com.example.demo.component.domain.Date
import com.example.demo.component.domain.Number
import com.example.demo.component.domain.PassengerNumber
import com.example.demo.component.domain.discount.GroupDiscount
import spock.lang.Specification
import spock.lang.Unroll

class GroupDiscountTest extends Specification {
    @Unroll
    def "時期#month月#day日の割引率#result"() {
        given:
        def group = new GroupDiscount(
                new PassengerNumber(new Number(8), new Number(1)),//8人以上いればなんでも良い
                new Date(2020, month, day),
        )

        when:
        def groupDiscountRate = group.getDiscountRate().getValue()

        then:
        groupDiscountRate == result as double

        where:
        month | day || result
        12    | 20  || 0.15
        12    | 21  || 0.1
        12    | 22  || 0.1
        1     | 9   || 0.1
        1     | 10  || 0.1
        1     | 11  || 0.15
    }

    @Unroll
    def "大人#adultNumber人で子供#childNumber人の時の往復割引が適用できるかどうかの判定結果#result"() {
        given:
        def group = new GroupDiscount(
                new PassengerNumber(new Number(adultNumber), new Number(childNumber)),
                new Date(2020, 8, 10),//日にち関係ない
        )

        when:
        def canGroupDiscount = group.canGroupDiscount(new PassengerNumber(new Number(adultNumber), new Number(childNumber)))

        then:
        canGroupDiscount == result

        where:
        adultNumber | childNumber || result
        0           | 8           || true
        1           | 7           || true
        7           | 1           || true
        8           | 0           || true

    }

    @Unroll
    def "大人#adultNumber人で子供#childNumber人の時の無賃人割引が適用できるかどうかの判定結果#result"() {
        given:
        def group = new GroupDiscount(
                new PassengerNumber(new Number(adultNumber), new Number(childNumber)),
                new Date(2020, 8, 10),//日にち関係ない
        )

        when:
        def canFreePassengerGroupDiscount = group.canFreePassengerGroupDiscount(new PassengerNumber(new Number(adultNumber), new Number(childNumber)))

        then:
        canFreePassengerGroupDiscount == result

        where:
        adultNumber | childNumber || result
        29          | 1           || false
        30          | 0           || false
        30          | 1           || true
        50          | 0           || true
    }

    @Unroll
    def "大人#adultNumber人で子供#childNumber人の時の無賃扱人数#result"() {
        given:
        def group = new GroupDiscount(
                new PassengerNumber(new Number(adultNumber), new Number(childNumber)),
                new Date(2020, 8, 10),//日にち関係ない
        )

        when:
        def freeNumber = group.createFreeNumber(new PassengerNumber(new Number(adultNumber), new Number(childNumber)))

        then:
        freeNumber == new Number(result)

        where:
        adultNumber | childNumber || result
        30          | 1           || 1
        50          | 0           || 1
        50          | 1           || 2
        101         | 0           || 3
    }

    @Unroll
    def "大人#adultNumber人で子供#childNumber人の時の無賃扱人を除いた大人人数#result"() {
        given:
        def group = new GroupDiscount(
                new PassengerNumber(new Number(adultNumber), new Number(childNumber)),
                new Date(2020, 8, 10),//日にち関係ない
        )

        when:
        def calculateAdultNumber = group.getFreePassengerExcludedAdultNumber()

        then:
        calculateAdultNumber == new Number(result)

        where:
        adultNumber | childNumber || result
        0           | 31          || 0
        50          | 1           || 48
        1           | 50          || 0
        30          | 0           || 30
        30          | 1           || 29
        50          | 0           || 49
    }

    @Unroll
    def "大人0人で子供#childNumber人の時の無賃扱人を除いた大人人数が0になるかどうかの判定テスト"() {
        given:
        def group = new GroupDiscount(
                new PassengerNumber(new Number(0), new Number(childNumber)),
                new Date(2020, 8, 10),//日にち関係ない
        )

        when:
        def calculateAdultNumber = group.getFreePassengerExcludedAdultNumber()

        then:
        calculateAdultNumber == new Number(result)

        where:
        adultNumber | childNumber || result
        0           | 31          || 0
        0           | 51          || 0
    }

    @Unroll
    def "大人人数が無賃扱人以下の場合大人人数が0になるかどうかの判定テスト"() {
        given:
        def group = new GroupDiscount(
                new PassengerNumber(new Number(adultNumber), new Number(childNumber)),
                new Date(2020, 8, 10),//日にち関係ない
        )

        when:
        def calculateAdultNumber = group.getFreePassengerExcludedAdultNumber()

        then:
        calculateAdultNumber == new Number(result)

        where:
        adultNumber | childNumber || result
        1           | 30          || 0
        1           | 51          || 0
    }

    @Unroll
    def "大人人数が無賃扱人以下の場合大人が優先して無賃扱い人が除かれているかどうか確認テスト"() {
        given:
        def group = new GroupDiscount(
                new PassengerNumber(new Number(adultNumber), new Number(childNumber)),
                new Date(2020, 8, 10),//日にち関係ない
        )

        when:
        def calculateChildNumber = group.getFreePassengerExcludedChildNumber()

        then:
        calculateChildNumber == new Number(result)

        where:
        adultNumber | childNumber || result
        1           | 30          || 30
        1           | 51          || 50
    }

    @Unroll
    def "大人#adultNumber人で子供#childNumber人で合計8人に満たない場合の例外メッセージは#result"() {
        when:
        new GroupDiscount(
                new PassengerNumber(new Number(adultNumber), new Number(childNumber)),
                new Date(2020, 8, 10),//日にち関係ない
        )

        then:
        Exception e = thrown()
        e.message == result

        where:
        adultNumber | childNumber || result
        7           | 0           || "団体割引を適用できません。"
        4           | 3           || "団体割引を適用できません。"

    }

}