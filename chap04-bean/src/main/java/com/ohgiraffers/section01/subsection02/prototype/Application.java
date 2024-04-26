package com.ohgiraffers.section01.subsection02.prototype;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

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
    }
}
