package com.ohgiraffers.section02.initdestroy.subsection03.xml;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {
    public static void main(String[] args){
        ApplicationContext context = new GenericXmlApplicationContext(
                "section02/initdestroy/subsection03/xml/spring-context.xml");
        Product carpBread = context.getBean("carpBread", Bread.class);
        Product milk = context.getBean("milk", Beverage.class);
        Product water = context.getBean("water", Beverage.class);

        ShoppingCart car1 = context.getBean("cart", ShoppingCart.class);
        car1.addItem(carpBread);
        car1.addItem(milk);

        System.out.println("cart1에 담긴 내용 : " + car1.getItems());

        ShoppingCart car2 = context.getBean("cart", ShoppingCart.class);
        car2.addItem(water);
        System.out.println("cart2에 담긴 내용 : " +  car2.getItems());

        ((GenericXmlApplicationContext) context).close();
    }
}
