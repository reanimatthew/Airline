package com.example.airline;

import com.example.airline.api.EmployeeAPI;
import com.example.airline.mapper.EmployeeMapper;
import com.example.airline.mapper.TicketMapper;
import com.example.airline.model.*;
import com.example.airline.model.enumerations.PositionType;
import com.example.airline.model.enumerations.TicketType;
import com.example.airline.repositories.*;
import com.example.airline.service.EmployeeService;
import com.example.airline.service.TicketService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Component
//@Profile("!test")
public class DBFiller {
    private final AirportRepository airportRepository;
    private final EmployeeRepository employeeRepository;
    private final FlightRepository flightRepository;
    private final GroundServiceRepository groundServiceRepository;
    private final PassengerRepository passengerRepository;
    private final PlaneModelRepository planeModelRepository;
    private final PlaneRepository planeRepository;
    private final TicketRepository ticketRepository;
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    private final PasswordEncoder passwordEncoder;

    private final EmployeeAPI employeeAPI;
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;


    public DBFiller(AirportRepository airportRepository,
                    EmployeeRepository employeeRepository,
                    FlightRepository flightRepository,
                    GroundServiceRepository groundServiceRepository,
                    PassengerRepository passengerRepository,
                    PlaneModelRepository planeModelRepository,
                    PlaneRepository planeRepository,
                    TicketRepository ticketRepository,
                    TicketService ticketService,
                    TicketMapper ticketMapper,
                    PasswordEncoder passwordEncoder,
                    EmployeeAPI employeeAPI, EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.airportRepository = airportRepository;
        this.employeeRepository = employeeRepository;
        this.flightRepository = flightRepository;
        this.groundServiceRepository = groundServiceRepository;
        this.passengerRepository = passengerRepository;
        this.planeModelRepository = planeModelRepository;
        this.planeRepository = planeRepository;
        this.ticketRepository = ticketRepository;
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
        this.passwordEncoder = passwordEncoder;
        this.employeeAPI = employeeAPI;
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostConstruct
    public void init() {

        writeAirport(
                "ANR",
                "Antwerp",
                "Europe/Brussels",
                "25");

        writeAirport(
                "BRU",
                "Brussels",
                "Europe/Brussels",
                "100");

        writeAirport(
                "CRL",
                "Brussels",
                "Europe/Brussels",
                "75");

        writeAirport(
                "LGG",
                "Liege",
                "Europe/Brussels",
                "25");

        writeAirport(
                "OST",
                "Ostend–Bruges",
                "Europe/Brussels",
                "25");

        writeEmployee(
                "Efimov",
                "Mikhail",
                "Nikiforovich",
                50,
                "4310588734",
                "CAPTAIN",
                30
        );

        writeEmployee(
                "Nesterov",
                "Petr",
                "Nikolayevich",
                45,
                "4617596150",
                "CAPTAIN",
                25
        );

        writeEmployee(
                "Kokkinaki",
                "Vladimir",
                "Konstantinovich",
                40,
                "4536717871",
                "CAPTAIN",
                20
        );

        writeEmployee(
                "Gastello",
                "Nikolay",
                "Frantsevich",
                40,
                "4845615093",
                "FIRST_OFFICER",
                20
        );

        writeEmployee(
                "Maresyev",
                "Alexey",
                "Petrovich",
                35,
                "4251724057",
                "FIRST_OFFICER",
                15
        );

        writeEmployee(
                "Bong",
                "Richard",
                "Ira",
                35,
                "4964698184",
                "FIRST_OFFICER",
                15
        );

        writeEmployee(
                "Paltrow",
                "Gwyneth",
                "Kate Martin",
                30,
                "4980722624",
                "PURSER",
                10
        );

        writeEmployee(
                "Applegate",
                "Christina",
                "Robertovna",
                30,
                "4296590909",
                "PURSER",
                10
        );

        writeEmployee(
                "Bergen",
                "Candice",
                "Patricia",
                40,
                "4483757612",
                "PURSER",
                20
        );

        writeEmployee(
                "Ricci",
                "Christina",
                "Ralfovna",
                30,
                "4549796331",
                "FLIGHT_ATTENDANT",
                10
        );

        writeEmployee(
                "Robbie",
                "Margot",
                "Elise",
                25,
                "4929403885",
                "FLIGHT_ATTENDANT",
                5
        );

        writeEmployee(
                "Vanasse",
                "Karine",
                "Frantsevna",
                30,
                "4990548906",
                "FLIGHT_ATTENDANT",
                10
        );

        writeEmployee(
                "Amosov",
                "Nikolay",
                "Mihaylovich",
                50,
                "4714846045",
                "FLIGHT_MEDIC",
                30
        );

        writeEmployee(
                "Filatov",
                "Vladimir",
                "Petrovich",
                55,
                "4946246510",
                "FLIGHT_MEDIC",
                35
        );

        writeEmployee(
                "Botkin",
                "Sergey",
                "Petrovich",
                55,
                "4574759901",
                "FLIGHT_MEDIC",
                35
        );

        writeGroundService(
                "LucOil",
                "ANR",
                "REFUELING"
        );

        writeGroundService(
                "Rosneft",
                "BRU",
                "REFUELING"
        );

        writeGroundService(
                "Gazprom",
                "CRL",
                "REFUELING"
        );

        writeGroundService(
                "Shell",
                "LGG",
                "REFUELING"
        );

        writeGroundService(
                "BP",
                "OST",
                "REFUELING"
        );

        writeGroundService(
                "Coffeemania",
                "BRU",
                "CATERING"
        );

        writeGroundService(
                "YandexDostavka",
                "BRU",
                "LUGGAGE"
        );

        writeGroundService(
                "DeliveryPlane",
                "BRU",
                "AIR_CARGO"
        );

        writeGroundService(
                "BaskinRobbins",
                "BRU",
                "DEICING"
        );

        writeGroundService(
                "UnitedWaffles",
                "LGG",
                "CATERING"
        );

        writePassenger(
                "Musk",
                "Elon",
                "Reeve",
                51,
                "4444573420",
                "PLATINUM",
                "Musk1",
                "Elon123"
        );

        writePassenger(
                "Bezos",
                "Jeffrey",
                "Preston",
                58,
                "4775131644",
                "PLATINUM",
                "Musk",
                "Elon123"
        );

        writePassenger(
                "Arnault",
                "Bernard",
                "Ferret",
                73,
                "4731566367",
                "PLATINUM",
                "Musk",
                "Elon123"
        );

        writePassenger(
                "Gates",
                "William",
                "Henry",
                66,
                "4558203020",
                "GOLD",
                "Musk",
                "Elon123"
        );

        writePassenger(
                "Ellison",
                "Lawrence",
                "Joseph",
                77,
                "4789958875",
                "GOLD",
                "Musk",
                "Elon123"
        );

        writePassenger(
                "Page",
                "Lawrence",
                "Edward",
                49,
                "4712579956",
                "GOLD",
                "Musk",
                "Elon123"
        );

        writePassenger(
                "Zuckerberg",
                "Mark",
                "Elliot",
                38,
                "4858277163",
                "SILVER",
                "Musk",
                "Elon123"
        );

        writePassenger(
                "Brin",
                "Sergey",
                "Mihaylovich",
                48,
                "4619104754",
                "SILVER",
                "Musk",
                "Elon123"
        );

        writePassenger(
                "Buffett",
                "Warren",
                "Edward",
                91,
                "4651930539",
                "SILVER",
                "Musk",
                "Elon123"
        );

        writePassenger(
                "Ballmer",
                "Steven",
                "Anthony",
                66,
                "4928363524",
                "STANDARD",
                "Musk",
                "Elon123"
        );

        writePassenger(
                "Meyers",
                "Francoise",
                "Bettencourt",
                69,
                "4321385967",
                "STANDARD",
                "Musk",
                "Elon123"
        );

        writePassenger(
                "Ambani",
                "Mukesh",
                "Dhirubhai",
                65,
                "4614455006",
                "STANDARD",
                "Musk",
                "Elon123"
        );

        writePlaneModel(
                "Airbus",
                "A320",
                "140",
                "5"
        );

        writePlaneModel(
                "Airbus",
                "A321",
                "180",
                "5"
        );

        writePlaneModel(
                "Boeing",
                "737-600",
                "130",
                "5"
        );

        writePlaneModel(
                "Boeing",
                "737-700",
                "150",
                "5"
        );

        writePlaneModel(
                "Sukhoi",
                "Superjet 100",
                "90",
                "4"
        );

        writePlane(
                "Superjet 100",
                "RA-89014",
                "FLIGHT",
                "BRU",
                "Rosneft"
        );

        writePlane(
                "A320",
                "TC-NCZ",
                "PARK",
                "LGG",
                "Shell"
        );

        writePlane(
                "737-600",
                "N873DN",
                "SERVICE",
                "ANR",
                "LucOil"
        );

        writeFlight(
                "OSTBRU001",
                "OST",
                "BRU",
                "2022-08-15T07:00:00",
                "2022-08-15T07:45:00",
                "RA-89014",
                100.
        );

        writeFlight(
                "LGGCRL002",
                "LGG",
                "CRL",
                "2022-08-15T08:15:00",
                "2022-08-15T09:10:00",
                "TC-NCZ",
                90.
        );

        writeFlight(
                "ANROST003",
                "ANR",
                "OST",
                "2022-08-15T09:50:00",
                "2022-08-15T10:35:00",
                "N873DN",
                110.
        );

        writeFlight(
                "CRLOST004",
                "CRL",
                "OST",
                "2022-08-15T12:00:00",
                "2022-08-15T12:50:00",
                "TC-NCZ",
                95.
        );

        writeFlight(
                "OSTCRL006",
                "OST",
                "CRL",
                "2022-08-15T14:30:00",
                "2022-08-15T15:15:00",
                "N873DN",
                100.
        );

        writeFlight(
                "BRULGG005",
                "BRU",
                "LGG",
                "2022-08-15T12:05:00",
                "2022-08-15T13:00:00",
                "RA-89014",
                105.
        );

        //далее моки flight
        writeFlight(
                "BRULGG005-02",
                "BRU",
                "LGG",
                "2022-08-15T12:05:00",
                "2022-08-15T13:00:00",
                "RA-89014",
                100.
        );

        writeFlight(
                "BRULGG005-03",
                "BRU",
                "LGG",
                "2022-08-15T12:05:00",
                "2022-08-15T13:00:00",
                "RA-89014",
                110.
        );

        writeFlight(
                "BRULGG005-04",
                "BRU",
                "LGG",
                "2022-08-15T12:05:00",
                "2022-08-15T13:00:00",
                "RA-89014",
                105.
        );

        writeFlight(
                "BRULGG005-05",
                "BRU",
                "LGG",
                "2022-08-15T12:05:00",
                "2022-08-15T13:00:00",
                "RA-89014",
                80.
        );



        writeTicket(
                "LFZQ80",
                "4444573420",
                "FIRST",
                "OSTBRU001"
        );

        writeTicket(
                "IAG2EP",
                "4558203020",
                "BUSINESS",
                "OSTBRU001"
        );

        writeTicket(
                "LF6ZYN",
                "4858277163",
                "ECONOMY",
                "OSTBRU001"
        );

        writeTicket(
                "33MXA8",
                "4928363524",
                "ECONOMY",
                "OSTBRU001"
        );

        writeTicket(
                "B0RNP0",
                "4558203020",
                "BUSINESS",
                "BRULGG005"
        );

        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(employee -> employee.setFlight(null));

        fillFlightWithCrew("4310588734", "OSTBRU001");
        fillFlightWithCrew("4845615093", "OSTBRU001");
        fillFlightWithCrew("4980722624", "OSTBRU001");
        fillFlightWithCrew("4549796331", "OSTBRU001");

        fillFlightWithCrew("4617596150", "LGGCRL002");
        fillFlightWithCrew("4251724057", "LGGCRL002");
        fillFlightWithCrew("4296590909", "LGGCRL002");
        fillFlightWithCrew("4990548906", "LGGCRL002");

//        List<Flight> flights = flightRepository.findByDepartureAirport_City("Brussels", PageRequest.of(0, 2, Sort.by("basePrice")));
//        List<Flight> flights1 = flightRepository.findByArrivalAirport_City("Liege", PageRequest.of(0, 2, Sort.by("basePrice")));
//
//        flights.forEach(System.out::println);
//        System.out.println("-----------------");
//        flights1.forEach(System.out::println);
//        System.out.println("-----------------");
//        System.out.println("-----------------");
//        ZonedDateTime startDate = ZonedDateTime.of(LocalDateTime.parse("2022-08-15T00:00:00"), ZoneId.of("Europe/Brussels"));
//        ZonedDateTime finishDate = ZonedDateTime.of(LocalDateTime.parse("2022-08-15T23:59:59"), ZoneId.of("Europe/Brussels"));
//        List<Flight> flights2 = flightRepository.findByDepartureDateOnly(LocalDate.of(2022, 8, 15), PageRequest.of(0, 4, Sort.by("basePrice")));
//        List<Flight> flights3 = flightRepository.findByArrivalDateBetween(startDate, finishDate, PageRequest.of(1, 2, Sort.by("basePrice")));
//        flights2.forEach(System.out::println);
//        System.out.println("-----------------");
//        flights3.forEach(System.out::println);

//        ticketService.getNewTicketDTO("4444573420", 1L, "FIRST");
        System.out.println(ticketRepository.findByTicketNumber("LFZQ80").orElseThrow().getTicketNumber());
        System.out.println("Загрузка завершена!");

    }


    private Airport writeAirport(String iata,
                                 String city,
                                 String zoneId,
                                 String capacity
    ) {
        Airport airport = new Airport();
        airport.setIata(iata);
        airport.setCity(city);
        airport.setZoneId(zoneId);
        airport.setCapacity(new BigInteger(capacity));
        airportRepository.save(airport);
        return airport;
    }

    private Employee writeEmployee(String surname,
                                   String name,
                                   String patronymic,
                                   Integer age,
                                   String passport,
                                   String position,
                                   Integer experience
    ) {
        Employee employee = new Employee();
        employee.setSurname(surname);
        employee.setName(name);
        employee.setPatronymic(patronymic);
        employee.setAge(new BigDecimal(age));
        employee.setPassport(passport);
        employee.setPosition(position);
        employee.setSalary(PositionType.valueOf(position).getBaseSalary());
        employee.setExperience(new BigDecimal(experience));
        employeeRepository.save(employee);
        return employee;
    }

    private GroundService writeGroundService(String name,
                                             String airportIata,
                                             String groundServiceType
    ) {
        GroundService groundService = new GroundService();
        groundService.setName(name);
        groundService.setAirport(
                airportRepository
                        .findAirportByIata(airportIata)
                        .orElseThrow()
        );

        groundService.setGroundServiceType(groundServiceType);

        groundServiceRepository.save(groundService);
        return groundService;
    }

    private Passenger writePassenger(String surname,
                                     String name,
                                     String patronymic,
                                     Integer age,
                                     String passport,
                                     String level,
                                     String login,
                                     String password
    ) {
        Passenger passenger = new Passenger();
        passenger.setSurname(surname);
        passenger.setName(name);
        passenger.setPatronymic(patronymic);
        passenger.setAge(new BigDecimal(age));
        passenger.setPassport(passport);
        passenger.setLevel(level);
        passenger.setLogin(login);
        passenger.setPassword(passwordEncoder.encode(password));
        passengerRepository.save(passenger);
        return passenger;
    }

    private PlaneModel writePlaneModel(String manufacturer,
                                       String model,
                                       String numberOfSeat,
                                       String numberOfCrewMembers
    ) {
        PlaneModel planeModel = new PlaneModel();
        planeModel.setManufacturer(manufacturer);
        planeModel.setModel(model);
        planeModel.setNumberOfSeat(numberOfSeat);
        planeModel.setNumberOfCrewMembers(numberOfCrewMembers);
        planeModelRepository.save(planeModel);
        return planeModel;
    }

    private Plane writePlane(String planeModel,
                             String registrationNumber,
                             String status,
                             String airportIata,
                             String groundServiceName
    ) {
        Plane plane = new Plane();

        plane.setPlaneModel(
                planeModelRepository
                        .findPlaneModelByModel(planeModel)
                        .orElseThrow()
        );

        plane.setRegistrationNumber(registrationNumber);

        plane.setStatus(status);

        plane.setAirport(
                airportRepository
                        .findAirportByIata(airportIata)
                        .orElseThrow()
        );

        plane.setGroundService(
                groundServiceRepository
                        .findGroundServiceByName(groundServiceName)
                        .orElseThrow()
        );
        planeRepository.save(plane);
        return plane;
    }

    private Flight writeFlight(String flightNumber,
                               String departureAirportIata,
                               String arrivalAirportIata,
                               String departureDate,
                               String arrivalDate,
                               String planeRegistrationNumber,
                               Double basePrice
    ) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);

