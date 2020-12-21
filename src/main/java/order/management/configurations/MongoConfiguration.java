package order.management.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import order.management.repository.MongoRepositoryBase;

@EnableMongoRepositories(basePackageClasses = MongoRepositoryBase.class)
@Configuration
public class MongoConfiguration {}
