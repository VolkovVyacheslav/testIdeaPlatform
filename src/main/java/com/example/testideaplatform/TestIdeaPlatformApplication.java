package com.example.testideaplatform;

import com.example.testideaplatform.model.Ticket;
import com.example.testideaplatform.model.Tickets;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;


public class TestIdeaPlatformApplication {

    public static void main(String[] args) {
        Tickets tickets =  readJson();
        minimumOnCarrier(tickets);
        System.out.println("Average for all tikets price is:" + average(tickets) +" median price is: " +  median(tickets) + " and different is: " + (average(tickets).subtract(median(tickets))));
    }

    private static Tickets readJson(){
        Tickets tickets = new Tickets();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        try{
            tickets = mapper.readValue(Files.readAllBytes(Paths.get("src/main/resources/tickets.json")), Tickets.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return tickets;
    }

    private static void minimumOnCarrier(Tickets tickets) {
        if (tickets.getTickets().isEmpty()) {
            return;
        }
        Map<String, List<Duration>> results = new HashMap<>();
        for (Ticket ticket : tickets.getTickets()) {
            if(ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV")) {
                if (!results.containsKey(ticket.getCarrier())) {
                    List<Duration> minimumTime = new ArrayList<>();
                    Duration flightTime = Duration.between(ticket.getDepartureTime(), ticket.getArrivalTime());
                    minimumTime.add(flightTime);
                    results.put(ticket.getCarrier(), minimumTime);
                } else {
                    results.get(ticket.getCarrier()).add(Duration.between(ticket.getDepartureTime(), ticket.getArrivalTime()));
                    Collections.sort(results.get(ticket.getCarrier()));
                }
            }
        }
        for (Map.Entry<String, List<Duration>> result : results.entrySet()) {
            System.out.println("For Carrier " + result.getKey() + " is minimum flight time: " + result.getValue().get(0));
        }
    }

    private static  BigDecimal median(Tickets tickets){
        List<BigDecimal> prices = getPrices(tickets);
        Collections.sort(prices);
        return prices.size()%2==0 ?
                ((prices.get(prices.size()/2-1).add(prices.get(prices.size()/2))).divide(new BigDecimal(2))):
                prices.get(prices.size()/2);
    }

    private static BigDecimal average(Tickets tickets){
        List<BigDecimal> prices = getPrices(tickets);
        BigDecimal result = new BigDecimal(0);
        for(BigDecimal price: prices){
            result =  result.add(price);
        }
        return result.divide(new BigDecimal(prices.size()), RoundingMode.HALF_UP);
    }

    private static List<BigDecimal> getPrices(Tickets tickets){
        return tickets.getTickets().stream().
                filter(ticket -> ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV")).
                map(ticket -> ticket.getPrice()).collect(Collectors.toList());
    }

}
