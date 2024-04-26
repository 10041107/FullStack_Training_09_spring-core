package com.ohgiraffers.section01.subsection01.scope;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    /*
    * bean scope란 스프링 빈이 생성될 때 생성되는 인스턴스의 범위를 의미한다. 스프링에서는 다양한 bean scope를 제공한다.
    * 1. singleton      하나의 인스턴스만 생성하고, 모든 빈이 해당 인스턴스를 공유하며 사용한다.
    * 2. prototype      매번 새로운 인스턴스를 생성한다.
    * 3. reqeust        http 요청을 처리할 때마다 새로운 인스턴스를 생성하고, 요청 처리가 끝나면 인스턴스를 폐기한다. 웹 애플리케이션 컨텍스트에만 해당된다.
    * 4. session        http 세션 당 하나의 인스턴스를 생성하고, 세션이 종료되면 인스턴스를 폐기한다. 웹 애플리케이션 컨텍스트에만 해당된다.
    * 5. globalSession  전역 세션 당 하나의 인스턴스를 생성하고, 전역 세션이 종료되면 인스턴스를 폐기한다. 포털 애플리케이션 컨텍스트에만 해당된다.
    * */
    public static void main(String[] args){
        /* 빈 설정 파일을 기반으로 IoC 컨테이너 생성 */
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames();
        for (String bean: beanNames) {
            System.out.println("BeanName : " + bean);
        }

        /* 붕어빵, 딸기우유, 지리산 암반수 등의 빈 객체를 반환 받는다. */
        Product carpBread = context.getBean("carpBread", Bread.class);
        Product milk = context.getBean("milk", Beverage.class);
        Product water = context.getBean("water", Beverage.class);

        /* 첫 번째 손님이 쇼핑 카트를 꺼낸다. */
        ShoppingCart cart = context.getBean("cart", ShoppingCart.class);
        cart.addItem(carpBread);
        cart.addItem(milk);

        /* 붕어빵과 딸기우유가 담겨있다. */
        System.out.println("cart의 담긴 내용 : " + cart.getItems());

        /* 두 번째 손님이 쇼핑 카트를 꺼낸다. */
        ShoppingCart cart2 = context.getBean("cart", ShoppingCart.class);
        cart2.addItem(water);

        /* 붕어빵과 딸기우유와 지리산암반수가 담겨있다. */
        System.out.println("cart2의 담긴 내용 : " +  cart2.getItems());

        /* 두 카드의 hashcode를 출력해보면 동일한 것을 볼 수 있다. */
        System.out.println("cart의 hashCode : " + cart.hashCode());
        System.out.println("cart2의 hashCode : " + cart2.hashCode());

        /* Spring Framework에서 Bean의 기본 스코프는 singleton이다.
         * singleton 스코프를 갖는 Bean은 애플리케이션 내에서 유일한 인스턴스를 갖는다.
         * 이 예제에서 손님 두 명이 각각 쇼핑 카트를 이용해 상품을 담았을 때
         * singleton으로 관리되는 cart는 두 손님이 동일한 카트에 물건을 담게 된다.
         */

    }
}