        flight.setDepartureAirport(
                airportRepository
                        .findAirportByIata(departureAirportIata)
                        .orElseThrow()
        );

        flight.setArrivalAirport(
                airportRepository
                        .findAirportByIata(arrivalAirportIata)
                        .orElseThrow()
        );

        flight.setDepartureDate(ZonedDateTime.of(LocalDateTime.parse(departureDate), flight.getArrivalAirport().getZoneId()));
        flight.setArrivalDate(ZonedDateTime.of(LocalDateTime.parse(arrivalDate), flight.getArrivalAirport().getZoneId()));
        flight.setDepartureDateOnly(LocalDate.of(flight.getDepartureDate().getYear(), flight.getDepartureDate().getMonth(), flight.getDepartureDate().getDayOfMonth()));
        flight.setPlane(
                planeRepository
                        .findPlaneByRegistrationNumber(planeRegistrationNumber)
                        .orElseThrow()
        );

        flight.setBasePrice(new BigDecimal(basePrice));

        flightRepository.save(flight);
        return flight;
    }

    private Ticket writeTicket(String ticketNumber,
                               String passengerPassport,
                               String ticketType,
                               String flight
    ) {
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(ticketNumber);
        Passenger passenger = passengerRepository.findPassengerByPassport(passengerPassport).orElseThrow();
        ticket.setPassenger(passenger);
        ticket.setTicketType(ticketType);
        ticket.setFlight(flightRepository.findFlightByFlightNumber(flight).orElseThrow());
        BigDecimal basePrice = flightRepository.findFlightByFlightNumber(flight).orElseThrow().getBasePrice();
        BigDecimal sum = basePrice.multiply(TicketType.valueOf(ticketType).getExtraCharge()).multiply(passenger.getLevel().getDiscount());
        ticket.setPrice(sum);
        ticketRepository.save(ticket);
        return ticket;
    }

    private Employee fillFlightWithCrew(String passport,
                                        String flightNumber
    ) {
        Employee employee = employeeRepository.findEmployeeByPassport(passport).orElseThrow();
        if (employee.getFlight() != null)
            throw new RuntimeException("Employee already on flight.");
        employee.setFlight(flightRepository.findFlightByFlightNumber(flightNumber).orElseThrow());
        employeeRepository.save(employee);
        return employee;
    }
}
