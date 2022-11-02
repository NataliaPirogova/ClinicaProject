//package com.example.clinicaproject.spec;
//
//import com.example.clinicaproject.model.Volunteer;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.util.function.Predicate;
//
//public class VolunteerSpecification {
//        public static Specification<Volunteer> volunteer(final String lastName) {
//            return new Specification<Volunteer>() {
//                @Override
//                public Predicate toPredicate(Root<Volunteer> root,
//                                             CriteriaQuery<?> criteriaQuery,
//                                             CriteriaBuilder criteriaBuilder) {
//                    return criteriaBuilder.equal(root.get("owner").get("lastName"), lastName);
//                }
//            };
//        }
//}
