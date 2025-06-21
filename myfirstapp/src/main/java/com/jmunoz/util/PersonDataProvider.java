package com.jmunoz.util;

import com.jmunoz.entity.Person;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class PersonDataProvider extends AbstractBackEndDataProvider<Person, PersonFilterForLazyLoading> {

    // Necesitamos la data en este servidor backend
    final List<Person> PEOPLE_LIST = DataGenerator.getPersons();

    @Override
    protected Stream<Person> fetchFromBackEnd(Query<Person, PersonFilterForLazyLoading> query) {
        Stream<Person> stream = PEOPLE_LIST.stream();

        // Aplicamos los filtros sobre el stream.
        if (query.getFilter().isPresent()) {
            stream = stream.filter(person -> query.getFilter().get().filter(person));
        }

        // Aplicamos la ordenación.
        if (!query.getSortOrders().isEmpty()) {
            stream = stream.sorted(sortComparator(query.getSortOrders()));
        }

        return stream.skip(query.getOffset()).limit(query.getLimit());
    }

    @Override
    protected int sizeInBackEnd(Query<Person, PersonFilterForLazyLoading> query) {
        return (int) fetchFromBackEnd(query).count();
    }

    private static Comparator<Person> sortComparator(
            List<QuerySortOrder> sortOrders) {
        return sortOrders.stream().map(sortOrder -> {
            Comparator<Person> comparator = personFieldComparator(
                    sortOrder.getSorted());

            if (sortOrder.getDirection() == SortDirection.DESCENDING) {
                comparator = comparator.reversed();
            }

            return comparator;
        }).reduce(Comparator::thenComparing).orElse((p1, p2) -> 0);
    }

    private static Comparator<Person> personFieldComparator(String sorted) {
        if (sorted.equals("gender")) {
            return Comparator.comparing(person -> person.getGender());
        } else if (sorted.equals("age")) {
            return Comparator.comparing(person -> person.getAge());
        } else if (sorted.equals("profession")) {
            return Comparator.comparing(person -> person.getProfession());
        }
        return (p1, p2) -> 0;
    }

    public int getTotalCount() {
        return PEOPLE_LIST.size();
    }
}
