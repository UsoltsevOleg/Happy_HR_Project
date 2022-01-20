package com.example.happy.hr.config;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.dialect.PostgreSQL94Dialect;

/*  Поддержку строковых массивов PostgreSQL
    Чтобы заработал тест. При создании проекта тест на поднятие спринг контекста генерируется автоматически
    В ProjectCard строковый массив маппится на массив varchar в бд
    В тесте не postgresql используется, а h2. В h2 нет массивов. Чтобы h2 воспринимала массивы. И не выкидывала ошибки
  */

public class PostgreSQL94CustomDialect extends PostgreSQL94Dialect {
    public PostgreSQL94CustomDialect() {
        super();
        this.registerHibernateType(2003, StringArrayType.class.getName());
    }
}
