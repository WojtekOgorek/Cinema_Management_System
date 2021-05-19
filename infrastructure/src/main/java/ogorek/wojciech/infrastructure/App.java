package ogorek.wojciech.infrastructure;

import ogorek.wojciech.infrastructure.web.error.ErrorRouting;
import ogorek.wojciech.infrastructure.web.routing.*;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

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

        /*
         * -----------------JDBI-----------------
         *
         */

        var jdbi = context.getBean("jdbi", Jdbi.class);
        createTables(jdbi);


        /*
         * -----------------ROUTES-----------------
         *
         */

        initExceptionHandler(e -> System.out.println(e.getMessage()));
        port(8081);

        var errorRouting = context.getBean("errorRouting", ErrorRouting.class);
        var securityFilter = context.getBean("securityFilter", SecurityFilter.class);
        var cityRouting = context.getBean("cityRouting", CityRouting.class);
        var cinemaRouting = context.getBean("cinemaRouting", CinemaRouting.class);
        var cinemaRoomRouting = context.getBean("cinemaRoomRouting", CinemaRoomRouting.class);
        var movieRouting = context.getBean("movieRouting", MovieRouting.class);
        var seanceRouting = context.getBean("seanceRouting", SeanceRouting.class);
        var seatRouting = context.getBean("seatRouting", SeatRouting.class);
        var ticketRouting = context.getBean("ticketRouting", TicketRouting.class);
        var userRouting = context.getBean("userRouting", UserRouting.class);

        securityFilter.initSecurityFilter();
        errorRouting.initErrorRoutes();
        cityRouting.initCityRoutes();
        cinemaRouting.initCinemaRoutes();
        cinemaRoomRouting.initCinemaRoomRoutes();
        movieRouting.initMovieRouting();
        seanceRouting.initSeanceRouting();
        seatRouting.initSeatRouting();
        ticketRouting.initUserRouting();
        userRouting.initUserRouting();

    }
}
