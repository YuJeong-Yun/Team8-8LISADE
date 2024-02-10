package com.lisade.togeduck.mapper;

import com.lisade.togeduck.dto.response.RouteRegistrationDto;
import com.lisade.togeduck.entity.Bus;
import com.lisade.togeduck.entity.Festival;
import com.lisade.togeduck.entity.Route;
import com.lisade.togeduck.entity.Station;
import com.lisade.togeduck.entity.enums.RouteStatus;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RouteMapper {

    public static Route toRoute(
        Festival festival,
        Bus bus,
        Station station,
        Integer price,
        Integer distance
    ) {
        // TODO expectedTime, startAt 설정하기
        
        return Route.builder()
            .bus(bus)
            .festival(festival)
            .price(price)
            .distance(distance)
            .station(station)
            .status(RouteStatus.PROGRESS)
            .expectedTime(LocalTime.now())
            .startedAt(LocalDateTime.now())
            .build();
    }

    public static RouteRegistrationDto toRouteRegistrationDto(Route route) {
        return RouteRegistrationDto.builder()
            .routeId(route.getId())
            .festivalId(route.getFestival().getId())
            .build();
    }
}
