package com.springreactive.demo.model.reactiverepository;

import com.springreactive.demo.model.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface EventReactiveRepository extends ReactiveMongoRepository<Event, String> {
    public Event findByName(String name);

    public Flux<Event> findAllByNameLike(String query);

}
