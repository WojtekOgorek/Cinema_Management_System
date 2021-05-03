package ogorek.wojciech.infrastructure;

import ogorek.wojciech.infrastructure.web.routing.*;
import ogorek.wojciech.service.services.cinema.*;
import ogorek.wojciech.service.services.security.UserService;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import spark.Route;

import static spark.Spark.initExceptionHandler;
import static spark.Spark.port;

public class App {

    public static void createTables(Jdbi jdbi) {
        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        System.out.println("********************");
        final String CITIES_SQL = """
                    create table if not exists cities (
                        id integer primary key auto_increment,
                        name varchar(50) not null
                    );
                """;
        final String CINEMAS_SQL = """
                    create table if not exists cinemas (
                        id integer primary key auto_increment,
                        name varchar(50) not null,
                        city_id integer,
                        foreign key (city_id) references cities(id) on delete cascade on update cascade 
                    );
                """;
        final String CINEMA_ROOMS_SQL = """
                    create table if not exists cinema_rooms (
                        id integer primary key auto_increment,
                        name varchar(50) not null,
                        row_quantity integer,
                        place_quantity integer,
                        cinema_id integer,
                        foreign key (cinema_id) references cinemas(id) on delete cascade on update cascade
                    );
                """;

        final String SEATS_SQL = """
                    create table if not exists seats (
                        id integer primary key auto_increment,
                        seat_row integer,
                        seat_place integer,
                        cinema_room_id integer,
                        foreign key (cinema_room_id) references cinema_rooms(id) on delete cascade on update cascade
                    );
                """;

        final String MOVIES_SQL = """
                    create table if not exists movies (
                        id integer primary key auto_increment,
                        title varchar(50) not null,
                        genre varchar(50) not null,
                        start_date timestamp,
                        end_date timestamp
                    );
                """;
        final String SEANCES_SQL = """
                    create table if not exists seances (
                        id integer primary key auto_increment,
                        movie_id integer,
                        foreign key (movie_id) references movies(id) on delete cascade on update cascade,
                        cinema_room_id integer,
                        foreign key (cinema_room_id) references cinema_rooms(id) on delete cascade on update cascade,
                        date_time timestamp
                    );
                """;
        final String USERS_SQL = """
                    create table if not exists users (
                        id integer primary key auto_increment,
                        name varchar(50),
                        surname varchar(50) not null,
                        username varchar(50) not null,
                        email varchar(50) not null,
                        password varchar(200) not null,
                        role varchar(50) not null
                    );
                """;
        final String TICKETS_SQL = """
                    create table if not exists tickets (
                        id integer primary key auto_increment,
                        seat_id integer,
                        foreign key (seat_id) references seats(id) on delete cascade on update cascade,
                        seance_id integer,
                        foreign key (seance_id) references seances(id) on delete cascade on update cascade,
                        price varchar(50),
                        state varchar(50),
                        discount double,
                        user_id integer,
                        foreign key (user_id) references users(id) on delete cascade on update cascade
                    );
                """;
        final String FAVOURITES_SQL = """
                create table if not exists favourite (
                id integer primary key auto_increment,
                add_date timestamp, 
                user_id integer,
                foreign key (user_id) references users(id) on delete cascade on update cascade,
                movie_id integer,
                foreign key (movie_id) references movies(id) on delete cascade on update cascade
                );
                """;


        jdbi.useHandle(handle -> {
            handle.execute(CITIES_SQL);
            handle.execute(CINEMAS_SQL);
            handle.execute(CINEMA_ROOMS_SQL);
            handle.execute(SEATS_SQL);
            handle.execute(MOVIES_SQL);
            handle.execute(SEANCES_SQL);
            handle.execute(USERS_SQL);
            handle.execute(TICKETS_SQL);
            handle.execute(FAVOURITES_SQL);
        });
    }


    public static void main(String[] args) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppSpringConfig.class);

        var cityService = context.getBean("cityService", CityService.class);
        var cinemaService = context.getBean("cinemaService", CinemaService.class);
        var cinemaRoomService = context.getBean("cinemaRoomService", CinemaRoomService.class);
        var movieService = context.getBean("movieService", MovieService.class);
        var seanceService = context.getBean("seanceService", SeanceService.class);
        var seatService = context.getBean("seatService", SeatService.class);
        var ticketService = context.getBean("ticketService", TicketService.class);
        var userService = context.getBean("userService", UserService.class);

        var jdbi = context.getBean("jdbi", Jdbi.class);
        createTables(jdbi);

        cinemaService.findAllCinemas();

//
//        initExceptionHandler(e -> System.out.println(e.getMessage()));
//        port(8081);
//
//
//        var cityRouting = new CityRouting(cityService);
//        cityRouting.initCityRoutes();
//
//        var cinemaRouting = new CinemaRouting(cinemaService);
//        cinemaRouting.initCinemaRoutes();
//
//        var cinemaRoomRouting = new CinemaRoomRouting(cinemaRoomService);
//        cinemaRoomRouting.initCinemaRoomRoutes();
//
//        var movieRouting = new MovieRouting(movieService);
//        movieRouting.initMovieRouting();
//
//        var seanceRouting = new SeanceRouting(seanceService);
//        seanceRouting.initSeanceRouting();
//
//        var seatRouting = new SeatRouting(seatService);
//        seatRouting.initSeatRouting();
//
//        var ticketRouting = new TicketRouting(ticketService);
//        ticketRouting.initUserRouting();
//
//        var userRouting = new UserRouting(userService);
//        userRouting.initUserRouting();



    }
}
